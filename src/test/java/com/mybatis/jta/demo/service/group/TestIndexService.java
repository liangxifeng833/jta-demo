package com.mybatis.jta.demo.service.group;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mybatis.jta.demo.DemoApplicationTests;
import com.mybatis.jta.demo.entity.car.MessagePackageNo;
import com.mybatis.jta.demo.entity.test.User;
import com.mybatis.jta.demo.util.SpringContextUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

/**
 * Description: jta-demo
 * Create by liangxifeng on 19-9-27
 */
public class TestIndexService extends DemoApplicationTests {
    @Autowired
    private IndexService indexService;

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Test
    public void testA() {
        MessagePackageNo message = new MessagePackageNo();
        message.setNo(3000);

        User user = new User();
        user.setName("wangwu");
        user.setAge(30);

        indexService.save(message,user);
    }

    @Test
    public void testB() throws SQLException {
        DruidXADataSource dataSource = (DruidXADataSource) SpringContextUtil.getBean("dataSourceXA");
        try {
            System.out.println("关闭数据源");
            dataSource.close();
            System.out.println("数据库连接url="+dataSource.getUrl());
        } catch (Exception e) {
            System.out.println("关闭连接失败");
            e.printStackTrace();
        }
        System.out.println("修改数据库连接");
        dataSource.setUrl("jdbc:mysql://localhost:3306/a?useUnicode=true&characterEncoding=utf8&useSSL=false");

        System.out.println("数据库连接url="+((DruidXADataSource) SpringContextUtil.getBean("dataSourceXA")).getUrl());

        AtomikosDataSourceBean dataSourceCar = (AtomikosDataSourceBean) SpringContextUtil.getBean("dataSourceCar");
        //dataSourceCar.setXaDataSource(dataSource);
        System.out.println("==="+dataSourceCar.getXaDataSource().getXAConnection());


        User user = new User();
        user.setName("lisi");
        user.setAge(20);

        indexService.save2(user);
    }


    public void modifyDataSourceConfig() {
        DruidXADataSource dataSource = (DruidXADataSource) SpringContextUtil.getBean("dataSourceXA");
        try {
            dataSource.close();
        } catch (Exception e) {
            System.out.println("关闭连接失败");
            e.printStackTrace();
        }
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false");
        System.out.println("++++++"+dataSource);
    }
}
