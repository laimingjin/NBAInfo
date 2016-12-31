package bl.tool;

import java.util.Comparator;

import vo.PlayerImpMostVO;

/**
 *@author:小春
 *@date:2015年6月6日下午3:54:11
 *@version
 */

public class PlayerImpSortTool implements Comparator<PlayerImpMostVO>{

	public int compare(PlayerImpMostVO o1, PlayerImpMostVO o2) {

		double num_first=o1.getImpRate();
		double num_second=o2.getImpRate();
		if (num_first<num_second) {
			return 1;
		}else if (num_first>num_second) {
			return -1;
		}
		return 0;
	
	}

}
