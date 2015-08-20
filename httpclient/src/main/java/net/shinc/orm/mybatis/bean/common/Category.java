package net.shinc.orm.mybatis.bean.common;

import java.text.MessageFormat;
import java.util.List;

public class Category {
	int id;
	String name ;
	int parent ;
	List<Category> children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
	
	
	@Override
	public String toString() {
		return MessageFormat.format("id:{0}\tname{1}\tparent:{2}\tchildren:{3}", this.id,this.name,this.parent,this.children);
	}
	
}
