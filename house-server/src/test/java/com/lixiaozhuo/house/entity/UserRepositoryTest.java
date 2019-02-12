package com.lixiaozhuo.house.entity;

import com.lixiaozhuo.house.ApplicationTests;
import com.lixiaozhuo.house.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * User测试
 */
public class UserRepositoryTest extends ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        User user = userRepository.findById(1L).get();
        Assert.assertEquals("wali",user.getName());
    }
}
