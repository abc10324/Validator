package com.valid.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages="com.valid.model.dao")
@ComponentScan("com.valid")
public class SpringJavaConfig {

	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
//                .addScript("schema.sql")
                .build();
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	
	  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	  vendorAdapter.setGenerateDdl(true);
//	  vendorAdapter.setShowSql(true);
	  vendorAdapter.setDatabase(Database.H2);
	    
	  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	  factory.setJpaVendorAdapter(vendorAdapter);
	  factory.setPackagesToScan("com.valid.model");
	  factory.setDataSource(dataSource());
	    
	  return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
	  JpaTransactionManager txManager = new JpaTransactionManager();
	  txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	  return txManager;
	}
	
	
}
