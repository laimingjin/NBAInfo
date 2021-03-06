package po;

import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午7:44:45
 *@version
 */

public class PlayerTotalPO {

	

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
	
	public PlayerTotalPO(TypeOfMatch matchType, String matchSeason,
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
}
