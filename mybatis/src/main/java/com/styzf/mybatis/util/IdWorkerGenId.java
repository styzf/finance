package com.styzf.mybatis.util;

import com.styzf.core.common.util.IdWorker;
import com.sun.corba.se.spi.ior.ObjectId;
import tk.mybatis.mapper.genid.GenId;

/**
 * 基于mybatis-plus所使用的推特全局唯一主键方案
 * @author yangzf
 */
public class IdWorkerGenId implements GenId<Long> {
	
	@Override
	public Long genId(String arg0, String arg1) {
		return IdWorker.getId();
	}
	
}
