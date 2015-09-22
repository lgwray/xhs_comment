package net.shinc.service.xhscomment.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.XhsNav;
import net.shinc.service.xhscomment.XhsNavService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpClient;
import net.shinc.utils.ParamUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/** 
 * @ClassName XhsNavServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月22日 下午5:38:53  
 */
@Service
public class XhsNavServiceImpl implements XhsNavService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${xhs.nav.url}")
	private String xhsNavUrl;
	
	@Value("${xhs.nav.citylist}")
	private String citylist;
	
	@Override
	public List<XhsNav> getXhsNavList() {
		List<XhsNav> list = new ArrayList<XhsNav>();
		
		String res = HttpClient.post(xhsNavUrl, ParamUtils.getCommonParamList("0"));
		Map map = Helper.jsonToMap(res);
		
		String state = (String)map.get("state");
		if("success".equals(state)) {
			List data_order = (List)map.get("data_order");
			List<XhsNav> parseNav = parseNav(data_order,null);
			list.addAll(parseNav);
			
			List data = (List)map.get("data");
			List<XhsNav> parseNav2 = parseNav(data,null);
			list.addAll(parseNav2);
			
			for (Object obj : data) {
				Map m = (Map)obj;
				String id = (String)m.get("id");
				String name = (String)m.get("name");
				List itemList = (List)m.get("items");
				List<XhsNav> parseNav3 = parseNav(itemList,null);
				list.addAll(parseNav3);
				
				if("468".equals(id)) {
					for (Object obj2 : itemList){
						Map m2 = (Map)obj2;
						String idstr = (String)m2.get("id");
						List<NameValuePair> paramList = ParamUtils.getCommonParamList("0");
						paramList.add(new BasicNameValuePair("provinceid", idstr));
						String itemres = HttpClient.post(citylist, paramList);
						Map jsonToMap = Helper.jsonToMap(itemres);
						
						String state2 = (String)jsonToMap.get("state");
						if("success".equals(state2)){
							Map province = (Map)jsonToMap.get("province");
							XhsNav xhsNav = getXhsNavFromMap(province);
							list.add(xhsNav);
							
							String provinceId = xhsNav.getId().toString();
							List datalist = (List)jsonToMap.get("data");
							for (Object object : datalist) {
								
							}
						}
						logger.info(itemres);
					}
				}
			}
			logger.info(data.toString());
		}
		logger.info(res);
		return list;
	}
	
	
	public List<XhsNav> parseNav(List list,String parent) {
		List<XhsNav> res = new ArrayList<XhsNav>();
		
		if(!CollectionUtils.isEmpty(list)) {
			for (Object object : list) {
				Map map2 = (Map)object;
				XhsNav nav = new XhsNav();
				
				Integer id = Integer.parseInt((String)map2.get("id"));
				String name = (String)map2.get("name");
				String columntype = (String)map2.get("columntype");
				
				String parentid = "";
				if(!StringUtils.isEmpty(parent)){
					 parentid = parent;
				} else {
					 parentid = convert(map2.get("parentid"));
				}
				
				String url = (String)map2.get("url");
				String orderid = convert(map2.get("orderid"));
				String hasorder = convert(map2.get("hasorder"));
				
				nav.setId(id);
				nav.setName(name);
				nav.setColumntype(columntype);
				nav.setParentid(parentid);
				nav.setUrl(url);
				nav.setOrderid(orderid);
				nav.setHasorder(hasorder);
				res.add(nav);
			}
		}
		return res;
	}
	
	public XhsNav getXhsNavFromMap(Map map2){
		XhsNav nav = new XhsNav();
		
		Integer id = Integer.parseInt((String)map2.get("id"));
		String name = (String)map2.get("name");
		String columntype = (String)map2.get("columntype");
		String parentid = convert(map2.get("parentid"));
		String url = (String)map2.get("url");
		String orderid = convert(map2.get("orderid"));
		String hasorder = convert(map2.get("hasorder"));
		
		nav.setId(id);
		nav.setName(name);
		nav.setColumntype(columntype);
		nav.setParentid(parentid);
		nav.setUrl(url);
		nav.setOrderid(orderid);
		nav.setHasorder(hasorder);
		
		return nav;
	}
	
	public String convert(Object obj) {
		String str = "";
		if(obj  instanceof Double) {
			int intValue = ((Double) obj).intValue();
			str = new Integer(intValue).toString();
		}
		if(obj  instanceof String) {
			str = (String)obj;
		}
		return str;
	}

}
