package vo;

import java.util.ArrayList;

import po.MembersPO;

/**
 *@author:小春
 *@date:2015年5月19日下午10:24:31
 *@version
 */

public class MembersVO {
    String teamID;
//	String teamName;//球队全名
     String matchSeason;//赛季
//     String nameOfMembers;//球员名称，用“，”隔开
     ArrayList<String> playerIDs;
     String chiefCoach;//主教练
    
     public MembersVO(String teamID, String matchSeason,
			ArrayList<String> playerIDs, String chiefCoach) {
		super();
		this.teamID = teamID;
		this.matchSeason = matchSeason;
		this.playerIDs = playerIDs;
		this.chiefCoach = chiefCoach;
	}
     public MembersVO(MembersPO po){
    	 super();
 		this.teamID = po.getTeamID();
 		this.matchSeason = po.getMatchSeason();
 		this.playerIDs = po.getPlayerIDs();
 		this.chiefCoach = po.getChiefCoach();
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
