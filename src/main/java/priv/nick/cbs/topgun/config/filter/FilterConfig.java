package priv.nick.cbs.topgun.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nics.sui
 * @since 2024-08-18
 */
@Configuration
public class FilterConfig {
    /**
     * 用于注册Filter的Bean，可自定义Filter instances的配置，可以对Filters进行排序，应用于哪些URL patterns等进行配置
     * @return
     */
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
