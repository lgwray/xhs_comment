package net.shinc.orm.mybatis.bean.xhscomment;

import java.text.MessageFormat;
import java.util.Date;

public class MatchComment {
    private Integer id;

    private Integer matchNewsId;

    private String cmtUuid;

    private String nick;

    private Date publishTime;

    private Boolean isHot;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchNewsId() {
        return matchNewsId;
    }

    public void setMatchNewsId(Integer matchNewsId) {
        this.matchNewsId = matchNewsId;
    }

    public String getCmtUuid() {
        return cmtUuid;
    }

    public void setCmtUuid(String cmtUuid) {
        this.cmtUuid = cmtUuid == null ? null : cmtUuid.trim();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
    
    @Override
    public String toString() {
    	return MessageFormat.format("id:{0}\tmatchNewsId:{1}\tpublishTime:{2}\tnick:{3}\tcomment:{4}\tisHot:{5}", this.id,this.matchNewsId,this.publishTime,this.nick,this.comment,this.isHot);
    }
}