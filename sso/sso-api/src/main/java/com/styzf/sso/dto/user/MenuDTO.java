package com.styzf.sso.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author styzf
 * @date 2019-11-11
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="权限表", description="权限表")
public class MenuDTO {
    
    private String id;
    private String code;
    private String pCode;
    private String pId;
    private String menuName;
    private String url;
    private String isMenu;
    private Integer level;
    private Integer sort;
    private String status;
    private String icon;
    private Date createTime;
    private Date updateTime;

}
