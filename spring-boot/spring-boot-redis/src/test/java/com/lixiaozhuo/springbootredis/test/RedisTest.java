package com.lixiaozhuo.springbootredis.test;


import com.lixiaozhuo.springbootredis.SpringBootRedisApplication;
import com.lixiaozhuo.springbootredis.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;


/**
 * Spring Data Redis测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRedisApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 添加一个字符串
     */
    @Test
    public void testSet() {
        this.stringRedisTemplate.opsForValue().set("key", "自定义字符串");
    }

    /**
     * 获取一个字符串
     */
    @Test
    public void testGet() {
        String value = this.stringRedisTemplate.opsForValue().get("key");
        System.out.println(value);
    }

    /**
     * 添加Users对象
     */
    @Test
    public void testSetUsers() {
        Users users = new Users();
        users.setAge(20);
        users.setName("张三丰");
        users.setId(1);
        //重新设置序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate.opsForValue().set("users", users);
    }


    /**
     * 取Users对象
     */
    @Test

    public void testGetUsers() {
        //重新设置序列化器
        this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Users users = (Users) this.redisTemplate.opsForValue().get("users");
        System.out.println(users);
    }

    /**
     * 基于JSON格式存Users对象
     */
    @Test

    public void testSetUsersUseJSON() {
        Users users = new Users();
        users.setAge(20);
        users.setName("李四丰");
        users.setId(1);
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
        this.redisTemplate.opsForValue().set("users_json", users);
    }

    /**
     * 基于JSON格式取Users对象
     */
    @Test
    public void testGetUseJSON() {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
        Users users = (Users) this.redisTemplate.opsForValue().get("users_json");
        System.out.println(users);
    }
}
