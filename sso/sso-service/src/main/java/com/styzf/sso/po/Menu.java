package com.styzf.sso.po;

import com.styzf.mybatis.base.BasePO;
import com.styzf.mybatis.util.IdWorkerGenId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author styzf
 * @date 2019-09-23
 */
@Data
@ToString
@Table(name="st_menu")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="权限表", description="权限表")
public class Menu extends BasePO {
    
    private String code;
    @Column(name="p_code")
    private String pCode;
    @Column(name="p_id")
    private Long pId;
    @Column(name="menu_name")
    private String menuName;
    private String url;
    @Column(name="is_menu")
    private String isMenu;
    private Integer level;
    private Integer sort;
    private String status;
    private String icon;

}
