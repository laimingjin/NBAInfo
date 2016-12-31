package dataService;

import java.sql.SQLException;
import java.util.ArrayList;

import po.PlayerBasicPO;
import po.PlayerHighPO;
import po.PlayerInMatchPO;
import po.PlayerTotalPO;
import enumerate.TypeOfMatch;

/**
 * @author:小春
 * @date:2015年5月29日上午12:21:57
 * @version
 */

public interface PlayerDataService {
	/**
	 * @param word_Search搜索关键词
	 * @param beforeOrAfter常规赛还是季后赛
	 *            ，常规赛是0，季后赛是1，全明星是2
	 * @param yearNumber年份选择
	 * @return
	 * @throws SQLException 
	 */

	
	
	// 球衣号码搜索
	public ArrayList<PlayerTotalPO> searchPlayersByNum(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException;

	// 球员姓名搜索
	public ArrayList<PlayerTotalPO> searchPlayersByName(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException;

	// 所在球队[缩写]
	public ArrayList<PlayerTotalPO> searchPlayersByTeam(String word_Search,
			TypeOfMatch matchType, String yearNumber)throws SQLException;

	// 球员位置
	public ArrayList<PlayerTotalPO> searchPlayersByPosition(String word_Search,
			TypeOfMatch matchType, String yearNumber)throws SQLException;



	/**
	 * 用于点击表格中某一行后得到该球员的基本信息用于新界面显示(basic中信息,包含有图片信息)
	 * 
	 * @param playerName
	 *            球员全名
	 * @return 无结果时为null
	 */
	public PlayerBasicPO getPlayerDetail(String playerID)throws SQLException;

	/*
	 * 得到具体球员的信息 无结果时为null
	 */

	public PlayerHighPO getPlayerHigh(String playerID,TypeOfMatch matchType, String matchSeason)throws SQLException;

	public PlayerTotalPO getPlayerTotal(String playerID,TypeOfMatch matchType, String matchSeason)throws SQLException;


	/**
	 * 
	 *@author:小春
	 *@data:2015年6月2日下午8:08:15
	 *@param name 球员姓名
	 *@param typeOfMatch 比赛类型
	 *@return  同一球员某一种比赛的全部基本记录
	 *ArrayList<PlayerTotalPO>
	 */
	public ArrayList<PlayerTotalPO> getplayerAllTotal(String playerID,TypeOfMatch typeOfMatch)throws SQLException;
	/**
	 * 
//	 *@author:小春
//	 *@data:2015年6月5日下午5:04:06
//	 *@param typeOfMatch 比赛类型 
//	 *@param yearNumber 比赛赛季
//	 *@return
//	 *ArrayList<PlayerTotalPO> 某比赛赛季某种类型比赛的全部球员数据
//	 */
//	public ArrayList<PlayerTotalPO> getplayerTotal(TypeOfMatch typeOfMatch, String yearNumber);
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月2日下午8:09:36
	 *@param typeOfMatch 比赛类型
	 *@return  同一球员某一种比赛的全部记录
	 *@return  同一球员某一种比赛的全部高阶记录
	 *ArrayList<PlayerHighPO>
	 */
	public ArrayList<PlayerHighPO> getplayerAllHigh(String playerID,TypeOfMatch typeOfMatch)throws SQLException;

	/**
	 * 得到所有球员
	 * 需要考虑赛季和比赛类型
	 */
	public ArrayList<PlayerBasicPO> getPlayerOfTeam(String teamID)throws SQLException;

	public ArrayList<PlayerHighPO> getAllHigh(TypeOfMatch typeOfShow,String yearNumber)throws SQLException;

	public ArrayList<PlayerTotalPO> getAllTotal(TypeOfMatch typeOfShow,String yearNumber)throws SQLException;
	
	//在DataControl里要用
	public  void addPlayer_basic(PlayerBasicPO po) throws SQLException;
	public void addPlayer_low(PlayerTotalPO po) throws SQLException;
	public void addPlayer_highData(PlayerHighPO po) throws SQLException;
}
