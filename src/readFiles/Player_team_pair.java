package readFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import po.Player_team_pairPO;

public class Player_team_pair {
public static ArrayList<Player_team_pairPO>get_pairs(String fileDir) throws IOException{
	File file=new File(fileDir);
	String []files=file.list();
	ArrayList< Player_team_pairPO>list=new ArrayList<Player_team_pairPO>();
	for(int i=0;i<files.length;i++){
		String playerID=files[i].substring(0, files[i].length()-4);
		String fileName=fileDir+"/"+files[i];
		ArrayList<Player_team_pairPO>temp=get_pair_from_file(playerID, fileName);
		for(int j=0;j<temp.size();j++){
			list.add(temp.get(j));
		}
	}
	return list;
}
public static ArrayList<Player_team_pairPO>get_pair_from_file(String playerID,String fileName) throws IOException{
	ArrayList<Player_team_pairPO>list=new ArrayList<Player_team_pairPO>();
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=br.readLine();
	while((line=br.readLine())!=null){
		if(line.startsWith("SeasonTotalsPostSeason")){
			break;
		}
		String infos[]=line.split(",");
		String teamID=infos[3];
		String teamName=infos[4].substring(1, infos[4].length()-1);
		String matchSeason=infos[1].substring(1, infos[1].length()-1);
		Player_team_pairPO pair=new Player_team_pairPO(playerID,teamID,teamName,matchSeason);
		list.add(pair);
	}
	return list;
}
public static void main(String[]args) throws IOException{
	ArrayList<Player_team_pairPO>list=get_pairs("D://nba_data/playerLowData");
	BufferedWriter bw=new BufferedWriter(new FileWriter("D://nba_data/player_team_pair.out"));
	
	for(int i=0;i<list.size();i++){
		Player_team_pairPO po=list.get(i);
		bw.write(po.getPlayerID()+","+po.getTeamID()+","+po.getTeamName()+","+po.getMatchSeason());
		bw.write("\t\n");
	}
	bw.flush();
	bw.close();
}
}
