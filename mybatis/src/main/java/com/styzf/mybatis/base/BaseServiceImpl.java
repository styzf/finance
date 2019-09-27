package com.styzf.mybatis.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.common.base.BaseService;
import com.styzf.core.common.base.PageParams;
import com.styzf.core.common.base.Pager;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.core.common.util.MyStringUtils;
import com.styzf.core.common.util.date.DateUtil;
import com.styzf.mybatis.constant.DeleteEnum;
import com.styzf.mybatis.util.PagerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * 基础实现类
 * @author styzf
 * @date 2019-09-24
 * @param <P>
 * @param <D>
 */
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public abstract class BaseServiceImpl<P extends BasePO,D extends BaseDTO>
		implements BaseService<D> {
	
	@Autowired
	protected Mapper<P> mapper;
	
	protected Class<P> clazzP;
	protected Class<D> clazzD;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = superclass.getActualTypeArguments();
		this.clazzP = (Class<P>) types[0];
		this.clazzD = (Class<D>) types[1];
	}
	
	@Override
	public List<D> baseList (D d){
		P p = newTclass(this.clazzP);
		BeanUtils.copyProperties(d, p);
		p.setDeleteFlag(DeleteEnum.EXIST.getStatus());
		return ConvertUtil.convertList(mapper.select(p), clazzD);
	}
	
	@Override
	public Pager<D> basePage(D d) {
		PageParams pageParams = ConvertUtil.convert(d, PageParams.class);
		
		pageBefore(d);
		
		if (null != d && null == d.getDeleteFlag()) {
			d.setDeleteFlag(DeleteEnum.EXIST.getStatus());
		}
		
		P p = ConvertUtil.convert(d, clazzP);
		
		String orderBy = pageParams.getOrderBy();
		if (MyStringUtils.isEmpty(orderBy)) {
			orderBy = "update_time DESC";
		}
		
		Example example = selectPage(d);
		PageInfo<P> pageInfo = null;
		if (example == null) {
			pageInfo = PageHelper.startPage(pageParams.getPage(), pageParams.getSize(), orderBy)
					.doSelectPageInfo(() -> mapper.select(p));
		} else {
			pageInfo = PageHelper.startPage(pageParams.getPage(), pageParams.getSize(), orderBy)
					.doSelectPageInfo(() -> mapper.selectByExample(example));
		}
		Pager<D> result = PagerUtil.convertPage(pageInfo, clazzD);
		
		pageAfter(result);
		return result;
	}
	
	/**
	 * 重写该方法实现分页条件查询
	 * @param d
	 * @return
	 */
	protected Example selectPage(D d) {
		return null;
	};
	
	/**
	 * 重写该方法，对分页查询出来的Pager<D>进行处理
	 * @param result
	 */
	protected void pageAfter(Pager<D> result) {}
	
	/**
	 * 重写该方法，分页前对dto数据进行处理
	 * @param d
	 */
	protected void pageBefore(D d) {}
	
	@Override
	public D baseInsertOrUpdate(D d) {
		Date now = DateUtil.now();
		insertOrUpdateBefore(d);
		if (null == d.getDeleteFlag()) {
			d.setDeleteFlag(DeleteEnum.EXIST.getStatus());
		}
		P p = newTclass(this.clazzP);
		BeanUtils.copyProperties(d, p);
		Long id = p.getId();
		
		if (null != id) {
			updateBefore(p);
			Example example = updateExample(p);
			p.setUpdateTime(now);
			if (example == null) {
				mapper.updateByPrimaryKeySelective(p);
			} else {
				mapper.updateByExampleSelective(p,example);
			}
		} else {
			p.setCreateTime(now);
			p.setUpdateTime(now);
			insertBefore(p);
			mapper.insertSelective(p);
		}
		BeanUtils.copyProperties(p, d);
		return d;
	}
	
	/**
	 * 重写该方法，更新的时候指定更新条件，默认以主键进行更新
	 * @param p 要进行更新的po
	 * @return 更新条件
	 */
	protected Example updateExample(P p){
		return  null;
	}
	
	/**
	 * 重写该接口，对插入前po进行处理
	 * @param p
	 */
	protected void insertBefore(P p) {}
	
	/**
	 * 重写该接口，对更新前po进行处理
	 * @param p
	 */
	protected void updateBefore(P p) {}
	
	/**
	 * 重写该接口，对插入或更新前dto进行处理
	 * @param d
	 */
	protected void insertOrUpdateBefore(D d) {}
	
	@Override
	public void baseDeleteById(Serializable id, D d) {
		if (null == id ||
				(id instanceof String && MyStringUtils.isNotNull((String) id))) {
			throw new RuntimeException("删除操作必须传递主键");
		}

		P p = mapper.selectByPrimaryKey(id);
		deleteBefore(p, d);

		p.setDeleteFlag(DeleteEnum.DELETE.getStatus());
		p.setUpdateTime(DateUtil.now());
		mapper.updateByPrimaryKey(p);

		deleteAfter(p,d);
	}
	
	/**
	 * 重写该方法，删除后操作
	 * @param p
	 * @param d
	 */
	protected void deleteAfter(P p, D d) {}
	
	/**
	 * 重写该方法，删除前操作
	 * @param p
	 */
	protected void deleteBefore(P p, D d) {}
	
	@Override
	public D baseGetById (Serializable id) {
		if (null == id) {
			return null;
		}
		
		P p = mapper.selectByPrimaryKey(id);
		if (p == null) {
			return null;
		}
		
		D dto = newTclass(clazzD);
		BeanUtils.copyProperties(p, dto);
		return dto;
	}
	
	/**
	 * 创建实体类
	 * @param clazz
	 * @return
	 */
	private <T> T newTclass(Class<T> clazz) {
		try {
			T a = clazz.newInstance();
			return a;
		} catch (Exception e) {
			log.error("创建实体类失败：{}", e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
}
