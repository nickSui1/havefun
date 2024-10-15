package priv.nick.cbs.topgun.security.resource;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;
import priv.nick.cbs.topgun.integration.vault.RedisKV;
import priv.nick.cbs.topgun.security.dto.RefreshTokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenResponse;
import priv.nick.cbs.topgun.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class TokenResource {
    private TokenService tokenService;
    private final RedisKV redisKV;

    public TokenResource(TokenService tokenService, RedisKV redisKV) {
        this.tokenService = tokenService;
        this.redisKV = redisKV;
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody @Validated TokenRequest tokenRequest){
        TokenResponse tokenResponse = tokenService.generateToken(tokenRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshToken(@RequestBody @Validated RefreshTokenRequest refreshTokenRequest){
        TokenResponse tokenResponse = tokenService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    /**
     * Logs the current user out by invalidating their token
     * @return
     */
    @GetMapping("/invalid_token")
    public ResponseEntity<?> invalidToken(HttpServletRequest request){
        String tokenHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            tokenService.invalidateToken(token);

            return ResponseEntity.ok(new ResponseInfo("invalid_token success",200));
        }
        return ResponseEntity.badRequest().body(new ResponseInfo("invalid_token",400));
    }

    @GetMapping("/credentials")
    public ResponseEntity<?> vaultSecret(){
        return ResponseEntity.ok(new ResponseInfo("seccess",redisKV));
    }
}
