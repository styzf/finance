package com.styzf.finance.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author styzf
 * @date 2019-12-25
 */
@Data
@Component("authSettings")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ConfigurationProperties(prefix="styzf.auth")
public class AuthSettings implements Serializable {
    
    private List<AuthUrl> authUrl;
    
}
