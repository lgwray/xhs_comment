package net.shinc.orm.mybatis.bean.xhscomment;

import java.util.Date;

public class MatchLog {
    private Integer id;

    private Integer matchNewsId;

    private Date collectTime;

    private Boolean isSuccess;

    private String remark;

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

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}