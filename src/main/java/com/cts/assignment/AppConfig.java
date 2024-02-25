package com.cts.assignment;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.cts.assignment.entity.Student;


@Configuration
public class AppConfig {
	
	@Bean
	public  DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/assignment");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }

}
