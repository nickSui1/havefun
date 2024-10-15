package priv.nick.cbs.topgun.integration;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class CustomStringOrJsonSerializer implements RedisSerializer<Object> {
    private final StringRedisSerializer stringSerializer = new StringRedisSerializer();
    private final GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

    @Override
    public byte[] serialize(Object value) throws SerializationException {
        if (value instanceof String) {
            // Handle plain string without serializing to JSON (no double quotes)
            return stringSerializer.serialize((String) value);
        } else {
            // Handle objects as JSON
            return jsonSerializer.serialize(value);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        // First try to deserialize as a string
        String stringValue = stringSerializer.deserialize(bytes);
        if (stringValue != null) {
            return stringValue;
        }
        // If not a string, deserialize as JSON
        return jsonSerializer.deserialize(bytes);
    }
}
