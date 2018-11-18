package github.weizibin.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisSingleInstanceLock implements DistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String COMPARE_AND_DELETE =
            "if redis.call('get',KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "    return redis.call('del',KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";

    public boolean tryLock(String resource, String value, long expire, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(resource,
                value, expire, timeUnit);
    }

    public void lock(String resource, String value, long expire, TimeUnit expireTimeUnit,
                     long duration, TimeUnit durationTimeUnit) {
        while (!tryLock(resource, value, expire, expireTimeUnit)) {
            try {
                durationTimeUnit.sleep(duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void release(String resource, String exceptValue) {
        List<String> keys = Collections.singletonList(resource);
        redisTemplate.execute(new DefaultRedisScript<>(COMPARE_AND_DELETE, Long.class),
                keys, exceptValue);
    }
}
