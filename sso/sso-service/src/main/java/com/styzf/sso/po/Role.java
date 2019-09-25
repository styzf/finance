package com.styzf.sso.po;

import com.styzf.mybatis.util.IdWorkerGenId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author styzf
 * @date 2019-09-23
 */
@Data
@ToString
@Entity
@Table(name="xc_role")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="角色表", description="角色表")
public class Role {
    
    @Id
    @KeySql(genId = IdWorkerGenId.class)
    private String id;
    @Column(name="role_name")
    private String roleName;
    @Column(name="roleCode")
    private String role_code;
    private String description;
    private String status;
    @Column(name="createTime")
    private Date create_time;
    @Column(name="update_time")
    private Date updateTime;


}
