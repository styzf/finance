package com.styzf.finance.web.request.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class FinanceRemarkRequest {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("年")
    @NotNull(message = "年份数据不能为空")
    private Integer year;
    
    @ApiModelProperty("月")
    @NotNull(message = "月份数据不能为空")
    private Integer month;
    
    @ApiModelProperty("所属分类id")
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @ApiModelProperty("日期")
    @NotNull(message = "年份数据不能为空")
    private Integer day;
    
    @ApiModelProperty("备注")
    @NotEmpty(message = "备注信息不能为空")
    private String remark;
}
