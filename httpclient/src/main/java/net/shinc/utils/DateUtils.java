package net.shinc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName DateUtils 
 * @Description 日期工具类
 * @author guoshijie 
 * @date 2015年9月18日 上午10:19:14  
 */
public class DateUtils {

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static String dateToString(Date date, String pattern){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
			String str = sdf.format(date);
			logger.info("dateToString:"+str);
			return str;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	
	public static Date stringToDate(String date, String pattern) {
		Date parsedate = null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);//小写的mm表示的是分钟  
			parsedate = sdf.parse(date);
		} catch (ParseException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return parsedate;
	}
	
    /** 
     * 设置时间 
     * @param year 
     * @param month 
     * @param date 
     * @return 
     */  
    public static Calendar setCalendar(int year,int month,int date){  
        Calendar cl = Calendar.getInstance();  
        cl.set(year, month-1, date);  
        return cl;  
    }  
      
    /** 
     * 获取当前时间的前一天时间 
     * @param cl 
     * @return 
     */  
    public static Calendar getBeforeDay(Calendar cl){  
        //使用roll方法进行向前回滚  
        //cl.roll(Calendar.DATE, -1);  
        //使用set方法直接进行设置  
        int day = cl.get(Calendar.DATE);  
        cl.set(Calendar.DATE, day-1);  
        return cl;  
    }  
      
    /** 
     * 获取当前时间的后一天时间 
     * @param cl 
     * @return 
     */  
    public static Calendar getAfterDay(Calendar cl){  
        //使用roll方法进行回滚到后一天的时间  
        //cl.roll(Calendar.DATE, 1);  
        //使用set方法直接设置时间值  
        int day = cl.get(Calendar.DATE);  
        cl.set(Calendar.DATE, day+1);  
        return cl;  
    }
    
    /**
     * 获取后几天的日期
     * @param cl
     * @param num
     * @return
     */
    public static Calendar getAfterDay(Calendar cl,Integer num) {
    	int day = cl.get(Calendar.DATE);  
        cl.set(Calendar.DATE, day+num);  
        return cl; 
    }
    
    /**
     * 获取后几天的日期
     * @param cl
     * @param num
     * @return
     */
    public static Date getAfterDays(Calendar cl,Integer num) {
    	Calendar cl2 = getAfterDay(cl, num);
    	return cl2.getTime(); 
    }
    
    /**
     * 获取前几天的日期list
     * @param num
     * @return
     */
    public static List<String> getBeforeFewsDate(Integer num, String pattern) {
    	List<String> list = new ArrayList<String>();
    	Calendar c = new GregorianCalendar();
    	list.add(dateToString(c.getTime(), pattern));
		for (int i = 0; i > 1 - num; i--) {
			c.add(Calendar.DAY_OF_MONTH, - 1);
			list.add(dateToString(c.getTime(), pattern));
		}
    	return list;
    }
      
    /** 
     * 打印时间 
     * @param cl 
     */  
    public static void printCalendar(Calendar cl) {  
        int year = cl.get(Calendar.YEAR);  
        int month = cl.get(Calendar.MONTH)+1;  
        int day = cl.get(Calendar.DATE); 
        logger.info(year+"-"+month+"-"+day);  
    }  
    
	/**
	 * 
	 * @param 要转换的毫秒数
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
	 * @author fy.zhang
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + " days " + hours + " hours " + minutes + " minutes "
				+ seconds + " seconds ";
	}
	
	/**
	 * 
	 * @param begin 时间段的开始
	 * @param end	时间段的结束
	 * @return	输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
	 * @author fy.zhang
	 */
	public static String formatDuring(Date begin, Date end) {
		return formatDuring(end.getTime() - begin.getTime());
	}
    
    public static void main(String[] args){  
        //当前时间  
//        Calendar cl = setCalendar(2014,01,01);  
//        logger.info("当前时间:");  
//        printCalendar(cl);  
//        //前一天  
//        cl = setCalendar(2014,01,01);  
//        getBeforeDay(cl);  
//        logger.info("前一天:");  
//        printCalendar(cl);  
//        //后一天  
//        cl = setCalendar(2014,01,01);  
//        getAfterDay(cl);  
//        logger.info("后一天:");  
//        printCalendar(cl);  
    	
//    	getBeforeFewsDate(7, "yyyy-MM-dd");
    	
    	Long l = 3690000l;
    	String str = formatDuring(l);
    	System.out.println(str);
    } 
}
