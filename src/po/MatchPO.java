package po;

import java.util.ArrayList;

import enumerate.TypeOfMatch;

public class MatchPO {
String gameID;
String matchDate;// 年月日
TypeOfMatch typeOfMatch;//比赛类型
String awayTeamID;
String awayTeam;// 客场球队
String homeTeamID;
String homeTeam;// 主场球队
int awayTeamTotalScore;
int homeTeamTotalScore;
int awayTeamScore1;
int awayTeamScore2;
int awayTeamScore3;
int awayTeamScore4;
int homeTeamScore1;
int homeTeamScore2;
int homeTeamScore3;
int homeTeamScore4;
ArrayList<PlayerInMatchPO>awayTeamPlayers;
ArrayList<PlayerInMatchPO>homeTeamPlayers;
TeamInMatchPO awayTeamStats;
TeamInMatchPO homeTeamStats;

public String getGameID() {
	return gameID;
}
public String getMatchDate() {
	return matchDate;
}
public TypeOfMatch getTypeOfMatch() {
	return typeOfMatch;
}
public String getAwayTeamID() {
	return awayTeamID;
}
public String getAwayTeam() {
	return awayTeam;
}
public String getHomeTeamID() {
	return homeTeamID;
}
public String getHomeTeam() {
	return homeTeam;
}
public int getAwayTeamTotalScore() {
	return awayTeamTotalScore;
}
public int getHomeTeamTotalScore() {
	return homeTeamTotalScore;
}
public int getAwayTeamScore1() {
	return awayTeamScore1;
}
public int getAwayTeamScore2() {
	return awayTeamScore2;
}
public int getAwayTeamScore3() {
	return awayTeamScore3;
}
public int getAwayTeamScore4() {
	return awayTeamScore4;
}
public int getHomeTeamScore1() {
	return homeTeamScore1;
}
public int getHomeTeamScore2() {
	return homeTeamScore2;
}
public int getHomeTeamScore3() {
	return homeTeamScore3;
}
public int getHomeTeamScore4() {
	return homeTeamScore4;
}
public ArrayList<PlayerInMatchPO> getAwayTeamPlayers() {
	return awayTeamPlayers;
}
public ArrayList<PlayerInMatchPO> getHomeTeamPlayers() {
	return homeTeamPlayers;
}
public TeamInMatchPO getAwayTeamStats() {
	return awayTeamStats;
}
public TeamInMatchPO getHomeTeamStats() {
	return homeTeamStats;
}

public MatchPO(String gameID, String matchDate, TypeOfMatch typeOfMatch,
		String awayTeamID, String awayTeam, String homeTeamID, String homeTeam,
		int awayTeamTotalScore, int homeTeamTotalScore, int awayTeamScore1,
		int awayTeamScore2, int awayTeamScore3, int awayTeamScore4,
		int homeTeamScore1, int homeTeamScore2, int homeTeamScore3,
		int homeTeamScore4, ArrayList<PlayerInMatchPO> awayTeamPlayers,
		ArrayList<PlayerInMatchPO> homeTeamPlayers,
		TeamInMatchPO awayTeamStats, TeamInMatchPO homeTeamStats) {
	super();
	this.gameID = gameID;
	this.matchDate = matchDate;
	this.typeOfMatch = typeOfMatch;
	this.awayTeamID = awayTeamID;
	this.awayTeam = awayTeam;
	this.homeTeamID = homeTeamID;
	this.homeTeam = homeTeam;
	this.awayTeamTotalScore = awayTeamTotalScore;
	this.homeTeamTotalScore = homeTeamTotalScore;
	this.awayTeamScore1 = awayTeamScore1;
	this.awayTeamScore2 = awayTeamScore2;
	this.awayTeamScore3 = awayTeamScore3;
	this.awayTeamScore4 = awayTeamScore4;
	this.homeTeamScore1 = homeTeamScore1;
	this.homeTeamScore2 = homeTeamScore2;
	this.homeTeamScore3 = homeTeamScore3;
	this.homeTeamScore4 = homeTeamScore4;
	this.awayTeamPlayers = awayTeamPlayers;
	this.homeTeamPlayers = homeTeamPlayers;
	this.awayTeamStats = awayTeamStats;
	this.homeTeamStats = homeTeamStats;
}



}
