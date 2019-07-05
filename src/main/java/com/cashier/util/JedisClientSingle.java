package com.cashier.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName: JedisClientSingle
 * @description  JedisPoolUtils
 * @author zhou jiaxin
 * @createDate 2018年12月26日
 */
@Service
public class JedisClientSingle {

    @Autowired
    private JedisPool jedisPool;

    public  String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        try {
            if (string ==null || string =="") {
                return null;
            }
			byte[] b=string.getBytes("utf-8");
			if(b==null){
				return null;
			}
			return new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            jedis.close();
        }
       return "";
    }


    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String string=null;
		try {
			string = jedis.set(key.getBytes("utf-8"), value.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    jedis.close();
        }
        return string;
    }
    // 设置键的过期时间
    public String setExpire(String key, Integer value) {
        Jedis jedis = jedisPool.getResource();
        String string=null;
        try {
            jedis.expire(key, value);
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            jedis.close();
        }
        return string;
    }
    
    
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = 0L;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            jedis.close();
        }
        return result;
    }
    
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }


    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }


    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }


    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }


    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }
    
    
    
    

}
