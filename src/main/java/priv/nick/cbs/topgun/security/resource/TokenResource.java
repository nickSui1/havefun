package priv.nick.cbs.topgun.security.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.nick.cbs.topgun.security.dto.TokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenResponse;
import priv.nick.cbs.topgun.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class TokenResource {

    private TokenService tokenService;

    public TokenResource(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody @Validated TokenRequest tokenRequest){
        TokenResponse tokenResponse = tokenService.generateToken(tokenRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken){
        TokenResponse tokenResponse = tokenService.refreshToken(refreshToken);
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
}
