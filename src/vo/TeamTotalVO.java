package vo;

import po.TeamTotalPO;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort4Teams;

/**
 *@author:小春
 *@date:2015年5月19日下午11:27:30
 *@version
 */

public class TeamTotalVO {


	TypeOfMatch matchType;//比赛类型
	String matchSeason;//赛季
	String teamID;
	String teamName;//球队名称缩写
	int GP;//出场数
	int WIN;//胜利场数
	int LOSS;
	int PTS;//总得分
	int FGA;//投篮出手数
	int FGM;//投篮命中数
	String FGPer;//投篮命中率
	int TPA;//三分出手数//原缩写为3PA
	int TPM;//三分命中数//3PM
	String TPPer;//三分命中率//3P%
	int FTA;//罚球出手数
	int FTM;//罚球命中数
	String FTPer;//罚球命中率
	int REB;//篮板数
	int OREB;//进攻篮板//前场篮板
	int DREB;//防守篮板//后场篮板
	int AST;//助攻数
	int STL;//抢断数
	int BLK;//盖帽数
	int TO;//失误数
	int PF;//犯规数
	
	public TeamTotalVO(TypeOfMatch matchType, String matchSeason,
			String teamID,String teamName, int gP, int wIN, int loss,int pTS, int fGA, int fGM,
			String fGPer, int tPA, int tPM, String tPPer, int fTA, int fTM,
			String fTPer, int rEB, int oREB, int dREB, int aST, int sTL,
			int bLK, int tO, int pF) {
		super();
		this.matchType = matchType;
		this.matchSeason = matchSeason;
		this.teamID=teamID;
		this.teamName = teamName;
		GP = gP;
		WIN = wIN;
		LOSS=loss;
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
		TO = tO;
		PF = pF;
	}
	public TeamTotalVO(TeamTotalPO po)
	{
		super();
		this.matchType = po.getMatchType();
		this.matchSeason = po.getMatchSeason();
		this.teamID=po.getTeamID();
		this.teamName = po.getTeamName();
		GP = po.getGP();
		WIN = po.getWIN();
		LOSS=po.getLOSS();
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
		TO = po.getTO();
		PF = po.getPF();
	
	}
	public TeamTotalVO(Builder builder){
		super();
		this.matchType = builder.matchType;
		this.matchSeason = builder.matchSeason;
		this.teamID=builder.teamID;
		this.teamName = builder.teamName;
		this.GP = builder.GP;
		this.LOSS=builder.LOSS;
		this.WIN = builder.WIN;
		this.PTS = builder.PTS;
		this.FGA = builder.FGA;
		this.FGM = builder.FGM;
		this.FGPer = builder.FGPer;
		this.TPA = builder.TPA;
		this.TPM = builder.TPM;
		this.TPPer = builder.TPPer;
		this.FTA = builder.FTA;
		this.FTM = builder.FTM;
		this.FTPer = builder.FTPer;
		this.REB = builder.REB;
		this.OREB = builder.OREB;
		this.DREB = builder.DREB;
		this.AST = builder.AST;
		this.STL = builder.STL;
		this.BLK = builder.BLK;
		this.TO = builder.TO;
		this.PF = builder.PF;
	}
	public static class Builder{
		private final TypeOfMatch matchType;//比赛类型
		private final String matchSeason;//赛季
		private final String teamID;
		private final String teamName;//球队名称全名
		private int GP=-1;//出场数
		private int WIN=-1;//胜利场数
		private int LOSS=-1;
		private int PTS=-1;//总得分
		private int FGA=-1;//投篮出手数
		private int FGM=-1;//投篮命中数
		private String FGPer="-1%";//投篮命中率
		private int TPA=-1;//三分出手数//原缩写为3PA
		private int TPM=-1;//三分命中数//3PM
		private String TPPer="-1%";//三分命中率//3P%
		private int FTA=-1;//罚球出手数
		private int FTM=-1;//罚球命中数
		private String FTPer="-1%";//罚球命中率
		private int REB=-1;//篮板数
		private int OREB=-1;//进攻篮板//前场篮板
		private int DREB=-1;//防守篮板//后场篮板
		private int AST=-1;//助攻数
		private int STL=-1;//抢断数
		private int BLK=-1;//盖帽数
		private int TO=-1;//失误数
		private int PF=-1;//犯规数
		
		public Builder(TypeOfMatch matchType, String matchSeason,String teamID,
				String teamName){
			super();
			this.matchType = matchType;
			this.matchSeason = matchSeason;
			this.teamID=teamID;
			this.teamName = teamName;
		}
		
		public Builder fromPO(TeamTotalPO po){
			if (po!=null) {
				GP = po.getGP();
				WIN = po.getWIN();
				LOSS=po.getLOSS();
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
				TO = po.getTO();
				PF = po.getPF();
			}
			
			return this;
		}
		
		public TeamTotalVO build(){
			return new TeamTotalVO(this);
		}
	}
	public TypeOfMatch getMatchType() {
		return matchType;
	}

	public String getMatchSeason() {
		return matchSeason;
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

	public int getWIN() {
		return WIN;
	}
public int getLOSS(){
	return LOSS;
}
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

	public int getTO() {
		return TO;
	}

	public int getPF() {
		return PF;
	}
	
	public double getNum(TypeOfSort4Teams type) {
		String temp;
		double tempNum;
		switch (type) {
		case GP:
			return GP;
		case	WIN:
			return WIN;// 胜利场数
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
		case	TO: // 失误数
			return TO;
		case	PF: // 犯规数
			return PF;
		default:
			return -1;
		}
	}

}
