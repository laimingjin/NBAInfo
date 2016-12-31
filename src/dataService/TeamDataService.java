package dataService;

import java.sql.SQLException;
import java.util.ArrayList;

import po.MembersPO;
import po.TeamBasicPO;
import po.TeamHighPO;
import po.TeamTotalPO;
import enumerate.TypeOfMatch;

/**
 * @author:小春
 * @date:2015年5月29日上午10:52:17
 * @version
 */

public interface TeamDataService {
	/**
	 * 用于点击表格中某一行后得到该球队的基本信息用于新界面显示(basic中信息,包含有图片信息)
	 * 
	 * @param teamName点击的那个表格中一行的球队名字
	 *            （缩写）
	 * 
	 * @return
	 */
	public TeamBasicPO getTeamDetail(String teamName)throws SQLException;
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月5日下午4:46:27
	 *@param partition 球队区域
	 *@return
	 *ArrayList<TeamBasicPO>
	 */
	public ArrayList<TeamBasicPO> getTeamByPartition(String partition)throws SQLException;

	/**
	 * @param abbreviation
	 *            点击的那个表格中一行的球队名字（缩写 ）
	 * @return 球队基本信息
	 */
	public TeamBasicPO get_from_abbreviation(String abbreviation)throws SQLException;

	/**
	 * @param teamName
	 *           缩写
	 * @return
	 */
	public TeamTotalPO getTeamDetail_whole(String teamName,
			TypeOfMatch matchType, String matchSeason)throws SQLException;

	public TeamHighPO changeToHigh(TypeOfMatch matchType, String matchSeason,
			String teamName)throws SQLException;

//	public TeamTotalPO changeToLow(TypeOfMatch matchType, String matchSeason,
//			String teamName);
/**
 * 
 *@author:小春
 *@data:2015年6月2日下午9:10:06
 *@param name 球队名称
 *@param typeOfMatch 比赛类型
 *@return 得到一个球队某一类型的所有比赛
 *ArrayList<TeamHighPO>
 */
	public ArrayList<TeamHighPO> getTeamAllHigh(String name,TypeOfMatch typeOfMatch)throws SQLException;
	public ArrayList<TeamTotalPO> getTeamAllTotal(String name,TypeOfMatch typeOfMatch)throws SQLException;
	
	
	/**
	 * 得到所有
	 *
	 * @author:小春
	 * @data:2015年5月29日上午11:42:37
	 * @return ArrayList<TeamHighPO>
	 */
	public ArrayList<TeamHighPO> getAllHigh(TypeOfMatch typeOfShow,String matchSeason)throws SQLException;

	public ArrayList<TeamTotalPO> getAllTotal(TypeOfMatch typeOfShow,String matchSeason)throws SQLException;
	
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月8日上午1:34:59
	 *@param teamID 球队ID
	 *@param matchSeason 赛季
	 *@return
	 *MembersPO 球队某赛季成员
	 * @throws SQLException 
	 */
	public MembersPO getMemberOfTeam(String teamID,String matchSeason) throws SQLException;
	//在DataControl里要用
		public  void addTeam_basic(TeamBasicPO po) throws SQLException ;
		public void addTeam_low(TeamTotalPO po)throws SQLException ;
		public void addTeam_highData(TeamHighPO po)throws SQLException;
		public void add_memberOfTeam(MembersPO po)throws SQLException;
}
