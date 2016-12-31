package bl.tool;

import java.util.Comparator;
import java.util.Date;

import po.PlayerInMatchPO;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4HotPointPlayer;

/**
 * @author:小春
 * @date:2015年4月3日下午6:53:09
 * @version
 */

public class PlayerMatchSortTool implements Comparator<PlayerInMatchPO> {

	
	/**
	 * 如果o1>o2,返回-1，若o1<o2,返回1，相等则返回0 日期是降序排序
	 */
	public int compare(PlayerInMatchPO o1, PlayerInMatchPO o2) {
		// 时间降序
		Date date1 = TimeChangeTool.strToDate(o1.getMatchDate());
		Date date2 = TimeChangeTool.strToDate(o2.getMatchDate());
		
		if (date1.before(date2)) {
			return 1;
		} else if (date1.after(date2)) {
			return -1;
		}

		return 0;
	}

}
