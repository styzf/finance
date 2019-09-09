package com.styzf.finance.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CategoryTree对象", description="分类树对象")
public class CategoryTree extends CategoryDTO {
	
	/**
	 * 子节点所拥有的分类
	 */
	@ApiModelProperty(value = "子节点所拥有的分类")
	private List<CategoryTree> childList;
}
