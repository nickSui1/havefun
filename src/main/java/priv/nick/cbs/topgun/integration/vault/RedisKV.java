package priv.nick.cbs.topgun.integration.vault;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("redis-cbs")
public class RedisKV {
    private String password;
}
