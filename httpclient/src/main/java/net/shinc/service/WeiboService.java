package net.shinc.service;

import java.util.List;

/** 
 * @ClassName WeiboService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月9日 下午2:47:40  
 */
public interface WeiboService {

	public String getWeiboComments(String type, String mid, String page, String num);
	public List getWeiboCommentsList(String type, String mid, String page, String num);
}
