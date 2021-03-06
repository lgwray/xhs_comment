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
import net.shinc.utils.UnicodeUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

/** 
 * @ClassName CommentSenderThread 
 * @Description 批量发评论
 * @author ztc 
 * @date 2015年10月8日 上午11:43:28  
 */
public class CommentSenderThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CommentServiceImpl bcs;
	
	@Autowired
	private JnlCommentService jcs;
	
	@Autowired
	private CommentQueue queue;
	
	private int batchUpdateSize = 50;
	
	@Value("${xhs.realSend}")
	private String realSend = "1";
	
	@Override
	public void run() {
		logger.info("started");
		List<JnlComment> list = new ArrayList<JnlComment>();
		while(true) {
			try {
				JnlComment comment = queue.poll();
				if(comment != null) {
					String flag = SendFlag.fail.getValue();
					
					if(realSend.equals("1")) {
						try {
							String words = comment.getContent();
							String newWords = words.replace("习", "");
							comment.setContent(newWords);
							String re = bcs.sendComment(String.valueOf(comment.getUserId()), comment.getArticleId(), newWords, comment.getNickName());
							logger.info("commentRes="+UnicodeUtils.decodeUnicode(re));
							Map resMap = Helper.jsonToMap(re);
							if(!CollectionUtils.isEmpty(resMap)) {
								if("success".equals(resMap.get("state"))){
									flag = SendFlag.sent.getValue();
								} 
							}
						} catch(Exception e) {
							flag = SendFlag.unknown.getValue();
							logger.error("send error:" + ExceptionUtils.getStackTrace(e));
						}
					} else {
						flag = SendFlag.test.getValue();
					}
					
					comment.setSendFlag(flag);
					comment.setSendTime(new Date());
					list.add(comment);
					String comment_way = comment.getCommentWay().equals("1")?"手动":"自动";
					logger.info("sent a comment:" + "articleid:" + comment.getArticleId() +" user:" + comment.getUserName() + 
							" comment_way:"+comment_way + " nickname:" + comment.getNickName() + " content:" + comment.getContent() + " flag:" + flag );
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
