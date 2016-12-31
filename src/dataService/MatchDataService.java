package dataService;

import java.sql.SQLException;
import java.util.ArrayList;

import po.MatchBasicPO;
import po.PlayerInMatchPO;
import po.TeamInMatchPO;
import vo.TeamInMatchVO;

/**
 * @author:小春
 * @date:2015年6月2日下午9:32:41
 * @version
 */

public interface MatchDataService {
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月12日上午10:46:29
	 *@param playerID 编号
	 *@return
	 *@throws SQLException
	 *ArrayList<PlayerInMatchPO> 最近5场比赛，实际不足5场的话也没事
	 */
	public ArrayList<PlayerInMatchPO> getPlayer5Matches(String playerID)throws SQLException;
	public ArrayList<TeamInMatchPO> getTeam5Matcher(String teamName) throws SQLException;
 	/**
	 * 
	 * @author:小春
	 * @data:2015年6月2日下午9:35:12
	 * @return ArrayList<PlayerInMatchPO> 一个球员的所有比赛
	 */
	public ArrayList<PlayerInMatchPO> getAllPlayer(String playerID)throws SQLException;

	/**
	 * 
	 * @author:小春
	 * @data:2015年6月2日下午9:37:46
	 * @param team
	 *            球队名称，缩写
	 * @return ArrayList<TeamInMatchPO> 一个球队的所有比赛
	 */
	public ArrayList<TeamInMatchPO> getAllTeam(String team)throws SQLException;
	/**
	 * 用于点击某一行显示具体详情信息
	 * @param matchTime    "2015-06-01"
	 * @param teamStrings "客队-主队"
	 * @return
	 */
	public ArrayList<PlayerInMatchPO> getPlayerInMatchVOs4Match(
			String matchTime, String teamAway,String teamHome)throws SQLException;
	
	/**
	 * 用于点击某一行显示具体详情信息
	 * @param matchTime    "2015-06-01"
	 * @param teamStrings "客队-主队" 没有结果请返回空的Arraylist,不要null
	 * @return
	 */
	public ArrayList<TeamInMatchPO> getTeamInMatchVOs4Match(String matchTime, String teamAway,String teamHome)throws SQLException;

	/**
	 * 
	 *@author:小春
	 *@data:2015年6月2日下午9:47:20
	 *@param teamName 球队名称，缩写
	 *@return
	 *ArrayList<MatchBasicPO> 球队相关的比赛基本信息 没有结果请返回空的Arraylist,不要null
	 */
    public ArrayList<MatchBasicPO> getBasicByTeam(String teamName)throws SQLException;
    /**
     * 
     *@author:小春
     *@data:2015年6月2日下午9:48:15
     *@param time 2015-12-06
     *@return
     *ArrayList<MatchBasicPO> 某天的比赛 没有结果请返回空的Arraylist,不要null
     */
    public ArrayList<MatchBasicPO> getBasicByTime(String time)throws SQLException;
    
    public MatchBasicPO getMatchById(String matchID) throws SQLException;
    
    /**
     * 
     *@author:小春
     *@data:2015年6月5日下午10:07:38
     *@param time 比赛日期 "2015-06-01"
     *@return
     *ArrayList<PlayerInMatchPO>
     */
    public ArrayList<PlayerInMatchPO> getPlayerByDate(String time)throws SQLException;
public void addMatch_basic(MatchBasicPO po)throws SQLException;
public void add_playerInMatch(PlayerInMatchPO po)throws SQLException;
public void add_teamInMatch(TeamInMatchPO po)throws SQLException;
}
