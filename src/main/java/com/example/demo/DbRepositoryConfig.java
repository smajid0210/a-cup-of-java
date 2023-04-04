package com.example.demo;

import com.example.api.MysqlDbRepository;
import com.example.api.PostGresDbRepository;
import com.example.entity.BlogDbRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbRepositoryConfig {


    @Bean
    @Qualifier("postgres")
    public BlogDbRepository postgresUserRepository() {
        return new PostGresDbRepository();
    }

    @Bean
    @Qualifier("mysql")
    public BlogDbRepository mysqlUserRepository() {
        return new MysqlDbRepository();
    }
}
