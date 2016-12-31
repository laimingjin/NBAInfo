package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import enumerate.TypeOfMatch;
import po.PlayerTotalPO;
import po.TeamTotalPO;

public class TeamLowData_regular {
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
	TypeOfMatch matchType=TypeOfMatch.REGULAR;//比赛类型
	while((line=br.readLine())!=null){
		String []infos=line.split(",");
		
		String matchSeason=infos[0];//赛季
		String teamID=infos[1];
		String teamName=infos[2];//球队名称全名
		int GP=NumberFormatChange.changeToInt(infos[3]);//出场数
		int WIN=NumberFormatChange.changeToInt(infos[4]);//胜利场数
		int LOSS=NumberFormatChange.changeToInt(infos[5]);
		int PTS=NumberFormatChange.changeToInt(infos[31]);//总得分
		int FGA=NumberFormatChange.changeToInt(infos[15]);//投篮出手数
		int FGM=NumberFormatChange.changeToInt(infos[14]);//投篮命中数
		String FGPer=NumberFormatChange.changeToPer(infos[16]);//投篮命中率
		int TPA=NumberFormatChange.changeToInt(infos[18]);//三分出手数//原缩写为3PA
		int TPM=NumberFormatChange.changeToInt(infos[17]);//三分命中数//3PM
		String TPPer=NumberFormatChange.changeToPer(infos[19]);//三分命中率//3P%
		int FTA=NumberFormatChange.changeToInt(infos[21]);//罚球出手数
		int FTM=NumberFormatChange.changeToInt(infos[20]);//罚球命中数
		String FTPer=NumberFormatChange.changeToPer(infos[22]);//罚球命中率
		int REB=NumberFormatChange.changeToInt(infos[25]);//篮板数
		int OREB=NumberFormatChange.changeToInt(infos[23]);//进攻篮板//前场篮板
		int DREB=NumberFormatChange.changeToInt(infos[24]);//防守篮板//后场篮板
		int AST=NumberFormatChange.changeToInt(infos[26]);//助攻数
		int STL=NumberFormatChange.changeToInt(infos[28]);//抢断数
		int BLK=NumberFormatChange.changeToInt(infos[30]);//盖帽数
		int TO=NumberFormatChange.changeToInt(infos[29]);//失误数
		int PF=NumberFormatChange.changeToInt(infos[27]);//犯规数
TeamTotalPO po=new TeamTotalPO(matchType, matchSeason, teamID, teamName, GP, WIN, LOSS, PTS, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TO, PF);
list.add(po);
	}
	return list;
}
}
