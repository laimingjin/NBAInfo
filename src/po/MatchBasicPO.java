package po;

import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午11:41:23
 *@version
 */

public class MatchBasicPO {
	
	TypeOfMatch matchType;//比赛类型
	String gameID;
	String matchSeason;//赛季
	String matchDate;//年月日
	String awayTeamID;
	String homeTeamID;
	String awayTeam;//客场球队
	String homeTeam;//主场球队
	int totalPoints_homeTeam;//总比分（客场在前主场在后）
	int firstPoints_homeTeam;//第一比分（客场在前主场在后）
	int secondPoints_homeTeam;//第二比分（客场在前主场在后）
	int thirdPoints_homeTeam;//第三比分（客场在前主场在后）
	int forthPoints_homeTeam;//第四比分（客场在前主场在后）
	int totalPoints_awayTeam;//总比分（客场在前主场在后）
	int firstPoints_awayTeam;//第一比分（客场在前主场在后）
	int secondPoints_awayTeam;//第二比分（客场在前主场在后）
	int thirdPoints_awayTeam;//第三比分（客场在前主场在后）
	int forthPoints_awayTeam;//第四比分（客场在前主场在后）
	
	public MatchBasicPO(String gameID,TypeOfMatch matchType, String matchSeason,
			String matchDate, String awayTeamID,String awayTeam, String homeTeamID,String homeTeam,
			int totalPoints_awayTeam,int totalPoints_homeTeam, int firstPoints_awayTeam,int firstPoints_homeTeam, int secondPoints_awayTeam,
			int secondPoints_homeTeam,int thirdPoints_awayTeam,int thirdPoints_homeTeam, int forthPoints_awayTeam,int forthPoints_homeTeam) {
		super();
		this.gameID=gameID;
		this.awayTeamID=awayTeamID;
		this.homeTeamID=homeTeamID;
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.matchDate = matchDate;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.totalPoints_awayTeam = totalPoints_awayTeam;
		this.firstPoints_awayTeam = firstPoints_awayTeam;
		this.secondPoints_awayTeam = secondPoints_awayTeam;
		this.thirdPoints_awayTeam = thirdPoints_awayTeam;
		this.forthPoints_awayTeam = forthPoints_awayTeam;
		this.totalPoints_homeTeam = totalPoints_homeTeam;
		this.firstPoints_homeTeam = firstPoints_homeTeam;
		this.secondPoints_homeTeam = secondPoints_homeTeam;
		this.thirdPoints_homeTeam = thirdPoints_homeTeam;
		this.forthPoints_homeTeam = forthPoints_homeTeam;
	}
public String getGameID(){
	return gameID;
}
public String getAwayTeamID(){
	return awayTeamID;
}
public String getHomeTeamID(){
	return homeTeamID;
}
	public TypeOfMatch getMatchType() {
		return matchType;
	}

	public String getMatchSeason() {
		return matchSeason;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public int getTotalPoints_awayTeam() {
		return totalPoints_awayTeam;
	}

	public int getFirstPoints_awayTeam() {
		return firstPoints_awayTeam;
	}

	public int getSecondPoints_awayTeam() {
		return secondPoints_awayTeam;
	}

	public int getThirdPoints_awayTeam() {
		return thirdPoints_awayTeam;
	}
	public int getForthPoints_awayTeam(){
		return forthPoints_awayTeam;
	}
	public int getTotalPoints_homeTeam() {
		return totalPoints_homeTeam;
	}

	public int getFirstPoints_homeTeam() {
		return firstPoints_homeTeam;
	}

	public int getSecondPoints_homeTeam() {
		return secondPoints_homeTeam;
	}

	public int getThirdPoints_homeTeam() {
		return thirdPoints_homeTeam;
	}
	public int getForthPoints_homeTeam() {
		return forthPoints_homeTeam;
	}
	
	
	
}
