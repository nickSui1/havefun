package priv.nick.cbs.topgun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.nick.cbs.topgun.config.security.AuthenticationFilter;
import priv.nick.cbs.topgun.util.SnowflakeIdWorker;

@Configuration
public class AppConfig {
    @Bean
    public AuthenticationFilter authenticationFilterBean(){
        return new AuthenticationFilter();
    }

    @Bean
    public SnowflakeIdWorker SnowflakeIdWorker() {
        return new SnowflakeIdWorker(1L, 1L);
    }
}
