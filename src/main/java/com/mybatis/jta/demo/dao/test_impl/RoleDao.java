package com.mybatis.jta.demo.dao.test_impl;

import com.mybatis.jta.demo.dao.DaoApi;
import com.mybatis.jta.demo.entity.test.Role;
import org.springframework.stereotype.Component;

/**
 * Role表 接口
 */
@Component
public interface RoleDao extends DaoApi<Role> {
}