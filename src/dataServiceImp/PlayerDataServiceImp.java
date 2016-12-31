package dataServiceImp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;













import po.PlayerBasicPO;
import po.PlayerHighPO;
import po.PlayerTotalPO;
import dataService.PlayerDataService;
import enumerate.TypeOfMatch;

public class PlayerDataServiceImp implements PlayerDataService{
	static String[] matchTypes={"regular","playoff","allstar"};
	private final PreparedStatement add_player_basic;
	private final PreparedStatement add_player_lowData;
	private final PreparedStatement add_player_highData;
	 private final Statement find_by_number;
	 private final Statement find_by_playerName;
	 private final Statement find_by_teamName;
	 private final Statement find_by_position;
	 private final Statement find_high;
	 private final Statement find_low;
	 private final Statement find_basic;
	 private final Statement find_playerOfTeam;
	 private final Statement find_player_high;
	 private final Statement get_all_low;
	 private final Statement get_all_high;
	private final Statement get_all_basic;
//	static{
//		try {
//			Database.init();
//		} catch (DatabaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public PlayerDataServiceImp() throws DatabaseException{
		 Database database=Database.getInstance();
	       this.add_player_basic=database.prepared_statement("insert into playerbasicinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	       this.add_player_lowData=database.prepared_statement("insert into playerlowdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	       this.add_player_highData=database.prepared_statement("insert into playerhighdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	       this.find_by_number=database.statement();
	       this.find_by_playerName=database.statement();
	       this.find_by_teamName=database.statement();
	       this.find_by_position=database.statement();
	       this.find_basic=database.statement();
	       this.find_low=database.statement();
	       this.find_high=database.statement();
	       this.find_playerOfTeam=database.statement();
	       this.find_player_high=database.statement();
	       this.get_all_low=database.statement();
	       this.get_all_high=database.statement();
	       this.get_all_basic=database.statement();
	} 
	
	public static void main(String[]args) throws DatabaseException, SQLException, IOException{
		PlayerDataServiceImp player=new PlayerDataServiceImp();
//		ArrayList<PlayerTotalPO>pos=player.searchPlayersByName("A", TypeOfMatch.REGULAR, "2014-15");
//		System.out.println(pos.size());
//		player.getAllBasic();
//		player.test();
	ArrayList<PlayerTotalPO>pos=	player.searchPlayersByNum("3", TypeOfMatch.REGULAR, "2014-15");
	for(int i=0;i<pos.size();i++){
		System.out.println(pos.get(i).getPlayerName());
	}
	System.out.println("ends");
	}
	
	public ArrayList<PlayerTotalPO> searchPlayersByNum(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		ArrayList<PlayerTotalPO> pos=new ArrayList<PlayerTotalPO>();
		String word="%"+word_Search+"%";
//		String sql1="select *from playerbasicinfo where numberSquad like '"+word+"'";
//		ResultSet rs1= this.find_by_number.executeQuery(sql1);
//		ArrayList<PlayerBasicPO>all=new ArrayList<PlayerBasicPO>();
//		all=get_basic_pos_from_rs(rs1);
//
//		
//		for(int i=0;i<all.size();i++){
////			String sql="select *from playerlowdata with(index(playerID)) where playerID= '"+all.get(i).getPlayerID()+"' and  matchType= '"+type+"' and matchSeason= '"+yearNumber+"'";
//			String sql="select *from playerlowdata  where matchType= '"+type+"' and matchSeason= '"+yearNumber+"' and  playerID= '"+all.get(i).getPlayerID()+"'";
//			ResultSet rs= this.find_by_number.executeQuery(sql);
//			ArrayList<PlayerTotalPO> temp=get_low_pos_from_rs(rs);
//			if(temp.size()>0){
//				for(int j=0;j<temp.size();j++){
//					pos.add(temp.get(j));
//				}
//				
//			}
//			
//		}
//		
		String sql="select *from playerbasicinfo inner join playerlowdata where playerbasicinfo.numberSquad like '"+word+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'and playerbasicinfo.playerID=playerlowdata.playerID";
		ResultSet rs=this.find_by_number.executeQuery(sql);
		pos=get_low_pos_from_rs(rs);
 
		
		return pos;
	}

	public ArrayList<PlayerTotalPO> searchPlayersByName(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		ArrayList<PlayerTotalPO>pos=new ArrayList<PlayerTotalPO>();
//		String sql1="select *from playerbasicinfo";
//		ResultSet rs1= this.find_by_playerName.executeQuery(sql1);
//		ArrayList<PlayerBasicPO>all=new ArrayList<PlayerBasicPO>();
//		all=get_basic_pos_from_rs(rs1);
//		ArrayList<PlayerBasicPO>basicPOs=new ArrayList<PlayerBasicPO>();
//		for(int k=0;k<all.size();k++){
//			if(all.get(k).getPlayerName().contains(word_Search)){
//				String sql="select *from playerlowdata where playerID= '"+all.get(k).getPlayerID()+"' and  matchType= '"+type+"' and matchSeason= '"+yearNumber+"'";
//				ResultSet rs= this.find_by_playerName.executeQuery(sql);
//				ArrayList<PlayerTotalPO> temp=get_low_pos_from_rs(rs);
//				if(temp.size()>0){
//					for(int j=0;j<temp.size();j++){
//						pos.add(temp.get(j));
//					}
////				basicPOs.add(all.get(k));
//			}
//		}
		
//		for(int i=0;i<basicPOs.size();i++){
//			String sql="select *from playerlowdata where playerID= '"+basicPOs.get(i).getPlayerID()+"' and  matchType= '"+type+"' and matchSeason= '"+yearNumber+"'";
//			ResultSet rs= this.find_by_playerName.executeQuery(sql);
//			ArrayList<PlayerTotalPO> temp=get_low_pos_from_rs(rs);
//			if(temp.size()>0){
//				for(int j=0;j<temp.size();j++){
//					pos.add(temp.get(j));
//				}
//				
//			}
//			
		
//		String sql="select *from playerlowdata where playerName='"+word_Search+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'";
//		ResultSet rs= this.find_by_playerName.executeQuery(sql);
//		pos=get_low_pos_from_rs(rs);
	String word="%"+word_Search+"%";
	String sql="select *from playerbasicinfo inner join playerlowdata where playerbasicinfo.playerName like '"+word+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'and playerbasicinfo.playerID=playerlowdata.playerID";
	ResultSet rs=this.find_by_playerName.executeQuery(sql);
	pos=get_low_pos_from_rs(rs);
		
		
		return pos;
	}

	public ArrayList<PlayerTotalPO> searchPlayersByTeam(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<PlayerTotalPO>pos=new ArrayList<PlayerTotalPO>();
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String word="%"+word_Search+"%";
		String sql="select *from playerbasicinfo inner join playerlowdata where playerbasicinfo.teamName like '"+word+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'and playerbasicinfo.playerID=playerlowdata.playerID";
		ResultSet rs=this.find_by_teamName.executeQuery(sql);
		pos=get_low_pos_from_rs(rs);
//		String sql="select *from playerlowdata where teamName='"+word_Search+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'";
//		ResultSet rs= this.find_by_teamName.executeQuery(sql);
//		pos=get_low_pos_from_rs(rs);
//		ArrayList<PlayerTotalPO>pos=new ArrayList<PlayerTotalPO>();
//		String sql1="select *from playerbasicinfo";
//		ResultSet rs1= this.find_by_teamName.executeQuery(sql1);
//		ArrayList<PlayerBasicPO>all=new ArrayList<PlayerBasicPO>();
//		all=get_basic_pos_from_rs(rs1);
//		ArrayList<PlayerBasicPO>basicPOs=new ArrayList<PlayerBasicPO>();
//		for(int k=0;k<all.size();k++){
//			if(all.get(k).getTeamName().contains(word_Search)){
//				basicPOs.add(all.get(k));
//			}
//		}
//		String type="";
//		if(matchType==TypeOfMatch.REGULAR){
//			type="regular";
//		}else if(matchType==TypeOfMatch.PLAYOFF){
//			type="playoff";
//		}else{
//			type="allstar";
//		}
//		for(int i=0;i<basicPOs.size();i++){
//			String sql="select *from playerlowdata where playerID= '"+basicPOs.get(i).getPlayerID().trim()+"' and  matchType= '"+type+"' and matchSeason= '"+yearNumber+"'";
//			ResultSet rs= this.find_by_teamName.executeQuery(sql);
//			ArrayList<PlayerTotalPO> temp=get_low_pos_from_rs(rs);
//			if(temp.size()>0){
//				for(int j=0;j<temp.size();j++){
//					pos.add(temp.get(j));
//				}
//				
//			}
//			
//		}
//		
		
		return pos;
	}

	public ArrayList<PlayerTotalPO> searchPlayersByPosition(String word_Search,
			TypeOfMatch matchType, String yearNumber) throws SQLException {
		// TODO Auto-generated method stub

		ArrayList<PlayerTotalPO> pos=new ArrayList<PlayerTotalPO>();
//		String sql1="select *from playerbasicinfo ";
//		ResultSet rs1= this.find_by_position.executeQuery(sql1);
//		ArrayList<PlayerBasicPO>basicPOs=new ArrayList<PlayerBasicPO>();
//		basicPOs=get_basic_pos_from_rs(rs1);
		
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String word="%"+word_Search+"%";
		String sql="select *from playerbasicinfo inner join playerlowdata where playerbasicinfo.position like '"+word+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'and playerbasicinfo.playerID=playerlowdata.playerID";
		ResultSet rs=this.find_by_position.executeQuery(sql);
		pos=get_low_pos_from_rs(rs);
//		ArrayList<PlayerBasicPO>newPOs=new ArrayList<PlayerBasicPO>();
//		for(int i=0;i<basicPOs.size();i++){
//			PlayerBasicPO po=basicPOs.get(i);
////			if(!po.getPosition().equals("")){
////				String ii[]=po.getPosition().split("-");
////				String newposition="";
////				if(ii.length==1){
////					newposition=ii[0].substring(0, 1);
////				}else if(ii.length==2){
////					newposition=ii[0].substring(0, 1)+"-"+ii[1].substring(0, 1);
////				}
////			}
//			if(po.getPosition().contains(word_Search)){
//				newPOs.add(po);
//			}
//			
//		}
//		if(newPOs.size()>0){
//			for(int i=0;i<newPOs.size();i++){
//			String sql="select *from playerlowdata where playerID='"+newPOs.get(i).getPlayerID()+"' and matchType='"+type+"' and matchSeason='"+yearNumber+"'";
//			ResultSet rs= this.find_by_position.executeQuery(sql);
//			ArrayList<PlayerTotalPO> temp=get_low_pos_from_rs(rs);
//			if(temp.size()>0){
//				for(int j=0;j<temp.size();j++){
//					pos.add(temp.get(j));
//				}
//				
//			}
//			
//		}
//		}
		
		
		
		return pos;
	}

	public PlayerBasicPO getPlayerDetail(String playerID) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from playerbasicinfo where playerID='"+playerID+"'";
		ResultSet rs=this.find_basic.executeQuery(sql);
		ArrayList<PlayerBasicPO>basic_pos=get_basic_pos_from_rs(rs);
		if(basic_pos.size()>0){
			return basic_pos.get(0);
		}
		return null;
	}

	public PlayerHighPO getPlayerHigh(String playerID, TypeOfMatch matchType,
			String matchSeason) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from playerhighdata where playerID='"+playerID+"' and matchType='"+type+"' and matchSeason='"+matchSeason+"'";
		ResultSet rs= this.find_high.executeQuery(sql);
		ArrayList<PlayerHighPO>high_POs=get_high_pos_from_rs(rs);
		if(high_POs.size()>0){
			return high_POs.get(0);////////////////////////////////如果有同名的球员而且同一年打比赛呢
		}
		return null;
	}

	public PlayerTotalPO getPlayerTotal(String playerID, TypeOfMatch matchType,
			String matchSeason) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from playerlowdata where playerID='"+playerID+"' and matchType='"+type+"' and matchSeason='"+matchSeason+"'";
		ResultSet rs= this.find_low.executeQuery(sql);
		ArrayList<PlayerTotalPO>low_POs=get_low_pos_from_rs(rs);
		if(low_POs.size()>0){
			return low_POs.get(0);////////////////////////////////如果有同名的球员而且同一年打比赛呢
		}
		return null;
	}

	public ArrayList<PlayerTotalPO> getplayerAllTotal(String playerID,
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
		String sql="select *from playerlowdata where playerID= '"+playerID+"' and matchType= '"+type+"'";
		ResultSet rs= this.find_low.executeQuery(sql);
		ArrayList<PlayerTotalPO>low_POs=get_low_pos_from_rs(rs);
		if(low_POs.size()>0){
			return low_POs;////////////////////////////////如果有同名的球员而且同一年打比赛呢
		}
		return null;
	}

	public ArrayList<PlayerHighPO> getplayerAllHigh(String playerID,
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
		String sql="select *from playerhighdata where playerID='"+playerID+"' and matchType='"+type+"'";
		ResultSet rs= this.find_high.executeQuery(sql);
		ArrayList<PlayerHighPO>high_POs=get_high_pos_from_rs(rs);
		if(high_POs.size()>0){
			return high_POs;////////////////////////////////如果有同名的球员而且同一年打比赛呢
		}
		return null;
	}

	public ArrayList<PlayerBasicPO> getPlayerOfTeam(String teamID)
			throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from playerbasicinfo where teamID='"+teamID+"'";
		ResultSet rs=this.find_playerOfTeam.executeQuery(sql);
		ArrayList<PlayerBasicPO>pos=get_basic_pos_from_rs(rs);
		if(pos.size()>0){
			return pos;
		}
		return null;
	}


//	public ArrayList<PlayerBasicPO> getPlayerOfTeam(String teamID)
//			throws SQLException {
//		// TODO Auto-generated method stub
//		String sql="select *from playerbasicinfo where teamID='"+teamID+"'";
//		ResultSet rs=this.find_playerOfTeam.executeQuery(sql);
//		ArrayList<PlayerBasicPO>pos=get_basic_pos_from_rs(rs);
//		if(pos.size()>0){
//			return pos;
//		}
//		return null;
//	}

	public ArrayList<PlayerHighPO> getAllHigh(TypeOfMatch typeOfShow,
			String yearNumber) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfShow==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfShow==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from playerhighdata where  matchType='"+type+"' and matchSeason='"+yearNumber+"'";
		ResultSet rs= this.get_all_high.executeQuery(sql);
		ArrayList<PlayerHighPO>high_POs=get_high_pos_from_rs(rs);
//		if(high_POs.size()>0){
			return high_POs;
//		}
//		return null;
	}

	public ArrayList<PlayerTotalPO> getAllTotal(TypeOfMatch typeOfShow,
			String yearNumber) throws SQLException {
		// TODO Auto-generated method stub
		String type="";
		if(typeOfShow==TypeOfMatch.REGULAR){
			type="regular";
		}else if(typeOfShow==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		String sql="select *from playerlowdata where  matchType= '"+type+"' and matchSeason= '"+yearNumber+"'";
		ResultSet rs= this.get_all_low.executeQuery(sql);
		ArrayList<PlayerTotalPO>low_POs=get_low_pos_from_rs(rs);
//		if(low_POs.size()>0){
			return low_POs;
//		}
//		return null;
	}

	public void addPlayer_basic(PlayerBasicPO po) throws SQLException {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			
			String playerID=po.getPlayerID();
			String	playerName=po.getPlayerName();
			String teamID=po.getTeamID();
			String	teamName=po.getTeamName();
			String	school=po.getSchool();
			String	country=po.getCountry();
			String	numberSquad=po.getNumberSquad();
			String	position=po.getPosition();
			String	height=po.getHeight();
			String	weight=po.getWeight();
			String	birthday=po.getBirthday();
			String	fromYear=po.getFromYear();
			String	toYear=po.getToYear();
			
			this.add_player_basic.setString(1, playerID);
			this.add_player_basic.setString(2, playerName);
		    this.add_player_basic.setString(3, teamID);
			this.add_player_basic.setString(4, teamName);
			this.add_player_basic.setString(5, school);
			this.add_player_basic.setString(6, country);
			this.add_player_basic.setString(7, birthday);
			this.add_player_basic.setString(8, position);
			this.add_player_basic.setString(9, height);
			this.add_player_basic.setString(10, weight);
			this.add_player_basic.setString(11, numberSquad);
			this.add_player_basic.setString(12, fromYear);
			this.add_player_basic.setString(13, toYear);
			
			 this.add_player_basic.execute();
			return ;
			
		
	}

	public void addPlayer_low(PlayerTotalPO po) throws SQLException {
		// TODO Auto-generated method stub
		TypeOfMatch matchType=po.getMatchType();//比赛类型
		String matchSeason=po.getMatchSeason();//赛季
		String playerID=po.getPlayerID();
		String playerName=po.getPlayerName();//球员名称
		String teamID=po.getTeamID();
		String teamName=po.getTeamName();//球队名称缩写
		int GP=po.getGP();// 出场数
		int GS=po.getGS();// 首发出场数
		int MIN=po.getMIN();// 出场时间
//		int WIN;// 胜利场数
		int PTS=po.getPTS();// 总得分
		int FGA=po.getFGA();// 投篮出手数
		int FGM=po.getFGM();// 投篮命中数
		String FGPer=po.getFGPer();// 投篮命中率
		int TPA=po.getTPA();// 三分出手数//原缩写为3PA
		int TPM=po.getTPM();// 三分命中数//3PM
		String TPPer=po.getTPPer();// 三分命中率//3P%
		int FTA=po.getFTA();// 罚球出手数
		int FTM=po.getFTM();// 罚球命中数
		String FTPer=po.getFTPer();// 罚球命中率
		int REB=po.getREB();// 篮板数
		int OREB=po.getOREB();// 进攻篮板//前场篮板
		int DREB=po.getDREB();// 防守篮板//后场篮板
		int AST=po.getAST();// 助攻数
		int STL=po.getSTL();// 抢断数
		int BLK=po.getBLK();// 盖帽数
		int TOV=po.getTOV();// 失误数
		int PF=po.getPF();// 犯规数
		double EffPer=po.getEffPer();
		String type="";
		if(matchType==TypeOfMatch.REGULAR){
			type="regular";
		}else if(matchType==TypeOfMatch.PLAYOFF){
			type="playoff";
		}else{
			type="allstar";
		}
		this.add_player_lowData.setString(1,playerID );
		this.add_player_lowData.setString(2,playerName );
		this.add_player_lowData.setString(3,type );
		this.add_player_lowData.setString(4,matchSeason );
		this.add_player_lowData.setString(5,teamID );
		this.add_player_lowData.setString(6, teamName);
		this.add_player_lowData.setInt(7,GP );
		this.add_player_lowData.setInt(8, GS);
		this.add_player_lowData.setInt(9, MIN);
		this.add_player_lowData.setInt(10, PTS);
		this.add_player_lowData.setInt(11,FGA );
		this.add_player_lowData.setInt(12,FGM );
		
		this.add_player_lowData.setString(13, FGPer);
		this.add_player_lowData.setInt(14,TPA );
		this.add_player_lowData.setInt(15, TPM);
		this.add_player_lowData.setString(16,TPPer );
		this.add_player_lowData.setInt(17, FTA);
		this.add_player_lowData.setInt(18,FTM );
		this.add_player_lowData.setString(19, FTPer);
		this.add_player_lowData.setInt(20, REB);
		this.add_player_lowData.setInt(21,OREB );
		this.add_player_lowData.setInt(22,DREB );
		this.add_player_lowData.setInt(23,AST );
		this.add_player_lowData.setInt(24, STL);
		this.add_player_lowData.setInt(25,BLK );
		this.add_player_lowData.setInt(26,TOV );
		this.add_player_lowData.setInt(27,PF );
		this.add_player_lowData.setDouble(28, EffPer);
		this.add_player_lowData.execute();
	}

	public void addPlayer_highData(PlayerHighPO po) throws SQLException {
		// TODO Auto-generated method stub
		TypeOfMatch matchType=po.getMatchType();//比赛类型
		String matchSeason=po.getMatchSeason();//赛季
		String playerID=po.getPlayerID();
		String playerName=po.getPlayerName();//球员名称
		String teamID=po.getTeamID();
		String teamName=po.getTeamName();//球队名称缩写
		int GP=po.getGP();
		double MIN=po.getMIN();
		int WIN=po.getWIN();
		int LOSS=po.getLOSS();
		double W_PCT=po.getW_PCT();
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
		double USGPer=po.getUSGPer();
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
		this.add_player_highData.setString(1,playerID );
		this.add_player_highData.setString(2,playerName );
		this.add_player_highData.setString(3,type );
		this.add_player_highData.setString(4, matchSeason);
		this.add_player_highData.setString(5, teamID);
		this.add_player_highData.setString(6,teamName );
		this.add_player_highData.setInt(7, GP);
		this.add_player_highData.setDouble(8, MIN);
		this.add_player_highData.setInt(9, WIN);
		this.add_player_highData.setInt(10, LOSS);
		this.add_player_highData.setDouble(11,W_PCT );
		this.add_player_highData.setDouble(12, OffRtg);
		this.add_player_highData.setDouble(13, DefRtg);
		this.add_player_highData.setDouble(14, NetRtg);
		this.add_player_highData.setDouble(15, ASTPER);
		this.add_player_highData.setDouble(16,AST_TO );
		this.add_player_highData.setDouble(17,ASTRatio );
		this.add_player_highData.setDouble(18, OREBPer);
		this.add_player_highData.setDouble(19, DREBPer);
		this.add_player_highData.setDouble(20, REBPer);
		this.add_player_highData.setDouble(21,TORatio );
		this.add_player_highData.setDouble(22, EFGPer);
		this.add_player_highData.setDouble(23, TSPer);
		this.add_player_highData.setDouble(24,USGPer );
		this.add_player_highData.setDouble(25,PACE );
		this.add_player_highData.setDouble(26,PIE );
		this.add_player_highData.execute();
	}
	private ArrayList<PlayerTotalPO>get_low_pos_from_rs(ResultSet rs) throws SQLException{
		ArrayList<PlayerTotalPO> pos=new ArrayList<PlayerTotalPO>();
		while(rs.next()){
			String matchType=rs.getString("matchType");//比赛类型
			String matchSeason=rs.getString("matchSeason");//赛季
			String playerID=rs.getString("playerID");
			String playerName=rs.getString("playerName");//球员名称
			String teamID=rs.getString("teamID");
			String teamName=rs.getString("teamName");//球队名称缩写
			int GP=rs.getInt("GP");// 出场数
			int GS=rs.getInt("GS");// 首发出场数
			int MIN=rs.getInt("MIN");// 出场时间
//			int WIN;// 胜利场数
			int PTS=rs.getInt("PTS");// 总得分
			int FGA=rs.getInt("FGA");// 投篮出手数
			int FGM=rs.getInt("FGM");// 投篮命中数
			String FGPer=rs.getString("FGPer");// 投篮命中率
			int TPA=rs.getInt("TPA");// 三分出手数//原缩写为3PA
			int TPM=rs.getInt("TPM");// 三分命中数//3PM
			String TPPer=rs.getString("TPPer");// 三分命中率//3P%
			int FTA=rs.getInt("FTA");// 罚球出手数
			int FTM=rs.getInt("FTM");// 罚球命中数
			String FTPer=rs.getString("FTPer");// 罚球命中率
			int REB=rs.getInt("REB");// 篮板数
			int OREB=rs.getInt("OREB");// 进攻篮板//前场篮板
			int DREB=rs.getInt("DREB");// 防守篮板//后场篮板
			int AST=rs.getInt("AST");// 助攻数
			int STL=rs.getInt("STL");// 抢断数
			int BLK=rs.getInt("BLK");// 盖帽数
			int TOV=rs.getInt("TOV");// 失误数
			int PF=rs.getInt("PF");// 犯规数
			double EffPer=rs.getDouble("EffPer");
			
			TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			if(matchType.equals("playoff")){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(matchType.equals("allstar")){
				typeOfMatch=TypeOfMatch.ALLSTAR;
			}
			PlayerTotalPO po=new PlayerTotalPO(typeOfMatch, matchSeason, playerID, playerName, teamID, teamName, GP, GS, MIN, PTS, FGA, FGM, FGPer, TPA, TPM, TPPer, FTA, FTM, FTPer, REB, OREB, DREB, AST, STL, BLK, TOV, PF,EffPer);
			pos.add(po);
		}
		return pos;
	}
	private ArrayList<PlayerBasicPO>get_basic_pos_from_rs(ResultSet rs) throws SQLException{
		 ArrayList<PlayerBasicPO> pos=new  ArrayList<PlayerBasicPO>();
		 while(rs.next()){
			 String playerID=rs.getString("playerID");
			 String playerName=rs.getString("playerName");
			 String teamID=rs.getString("teamID");
			 String teamName=rs.getString("teamName");//球队名称缩写
			 String school=rs.getString("school");//城市
			 String country=rs.getString("country");
			 String birthday=rs.getString("birthday");//生日
			 String position=rs.getString("position");//位置
			 String height=rs.getString("height");//身高
			 String weight=rs.getString("weight");//体重
			 String numberSquad=rs.getString("numberSquad");//球衣号码
			 String fromYear=rs.getString("fromYear");
			 String toYear=rs.getString("toYear");
			 pos.add(new PlayerBasicPO(playerID, playerName,teamID, teamName, school, country, birthday, position, height, weight, numberSquad, fromYear, toYear));
			 
		 }
		 return pos;
	}
	private ArrayList<PlayerHighPO>get_high_pos_from_rs(ResultSet rs) throws SQLException{
		ArrayList<PlayerHighPO> pos=new ArrayList<PlayerHighPO>();
		while (rs.next()){
			String matchType=rs.getString("matchType");//比赛类型
			String matchSeason=rs.getString("matchSeason");//赛季
			String playerID=rs.getString("playerID");
			String playerName=rs.getString("playerName");//球员名称
			String teamID=rs.getString("teamID");
			String teamName=rs.getString("teamName");//球队名称缩写
			int GP=rs.getInt("GP");
			double MIN=rs.getDouble("MIN");
			int WIN=rs.getInt("WIN");
			int LOSS=rs.getInt("LOSS");
			double W_PCT=rs.getDouble("W_PCT");
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
			double USGPer=rs.getDouble("USGPer");
			double PACE=rs.getDouble("PACE");
			double PIE=rs.getDouble("PIE");
			
			
			
			TypeOfMatch typeOfMatch=TypeOfMatch.REGULAR;
			if(matchType.equals("playoff")){
				typeOfMatch=TypeOfMatch.PLAYOFF;
			}else if(matchType.equals("allstar")){
				typeOfMatch=TypeOfMatch.ALLSTAR;
			}
			PlayerHighPO po=new PlayerHighPO(typeOfMatch, matchSeason, playerID, playerName, teamID, teamName, GP, MIN, WIN, LOSS, W_PCT, OffRtg, DefRtg, NetRtg, ASTPER, AST_TO, ASTRatio, OREBPer, DREBPer, REBPer, TORatio, EFGPer, TSPer, USGPer, PACE, PIE);
			pos.add(po);
		}
		return pos;
	}
	public void test() throws SQLException, IOException{
		BufferedWriter bw=new BufferedWriter(new FileWriter("team"));
		BufferedReader br=new BufferedReader(new FileReader("D://nba_data/teamPair_name_id.out"));
		String line=null;
		while((line=br.readLine())!=null){
			
		}
		String sql="select *from playerbasicinfo ";
		ResultSet rs=this.find_basic.executeQuery(sql);
		ArrayList<PlayerBasicPO>pos=get_basic_pos_from_rs(rs);
		for(int i=0;i<pos.size();i++){
			PlayerBasicPO po=pos.get(i);
			System.out.println(i);
			bw.write(po.getPlayerID()+","+po.getPlayerName()+","+po.getTeamName()+","+po.getNumberSquad()+","+po.getPosition());
		    bw.write("\t\n");
		}
		bw.flush();
		bw.close();
	}
}
