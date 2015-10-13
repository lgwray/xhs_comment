package net.shinc.service.xhscomment.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.XhsNav;
import net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.XhsNavMapper;
import net.shinc.service.NewsService;
import net.shinc.service.xhscomment.XhsNavService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpClient;
import net.shinc.utils.ParamUtils;
import net.shinc.utils.UnicodeUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
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
	
	@Autowired
	private XhsNavMapper xhsNavMapper;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ArticleMapper articleMapper;
	
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
							List<XhsNav> parseNav4 = parseNav(datalist, provinceId);
							list.addAll(parseNav4);
							for (Object object : datalist) {
								Map map3 = (Map)object;
								List itemList2 = (List)map3.get("items");
								List<XhsNav> parseNav5 = parseNav(itemList2,null);
								list.addAll(parseNav5);
							}
						}
						logger.info(UnicodeUtils.decodeUnicode(itemres));
					}
				}
			}
			logger.info(data.toString());
		}
		logger.info(UnicodeUtils.decodeUnicode(res));
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


	@Override
	public Integer addXhsNavBatch() {
		List<XhsNav> list2 = getXhsNavList();
//		int i = xhsNavMapper.insertBatch(list2);
		int sum = 0;
		for (XhsNav xhsNav : list2) {
			try {
				int i = xhsNavMapper.insert(xhsNav);
				sum = sum+i;
			} catch (Exception e) {
				logger.info(ExceptionUtils.getStackTrace(e));
			}
		}
		return sum;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Integer fetchLocalArticleList() {
		Integer sum = 0;
		List<XhsNav> xhsNavList = getXhsNavList();
		int channelSum = xhsNavList.size();
		for (int i = 0; i < channelSum;i++) {//各频道
			XhsNav xhsNav = xhsNavList.get(i);
			Integer sub = 0;
			Integer exist = 0;
			List articleList = newsService.getNewsList("0", String.valueOf(xhsNav.getId()), xhsNav.getColumntype());
			if(!CollectionUtils.isEmpty(articleList)) {
				for (Object object : articleList) {
					Map map = (Map)object;
					map.put("xhsChannel", xhsNav.getName());
					map.put("category", xhsNav.getId());
					try {
						Integer num = articleMapper.insertLocalArticle(map);
						sub += num;
						sum += num;
					} catch (DuplicateKeyException e) {
						exist++;
					} catch (DataIntegrityViolationException e){
					} catch (UncategorizedSQLException e) {
					} catch(Exception e) {
						logger.info(ExceptionUtils.getStackTrace(e));
					}
				}
				logger.info((i + 1) + "/" + channelSum + "==>当前频道:" + xhsNav.getName() + "==>抓取新闻数:" + sub + "条"+"==>已存在条数:"+exist);
			}
		}
		logger.info("各地共抓取新闻数:"+sum+"条");
		return null;
	}

}
