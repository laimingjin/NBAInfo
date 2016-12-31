package bl.tool;

import java.util.Comparator;

import enumerate.TypeOfSort;
import enumerate.TypeOfSort4PlayerHigh;
import vo.PlayerHighVO;

/**
 * @author:小春
 * @date:2015年6月5日上午11:05:17
 * @version
 */

public class PlayerHighSortTool implements Comparator<PlayerHighVO> {
	TypeOfSort typeOfSort;
	TypeOfSort4PlayerHigh typeOfSort4PlayerHigh;
	public PlayerHighSortTool(TypeOfSort typeOfSort,
	TypeOfSort4PlayerHigh typeOfSort4PlayerHigh){
		this.typeOfSort=typeOfSort;
		this.typeOfSort4PlayerHigh=typeOfSort4PlayerHigh;
	}

	public int compare(PlayerHighVO o1, PlayerHighVO o2) {
		double num_first = o1.getNum(typeOfSort4PlayerHigh);
		double num_second = o2.getNum(typeOfSort4PlayerHigh);
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
