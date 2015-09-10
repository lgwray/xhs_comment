package net.shinc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static String dateToString(Date date, String pattern){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
			String str = sdf.format(date);
			return str;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
}
