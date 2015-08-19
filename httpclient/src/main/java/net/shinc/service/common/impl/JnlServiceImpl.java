package net.shinc.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import net.shinc.orm.mybatis.mappers.common.JnlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName JnlServiceImpl 
 * @Description TODO
 * @author zhonglinzhao 
 * @date 2015年8月19日 下午5:03:17
 */
@Service
public class JnlServiceImpl {
	@Autowired
	private JnlMapper jnlMapper;
	public int insertJnlArticleComment(String articleId, String content,String contentType,String userId,String commentWay) {
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("content", content);
		map.put("contentType", contentType);
		map.put("userId", userId);
		map.put("commentWay", commentWay);
		return jnlMapper.insertJnlArticleComment(map);
	}
}
