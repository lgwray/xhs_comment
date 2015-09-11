package net.shinc.xhscomment.task.sender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment.SendFlag;
import net.shinc.service.impl.CommentServiceImpl;
import net.shinc.service.xhscomment.JnlCommentService;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentSenderThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CommentServiceImpl bcs;
	
	@Autowired
	private JnlCommentService jcs;
	
	@Autowired
	private CommentQueue queue;
	
	private int batchUpdateSize = 50;
	
	private boolean realSend = true;
	@Override
	public void run() {
		logger.info("started");
		List<JnlComment> list = new ArrayList<JnlComment>();
		while(true) {
			try {
				JnlComment comment = queue.poll();
				if(comment != null) {
					String flag = SendFlag.fail.getValue();
					if(realSend) {
						String re = bcs.sendComment(String.valueOf(comment.getUserId()), comment.getArticleId(), comment.getContent(), comment.getNickName());
						Map resMap = Helper.jsonToMap(re);
						if("success".equals(resMap.get("state"))){
							flag = SendFlag.sent.getValue();
						} 
					} else {
						flag = SendFlag.unknown.getValue();
					}
					
					comment.setSendFlag(flag);
					comment.setSendTime(new Date());
					list.add(comment);
					logger.debug("sent a comment:\n" + "articleid:" + comment.getArticleId() + " nickname:" + comment.getNickName() + " content:" + comment.getContent());
					if(list.size() > batchUpdateSize) {
						jcs.updateCommentSendFlag(list);
						list.clear();
					}
				} else {
					if(list.size() > 0) {
						jcs.updateCommentSendFlag(list);
						list.clear();
					}
					Thread.sleep(10000);
				}
				
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
		}
		 
	}

}
