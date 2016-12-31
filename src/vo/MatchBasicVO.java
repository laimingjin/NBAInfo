package vo;

import po.MatchBasicPO;
import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午11:48:09
 *@version
 */

public class MatchBasicVO {

	
	
	String gameID;
	TypeOfMatch matchType;//比赛类型
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
	
	
public MatchBasicVO(String gameID, TypeOfMatch matchType,
			String matchSeason, String matchDate, String awayTeamID,
			String homeTeamID, String awayTeam, String homeTeam,
			int totalPoints_homeTeam, int firstPoints_homeTeam,
			int secondPoints_homeTeam, int thirdPoints_homeTeam,
			int forthPoints_homeTeam, int totalPoints_awayTeam,
			int firstPoints_awayTeam, int secondPoints_awayTeam,
			int thirdPoints_awayTeam, int forthPoints_awayTeam) {
		super();
		this.gameID = gameID;
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.matchDate = matchDate;
		this.awayTeamID = awayTeamID;
		this.homeTeamID = homeTeamID;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.totalPoints_homeTeam = totalPoints_homeTeam;
		this.firstPoints_homeTeam = firstPoints_homeTeam;
		this.secondPoints_homeTeam = secondPoints_homeTeam;
		this.thirdPoints_homeTeam = thirdPoints_homeTeam;
		this.forthPoints_homeTeam = forthPoints_homeTeam;
		this.totalPoints_awayTeam = totalPoints_awayTeam;
		this.firstPoints_awayTeam = firstPoints_awayTeam;
		this.secondPoints_awayTeam = secondPoints_awayTeam;
		this.thirdPoints_awayTeam = thirdPoints_awayTeam;
		this.forthPoints_awayTeam = forthPoints_awayTeam;
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

	public MatchBasicVO(MatchBasicPO po){
		super();
		this.gameID=po.getGameID();
		this.awayTeamID=po.getAwayTeamID();
		this.homeTeamID=po.getHomeTeamID();
		this.matchType = po.getMatchType();
		this.matchSeason = po.getMatchSeason();
		this.matchDate = po.getMatchDate();
		this.awayTeam = po.getAwayTeam();
		this.homeTeam = po.getHomeTeam();
		this.totalPoints_awayTeam = po.getTotalPoints_awayTeam();
		this.firstPoints_awayTeam = po.getFirstPoints_awayTeam();
		this.secondPoints_awayTeam = po.getSecondPoints_awayTeam();
		this.thirdPoints_awayTeam = po.getThirdPoints_awayTeam();
		this.forthPoints_awayTeam = po.getForthPoints_awayTeam();
		this.totalPoints_homeTeam = po.getTotalPoints_homeTeam();
		this.firstPoints_homeTeam = po.getFirstPoints_homeTeam();
		this.secondPoints_homeTeam = po.getSecondPoints_homeTeam();
		this.thirdPoints_homeTeam = po.getThirdPoints_homeTeam();
		this.forthPoints_homeTeam = po.getForthPoints_homeTeam();
	}
	
}
