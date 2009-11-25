package com.kejikeji.lbs.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import com.kejikeji.common.web.BaseMultiActionController;
import com.kejikeji.lbs.model.Message;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.service.MessageService;
import com.kejikeji.lbs.view.bean.CommentResult;
import com.kejikeji.lbs.view.bean.MessageResult;
import com.kejikeji.lbs.view.bean.Result;

public class MessageAction extends BaseMultiActionController {
	
	private MessageService messageService;
	
	
	
	public MessageService getMessageService() {
		return messageService;
	}



	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}



	public ModelAndView item( HttpServletRequest request, HttpServletResponse response ){
		
		String postId=request.getParameter("postId");
		MessageResult mr=new MessageResult();
		
		try {
			Message message=messageService.getById(new Long(postId));
			mr.setTitle(message.getTitle());
			mr.setImagelink(message.getPicFile());
			mr.setCreateTime(DateFormatUtils.format(message.getPubDate(), "yyyy-mm-dd"));
			mr.setSoundlink(message.getAudioFile());
			mr.setContent(message.getContent());
			mr.setLocationCode(message.getLocation().getCode());
			mr.setReporter(message.getUser().getId());
			mr.setPostID(message.getId());
			mr.setResultCode(Result.SUCCESS);
			
			//添加评论
//			List<CommentResult> list=new ArrayList<CommentResult>();
//			for (Comment comment : message.get) {
//				
//			}
		} catch (Exception e) {			
			mr.setResultCode(Result.UNKNOWN_ERROR);
		}
		
		
		try {
			response.getWriter().print(new JSONObject(mr));
			response.flushBuffer();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return null;
		
	}
}
