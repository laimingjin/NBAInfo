package dataServiceImp;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import po.MatchBasicPO;
import po.MatchPO;
import po.PlayerInMatchPO;
import po.TeamInMatchPO;
import readFiles.Match_read;
import dataService.MatchDataService;

public class Match {
	  MatchDataService matchDataService=null;
		public Match() throws DatabaseException {
			 matchDataService=new MatchDataServiceImp();
		}
public void addMatchBasic() throws IOException, SQLException{

	String fileDir="/Users/mj/Downloads/nba_data/match_final";///////////////////////这里改成你的数据所在的位置
	
	File file=new File(fileDir);
	String []files=file.list();
	ArrayList<MatchPO>matchPOs=new ArrayList<MatchPO>();
	ArrayList<MatchBasicPO>matchBasicPOs=new ArrayList<MatchBasicPO>();
	ArrayList<PlayerInMatchPO>playerInMatchPOs=new ArrayList<PlayerInMatchPO>();
	ArrayList<TeamInMatchPO>teamInMatchPOs=new ArrayList<TeamInMatchPO>();
	System.out.println(files.length);
	for(int i=0;i<files.length;i++){
		String fileName=fileDir+"/"+files[i];
		MatchPO matchPO=Match_read.read_one_match(fileName);
		if(matchPO!=null){
			matchPOs.add(matchPO);
			MatchBasicPO matchBasicPO=Match_read.changeToMatchBasicPO(matchPO);
			if(matchBasicPO!=null){
//					matchBasicPOs.add(matchBasicPO);
					 matchDataService.addMatch_basic(matchBasicPO);
			}
		
			ArrayList<PlayerInMatchPO>playerList1=matchPO.getHomeTeamPlayers();
			ArrayList<PlayerInMatchPO>playerList2=matchPO.getAwayTeamPlayers();
			if(playerList1.size()>0){
				for(int j=0;j<playerList1.size();j++){
//			    	  playerInMatchPOs.add(playerList1.get(j));
			    	  matchDataService.add_playerInMatch(playerList1.get(j));
			      }
			}
	      if(playerList2.size()>0){
	    	   for(int k=0;k<playerList2.size();k++){
//	    	  playerInMatchPOs.add(playerList2.get(k));
	    	  matchDataService.add_playerInMatch(playerList2.get(k));
	      }
	      }
	     if(matchPO.getAwayTeamStats()!=null){
//	    	 teamInMatchPOs.add(matchPO.getAwayTeamStats());
	    	 matchDataService.add_teamInMatch(matchPO.getAwayTeamStats());
	     }
	      if(matchPO.getHomeTeamStats()!=null){
//	    	  teamInMatchPOs.add(matchPO.getHomeTeamStats());
		      matchDataService.add_teamInMatch(matchPO.getHomeTeamStats());
	      }
	     
		}
		System.out.println(i);
	}
}
public void test() throws IOException, SQLException{
	
	ArrayList<MatchPO>matchPOs=new ArrayList<MatchPO>();
	ArrayList<MatchBasicPO>matchBasicPOs=new ArrayList<MatchBasicPO>();
	ArrayList<PlayerInMatchPO>playerInMatchPOs=new ArrayList<PlayerInMatchPO>();
	ArrayList<TeamInMatchPO>teamInMatchPOs=new ArrayList<TeamInMatchPO>();
		MatchPO matchPO=Match_read.read_one_match("D://nba_data/match_final/1956-03-08-0025500271.txt");
		if(matchPO!=null){
			matchPOs.add(matchPO);
			MatchBasicPO matchBasicPO=Match_read.changeToMatchBasicPO(matchPO);
			if(matchBasicPO!=null){
					matchBasicPOs.add(matchBasicPO);
					 matchDataService.addMatch_basic(matchBasicPO);
			}
		
			ArrayList<PlayerInMatchPO>playerList1=matchPO.getHomeTeamPlayers();
			ArrayList<PlayerInMatchPO>playerList2=matchPO.getAwayTeamPlayers();
			if(playerList1.size()>0){
				for(int j=0;j<playerList1.size();j++){
			    	  playerInMatchPOs.add(playerList1.get(j));
			    	  matchDataService.add_playerInMatch(playerList1.get(j));
			      }
			}
	      if(playerList2.size()>0){
	    	   for(int k=0;k<playerList2.size();k++){
	    	  playerInMatchPOs.add(playerList2.get(k));
	    	  matchDataService.add_playerInMatch(playerList2.get(k));
	      }
	      }
	     if(matchPO.getAwayTeamStats()!=null){
	    	 teamInMatchPOs.add(matchPO.getAwayTeamStats());
	    	 matchDataService.add_teamInMatch(matchPO.getAwayTeamStats());
	     }
	      if(matchPO.getHomeTeamStats()!=null){
	    	  teamInMatchPOs.add(matchPO.getHomeTeamStats());
		      matchDataService.add_teamInMatch(matchPO.getHomeTeamStats());
	      }
	     
	     
			
		
	}
}
public static void main(String[]args) throws DatabaseException, IOException, SQLException{
	Match match =new Match();
	match.addMatchBasic();
	
//	match.test();
	
//	MatchPO matchPO=Match_read.read_one_match("D://nba_data/match_final/1947-12-21-0024700061.txt");
//	if(matchPO!=null){
//		MatchBasicPO matchBasicPO=Match_read.changeToMatchBasicPO(matchPO);
//		ArrayList<PlayerInMatchPO>playerList1=matchPO.getHomeTeamPlayers();
//		ArrayList<PlayerInMatchPO>playerList2=matchPO.getAwayTeamPlayers();
//      for(int j=0;j<playerList1.size();j++){
////    	  playerInMatchPOs.add(playerList1.get(j));
//    	  matchDataService.add_playerInMatch(playerList1.get(j));
//      }
//      for(int k=0;k<playerList2.size();k++){
////    	  playerInMatchPOs.add(playerList2.get(k));
//    	  matchDataService.add_playerInMatch(playerList2.get(k));
//      }
////      teamInMatchPOs.add(matchPO.getAwayTeamStats());
////      teamInMatchPOs.add(matchPO.getHomeTeamStats());
//      
//      matchDataService.add_teamInMatch(matchPO.getAwayTeamStats());
//      matchDataService.add_teamInMatch(matchPO.getHomeTeamStats());
//      matchDataService.addMatch_basic(matchBasicPO);
		
//}
	
}

}