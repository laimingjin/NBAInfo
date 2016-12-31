package po;

import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午11:33:49
 *@version
 */

public class TeamHighPO {
	
	/*****所有的什么率，我均在其原缩写后加Per****/
	TypeOfMatch matchType;//比赛类型
	String matchSeason;//赛季
	String teamID;
	String teamName;//球队名称缩写
	int GP;
	int MIN;
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
	double PACE;
	double PIE;
	public TeamHighPO(TypeOfMatch matchType, String matchSeason, String teamID,
			String teamName, int gP, int mIN, double offRtg, double defRtg,
			double netRtg, double aSTPER, double aST_TO, double aSTRatio,
			double oREBPer, double dREBPer, double rEBPer, double tORatio,
			double eFGPer, double tSPer, double pACE, double pIE) {
		super();
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.teamID = teamID;
		this.teamName = teamName;
		GP = gP;
		MIN = mIN;
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
		PACE = pACE;
		PIE = pIE;
	}
	


	public TypeOfMatch getMatchType() {
		return matchType;
	}
	public String getMatchSeason() {
		return matchSeason;
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
	public int getMIN() {
		return MIN;
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
	public double getPACE() {
		return PACE;
	}
	public double getPIE() {
		return PIE;
	}

}