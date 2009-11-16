package com.kejikeji.lbs.service.impl;

import java.util.List;

import com.kejikeji.common.dao.CommonDaoSupport;
import com.kejikeji.lbs.model.Comment;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.model.Rank;
import com.kejikeji.lbs.service.MessageService;

public class MessageServiceImpl extends CommonDaoSupport implements MessageService {

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRank(Rank rank) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message getById(Long id) {
		return (Message) dao.get(Message.class, id);
	}

	@Override
	public List<Message> getLastedMessages(String locationCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void publish(Message message) {
		dao.save(message);

	}

}
