package po;

import enumerate.TypeOfMatch;

/**
 * @author:小春
 * @date:2015年5月19日下午11:58:33
 * @version
 */

public class PlayerInMatchPO {
	String gameID;
	String matchDate;// 年月日
	TypeOfMatch typeOfMatch;//比赛类型
	String awayTeamID;
	String awayTeam;// 客场球队
	String homeTeamID;
	String homeTeam;// 主场球队
//	int awayTeamTotalScore;
//	int homeTeamTotalScore;
//	int awayTeamScore1;
//	int awayTeamScore2;
//	int awayTeamScore3;
//	int awayTeamScore4;
//	int homeTeamScore1;
//	int homeTeamScore2;
//	int homeTeamScore3;
//	int homeTeamScore4;
	String playerID;
	String playerName;// 球员名称
	String teamOfPlayer;// 球员球队
	String position;
	int isGS;// 是否首发
	int MIN;// 出场时间
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
	int PTS;// 总得分
	double PLUS_MINUS;// 真實命中率

    public PlayerInMatchPO(String gameID, String matchDate,
			TypeOfMatch typeOfMatch, String awayTeamID, String awayTeam,
			String homeTeamID, String homeTeam, 
//			int awayTeamTotalScore,
//			int homeTeamTotalScore, int awayTeamScore1, int awayTeamScore2,
//			int awayTeamScore3, int awayTeamScore4, int homeTeamScore1,
//			int homeTeamScore2, int homeTeamScore3, int homeTeamScore4,
			String playerID, String playerName, String teamOfPlayer,
			String position, int isGS, int mIN, int fGA, int fGM, String fGPer,
			int tPA, int tPM, String tPPer, int fTA, int fTM, String fTPer,
			int rEB, int oREB, int dREB, int aST, int sTL, int bLK, int tOV,
			int pF, int pTS, double PLUS_MINUS) {
		super();
		this.gameID = gameID;
		this.matchDate = matchDate;
		this.typeOfMatch = typeOfMatch;
		this.awayTeamID = awayTeamID;
		this.awayTeam = awayTeam;
		this.homeTeamID = homeTeamID;
		this.homeTeam = homeTeam;
//		this.awayTeamTotalScore = awayTeamTotalScore;
//		this.homeTeamTotalScore = homeTeamTotalScore;
//		this.awayTeamScore1 = awayTeamScore1;
//		this.awayTeamScore2 = awayTeamScore2;
//		this.awayTeamScore3 = awayTeamScore3;
//		this.awayTeamScore4 = awayTeamScore4;
//		this.homeTeamScore1 = homeTeamScore1;
//		this.homeTeamScore2 = homeTeamScore2;
//		this.homeTeamScore3 = homeTeamScore3;
//		this.homeTeamScore4 = homeTeamScore4;
		this.playerID = playerID;
		this.playerName = playerName;
		this.teamOfPlayer = teamOfPlayer;
		this.position = position;
		this.isGS = isGS;
		MIN = mIN;
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
	
	public String getGameID() {
		return gameID;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public TypeOfMatch getTypeOfMatch() {
		return typeOfMatch;
	}
	public String getAwayTeamID() {
		return awayTeamID;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public String getHomeTeamID() {
		return homeTeamID;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
//	public int getAwayTeamTotalScore() {
//		return awayTeamTotalScore;
//	}
//	public int getHomeTeamTotalScore() {
//		return homeTeamTotalScore;
//	}
//	public int getAwayTeamScore1() {
//		return awayTeamScore1;
//	}
//	public int getAwayTeamScore2() {
//		return awayTeamScore2;
//	}
//	public int getAwayTeamScore3() {
//		return awayTeamScore3;
//	}
//	public int getAwayTeamScore4() {
//		return awayTeamScore4;
//	}
//	public int getHomeTeamScore1() {
//		return homeTeamScore1;
//	}
//	public int getHomeTeamScore2() {
//		return homeTeamScore2;
//	}
//	public int getHomeTeamScore3() {
//		return homeTeamScore3;
//	}
//	public int getHomeTeamScore4() {
//		return homeTeamScore4;
//	}
	public String getPlayerID() {
		return playerID;
	}
	public String getPlayerName() {
		return playerName;
	}
	public String getTeamOfPlayer() {
		return teamOfPlayer;
	}
	public String getPosition() {
		return position;
	}
	public int getIsGS() {
		return isGS;
	}
	public int getMIN() {
		return MIN;
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
	public int getPTS() {
		return PTS;
	}
	public double getPLUS_MINUS() {
		return PLUS_MINUS;
	}
	
	
}
