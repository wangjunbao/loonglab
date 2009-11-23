package com.kejikeji.lbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kejikeji.common.dao.CommonDaoSupport;
import com.kejikeji.common.dao.hibernate.PageBean;
import com.kejikeji.lbs.model.Comment;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.service.MessageService;
import com.kejikeji.lbs.view.bean.PostCondition;

public class MessageServiceImpl extends CommonDaoSupport implements MessageService {

	@Override
	public void addComment(Comment comment) {
		dao.save(comment);

	}


	@Override
	public Message getById(Long id) {
		return (Message) dao.get(Message.class, id);
	}

	@Override
	public List<Message> getLastedMessages(PostCondition condition,PageBean page) {
		String hqlStr="from Message m where ";
		List<Object> paramList=new ArrayList<Object>();
		if(condition.getUserId()!=null){
			hqlStr=hqlStr+"m.user.id=? ";
			paramList.add(condition.getUserId());
		}
		if(condition.getExpectedDate()!=null){
			hqlStr=hqlStr+"m.pubDate<? ";
			paramList.add(condition.getExpectedDate());
		}
		if(condition.getLocationCode()!=null){
			hqlStr=hqlStr+"m.location.code=? ";
			paramList.add(condition.getLocationCode());
		}
		if(condition.getStartPostId()!=null){
			hqlStr=hqlStr+"m.id>?";
			paramList.add(condition.getStartPostId());
		}
		
		dao.find(hqlStr,page,paramList.toArray());
		
				
		return page.getResults();
	}

	@Override
	public void publish(Message message) {
		dao.save(message);

	}

}
