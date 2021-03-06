package com.aiqiyi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key,Object value){
        boolean result=false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置失效时间
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit){
        boolean result=false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key,expireTime,timeUnit);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的values
     * @param keys
     */
    public void remove(final String... keys){
        for (String key :
                keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size()>0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key){
        Object result=null;
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }

    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hashSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hashGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void listPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush(k,v);
    }

    /**
     * 列表获取
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> listRange(String key, long start, long end){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key,start,end);
    }

    /**
     * 无序集合添加
     * @param key
     * @param value
     */
    public void setAdd(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 无序集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param score
     */
    public void zSetAdd(String key,Object value,double score){
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(key,value,score);
    }

    /**
     * 有序集合获取
     * @param key
     * @param score
     * @param score1
     * @return
     */
    public Set<Object> zSetRangeByScore(String key,double score,double score1){
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, score, score1);
    }
}
