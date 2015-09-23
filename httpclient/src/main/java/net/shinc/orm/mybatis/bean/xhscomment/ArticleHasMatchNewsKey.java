package net.shinc.orm.mybatis.bean.xhscomment;

public class ArticleHasMatchNewsKey {
	
    private String articleId;

    private Integer matchNewsId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public Integer getMatchNewsId() {
        return matchNewsId;
    }

    public void setMatchNewsId(Integer matchNewsId) {
        this.matchNewsId = matchNewsId;
    }
}