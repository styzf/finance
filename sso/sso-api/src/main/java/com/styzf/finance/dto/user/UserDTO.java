package com.styzf.finance.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author styzf
 * @date 2019-09-23
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户对象", description="用户表")
public class UserDTO {
    
    private String id;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "用户密码")
    private String password;
    
    
    private String salt;
    
    @ApiModelProperty(value = "用户名")
    private String name;
    
    @ApiModelProperty(value = "用户类型")
    private String utype;
    
    @ApiModelProperty(value = "生日")
    private String birthday;
    
    @ApiModelProperty(value = "头像照片")
    private String userpic;
    
    @ApiModelProperty(value = "性别")
    private String sex;
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "手机号码")
    private String phone;
    
    @ApiModelProperty(value = "状态")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
