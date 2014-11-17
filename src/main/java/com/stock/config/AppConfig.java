package com.stock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "com.stock.config", "com.stock.model", "com.stock.dao"})
@Import({ MongoConfig.class })
public class AppConfig {

}