package com.mybatis.jta.demo.util;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import lombok.extern.slf4j.Slf4j;

/**
 * 动态修改数据库连接池的url属性类
 * Create by liangxifeng on 19-10-13
 */
@Slf4j
public class SwitchDB {
    public static void to(int ljyunId) {
        //从spring容器中获取数据源
        DruidXADataSource dataSource = (DruidXADataSource) SpringContextUtil.getBean("dataSourceYunXA");
        try {
            // 这里需要先关闭数据源，才可以使新修改的数据源设置生效
            dataSource.close();
        } catch (Exception e) {
            log.info("关闭数据源失败,连接url={}",dataSource.getUrl());
            e.printStackTrace();
        }
        String preUrl = "jdbc:mysql://localhost:3306/";
        String postUrl = "useUnicode=true&characterEncoding=utf8&useSSL=false";
        log.info("切换数据库ljyunId="+ljyunId);
        //修改数据源的连接url属性
        dataSource.setUrl(preUrl+"ljyun_"+ljyunId+"_merchant?" +postUrl);
    }
}
