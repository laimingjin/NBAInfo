package readFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import enumerate.TypeOfMatch;
import po.PlayerHighPO;
import po.PlayerPair;
import po.PlayerTotalPO;

public class PlayerLowData_regular_read {
public static ArrayList<PlayerPair>pairs=FindPlayer.getPlayerPairs("D://nba_data/playerPair_name_id.out");
public static ArrayList<PlayerTotalPO>read_all(String fileDir) throws IOException{
	ArrayList<PlayerTotalPO> list=new ArrayList<PlayerTotalPO>();
	File file=new File(fileDir);
	String infos[]=file.list();
	for(int i=0;i<infos.length;i++){
		ArrayList<PlayerTotalPO> pos=read(fileDir+"/"+infos[i]);
		for(int j=0;j<pos.size();j++){
			list.add(pos.get(j));
		}
	}
	return list;
}
public static ArrayList<PlayerTotalPO> read(String fileName) throws IOException{
	ArrayList<PlayerTotalPO> list=new ArrayList<PlayerTotalPO>();
	TypeOfMatch matchType=TypeOfMatch.REGULAR;//比赛类型
	String matchSeason="";//赛季
	String playerName="";//球员名称
	String teamID="";
	String teamName="";//球队名称缩写
	int numOnCourt=0;//出场数
	int numFirstOn=0;//首发出场数
	int timeOnCourt=0;//出场时间
//	int WIN=0;//胜利场数///////////////////////////
//	int LOSE=0;
	int score=0;//总得分
	int shotRelease=0;//投篮出手数
	int shoting=0;//投篮命中数
	String shotPer="";//投篮命中率
	int threePoint=0;//三分出手数//原缩写为3PA
	int threePointShot=0;//三分命中数//3PM
	String threePointPer="";//三分命中率//3P%
	int freeThrow=0;//罚球出手数
	int freeThrowShot=0;//罚球命中数
	String freeThrowPer="";//罚球命中率
	int rebound=0;//篮板数
	int offensiveRebound=0;//进攻篮板//前场篮板
	int defensiveRebound=0;//防守篮板//后场篮板
	int assist=0;//助攻数
	int steal=0;//抢断数
	int block=0;//盖帽数
	int fault=0;//失误数
	int foul=0;//犯规数
	double EffPer=0;
	
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=br.readLine();
	line=br.readLine();
	if(line.startsWith("SeasonTotalsPostSeason")){
		return list;
	}
	String info1[]=line.split(",");
	String playerID=info1[0];
	for(int i=0;i<pairs.size();i++){
		if(pairs.get(i).getID().equals(playerID)){
			playerName=pairs.get(i).getPlayerName();
			break;
		}
	}
	//System.out.println(fileName);
	matchSeason=info1[1].substring(1, info1[1].length()-1);
	teamID=info1[3];
	teamName=info1[4].substring(1, info1[4].length()-1);
	numOnCourt=NumberFormatChange.changeToInt(info1[6]);
	numFirstOn=NumberFormatChange.changeToInt(info1[7]);
	timeOnCourt=NumberFormatChange.changeToInt(info1[8]);
	shoting=NumberFormatChange.changeToInt(info1[9]);
	shotRelease=NumberFormatChange.changeToInt(info1[10]);
	shotPer=NumberFormatChange.changeToPer(info1[11]);
	threePointShot=NumberFormatChange.changeToInt(info1[12]);
	threePoint=NumberFormatChange.changeToInt(info1[13]);
	threePointPer=NumberFormatChange.changeToPer(info1[14]);
	freeThrowShot=NumberFormatChange.changeToInt(info1[15]);
	freeThrow=NumberFormatChange.changeToInt(info1[16]);
	freeThrowPer=NumberFormatChange.changeToPer(info1[17]);
	offensiveRebound=NumberFormatChange.changeToInt(info1[18]);
	defensiveRebound=NumberFormatChange.changeToInt(info1[19]);
	rebound=NumberFormatChange.changeToInt(info1[20]);
	assist=NumberFormatChange.changeToInt(info1[21]);
	steal=NumberFormatChange.changeToInt(info1[22]);
	block=NumberFormatChange.changeToInt(info1[23]);
	fault=NumberFormatChange.changeToInt(info1[24]);
	foul=NumberFormatChange.changeToInt(info1[25]);
	score=NumberFormatChange.changeToInt(info1[26]);
	EffPer=((score+rebound+assist+steal+block)-(shotRelease-shoting)-(freeThrow-freeThrowShot)-fault)*1.0/numOnCourt;
	list.add(new PlayerTotalPO(matchType, matchSeason, playerID,playerName,teamID, teamName, numOnCourt, numFirstOn, timeOnCourt, score, shotRelease, shoting, shotPer, threePoint, threePointShot, threePointPer, freeThrow, freeThrowShot, freeThrowPer, rebound, offensiveRebound, defensiveRebound, assist, steal, block, fault, foul,EffPer));
	while((line=br.readLine())!=null  ){
		if(line.startsWith("SeasonTotalsPostSeason")){
			break;
		}
		String []info=line.split(",");
		matchSeason=info[1].substring(1, info[1].length()-1);
		teamID=info[3];
		teamName=info[4].substring(1, info[4].length()-1);
		numOnCourt=NumberFormatChange.changeToInt(info[6]);
		numFirstOn=NumberFormatChange.changeToInt(info[7]);
		timeOnCourt=NumberFormatChange.changeToInt(info[8]);
		shoting=NumberFormatChange.changeToInt(info[9]);
		shotRelease=NumberFormatChange.changeToInt(info[10]);
		shotPer=NumberFormatChange.changeToPer(info[11]);
		threePointShot=NumberFormatChange.changeToInt(info[12]);
		threePoint=NumberFormatChange.changeToInt(info[13]);
		threePointPer=NumberFormatChange.changeToPer(info[14]);
		freeThrowShot=NumberFormatChange.changeToInt(info[15]);
		freeThrow=NumberFormatChange.changeToInt(info[16]);
		freeThrowPer=NumberFormatChange.changeToPer(info[17]);
		offensiveRebound=NumberFormatChange.changeToInt(info[18]);
		defensiveRebound=NumberFormatChange.changeToInt(info[19]);
		rebound=NumberFormatChange.changeToInt(info[20]);
		assist=NumberFormatChange.changeToInt(info[21]);
		steal=NumberFormatChange.changeToInt(info[22]);
		block=NumberFormatChange.changeToInt(info[23]);
		fault=NumberFormatChange.changeToInt(info[24]);
		foul=NumberFormatChange.changeToInt(info[25]);
		score=NumberFormatChange.changeToInt(info[26]);
		EffPer=((score+rebound+assist+steal+block)-(shotRelease-shoting)-(freeThrow-freeThrowShot)-fault)*1.0/numOnCourt;
		list.add(new PlayerTotalPO(matchType, matchSeason,playerID, playerName,teamID, teamName, numOnCourt, numFirstOn, timeOnCourt, score, shotRelease, shoting, shotPer, threePoint, threePointShot, threePointPer, freeThrow, freeThrowShot, freeThrowPer, rebound, offensiveRebound, defensiveRebound, assist, steal, block, fault, foul,EffPer));
	}
	return list;
}
//public static void main(String[]args) throws IOException{
//	ArrayList<PlayerTotalPO> pos=read_all("D://nba_data/playerLowData_offAndStar");
//	String line="";
//	BufferedWriter bw=new BufferedWriter(new FileWriter("D://playeroff.txt"));
//	for(int i=0;i<pos.size();i++){
//		line=pos.get(i).getPlayerID()+","+pos.get(i).getMatchSeason();
//		System.out.println(line);
//		bw.write(line);
//		bw.write("\n\t");
//	}
//}
//public ArrayList<PlayerHighPO> getHighInfos(ArrayList<PlayerTotalPO> lowPOs){
//	 ArrayList<PlayerHighPO> highPOs=new  ArrayList<PlayerHighPO>();
//	 
//	 
//	 return highPOs;
//}
//public static void main(String[]args) throws IOException{
//	ArrayList<PlayerTotalPO> pos=PlayerLowData_regular_read.read("D://nba_data/playerLowData/24.txt");
//System.out.println(pos.get(1).getPTS());
//
//}
}
