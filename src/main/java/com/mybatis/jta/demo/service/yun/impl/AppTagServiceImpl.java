package com.mybatis.jta.demo.service.yun.impl;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mybatis.jta.demo.dao.test_impl.UserDao;
import com.mybatis.jta.demo.dao.yun_impl.AppTagDao;
import com.mybatis.jta.demo.entity.test.User;
import com.mybatis.jta.demo.entity.yun.AppTag;
import com.mybatis.jta.demo.util.SpringContextUtil;
import com.mybatis.jta.demo.util.SwitchDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
@Service
public class AppTagServiceImpl {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AppTagDao appTagDao;

    @Transactional
    public void insert(User user, AppTag appTag) {
        user.setCreateTime(null);
        userDao.insert(user);

        SwitchDB.to(1);

        System.out.println("yun数据库连接url="+((DruidXADataSource) SpringContextUtil.getBean("dataSourceYunXA")).getUrl());
        DruidXADataSource dataSource = (DruidXADataSource) SpringContextUtil.getBean("dataSourceCarXA");
        System.out.println("db_car连接url="+dataSource.getUrl());
        //int a = 10/0; //故意异常回滚

        appTagDao.insert(appTag);
    }
}
