package po;

import enumerate.TypeOfMatch;

/**
 *@author:小春
 *@date:2015年5月19日下午9:14:25
 *@version
 */

public class PlayerShootPO {
   

	TypeOfMatch matchType;//比赛类型
	String matchSeason;//赛季
	String playerName;//球员名称
	String teamName;//球队名称缩写
   double shotDistance;//出手距离
   double rimHitPer;//篮下命中率
   double rimHit;//平均篮下命中数
   double timShot;//平均场下出手数
   double rimTeamPer;//篮下占比
   double closeTwoHitPer;//近距两分命中率
   double closeTwoHit;//平均近距两分命中数
   double closeTwoShot;//平均近距两分出手数
   double closeTwoTeamPer;//近距两分占比
   double midTwoHitPer;//中距两分命中率
   double midTwoHit;//平均中距两分命中数
   double midTwoShot;//平均中距两分出手数
   double midTwoTeamPer;//中距两分占比
   double teleTwoHitPer;//远距两分命中率
   double teleTwoHit;//平均远距两分命中数
   double teleTwoShot;//平均远距两分出手数
   double teleTwoTeamPer;//远距两分占比
   double realPMPer;//真实命中率
   double PGMEff;//投篮效率
   
   public PlayerShootPO(TypeOfMatch matchType, String matchSeason,
		   String playerName, String teamName, double shotDistance,
		   double rimHitPer, double rimHit, double timShot, double rimTeamPer,
		   double closeTwoHitPer, double closeTwoHit, double closeTwoShot,
		   double closeTwoTeamPer, double midTwoHitPer, double midTwoHit,
		   double midTwoShot, double midTwoTeamPer, double teleTwoHitPer,
		   double teleTwoHit, double teleTwoShot, double teleTwoTeamPer,
		   double realPMPer, double pGMEff) {
	   super();
	   this.matchType = matchType;
	   this.matchSeason = matchSeason;
	   this.playerName = playerName;
	   this.shotDistance = shotDistance;
	   this.rimHitPer = rimHitPer;
	   this.teamName = teamName;
	   this.rimHit = rimHit;
	   this.timShot = timShot;
	   this.rimTeamPer = rimTeamPer;
	   this.closeTwoHitPer = closeTwoHitPer;
	   this.closeTwoHit = closeTwoHit;
	   this.closeTwoShot = closeTwoShot;
	   this.closeTwoTeamPer = closeTwoTeamPer;
	   this.midTwoHitPer = midTwoHitPer;
	   this.midTwoHit = midTwoHit;
	   this.midTwoShot = midTwoShot;
	   this.midTwoTeamPer = midTwoTeamPer;
	   this.teleTwoHitPer = teleTwoHitPer;
	   this.teleTwoHit = teleTwoHit;
	   this.teleTwoShot = teleTwoShot;
	   this.teleTwoTeamPer = teleTwoTeamPer;
	   this.realPMPer = realPMPer;
	   PGMEff = pGMEff;
   }
   

public TypeOfMatch getMatchType() {
	return matchType;
}


public String getMatchSeason() {
	return matchSeason;
}


public String getPlayerName() {
	return playerName;
}


public String getTeamName() {
	return teamName;
}

public double getShotDistance() {
	return shotDistance;
}

public double getRimHitPer() {
	return rimHitPer;
}

public double getRimHit() {
	return rimHit;
}

public double getTimShot() {
	return timShot;
}

public double getRimTeamPer() {
	return rimTeamPer;
}

public double getCloseTwoHitPer() {
	return closeTwoHitPer;
}

public double getCloseTwoHit() {
	return closeTwoHit;
}

public double getCloseTwoShot() {
	return closeTwoShot;
}

public double getCloseTwoTeamPer() {
	return closeTwoTeamPer;
}

public double getMidTwoHitPer() {
	return midTwoHitPer;
}

public double getMidTwoHit() {
	return midTwoHit;
}

public double getMidTwoShot() {
	return midTwoShot;
}

public double getMidTwoTeamPer() {
	return midTwoTeamPer;
}

public double getTeleTwoHitPer() {
	return teleTwoHitPer;
}

public double getTeleTwoHit() {
	return teleTwoHit;
}

public double getTeleTwoShot() {
	return teleTwoShot;
}

public double getTeleTwoTeamPer() {
	return teleTwoTeamPer;
}

public double getRealPMPer() {
	return realPMPer;
}

public double getPGMEff() {
	return PGMEff;
}
   
   
   
}
