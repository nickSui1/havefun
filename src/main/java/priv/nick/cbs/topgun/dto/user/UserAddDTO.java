package priv.nick.cbs.topgun.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserAddDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5110163399343706511L;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String mobilePhone;
    @NotBlank
    private String email;
}
