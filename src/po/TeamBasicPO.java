package po;
/**
 *@author:小春
 *@date:2015年5月19日下午10:02:22
 *@version
 */

public class TeamBasicPO {
    String teamID;
	String teamName;//球队全名
    String abbreviation;//球队缩写
    String location;//所在地
    String zone;//赛区
    String team_partition;//分区
    String homeField;//主场
    int time_setUp;//建立时间
    
    public TeamBasicPO(String teamID,String teamName, String abbreviation, String location,
			String zone, String team_partition, String homeField, int time_setUp) {
		super();
		this.teamID=teamID;
		this.teamName = teamName;
		this.abbreviation = abbreviation;
		this.location = location;
		this.zone = zone;
		this.team_partition = team_partition;
		this.homeField = homeField;
		this.time_setUp = time_setUp;
	}
    public String getTeamID(){
    	return teamID;
    }
	public String getTeamName() {
		return teamName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public String getLocation() {
		return location;
	}
	public String getZone() {
		return zone;
	}
	public String getTeam_partition() {
		return team_partition;
	}
	public String getHomeField() {
		return homeField;
	}
	public int getTime_setUp() {
		return time_setUp;
	}

    
}
