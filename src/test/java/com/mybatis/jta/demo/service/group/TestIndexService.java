package com.mybatis.jta.demo.service.group;

import com.mybatis.jta.demo.DemoApplicationTests;
import com.mybatis.jta.demo.entity.car.MessagePackageNo;
import com.mybatis.jta.demo.entity.test.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
public class TestIndexService extends DemoApplicationTests {
    @Autowired
    private IndexService indexService;

    @Test
    public void testA() {
        MessagePackageNo message = new MessagePackageNo();
        message.setNo(2000);

        User user = new User();
        user.setName("wangwu");
        user.setAge(10);

        indexService.save(message,user);
    }

    @Test
    public void testB() {
        User user = new User();
        user.setName("lisi");
        user.setAge(20);

        indexService.save2(user);
    }
}
