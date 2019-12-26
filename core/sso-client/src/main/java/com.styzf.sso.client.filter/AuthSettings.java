package com.styzf.sso.client.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author styzf
 * @date 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
@ConfigurationProperties("styzf.auth")
public class AuthSettings implements Serializable {
    
    private List<AuthUrl> authUrl;
    
}
