package po;

import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午8:34:15
 *@version
 */

public class PlayerHighPO {
	
	/*****所有的什么率，我均在其原缩写后加Per****/
	TypeOfMatch matchType;//比赛类型
	String matchSeason;//赛季
	String playerID;
	String playerName;//球员名称
	String teamID;
	String teamName;//球队名称缩写
	int GP;
	double MIN;
	int WIN;
	int LOSS;
	double W_PCT;
	double OffRtg;
	double DefRtg;
	double NetRtg;
	double ASTPER;
	double AST_TO;
	double ASTRatio;
	double OREBPer;
	double DREBPer;
	double REBPer;
	double TORatio;
	double EFGPer;
	double TSPer;
	double USGPer;
	double PACE;
	double PIE;
	
	public PlayerHighPO(TypeOfMatch matchType, String matchSeason,
			String playerID, String playerName, String teamID, String teamName,
			int gP, double mIN, int wIN, int lOSS, double w_PCT, double offRtg,
			double defRtg, double netRtg, double aSTPER, double aST_TO,
			double aSTRatio, double oREBPer, double dREBPer, double rEBPer,
			double tORatio, double eFGPer, double tSPer, double uSGPer,
			double pACE, double pIE) {
		super();
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.playerID = playerID;
		this.playerName = playerName;
		this.teamID = teamID;
		this.teamName = teamName;
		GP = gP;
		MIN = mIN;
		WIN = wIN;
		LOSS = lOSS;
		W_PCT = w_PCT;
		OffRtg = offRtg;
		DefRtg = defRtg;
		NetRtg = netRtg;
		ASTPER = aSTPER;
		AST_TO = aST_TO;
		ASTRatio = aSTRatio;
		OREBPer = oREBPer;
		DREBPer = dREBPer;
		REBPer = rEBPer;
		TORatio = tORatio;
		EFGPer = eFGPer;
		TSPer = tSPer;
		USGPer = uSGPer;
		PACE = pACE;
		PIE = pIE;
	}
	
	
	
	public TypeOfMatch getMatchType() {
		return matchType;
	}
	public String getMatchSeason() {
		return matchSeason;
	}
	public String getPlayerID() {
		return playerID;
	}
	public String getPlayerName() {
		return playerName;
	}
	public String getTeamID() {
		return teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public int getGP() {
		return GP;
	}
	public double getMIN() {
		return MIN;
	}
	public int getWIN() {
		return WIN;
	}
	public int getLOSS() {
		return LOSS;
	}
	public double getW_PCT() {
		return W_PCT;
	}
	public double getOffRtg() {
		return OffRtg;
	}
	public double getDefRtg() {
		return DefRtg;
	}
	public double getNetRtg() {
		return NetRtg;
	}
	public double getASTPER() {
		return ASTPER;
	}
	public double getAST_TO() {
		return AST_TO;
	}
	public double getASTRatio() {
		return ASTRatio;
	}
	public double getOREBPer() {
		return OREBPer;
	}
	public double getDREBPer() {
		return DREBPer;
	}
	public double getREBPer() {
		return REBPer;
	}
	public double getTORatio() {
		return TORatio;
	}
	public double getEFGPer() {
		return EFGPer;
	}
	public double getTSPer() {
		return TSPer;
	}
	public double getUSGPer() {
		return USGPer;
	}
	public double getPACE() {
		return PACE;
	}
	public double getPIE() {
		return PIE;
	}
	
}
