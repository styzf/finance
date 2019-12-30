package com.styzf.sso.dto.user;

import com.styzf.core.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author styzf
 * @date 2019-12-27
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="角色表", description="角色表")
public class RoleDTO extends BaseDTO {
    
    private String id;
    
    /**
     * 角色名
     */
    private String roleName;
    
    /**
     * 角色编码
     */
    private String roleCode;
    
    /**
     * 说明
     */
    private String description;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;

}
