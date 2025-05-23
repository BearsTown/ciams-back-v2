package com.uitgis.ciams.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(value = "com.uitgis.gis.mapper", sqlSessionFactoryRef = "gisSqlSessionFactory")
public class GisDatabaseConfig {

	@Bean(name = "gisDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.gis.hikari")
	public DataSource gisDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "gisSqlSessionFactory")
    public SqlSessionFactory gisSqlSessionFactory(@Qualifier("gisDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/gis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "gisSqlSessionTemplate")
    public SqlSessionTemplate gisSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager gisTransactionManager(@Qualifier("gisDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}
