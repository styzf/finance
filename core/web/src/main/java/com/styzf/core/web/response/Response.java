package com.styzf.core.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 返回实体类的父类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    protected Boolean success;
    
    protected String msg;
    
    public Response(Boolean success) {
      this.success = success;
    }
    
}
