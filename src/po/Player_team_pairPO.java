package po;

public class Player_team_pairPO {
String playerID;
String teamID;
String teamName;
String matchSeason;
public String getPlayerID() {
	return playerID;
}
public String getTeamID() {
	return teamID;
}
public String getTeamName() {
	return teamName;
}
public String getMatchSeason() {
	return matchSeason;
}

public Player_team_pairPO(String playerID, String teamID, String teamName,
		String matchSeason) {
	super();
	this.playerID = playerID;
	this.teamID = teamID;
	this.teamName = teamName;
	this.matchSeason = matchSeason;
}


}
