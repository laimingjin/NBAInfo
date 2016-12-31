package bl.tool;

import java.util.Comparator;
import java.util.Date;

import po.TeamInMatchPO;
import enumerate.TypeOfSort;

/**
 *@author:小春
 *@date:2015年6月4日上午11:10:54
 *@version
 */

public class TeamMatchSortTool implements Comparator<TeamInMatchPO>{
	
	/**
	 * 如果o1>o2,返回-1，若o1<o2,返回1，相等则返回0
	 * 日期是降序排序
	 */
	public int compare(TeamInMatchPO o1, TeamInMatchPO o2) {
		//时间降序
			Date date1=TimeChangeTool.strToDate(o1.getMatchDate());
			Date date2=TimeChangeTool.strToDate(o2.getMatchDate());
			if (date1.before(date2)) {
				return 1;
			}else if (date1.after(date2)) {
				return -1;
			}	
		
		return 0;
	}

}
