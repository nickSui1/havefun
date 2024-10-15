package priv.nick.cbs.topgun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import priv.nick.cbs.topgun.config.security.AuthenticationFilter;
import priv.nick.cbs.topgun.util.SnowflakeIdWorker;

@Configuration
public class AppConfig {
    @Bean
    public SnowflakeIdWorker SnowflakeIdWorker() {
        return new SnowflakeIdWorker(1L, 1L);
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
