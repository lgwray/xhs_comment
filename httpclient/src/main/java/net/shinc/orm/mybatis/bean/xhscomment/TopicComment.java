package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

public class TopicComment {
    private Integer id;

    private Integer shTopicId;

    private String content;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShTopicId() {
        return shTopicId;
    }

    public void setShTopicId(Integer shTopicId) {
        this.shTopicId = shTopicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}