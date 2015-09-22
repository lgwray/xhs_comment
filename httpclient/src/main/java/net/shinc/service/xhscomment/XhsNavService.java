package net.shinc.service.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.XhsNav;


/** 
 * @ClassName XhsNavService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月22日 下午5:25:59  
 */
public interface XhsNavService {
	
	/**
	 * 获取新华社所有导航栏目列表
	 * @return
	 */
	public List<XhsNav> getXhsNavList();

}
