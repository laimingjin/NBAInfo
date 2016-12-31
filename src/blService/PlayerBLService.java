package blService;

import java.util.ArrayList;

import vo.ContrastBoardVO;
import vo.DataKingVO;
import vo.PlayerBasicVO;
import vo.PlayerHighVO;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import enumerate.AreaOfPlayer;
import enumerate.PositionOfPlayer;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4PlayerHigh;
import enumerate.TypeOfSort4Players;


public interface PlayerBLService {
	/**PlayerTotalVO是球员多个赛季的总数据
	 * 搜索框随意输入不再规定用户必须选择哪个模块，而由逻辑层自动分析先判断是否是数字，
	 * 是的话就代表是按照球衣号码，不是的话就是 姓名 位置  所在球队，返回的时候，就按照
	 * 姓名所在球队位置这样的顺序拼接成一整个PlayerTotalVO
	 * @param word_Search搜索框的输入
	 * @param beforeOrAfter常规赛还是季后赛，常规赛是0，季后赛是1,全明星是2
	 * @param yearNumber年份选择
	 * @return
	 */
   
	public ArrayList<PlayerTotalVO> searchPlayers(String word_Search,TypeOfMatch typeOfShow,String yearNumber);
	 /**
     * 这样方法同样可以直接用来我界面改变年份或者改变常规还是季候，只要把具体球员名字和当前所选重新调用这个函数就好
     */
	public ArrayList<PlayerTotalVO> changeseasonsTypesTotal(ArrayList<PlayerTotalVO> playerTotalVOs,TypeOfMatch typeOfShow,String yearNumber);
	 /**
     * 这样方法同样可以直接用来我界面改变年份或者改变常规还是季候，只要把具体球员名字和当前所选重新调用这个函数就好
     */
	public ArrayList<PlayerHighVO> changeseasonsTypesHigh(ArrayList<PlayerHighVO> playerTotalVOs,TypeOfMatch typeOfShow,String yearNumber);
	
	/**
	 * 从低转到高
	 * @param playerTotalVOs
	 * @return
	 */
	public ArrayList<PlayerHighVO>changeToHigh( ArrayList<PlayerTotalVO> playerTotalVOs);
	/**
	 * 从高转到底
	 * @param playerHighVOs
	 * @return
	 */
	public ArrayList<PlayerTotalVO>changeToLow(ArrayList<PlayerHighVO> playerHighVOs);
	/**
	 * 用于点击表格中某一行后得到该球员的基本信息用于新界面显示(basic中信息,包含有图片信息)
	 * @param playerName  球员全名
	 * @return
	 */
	public PlayerBasicVO getPlayerDetail(String playerID);
     
	/**
	 * 用于点击了表格中表头某一项然后对全部排序
	 * 这个需要分是高阶还是低阶得了
	 * 所以方法要重写还是重啥,反正去了和风食堂,觉得和风就一般般(天惹刚才脑子不正常穿越写了啥啊。。。
	 * @param item某一项项目名称
	 * @param typeOfSort排序类型
	 *            ，【一共五种，详见TypeOfSort注释】·
	 * @return
	 */
	public ArrayList<PlayerTotalVO> sortBySelectedTotal(ArrayList<PlayerTotalVO> totalList,TypeOfSort4Players item,
			TypeOfSort typeOfSort);
	public ArrayList<PlayerHighVO> sortBySelectedHigh(ArrayList<PlayerHighVO> highList,TypeOfSort4PlayerHigh item,
			TypeOfSort typeOfSort);

   /**
    * 对所有查询到的进行筛选
    * @param playerVO_scores前面所得倒的ArrayList<PlayerTotalVO>
    * @param positionOfPlayer 界面上用户所选择的球员位置
    * @param areaOfPlayer  界面上用户所选择的球员联盟
    * @return  返回筛选后的ArrayList<PlayerTotalVO>
    * 然而去掉了排序依据
    * 个人觉得有表头排序了啊
    * 而两双单独设置为一个按钮可以看所有符合两双的人，都不影响
    */
	public ArrayList<PlayerTotalVO> shiftTotal(
			ArrayList<PlayerTotalVO> playerTotalVOs,
			PositionOfPlayer positionOfPlayer, AreaOfPlayer areaOfPlayer);
	public ArrayList<PlayerHighVO> shiftHigh(
			ArrayList<PlayerHighVO> playerHighVOs,
			PositionOfPlayer positionOfPlayer, AreaOfPlayer areaOfPlayer);
	/**
	 * 
	 * @param playerName  比分板针对的那个球员 firstname+“ ” +lastName
	 * @return 取得球员界面球员详情中的子模块——对比板的VO
	 * @return 年代久远的球员因为缺乏球队基本信息，无法计算同联盟的数据，故返回null
	 */
	public ContrastBoardVO getContrastData(String playerID,TypeOfMatch typeOfShow,String yearNumber);
	/**
	 * 
	 *@author:小春
	 *@data:2015年6月5日下午5:24:41
	 *@param teamName
	 *@param typeOfShow
	 *@param yearNumber
	 *@return
	 *DataKingVO 
	 */
	public DataKingVO getDataKing(String teamName,TypeOfMatch typeOfShow,String yearNumber);
	
	/**
	 * 得到全部
	 */
	public ArrayList<PlayerHighVO>  getAllHigh(TypeOfMatch typeOfShow,String yearNumber);
	public ArrayList<PlayerTotalVO> getAllTotal(TypeOfMatch typeOfShow,String yearNumber);
	
	public ArrayList<PlayerTotalVO> playerInit(TypeOfMatch typeOfMatch,String yearNumber);
	/**
	 * 服务于球员详情界面，辛苦辛苦！
	 * @param name
	 * @return
	 */
	public ArrayList<PlayerTotalVO> getRegularByName(String playerID);
	public ArrayList<PlayerTotalVO> getPlayoffByName(String playerID);
	public ArrayList<PlayerTotalVO> getAllStarByName(String playerID);
	public ArrayList<PlayerHighVO> getRegularByNameHigh(String playerID);
	public ArrayList<PlayerHighVO> getPlayoffByNameHigh(String playerID);
//	public ArrayList<PlayerHighVO> getAllStarByNameHigh(String playerID);
	
	public ArrayList<PlayerInMatchVO> getRecentMatchesforPlayer(String playerID);

}
