package vo;

import po.TeamBasicPO;

/**
 *@author:小春
 *@date:2015年5月19日下午10:13:33
 *@version
 */

public class TeamBasicVO {

    String teamID;
	String teamName;//球队全名
    String abbreviation;//球队缩写
    String location;//所在地
    String zone;//赛区
    String team_partition;//分区
    String homeField;//主场
    int time_setUp;//建立时间
    
    public TeamBasicVO(String teamID,String teamName, String abbreviation, String location,
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
    
    public TeamBasicVO(Builder builder){
    	super();
    	this.teamID=builder.teamID;
		this.teamName = builder.teamName;
		this.abbreviation = builder.abbreviation;
		this.location = builder.location;
		this.zone = builder.zone;
		this.team_partition = builder.team_partition;
		this.homeField = builder.homeField;
		this.time_setUp = builder.time_setUp;
    }
    
    public static class Builder{
    	private final String teamID;
    	private final String teamName;//球队全名
    	private final String abbreviation;//球队缩写
    	private String location=" ";//所在地
    	private String zone=" ";//赛区
    	private String team_partition=" ";//分区
    	private String homeField=" ";//主场
    	private int time_setUp=-1;//建立时间
    	
    	public Builder (String teamID,String teamName, String abbreviation){
    		this.teamID=teamID;
    		this.teamName = teamName;
    		this.abbreviation = abbreviation;
    	}
    	
    	public Builder fromPO(TeamBasicPO po){
    		if (po!=null) {
    			this.location = po.getLocation();
    			this.zone = po.getZone();
    			this.team_partition = po.getTeam_partition();
    			this.homeField = po.getHomeField();
    			this.time_setUp = po.getTime_setUp();
			}
    		return this;
    	}
    	
    	public TeamBasicVO build(){
    		return new TeamBasicVO(this);
    	}
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
