package com.mybatis.jta.demo.dao.test_impl;

import com.mybatis.jta.demo.dao.DaoApi;
import com.mybatis.jta.demo.entity.test.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends DaoApi<User> {
}