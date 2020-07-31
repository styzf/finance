package com.styzf.sso.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author styzf
 * @date 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MenuTree", description="权限树对象")
public class MenuTree extends MenuDTO {
	
	/**
	 * 子节点所拥有的分类
	 */
	@ApiModelProperty(value = "子节点所拥有的权限")
	private List<MenuTree> childList;
}
