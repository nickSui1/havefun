package priv.nick.cbs.topgun.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import priv.nick.cbs.topgun.config.security.serviceImpl.CustomAccessDeniedHandlerImpl;
import priv.nick.cbs.topgun.config.security.serviceImpl.CustomAuthenticationEntryPointImpl;

/**
 * @author nick.sui
 * @since 2024-08-24
 * Web Security Configuration
 * WebSecurityConfigurerAdapter has been deprecated In Spring Security 5.7.0-M2
 */
@Configuration
public class SecurityConfiguration {
    @Autowired
    private CorsFilter corsFilter;

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPointImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/token").permitAll()
                            .requestMatchers("/logout").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint())
                                .accessDeniedHandler(accessDeniedHandler())
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
