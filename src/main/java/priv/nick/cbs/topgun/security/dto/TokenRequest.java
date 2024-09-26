package priv.nick.cbs.topgun.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TokenRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2570401360767138297L;
    @NotBlank(message = "username can not be blank")
    private String username;
    @NotBlank(message = "password can not be blank")
    private String password;
}
