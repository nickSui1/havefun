package priv.nick.cbs.topgun.constant;

public final class RedisKeyConstants {
    public RedisKeyConstants() throws InstantiationException {
        throw new InstantiationException("Failed to create a new instance of the constant class");
    }
    public static final String REDIS_BLACK_LIST_KEY = "cbs:auth:token";
}
