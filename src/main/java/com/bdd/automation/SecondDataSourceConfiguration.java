package com.bdd.automation;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class SecondDataSourceConfiguration {
//	@Bean(name = "SecondDataSource")
//	@ConfigurationProperties(prefix = "spring.seconddatasource")
//	public DataSource db2DataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "secondarydatasource")
//	public JdbcTemplate db1JdbcTemplate(@Qualifier("SecondDataSource") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}

}
