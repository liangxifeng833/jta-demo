package com.mybatis.jta.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
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
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * 数据源配置
 * Create by liangxifeng on 19-9-27
 */
@Configuration
@MapperScan(basePackages = {"com.mybatis.jta.demo.mapper.car*"}, sqlSessionTemplateRef = "sqlSessionTemplateCar") //
// 扫描dao或mapper接口
public class DataSourceCarConfig {

    @Primary
    @Bean(name = "dataSourceCar")
    public DataSource dataSourceCar(DataSourceCarProperties dataSourceCarProperties){
        DruidXADataSource dataSource = new DruidXADataSource();
        BeanUtils.copyProperties(dataSourceCarProperties,dataSource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("dataSourceCar");
        return xaDataSource;
    }


    /*
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     *
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }*/

    @Bean(name = "sqlSessionFactoryCar")
    public SqlSessionFactory sqlSessionFactoryCar(@Qualifier("dataSourceCar") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.mybatis.jta.demo.entity.car");
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/car/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateCar")
    public SqlSessionTemplate sqlSessionTemplateCar(
            @Qualifier("sqlSessionFactoryCar") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
