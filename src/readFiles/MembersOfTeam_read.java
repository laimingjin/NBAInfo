package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import po.MembersPO;

public class MembersOfTeam_read {
public static ArrayList<MembersPO>read_all(String fileDir)throws IOException{
	ArrayList<MembersPO>list=new ArrayList<MembersPO>();
	File file=new File(fileDir);
	String files[]=file.list();
	for(int i=0;i<files.length;i++){
		String teamID=files[i].substring(0, 10);
		String fileName=fileDir+"/"+files[i];
		ArrayList<MembersPO>temp=read_one_team(fileName, teamID);
		if(temp.size()>0){
			for(int j=0;j<temp.size();j++){
				list.add(temp.get(j));
			}
		}
	}
	
	return list;
}
public static ArrayList<MembersPO>read_one_team(String fileName,String teamID)throws IOException{
	ArrayList<MembersPO> list=new ArrayList<MembersPO>();
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=null;
	while((line=br.readLine())!=null){
		String matchSeason=line.substring(0, 7);
		line=br.readLine();
		String infos[]=line.split(",");
		
	     ArrayList<String> playerIDs=new ArrayList<String>();
	     String chiefCoach=infos[0];//主教练
	     int size=infos.length-1;//numOfPlayers
	     for(int i=1;i<size+1;i++){
	    	 playerIDs.add(infos[i]);
	     }
	     list.add(new MembersPO(teamID, matchSeason, playerIDs, chiefCoach));
	}
	return list;
}
}
