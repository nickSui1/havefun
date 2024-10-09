package priv.nick.cbs.topgun.security.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.nick.cbs.topgun.dto.VaultSecret;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;
import priv.nick.cbs.topgun.security.dto.RefreshTokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenResponse;
import priv.nick.cbs.topgun.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class TokenResource {

    private TokenService tokenService;

//    @Value("${spring.datasource.username}")
//    private String sqlUsername;
    private final VaultSecret vaultSecret;

    public TokenResource(TokenService tokenService, VaultSecret vaultSecret) {
        this.tokenService = tokenService;
        this.vaultSecret = vaultSecret;
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody @Validated TokenRequest tokenRequest){
//        String s = sqlUsername;
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
    public ResponseEntity<?> invalidToken(){
        return null;
    }

    @GetMapping("/credentials")
    public ResponseEntity<?> vaultSecret(){
        return ResponseEntity.ok(new ResponseInfo("seccess",vaultSecret));
    }
}
