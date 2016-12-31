package vo;

/**
 * @author:小春
 * @date:2015年6月6日下午2:54:54
 * @version
 */

public class PlayerImpMostVO {

	String playerName;
	String playerID;
	double impRate;

	public PlayerImpMostVO(String playerName, String playerID, double impRate) {
		super();
		this.playerName = playerName;
		this.playerID = playerID;
		this.impRate = impRate;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerID() {
		return playerID;
	}

	public double getImpRate() {
		return impRate;
	}

}
