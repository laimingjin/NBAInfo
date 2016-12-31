package vo;

import po.TeamHighPO;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort4TeamsHigh;

/**
 *@author:小春
 *@date:2015年5月19日下午11:36:13
 *@version
 */

public class TeamHighVO {

	
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

	public TeamHighVO(TypeOfMatch matchType, String matchSeason, String teamID,
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
public TeamHighVO(TeamHighPO po){
	super();
	this.matchType = po.getMatchType();
	this.matchSeason = po.getMatchSeason();
	this.teamID=po.getTeamID();
	this.teamName = po.getTeamName();

	REBPer = po.getREBPer();
	OREBPer = po.getOREBPer();
	DREBPer = po.getDREBPer();
	GP = po.getGP();
	MIN = po.getMIN();
	OffRtg = po.getOffRtg();
	DefRtg = po.getDefRtg();
	NetRtg = po.getNetRtg();
	ASTPER = po.getASTPER();
	AST_TO = po.getAST_TO();
	ASTRatio = po.getASTRatio();
	TORatio = po.getTORatio();
	EFGPer = po.getEFGPer();
	TSPer = po.getTSPer();
	PACE = po.getPACE();
	PIE = po.getPIE();

}
public TeamHighVO(Builder builder){
	super();
	this.matchType = builder.matchType;
	this.matchSeason = builder.matchSeason;
	this.teamID=builder.teamID;
	this.teamName = builder.teamName;
	REBPer = builder.REBPer;
	OREBPer = builder.OREBPer;
	DREBPer = builder.DREBPer;
	GP = builder.GP;
	MIN = builder.MIN;
	OffRtg = builder.OffRtg;
	DefRtg = builder.DefRtg;
	NetRtg = builder.NetRtg;
	ASTPER = builder.ASTPER;
	AST_TO = builder.AST_TO;
	ASTRatio = builder.ASTRatio;
	TORatio = builder.TORatio;
	EFGPer = builder.EFGPer;
	TSPer = builder.TSPer;
	PACE = builder.PACE;
	PIE = builder.PIE;
	
	
}
public static class Builder{
	private final TypeOfMatch matchType;//比赛类型
	private final String matchSeason;//赛季
	private final String teamID;
	private final String teamName;//球队名称全名
	private int GP=-1;
	private int MIN=-1;
	private double OffRtg=-1;
	private double DefRtg=-1;
	private double NetRtg=-1;
	private double ASTPER=-1;
	private double AST_TO=-1;
	private double ASTRatio=-1;
	private double OREBPer=-1;
	private double DREBPer=-1;
	private double REBPer=-1;
	private double TORatio=-1;
	private double EFGPer=-1;
	private double TSPer=-1;
	private double PACE=-1;
	private double PIE=-1;
	
	
	public Builder(TypeOfMatch matchType, String matchSeason,
			String teamID,String teamName){
		super();
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.teamID=teamID;
		this.teamName = teamName;
	}
	
	public Builder fromPO(TeamHighPO po){
		if (po!=null) {
			REBPer = po.getREBPer();
			OREBPer = po.getOREBPer();
			DREBPer = po.getDREBPer();
			GP = po.getGP();
			MIN = po.getMIN();
			OffRtg = po.getOffRtg();
			DefRtg = po.getDefRtg();
			NetRtg = po.getNetRtg();
			ASTPER = po.getASTPER();
			AST_TO = po.getAST_TO();
			ASTRatio = po.getASTRatio();
			TORatio = po.getTORatio();
			EFGPer = po.getEFGPer();
			TSPer = po.getTSPer();
			PACE = po.getPACE();
			PIE = po.getPIE();
		}
		return this;
	}
	
	public TeamHighVO build(){
		return new TeamHighVO(this);
	}
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


public double getNum(TypeOfSort4TeamsHigh type){
	switch (type) {
	case OffRtg:
		return OffRtg;
	case DefRtg: 
		return DefRtg;
	case NetRtg: 
		return NetRtg;
	case ASTPER: 
		return ASTPER;
	case AST_TO: 
		return AST_TO;
	case ASTRatio:
		return ASTRatio;
	case OREBPer: 
		return OREBPer;
	case DREBPer: 
		return DREBPer;
	case REBPer: 
		return REBPer;
	case TORatio:
		return TORatio;
	case EFGPer:
		return EFGPer;
	case TSPer:
		return TSPer;
	case PACE:
		return PACE;
	case PIE:
		return PIE;
	default:
		return -1;
	}
}

}
