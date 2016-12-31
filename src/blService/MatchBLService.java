package blService;

import java.util.ArrayList;

import vo.MatchBasicVO;
import vo.PlayerInMatchVO;
import vo.TeamInMatchVO;


public interface MatchBLService {
	//!!!!!!!!!!!!!!!!!!!!!!!!!!所有的球队在比赛这里都用缩写 
	/**
	 * 通过选择时间或者球队或者两个都选择得到比赛信息
	 * 如果用户只选了球队没选时间，时间会传“”（空）
	 * 如果用户只选择了时间没选球队，球队传“”（空）
	 * @param matchTime
	 * @param teamName
	 * @return
	 */
	public ArrayList<MatchBasicVO> searchMatchByTime(String matchTime,String teamName);
	
	/**
	 * @param playerName 球员名字
	 * @param numOfMatches 表示需要返回最近的numOfMatches场比赛
	 * @return  取得指定球员最近numOfMatches场比赛的数据列表 默认为5个
	 * */
	
	public ArrayList<PlayerInMatchVO> getMatches4Player(String playerID);
	
	/**
	 * @param team 查找所用的其中一只比赛队伍(缩写)
	 * @param numOfMatches 表示需要返回最近的numOfMatches场比赛
	 * @return 取得指定球员最近numOfMatches场比赛的数据列表
	 * 
	 */
	public  ArrayList<TeamInMatchVO> getMatches4Team(String team);
	/**
	 * 用于点击某一行显示具体详情信息
	 * @param matchTime    "20150601"
	 * @param teamStrings "客队-主队"
	 * @return
	 */
	public ArrayList<PlayerInMatchVO> getPlayerInMatchVOs4Match(String matchTime,String teamAway,String teamHome,String theTeam);
	/**
	 * 用于点击某一行显示具体详情信息
	 * @param matchTime    "20150601"
	 * @param teamStrings "客队-主队"
	 * @return
	 */
	public ArrayList<TeamInMatchVO> getTeamInMatchVOs4Match(String matchTime,String teamAway,String teamHome,String theTeam);

	/**
	 * 
	 *@author:小春
	 *@data:2015年6月5日下午10:08:54
	 *@param time time 比赛日期 "2015-06-01"
	 *@return
	 *ArrayList<PlayerInMatchVO>
	 */
	public ArrayList<PlayerInMatchVO> getPlayerByDate(String time);
	
	public ArrayList<PlayerInMatchVO> getPlayerBySeason(String playerID,String matchSeason);
	public ArrayList<TeamInMatchVO> getTeamBySeason(String teamName,String matchSeason);
	/**
	 * 通过ID得到baisc
	 */
	public MatchBasicVO getMatchById(String matchID);
}
