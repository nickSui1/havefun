package priv.nick.cbs.topgun.constant;

/**
 * @author nick.sui
 * @since 2024-08-21
 * Security模块常量
 */
public final class SecurityConstants {
    public SecurityConstants() throws InstantiationException {
        throw new InstantiationException("Failed to create a new instance of the constant class");
    }
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 1L;
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 3L;
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String TOKEN_CLAIM_ROLE = "role";
}
