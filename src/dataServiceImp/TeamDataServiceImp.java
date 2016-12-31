package dataServiceImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.MembersPO;
import po.TeamBasicPO;
import po.TeamHighPO;
import po.TeamTotalPO;
import dataService.TeamDataService;
import enumerate.TypeOfMatch;
//lowData中的TO是保留字，所以改成了TOV
public class TeamDataServiceImp implements TeamDataService{
	private final PreparedStatement add_team_basic;
	private final PreparedStatement add_team_lowData;
	private final PreparedStatement add_team_highData;
	private final PreparedStatement add_memberOfTeam;
	 private final Statement get_team_by_name;
	 private final Statement get_team_by_partition;
	 private final Statement get_team_by_abbreviation;
	 private final Statement get_low_exact;
	 private final Statement get_high_exact;
	 private final Statement get_team_all_high;
	 private final Statement get_team_all_low;
	 private final Statement get_all_high;
	 private final Statement get_all_low;
	 private final Statement get_member_of_team;
	 
//	 static{
//			try {
//				Database.init();
//			} catch (DatabaseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	  public TeamDataServiceImp() throws DatabaseException {
		// TODO Auto-generated constructor stub
		 Database database;
		
			database = Database.getInstance();
			this.add_team_basic=database.prepared_statement("insert into teambasicinfo values(?,?,?,?,?,?,?,?)");
			 this.add_team_lowData=database.prepared_statement("insert into teamlowdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			 this.add_team_highData=database.prepared_statement("insert into teamhighdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			this.add_memberOfTeam=database.prepared_statement("insert into membersofteam values(?,?,?,?)");
			 this.get_team_by_name=database.statement();
			 this.get_team_by_partition=database.statement();
			 this.get_team_by_abbreviation=database.statement();
		     this.get_low_exact=database.statement();
		     this.get_high_exact=database.statement();
		     this.get_team_all_high=database.statement();
		     this.get_team_all_low=database.statement();
		     this.get_all_high=database.statement();
		     this.get_all_low=database.statement();
		     this.get_member_of_team=database.statement();
	}
	  
	public TeamBasicPO getTeamDetail(String teamName) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from teambasicinfo where teamName= '"+teamName+"'";
		ResultSet rs=this.get_team_by_name.executeQuery(sql);
		ArrayList<TeamBasicPO>list=get_basic_from_rs(rs);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public ArrayList<TeamBasicPO> getTeamByPartition(String partition) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql="select *from teambasicinfo where team_partition= '"+partition+"'";
		ResultSet rs=this.get_team_by_partition.executeQuery(sql);
		ArrayList<TeamBasicPO>list=get_basic_from_rs(rs);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	public TeamBasicPO get_from_abbreviation(String abbreviation) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql="select *from teambasicinfo where abbreviation= '"+abbreviation+"'";
		ResultSet rs=this.get_team_by_abbreviation.executeQuery(sql);
		ArrayList<TeamBasicPO>list=get_basic_from_rs(rs);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public TeamTotalPO getTeamDetail_whole(String teamName,
			TypeOfMatch matchType, String matchSeason) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamlowdata where teamName= '"+teamName+"' and matchType= '"+type+"' and matchSeason= '"+matchSeason+"'";
		ResultSet rs=this.get_low_exact.executeQuery(sql);
		ArrayList<TeamTotalPO>list=get_low_from_rs(rs);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public TeamHighPO changeToHigh(TypeOfMatch matchType, String matchSeason,
			String teamName) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamhighdata where teamName= '"+teamName+"' and matchType= '"+type+"' and matchSeason= '"+matchSeason+"'";
		ResultSet rs=this.get_high_exact.executeQuery(sql);
		ArrayList<TeamHighPO>list=get_high_from_rs(rs);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

//	@Override
//	public TeamTotalPO changeToLow(TypeOfMatch matchType, String matchSeason,
//			String teamName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<TeamHighPO> getTeamAllHigh(String name,
			TypeOfMatch typeOfMatch) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfMatch==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfMatch==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamhighdata where teamName= '"+name+"' and matchType= '"+type+"'";
		ResultSet rs=this.get_team_all_high.executeQuery(sql);
		ArrayList<TeamHighPO>list=get_high_from_rs(rs);
		return list;
	}

	public ArrayList<TeamTotalPO> getTeamAllTotal(String name,
			TypeOfMatch typeOfMatch) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfMatch==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfMatch==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamlowdata where teamName= '"+name+"' and matchType= '"+type+"'";
		ResultSet rs=this.get_team_all_low.executeQuery(sql);
		ArrayList<TeamTotalPO>list=get_low_from_rs(rs);
		return list;
	}

	public ArrayList<TeamHighPO> getAllHigh(TypeOfMatch typeOfShow,
			String matchSeason) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfShow==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfShow==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamhighdata where matchSeason= '"+matchSeason+"' and matchType= '"+type+"'";
		ResultSet rs=this.get_all_high.executeQuery(sql);
		ArrayList<TeamHighPO>list=get_high_from_rs(rs);
		return list;
	}

	public ArrayList<TeamTotalPO> getAllTotal(TypeOfMatch typeOfShow,
			String matchSeason) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfShow==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfShow==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from teamlowdata where matchSeason= '"+matchSeason+"' and matchType= '"+type+"'";
		ResultSet rs=this.get_all_low.executeQuery(sql);
		ArrayList<TeamTotalPO>list=get_low_from_rs(rs);
		return list;
	}

	public void addTeam_basic(TeamBasicPO po) throws SQLException {
		// TODO Auto-generated method stub
		 String teamID=po.getTeamID();
			String teamName=po.getTeamName();//球队全名
		    String abbreviation=po.getAbbreviation();//球队缩写
		    String location=po.getLocation();//所在地
		    String zone=po.getZone();//赛区
		    String team_partition=po.getTeam_partition();//分区
		    String homeField=po.getHomeField();//主场
		    int time_setUp=po.getTime_setUp();//建立时间
		    
		    this.add_team_basic.setString(1, teamID);
		    this.add_team_basic.setString(2,teamName );
		    this.add_team_basic.setString(3,abbreviation );
		    this.add_team_basic.setString(4,location );
		    this.add_team_basic.setString(5,zone );
		    this.add_team_basic.setString(6,team_partition );
		    this.add_team_basic.setString(7,homeField );
		    this.add_team_basic.setInt(8,time_setUp );
		    this.add_team_basic.execute();
	}

	public void addTeam_low(TeamTotalPO po) throws SQLException {
		// TODO Auto-generated method stub
		TypeOfMatch matchType=po.getMatchType();//比赛类型
		String matchSeason=po.getMatchSeason();//赛季
		String teamID=po.getTeamID();
		String teamName=po.getTeamName();//球队名称全名
		int GP=po.getGP();//出场数
		int WIN=po.getWIN();//胜利场数
		int LOSS=po.getLOSS();
		int PTS=po.getPTS();//总得分
		int FGA=po.getFGA();//投篮出手数
		int FGM=po.getFGM();//投篮命中数
		String FGPer=po.getFGPer();//投篮命中率
		int TPA=po.getTPA();//三分出手数//原缩写为3PA
		int TPM=po.getTPM();//三分命中数//3PM
		String TPPer=po.getTPPer();//三分命中率//3P%
		int FTA=po.getFTA();//罚球出手数
		int FTM=po.getFTM();//罚球命中数
		String FTPer=po.getFTPer();//罚球命中率
		int REB=po.getREB();//篮板数
		int OREB=po.getOREB();//进攻篮板//前场篮板
		int DREB=po.getDREB();//防守篮板//后场篮板
		int AST=po.getAST();//助攻数
		int STL=po.getSTL();//抢断数
		int BLK=po.getBLK();//盖帽数
		int TO=po.getTO();//失误数
		int PF=po.getPF();//犯规数
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		this.add_team_lowData.setString(1, type);
		this.add_team_lowData.setString(2, matchSeason);
		this.add_team_lowData.setString(3, teamID);
		this.add_team_lowData.setString(4, teamName);
		this.add_team_lowData.setInt(5, GP);
		this.add_team_lowData.setInt(6, WIN);
		this.add_team_lowData.setInt(7, LOSS);
		this.add_team_lowData.setInt(8, PTS);
		this.add_team_lowData.setInt(9, FGA);
		this.add_team_lowData.setInt(10, FGM);
		this.add_team_lowData.setString(11,FGPer );
		this.add_team_lowData.setInt(12,TPA );
		this.add_team_lowData.setInt(13,TPM );
		this.add_team_lowData.setString(14,TPPer );
		this.add_team_lowData.setInt(15, FTA);
		this.add_team_lowData.setInt(16,FTM );
		this.add_team_lowData.setString(17, FTPer);
		this.add_team_lowData.setInt(18,REB );
		this.add_team_lowData.setInt(19, OREB);
		this.add_team_lowData.setInt(20, DREB);
		this.add_team_lowData.setInt(21, AST);
		this.add_team_lowData.setInt(22,STL );
		this.add_team_lowData.setInt(23,BLK );
		this.add_team_lowData.setInt(24, TO);
		this.add_team_lowData.setInt(25,PF );
		this.add_team_lowData.execute();
	}

	public void addTeam_highData(TeamHighPO po) throws SQLException {
		// TODO Auto-generated method stub
		TypeOfMatch matchType=po.getMatchType();//比赛类型
		String matchSeason=po.getMatchSeason();//赛季
		String teamID=po.getTeamID();
		String teamName=po.getTeamName();//球队名称全名
		int GP=po.getGP();//出场数
		int MIN=po.getMIN();
		double OffRtg=po.getOffRtg();
		double DefRtg=po.getDefRtg();
		double NetRtg=po.getNetRtg();
		double ASTPER=po.getASTPER();
		double AST_TO=po.getAST_TO();
		double ASTRatio=po.getASTRatio();
		double OREBPer=po.getOREBPer();
		double DREBPer=po.getDREBPer();
		double REBPer=po.getREBPer();
		double TORatio=po.getTORatio();
		double EFGPer=po.getEFGPer();
		double TSPer=po.getTSPer();
		double PACE=po.getPACE();
		double PIE=po.getPIE();
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		this.add_team_highData.setString(1, type);
		this.add_team_highData.setString(2, matchSeason);
		this.add_team_highData.setString(3, teamID);
		this.add_team_highData.setString(4, teamName);
		this.add_team_highData.setInt(5, GP);
		this.add_team_highData.setInt(6, MIN);
		this.add_team_highData.setDouble(7, OffRtg);
		this.add_team_highData.setDouble(8,DefRtg );
		this.add_team_highData.setDouble(9,NetRtg );
		this.add_team_highData.setDouble(10,ASTPER );
		this.add_team_highData.setDouble(11,AST_TO );
		this.add_team_highData.setDouble(12,ASTRatio );
		this.add_team_highData.setDouble(13,OREBPer );
		this.add_team_highData.setDouble(14,DREBPer );
		this.add_team_highData.setDouble(15,REBPer );
		this.add_team_highData.setDouble(16,TORatio );
		this.add_team_highData.setDouble(17,EFGPer );
		this.add_team_highData.setDouble(18,TSPer );
		this.add_team_highData.setDouble(19,PACE );
		this.add_team_highData.setDouble(20, PIE);
		this.add_team_highData.execute();
	}
private ArrayList<TeamBasicPO>get_basic_from_rs(ResultSet rs){
	ArrayList<TeamBasicPO>list=new ArrayList<TeamBasicPO>();
	try {
		while(rs.next()){
			 String teamID=rs.getString("teamID");
				String teamName=rs.getString("teamName");//球队全名
			    String abbreviation=rs.getString("abbreviation");//球队缩写
			    String location=rs.getString("location");//所在地
			    String zone=rs.getString("zone");//赛区
			    String team_partition=rs.getString("team_partition");//分区
			    String homeField=rs.getString("homeField");//主场
			    int time_setUp=rs.getInt("time_setUp");//建立时间
			    TeamBasicPO po=new TeamBasicPO(teamID, teamName, abbreviation, location, zone, team_partition, homeField, time_setUp);
			    list.add(po);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
private ArrayList<TeamTotalPO >get_low_from_rs(ResultSet rs){
	ArrayList<TeamTotalPO >list=new ArrayList<TeamTotalPO >();
	try {
		while(rs.next()){
			String matchType=rs.getString("matchType");//比赛类型
			String matchSeason=rs.getString("matchSeason");//赛季
			String teamID=rs.getString("teamID");
			String teamName=rs.getString("teamName");//球队名称全名
			int GP=rs.getInt("GP");//出场数
			int WIN=rs.getInt("WIN");//胜利场数
			int LOSS=rs.getInt("LOSS");
			int PTS=rs.getInt("PTS");//总得分
			int FGA=rs.getInt("FGA");//投篮出手数
			int FGM=rs.getInt("FGM");//投篮命中数
			String FGPer=rs.getString("FGPer");//投篮命中率
			int TPA=rs.getInt("TPA");//三分出手数//原缩写为3PA
			int TPM=rs.getInt("TPM");//三分命中数//3PM
			String TPPer=rs.getString("TPPer");//三分命中率//3P%
			int FTA=rs.getInt("FTA");//罚球出手数
			int FTM=rs.getInt("FTM");//罚球命中数
			String FTPer=rs.getString("FTPer");//罚球命中率
			int REB=rs.getInt("REB");//篮板数
			int OREB=rs.getInt("OREB");//进攻篮板//前场篮板
			int DREB=rs.getInt("DREB");//防守篮板//后场篮板
			int AST=rs.getInt("AST");//助攻数
			int STL=rs.getInt("STL");//抢断数
			int BLK=rs.getInt("BLK");//盖帽数
			int TO=rs.getInt("TOV");//失误数
			int PF=rs.getInt("PF");//犯规数
			TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			if(matchType.equals("playoff")){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(matchType.equals("allstar")){
				typeOfMatch=TypeOfMatch.ALLSTAR;
			}
			TeamTotalPO po=new TeamTotalPO(typeOfMatch, matchSeason, teamID, teamName, GP, WIN, LOSS, PTS, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TO, PF);
			list.add(po);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
private ArrayList<TeamHighPO>get_high_from_rs(ResultSet rs){
	ArrayList<TeamHighPO>list=new ArrayList<TeamHighPO>();
	try {
		while(rs.next()){
			String matchType=rs.getString("matchType");//比赛类型
			String matchSeason=rs.getString("matchSeason");//赛季
			String teamID=rs.getString("teamID");
			String teamName=rs.getString("teamName");//球队名称全名
			int GP=rs.getInt("GP");
			int MIN=rs.getInt("MIN");
			double OffRtg=rs.getDouble("OffRtg");
			double DefRtg=rs.getDouble("DefRtg");
			double NetRtg=rs.getDouble("NetRtg");
			double ASTPER=rs.getDouble("ASTPER");
			double AST_TO=rs.getDouble("AST_TO");
			double ASTRatio=rs.getDouble("ASTRatio");
			double OREBPer=rs.getDouble("OREBPer");
			double DREBPer=rs.getDouble("DREBPer");
			double REBPer=rs.getDouble("REBPer");
			double TORatio=rs.getDouble("TORatio");
			double EFGPer=rs.getDouble("EFGPer");
			double TSPer=rs.getDouble("TSPer");
			double PACE=rs.getDouble("PACE");
			double PIE=rs.getDouble("PIE");
			TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			if(matchType.equals("playoff")){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(matchType.equals("allstar")){
				typeOfMatch=TypeOfMatch.ALLSTAR;
			}
			TeamHighPO po=new TeamHighPO(typeOfMatch, matchSeason, teamID, teamName, GP, MIN, OffRtg, DefRtg, NetRtg, ASTPER, AST_TO, ASTRatio, OREBPer, DREBPer, REBPer, TORatio, EFGPer, TSPer, PACE, PIE);
			list.add(po);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
public void add_memberOfTeam(MembersPO po)throws SQLException{
	String matchSeason=po.getMatchSeason();
	String teamID=po.getTeamID();
	ArrayList<String>playerIDs=po.getPlayerIDs();
	String chiefCoach=po.getChiefCoach();
	String players="";
	if(playerIDs.size()>0){
		players=playerIDs.get(0);
		for(int i=1;i<playerIDs.size();i++){
			players=players+","+playerIDs.get(i);
		}
	
	}
	this.add_memberOfTeam.setString(1,teamID );
	this.add_memberOfTeam.setString(2, matchSeason);
	this.add_memberOfTeam.setString(3,players );
	this.add_memberOfTeam.setString(4,chiefCoach );
	this.add_memberOfTeam.execute();
}
public MembersPO getMemberOfTeam(String teamID, String matchSeason) throws SQLException {
	// TODO Auto-generated method stub
	String sql="select *from membersofteam where teamID= '"+teamID+"' and matchSeason= '"+matchSeason+"'";
	ResultSet rs=this.get_member_of_team.executeQuery(sql);
	while(rs.next()){
		ArrayList<String>playerIDs=new ArrayList<String>();
		String players=rs.getString("playerIDs");
		String chiefCoach=rs.getString("chiefCoach");
		if(players!=""){
			String ids[]=players.split(",");
			for(int i=0;i<ids.length;i++){
				playerIDs.add(ids[i]);
			}
		}
		MembersPO po=new MembersPO(teamID, matchSeason, playerIDs, chiefCoach);
		return po;
	}
	return null;
}
}
