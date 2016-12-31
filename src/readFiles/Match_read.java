package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import enumerate.PositionOfPlayer;
import enumerate.TypeOfMatch;
import po.MatchBasicPO;
import po.MatchPO;
import po.PlayerInMatchPO;
import po.TeamInMatchPO;

public class Match_read {
public static MatchPO read_one_match(String fileName) throws IOException{
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=br.readLine();//1
	if(line==null){
		return null;
	}
	if(line.length()<1){
		return null;
	}
	String []line1=line.split(",");
	String matchDate=line1[0];
	String gameID=line1[1];
	line=br.readLine();//2
	String []line2=line.split(",");
	line=br.readLine();//3
	String []line3=line.split(",");
	String awayTeamID=line2[0];
	String homeTeamID=line3[0];
	String awayTeam=line2[1];//客场球队
	String homeTeam=line3[1];//主场球队
	int totalPoints_homeTeam=NumberFormatChange.changeToInt(line3[2]);//总比分（客场在前主场在后）
	int firstPoints_homeTeam=NumberFormatChange.changeToInt(line3[3]);//第一比分（客场在前主场在后）
	int secondPoints_homeTeam=NumberFormatChange.changeToInt(line3[4]);//第二比分（客场在前主场在后）
	int thirdPoints_homeTeam=NumberFormatChange.changeToInt(line3[5]);//第三比分（客场在前主场在后）
	int forthPoints_homeTeam=NumberFormatChange.changeToInt(line3[6].trim());//第四比分（客场在前主场在后）
	int totalPoints_awayTeam=NumberFormatChange.changeToInt(line2[2]);//总比分（客场在前主场在后）
	int firstPoints_awayTeam=NumberFormatChange.changeToInt(line2[3]);//第一比分（客场在前主场在后）
	int secondPoints_awayTeam=NumberFormatChange.changeToInt(line2[4]);//第二比分（客场在前主场在后）
	int thirdPoints_awayTeam=NumberFormatChange.changeToInt(line2[5]);//第三比分（客场在前主场在后）
	int forthPoints_awayTeam=NumberFormatChange.changeToInt(line2[6].trim());//第四比分（客场在前主场在后）
	int away_win=0;
	int home_win=0;
	if(totalPoints_awayTeam>totalPoints_homeTeam){
		away_win=1;
		home_win=-1;
	}else{
		away_win=-1;
		home_win=1;
	}
	//对常规赛还是季后赛判断
			TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			int year=Integer.parseInt(matchDate.substring(0,4));
			int month=Integer.parseInt(matchDate.substring(5, 7));
			int date=Integer.parseInt(matchDate.substring(8));
			if(month>4  && month<9){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(month==4){
				if(date>=22){
					typeOfMatch=TypeOfMatch.PLAYOFF;
				}
			}
			String matchSeason="";
			if(month>9){
				int y=year+1;
			String yy=(y+"").substring(2);
			matchSeason=year+"-"+yy;
			}else{
				int y=year-1;
				String yy=(year+"").substring(2);
				matchSeason=y+"-"+yy;
			}
			
			 
	ArrayList<PlayerInMatchPO>awayList=new ArrayList<PlayerInMatchPO>();
	ArrayList<PlayerInMatchPO>homeList=new ArrayList<PlayerInMatchPO>();
	ArrayList<PlayerInMatchPO>totalList=new ArrayList<PlayerInMatchPO>();
	ArrayList<TeamInMatchPO>teamList=new ArrayList<TeamInMatchPO>();
	while((line=br.readLine())!=null){
		if(line.startsWith("Total")){
			break;
		}
		String []infos=line.split(",");
//		if(infos.length>26){
//			System.out.println(infos[3]+infos[6]);
//		}
//		System.out.println(matchDate+","+infos[3]+","+infos[6]+","+infos.length);
		String teamID=infos[1];
//		String teamName=infos[2];

		String playerID=infos[3];
		String playerName=infos[4];// 球员名称
		String teamOfPlayer=infos[2];// 球员球队
		String position=infos[5];
		int isGS=0;// 是否首发
		if(!position.equals("")){
			isGS=1;
		}
//		System.out.println(fileName);
		int MIN=0;
		if(infos[6].length()>2){
			 MIN=NumberFormatChange.changeTimeForm(infos[6].substring(1, infos[6].length()-1));// 出场时间
		}
		int FGA=NumberFormatChange.changeToInt(infos[8]);// 投篮出手数
		int FGM=NumberFormatChange.changeToInt(infos[7]);// 投篮命中数
		String FGPer=NumberFormatChange.changeToPer(infos[9]);// 投篮命中率
		int TPA=NumberFormatChange.changeToInt(infos[11]);// 三分出手数//原缩写为3PA
		int TPM=NumberFormatChange.changeToInt(infos[10]);// 三分命中数//3PM
		String TPPer=NumberFormatChange.changeToPer(infos[12]);// 三分命中率//3P%
		int FTA=NumberFormatChange.changeToInt(infos[14]);// 罚球出手数
		int FTM=NumberFormatChange.changeToInt(infos[13]);// 罚球命中数
		String FTPer=NumberFormatChange.changeToPer(infos[15]);// 罚球命中率
		int REB=NumberFormatChange.changeToInt(infos[18]);// 篮板数
		int OREB=NumberFormatChange.changeToInt(infos[16]);// 进攻篮板//前场篮板
		int DREB=NumberFormatChange.changeToInt(infos[17]);// 防守篮板//后场篮板
		int AST=NumberFormatChange.changeToInt(infos[19]);// 助攻数
		int STL=NumberFormatChange.changeToInt(infos[20]);// 抢断数
		int BLK=NumberFormatChange.changeToInt(infos[21]);// 盖帽数
		int TOV=NumberFormatChange.changeToInt(infos[22]);// 失误数
		int PF=NumberFormatChange.changeToInt(infos[23]);// 犯规数
		int PTS=NumberFormatChange.changeToInt(infos[24]);// 总得分
		double PLUS_MINUS=NumberFormatChange.changeToDouble(infos[25]);// 真實命中率
		
		
	PlayerInMatchPO po=new PlayerInMatchPO(gameID, matchDate, typeOfMatch, awayTeamID, awayTeam, homeTeamID, homeTeam, playerID, playerName, teamOfPlayer, position, isGS, MIN, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS);
	totalList.add(po);
	}
	while((line=br.readLine())!=null){
		String []infos=line.split(",");
		String teamID=infos[1];
//		String teamName=infos[2];

		
		String teamName=infos[2];// 球员球队
		int MIN=0;
		if(infos[3].length()>2){
			 MIN=NumberFormatChange.changeTimeForm(infos[3].substring(1, infos[3].length()-1));// 出场时间
		}
//		int MIN=NumberFormatChange.changeTimeForm(infos[3]);// 出场时间
		int FGA=NumberFormatChange.changeToInt(infos[5]);// 投篮出手数
		int FGM=NumberFormatChange.changeToInt(infos[4]);// 投篮命中数
		double FGPer=NumberFormatChange.changeToDouble(infos[6]);// 投篮命中率
		int TPA=NumberFormatChange.changeToInt(infos[8]);// 三分出手数//原缩写为3PA
		int TPM=NumberFormatChange.changeToInt(infos[7]);// 三分命中数//3PM
		double TPPer=NumberFormatChange.changeToDouble(infos[9]);// 三分命中率//3P%
		int FTA=NumberFormatChange.changeToInt(infos[11]);// 罚球出手数
		int FTM=NumberFormatChange.changeToInt(infos[10]);// 罚球命中数
		double FTPer=NumberFormatChange.changeToDouble(infos[12]);// 罚球命中率
		int REB=NumberFormatChange.changeToInt(infos[15]);// 篮板数
		int OREB=NumberFormatChange.changeToInt(infos[13]);// 进攻篮板//前场篮板
		int DREB=NumberFormatChange.changeToInt(infos[14]);// 防守篮板//后场篮板
		int AST=NumberFormatChange.changeToInt(infos[16]);// 助攻数
		int STL=NumberFormatChange.changeToInt(infos[17]);// 抢断数
		int BLK=NumberFormatChange.changeToInt(infos[18]);// 盖帽数
		int TOV=NumberFormatChange.changeToInt(infos[19]);// 失误数
		int PF=NumberFormatChange.changeToInt(infos[20]);// 犯规数
		int PTS=NumberFormatChange.changeToInt(infos[21]);// 总得分
		double PLUS_MINUS=NumberFormatChange.changeToDouble(infos[22]);// 
		int isWin=0;
		if(teamID.equals(awayTeamID)){
			isWin=away_win;
		}else{
			isWin=home_win;
		}
	teamList.add(new TeamInMatchPO(gameID, matchDate, typeOfMatch, matchSeason, awayTeamID, awayTeam, homeTeamID, homeTeam, teamID, teamName,0,isWin,  FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS));
	}
	int numOfAwayPlayer=0;
	int numOfHomePlayer=0;
	for(int i=0;i<totalList.size();i++){
	PlayerInMatchPO po=totalList.get(i);
	if(po.getTeamOfPlayer().equals(awayTeam)){
		awayList.add(po);
		numOfAwayPlayer++;
	}else {
		homeList.add(po);
		numOfHomePlayer++;
	}
	}
	TeamInMatchPO awayTeamInMatchPO=null;
	TeamInMatchPO homeTeamInMatchPO=null;
	if(teamList.size()>0){
		for(int k=0;k<teamList.size();k++){
			TeamInMatchPO po=teamList.get(k);
			if(po.getTeamName().equals(awayTeam)){
				awayTeamInMatchPO=po;
				awayTeamInMatchPO.setNumOfPlayer(numOfAwayPlayer);
			}
			if(po.getTeamName().equals(homeTeam)){
				homeTeamInMatchPO=po;
				homeTeamInMatchPO.setNumOfPlayer(numOfHomePlayer);
			}
		}
	}
	return new MatchPO(gameID, matchDate, typeOfMatch, awayTeamID, awayTeam, homeTeamID, homeTeam, totalPoints_awayTeam, totalPoints_homeTeam, firstPoints_awayTeam, secondPoints_awayTeam, thirdPoints_awayTeam, forthPoints_awayTeam, firstPoints_homeTeam, secondPoints_homeTeam, thirdPoints_homeTeam, forthPoints_homeTeam, awayList, homeList, awayTeamInMatchPO, homeTeamInMatchPO);
}
//public static void main(String[]args) throws IOException{
////	Match_read read=new Match_read();
////	MatchPO po=read.read_one_match("D://nba_data/match_final/1947-12-21-0024700061.txt");
////	System.out.println(po);
//////	System.out.println(po.getHomeTeamStats().getNumOfPlayer());
//////	System.out.println(po.getHomeTeamStats().getMatchSeason());
//		String fileDir="D://nba_data/match_final";
//		File file=new File(fileDir);
//		String []files=file.list();
//		ArrayList<MatchPO>matchPOs=new ArrayList<MatchPO>();
//		ArrayList<MatchBasicPO>matchBasicPOs=new ArrayList<MatchBasicPO>();
//		ArrayList<PlayerInMatchPO>playerInMatchPOs=new ArrayList<PlayerInMatchPO>();
//		ArrayList<TeamInMatchPO>teamInMatchPOs=new ArrayList<TeamInMatchPO>();
//		for(int i=0;i<files.length;i++){
//			String fileName=fileDir+"/"+files[i];
//			MatchPO matchPO=Match_read.read_one_match(fileName);
//			if(matchPO!=null){
//				matchPOs.add(matchPO);
//				MatchBasicPO matchBasicPO=Match_read.changeToMatchBasicPO(matchPO);
//				matchBasicPOs.add(matchBasicPO);
//				ArrayList<PlayerInMatchPO>playerList1=matchPO.getHomeTeamPlayers();
//				ArrayList<PlayerInMatchPO>playerList2=matchPO.getAwayTeamPlayers();
//		      for(int j=0;j<playerList1.size();j++){
//		    	  playerInMatchPOs.add(playerList1.get(j));
////		    	  matchDataService.add_playerInMatch(playerList1.get(j));
//		      }
//		      for(int k=0;k<playerList2.size();k++){
//		    	  playerInMatchPOs.add(playerList2.get(k));
////		    	  matchDataService.add_playerInMatch(playerList2.get(k));
//		      }
//		      teamInMatchPOs.add(matchPO.getAwayTeamStats());
//		      teamInMatchPOs.add(matchPO.getHomeTeamStats());
//		      
////		      matchDataService.add_teamInMatch(matchPO.getAwayTeamStats());
////		      matchDataService.add_teamInMatch(matchPO.getHomeTeamStats());
////		      matchDataService.addMatch_basic(matchBasicPO);
//				
//			System.out.println(matchPO.getMatchDate()+","+matchPO.getGameID());
//		}
//	}
//}
public static MatchBasicPO changeToMatchBasicPO(MatchPO po){
	TypeOfMatch matchType=po.getTypeOfMatch();//比赛类型
	String gameID=po.getGameID();
	String matchDate=po.getMatchDate();//年月日
	String matchSeason=changeMatchDateToSeason(matchDate);//赛季
	String awayTeamID=po.getAwayTeamID();
	String homeTeamID=po.getHomeTeamID();
	String awayTeam=po.getAwayTeam();//客场球队
	String homeTeam=po.getHomeTeam();//主场球队
	int totalPoints_homeTeam=po.getHomeTeamTotalScore();//总比分（客场在前主场在后）
	int firstPoints_homeTeam=po.getHomeTeamScore1();//第一比分（客场在前主场在后）
	int secondPoints_homeTeam=po.getHomeTeamScore2();//第二比分（客场在前主场在后）
	int thirdPoints_homeTeam=po.getHomeTeamScore3();//第三比分（客场在前主场在后）
	int forthPoints_homeTeam=po.getHomeTeamScore4();//第四比分（客场在前主场在后）
	int totalPoints_awayTeam=po.getAwayTeamTotalScore();//总比分（客场在前主场在后）
	int firstPoints_awayTeam=po.getAwayTeamScore1();//第一比分（客场在前主场在后）
	int secondPoints_awayTeam=po.getAwayTeamScore2();//第二比分（客场在前主场在后）
	int thirdPoints_awayTeam=po.getAwayTeamScore3();//第三比分（客场在前主场在后）
	int forthPoints_awayTeam=po.getAwayTeamScore4();//第四比分（客场在前主场在后）
	MatchBasicPO matchBasicPO=new MatchBasicPO(gameID, matchType, matchSeason, matchDate, awayTeamID, awayTeam, homeTeamID, homeTeam, totalPoints_awayTeam, totalPoints_homeTeam, firstPoints_awayTeam, firstPoints_homeTeam, secondPoints_awayTeam, secondPoints_homeTeam, thirdPoints_awayTeam, thirdPoints_homeTeam, forthPoints_awayTeam, forthPoints_homeTeam);
	return matchBasicPO;
}
public static String changeMatchDateToSeason(String matchDate){
	String []s=matchDate.split("-");
	String yearStr=s[0];
	String monthStr=s[1];
	int month=Integer.parseInt(monthStr);
	String season="";
	if(month>=9){
		int y=Integer.parseInt(yearStr)+1;
		season=yearStr+"-"+(y+"").substring(2);
	}else{
		int yy=Integer.parseInt(yearStr)-1;
		season=(yy+"")+"-"+yearStr.substring(2);
	}
	return season;
}
public static void main(String []args) throws IOException{
//	String fileDir="D://nba_data/match_final";
//	File file=new File(fileDir);
//	String []files=file.list();
////	ArrayList<String>
//	for(int i=0;i<files.length;i++){
//		read_one_match(fileDir+"/"+files[i]);
//	}
	String fileName="D://nba_data/match_final/2015-05-25-0041400314.txt";
	MatchPO po=read_one_match(fileName);
	TeamInMatchPO team1=po.getHomeTeamStats();
	System.out.println(team1.getIsWin());
}
}
