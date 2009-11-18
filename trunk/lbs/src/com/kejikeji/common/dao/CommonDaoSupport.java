    /*
	 * Copyright (c) 2001-2008 Beijing BidLink Info-Tech Co., Ltd.
	 * All rights reserved.
	 * 北京必联信息技术有限公司 版权所有
	 *
	 * Created on 2008-5-22
	 *
	 */

package com.kejikeji.common.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kejikeji.lbs.model.Comment;

public abstract class CommonDaoSupport {
	protected Log log = LogFactory.getLog(this.getClass());
	
	protected ICommonDAO dao;

	public ICommonDAO getDao() {
		return dao;
	}

	public void setDao(ICommonDAO dao) {
		this.dao = dao;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}


	
	
}
