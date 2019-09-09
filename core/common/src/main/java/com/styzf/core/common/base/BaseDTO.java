package com.styzf.core.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@Data
public abstract class BaseDTO implements Serializable{
    private static final long serialVersionUID = -4083754097244748607L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long creatorId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "修改者")
    private String updater;
    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "修改者id")
    private Long updateId;
    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean deleteFlag;
    
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
