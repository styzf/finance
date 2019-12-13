package com.styzf.sso;

import com.styzf.core.common.util.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
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
@MapperScan("com.styzf.sso.mapper*")
@EnableCaching
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
public class SSOApplicationMain extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SSOApplicationMain.class, args);
        log.info(MyStringUtils.center(" styzf-sso start success! ", 80, "*"));
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SSOApplicationMain.class);
    }
}