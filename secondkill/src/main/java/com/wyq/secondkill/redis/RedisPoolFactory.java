package com.wyq.secondkill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: RedisPoolFactory
 * @description: RedisPool
 * @date 2019/2/14 21:14
 */
@Service
public class RedisPoolFactory {
    @Autowired
    private RedisConfig redisConfig;

    //通过这种方式，将JedisPool注入到spring容器中
    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(),
                redisConfig.getPort(), redisConfig.getTimeout() * 1000,
                redisConfig.getPassword(), 0);
        //上面这个0表示0数据库，redis默认支持16个库，从0到15

        return jp;
    }
}
