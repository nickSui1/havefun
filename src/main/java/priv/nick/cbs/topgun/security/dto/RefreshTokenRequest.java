package priv.nick.cbs.topgun.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RefreshTokenRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6920326777868125944L;

    @NotBlank(message = "RefreshToken can not be blank")
    private String refreshToken;
}
