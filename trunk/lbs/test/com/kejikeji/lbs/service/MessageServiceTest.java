package com.kejikeji.lbs.service;

import java.util.Date;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kejikeji.common.DateUtil;
import com.kejikeji.common.dao.hibernate.PageBean;
import com.kejikeji.common.test.BaseSpringTestCase;
import com.kejikeji.lbs.model.Comment;
import com.kejikeji.lbs.model.LocationCatalog;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.view.bean.PostCondition;

public class MessageServiceTest extends BaseSpringTestCase {
	
	static Log log = LogFactory.getLog(MessageServiceTest.class);

	protected MessageService messageService;
	
	public void testGetLastedMessages() {
		PostCondition cond=new PostCondition();
		cond.setReporterId(1L);
		cond.setLocationCode("860020");
		//cond.setStartPostId(1L);
		
		//Date date=DateUtil.parse("2009-11-24 00:00:00");
		//cond.setExpectedDate(date);
		
		PageBean page=new PageBean(1,20);
		
		List<Message> list=messageService.getLastedMessages(cond, page);
		
		for (Message message : list) {
			log.info("id="+message.getId()+",title="+message.getTitle());
		}
		log.info(page.getTotalRecords());
	}

	public void testGetById() {
		Message ms=messageService.getById(1L);
		log.info(ms.getUser().getUserName());
	}

	public void testPublish() {
		Message ms=new Message();
		ms.setAudioFile("haha");
		ms.setContent("haha222");
		ms.setLocation(new LocationCatalog("860010",""));
		ms.setPicFile("ddd");
		ms.setPubDate(new Date());
		ms.setTitle("tttt");
		ms.setUser(new User(1L));
		
		messageService.publish(ms);
		
		super.setComplete();
	}

	public void testAddComment() {
		Comment comment=new Comment();
		comment.setComment("hihi");
		comment.setPostId(1L);
		comment.setTitle("haha");
		comment.setPublisher(new User(2L));
		comment.setPubdate(new Date());
		
		messageService.addComment(comment);
		
		super.setComplete();
	}

	public void testAddRank() {
		fail("Not yet implemented");
	}

}
