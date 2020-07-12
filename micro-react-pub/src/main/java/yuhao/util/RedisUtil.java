package yuhao.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author 喻浩
 * @create 2020-02-12-13:25
 */
@Slf4j
@Component
public class RedisUtil{

    @Autowired
    private JedisPool jedisPool;


    /**
     * 设置发送的验证码过期时间
     * @return
     */
    public boolean saveSendCode(String email,String code, int indexDB){
        Jedis jedis = null;
        Long expire = 0L;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDB);
            jedis.set(email, code);
            expire = jedis.expire(email, 60);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedisPool, jedis);
        }
        return expire==1L;
    }

    /**
     * <p>
     * 通过key获取储存在redis中的value
     * </p>
     * <p>
     * 并释放连接
     * </p>
     *
     * @param key
     * @param indexDb 选择redis库 0-15
     * @return 成功返回value 失败返回null
     */
    public String get(String key,int indexDb) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            value = jedis.get(key);
            log.info(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 顺便设置过期时间,默认0号数据库
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public String setByExpire(String key, String value, int expireTime){
        return setByExpire(key,value,expireTime,0);
    }

    /**
     * 自定义数据库设置数据
     * @param key
     * @param value
     * @param expireTime
     * @param indexDb
     * @return
     */
    public String setByExpire(String key, String value, int expireTime, int indexDb){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            result = jedis.setex(key, expireTime, value);
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return result;
    }

    /**
     * 默认选择0号db
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            value = jedis.get(key);
            log.info(value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * <p>
     * 通过key获取储存在redis中的value
     * </p>
     * <p>
     * 并释放连接
     * </p>
     *
     * @param key
     * @param indexdb 选择redis库 0-15
     * @return 成功返回value 失败返回null
     */
    public byte[] get(byte[] key,int indexdb) {
        Jedis jedis = null;
        byte[] value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.get(key);
        } catch (Exception e) {

            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }
    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param indexdb 选择redis库 0-15
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 默认存0号db
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(0);
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param indexdb 选择redis库 0-15
     * @return 成功 返回OK 失败返回 0
     */
    public String set(byte[] key, byte[] value,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {

            log.error(e.getMessage());
            return "0";
        } finally {
            returnResource(jedisPool, jedis);
        }
    }
    /**
     * <p>
     * 删除指定的key,也可以传入一个包含key的数组
     * </p>
     *
     * @param keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {

            log.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 是否存在此key
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 返还到连接池
     *
     * @param jedisPool
     * @param jedis
     */
    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        try {
            if(jedis!=null){
                jedis.close();//替代方法
                log.info("returnToPool success ");
            }
        } catch (Exception e) {
            log.error("returnToPool error "+e.toString());
        }
    }

}
