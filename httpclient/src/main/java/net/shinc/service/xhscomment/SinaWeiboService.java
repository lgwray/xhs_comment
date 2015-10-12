package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;


/** 
 * @ClassName SinaWeiboService 
 * @Description 新浪微博接口（新）
 * @author guoshijie 
 * @date 2015年10月12日 上午10:24:04  
 */
public interface SinaWeiboService {

	public List<Map> getSinaWbCommentByArticleId(String articleId,String page,String num);
}
