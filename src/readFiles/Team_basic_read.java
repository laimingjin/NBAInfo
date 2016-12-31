package readFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import po.TeamBasicPO;

public class Team_basic_read {
public static ArrayList<TeamBasicPO>read(String fileName) throws NumberFormatException, IOException{
	ArrayList<TeamBasicPO> list=new ArrayList<TeamBasicPO>();
	 String teamName="";
		String abbreviation="";
		String location="";
		String zone="";
		String team_partition="";
		String homeField="";
		int time_setUp=0;
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		String line=null;
		while((line=br.readLine())!=null){
			if(line.startsWith("║")){
				String mass[]=line.split("║");
				String contents[]=mass[1].split("│");
				teamName=contents[0].trim();
				 abbreviation=contents[1].trim();
				 String teamID=getTeamID(abbreviation);
				 location=contents[2].trim();
				 zone=contents[3].trim();
				 team_partition=contents[4].trim();
				 homeField=contents[5].trim();
				 
				time_setUp=Integer.parseInt(contents[6].trim());
				
			list.add(new TeamBasicPO(teamID,teamName, abbreviation, location, zone, team_partition, homeField, time_setUp));	
				
}
}
		return list;
}
public static String getTeamID(String teamName) throws IOException{
	BufferedReader br=new BufferedReader(new FileReader("D://nba_data/teamPair_name_id.out"));
	String line=null;
	while((line=br.readLine())!=null){
		String []infos=line.split(",");
		if(infos[1].equals(teamName)){
			return infos[0];
		}
	}
	return null;
}

}