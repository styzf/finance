package com.styzf.finance.dto.category;

import com.styzf.core.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Category对象", description="类别表")
public class CategoryDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;
    @NotNull
    @ApiModelProperty(value = "类别名")
    private String name;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "父类id")
    private Long parentId;
    
    @ApiModelProperty(value = "关键key")
    private String categoryKey;
    
    public static void main(String[] args) {
    }
}
