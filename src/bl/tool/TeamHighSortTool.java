package bl.tool;

import java.util.Comparator;

import vo.TeamHighVO;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4TeamsHigh;

/**
 *@author:小春
 *@date:2015年6月5日下午9:07:08
 *@version
 */

public class TeamHighSortTool implements Comparator<TeamHighVO>{
	TypeOfSort typeOfSort;
	TypeOfSort4TeamsHigh typeOfSort4TeamHigh;
	public TeamHighSortTool(TypeOfSort typeOfSort,
			TypeOfSort4TeamsHigh typeOfSort4PlayerHigh){
		this.typeOfSort=typeOfSort;
		this.typeOfSort4TeamHigh=typeOfSort4PlayerHigh;
	}
	public int compare(TeamHighVO o1, TeamHighVO o2) {
		double num_first = o1.getNum(typeOfSort4TeamHigh);
		double num_second = o2.getNum(typeOfSort4TeamHigh);
		switch (typeOfSort) {
		case ASCENDING_ORDER_TOTAL:// 升序总数据
		case ASCENDING_ORDER_AVERAGE:
			if (num_first > num_second) {
				return 1;
			} else if (num_first < num_second) {
				return -1;
			}
			return 0;
		case DESCENDING_ORDER_TOTAL:// 降序总数据
		case DESCENDING_ORDER_AVERAGE:
			if (num_first < num_second) {
				return 1;
			} else if (num_first > num_second) {
				return -1;
			}
			return 0;
		default:
			return 0;
		}
	}

}
