package blServiceImp;

import java.util.ArrayList;
import java.util.Collections;

import vo.PlayerBasicVO;
import vo.PlayerImpMostVO;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import vo.TeamBasicVO;
import vo.TeamTotalVO;
import bl.tool.PlayerImpSortTool;
import bl.tool.PlayerMatchVOSortTool;
import blService.HotBLService;
import blService.MatchBLService;
import blService.PlayerBLService;
import blService.TeamBLService;
import dataServiceImp.DatabaseException;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4HotPointPlayer;
import enumerate.TypeOfSort4MostImprovedPlayer;
import enumerate.TypeOfSort4Players;
import enumerate.TypeOfSort4Teams;

/**
 * @author:小春
 * @date:2015年6月5日下午9:17:06
 * @version
 */

public class HotBLImp implements HotBLService {
	/**
	 * 取得当天的热点球员，5个人(因为考虑到数据可能没有当日的，所以修改成日期可以确定)
	 * 
	 * @param typeOfSort
	 *            排序方式
	 * @param date
	 *            日期
	 * @return 当天热点球员,比赛数据为列表,仅包含当天
	 */

	PlayerBLService playerBLService;
	TeamBLService teamBLService;
	MatchBLService matchBLService;
	static ArrayList<PlayerBasicVO> mostImpPlayerPST = null;// 得分
	static ArrayList<PlayerBasicVO> mostImpPlayerREB = null;// 篮板
	static ArrayList<PlayerBasicVO> mostImpPlayerAST = null;// 助攻
	static ArrayList<PlayerBasicVO> mostImpPlayerBLK = null;// 盖帽
	static ArrayList<PlayerBasicVO> mostImpPlayerSTL = null;// 抢断
	static ArrayList<PlayerBasicVO> mostImpPlayerTPPer = null;// 三分
	static ArrayList<PlayerBasicVO> mostImpPlayerSHPer = null;// 投篮
	static ArrayList<PlayerBasicVO> mostImpPlayerFREPer = null;// 罚球

	public HotBLImp() {
		try {
			playerBLService = new PlayerBLImp();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		teamBLService = new TeamBLImp();
		matchBLService = new MatchBLImp();
		mostImpPlayerPST = getInstancePST();
//		mostImpPlayerREB = getInstanceREB();// 篮板
//		mostImpPlayerAST = getInstanceAST();// 助攻
//		mostImpPlayerBLK = getInstanceBLK();// 盖帽
//		mostImpPlayerSTL = getInstanceSTL();// 抢断
//		mostImpPlayerTPPer = getInstanceTPPer();// 三分
//		mostImpPlayerSHPer = getInstanceSHPer();// 投篮
//		mostImpPlayerFREPer = getInstanceFREPer();// 罚球
	}

	public ArrayList<PlayerBasicVO> getHotPointPlayerToday(
			TypeOfSort4HotPointPlayer typeOfSort) {
		ArrayList<PlayerInMatchVO> tempList = matchBLService
				.getPlayerByDate("2015-06-04");// 得到当天球员比赛数据
		ArrayList<PlayerBasicVO> result = new ArrayList<PlayerBasicVO>();
		PlayerMatchVOSortTool tool = new PlayerMatchVOSortTool(typeOfSort);
		if (tempList != null) {
			Collections.sort(tempList, tool);
			int num = 5;
			if (tempList.size() < 5) {
				num = tempList.size();
			}
			for (int i = 0; i < num; i++) {
				PlayerBasicVO tempBasic = playerBLService
						.getPlayerDetail(tempList.get(i).getPlayerID());
				if (tempBasic != null) {
					result.add(tempBasic);
				}
			}
		}
		return result;
	}

	/**
	 * 取得赛季的热点球员，5个人
	 * 
	 * @param typeOfSort
	 *            排序方式
	 * @return 赛季热点球员,比赛数据为列表,仅包含当前赛季
	 */
	public ArrayList<PlayerBasicVO> getHotPointPlayerSeason(
			TypeOfSort4HotPointPlayer item) {
		TypeOfSort4Players type = checkSortType(item);// 得到对应的排序类型
		ArrayList<PlayerTotalVO> tempList = playerBLService.getAllTotal(
				TypeOfMatch.REGULAR, "2014-15");// 当前赛季的常规赛
		ArrayList<PlayerBasicVO> result = new ArrayList<PlayerBasicVO>();
		if (tempList != null) {
			tempList = playerBLService.sortBySelectedTotal(tempList, type,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 场均降序排序
			int num = 5;
			if (tempList.size() < 5) {
				num = tempList.size();
			}
			for (int i = 0; i < num; i++) {
				PlayerBasicVO tempBasicVO = playerBLService
						.getPlayerDetail(tempList.get(i).getPlayerID());// 得到基本信息
				if (tempBasicVO != null) {
					result.add(tempBasicVO);
				}
			}
		}
		return result;
	}

	/**
	 * 取得赛季的热点球队，5只
	 * 
	 * @param typeOfSort
	 *            排序方式
	 * @return 赛季热点球队，5只,,比赛数据为列表,仅包含当前赛季
	 */
	public ArrayList<TeamBasicVO> getHotPointTeamSeason(TypeOfSort4Teams item) {
		ArrayList<TeamTotalVO> tempList = teamBLService.sortBySelectedItem(
				TypeOfMatch.REGULAR, "2014-15", item,
				TypeOfSort.DESCENDING_ORDER_AVERAGE);// 得到已经排好序的球队列表
		ArrayList<TeamBasicVO> result = new ArrayList<TeamBasicVO>();
		int num = 5;
		if (tempList != null) {
			if (tempList.size() < 5) {
				num = tempList.size();
			}
			for (int i = 0; i < num; i++) {
				TeamBasicVO tempBasicVO = teamBLService
						.get_from_abbreviation(tempList.get(i).getTeamName());// 得到基本信息
				if (tempBasicVO != null) {
					result.add(tempBasicVO);
				}
			}
		}
		return result;
	}

	/**
	 * 取得进步最快球员，5个
	 * 
	 * @param typeOfSort
	 *            排序方式
	 * @return 进步最快球员，5个,比赛数据为列表,仅包含最近5场
	 */
	public ArrayList<PlayerBasicVO> getMostImprovedPlayer(
			TypeOfSort4MostImprovedPlayer typeOfSort) {
		switch (typeOfSort) {
		case AVERAGE_PSCORING:
			return mostImpPlayerPST;
		case AVERAGE_REBOUND:// 場均篮板
			return mostImpPlayerREB;
		case AVERAGE_ASSIST:// 場均助攻
			return mostImpPlayerAST;
		case AVERAGE_BLOCK:// 場均盖帽
			return mostImpPlayerBLK;
		case AVERAGE_STLS_STEALS:// 場均抢断
			return mostImpPlayerSTL;
		case AVERAGE_THREEPOINT: // 三分命中率
			return mostImpPlayerTPPer;
		case AVERAGE_SHOTING:// 投籃命中率
			return mostImpPlayerSHPer;
		case AVERAGE_FREETHROW:// 罰球命中率
			return mostImpPlayerFREPer;
		default:
			return null;
		}

	}

	private TypeOfSort4Players checkSortType(TypeOfSort4HotPointPlayer type) {
		switch (type) {
		case SCORING:
			return TypeOfSort4Players.PTS;
		case REBOUND:
			return TypeOfSort4Players.REB;
		case ASSIST:
			return TypeOfSort4Players.AST;
		case BLOCK:
			return TypeOfSort4Players.BLK;
		case STLSv_STEALS:
			return TypeOfSort4Players.STL;
		case TPPer:// 三分命中率
			return TypeOfSort4Players.TPPer;
		case FGPer:// 投篮命中率
			return TypeOfSort4Players.FGPer;
		case FTPer:// 罚球命中率
			return TypeOfSort4Players.FTPer;
		default:
			return TypeOfSort4Players.ETTOR;
		}
	}

	private ArrayList<PlayerBasicVO> getInstancePST() {
		if (mostImpPlayerPST != null) {
			return mostImpPlayerPST;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_PSCORING);
	}

	private ArrayList<PlayerBasicVO> getInstanceREB() {
		if (mostImpPlayerREB != null) {
			return mostImpPlayerREB;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_REBOUND);
	}

	private ArrayList<PlayerBasicVO> getInstanceAST() {
		if (mostImpPlayerAST != null) {
			return mostImpPlayerAST;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_ASSIST);
	}

	private ArrayList<PlayerBasicVO> getInstanceBLK() {
		if (mostImpPlayerBLK != null) {
			return mostImpPlayerBLK;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_BLOCK);
	}

	private ArrayList<PlayerBasicVO> getInstanceSTL() {
		if (mostImpPlayerSTL != null) {
			return mostImpPlayerSTL;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_STLS_STEALS);
	}

	private ArrayList<PlayerBasicVO> getInstanceTPPer() {
		if (mostImpPlayerTPPer != null) {
			return mostImpPlayerTPPer;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_THREEPOINT);
	}

	private ArrayList<PlayerBasicVO> getInstanceSHPer() {
		if (mostImpPlayerSHPer != null) {
			return mostImpPlayerSHPer;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_SHOTING);
	}

	private ArrayList<PlayerBasicVO> getInstanceFREPer() {
		if (mostImpPlayerFREPer != null) {
			return mostImpPlayerFREPer;
		}
		return getBasicVOs(TypeOfSort4MostImprovedPlayer.AVERAGE_FREETHROW);
	}

	private ArrayList<PlayerBasicVO> getBasicVOs(
			TypeOfSort4MostImprovedPlayer type) {
		ArrayList<PlayerImpMostVO> tempList = getImportRate(type);
		ArrayList<PlayerBasicVO> result = new ArrayList<PlayerBasicVO>();
		int num = 5;
		if (tempList.size() < num) {
			num = tempList.size();
		}
		for (int i = 0; i < num; i++) {
			System.out.println("hotnum:"+i);
			PlayerBasicVO temp = playerBLService.getPlayerDetail(tempList
					.get(i).getPlayerID());
			if (temp != null) {
				result.add(temp);
			}
		}
		return result;
	}

	private ArrayList<PlayerImpMostVO> getImportRate(
			TypeOfSort4MostImprovedPlayer typeOfSort) {
		ArrayList<PlayerTotalVO> tempTotalList = playerBLService.getAllTotal(
				TypeOfMatch.REGULAR, "2014-15");// 得到当前赛季数据
		ArrayList<PlayerImpMostVO> result = new ArrayList<PlayerImpMostVO>();

		System.out.println("rateSize:"+tempTotalList.size());
		for (int i = 0; i < 2; i++) {
			PlayerTotalVO tempScores = tempTotalList.get(i);
			ArrayList<PlayerInMatchVO> tempList = matchBLService
					.getMatches4Player(tempScores.getPlayerID());// 得到一个球员最近5场比赛
			int num_current = 0;
			int num_total = 0;
			int num_match = 0;
			int num_match_total = 0;
			if (tempList != null) {
				num_match = tempList.size();
				num_match_total = tempScores.getGP();
				switch (typeOfSort) {
				case AVERAGE_PSCORING:
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getPTS();// 计算近几场的得分
					}
					num_total = tempScores.getPTS() - num_current;
					break;
				case AVERAGE_REBOUND:
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getREB();// 计算近几场的篮板
					}
					num_total = tempScores.getREB() - num_current;
					break;
				case AVERAGE_ASSIST:
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getAST();// 计算近几场的助攻
					}
					num_total = tempScores.getAST() - num_current;
					break;
				case AVERAGE_BLOCK:
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getBLK();// 计算近几场的盖帽
					}
					num_total = tempScores.getBLK() - num_current;
					break;
				case AVERAGE_STLS_STEALS:
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getSTL();// 计算近几场的抢断
					}
					num_total = tempScores.getSTL() - num_current;
					break;
				case AVERAGE_THREEPOINT:
					num_match = 0;
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getTPM();// 计算近几场的三分球
						num_match = num_match + tempList.get(j).getTPA();// 计算近几场的三分出手数
					}
					num_total = tempScores.getTPM() - num_current;
					num_match_total = tempScores.getTPA();
					break;
				case AVERAGE_SHOTING:
					num_match = 0;
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getFGM();// 计算近几场的投篮
						num_match = num_match + tempList.get(j).getFGA();// 计算近几场的投篮出手数
					}
					num_total = tempScores.getFGM() - num_current;
					num_match_total = tempScores.getFGA();
					break;
				case AVERAGE_FREETHROW:
					num_match = 0;
					for (int j = 0; j < tempList.size(); j++) {
						num_current = num_current + tempList.get(j).getFTM();// 计算近几场的罚球命中数
						num_match = num_match + tempList.get(j).getFTA();// 计算近几场的罚球出手数
					}
					num_total = tempScores.getFTM() - num_current;
					num_match_total = tempScores.getFTA();
					break;
				default:
					break;
				}
			}
			num_match_total = num_match_total - num_match;
			if (num_match == 0) {
				num_match = 1;
			}
			if (num_match_total == 0) {
				num_match_total = 1;
			}
			if (num_total == 0) {
				num_total = 1;
			}
			double num_first = (double) num_current / (double) num_match;
			double num_second = (double) num_total / (double) num_match_total;
			double rate = (num_first - num_second) / num_second;

			PlayerImpMostVO playerImpMostVO = new PlayerImpMostVO(
					tempScores.getPlayerName(), tempScores.getPlayerID(), rate);
			result.add(playerImpMostVO);
		}

		PlayerImpSortTool tool = new PlayerImpSortTool();
		Collections.sort(result, tool);// 将所得列表排序
		return result;
	}
}
