package priv.nick.cbs.topgun.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private List<String> roles;
}
