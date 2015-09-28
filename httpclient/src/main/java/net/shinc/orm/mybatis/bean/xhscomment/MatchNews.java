package net.shinc.orm.mybatis.bean.xhscomment;

/** 
 * @ClassName MatchNews 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月18日 下午6:43:23  
 */
public class MatchNews {
    private Integer id;

    private String newsType;

    private String newsUuid;

    private String title;

    private String url;

    private String spiderTime;

    private String remark;
    
    private Integer matchCommentsNum;
    
    private String isAuto;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getSpiderTime() {
		return spiderTime;
	}

	public void setSpiderTime(String spiderTime) {
		this.spiderTime = spiderTime;
	}

	public Integer getMatchCommentsNum() {
		return matchCommentsNum;
	}

	public void setMatchCommentsNum(Integer matchCommentsNum) {
		this.matchCommentsNum = matchCommentsNum;
	}

	public String getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}
    
}