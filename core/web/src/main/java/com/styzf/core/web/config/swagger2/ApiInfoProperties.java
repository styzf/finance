package com.styzf.core.web.config.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="swagger2.api-info")
public class ApiInfoProperties {
    private String version;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String pathsRegex;
//    private Contact contact;
}