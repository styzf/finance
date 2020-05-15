package com.styzf.finance.web.request.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author styzf
 * @date 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Finance对象", description="账单表")
public class FinanceUpdateRequest {
	
	@ApiModelProperty("id")
	@NotNull(message = "账单id不能为空")
	private Long id;
	
	@ApiModelProperty("记账名")
	private String name;
	
	@ApiModelProperty("父类id")
	private Long parentId;
	
	@ApiModelProperty("时间")
	private Date date;
	
	@ApiModelProperty("所属分类id")
	private Long categoryId;
	
	@NotNull
	@ApiModelProperty("金额")
	private Long money;
	
	@ApiModelProperty("备注")
	private String remark;
	
	@ApiModelProperty("年")
	private Integer year;
	
	@ApiModelProperty("月")
	private Integer month;
	
	@ApiModelProperty("日期")
	private Integer day;
}
