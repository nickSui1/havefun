package priv.nick.cbs.topgun.security.service;

import priv.nick.cbs.topgun.security.dto.RefreshTokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenRequest;
import priv.nick.cbs.topgun.security.dto.TokenResponse;

public interface TokenService {
    public TokenResponse generateToken(TokenRequest tokenRequest);
    public TokenResponse refreshToken(RefreshTokenRequest refreshToken);
    public void invalidateToken();
}
