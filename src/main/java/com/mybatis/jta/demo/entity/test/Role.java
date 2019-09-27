package com.mybatis.jta.demo.entity.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色表
 * Create by liangxifeng on 19-9-27
 */
@Setter
@Getter
@ToString
public class Role {

    private Integer id;
    /**
     * 名字
     */
    private String name;
}
