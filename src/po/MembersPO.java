package po;

import java.util.ArrayList;

/**
 *@author:小春
 *@date:2015年5月19日下午10:18:29
 *球队中球员
 *@version
 */

public class MembersPO {
    String teamID;
//	String teamName;//球队全名
     String matchSeason;//赛季
//     String nameOfMembers;//球员名称，用“，”隔开
     ArrayList<String> playerIDs;
     String chiefCoach;//主教练
    
     public MembersPO(String teamID, String matchSeason,
			ArrayList<String> playerIDs, String chiefCoach) {
		super();
		this.teamID = teamID;
		this.matchSeason = matchSeason;
		this.playerIDs = playerIDs;
		this.chiefCoach = chiefCoach;
	}
	public String getTeamID(){
    	 return teamID;
     }
	
	public String getMatchSeason() {
		return matchSeason;
	}
	
	public ArrayList<String> getPlayerIDs() {
		return playerIDs;
	}
	public String getChiefCoach() {
		return chiefCoach;
	}
     
}
