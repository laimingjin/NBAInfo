package po;

import enumerate.TypeOfMatch;

/**
 * @author:小春
 * @date:2015年5月20日上午12:07:53
 * @version
 */

public class TeamInMatchPO {
	String gameID;
	String matchDate;// 年月日
	TypeOfMatch matchType;// 比赛类型
	String matchSeason;// 赛季
	String awayTeamID;
	String awayTeam;// 客场球队
	String homeTeamID;
	String homeTeam;// 主场球队
	String teamID;
	String teamName;// 該球隊
	int isWin;//输赢，赢为1；输为-1
	int numOfPlayer;// 出場人數
	int FGA;// 投篮出手数
	int FGM;// 投篮命中数
	double FGPer;// 投篮命中率
	int TPA;// 三分出手数//原缩写为3PA
	int TPM;// 三分命中数//3PM
	double TPPer;// 三分命中率//3P%
	int FTA;// 罚球出手数
	int FTM;// 罚球命中数
	double FTPer;// 罚球命中率
	int REB;// 篮板数
	int OREB;// 进攻篮板//前场篮板
	int DREB;// 防守篮板//后场篮板
	int AST;// 助攻数
	int STL;// 抢断数
	int BLK;// 盖帽数
	int TOV;// 失误数
	int PF;// 犯规数
	int PTS;// 总得分
	double PLUS_MINUS;// 真實命中率
	
	public TeamInMatchPO(String gameID,String matchDate, TypeOfMatch matchType,
			String matchSeason, String awayTeamID,String awayTeam, String homeTeamID, String homeTeam,
			String teamID,String teamName, int numOfPlayer,int isWin, int fGA, int fGM, double fGPer,
			int tPA, int tPM, double tPPer, int fTA, int fTM, double fTPer,
			int rEB, int oREB, int dREB, int aST, int sTL, int bLK, int tOV,
			int pF, int pTS, double PLUS_MINUS) {
		super();
		this.gameID=gameID;
		this.matchDate = matchDate;
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.awayTeamID=awayTeamID;
		this.awayTeam = awayTeam;
		this.homeTeamID=homeTeamID;
		this.homeTeam = homeTeam;
		this.teamID=teamID;
		this.teamName = teamName;
		this.isWin=isWin;
		this.numOfPlayer = numOfPlayer;
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
		PTS = pTS;
		this.PLUS_MINUS = PLUS_MINUS;
	}
public String getGameID(){
	return gameID;
}
public String getAwayTeamID(){
	return awayTeamID;
}
public String getHomeTeamID(){
	return homeTeamID;
}
public String getTeamID(){
	return teamID;
}
	public String getMatchDate() {
		return matchDate;
	}

	public TypeOfMatch getMatchType() {
		return matchType;
	}

	public String getMatchSeason() {
		return matchSeason;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getNumOfPlayer() {
		return numOfPlayer;
	}

	public int getFGA() {
		return FGA;
	}

	public int getFGM() {
		return FGM;
	}

	public double getFGPer() {
		return FGPer;
	}

	public int getTPA() {
		return TPA;
	}

	public int getTPM() {
		return TPM;
	}

	public double getTPPer() {
		return TPPer;
	}

	public int getFTA() {
		return FTA;
	}

	public int getFTM() {
		return FTM;
	}

	public double getFTPer() {
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

	public int getPTS() {
		return PTS;
	}

	public double getPLUS_MINUS() {
		return PLUS_MINUS;
	}
	public void setNumOfPlayer(int num){
		numOfPlayer=num;
	}
	public int getIsWin() {
		return isWin;
	}
	
}
