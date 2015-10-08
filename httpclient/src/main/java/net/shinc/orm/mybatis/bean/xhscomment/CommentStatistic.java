package net.shinc.orm.mybatis.bean.xhscomment;

import java.math.BigDecimal;
import java.util.Date;

public class CommentStatistic {
    private Integer statisticType;

    private Integer divisor;

    private Integer dividend;

    private BigDecimal percent;

    private String insertDate;
    
    private Integer autoNum;
    
    private Integer articleNum;

    public Integer getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(Integer statisticType) {
        this.statisticType = statisticType;
    }

    public Integer getDivisor() {
        return divisor;
    }

    public void setDivisor(Integer divisor) {
        this.divisor = divisor;
    }

    public Integer getDividend() {
        return dividend;
    }

    public void setDividend(Integer dividend) {
        this.dividend = dividend;
    }

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public Integer getAutoNum() {
		return autoNum;
	}

	public void setAutoNum(Integer autoNum) {
		this.autoNum = autoNum;
	}

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
}