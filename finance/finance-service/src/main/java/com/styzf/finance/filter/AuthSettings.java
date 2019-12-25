package com.styzf.finance.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author styzf
 */
@Data
@Component("authSettings")
@ConfigurationProperties(prefix="styzf.auth")
public class AuthSettings {
    
    private List<String> authUrl;
    
}
