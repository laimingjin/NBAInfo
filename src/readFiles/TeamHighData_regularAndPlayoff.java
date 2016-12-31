package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import enumerate.TypeOfMatch;
import po.TeamHighPO;
import po.TeamTotalPO;

public class TeamHighData_regularAndPlayoff {
	public static ArrayList<TeamHighPO>read_all(String fileDir,TypeOfMatch typeOfMatch) throws IOException{
		ArrayList<TeamHighPO>list=new ArrayList<TeamHighPO>();
		File file=new File(fileDir);
		String infos[]=file.list();
		for(int i=0;i<infos.length;i++){
			ArrayList<TeamHighPO> pos=read(fileDir+"/"+infos[i],typeOfMatch);
			for(int j=0;j<pos.size();j++){
				list.add(pos.get(j));
			}
		}
		return list;
	}
	public static ArrayList<TeamHighPO>read(String fileName,TypeOfMatch typeOfMatch) throws IOException{
		ArrayList<TeamHighPO>list=new ArrayList<TeamHighPO>();
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		String line=null;
//		TypeOfMatch matchType=TypeOfMatch.REGULAR;//比赛类型
		while((line=br.readLine())!=null){
			String []infos=line.split(",");
			
			String matchSeason=infos[0];//赛季
			String teamID=infos[1];
			String teamName=infos[2];//球队名称缩写
			int GP=NumberFormatChange.changeToInt(infos[3]);
			int MIN=NumberFormatChange.changeToInt(infos[7]);
			double OffRtg=NumberFormatChange.changeToDouble(infos[8]);
			double DefRtg=NumberFormatChange.changeToDouble(infos[9]);
			double NetRtg=NumberFormatChange.changeToDouble(infos[10]);
			double ASTPER=NumberFormatChange.changeToDouble(infos[11]);
			double AST_TO=NumberFormatChange.changeToDouble(infos[12]);
			double ASTRatio=NumberFormatChange.changeToDouble(infos[13]);
			double OREBPer=NumberFormatChange.changeToDouble(infos[14]);
			double DREBPer=NumberFormatChange.changeToDouble(infos[15]);
			double REBPer=NumberFormatChange.changeToDouble(infos[16]);
			double TORatio=NumberFormatChange.changeToDouble(infos[17]);
			double EFGPer=NumberFormatChange.changeToDouble(infos[18]);
			double TSPer=NumberFormatChange.changeToDouble(infos[19]);
			double PACE=NumberFormatChange.changeToDouble(infos[20]);
			double PIE=NumberFormatChange.changeToDouble(infos[21]);
			TeamHighPO po=new TeamHighPO(typeOfMatch, matchSeason, teamID, teamName, GP, MIN, OffRtg, DefRtg, NetRtg, ASTPER, AST_TO, ASTRatio, OREBPer, DREBPer, REBPer, TORatio, EFGPer, TSPer, PACE, PIE);
			list.add(po);
		}
		return list;
	}
}
