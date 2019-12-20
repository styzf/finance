package com.styzf.core.web.config.swagger2;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties({ApiInfoProperties.class})
public class Swagger2Config {
    @Bean
    public Docket springfoxDocket(ApiInfoProperties apiInfo) {
	
	    ParameterBuilder authPar = new ParameterBuilder();
	    ParameterBuilder cookiePar = new ParameterBuilder();
	    List<Parameter> pars = new ArrayList<Parameter>();
	    authPar.name("Authorization").description("jwt令牌")
			    .modelRef(new ModelRef("string")).parameterType("header")
			    .required(false).build(); //header中的ticket参数非必填，传空也可以
	    cookiePar.name("Cookie").description("Cookie")
			    .modelRef(new ModelRef("string")).parameterType("header")
			    .required(false).build(); //header中的ticket参数非必填，传空也可以
	    pars.add(authPar.build());    //根据每个方法名也知道当前方法在设置什么参数
	    pars.add(cookiePar.build());    //根据每个方法名也知道当前方法在设置什么参数
	    
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(new Class[] { ApiIgnore.class })
                .apiInfo(apiInfo(apiInfo))
                .pathMapping("/").select().paths(PathSelectors.regex("^.*(?<!error)$")).build()
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build()
		        .globalOperationParameters(pars);
        return docket;
    }
    
    @Bean
    ApiInfo apiInfo(ApiInfoProperties apiInfoProperties) {
    	return new ApiInfoBuilder()
			    .title(apiInfoProperties.getTitle())
			    .description(apiInfoProperties.getDescription())
			    .version(apiInfoProperties.getVersion())
			    .termsOfServiceUrl(apiInfoProperties.getTermsOfServiceUrl())
//			    .contact(apiInfoProperties.getContact())
			    .build();
    }
    
}
