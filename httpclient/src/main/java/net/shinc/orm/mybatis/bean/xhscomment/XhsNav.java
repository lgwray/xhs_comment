package net.shinc.orm.mybatis.bean.xhscomment;

public class XhsNav {
    private Integer id;

    private String name;

    private String columntype;

    private String parentid;

    private String url;

    private String orderid = "0";

    private String hasorder = "0";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getColumntype() {
        return columntype;
    }

    public void setColumntype(String columntype) {
        this.columntype = columntype == null ? null : columntype.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getHasorder() {
        return hasorder;
    }

    public void setHasorder(String hasorder) {
        this.hasorder = hasorder == null ? null : hasorder.trim();
    }
}