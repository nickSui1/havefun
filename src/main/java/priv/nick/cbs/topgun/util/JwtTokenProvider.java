package priv.nick.cbs.topgun.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import priv.nick.cbs.topgun.constant.SecurityConstants;

import java.security.Key;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


/**
 * @author nick.sui
 * @since 2024-08-19
 * JWTToken 生成工具类
 */
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    // 生成 JWT token
    public String generateToken(String username, List<String> rows,String grantType){
        long expirationConstant = 0L;
        if(grantType.equals(SecurityConstants.ACCESS_TOKEN)){
            expirationConstant = SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME;
        }else if(grantType.equals(SecurityConstants.REFRESH_TOKEN)){
            expirationConstant = SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME;
        }else{
            throw new IllegalArgumentException("grantType is invalidate");
        }
        ZonedDateTime expirationZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusSeconds(expirationConstant);
        Date expirationTime = Date.from(expirationZonedDateTime.toInstant());

        String token = Jwts.builder()
                .setSubject(username)
                .claim(SecurityConstants.TOKEN_CLAIM_ROLE, rows)
                .setIssuedAt(Date.from(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toInstant()))
                .setExpiration(expirationTime)
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    public List<String> getRoles(String token){
        return (List<String>)extractClaims(token).get(SecurityConstants.TOKEN_CLAIM_ROLE);
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parser()
                                .setSigningKey(key())
                                .parseClaimsJws(token)
                                .getBody();
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();
            List<String>roles = this.getRoles(token);
            return (username.equals(username) && !expiration.before(new Date()) && (roles!=null && !roles.isEmpty()));
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public Authentication getAuthentication(String token){
        String username = this.getUsername(token);
        List<String> roles = this.getRoles(token);
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role->authorities.add(new SimpleGrantedAuthority(role)));

        return new UsernamePasswordAuthenticationToken(username, token, authorities);
    }
}
