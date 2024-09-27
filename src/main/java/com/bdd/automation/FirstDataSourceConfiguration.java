package com.bdd.automation;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class FirstDataSourceConfiguration {
//	@Bean(name = "FirstDataSource")
//	@ConfigurationProperties(prefix = "spring.firstdatasource")
//	public DataSource db1DataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "primarydatasource")
//	public JdbcTemplate db1JdbcTemplate(@Qualifier("FirstDataSource") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}

}
