package github.weizibin.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {

    boolean tryLock(String resource, String value, long expire, TimeUnit timeUnit);

    void lock(String resource, String value, long expire, TimeUnit expireTimeUnit,
              long duration, TimeUnit durationTimeUnit);

    void release(String resource, String exceptValue);

}
