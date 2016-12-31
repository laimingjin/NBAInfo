package po;
/**
 *@author:小春
 *@date:2015年5月19日下午7:25:37
 *@version
 */

public class PlayerBasicPO {
String playerID;
String playerName;
String teamID;
String teamName;//球队名称缩写
String school;//城市
String country;
String birthday;//生日
String position;//位置
String height;//身高
String weight;//体重
String numberSquad;//球衣号码
String fromYear;
String toYear;
public PlayerBasicPO(String playerID,String playerName,String teamID,String teamName, String school,String country, String birthday,
		String position, String height, String weight, String numberSquad,String fromYear,String toYear) {
	super();
	this.playerID=playerID;
	this.playerName=playerName;
	this.teamID=teamID;
	this.teamName = teamName;
	this.school = school;
	this.country=country;
	this.birthday = birthday;
	this.position = position;
	this.height = height;
	this.weight = weight;
	this.numberSquad = numberSquad;
	this.fromYear=fromYear;
	this.toYear=toYear;
}
public String getPlayerID(){
	return playerID;
}
public String getPlayerName(){
	return playerName;
}
public String getTeamID(){
	return teamID;
}
public String getTeamName() {
	return teamName;
}

public String getSchool() {
	return school;
}
public String getCountry(){
	return country;
}
public String getFromYear(){
	return fromYear;
}
public String getToYear(){
	return toYear;
}
public String getBirthday() {
	return birthday;
}

public String getPosition() {
	return position;
}

public String getHeight() {
	return height;
}

public String getWeight() {
	return weight;
}

public String getNumberSquad() {
	return numberSquad;
}

}

