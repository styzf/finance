package com.styzf.finance.web.request.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author styzf
 * @date 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Finance对象", description="账单表")
public class FinanceAddRequest {
	
	@NotNull
	@ApiModelProperty("所属分类id")
	private Long categoryId;
	
	@NotNull
	@ApiModelProperty("金额")
	private BigDecimal money;
	
	@NotNull
	@ApiModelProperty("年")
	private Integer year;
	
	@NotNull
	@ApiModelProperty("月")
	private Integer month;
	
	@NotNull
	@ApiModelProperty("日")
	private Integer day;
}
