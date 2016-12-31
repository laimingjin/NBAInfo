package bl.tool;

import java.util.Date;


/**
 *@author:小春
 *@date:2015年6月10日上午10:54:15
 *@version
 */

public class SeasonCheckTool {

	public static boolean checkSeason(String dateString,String season){
		String seasonStart=season.split("-")[0];
		String seasonEnd=Integer.toString(Integer.parseInt(seasonStart)+1);
		seasonStart=seasonStart+"-10-10";//赛季开始
		seasonEnd=seasonEnd+"-05-01";//常规赛结束
		Date datecurrent=TimeChangeTool.strToDate(dateString);//得到日期
		Date dateStart=TimeChangeTool.strToDate(seasonStart);
		Date dateEnd=TimeChangeTool.strToDate(seasonEnd);
		if (datecurrent.after(dateStart)&&datecurrent.before(dateEnd)) {
			return true;
		}
		return false;
	}
}
