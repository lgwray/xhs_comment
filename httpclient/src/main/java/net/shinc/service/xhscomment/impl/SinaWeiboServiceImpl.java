package net.shinc.service.xhscomment.impl;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.mappers.xhscomment.SinaWeiboMapper;
import net.shinc.service.xhscomment.SinaWeiboService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/** 
 * @ClassName SinaWeiboServiceImpl 
 * @Description 新浪微博接口新实现
 * @author guoshijie 
 * @date 2015年10月12日 上午10:47:04  
 */
@Service
public class SinaWeiboServiceImpl implements SinaWeiboService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String pattern = "yyyy-MM-dd";
	
	@Autowired
	private SinaWeiboMapper sinaWbMapper;
	
	@Override
	public List<Map> getSinaWbCommentByArticleId(String articleId, String page, String num) {
		PageBounds pb = new PageBounds(Integer.parseInt(page),Integer.parseInt(num));
		List list = sinaWbMapper.getSinaWbCommentByArticleId(articleId, pb);
		return list;
	}
	

}
