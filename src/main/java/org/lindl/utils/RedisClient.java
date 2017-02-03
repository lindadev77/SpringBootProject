package org.lindl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by lin on 2017/2/2.
 */
@Component
public class RedisClient {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JedisPool jedisPool;

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }
        catch (Exception e){
            logger.info(e.toString());
        }
        finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }
        catch (Exception e){
            logger.info(e.toString());
        }
        finally {
            //返还到连接池
            jedis.close();
        }
        return "fail";
    }
}
