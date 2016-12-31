package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import enumerate.TypeOfMatch;
import po.PlayerTotalPO;
import po.TeamTotalPO;
////////////////有个问题  季后赛的数据都是场均的该怎么办

public class TeamLowData_playeroff {
public static ArrayList<TeamTotalPO>read_all(String fileDir) throws IOException{
	ArrayList<TeamTotalPO>list=new ArrayList<TeamTotalPO>();
	File file=new File(fileDir);
	String infos[]=file.list();
	for(int i=0;i<infos.length;i++){
		ArrayList<TeamTotalPO> pos=read(fileDir+"/"+infos[i]);
		for(int j=0;j<pos.size();j++){
			list.add(pos.get(j));
		}
	}
	return list;
}
public static ArrayList<TeamTotalPO>read(String fileName) throws IOException{
	ArrayList<TeamTotalPO>list=new ArrayList<TeamTotalPO>();
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=null;
	TypeOfMatch matchType=TypeOfMatch.PLAYOFF;//比赛类型
	while((line=br.readLine())!=null){
		String []infos=line.split(",");
		
		String matchSeason=infos[0];//赛季
		String teamID=infos[1];
		String teamName=infos[2];//球队名称全名
		int GP=NumberFormatChange.changeToInt(infos[3]);//出场数
		int WIN=NumberFormatChange.changeToInt(infos[4]);//胜利场数
		int LOSS=NumberFormatChange.changeToInt(infos[5]);
		int PTS=NumberFormatChange.changeAvgToTotal(infos[6],GP);//总得分
		int FGA=NumberFormatChange.changeAvgToTotal(infos[7],GP);//投篮出手数
		int FGM=NumberFormatChange.changeAvgToTotal(infos[8],GP);//投篮命中数
		String FGPer=NumberFormatChange.changeToPer(infos[9]);//投篮命中率
		int TPA=NumberFormatChange.changeAvgToTotal(infos[10],GP);//三分出手数//原缩写为3PA
		int TPM=NumberFormatChange.changeAvgToTotal(infos[11],GP);//三分命中数//3PM
		String TPPer=NumberFormatChange.changeToPer(infos[12]);//三分命中率//3P%
		int FTA=NumberFormatChange.changeAvgToTotal(infos[13],GP);//罚球出手数
		int FTM=NumberFormatChange.changeAvgToTotal(infos[14],GP);//罚球命中数
		String FTPer=NumberFormatChange.changeToPer(infos[15]);//罚球命中率
		int REB=NumberFormatChange.changeAvgToTotal(infos[16],GP);//篮板数
		int OREB=NumberFormatChange.changeAvgToTotal(infos[17],GP);//进攻篮板//前场篮板
		int DREB=NumberFormatChange.changeAvgToTotal(infos[18],GP);//防守篮板//后场篮板
		int AST=NumberFormatChange.changeAvgToTotal(infos[19],GP);//助攻数
		int STL=NumberFormatChange.changeAvgToTotal(infos[20],GP);//抢断数
		int BLK=NumberFormatChange.changeAvgToTotal(infos[21],GP);//盖帽数
		int TO=NumberFormatChange.changeAvgToTotal(infos[22],GP);//失误数
		int PF=NumberFormatChange.changeAvgToTotal(infos[23],GP);//犯规数
TeamTotalPO po=new TeamTotalPO(matchType, matchSeason, teamID, teamName, GP, WIN, LOSS, PTS, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TO, PF);
list.add(po);
	}
	return list;
}
}
