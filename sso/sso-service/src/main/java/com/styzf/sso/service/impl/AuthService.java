package com.styzf.sso.service.impl;

import com.alibaba.fastjson.JSON;
import com.styzf.core.common.constant.ServiceConstant;
import com.styzf.core.common.exception.BaseException;
import com.styzf.core.redis.RedisUtil;
import com.styzf.sso.constant.UserRedisKey;
import com.styzf.sso.dto.AuthToken;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 **/
@Log
@Service
public class AuthService {
	
    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    
	@Autowired
	LoadBalancerClient loadBalancerClient;
 
	@Autowired
    private RedisUtil redisUtil;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
    //用户认证申请令牌，将令牌存储到redis
    public AuthToken login(String username, String password, String clientId, String clientSecret) {

        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if(authToken == null){
            // 改为user自带的exception
            throw new BaseException("认证失败");
        }
        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //存储到redis中的内容
        String jsonString = JSON.toJSONString(authToken);
        //将令牌存储到redis
        boolean result = this.saveToken(access_token, jsonString, tokenValiditySeconds);
        if (!result) {
            throw new BaseException("");
        }
        return authToken;

    }
    //存储到令牌到redis

    /**
     *
     * @param access_token 用户身份令牌
     * @param content  内容就是AuthToken对象的内容
     * @param ttl 过期时间
     * @return
     */
    private boolean saveToken(String access_token,String content,long ttl){
        redisUtil.set(UserRedisKey.User.TOKEN + access_token, content, ttl, TimeUnit.SECONDS);

        Long expire = redisUtil.getExpire(UserRedisKey.User.TOKEN + access_token);
        return expire>0;
    }
    //申请令牌
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        //从eureka中获取认证服务的地址（因为spring security在认证服务中）
        //从eureka中获取认证服务的一个实例的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceConstant.SSO_AUTH);
        //此地址就是http://ip:port
        URI uri = serviceInstance.getUri();
        //令牌申请的地址 http://localhost:40400/auth/oauth/token
        String authUrl = uri+ "/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization",httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });
    
        
        log.info(authUrl + JSON.toJSONString(httpEntity));
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        log.info(JSON.toJSONString(exchange));
        
        //申请令牌信息
        Map exchangeBody = exchange.getBody();
        Map data = new HashMap<String, Object>();
        if (Boolean.TRUE.equals(exchangeBody.get("success"))) {
            data = (Map) exchangeBody.get("data");
        }
        if(Objects.isNull(data.get("access_token")) ||
           Objects.isNull(data.get("refresh_token")) ||
           Objects.isNull(data.get("jti"))){
            return null;
        }
        AuthToken authToken = new AuthToken();
        authToken.setAccess_token((String) data.get("jti"));//用户身份令牌
        authToken.setRefresh_token((String) data.get("refresh_token"));//刷新令牌
        authToken.setJwt_token((String) data.get("access_token"));//jwt令牌
        return authToken;
    }
    
    //获取httpbasic的串
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }
}
