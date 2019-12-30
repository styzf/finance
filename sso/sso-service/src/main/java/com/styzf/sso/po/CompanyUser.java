package com.styzf.sso.po;

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
import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@Table(name="st_menu")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="公司用户中间关联表", description="公司用户中间关联表")
public class CompanyUser implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @Id
    @KeySql(genId = IdWorkerGenId.class)
    @Column(length = 32)
    private String id;
    @Column(name="company_id")
    private String companyId;
    @Column(name="user_id")
    private String userId;


}
