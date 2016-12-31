package bl.tool;

import java.util.Comparator;

import vo.TeamTotalVO;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4Teams;

/**
 *@author:小春
 *@date:2015年6月5日下午3:31:50
 *@version
 */

public class TeamTotalSortTool implements Comparator<TeamTotalVO>{
	TypeOfSort typeOfSort;
	TypeOfSort4Teams typeOfSort4Teams;

	public TeamTotalSortTool(TypeOfSort typeOfSort,
			TypeOfSort4Teams typeOfSort4Teams) {
		this.typeOfSort = typeOfSort;
		this.typeOfSort4Teams = typeOfSort4Teams;
	}

	public int compare(TeamTotalVO o1, TeamTotalVO o2) {
		double num_first = o1.getNum(typeOfSort4Teams);
		double num_second = o2.getNum(typeOfSort4Teams);
		switch (typeOfSort) {
		case ASCENDING_ORDER_TOTAL:// 升序总数据
			if (num_first > num_second) {
				return 1;
			} else if (num_first < num_second) {
				return -1;
			}
			return 0;
		case DESCENDING_ORDER_TOTAL:// 降序总数据
			if (num_first < num_second) {
				return 1;
			} else if (num_first > num_second) {
				return -1;
			}
			return 0;
		case ASCENDING_ORDER_AVERAGE:// 升序平均数据
			int num_games_first = o1.getGP();
			int num_games_second = o2.getGP();
			if (num_games_first == 0) {
				num_games_first = 1;
			}
			if (num_games_second == 0) {
				num_games_second = 1;
			}
			double number_first = num_first / (double) num_games_first;
			double number_second = num_second / (double) num_games_second;
			if (number_first > number_second) {
				return 1;
			} else if (number_first < number_second) {
				return -1;
			}
			return 0;
		case DESCENDING_ORDER_AVERAGE:// 降序平均数据
			int num_games1 = o1.getGP();
			int num_games2 = o2.getGP();
			if (num_games1 == 0) {
				num_games1 = 1;
			}
			if (num_games2 == 0) {
				num_games2 = 1;
			}
			double number1 = num_first / (double) num_games1;
			double number2 = num_second / (double) num_games2;
			if (number1 < number2) {
				return 1;
			} else if (number1 > number2) {
				return -1;
			}
			return 0;
		default:
			return 0;
		}
	}

}
