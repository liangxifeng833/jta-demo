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
 * db_car数据源配置
 * Create by liangxifeng on 19-9-27
 */
@Configuration
// 使用注解的方式使用
//@MapperScan(basePackages = {"com.mybatis.jta.demo.mapper.car*"}, sqlSessionTemplateRef = "sqlSessionTemplateCar")
@MapperScan(basePackages = {"com.mybatis.jta.demo.dao.car_impl*"}, sqlSessionTemplateRef = "sqlSessionTemplateCar") //
// 扫描dao或mapper接口
public class DataSourceCarConfig {
    //只能在三个数据源配置信息中出现一次，表示告诉spring初始化时候优先加载哪个数据源
    @Primary
    @Bean("dataSourceCarXA")
    public DruidXADataSource dataSourceXA(DataSourceCarProperties dataSourceCarProperties) {
        DruidXADataSource dataSource = new DruidXADataSource();
        //使用BeanUtils将数据库连接属性映射到数据源DruidXADataSource的属性中
        //当然也可以通过dataSource.setUrl()的方式配置
        BeanUtils.copyProperties(dataSourceCarProperties,dataSource);
        return dataSource;
    }

    @Bean(name = "dataSourceCar")
    public DataSource dataSourceCar(@Qualifier("dataSourceCarXA") DruidXADataSource dataSource){
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("dataSourceCar");
        return xaDataSource;
    }

    @Bean(name = "sqlSessionFactoryCar")
    public SqlSessionFactory sqlSessionFactoryCar(@Qualifier("dataSourceCar") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.mybatis.jta.demo.entity.car");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/car/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateCar")
    public SqlSessionTemplate sqlSessionTemplateCar(
            @Qualifier("sqlSessionFactoryCar") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
