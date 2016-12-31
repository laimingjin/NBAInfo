package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import enumerate.TypeOfMatch;
import po.PlayerHighPO;
import po.PlayerPair;
import po.PlayerTotalPO;

public class PlayerHighData_regular {
	public static ArrayList<PlayerPair>pairs=FindPlayer.getPlayerPairs("D://nba_data/playerPair_name_id.out");
	public static ArrayList<PlayerHighPO>read_all(String fileDir) throws IOException{
		ArrayList<PlayerHighPO> list=new ArrayList<PlayerHighPO>();
		File file=new File(fileDir);
		String infos[]=file.list();
		for(int i=0;i<infos.length;i++){
			ArrayList<PlayerHighPO> pos=read(infos[i].substring(0, infos[i].length()-4),fileDir+"/"+infos[i]);
			for(int j=0;j<pos.size();j++){
				list.add(pos.get(j));
			}
		}
		return list;
	} 
	public static ArrayList<PlayerHighPO>read(String playerID,String fileName)throws IOException{
		ArrayList<PlayerHighPO> list=new ArrayList<PlayerHighPO>();
		TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
		String matchSeason="";//赛季
		
		String playerName="";//球员名称
		String teamID="";
		String teamName="";//球队名称缩写
		int GP=0;
		double MIN=0;
		int WIN=0;
		int LOSS=0;
		double W_PCT=0;
		double OffRtg=0;
		double DefRtg=0;
		double NetRtg=0;
		double ASTPER=0;
		double AST_TO=0;
		double ASTRatio=0;
		double OREBPer=0;
		double DREBPer=0;
		double REBPer=0;
		double TORatio=0;
		double EFGPer=0;
		double TSPer=0;
		double USGPer=0;
		double PACE=0;
		double PIE=0;
		
		
		for(int i=0;i<pairs.size();i++){
			if(pairs.get(i).getID().equals(playerID)){
				playerName=pairs.get(i).getPlayerName();
				break;
			}
		}
		BufferedReader lowbr=new BufferedReader(new FileReader("D://nba_data/player_team_pair.out"));
		String lowline=lowbr.readLine();
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		String line=null;
		while((line=br.readLine())!=null){
//			System.out.println(line);
			if(line.length()>1){
				String []infos=line.split(",");
				matchSeason=infos[0].substring(1, infos[0].length()-1);
				
				GP=NumberFormatChange.changeToInt(infos[1]);
				WIN=NumberFormatChange.changeToInt(infos[2]);
				LOSS=NumberFormatChange.changeToInt(infos[3]);
				W_PCT=NumberFormatChange.changeToDouble(infos[4]);
				MIN=NumberFormatChange.changeToDouble(infos[5]);
				OffRtg=NumberFormatChange.changeToDouble(infos[6]);
				DefRtg=NumberFormatChange.changeToDouble(infos[7]);
				NetRtg=NumberFormatChange.changeToDouble(infos[8]);
				ASTPER=NumberFormatChange.changeToDouble(infos[9]);
				AST_TO=NumberFormatChange.changeToDouble(infos[10]);
				ASTRatio=NumberFormatChange.changeToDouble(infos[11]);
				OREBPer=NumberFormatChange.changeToDouble(infos[12]);
				DREBPer=NumberFormatChange.changeToDouble(infos[13]);
				REBPer=NumberFormatChange.changeToDouble(infos[14]);
				TORatio=NumberFormatChange.changeToDouble(infos[15]);
				EFGPer=NumberFormatChange.changeToDouble(infos[16]);
				TSPer=NumberFormatChange.changeToDouble(infos[17]);
				USGPer=NumberFormatChange.changeToDouble(infos[18]);
				PACE=NumberFormatChange.changeToDouble(infos[19]);
				PIE=NumberFormatChange.changeToDouble(infos[20]);
				
				while((lowline=lowbr.readLine())!=null){
					String tt[]=lowline.split(",");
					if(tt[0].equals(playerID) && (tt[3].substring(0, 7).equals(matchSeason))){
						teamID=tt[1];
						teamName=tt[2];
						break;
					}
				}
				
				PlayerHighPO po=new PlayerHighPO(typeOfMatch, matchSeason, playerID, playerName, teamID, teamName, GP, MIN, WIN, LOSS, W_PCT, OffRtg, DefRtg, NetRtg, ASTPER, AST_TO, ASTRatio, OREBPer, DREBPer, REBPer, TORatio, EFGPer, TSPer, USGPer, PACE, PIE);
				list.add(po);
			}
		}
		return list;
	}
//	public static void main(String[]args) throws IOException{
//		ArrayList<PlayerHighPO>pos=PlayerHighData_regular.read_all("D://nba_data/playerHighData_regular");
//	   for(int i=0;i<pos.size();i++){
//		   System.out.println(pos.get(i).getPlayerID());
//	   }
//	}
}
