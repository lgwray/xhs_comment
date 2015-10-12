package net.shinc.orm.mybatis.bean.xhscomment;

public class SinaWeiboArticleKey {
    private Long mid;

    private String articleId;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }
}