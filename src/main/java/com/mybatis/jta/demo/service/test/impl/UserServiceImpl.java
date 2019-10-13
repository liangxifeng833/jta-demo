package com.mybatis.jta.demo.service.test.impl;

import com.mybatis.jta.demo.dao.test_impl.RoleDao;
import com.mybatis.jta.demo.dao.test_impl.UserDao;
import com.mybatis.jta.demo.entity.test.Role;
import com.mybatis.jta.demo.entity.test.User;
import com.mybatis.jta.demo.mapper.test.RoleMapper;
import com.mybatis.jta.demo.mapper.test.UserMapper;
import com.mybatis.jta.demo.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
@Service
public class UserServiceImpl implements UserService {
    //@Autowired
    //private UserMapper userMapper;

    //@Autowired
    //private RoleMapper roleMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void insert(User user) {
        user.setCreateTime(null);
        userDao.insert(user);
        Role  role = new Role();
        role.setName("aa");
        roleDao.insert(role);
    }
}
