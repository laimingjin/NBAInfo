package bl.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@author:小春
 *@date:2015年4月3日下午6:48:56
 *用于将yyyymmdd格式的string转为date类型
 *@version
 */

public class TimeChangeTool {

	public static Date strToDate(String timeString){
		Date result=new Date();
		DateFormat toolFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			result=(Date)toolFormat.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
