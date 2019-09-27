package com.mybatis.jta.demo.mapper.test;

import com.mybatis.jta.demo.entity.test.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
@Repository
public interface RoleMapper {
    @Insert("INSERT INTO `tb_role` (`id`, `name`) VALUES (NULL, #{role.name})")
    void insert(@Param("role") Role role);
}
