package bl.tool;

import java.util.Comparator;

import vo.PlayerTotalVO;

/**
 * @author:小春
 * @date:2015年6月11日下午7:53:17
 * @version
 */

public class PlayerTotalDateSortTool implements Comparator<PlayerTotalVO> {

	public int compare(PlayerTotalVO o1, PlayerTotalVO o2) {
		String time1 = o1.getMatchSeason().split("-")[0];
		String time2 = o2.getMatchSeason().split("-")[0];
		int num_first = Integer.parseInt(time1);
		int num_second = Integer.parseInt(time2);
		if (num_first < num_second) {
			return 1;
		} else if (num_first > num_second) {
			return -1;
		}
		return 0;
	}

}
