package com.styzf.finance.po;

import javax.persistence.Table;

import com.styzf.mybatis.base.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类别表
 * </p>
 *
 * @author styzf
 * @since 2019-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "b_category")
@ApiModel(value="Category对象", description="类别表")
public class Category extends BasePO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别名")
    private String name;
    
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    @ApiModelProperty(value = "父类id")
    private Long parentId;
    
    @ApiModelProperty(value = "分类关键key")
    private String categoryKey;
    
    @ApiModelProperty(value = "父类关键key")
    private String parentKey;
    
    @ApiModelProperty(value = "值")
    private String value;
}
