package net.shinc.orm.mybatis.bean.xhscomment;

public enum ArticleCategory {
	
	要闻("470" , "4001","0" ,"要闻"),
	体育("461" , "4002","1" ,"体育"),
	国际("462" , "4002","2" ,"国际"),
	财经("463" , "4002","3" ,"财经"),
	军事("464" , "4002","4" ,"军事"),
	汽车("477" , "4002","5" ,"汽车"),
	图片("479" , "4002","6" ,"图片"),
	视频("480" , "4002","7" ,"视频"),
	评论("235" , "4003","8" ,"评论"),
	社会("3172", "4003","9" ,"社会"),
	科技("3173", "4003","10","科技"),
	动新闻("3174", "4003","11","动新闻");
	
	private String cid;
	private String ctype;
	private String value;
	private String remark;
	
	ArticleCategory(String cid,String ctype,String value,String remark) {
		this.cid = cid;
		this.ctype = ctype;
		this.value = value;
		this.remark = remark;
				
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
