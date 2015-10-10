package net.shinc.orm.mybatis.bean.xhscomment;

/** 
 * @ClassName CommentReport 
 * @Description 报表
 * @author guoshijie 
 * @date 2015年10月10日 上午11:03:19  
 */
public class CommentReport {
    private String createTime;

    private Integer adminUserId;

    private Integer total;

    private Integer autoSum;

    private Integer handSum;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAutoSum() {
        return autoSum;
    }

    public void setAutoSum(Integer autoSum) {
        this.autoSum = autoSum;
    }

    public Integer getHandSum() {
        return handSum;
    }

    public void setHandSum(Integer handSum) {
        this.handSum = handSum;
    }

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}