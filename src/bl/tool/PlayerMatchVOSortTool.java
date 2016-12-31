package bl.tool;

import java.util.Comparator;

import enumerate.TypeOfSort4HotPointPlayer;
import vo.PlayerInMatchVO;

/**
 * @author:小春
 * @date:2015年6月6日上午9:56:59
 * @version
 */

public class PlayerMatchVOSortTool implements Comparator<PlayerInMatchVO> {
	TypeOfSort4HotPointPlayer typeOfSort4HotPointPlayer;

	public PlayerMatchVOSortTool(
			TypeOfSort4HotPointPlayer typeOfSort4HotPointPlayer) {
		this.typeOfSort4HotPointPlayer = typeOfSort4HotPointPlayer;
	}

	// 降序排序
	public int compare(PlayerInMatchVO o1, PlayerInMatchVO o2) {
		double num_first = o1.getNum(typeOfSort4HotPointPlayer);
		double num_second = o2.getNum(typeOfSort4HotPointPlayer);
		if (num_first < num_second) {
			return 1;
		} else if (num_first > num_second) {
			return -1;
		}
		return 0;
	}

}
