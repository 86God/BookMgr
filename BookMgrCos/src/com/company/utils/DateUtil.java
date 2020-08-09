package com.company.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 提供字符串转日期,日期转字符串
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 将指定的日期转为指定格式的字符串
	 * @param date 指定的日期
	 * @param p 指定格式
	 * @return 返回格式化后的字符串日期
	 */
	public static String dateToString(Date date,String p){
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.format(date);
	}

	/**
	 * 将当前日期转为指定格式字符串
	 * @param p
	 * @return
	 */
	public static String currentDateToString(String p){
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.format(new Date());
	}

	/**
	 * 字符串转日期
	 * @param datestr
	 * @param p
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String datestr,String p) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		return sdf.parse(datestr);
	}


}
