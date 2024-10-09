package priv.nick.cbs.topgun.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("credentials-client")
public class VaultSecret {
    private String username;

    private String password;
}
