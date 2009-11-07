package com.kejikeji.lbs.service;

import java.util.List;

import com.kejikeji.lbs.model.Comment;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.model.Rank;

public interface MessageService {
	/**
	 * 获取最新信息列表
	 * @param locationCode
	 * @return
	 */
	public List<Message> getLastedMessages(String locationCode);
	
	/**
	 * 获取指定消息
	 * @param id
	 * @return
	 */
	public Message getById(Long id);
	
	/**
	 * 发布某消息
	 * @param message
	 */
	public void publish(Message message);
	
	/**
	 * 添加评论
	 * @param comment
	 */
	public void addComment(Comment comment);
	
	/**
	 * 添加评级
	 * @param rank
	 */
	public void addRank(Rank rank);
}
