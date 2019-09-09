package com.styzf.finance.dto.finance;

import com.styzf.core.common.base.BaseDTO;
import com.styzf.finance.dto.category.CategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

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
public class FinanceListData extends CategoryDTO {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty("是否是子节点，默认false")
    private boolean isChild;
    
    @ApiModelProperty("查询月份的财务数据")
    private List<FinanceDTO> data;
}
