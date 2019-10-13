package com.mybatis.jta.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * ljyun私有库数据源配置
 * Create by liangxifeng on 19-9-27
 */
@Configuration
// 使用注解的方式使用
//@MapperScan(basePackages = {"com.mybatis.jta.demo.mapper.car*"}, sqlSessionTemplateRef = "sqlSessionTemplateCar")
@MapperScan(basePackages = {"com.mybatis.jta.demo.dao.yun_impl*"}, sqlSessionTemplateRef = "sqlSessionTemplateYun") //
// 扫描dao或mapper接口
public class DataSourceYunConfig {

    @Bean("dataSourceYunXA")
    public DruidXADataSource dataSourceXA(DataSourceYunProperties dataSourceYunProperties) {
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dataSourceYunProperties,dataSource);
        return dataSource;
    }

    @Bean(name = "dataSourceYun")
    public DataSource dataSourceYun(@Qualifier("dataSourceYunXA") DruidXADataSource dataSource){
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("dataSourceYun");
        return xaDataSource;
    }

    @Bean(name = "sqlSessionFactoryYun")
    public SqlSessionFactory sqlSessionFactoryYun(@Qualifier("dataSourceYun") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.mybatis.jta.demo.entity.yun");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources
                ("classpath:/mapper/yun/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateYun")
    public SqlSessionTemplate sqlSessionTemplateYun(
            @Qualifier("sqlSessionFactoryYun") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
