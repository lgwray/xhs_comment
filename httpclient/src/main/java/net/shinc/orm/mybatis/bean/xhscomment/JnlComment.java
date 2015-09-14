package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

import net.shinc.orm.mybatis.bean.common.ResultBean;


/**
 * 所发送的评论信息
 * @author zhangtaichao 2015年9月10日
 *
 */
public class JnlComment implements ResultBean {

	
	public enum SendFlag {
		nosend("0"),
		sending("1"),
		sent("2"),
		fail("3"),
		unknown("4");
		
		
		private String value;
		SendFlag(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		
	}
	
	private Integer id;
	private String articleId;
	private Date addDate;
	private String nickName;
	private String content;
	private String contentType;
	private Integer userId;
	private String commentWay;
	private String sendFlag;   //0=待发送，1=发送中，2=发送成功，3=发送失败，4=状态未明
	private Date sendTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCommentWay() {
		return commentWay;
	}
	public void setCommentWay(String commentWay) {
		this.commentWay = commentWay;
	}
	public String getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
