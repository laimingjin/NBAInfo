package blService;

import java.util.ArrayList;

import vo.PlayerBasicVO;
import vo.TeamBasicVO;
import enumerate.TypeOfSort4HotPointPlayer;
import enumerate.TypeOfSort4MostImprovedPlayer;
import enumerate.TypeOfSort4Players;
import enumerate.TypeOfSort4Teams;

public interface HotBLService {

	/**
	 * 取得当天的热点球员，5个人(因为考虑到数据可能没有当日的，所以修改成日期可以确定)
	 * @param typeOfSort 排序方式 
	 * @param date 日期
	 * @return 当天热点球员,比赛数据为列表,仅包含当天
	 */
	public ArrayList<PlayerBasicVO> getHotPointPlayerToday(TypeOfSort4HotPointPlayer typeOfSort);
	
	/**
	 * 取得赛季的热点球员，5个人
	 * @param typeOfSort 排序方式 
	 * @return 赛季热点球员,比赛数据为列表,仅包含当前赛季
	 */
	public ArrayList<PlayerBasicVO> getHotPointPlayerSeason(
			TypeOfSort4HotPointPlayer item);
	
	/**
	 * 取得赛季的热点球队，5只
	 * @param typeOfSort 排序方式 
	 * @return 赛季热点球队，5只,,比赛数据为列表,仅包含当前赛季
	 */
	public ArrayList<TeamBasicVO> getHotPointTeamSeason(TypeOfSort4Teams item);
	
	/**
	 * 取得进步最快球员，5个
	 * @param typeOfSort 排序方式 
	 * @return 进步最快球员，5个,比赛数据为列表,仅包含最近5场
	 */
	public ArrayList<PlayerBasicVO> getMostImprovedPlayer(TypeOfSort4MostImprovedPlayer typeOfSort);

}
