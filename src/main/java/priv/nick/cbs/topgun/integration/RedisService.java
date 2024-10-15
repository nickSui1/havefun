package priv.nick.cbs.topgun.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public Object lRange(String key, long start, long end){
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void lPush(String key, List<Object> value){
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    public void saveWithExpiration(String key, Object value, long timeout, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public void saveInHash(String hashKey, String key, Object value){
        redisTemplate.opsForHash().put(hashKey, key, value);
    }

    public Object getFromHash(String hashKey, String key){
        return redisTemplate.opsForHash().get(hashKey, key);
    }
}
