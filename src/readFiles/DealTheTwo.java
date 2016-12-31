package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import po.PlayerInMatchPO;
import po.Player_for_update;

public class DealTheTwo {
public static ArrayList<Player_for_update>get_new_pos(String fileName) throws IOException{
ArrayList<Player_for_update>pos=new ArrayList<Player_for_update>();
BufferedReader br=new BufferedReader(new FileReader(fileName));
String line=null;
while((line=br.readLine())!=null){
	String infos[]=line.split(",");
	String gameID=infos[0];
	
	String teamID=infos[1];
	String teamName=infos[2];
	String playerID=infos[3];
	String playerName=infos[4];
	String position=infos[5];
	int isGS=0;// 是否首发
	if(!position.equals("")){
		isGS=1;
	}
//	System.out.println(fileName);
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
	Player_for_update po=new Player_for_update(gameID, playerID, playerName, teamName, position, isGS, MIN, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF, PTS, PLUS_MINUS);
	pos.add(po);
	
}
return pos;
}
//public static void main(String []args) throws IOException{
//	DealTheTwo deal=new DealTheTwo();
//	ArrayList<Player_for_update>pos=deal.get_new_pos("D://nba_data/dealed.txt");
////	System.out.println(pos.size());
//	System.out.println(pos.get(3).getPTS()+","+pos.get(3).getGameID());
//}
}