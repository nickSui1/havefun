package priv.nick.cbs.topgun.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.nick.cbs.topgun.util.SnowflakeIdWorker;

@Configuration
public class IDGenerateConfiguration {
    @Bean
    public SnowflakeIdWorker SnowflakeIdWorker() {
        return new SnowflakeIdWorker(1L, 1L);
    }
}
