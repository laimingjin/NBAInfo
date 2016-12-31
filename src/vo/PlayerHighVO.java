package vo;

import po.PlayerHighPO;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort4PlayerHigh;

/**
 *@author:小春
 *@date:2015年5月19日下午9:12:30
 *@version
 */

public class PlayerHighVO {
	
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
	
	public PlayerHighVO(TypeOfMatch matchType, String matchSeason,
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
	
	public PlayerHighVO(PlayerHighPO po){
		super();
		this.matchType = po.getMatchType();
		this.matchSeason = po.getMatchSeason();
		this.playerID=po.getPlayerID();
		this.playerName = po.getPlayerName();
		this.teamID=po.getTeamID();
		this.teamName = po.getTeamName();
		REBPer = po.getREBPer();
		DREBPer = po.getDREBPer();
		OREBPer = po.getOREBPer();
		GP = po.getGP();
		MIN = po.getMIN();
		WIN = po.getWIN();
		LOSS = po.getLOSS();
		W_PCT = po.getW_PCT();
		OffRtg = po.getOffRtg();
		DefRtg = po.getDefRtg();
		NetRtg = po.getNetRtg();
		ASTPER = po.getASTPER();
		AST_TO = po.getAST_TO();
		ASTRatio = po.getASTRatio();
		TORatio = po.getTORatio();
		EFGPer = po.getEFGPer();
		TSPer = po.getTSPer();	
		USGPer=po.getUSGPer();
		PACE=po.getPACE();
		PIE=po.getPIE();
	
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
	
	public PlayerHighVO(Builder builder){
		super();
		this.matchType = builder.matchType;
		this.matchSeason = builder.matchSeason;
		this.playerName = builder.playerName;
		this.teamName = builder.teamName;
		this.playerID = builder.playerID;
		this.teamID = builder.teamID;
		this.REBPer = builder.REBPer;
		this.DREBPer = builder.DREBPer;
		this.OREBPer = builder.OREBPer;
		this.GP = builder.GP;
		this.MIN = builder.MIN;
		this.WIN = builder.WIN;
		this.LOSS = builder.LOSS;
		this.W_PCT = builder.W_PCT;
		this.OffRtg = builder.OffRtg;
		this.DefRtg = builder.DefRtg;
		this.NetRtg = builder.NetRtg;
		this.ASTPER = builder.ASTPER;
		this.AST_TO = builder.AST_TO;
		this.ASTRatio = builder.ASTRatio;
		this.TORatio = builder.TORatio;
		this.EFGPer = builder.EFGPer;
		this.TSPer = builder.TSPer;
		this.USGPer=builder.USGPer;
		this.PACE=builder.PACE;
		this.PIE=builder.PIE;
	
		
	}
	
	public static class Builder{
		private final TypeOfMatch matchType;//比赛类型
		private final String matchSeason;//赛季
		private final String playerID;
		private final String playerName;//球员名称
		private final String teamID;
		private final String teamName;//球队名称缩写
		int GP=-1;
		double MIN=-1;
		int WIN=-1;
		int LOSS=-1;
		double W_PCT=-1;
		double OffRtg=-1;
		double DefRtg=-1;
		double NetRtg=-1;
		double ASTPER=-1;
		double AST_TO=-1;
		double ASTRatio=-1;
		double OREBPer=-1;
		double DREBPer=-1;
		double REBPer=-1;
		double TORatio=-1;
		double EFGPer=-1;
		double TSPer=-1;
		double USGPer=-1;
		double PACE=-1;
		double PIE=-1;
		
		public Builder(TypeOfMatch matchType, String matchSeason,
				String playerID,String playerName, String teamID,String teamName){
			this.matchType = matchType;
			this.matchSeason = matchSeason;
			this.playerID=playerID;
			this.playerName = playerName;
			this.teamID=teamID;
			this.teamName = teamName;
		}
		public Builder fromPO(PlayerHighPO po){
			if (po!=null) {
				REBPer = po.getREBPer();
				DREBPer = po.getDREBPer();
				OREBPer = po.getOREBPer();
				GP = po.getGP();
				MIN = po.getMIN();
				WIN = po.getWIN();
				LOSS = po.getLOSS();
				W_PCT = po.getW_PCT();
				OffRtg = po.getOffRtg();
				DefRtg = po.getDefRtg();
				NetRtg = po.getNetRtg();
				ASTPER = po.getASTPER();
				AST_TO = po.getAST_TO();
				ASTRatio = po.getASTRatio();
				TORatio = po.getTORatio();
				EFGPer = po.getEFGPer();
				TSPer = po.getTSPer();	
				USGPer=po.getUSGPer();
				PACE=po.getPACE();
				PIE=po.getPIE();
			}
			return this;
		}
		
		public PlayerHighVO build(){
			return new PlayerHighVO(this);
		}
	}
	
	
	public double getNum(TypeOfSort4PlayerHigh item){
		switch (item) {
		case W_PCT: 		 
			return W_PCT;
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
		case USGPer: 
			return USGPer;
		case PACE: 
			return PACE;
		case PIE: 
			return PIE;
		default:
			return -1;
		}
	}
}
