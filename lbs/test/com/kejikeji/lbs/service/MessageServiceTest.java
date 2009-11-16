package com.kejikeji.lbs.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kejikeji.common.test.BaseSpringTestCase;
import com.kejikeji.lbs.model.LocationCatalog;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.model.User;

public class MessageServiceTest extends BaseSpringTestCase {
	
	static Log log = LogFactory.getLog(MessageServiceTest.class);

	protected MessageService messageService;
	
	public void testGetLastedMessages() {
		fail("Not yet implemented");
	}

	public void testGetById() {
		Message ms=messageService.getById(1L);
		log.info(ms.getUser().getName());
	}

	public void testPublish() {
		Message ms=new Message();
		ms.setAudioFile("haha");
		ms.setContent("haha222");
		ms.setLocation(new LocationCatalog("860010",""));
		ms.setPicFile("ddd");
		ms.setPubDate(new Date());
		ms.setTitle("tttt");
		ms.setUser(new User(2L));
		
		messageService.publish(ms);
		
		super.setComplete();
	}

	public void testAddComment() {
		fail("Not yet implemented");
	}

	public void testAddRank() {
		fail("Not yet implemented");
	}

}
