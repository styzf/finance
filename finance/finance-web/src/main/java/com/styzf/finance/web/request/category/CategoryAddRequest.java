package com.styzf.finance.web.request.category;

import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.web.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 类别表
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Category添加请求对象", description="类别表")
public class CategoryAddRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "类别名称不能为空")
	@ApiModelProperty(value = "类别名")
	private String name;
	
	@ApiModelProperty(value = "用户id")
	private Long userId;
	
	@NotNull(message = "父类id不能为空")
	@ApiModelProperty(value = "父类id")
	private Long parentId;
	
}

