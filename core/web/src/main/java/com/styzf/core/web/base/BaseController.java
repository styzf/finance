package com.styzf.core.web.base;

import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.common.base.BaseService;
import com.styzf.core.common.base.Pager;
import com.styzf.core.common.exception.BaseException;
import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.common.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * 基础控制器
 * 1、提供基础的增删改查功能；
 * 待添加功能
 * 1、基础request提供，与后续的dto区别开
 * 2、基础vo提供
 * 3、注解关闭方法
 * @author yangzf
 * @date 2020-07-03
 */
@Slf4j
public abstract class BaseController<D extends BaseDTO> implements BaseControllerDoc<D> {

    @Autowired
    private BaseService<D> service;
    
    protected Class<D> clazzD;
    
    public BaseController() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = superclass.getActualTypeArguments();
        this.clazzD = (Class<D>) types[0];
    }
    
    @Override
    @GetMapping("/{id}")
    public Response baseGetById(@PathVariable Serializable id) {
        D d = service.baseGetById(id);
        d = afterGetById(id, d);
        return SuccessResponseData.newInstance(d);
    }

    protected D afterGetById(Serializable id, D d) {
        return d;
    }
    
    /**
     * 列表查询
     * @return
     */
    @Override
    @GetMapping("list")
    public Response baseList(D d) {
        d = beforeList(d);
        List<D> list = service.baseList(d);
        list = afterList(d, list);
        return SuccessResponseData.newInstance(list);
    }
    
    protected D beforeList(D d) {
        return d;
    }
    
    protected List<D> afterList(D d, List<D> list) {
        return list;
    }
    
    /**
     * 分页管理
     * @return
     */
    @Override
    @GetMapping("page")
    public Response basePage(D d) {
        d = beforePage(d);
        Pager<D> page = service.basePage(d);
        page = afterPage(d, page);
        return SuccessResponseData.newInstance(page);
    }
    
    protected D beforePage(D d) {
        return d;
    }
    
    protected Pager<D> afterPage(D d, Pager<D> page) {
        return page;
    }
    
    @Override
    @PostMapping
    public Response baseAdd(@RequestBody @Valid D d) {
        d= beforeAdd(d);
        if (Objects.nonNull(d.getId())) {
            throw new BaseException("添加方法提交id参数");
        }
        service.baseInsertOrUpdate(d);
        d = afterAdd(d);
        return SuccessResponseData.newInstance(d);
    }
    
    protected D beforeAdd(D d) {
        return d;
    }
    
    protected D afterAdd(D d) {
        return d;
    }
    
    @Override
    @PutMapping
    public Response baseUpdate(@RequestBody @Valid D d) {
        d = beforeUpdate(d);
        if (Objects.isNull(d.getId())) {
            throw new BaseException("更新id必须提交");
        }
        service.baseInsertOrUpdate(d);
        d = afterUpdate(d);
        return SuccessResponseData.newInstance(d);
    }
    
    protected D beforeUpdate(D d) {
        return d;
    }
    
    protected D afterUpdate(D d) {
        return d;
    }
    
    @Override
    @DeleteMapping("/{id}")
    public Response baseDeleteById(@PathVariable Serializable id) {
        D d = ObjectUtil.newTclass(clazzD);
        d = beforeDeleteById(id, d);
    
        service.baseDeleteById(id, d);
        d = afterDeleteById(id, d);
        return SuccessResponseData.newInstance(d);
    }
    
    protected D beforeDeleteById(Serializable id, D d) {
        return d;
    }
    protected D afterDeleteById(Serializable id, D d) {
        return d;
    }
    
    @Override
    @DeleteMapping("remove")
    public Response remove(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return SuccessResponseData.newInstance();
        }
        ids.stream().forEach(id -> {
            baseDeleteById(id);
        });
        return SuccessResponseData.newInstance();
    }
}
