package blService;

import java.util.ArrayList;

import vo.MembersVO;
import vo.PlayerHighVO;
import vo.PlayerTotalVO;
import vo.TeamBasicVO;
import vo.TeamHighVO;
import vo.TeamInMatchVO;
import vo.TeamTotalVO;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4Teams;
import enumerate.TypeOfSort4TeamsHigh;

public interface TeamBLService {
	/**
	 * 用于点击表格中某一行后得到该球队的基本信息用于新界面显示(basic中信息,包含有图片信息)
	 * 
	 * @param teamName点击的那个表格中一行的球队名字（全名）
	 * 
	 * @return
	 */
	public TeamBasicVO getTeamDetail(String teamName);
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月5日下午4:47:47
	 *@param partition
	 *@return
	 *ArrayList<TeamBasicPO>
	 */
	public ArrayList<TeamBasicVO> getTeamByPartition(String partition);
	/**
	 * @param abbreviation 点击的那个表格中一行的球队名字（缩写 ）
	 * @return 球队基本信息
	 */
	public TeamBasicVO get_from_abbreviation(String abbreviation);
	/**
	 * @param abbreviation 缩写
	 * @return
	 */
	public TeamTotalVO getTeamDetail_whole(String abbreviation,int beforeOrAfter,String yearNumber);
	/**
	 * 得到全部
	 */
	public ArrayList<TeamHighVO>  getAllHigh(TypeOfMatch typeOfShow,String yearNumber);
	public ArrayList<TeamTotalVO> getAllTotal(TypeOfMatch typeOfShow,String yearNumber);
	
	/**
	 * 用于点击了表格中表头某一项然后对全部排序
	 * 
	 * @param item某一项项目名称
	 * @param typeOfSort排序类型，详见TypeOfSort
	 * @return
	 */
	public ArrayList<TeamTotalVO> sortBySelectedItem(TypeOfMatch typeOfShow,String matchSeason,TypeOfSort4Teams item,TypeOfSort typeOfSort);
	
	public ArrayList<TeamHighVO> sortBySelectedItemHigh(TypeOfMatch typeOfShow,String matchSeason,TypeOfSort4TeamsHigh item,TypeOfSort typeOfSort);
	
	public ArrayList<TeamHighVO>changeToHigh( ArrayList<TeamTotalVO> teamTotalVOs);
	
	public ArrayList<TeamTotalVO>changeToLow(ArrayList<TeamHighVO>teamHighVOs);
	
	/** * 这样方法同样可以直接用来我界面改变年份或者改变常规还是季候，只要把具体球员名字和当前所选重新调用这个函数就好
     */
	public ArrayList<TeamTotalVO> changeseasonsTypesTotal(ArrayList<TeamTotalVO> teamTotalVOs,TypeOfMatch typeOfShow,String yearNumber);
	 /**
     * 这样方法同样可以直接用来我界面改变年份或者改变常规还是季候，只要把具体球员名字和当前所选重新调用这个函数就好
     */
	public ArrayList<TeamHighVO> changeseasonsTypesHigh(ArrayList<TeamHighVO> teamTotalVOs,TypeOfMatch typeOfShow,String yearNumber);
	/**
	 * 服务于球队详情界面，辛苦辛苦！
	 * @param name
	 * @return
	 */
	public ArrayList<TeamTotalVO> getRegularByTeamName(String name);
	public ArrayList<TeamTotalVO> getPlayoffByTeamName(String name);

	public ArrayList<TeamHighVO> getRegularByTeamNameHigh(String name);
	public ArrayList<TeamHighVO> getPlayoffByTeamNameHigh(String name);
	
	public ArrayList<TeamInMatchVO> getRecentMatchesforTeam(String name);
	
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月8日上午1:39:43
	 *@param teamID
	 *@param matchSeason
	 *@return
	 *MembersVO 球队某赛季成员，无时返回空
	 */
	public MembersVO getMemberOfTeam(String teamID,String matchSeason);
//	public ArrayList<MembersVO> getAllMemOfTeam(String teamID);
}
