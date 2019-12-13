package com.styzf.govern.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 * @version 1.0
 **/
@EnableEurekaServer //标识此工程是一个EurekaServer
@SpringBootApplication
public class GovernCenterApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(GovernCenterApplication.class,args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GovernCenterApplication.class);
    }
}
