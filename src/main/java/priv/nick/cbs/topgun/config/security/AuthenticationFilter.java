package priv.nick.cbs.topgun.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import priv.nick.cbs.topgun.constant.RedisKeyConstants;
import priv.nick.cbs.topgun.integration.RedisService;
import priv.nick.cbs.topgun.util.JwtTokenProvider;

import java.io.IOException;
import java.util.List;

/**
 * @author nick.sui
 * @since 2024-8-18
 */
public class AuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;
    private RedisService redisService;
    @Autowired
    public AuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                    RedisService redisService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisService = redisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Filtering request for: " + request.getRequestURI());
        // Skip authentication for certain paths
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/auth/token")) {
            filterChain.doFilter(request, response);
            return; // Skip further processing for these endpoints
        }
        String token = this.getAuthTokenFromHeader(request);

        //Check whether the blackList contains the token
        Object token_black_list_obj = redisService.lRange(RedisKeyConstants.REDIS_BLACK_LIST_KEY,0,-1);
        if(token_black_list_obj != null && token_black_list_obj instanceof List<?>){
            List<String> token_black_list = (List<String>)token_black_list_obj;
            if(token_black_list.contains(token)){
                filterChain.doFilter(request, response);
                return;
            }
        }

        //Authentication
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAuthTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
