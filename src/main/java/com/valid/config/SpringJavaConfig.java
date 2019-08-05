package com.valid.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@EnableJpaRepositories(basePackages="com.valid.model.dao")
@EnableRedisRepositories(basePackages="com.valid.model.dao")
@ComponentScan("com.valid")
public class SpringJavaConfig {

//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .ignoreFailedDrops(true)
////                .addScript("schema.sql")
//                .build();
//    }
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//	
//	  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	  vendorAdapter.setGenerateDdl(true);
////	  vendorAdapter.setShowSql(true);
//	  vendorAdapter.setDatabase(Database.H2);
//	    
//	  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//	  factory.setJpaVendorAdapter(vendorAdapter);
//	  factory.setPackagesToScan("com.valid.model");
//	  factory.setDataSource(dataSource());
//	    
//	  return factory;
//	}
//	
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//	  JpaTransactionManager txManager = new JpaTransactionManager();
//	  txManager.setEntityManagerFactory(entityManagerFactory().getObject());
//	  return txManager;
//	}
	
	@Bean
	public Validator validator(){
		return new LocalValidatorFactoryBean();
	}

	@Bean 
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		MethodValidationPostProcessor methodPostProcessor = new MethodValidationPostProcessor();
		methodPostProcessor.setValidator(validator());
		return methodPostProcessor;
	}
	
	@Bean
	public RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(6379);
		return factory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory());
		return template;
	}
	
}
