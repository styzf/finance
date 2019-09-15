package com.styzf.finance;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
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
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 
 * @author styzf
 * @date 2018年7月23日 
 *
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication()
@ComponentScan(basePackages = { "com.styzf"})
@MapperScan("com.styzf.finance.mapper*")
@EnableCaching
@EnableAsync
@EnableDubboConfiguration
public class ProviderApplicationMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProviderApplicationMain.class, args);
        log.info(StringUtils.rightPad("*", 50, "*"));
        log.info(StringUtils.center(" styzf-finance-provider start success! ", 80, "*"));
        log.info(StringUtils.rightPad("*", 50, "*"));
    }



}