package com.styzf.finance;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.styzf"})
@EnableCaching
@EnableAsync
@SpringBootApplication
@EnableDubboConfiguration
public class WebApplicationMain {
 
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebApplicationMain.class);
		springApplication.run(args);
        log.info(StringUtils.rightPad("*", 50, "*"));
        log.info(StringUtils.center(" styzf-finance-web start success! ", 50, "*"));
        log.info(StringUtils.rightPad("*", 50, "*"));
	}
}