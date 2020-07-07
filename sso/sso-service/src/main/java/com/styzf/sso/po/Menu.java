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
    
    /**
     * 菜单编码
     */
    private String code;
    
    /**
     * 父菜单编码
     */
    @Column(name="p_code")
    private String pCode;
    
    /**
     * 父菜单id
     */
    @Column(name="p_id")
    private Long pId;
    
    /**
     * 菜单名
     */
    @Column(name="menu_name")
    private String menuName;
    
    /**
     * 请求地址
     */
    private String url;
    
    /**
     * 是否是菜单
     */
    @Column(name="is_menu")
    private String isMenu;
    
    /**
     * 菜单层级
     */
    private Integer level;
    
    /**
     * 菜单排序
     */
    private Integer sort;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 图标样式，这个应该没啥用
     */
    private String icon;

}
