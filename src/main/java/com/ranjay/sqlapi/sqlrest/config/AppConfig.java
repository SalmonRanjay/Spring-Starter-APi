package com.ranjay.sqlapi.sqlrest.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("application.properties")
public class AppConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource datasource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName(env.getProperty("dev.driverClassName"));
		basicDataSource.setUsername(env.getProperty("dev.username"));
		basicDataSource.setPassword(env.getProperty("dev.password"));
		basicDataSource.setUrl(env.getProperty("dev.dbUrl"));
		return basicDataSource;
	}
	
	@Bean
	public JpaVendorAdapter vendorAdapter() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(DataSource dataSource, 
			JpaVendorAdapter vendorAdapter) {
		LocalContainerEntityManagerFactoryBean emf = 
				new LocalContainerEntityManagerFactoryBean();
		
		emf.setDataSource(dataSource);
		emf.setJpaProperties(getHibernateProperties());
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setPackagesToScan("com.ranjay.sqlapi.sqlrest.model");
		
		return emf;
		
	}
	
	 private Properties getHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
	        properties.put("hibernate.implicit_naming_strategy",env.getProperty("hibernate.implicit_naming_strategy"));
	        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	        return properties;
	    }
	
	
}
