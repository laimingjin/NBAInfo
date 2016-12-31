package dataServiceImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dataService.MatchDataService;
import dataService.PlayerDataService;
import dataService.TeamDataService;
import po.PlayerBasicPO;
import po.PlayerHighPO;
import po.PlayerTotalPO;
import readFiles.PlayerHighData_playoff;
import readFiles.PlayerHighData_regular;
import readFiles.PlayerLowData_allStar_read;
import readFiles.PlayerLowData_playoff_read;
import readFiles.PlayerLowData_regular_read;
import readFiles.Player_basic_read;

public class DataControl {
	PlayerDataService playerDataService=null;
//	TeamDataService teamDataService=null;
//	MatchDataService matchDataService=null;
	public DataControl() throws DatabaseException{
		playerDataService=new PlayerDataServiceImp();
//		teamDataService=new TeamDataServiceImp();
//		matchDataService=new MatchDataServiceImp();
	}
public void addPlayerBasicToDB() throws IOException, SQLException{
	ArrayList<PlayerBasicPO>pos=Player_basic_read.read_all_basic("/Users/mj/Downloads/nba_data/playerBasic");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_basic(pos.get(i));
	}
}
public void addPlayerLowData_playoffToDB() throws SQLException, IOException{
	ArrayList<PlayerTotalPO>pos=PlayerLowData_playoff_read.read_all("/Users/mj/Downloads/nba_data/playerLowData");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_low(pos.get(i));
	}
	System.out.println("ends");
}
public void addPlayerLowData_allStarToDB() throws SQLException, IOException{
	ArrayList<PlayerTotalPO>pos=PlayerLowData_allStar_read.read_all("/Users/mj/Downloads/nba_data/playerLowData");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_low(pos.get(i));
	}
	System.out.println("ends");
}

public void addPlayerLowDataToDB() throws IOException, SQLException{
	ArrayList<PlayerTotalPO>pos=PlayerLowData_regular_read.read_all("/Users/mj/Downloads/nba_data/playerLowData");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_low(pos.get(i));
	}
}
public void addPlayerHigh_regularToDB() throws IOException, SQLException{
	ArrayList<PlayerHighPO>pos=PlayerHighData_regular.read_all("/Users/mj/Downloads/nba_data/playerHighData_regular");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_highData(pos.get(i));
	}
	System.out.println("ends");
}
public void addPlayerHigh_playoffToDB() throws IOException, SQLException{
	ArrayList<PlayerHighPO>pos=PlayerHighData_playoff.read_all("/Users/mj/Downloads/nba_data/playerHighData_playoff");
	for(int i=0;i<pos.size();i++){
		playerDataService.addPlayer_highData(pos.get(i));
	}
	System.out.println("ends");
}
public static void main(String[]args) throws IOException, SQLException, DatabaseException{
	DataControl dataControl=new DataControl();
//	dataControl.addPlayerBasicToDB();
//	dataControl.addPlayerLowDataToDB();
//	dataControl.addPlayerLowData_playoffToDB();
//	dataControl.addPlayerLowData_allStarToDB();
//	dataControl.addPlayerHigh_regularToDB();
//	dataControl.addPlayerHigh_playoffToDB();
}
}
