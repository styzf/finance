package com.styzf.finance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author styzf
 * @date 2020-06-15
 */
@Data
@ConfigurationProperties(prefix="styzf.security")
public class SecurityProperties {
    private String[] matchers;
}
