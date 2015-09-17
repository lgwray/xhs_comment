package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

public class MatchNews {
    private Integer id;

    private String newsType;

    private String newsUuid;

    private String title;

    private String url;

    private Date spiderTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType == null ? null : newsType.trim();
    }

    public String getNewsUuid() {
        return newsUuid;
    }

    public void setNewsUuid(String newsUuid) {
        this.newsUuid = newsUuid == null ? null : newsUuid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getSpiderTime() {
        return spiderTime;
    }

    public void setSpiderTime(Date spiderTime) {
        this.spiderTime = spiderTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}