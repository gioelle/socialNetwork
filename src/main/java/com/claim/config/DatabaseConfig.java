package com.claim.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages="com.claim.repository")
public class DatabaseConfig {
//tells hibernate where the classes are that maps to your database table and the user name and to use your the username and password which is speecified in the property file 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.claim.entity")
				.build();
	}
	//creating a transaction manager so you can query the database
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
}

//to reuse this code, all you have to do is replace both package locations (com.claim.repository) and (com.claim.entity)