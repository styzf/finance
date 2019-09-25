package com.styzf.core.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Date;

@Data
public abstract class BaseRequest extends PageParams {
    private static final long serialVersionUID = -4083754097244748607L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private String creatorId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人")
    private String updateId;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private Date updateTime;
    
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean delete;
    
    /**
     * 语言类型
     */
    @ApiModelProperty(value = "语言类型")
    private String langType;
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Class<?> clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();
        sb.append(clazz.getName() + "{");
        try {
          for (Field field : fields) {
            field.setAccessible(true);
            String value = field.get(this) == null ? "" : field.get(this).toString();
            sb.append("\n  " + field.getName() + ":" + value);
          }
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        sb.append("\n}");
        
        return sb.toString();
    }
}
