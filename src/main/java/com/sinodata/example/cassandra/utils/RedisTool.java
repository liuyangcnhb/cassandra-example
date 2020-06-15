package com.sinodata.example.cassandra.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FileName: RedisTool
 * Author:  liuyang
 * Date:    2020/6/1410:31
 * Description: Rdis工具类
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Service
public class RedisTool {

    /**
     * 释放锁lua脚本
     */
    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    /**
     * 释放锁成功返回值
     */
    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;


    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间, 毫秒
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(RedisTemplate<String, String> redisTemplate, String lockKey, String requestId, int expireTime) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.MILLISECONDS);
    }


    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static Boolean releaseDistributedLock(RedisTemplate<String, String> redisTemplate, String lockKey, String requestId) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT, Long.class);
        List<String> keys = Collections.singletonList(lockKey);
        Long result = redisTemplate.execute(redisScript, keys, requestId);
        return RELEASE_LOCK_SUCCESS_RESULT.equals(result)? true: false;
    }

}
