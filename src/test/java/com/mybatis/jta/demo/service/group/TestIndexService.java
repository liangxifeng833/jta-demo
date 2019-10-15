package com.mybatis.jta.demo.service.group;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mybatis.jta.demo.DemoApplicationTests;
import com.mybatis.jta.demo.entity.car.MessagePackageNo;
import com.mybatis.jta.demo.entity.test.User;
import com.mybatis.jta.demo.entity.yun.AppTag;
import com.mybatis.jta.demo.service.yun.impl.AppTagServiceImpl;
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

    @Autowired
    private AppTagServiceImpl appTagService;

    /**
     * 单元测试新增db_car.MessagePackageNo和db_test.User表数据
     * 测试固定数据源，不需要动态修改
     * 在service中启用事务注解
     */
    @Test
    public void testMessageUser() {
        MessagePackageNo message = new MessagePackageNo();
        message.setNo(3000);

        User user = new User();
        user.setName("wangwu");
        user.setAge(30);
        //在save()方法中启用事务注解
        indexService.save(message,user);
    }

    /**
     * 单元测试新增db_test.User和ljyun_512_merchant.app_tag表数据
     * 测试动态修改数据源的连接url属性，也就意味着切换数据库了
     * 在service中启用事务注解
     */
    @Test
    public void testAddUserTags(){
        User user = new User();
        user.setCreateTime(null);
        user.setName("张三");
        user.setAge(10);

        AppTag appTag = new AppTag();
        appTag.setTagName("app_name_1");
        //appTag.setTagType(1);
        appTagService.insert(user,appTag);
    }

    /**
     * 手动修改spring容器中的指定bean的属性
     */
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
