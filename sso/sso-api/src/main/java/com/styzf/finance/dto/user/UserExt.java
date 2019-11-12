package com.styzf.finance.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO 暂时先放这里，有空再处理
 * @author styzf
 * @date 2019-11-11
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户扩展对象", description="用户扩展对象")
public class UserExt extends UserDTO {

    //权限信息
    private List<MenuDTO> permissions;

    //企业信息
    private String companyId;
}
