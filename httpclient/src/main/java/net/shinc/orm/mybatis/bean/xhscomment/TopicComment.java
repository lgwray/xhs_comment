package net.shinc.orm.mybatis.bean.xhscomment;


public class TopicComment {
    private Integer id;

    private Integer shTopicId;

    private String content;

    private String createTime;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}