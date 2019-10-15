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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.sql.DataSource;

/**
 * Description: 分布式事务测试service
 * 向固定数据源db_test.user和动态数据源ljyun_512_merchant.app_tag表新增测试
 * 注意：512是云编号可以作为接口参数传递进来，已达到切换数据源的目的
 * Create by liangxifeng on 19-9-27
 */
@Service
public class AppTagServiceImpl {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AppTagDao appTagDao;

    /**
     * 分布式事务新增操作
     * @param user db_test.tb_user实体
     * @param appTag 私有库app_tag实体
     */
    @Transactional
    public void insert(User user, AppTag appTag) {
        user.setCreateTime(null);
        //新增db_test.tb_user表数据
        userDao.insert(user);
        //切换为ljyun_1_merchant数据库
        SwitchDB.to(1);

        //ljYun私有库数据库连接url信息
        System.out.println("yun数据库连接url="+((DruidXADataSource) SpringContextUtil.getBean("dataSourceYunXA")).getUrl());
        //db_car数据库连接url信息
        DruidXADataSource dataSourceCar = (DruidXADataSource) SpringContextUtil.getBean("dataSourceCarXA");
        System.out.println("db_car连接url="+dataSourceCar.getUrl());
        //db_test数据库连接url信息
        DruidXADataSource dataSourceTest = (DruidXADataSource) SpringContextUtil.getBean("dataSourceTestXA");
        System.out.println("db_test连接url="+dataSourceTest.getUrl());
        //int a = 10/0; //故意异常回滚

        //新增私有库.app_tag表数据
        appTagDao.insert(appTag);
        //手动回滚事务
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
