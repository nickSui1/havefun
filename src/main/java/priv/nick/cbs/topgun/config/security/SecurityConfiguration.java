package priv.nick.cbs.topgun.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import priv.nick.cbs.topgun.config.security.serviceImpl.CustomAccessDeniedHandlerImpl;
import priv.nick.cbs.topgun.config.security.serviceImpl.CustomAuthenticationEntryPointImpl;
import priv.nick.cbs.topgun.integration.RedisService;
import priv.nick.cbs.topgun.security.service.CustomUserDetailsService;
import priv.nick.cbs.topgun.util.JwtTokenProvider;

/**
 * @author nick.sui
 * @since 2024-08-24
 * Web Security Configuration
 * WebSecurityConfigurerAdapter has been deprecated In Spring Security 5.7.0-M2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderBean());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public AuthenticationFilter authenticationFilter(){
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtTokenProvider, redisService);
        return authenticationFilter;
    }

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
        http.cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/auth/token").permitAll()
                         .requestMatchers("/auth/refresh_token").permitAll()
                         .anyRequest().authenticated();
            })
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint())
                            .accessDeniedHandler(accessDeniedHandler())
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
