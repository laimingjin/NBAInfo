package dataServiceImp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.Last5Games_player;
import po.Last5Games_team;
import po.MatchBasicPO;
import po.PlayerInMatchPO;
import po.Player_for_update;
import po.TeamInMatchPO;
import readFiles.DealTheTwo;
import readFiles.Match_read;
import dataService.MatchDataService;
import enumerate.TypeOfMatch;

public class MatchDataServiceImp implements MatchDataService {
	private final PreparedStatement add_match_basic;
	private final PreparedStatement add_playerInMatch;
	private final PreparedStatement add_teamInMatch;
	private final PreparedStatement add_last5Games_player;
	private final PreparedStatement add_last5Games_team;
	private final Statement find_player_by_id;
	private final Statement find_matchBasic_by_id;
	private final Statement find_team_by_name;
	private final Statement find_match_basic;
	private final Statement find_team_by_id;
	private final Statement find_last5_player;
	private final Statement find_last5_team;
	
//	static{
//		try {
//			Database.init();
//		} catch (DatabaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	 public MatchDataServiceImp() throws DatabaseException {
		// TODO Auto-generated constructor stub
		Database database=Database.getInstance();
		this.add_match_basic=database.prepared_statement("insert into matchbasicinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		this.add_playerInMatch=database.prepared_statement("insert into playerinmatch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		this.add_teamInMatch=database.prepared_statement("insert into teaminmatch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	     this.add_last5Games_player=database.prepared_statement("insert into last5gamesofplayer values(?,?,?,?,?,?,?)");
	     this.add_last5Games_team=database.prepared_statement("insert into last5gamesofteam values(?,?,?,?,?,?,?)");
		this.find_player_by_id=database.statement();
	 this.find_matchBasic_by_id=database.statement();
	 this.find_team_by_id=database.statement();
	 this.find_team_by_name=database.statement();
	 this.find_match_basic=database.statement();
	 this.find_last5_player=database.statement();
	 this.find_last5_team=database.statement();
	 }
	 private MatchBasicPO find_matchBasic_by_id(String gameID) throws SQLException{
		 String sql="select *from matchbasicinfo where gameID='"+gameID+"'";
		 ResultSet rs=this.find_matchBasic_by_id.executeQuery(sql);
		 while(rs.next()){
			 String matchType=rs.getString("matchType");
			 
			 TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
				if(matchType.equals("playoff")){
					typeOfMatch=TypeOfMatch.PLAYOFF;
				}else if(matchType.equals("allstar")){
					typeOfMatch=TypeOfMatch.ALLSTAR;
				}
				String matchSeason=rs.getString("matchSeason");//赛季
				String matchDate=rs.getString("matchDate");//年月日
				String awayTeamID=rs.getString("awayTeamID");
				String homeTeamID=rs.getString("homeTeamID");
				String awayTeam=rs.getString("awayTeam");//客场球队
				String homeTeam=rs.getString("homeTeam");//主场球队
				int totalPoints_homeTeam=rs.getInt("totalPoints_homeTeam");//总比分（客场在前主场在后）
				int firstPoints_homeTeam=rs.getInt("firstPoints_homeTeam");//第一比分（客场在前主场在后）
				int secondPoints_homeTeam=rs.getInt("secondPoints_homeTeam");//第二比分（客场在前主场在后）
				int thirdPoints_homeTeam=rs.getInt("thirdPoints_homeTeam");//第三比分（客场在前主场在后）
				int forthPoints_homeTeam=rs.getInt("forthPoints_homeTeam");//第四比分（客场在前主场在后）
				int totalPoints_awayTeam=rs.getInt("totalPoints_awayTeam");//总比分（客场在前主场在后）
				int firstPoints_awayTeam=rs.getInt("firstPoints_awayTeam");//第一比分（客场在前主场在后）
				int secondPoints_awayTeam=rs.getInt("secondPoints_awayTeam");//第二比分（客场在前主场在后）
				int thirdPoints_awayTeam=rs.getInt("thirdPoints_awayTeam");//第三比分（客场在前主场在后）
				int forthPoints_awayTeam=rs.getInt("forthPoints_awayTeam");//第四比分（客场在前主场在后）
				MatchBasicPO po=new MatchBasicPO(gameID, typeOfMatch, matchSeason, matchDate, awayTeamID, awayTeam, homeTeamID, homeTeam, totalPoints_awayTeam, totalPoints_homeTeam, firstPoints_awayTeam, firstPoints_homeTeam, secondPoints_awayTeam, secondPoints_homeTeam, thirdPoints_awayTeam, thirdPoints_homeTeam, forthPoints_awayTeam, forthPoints_homeTeam);
				return po;
		 }
		 return null;
	 }
//	 private ArrayList<String>getGameIDsFromRS(ResultSet rs) throws SQLException{
//		 ArrayList<String> list=new  ArrayList<String>();
//		 while(rs.next()){
//			 String gameID=rs.getString("gameID");
//			 list.add(gameID);
//		 }
//		 return list;
//	 }
	 private ArrayList<TeamInMatchPO>get_teamInMatch_from_rs(ResultSet rs)throws SQLException{
		 ArrayList<TeamInMatchPO>list=new  ArrayList<TeamInMatchPO>();
		 while(rs.next()){
			 String gameID=rs.getString("gameID");
				
				String teamID=rs.getString("teamID");
				String teamName=rs.getString("teamName");// 該球隊
				int numOfPlayer=rs.getInt("numOfPlayer");// 出場人數
				int FGA=rs.getInt("FGA");// 投篮出手数
				int FGM=rs.getInt("FGM");// 投篮命中数
				double FGPer=rs.getDouble("FGPer");// 投篮命中率
				int TPA=rs.getInt("TPA");// 三分出手数//原缩写为3PA
				int TPM=rs.getInt("TPM");// 三分命中数//3PM
				double TPPer=rs.getDouble("TPPer");// 三分命中率//3P%
				int FTA=rs.getInt("FTA");// 罚球出手数
				int FTM=rs.getInt("FTM");// 罚球命中数
				double FTPer=rs.getDouble("FTPer");// 罚球命中率
				int REB=rs.getInt("REB");// 篮板数
				int OREB=rs.getInt("OREB");// 进攻篮板//前场篮板
				int DREB=rs.getInt("DREB");// 防守篮板//后场篮板
				int AST=rs.getInt("AST");// 助攻数
				int STL=rs.getInt("STL");// 抢断数
				int BLK=rs.getInt("BLK");// 盖帽数
				int TOV=rs.getInt("TOV");// 失误数
				int PF=rs.getInt("PF");// 犯规数
				int PTS=rs.getInt("PTS");// 总得分
				double PLUS_MINUS=rs.getDouble("PLUS_MINUS");// 真實命中率
				int isWin=rs.getInt("isWin");
				
				MatchBasicPO matchBasicPO=find_matchBasic_by_id(gameID);
				String matchDate=matchBasicPO.getMatchDate();// 年月日
				TypeOfMatch typeOfMatch=matchBasicPO.getMatchType();//比赛类型
				String awayTeamID=matchBasicPO.getAwayTeamID();
				String awayTeam=matchBasicPO.getAwayTeam();// 客场球队
				String homeTeamID=matchBasicPO.getHomeTeamID();
				String homeTeam=matchBasicPO.getHomeTeam();// 主场球队
				String matchSeason=Match_read.changeMatchDateToSeason(matchDate);
				
				TeamInMatchPO po=new TeamInMatchPO(gameID, matchDate, typeOfMatch, matchSeason, awayTeamID, awayTeam, homeTeamID, homeTeam, teamID, teamName, numOfPlayer,isWin, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS);
				list.add(po);
		 }
		 return list;
	 }
	 private ArrayList<PlayerInMatchPO>get_playerInMatch_from_rs(ResultSet rs)throws SQLException{
		 ArrayList<PlayerInMatchPO>list=new ArrayList<PlayerInMatchPO>();
		 while(rs.next()){
			 String gameID=rs.getString("gameID");
				String playerID=rs.getString("playerID");
				String playerName=rs.getString("playerName");// 球员名称
				String teamOfPlayer=rs.getString("teamOfPlayer");// 球员球队
				String position=rs.getString("position");
				int isGS=rs.getInt("isGS");// 是否首发
				int MIN=rs.getInt("MIN");// 出场时间
				int FGA=rs.getInt("FGA");// 投篮出手数
				int FGM=rs.getInt("FGM");// 投篮命中数
				String FGPer=rs.getString("FGPer");// 投篮命中率
				int TPA=rs.getInt("TPA");// 三分出手数//原缩写为3PA
				int TPM=rs.getInt("TPM");// 三分命中数//3PM
				String TPPer=rs.getString("TPPer");// 三分命中率//3P%
				int FTA=rs.getInt("FTA");// 罚球出手数
				int FTM=rs.getInt("FTM");// 罚球命中数
				String FTPer=rs.getString("FTPer");// 罚球命中率
				int REB=rs.getInt("REB");// 篮板数
				int OREB=rs.getInt("OREB");// 进攻篮板//前场篮板
				int DREB=rs.getInt("DREB");// 防守篮板//后场篮板
				int AST=rs.getInt("AST");// 助攻数
				int STL=rs.getInt("STL");// 抢断数
				int BLK=rs.getInt("BLK");// 盖帽数
				int TOV=rs.getInt("TOV");// 失误数
				int PF=rs.getInt("PF");// 犯规数
				int PTS=rs.getInt("PTS");// 总得分
				double PLUS_MINUS=rs.getDouble("PLUS_MINUS");// 真實命中率
				
				MatchBasicPO matchBasicPO=find_matchBasic_by_id(gameID);
				String matchDate=matchBasicPO.getMatchDate();// 年月日
				TypeOfMatch typeOfMatch=matchBasicPO.getMatchType();//比赛类型
				String awayTeamID=matchBasicPO.getAwayTeamID();
				String awayTeam=matchBasicPO.getAwayTeam();// 客场球队
				String homeTeamID=matchBasicPO.getHomeTeamID();
				String homeTeam=matchBasicPO.getHomeTeam();// 主场球队
				PlayerInMatchPO po=new PlayerInMatchPO(gameID, matchDate, typeOfMatch, awayTeamID, awayTeam, homeTeamID, homeTeam, playerID, playerName, teamOfPlayer, position, isGS, MIN, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS);
				list.add(po);
		 }
		 return list;
	 }
	 
	public ArrayList<PlayerInMatchPO> getAllPlayer(String playerID) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from playerinmatch where playerID='"+playerID+"'";
       ResultSet rs=this.find_player_by_id.executeQuery(sql);
       ArrayList<PlayerInMatchPO>playerInMatchPOs=get_playerInMatch_from_rs(rs);
		return playerInMatchPOs;

		
	}
private ArrayList<PlayerInMatchPO>get_playerInMatch(ResultSet rs) throws SQLException{
	 ArrayList<PlayerInMatchPO>list=new ArrayList<PlayerInMatchPO>();
	 while(rs.next()){
		 String gameID=rs.getString("gameID");
			String playerID=rs.getString("playerID");
			String playerName=rs.getString("playerName");// 球员名称
			String teamOfPlayer=rs.getString("teamOfPlayer");// 球员球队
			String position=rs.getString("position");
			int isGS=rs.getInt("isGS");// 是否首发
			int MIN=rs.getInt("MIN");// 出场时间
			int FGA=rs.getInt("FGA");// 投篮出手数
			int FGM=rs.getInt("FGM");// 投篮命中数
			String FGPer=rs.getString("FGPer");// 投篮命中率
			int TPA=rs.getInt("TPA");// 三分出手数//原缩写为3PA
			int TPM=rs.getInt("TPM");// 三分命中数//3PM
			String TPPer=rs.getString("TPPer");// 三分命中率//3P%
			int FTA=rs.getInt("FTA");// 罚球出手数
			int FTM=rs.getInt("FTM");// 罚球命中数
			String FTPer=rs.getString("FTPer");// 罚球命中率
			int REB=rs.getInt("REB");// 篮板数
			int OREB=rs.getInt("OREB");// 进攻篮板//前场篮板
			int DREB=rs.getInt("DREB");// 防守篮板//后场篮板
			int AST=rs.getInt("AST");// 助攻数
			int STL=rs.getInt("STL");// 抢断数
			int BLK=rs.getInt("BLK");// 盖帽数
			int TOV=rs.getInt("TOV");// 失误数
			int PF=rs.getInt("PF");// 犯规数
			int PTS=rs.getInt("PTS");// 总得分
			double PLUS_MINUS=rs.getDouble("PLUS_MINUS");// 真實命中率
			
            String matchType=rs.getString("matchType");
			 TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
				if(matchType.equals("playoff")){
					typeOfMatch=TypeOfMatch.PLAYOFF;
				}else if(matchType.equals("allstar")){
					typeOfMatch=TypeOfMatch.ALLSTAR;
				}
			String matchDate=rs.getString("matchDate");//年月日
			String awayTeamID=rs.getString("awayTeamID");
			String homeTeamID=rs.getString("homeTeamID");
			String awayTeam=rs.getString("awayTeam");//客场球队
			String homeTeam=rs.getString("homeTeam");//主场球队
			PlayerInMatchPO po=new PlayerInMatchPO(gameID, matchDate, typeOfMatch, awayTeamID, awayTeam, homeTeamID, homeTeam, playerID, playerName, teamOfPlayer, position, isGS, MIN, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS);
			list.add(po);
	 }
	 return list;
}
	public ArrayList<TeamInMatchPO> getAllTeam(String team) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from teaminmatch where teamName='"+team+"'";
		ResultSet rs=this.find_team_by_name.executeQuery(sql);
		ArrayList<TeamInMatchPO>teamInMatchPOs=get_teamInMatch_from_rs(rs);
		return teamInMatchPOs;
	}

	
private ArrayList<MatchBasicPO>get_basicMatch_from_rs(ResultSet rs1)throws SQLException{
	ArrayList<MatchBasicPO>list=new ArrayList<MatchBasicPO>();
	 while(rs1.next()){
		 String gameID=rs1.getString("gameID");
		 String matchType=rs1.getString("matchType");
		 
		 TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			if(matchType.equals("playoff")){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(matchType.equals("allstar")){
				typeOfMatch=TypeOfMatch.ALLSTAR;
			}
			String matchSeason=rs1.getString("matchSeason");//赛季
			String matchDate=rs1.getString("matchDate");//年月日
			String awayTeamID=rs1.getString("awayTeamID");
			String homeTeamID=rs1.getString("homeTeamID");
			String awayTeam=rs1.getString("awayTeam");//客场球队
			String homeTeam=rs1.getString("homeTeam");//主场球队
			int totalPoints_homeTeam=rs1.getInt("totalPoints_homeTeam");//总比分（客场在前主场在后）
			int firstPoints_homeTeam=rs1.getInt("firstPoints_homeTeam");//第一比分（客场在前主场在后）
			int secondPoints_homeTeam=rs1.getInt("secondPoints_homeTeam");//第二比分（客场在前主场在后）
			int thirdPoints_homeTeam=rs1.getInt("thirdPoints_homeTeam");//第三比分（客场在前主场在后）
			int forthPoints_homeTeam=rs1.getInt("forthPoints_homeTeam");//第四比分（客场在前主场在后）
			int totalPoints_awayTeam=rs1.getInt("totalPoints_awayTeam");//总比分（客场在前主场在后）
			int firstPoints_awayTeam=rs1.getInt("firstPoints_awayTeam");//第一比分（客场在前主场在后）
			int secondPoints_awayTeam=rs1.getInt("secondPoints_awayTeam");//第二比分（客场在前主场在后）
			int thirdPoints_awayTeam=rs1.getInt("thirdPoints_awayTeam");//第三比分（客场在前主场在后）
			int forthPoints_awayTeam=rs1.getInt("forthPoints_awayTeam");//第四比分（客场在前主场在后）
			MatchBasicPO po=new MatchBasicPO(gameID, typeOfMatch, matchSeason, matchDate, awayTeamID, awayTeam, homeTeamID, homeTeam, totalPoints_awayTeam, totalPoints_homeTeam, firstPoints_awayTeam, firstPoints_homeTeam, secondPoints_awayTeam, secondPoints_homeTeam, thirdPoints_awayTeam, thirdPoints_homeTeam, forthPoints_awayTeam, forthPoints_homeTeam);
			list.add(po);
	 }
	return list;
}
	public ArrayList<MatchBasicPO> getBasicByTeam(String teamName) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<MatchBasicPO>list=new ArrayList<MatchBasicPO>();
		 String sql1="select *from matchbasicinfo where awayTeam='"+teamName+"'";
		 ResultSet rs1=this.find_matchBasic_by_id.executeQuery(sql1);
		list=get_basicMatch_from_rs(rs1);
		String sql2="select *from matchbasicinfo where homeTeam='"+teamName+"'";
		 ResultSet rs2=this.find_matchBasic_by_id.executeQuery(sql2);
		 ArrayList<MatchBasicPO>temp=get_basicMatch_from_rs(rs2);
		 if(temp.size()>0){
			 for(int i=0;i<temp.size();i++){
				 list.add(temp.get(i));
			 }
		 }
		return list;
	}

	public ArrayList<MatchBasicPO> getBasicByTime(String time) throws SQLException {
		// TODO Auto-generated method stub
//		ArrayList<MatchBasicPO>list=new ArrayList<MatchBasicPO>();
		String sql="select *from matchbasicinfo where matchDate='"+time+"'";
		ResultSet rs=this.find_matchBasic_by_id.executeQuery(sql);
		ArrayList<MatchBasicPO>matchBasicPOs=get_basicMatch_from_rs(rs);
		return matchBasicPOs;
	}

	public ArrayList<PlayerInMatchPO> getPlayerByDate(String time) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from matchbasicinfo inner join playerinmatch  where matchbasicinfo.matchDate= '"+time+"' and playerinmatch.gameID=matchbasicinfo.gameID";
		ResultSet rs=this.find_player_by_id.executeQuery(sql);
		ArrayList<PlayerInMatchPO>pos=get_playerInMatch(rs);
		return pos;
		
//		ArrayList<PlayerInMatchPO> list=new ArrayList<PlayerInMatchPO>();
//		String sql="select *from matchbasicinfo where matchDate='"+time+"'";
//		ResultSet rs=this.find_matchBasic_by_id.executeQuery(sql);
//		ArrayList<MatchBasicPO>matchBasicPOs=get_basicMatch_from_rs(rs);
//		if(matchBasicPOs.size()>0){
//			for(int i=0;i<matchBasicPOs.size();i++){
//				MatchBasicPO matchBasicPO=matchBasicPOs.get(i);
//				String gameID=matchBasicPO.getGameID();
//				String sql1="select *from playerinmatch where gameID='"+gameID+"'";
//				ResultSet rs1=this.find_player_by_id.executeQuery(sql1);
//				ArrayList<PlayerInMatchPO>playerInMatchPOs=get_playerInMatch_from_rs(rs1);
//				if(playerInMatchPOs.size()>0){
//					for(int j=0;j<playerInMatchPOs.size();j++){
//						list.add(playerInMatchPOs.get(j));
//					}
//				}
//			}
//		}
//		return list;
	}

	public void addMatch_basic(MatchBasicPO po) throws SQLException {
		TypeOfMatch matchType=po.getMatchType();//比赛类型
		String gameID=po.getGameID();
		String matchSeason=po.getMatchSeason();//赛季
		String matchDate=po.getMatchDate();//年月日
		String awayTeamID=po.getAwayTeamID();
		String homeTeamID=po.getHomeTeamID();
		String awayTeam=po.getAwayTeam();//客场球队
		String homeTeam=po.getHomeTeam();//主场球队
		int totalPoints_homeTeam=po.getTotalPoints_homeTeam();//总比分（客场在前主场在后）
		int firstPoints_homeTeam=po.getFirstPoints_homeTeam();//第一比分（客场在前主场在后）
		int secondPoints_homeTeam=po.getSecondPoints_homeTeam();//第二比分（客场在前主场在后）
		int thirdPoints_homeTeam=po.getThirdPoints_homeTeam();//第三比分（客场在前主场在后）
		int forthPoints_homeTeam=po.getForthPoints_homeTeam();//第四比分（客场在前主场在后）
		int totalPoints_awayTeam=po.getTotalPoints_awayTeam();//总比分（客场在前主场在后）
		int firstPoints_awayTeam=po.getFirstPoints_awayTeam();//第一比分（客场在前主场在后）
		int secondPoints_awayTeam=po.getSecondPoints_awayTeam();//第二比分（客场在前主场在后）
		int thirdPoints_awayTeam=po.getThirdPoints_awayTeam();//第三比分（客场在前主场在后）
		int forthPoints_awayTeam=po.getForthPoints_awayTeam();//第四比分（客场在前主场在后）
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		this.add_match_basic.setString(1, type);
		this.add_match_basic.setString(2, gameID);
		this.add_match_basic.setString(3,matchSeason );
		this.add_match_basic.setString(4, matchDate);
		this.add_match_basic.setString(5, awayTeamID);
		this.add_match_basic.setString(6,homeTeamID );
		this.add_match_basic.setString(7,awayTeam );
		this.add_match_basic.setString(8,homeTeam );
		this.add_match_basic.setInt(9, totalPoints_homeTeam);
		this.add_match_basic.setInt(10,firstPoints_homeTeam );
		this.add_match_basic.setInt(11,secondPoints_homeTeam );
		this.add_match_basic.setInt(12,thirdPoints_homeTeam );
		this.add_match_basic.setInt(13,forthPoints_homeTeam );
		this.add_match_basic.setInt(14, totalPoints_awayTeam);
		this.add_match_basic.setInt(15, firstPoints_awayTeam);
		this.add_match_basic.setInt(16, secondPoints_awayTeam);
		this.add_match_basic.setInt(17,thirdPoints_awayTeam );
		this.add_match_basic.setInt(18,forthPoints_awayTeam );
		this.add_match_basic.execute();
	}

	public void add_playerInMatch(PlayerInMatchPO po) throws SQLException {
		// TODO Auto-generated method stub
		String gameID=po.getGameID();
		String playerID=po.getPlayerID();
		String playerName=po.getPlayerName();// 球员名称
		String teamOfPlayer=po.getTeamOfPlayer();// 球员球队
		String position=po.getPosition();
		int isGS=po.getIsGS();// 是否首发
		int MIN=po.getMIN();// 出场时间
		int FGA=po.getFGA();// 投篮出手数
		int FGM=po.getFGM();// 投篮命中数
		String FGPer=po.getFGPer();// 投篮命中率
		int TPA=po.getTPA();// 三分出手数//原缩写为3PA
		int TPM=po.getTPM();// 三分命中数//3PM
		String TPPer=po.getTPPer();// 三分命中率//3P%
		int FTA=po.getFTA();// 罚球出手数
		int FTM=po.getFTM();// 罚球命中数
		String FTPer=po.getFTPer();// 罚球命中率
		int REB=po.getREB();// 篮板数
		int OREB=po.getOREB();// 进攻篮板//前场篮板
		int DREB=po.getDREB();// 防守篮板//后场篮板
		int AST=po.getAST();// 助攻数
		int STL=po.getSTL();// 抢断数
		int BLK=po.getBLK();// 盖帽数
		int TOV=po.getTOV();// 失误数
		int PF=po.getPF();// 犯规数
		int PTS=po.getPTS();// 总得分
		double PLUS_MINUS=po.getPLUS_MINUS();// 真實命中率
		
		this.add_playerInMatch.setString(1, gameID);
		this.add_playerInMatch.setString(2, playerID);
		this.add_playerInMatch.setString(3,playerName );
		this.add_playerInMatch.setString(4,teamOfPlayer );
		this.add_playerInMatch.setString(5, position);
		this.add_playerInMatch.setInt(6, isGS);
		this.add_playerInMatch.setInt(7,MIN );
		this.add_playerInMatch.setInt(8,FGA );
		this.add_playerInMatch.setInt(9, FGM);
		this.add_playerInMatch.setString(10, FGPer);
		this.add_playerInMatch.setInt(11,TPA );
		this.add_playerInMatch.setInt(12,TPM );
		this.add_playerInMatch.setString(13,TPPer );
		this.add_playerInMatch.setInt(14, FTA);
		this.add_playerInMatch.setInt(15,FTM );
		this.add_playerInMatch.setString(16,FTPer );
		this.add_playerInMatch.setInt(17, REB);
		this.add_playerInMatch.setInt(18, OREB);
		this.add_playerInMatch.setInt(19,DREB );
		this.add_playerInMatch.setInt(20,AST );
		this.add_playerInMatch.setInt(21, STL);
		this.add_playerInMatch.setInt(22,BLK );
		this.add_playerInMatch.setInt(23,TOV );
		this.add_playerInMatch.setInt(24,PF );
		this.add_playerInMatch.setInt(25, PTS);
		this.add_playerInMatch.setDouble(26,PLUS_MINUS );
		this.add_playerInMatch.execute();
	}

	public void add_teamInMatch(TeamInMatchPO po) throws SQLException {
		// TODO Auto-generated method stub
		String gameID=po.getGameID();
		
		String teamID=po.getTeamID();
		String teamName=po.getTeamName();// 該球隊
		int numOfPlayer=po.getNumOfPlayer();// 出場人數
		int FGA=po.getFGA();// 投篮出手数
		int FGM=po.getFGM();// 投篮命中数
		double FGPer=po.getFGPer();// 投篮命中率
		int TPA=po.getTPA();// 三分出手数//原缩写为3PA
		int TPM=po.getTPM();// 三分命中数//3PM
		double TPPer=po.getTPPer();// 三分命中率//3P%
		int FTA=po.getFTA();// 罚球出手数
		int FTM=po.getFTM();// 罚球命中数
		double FTPer=po.getFTPer();// 罚球命中率
		int REB=po.getREB();// 篮板数
		int OREB=po.getOREB();// 进攻篮板//前场篮板
		int DREB=po.getDREB();// 防守篮板//后场篮板
		int AST=po.getAST();// 助攻数
		int STL=po.getSTL();// 抢断数
		int BLK=po.getBLK();// 盖帽数
		int TOV=po.getTOV();// 失误数
		int PF=po.getPF();// 犯规数
		int PTS=po.getPTS();// 总得分
		double PLUS_MINUS=po.getPLUS_MINUS();// 真實命中率
		int isWin=po.getIsWin();
		
		this.add_teamInMatch.setString(1,gameID );
		this.add_teamInMatch.setString(2,teamID );
		this.add_teamInMatch.setString(3, teamName);
		this.add_teamInMatch.setInt(4,numOfPlayer );
		this.add_teamInMatch.setInt(5,FGA );
		this.add_teamInMatch.setInt(6, FGM);
		this.add_teamInMatch.setDouble(7, FGPer);
		this.add_teamInMatch.setInt(8,TPA );
		this.add_teamInMatch.setInt(9,TPM );
		this.add_teamInMatch.setDouble(10,TPPer );
		this.add_teamInMatch.setInt(11, FTA);
		this.add_teamInMatch.setInt(12,FTM );
		this.add_teamInMatch.setDouble(13,FTPer );
		this.add_teamInMatch.setInt(14, REB);
		this.add_teamInMatch.setInt(15, OREB);
		this.add_teamInMatch.setInt(16,DREB );
		this.add_teamInMatch.setInt(17,AST );
		this.add_teamInMatch.setInt(18, STL);
		this.add_teamInMatch.setInt(19,BLK );
		this.add_teamInMatch.setInt(20,TOV );
		this.add_teamInMatch.setInt(21,PF );
		this.add_teamInMatch.setInt(22, PTS);
		this.add_teamInMatch.setDouble(23,PLUS_MINUS );
		this.add_teamInMatch.setInt(24,isWin);
		this.add_teamInMatch.execute();
	}
	public ArrayList<PlayerInMatchPO> getPlayerInMatchVOs4Match(
			String matchTime, String teamAway, String teamHome)
			throws SQLException {
		// TODO Auto-generated method stub
//		//两种方法时间一样都是9秒
		ArrayList<PlayerInMatchPO>list=new ArrayList<PlayerInMatchPO>();
//		String sql="select *from matchbasicinfo inner join playerinmatch  where matchbasicinfo.matchDate= '"+matchTime+"'and awayTeam='"+teamAway+"' and homeTeam='"+teamHome+"' and playerinmatch.gameID=matchbasicinfo.gameID";
//		ResultSet rs=this.find_player_by_id.executeQuery(sql);
//		list=get_playerInMatch(rs);
		String sql="select *from matchbasicinfo where matchDate= '"+matchTime+"' and awayTeam= '"+teamAway+"' and homeTeam= '"+teamHome+"'";
		ResultSet rs=this.find_matchBasic_by_id.executeQuery(sql);
		ArrayList<MatchBasicPO>matchBasicPOs=get_basicMatch_from_rs(rs);
//		System.out.println("gameID="+matchBasicPOs.get(0).getGameID());
		for(int i=0;i<matchBasicPOs.size();i++){
			String gameID=matchBasicPOs.get(i).getGameID().trim();
			gameID="%"+gameID.substring(2)+"%";
			String sql1="select *from playerinmatch where gameID like '"+gameID+"'";
			ResultSet rs1=this.find_player_by_id.executeQuery(sql1);
			ArrayList<PlayerInMatchPO>playerInMatchPOs=get_playerInMatch_from_rs(rs1);
//			System.out.println("size="+playerInMatchPOs.size());
			for(int j=0;j<playerInMatchPOs.size();j++){
				list.add(playerInMatchPOs.get(j));
			}
		}
		return list;
	}
	public ArrayList<TeamInMatchPO> getTeamInMatchVOs4Match(String matchTime,
			String teamAway, String teamHome) throws SQLException {
		// TODO Auto-generated method stub
ArrayList<TeamInMatchPO>list=new ArrayList<TeamInMatchPO>();
		String sql="select *from matchbasicinfo where matchDate='"+matchTime+"' and awayTeam='"+teamAway+"' and homeTeam='"+teamHome+"'";
		ResultSet rs=this.find_match_basic.executeQuery(sql);
		ArrayList<MatchBasicPO>matchBasicPOs=get_basicMatch_from_rs(rs);
		for(int i=0;i<matchBasicPOs.size();i++){
			String gameID=matchBasicPOs.get(i).getGameID();
			String sql1="select *from teaminmatch where gameID='"+gameID+"'";
			ResultSet rs1=this.find_team_by_id.executeQuery(sql1);
			ArrayList<TeamInMatchPO>teamInMatchPOs=get_teamInMatch_from_rs(rs1);
			for(int j=0;j<teamInMatchPOs.size();j++){
				list.add(teamInMatchPOs.get(j));
			}
		}
		return list;
	}
//	private  void update(String awayTeam) throws SQLException{
//		String sql="update matchbasicinfo set matchType= 'allstar' where awayTeam= '"+awayTeam+"'";
//	this.find_team_by_id.executeUpdate(sql);
//	}
	


//public void test1() throws IOException{
//	BufferedReader br=new BufferedReader(new FileReader("D://nba_data/Hound Dog McClain.txt"));
//	String line=null; 
//	BufferedWriter bw=new BufferedWriter(new FileWriter("D://nba_data/name_match.txt",true));
//	File file=new File("D://nba_data/match_final");
//	String []files=file.list();
//	while((line=br.readLine())!=null){
//		String gameID=line.split(",")[0].substring(0, line.split(",")[0].length()-1);
////		String gameID=line.split(",")[0];
////		System.out.println(gameID);
//     		for(int i=0;i<files.length;i++){
////     			System.out.println(files[i]);
//     			if(files[i].contains(gameID)){
//     				bw.write(files[i]);
//     				bw.write("\t\n");
//     				break;
//     			}
//     		}
//     		
//     		
//	}
//	bw.flush();
//	bw.close();
//	System.out.println("ends");
//}
public void delete() throws SQLException{
	String sql="delete  from playerinmatch where playerID='77036'";//另一个playerID是77510 77036
	this.find_player_by_id.executeUpdate(sql);
	System.out.println("ends");
}
private void update_playerInMatch(Player_for_update po) throws SQLException {
	// TODO Auto-generated method stub
	String gameID=po.getGameID();

	String playerID=po.getPlayerID();
	String playerName=po.getPlayerName();// 球员名称
	String teamOfPlayer=po.getTeamOfPlayer();// 球员球队
	String position=po.getPosition();
	int isGS=po.getIsGS();// 是否首发
	int MIN=po.getMIN();// 出场时间
	int FGA=po.getFGA();// 投篮出手数
	int FGM=po.getFGM();// 投篮命中数
	String FGPer=po.getFGPer();// 投篮命中率
	int TPA=po.getTPA();// 三分出手数//原缩写为3PA
	int TPM=po.getTPM();// 三分命中数//3PM
	String TPPer=po.getTPPer();// 三分命中率//3P%
	int FTA=po.getFTA();// 罚球出手数
	int FTM=po.getFTM();// 罚球命中数
	String FTPer=po.getFTPer();// 罚球命中率
	int REB=po.getREB();// 篮板数
	int OREB=po.getOREB();// 进攻篮板//前场篮板
	int DREB=po.getDREB();// 防守篮板//后场篮板
	int AST=po.getAST();// 助攻数
	int STL=po.getSTL();// 抢断数
	int BLK=po.getBLK();// 盖帽数
	int TOV=po.getTOV();// 失误数
	int PF=po.getPF();// 犯规数
	int PTS=po.getPTS();// 总得分
	double PLUS_MINUS=po.getPLUS_MINUS();// 真實命中率

	this.add_playerInMatch.setString(1, gameID);
	this.add_playerInMatch.setString(2, playerID);
	this.add_playerInMatch.setString(3,playerName );
	this.add_playerInMatch.setString(4,teamOfPlayer );
	this.add_playerInMatch.setString(5, position);
	this.add_playerInMatch.setInt(6, isGS);
	this.add_playerInMatch.setInt(7,MIN );
	this.add_playerInMatch.setInt(8,FGA );
	this.add_playerInMatch.setInt(9, FGM);
	this.add_playerInMatch.setString(10, FGPer);
	this.add_playerInMatch.setInt(11,TPA );
	this.add_playerInMatch.setInt(12,TPM );
	this.add_playerInMatch.setString(13,TPPer );
	this.add_playerInMatch.setInt(14, FTA);
	this.add_playerInMatch.setInt(15,FTM );
	this.add_playerInMatch.setString(16,FTPer );
	this.add_playerInMatch.setInt(17, REB);
	this.add_playerInMatch.setInt(18, OREB);
	this.add_playerInMatch.setInt(19,DREB );
	this.add_playerInMatch.setInt(20,AST );
	this.add_playerInMatch.setInt(21, STL);
	this.add_playerInMatch.setInt(22,BLK );
	this.add_playerInMatch.setInt(23,TOV );
	this.add_playerInMatch.setInt(24,PF );
	this.add_playerInMatch.setInt(25, PTS);
	this.add_playerInMatch.setDouble(26,PLUS_MINUS );
	this.add_playerInMatch.execute();
}
public MatchBasicPO getMatchById(String matchID) throws SQLException {
	// TODO Auto-generated method stub
	String sql="select *from matchbasicinfo where gameID= '"+matchID+"'";
	ResultSet rs=this.find_match_basic.executeQuery(sql);
	ArrayList<MatchBasicPO>pos=get_basicMatch_from_rs(rs);
	if(pos.size()>0){
		return pos.get(0);
	}
	return null;
}
//public void updateMatches() throws SQLException {
//	// TODO Auto-generated method stub
//	String sql="select *from matchbasicinfo ";
//	ResultSet rs=this.find_match_basic.executeQuery(sql);
//	ArrayList<MatchBasicPO>pos=get_basicMatch_from_rs(rs);
//	System.out.println(pos.size());
//	for(int i=0;i<pos.size();i++){
//		MatchBasicPO po=pos.get(i);
//		String gameID=po.getGameID();
//		String awayTeamID=po.getAwayTeamID();
//		String homeTeamID=po.getHomeTeamID();
//		int awayTeam_totalScore=po.getTotalPoints_awayTeam();
//		int homeTeam_totalScore=po.getTotalPoints_homeTeam();
//		int away_win=0;
//		int home_win=0;
//		if(awayTeam_totalScore>homeTeam_totalScore){
//			away_win=1;
//			home_win=-1;
//		}else{
//			away_win=-1;
//			home_win=1;
//		}
//		String  sql_away="update teaminmatch set isWin= '"+away_win+"' where gameID= '"+gameID+"' and teamID= '"+awayTeamID+"'";
//		this.find_team_by_id.executeUpdate(sql_away);
//		String  sql_home="update teaminmatch set isWin= '"+home_win+"' where gameID= '"+gameID+"' and teamID= '"+homeTeamID+"'";
//		this.find_team_by_id.executeUpdate(sql_home);
//	}
//	System.out.println("ends");
//}
private PlayerInMatchPO getPlayerInMatchFromID(String gameID,String playerID) throws SQLException{
	gameID="%"+gameID.trim()+"%";
	String sql1="select *from playerinmatch where gameID like '"+gameID+"' and playerID= '"+playerID+"'";
	ResultSet rs=this.find_player_by_id.executeQuery(sql1);
	ArrayList<PlayerInMatchPO>pos=get_playerInMatch_from_rs(rs);
	if(pos.size()>0){
		return pos.get(0);
	}
	return null;
}
private TeamInMatchPO getTeamInMatchFromTeamName(String gameID,String teamName) throws SQLException{
	String sql1="select *from teaminmatch where gameID= '"+gameID+"' and teamName= '"+teamName+"'";
	ResultSet rs=this.find_team_by_name.executeQuery(sql1);
	ArrayList<TeamInMatchPO>pos=get_teamInMatch_from_rs(rs);
	if(pos.size()>0){
		return pos.get(0);
	}
	return null;
}
public ArrayList<PlayerInMatchPO> getPlayer5Matches(String playerID)
		throws SQLException {
	// TODO Auto-generated method stub
	ArrayList<PlayerInMatchPO> list=new ArrayList<PlayerInMatchPO>();
	String sql1="select *from last5gamesofplayer where playerID= '"+playerID+"'";
	ResultSet  rs=this.find_last5_player.executeQuery(sql1);
	while(rs.next()){
		
//		String playerName=rs.getString("playerName");
		String gameID1=rs.getString("gameID1");
		String gameID2=rs.getString("gameID2");
		String gameID3=rs.getString("gameID3");
		String gameID4=rs.getString("gameID4");
		String gameID5=rs.getString("gameID5");
		PlayerInMatchPO po1=getPlayerInMatchFromID(gameID1, playerID);
		PlayerInMatchPO po2=getPlayerInMatchFromID(gameID2, playerID);
		PlayerInMatchPO po3=getPlayerInMatchFromID(gameID3, playerID);
		PlayerInMatchPO po4=getPlayerInMatchFromID(gameID4, playerID);
		PlayerInMatchPO po5=getPlayerInMatchFromID(gameID5, playerID);
		if(po1!=null){
			list.add(po1);
		}
		if(po2!=null){
			list.add(po2);
		}
		if(po3!=null){
			list.add(po3);
		}
		if(po4!=null){
			list.add(po4);
		}
		if(po5!=null){
			list.add(po5);
		}
		
	}
	return list;
}
public ArrayList<TeamInMatchPO> getTeam5Matcher(String teamName)
		throws SQLException {
	// TODO Auto-generated method stub
	ArrayList<TeamInMatchPO> list=new ArrayList<TeamInMatchPO>();
	String sql1="select *from last5gamesofteam where teamName= '"+teamName+"'";
	ResultSet  rs=this.find_last5_team.executeQuery(sql1);
	while(rs.next()){
		
//		String playerName=rs.getString("playerName");
		String gameID1=rs.getString("gameID1");
		String gameID2=rs.getString("gameID2");
		String gameID3=rs.getString("gameID3");
		String gameID4=rs.getString("gameID4");
		String gameID5=rs.getString("gameID5");
		TeamInMatchPO po1=getTeamInMatchFromTeamName(gameID1, teamName);
		TeamInMatchPO po2=getTeamInMatchFromTeamName(gameID2, teamName);
		TeamInMatchPO po3=getTeamInMatchFromTeamName(gameID3, teamName);
		TeamInMatchPO po4=getTeamInMatchFromTeamName(gameID4, teamName);
		TeamInMatchPO po5=getTeamInMatchFromTeamName(gameID5, teamName);
		if(po1!=null){
			list.add(po1);
		}
		if(po2!=null){
			list.add(po2);
		}
		if(po3!=null){
			list.add(po3);
		}
		if(po4!=null){
			list.add(po4);
		}
		if(po5!=null){
			list.add(po5);
		}
		
	}
	return list;
}
public void add_last5games_player(Last5Games_player po) throws SQLException{
	
	String playerID=po.getPlayerID();
	String playerName=po.getPlayerName();
	String gameID1=po.getGameID1();
	String gameID2=po.getGameID2();
	String gameID3=po.getGameID3();
	String gameID4=po.getGameID4();
	String gameID5=po.getGameID5();
	
	this.add_last5Games_player.setString(1, playerID);
	this.add_last5Games_player.setString(2,playerName );
	this.add_last5Games_player.setString(3, gameID1);
	this.add_last5Games_player.setString(4,gameID2 );
	this.add_last5Games_player.setString(5,gameID3 );
	this.add_last5Games_player.setString(6, gameID4);
	this.add_last5Games_player.setString(7, gameID5);
	this.add_last5Games_player.execute();
	
}
public void add_last5games_team(Last5Games_team po) throws SQLException{
	
	String teamID=po.getTeamID();
	String teamName=po.getTeamName();
	String gameID1=po.getGameID1();
	String gameID2=po.getGameID2();
	String gameID3=po.getGameID3();
	String gameID4=po.getGameID4();
	String gameID5=po.getGameID5();
	
	this.add_last5Games_team.setString(1, teamID);
	this.add_last5Games_team.setString(2,teamName );
	this.add_last5Games_team.setString(3, gameID1);
	this.add_last5Games_team.setString(4,gameID2 );
	this.add_last5Games_team.setString(5,gameID3 );
	this.add_last5Games_team.setString(6, gameID4);
	this.add_last5Games_team.setString(7, gameID5);
	this.add_last5Games_team.execute();
	
}
private Last5Games_team changeToLast5Games_team(ArrayList<TeamInMatchPO>pos){
	if(pos.size()>0){
		String teamID="";
		String teamName="";
		String gameID1="";
		String gameID2="";
		String gameID3="";
		String gameID4="";
		String gameID5="";
		teamID=pos.get(0).getTeamID();
		teamName=pos.get(0).getTeamName();
		gameID1=pos.get(0).getGameID();
		if(pos.size()>1){
			gameID2=pos.get(1).getGameID();
		}
		if(pos.size()>2){
			gameID3=pos.get(2).getGameID();
		}
		if(pos.size()>3){
			gameID4=pos.get(3).getGameID();
		}
		if(pos.size()>4){
			gameID5=pos.get(4).getGameID();
		}
		return new Last5Games_team(teamID, teamName, gameID1, gameID2, gameID3, gameID4, gameID5);
	}
	return null;
}
private Last5Games_player changeToLast5Games_player(ArrayList<PlayerInMatchPO>pos){
	if(pos.size()>0){
		String playerID="";
		String playerName="";
		String gameID1="";
		String gameID2="";
		String gameID3="";
		String gameID4="";
		String gameID5="";
		playerID=pos.get(0).getPlayerID();
		playerName=pos.get(0).getPlayerName();
		gameID1=pos.get(0).getGameID();
		if(pos.size()>1){
			gameID2=pos.get(1).getGameID();
		}
		if(pos.size()>2){
			gameID3=pos.get(2).getGameID();
		}
		if(pos.size()>3){
			gameID4=pos.get(3).getGameID();
		}
		if(pos.size()>4){
			gameID5=pos.get(4).getGameID();
		}
		return new Last5Games_player(playerID, playerName, gameID1, gameID2, gameID3, gameID4, gameID5);
	}
	return null;
}
public ArrayList<Last5Games_team> getLast5Games_allTeam(String fileName) throws IOException, SQLException{
	 ArrayList<Last5Games_team>teamList=new  ArrayList<Last5Games_team>();
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=null;
	while((line=br.readLine())!=null){
		String teamID=line.split(",")[0].trim();
		ArrayList<TeamInMatchPO> teams=getLast5Games_team(teamID);
		Last5Games_team last=changeToLast5Games_team(teams);
		if(last!=null){
			teamList.add(last);
		}
		
	}
	return teamList;
}
public ArrayList<Last5Games_player> getLast5Games_allPlayer(String fileName) throws IOException, SQLException{
	 ArrayList<Last5Games_player>teamList=new  ArrayList<Last5Games_player>();
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=null;
	ArrayList<String> playersID=new ArrayList<String>();
	while((line=br.readLine())!=null){
		String playerID=line.split(",")[0].trim();
		playersID.add(playerID);
	}
	
	System.out.println(playersID.size());

//	for (int i = 1589; i < 3000; i++) {
	for (int i = 2304; i < 3000; i++) {
		ArrayList<PlayerInMatchPO> players=getLast5Games_player(playersID.get(i));
		Last5Games_player last=changeToLast5Games_player(players);
		if(last!=null){
//			teamList.add(last);
//			add_last5games_player(last);
			add_last5games_player(last);		
		}
		System.out.println(i);
	}
	return teamList;
}
private int getIndexOfLongest_player(ArrayList<PlayerInMatchPO>pos){
	int index=0;
	for(int i=1;i<pos.size();i++){
		if(pos.get(index).getMatchDate().compareTo(pos.get(i).getMatchDate())>0){
			index=i;
			
		}
	}
	return index;
}
private int getIndexOfLongest_team(ArrayList<TeamInMatchPO>pos){
	int index=0;
	for(int i=1;i<pos.size();i++){
		if(pos.get(index).getMatchDate().compareTo(pos.get(i).getMatchDate())>0){
			index=i;
			
		}
	}
	return index;
}
public ArrayList<TeamInMatchPO> getLast5Games_team(String teamID) throws SQLException{
	ArrayList<TeamInMatchPO>list=new ArrayList<TeamInMatchPO>();
	String sql1="select *from teaminmatch where teamID= '"+teamID+"'";
	ResultSet rs=this.find_team_by_id.executeQuery(sql1);
	ArrayList<TeamInMatchPO>pos=get_teamInMatch_from_rs(rs);
	for(int i=0;i<pos.size();i++){
		if(i<5){
			list.add(pos.get(i));
		}else{
			int index=getIndexOfLongest_team(list);
			if(list.get(index).getMatchDate().compareTo(pos.get(i).getMatchDate())<0){
				list.remove(index);
				list.add(pos.get(i));
			}
		}
		
	}
	return list;
}
public ArrayList<PlayerInMatchPO> getLast5Games_player(String playerID) throws SQLException{
	ArrayList<PlayerInMatchPO>list=new ArrayList<PlayerInMatchPO>();
	String sql1="select *from playerinmatch where playerID= '"+playerID+"'";
	ResultSet rs=this.find_player_by_id.executeQuery(sql1);
	ArrayList<PlayerInMatchPO>pos=get_playerInMatch_from_rs(rs);
	for(int i=0;i<pos.size();i++){
		if(i<5){
			list.add(pos.get(i));
		}else{
			int index=getIndexOfLongest_player(list);
			if(list.get(index).getMatchDate().compareTo(pos.get(i).getMatchDate())<0){
				list.remove(index);
				list.add(pos.get(i));
			}
		}
		
	}
	return list;
}
private ArrayList<PlayerInMatchPO>getAllMatches_player(String playerID) throws SQLException{
	String sql="select *from playerinmatch where playerID= '"+playerID+"'";
	ResultSet rs=this.find_player_by_id.executeQuery(sql);
	ArrayList<PlayerInMatchPO>pos=get_playerInMatch_from_rs(rs);
	return pos;
}
private ArrayList<TeamInMatchPO>getAllMatches_team(String teamID) throws SQLException{
	String sql="select *from teaminmatch where teamID= '"+teamID+"'";
	ResultSet rs=this.find_team_by_id.executeQuery(sql);
	ArrayList<TeamInMatchPO>pos=get_teamInMatch_from_rs(rs);
	return pos;
}
public static void main(String[]args) throws DatabaseException, SQLException, IOException{
	MatchDataServiceImp match=new MatchDataServiceImp();

//	match.delete();
	ArrayList<Player_for_update>pos=DealTheTwo.get_new_pos("D:\\source\\dealed.txt");
	for(int i=0;i<pos.size();i++){
		match.update_playerInMatch(pos.get(i));
		System.out.println(i);
	}
//	System.out.println("ends");
//	ArrayList<TeamInMatchPO>all=match.getAllMatches_team("1610610035");
//	for(int j=0;j<all.size();j++){
//		System.out.println(j+","+all.get(j).getGameID()+","+all.get(j).getMatchDate());
//		
//	}
//	System.out.println("all stop");
//	ArrayList<TeamInMatchPO>pos=match.getLast5Games_team("1610610035");
//	for(int i=0;i<pos.size();i++){
//		System.out.println(pos.get(i).getGameID()+","+pos.get(i).getMatchDate());
//	}
//	System.out.println("ends");
//

	//这是球员的最近五场,参数是所有球员ID的文件的地址
//		match.getLast5Games_allPlayer("D://nba_data/playerPair_name_id.out");
		
//	for(int i=0;i<players.size();i++){
//		match.add_last5games_player(players.get(i));
//	}
//	}

//球队的最近五场
//ArrayList<Last5Games_team>teams=match.getLast5Games_allTeam("D://nba_data/teamPair_name_id.out");
////ArrayList<Last5Games_team>teams=match.getLast5Games_allTeam("D:\\source\\teamPair_name_id.out");
//for(int j=0;j<teams.size();j++){
//	System.out.println(j);
//	match.add_last5games_team(teams.get(j));
//	}
//System.out.println("ends");
}
}
