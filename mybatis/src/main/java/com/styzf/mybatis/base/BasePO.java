package com.styzf.mybatis.base;

import com.styzf.core.common.util.IdWorker;
import com.styzf.mybatis.util.IdWorkerGenId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@Data
public abstract class BasePO implements Serializable{
    private static final long serialVersionUID = -4083754095494748607L;
    
    /**
     * id
     */
    @Id
    @KeySql(genId = IdWorkerGenId.class)
    private Long id;
    /**
     * 创建人id
     */
    @Column(name = "creator_id")
    private Long creatorId;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    
    /**
     * 修改人
     */
    private String updater;
    /**
     * 修改人id
     */
    @Column(name = "update_id")
    private Long updateId;
    
    /**
     * 删除标识
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;
    
    /**
     * 语言类型
     */
    @Column(name = "lang_type")
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
