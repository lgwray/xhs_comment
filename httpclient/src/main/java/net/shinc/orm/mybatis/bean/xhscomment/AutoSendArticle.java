package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class AutoSendArticle {
	
    private Integer id;

    @NotEmpty(message="{articleId.not.empty}")
    private String articleId;

    @NotEmpty(message="{matchNewsId.not.empty}")
    private String matchNewsId;

    @NotEmpty(message="{enabled.not.empty}")
    private String enabled;

    private Integer userId;

    private Date begindate;

    private Date enddate;

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
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

	public String getMatchNewsId() {
		return matchNewsId;
	}

	public void setMatchNewsId(String matchNewsId) {
		this.matchNewsId = matchNewsId;
	}
}