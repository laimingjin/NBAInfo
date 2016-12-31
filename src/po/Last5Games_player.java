package po;

public class Last5Games_player {
String playerID;
String playerName;
String gameID1;
String gameID2;
String gameID3;
String gameID4;
String gameID5;


public Last5Games_player(String playerID, String playerName, String gameID1,
		String gameID2, String gameID3, String gameID4, String gameID5) {
	super();
	this.playerID = playerID;
	this.playerName = playerName;
	this.gameID1 = gameID1;
	this.gameID2 = gameID2;
	this.gameID3 = gameID3;
	this.gameID4 = gameID4;
	this.gameID5 = gameID5;
}
public String getPlayerID() {
	return playerID;
}


public String getPlayerName() {
	return playerName;
}


public String getGameID1() {
	return gameID1;
}


public String getGameID2() {
	return gameID2;
}


public String getGameID3() {
	return gameID3;
}


public String getGameID4() {
	return gameID4;
}


public String getGameID5() {
	return gameID5;
}
}
