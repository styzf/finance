package com.styzf.sso.po;

import com.styzf.mybatis.base.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * @author styzf
 * @date 2019-09-23
 */
@Data
@ToString
@Table(name="st_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户对象", description="用户表")
public class MyUser extends BasePO {
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
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

}
