package vo;

import po.PlayerBasicPO;

/**
 * @author:小春
 * @date:2015年5月19日下午7:39:30
 * @version
 */

public class PlayerBasicVO {
	
	/**
	 *@author:小春
	 *@date:2015年5月19日下午7:25:37
	 *@version
	 */

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
	public PlayerBasicVO(String playerID,String playerName,String teamID,String teamName, String school,String country, String birthday,
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
	public PlayerBasicVO(PlayerBasicPO po){
		super();
		this.playerID=po.getPlayerID();
		this.playerName=po.getPlayerName();
		this.teamID=po.getTeamID();
		this.teamName = po.getTeamName();// 球队名称缩写
		this.school = po.getSchool();// 
		this.country=po.getCountry();
		this.birthday = po.getBirthday();// 生日
		this.position = po.getPosition();// 位置
		this.height = po.getHeight();// 身高
		this.weight = po.getWeight();// 体重
		this.numberSquad = po.getNumberSquad();// 球衣号码
	    this.fromYear=po.getFromYear();
	    this.toYear=po.getToYear();
	}
	public String getPlayerName(){
		return playerName;
	}
	public String getTeamName() {
		return teamName;
	}
public String getPlayerID(){
	return playerID;
	
}
public String getTeamID(){
	return teamID;
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

	


	
	

	
	private PlayerBasicVO(Builder builder){
		this.playerID=builder.playerID;
		this.playerName =builder.playerName ;
		this.teamID=builder.teamID;
		this.teamName = builder.teamName;
		this.school = builder.school;
		this.country=builder.country;
		this.birthday = builder.birthday;
		this.position = builder.position;
		this.height = builder.height;
		this.weight = builder.weight;
		this.numberSquad = builder.numberSquad;
		this.fromYear=builder.fromYear;
		this.toYear=builder.toYear;
	}
	public static class Builder {
		private final String playerID;
		private final String playerName;
		private String teamID=" ";
		private String teamName = " ";// 球队名称缩写
		private String school = " ";// 
		private String country=" ";
		private String birthday = " ";// 生日
		private String position = " ";// 位置
		private String height = " ";// 身高
		private String weight = " ";// 体重
		private String numberSquad = " ";// 球衣号码
		private String fromYear = " ";
		private String toYear = " ";
		public Builder(String id,String name) {
			this.playerID=id;
			this.playerName = name;
		}

		public Builder basic(PlayerBasicPO po) {
			if (po != null) {
				this.teamID=po.getTeamID();
				this.teamName = po.getTeamName();// 球队名称缩写
				this.school = po.getSchool();// 
				this.country=po.getCountry();
				this.birthday = po.getBirthday();// 生日
				this.position = po.getPosition();// 位置
				this.height = po.getHeight();// 身高
				this.weight = po.getWeight();// 体重
				this.numberSquad = po.getNumberSquad();// 球衣号码
			    this.fromYear=po.getFromYear();
			    this.toYear=po.getToYear();
			}

			return this;
		}
		public PlayerBasicVO build(){
			return new PlayerBasicVO(this);
		}
	}



}
