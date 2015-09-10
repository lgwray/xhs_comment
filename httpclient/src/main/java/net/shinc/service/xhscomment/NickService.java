package net.shinc.service.xhscomment;

import java.util.Map;

import net.shinc.formbean.common.NickForm;
import net.shinc.orm.mybatis.bean.xhscomment.Nick;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @ClassName NickService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月10日 下午3:39:10  
 */
public interface NickService {

	public PageList<Nick> getNickListByPage(PageBounds pageBounds);
	
	public PageList<Map> getNickListByPage2(PageBounds pageBounds);
	
	public Integer deleteNick(Nick nick);
	
	public Integer filterNick(NickForm nickform);
	
	
}
