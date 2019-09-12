package com.styzf.finance.dto.finance;

import com.styzf.core.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 账单表
 * </p>
 *
 * @author styzf
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Finance对象", description="账单表")
public class FinanceDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记账名")
    private String name;
    
    @ApiModelProperty("用户id")
    private Long userId;
    
    @ApiModelProperty("父类id")
    private Long parentId;
    
    @ApiModelProperty("年")
    private Integer year;
    
    @ApiModelProperty("月")
    private Integer month;
    
    @ApiModelProperty("所属分类id")
    private Long categoryId;
    
    @ApiModelProperty("金额")
    private BigDecimal money;
    
    @ApiModelProperty("日期")
    private Integer day;
    
    @ApiModelProperty("备注")
    private String remark;
}
