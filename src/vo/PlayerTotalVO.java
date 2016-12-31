package vo;

import po.PlayerTotalPO;
import enumerate.PositionOfPlayer;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort4Players;

/**
 * @author:小春
 * @date:2015年5月19日下午8:33:08
 * @version
 */

public class PlayerTotalVO {

	TypeOfMatch matchType;// 比赛类型
	String matchSeason;// 赛季
	String  playerID;
	String playerName;// 球员名称
	String teamID;
	String teamName;// 球队名称缩写
	int GP;// 出场数
	int GS;// 首发出场数
	int MIN;// 出场时间
//	int WIN;// 胜利场数
	int PTS;// 总得分
	int FGA;// 投篮出手数
	int FGM;// 投篮命中数
	String FGPer;// 投篮命中率
	int TPA;// 三分出手数//原缩写为3PA
	int TPM;// 三分命中数//3PM
	String TPPer;// 三分命中率//3P%
	int FTA;// 罚球出手数
	int FTM;// 罚球命中数
	String FTPer;// 罚球命中率
	int REB;// 篮板数
	int OREB;// 进攻篮板//前场篮板
	int DREB;// 防守篮板//后场篮板
	int AST;// 助攻数
	int STL;// 抢断数
	int BLK;// 盖帽数
	int TOV;// 失误数
	int PF;// 犯规数
	double EffPer;//效率

	public PlayerTotalVO(TypeOfMatch matchType, String matchSeason,
			String playerID,String playerName, String teamID,String teamName, int gP, int gS, int mIN,
		    int pTS, int fGA, int fGM, String fGPer, int tPA, int tPM,
			String tPPer, int fTA, int fTM, String fTPer, int rEB, int oREB,
			int dREB, int aST, int sTL, int bLK, int tOV, int pF,double EffPer) {
		super();
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.playerID=playerID;
		this.playerName = playerName;
		this.teamID=teamID;
		this.teamName = teamName;
		GP = gP;
		GS = gS;
		MIN = mIN;
//		WIN = wIN;
		PTS = pTS;
		FGA = fGA;
		FGM = fGM;
		FGPer = fGPer;
		TPA = tPA;
		TPM = tPM;
		TPPer = tPPer;
		FTA = fTA;
		FTM = fTM;
		FTPer = fTPer;
		REB = rEB;
		OREB = oREB;
		DREB = dREB;
		AST = aST;
		STL = sTL;
		BLK = bLK;
		TOV = tOV;
		PF = pF;
		this.EffPer=EffPer;
	}

	public PlayerTotalVO(PlayerTotalPO po){
		super();
		this.matchType = po.getMatchType();
		this.matchSeason = po.getMatchSeason();
		this.playerID=po.getPlayerID();
		this.playerName = po.getPlayerName();
		this.teamID=po.getTeamID();
		this.teamName = po.getTeamName();
		GP = po.getGP();
		GS = po.getGS();
		MIN = po.getMIN();
//		WIN = po.getWIN();
		PTS = po.getPTS();
		FGA = po.getFGA();
		FGM = po.getFGM();
		FGPer = po.getFGPer();
		TPA = po.getTPA();
		TPM = po.getTPM();
		TPPer = po.getTPPer();
		FTA = po.getFTA();
		FTM = po.getFTM();
		FTPer = po.getFTPer();
		REB = po.getREB();
		OREB = po.getOREB();
		DREB = po.getDREB();
		AST = po.getAST();
		STL = po.getSTL();
		BLK = po.getBLK();
		TOV = po.getTOV();
		PF = po.getPF();
		EffPer=po.getEffPer();
	
	}
	public PlayerTotalVO(Builder builder) {
		super();
		this.matchType = builder.matchType;
		this.matchSeason = builder.matchSeason;
		this.playerID=builder.playerID;
		this.playerName = builder.playerName;
		this.teamID=builder.teamID;
		this.teamName = builder.teamName;
		GP = builder.GP;
		GS = builder.GS;
		MIN = builder.MIN;
//		WIN = builder.WIN;
		PTS = builder.PTS;
		FGA = builder.FGA;
		FGM = builder.FGM;
		FGPer = builder.FGPer;
		TPA = builder.TPA;
		TPM = builder.TPM;
		TPPer = builder.TPPer;
		FTA = builder.FTA;
		FTM = builder.FTM;
		FTPer = builder.FTPer;
		REB = builder.REB;
		OREB = builder.OREB;
		DREB = builder.DREB;
		AST = builder.AST;
		STL = builder.STL;
		BLK = builder.BLK;
		TOV = builder.TOV;
		PF = builder.PF;
		EffPer=builder.EffPer;
	}

	public static class Builder {
		private final TypeOfMatch matchType;// 比赛类型
		private final String matchSeason;// 赛季
		private final String playerID;
		private final String playerName;// 球员名称
		private final String teamID;
		private final String teamName;// 球队名称缩写
		private int GP = -1;// 出场数
		private int GS = -1;// 首发出场数
		private int MIN = -1;// 出场时间
//		private int WIN = -1;// 胜利场数
		private int PTS = -1;// 总得分
		private int FGA = -1;// 投篮出手数
		private int FGM = -1;// 投篮命中数
		private String FGPer = "";// 投篮命中率
		private int TPA = -1;// 三分出手数//原缩写为3PA
		private int TPM = -1;// 三分命中数//3PM
		private String TPPer = "";// 三分命中率//3P%
		private int FTA = -1;// 罚球出手数
		private int FTM = -1;// 罚球命中数
		private String FTPer = "";// 罚球命中率
		private int REB = -1;// 篮板数
		private int OREB = -1;// 进攻篮板//前场篮板
		private int DREB = -1;// 防守篮板//后场篮板
		private int AST = -1;// 助攻数
		private int STL = -1;// 抢断数
		private int BLK = -1;// 盖帽数
		private int TOV = -1;// 失误数
		private int PF = -1;// 犯规数
		private double EffPer=-1;

		public Builder(TypeOfMatch matchType, String matchSeason,
				String playerID,String playerName,String teamID, String teamName) {
			
			this.matchType = matchType;
			this.matchSeason = matchSeason;
			this.playerID=playerID;
			this.playerName = playerName;
			this.teamID=teamID;
			this.teamName = teamName;
		}

		public Builder fromPO(PlayerTotalPO po) {
			if (po != null) {
				GP = po.getGP();
				GS = po.getGS();
				MIN = po.getMIN();
//				WIN = po.getWIN();
				PTS = po.getPTS();
				FGA = po.getFGA();
				FGM = po.getFGM();
				FGPer = po.getFGPer();
				TPA = po.getTPA();
				TPM = po.getTPM();
				TPPer = po.getTPPer();
				FTA = po.getFTA();
				FTM = po.getFTM();
				FTPer = po.getFTPer();
				REB = po.getREB();
				OREB = po.getOREB();
				DREB = po.getDREB();
				AST = po.getAST();
				STL = po.getSTL();
				BLK = po.getBLK();
				TOV = po.getTOV();
				PF = po.getPF();
				EffPer=po.getEffPer();
			}
			return this;
		}

		public PlayerTotalVO build() {
			return new PlayerTotalVO(this);
		}
	}

	public TypeOfMatch getMatchType() {
		return matchType;
	}

	public String getMatchSeason() {
		return matchSeason;
	}
public String getPlayerID(){
	return playerID;
}
	public String getPlayerName() {
		return playerName;
	}
public String getTeamID(){
	return teamID;
}
	public String getTeamName() {
		return teamName;
	}

	public int getGP() {
		return GP;
	}

	public int getGS() {
		return GS;
	}

	public int getMIN() {
		return MIN;
	}

//	public int getWIN() {
//		return WIN;
//	}

	public int getPTS() {
		return PTS;
	}

	public int getFGA() {
		return FGA;
	}

	public int getFGM() {
		return FGM;
	}

	public String getFGPer() {
		return FGPer;
	}

	public int getTPA() {
		return TPA;
	}

	public int getTPM() {
		return TPM;
	}

	public String getTPPer() {
		return TPPer;
	}

	public int getFTA() {
		return FTA;
	}

	public int getFTM() {
		return FTM;
	}

	public String getFTPer() {
		return FTPer;
	}

	public int getREB() {
		return REB;
	}

	public int getOREB() {
		return OREB;
	}

	public int getDREB() {
		return DREB;
	}

	public int getAST() {
		return AST;
	}

	public int getSTL() {
		return STL;
	}

	public int getBLK() {
		return BLK;
	}

	public int getTOV() {
		return TOV;
	}

	public int getPF() {
		return PF;
	}
public double getEffPer(){
	return EffPer;
}
	public double getNum(TypeOfSort4Players type) {
		String temp;
		double tempNum;
		switch (type) {
		case GP:
			return GP;
		case GS:
			return GS;
		case MIN:
			return MIN;
//		case	WIN:
//			return WIN;// 胜利场数
		case	PTS: // 总得分
			return PTS;
		case	FGA: // 投篮出手数
			return FGA;
		case FGM: // 投篮命中数
			return FGM;
		case	FGPer: // 投篮命中率
			temp=FGPer.replaceAll("%", "");
			tempNum=Double.parseDouble(temp)/100;
			return tempNum;
		case	TPA: // 三分出手数//原缩写为3PA
			return TPA;
		case	TPM: // 三分命中数//3PM
			return TPM;
		case	TPPer: // 三分命中率//3P/罚球命中数
			temp=TPPer.replaceAll("%", "");
			tempNum=Double.parseDouble(temp)/100;
			return tempNum;
		case	FTA:
			return FTA;
		case	FTM:
			return FTM;
		case	FTPer: // 罚球命中率
			temp=FTPer.replaceAll("%", "");
			tempNum=Double.parseDouble(temp)/100;
			return tempNum;
		case	REB: // 篮板数
			return REB;
		case	OREB: // 进攻篮板//前场篮板
			return OREB;
		case	DREB: // 防守篮板//后场篮板
			return DREB;
		case	AST: // 助攻数
			return AST;
		case	STL: // 抢断数
			return STL;
		case	BLK: // 盖帽数
			return BLK;
		case	TOV: // 失误数
			return TOV;
		case	PF: // 犯规数
			return PF;
		default:
			return -1;
		}
	}
}
