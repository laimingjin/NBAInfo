package dataServiceImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import po.MembersPO;
import po.TeamBasicPO;
import po.TeamHighPO;
import po.TeamTotalPO;
import readFiles.MembersOfTeam_read;
import readFiles.TeamHighData_regularAndPlayoff;
import readFiles.TeamLowData_playeroff;
import readFiles.TeamLowData_regular;
import readFiles.Team_basic_read;
import dataService.MatchDataService;
import dataService.TeamDataService;
import enumerate.TypeOfMatch;

public class Team {
	TeamDataService teamDataService = null;

	// MatchDataService matchDataService=null;
	public Team() throws DatabaseException {
		teamDataService = new TeamDataServiceImp();
		// matchDataService=new MatchDataServiceImp();
	}

	public void addTeamBasic() throws NumberFormatException, IOException,
			SQLException {
		ArrayList<TeamBasicPO> pos = Team_basic_read
				.read("/Users/mj/Downloads/nba_data/teamBasic");
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.addTeam_basic(pos.get(i));
		}
	}

	public void addTeamLow_regular() throws SQLException, IOException {
		ArrayList<TeamTotalPO> pos = TeamLowData_regular
				.read_all("/Users/mj/Downloads/nba_data/teamLowData_regular");
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.addTeam_low(pos.get(i));
		}
	}

	public void addTeamLow_playoff() throws SQLException, IOException {
		ArrayList<TeamTotalPO> pos = TeamLowData_playeroff
				.read_all("/Users/mj/Downloads/nba_data/teamLowData_playoff");
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.addTeam_low(pos.get(i));
		}
	}
	public void addTeamHigh_regular() throws SQLException, IOException {
		ArrayList<TeamHighPO> pos = TeamHighData_regularAndPlayoff
				.read_all("/Users/mj/Downloads/nba_data/teamHighData_regular", TypeOfMatch.REGULAR);
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.addTeam_highData(pos.get(i));
		}
	}

	public void addTeamHigh_playoff() throws SQLException, IOException {
		ArrayList<TeamHighPO> pos = TeamHighData_regularAndPlayoff
				.read_all("/Users/mj/Downloads/nba_data/teamHighData_playoff",TypeOfMatch.PLAYOFF);
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.addTeam_highData(pos.get(i));
		}
	}
	public void addTeamMembers() throws IOException, SQLException{
		ArrayList<MembersPO>pos=MembersOfTeam_read.read_all("/Users/mj/Downloads/nba_data/teamMembers");
		for (int i = 0; i < pos.size(); i++) {
			teamDataService.add_memberOfTeam(pos.get(i));
		}
	}
	public static void main(String[] args) throws NumberFormatException,
			IOException, SQLException, DatabaseException {
		Team team = new Team();
		// team.addTeamBasic();
		// team.addTeamLow_regular();
//		team.addTeamLow_playoff();
//		team.addTeamHigh_regular();
//		team.addTeamHigh_playoff();
//		team.addTeamMembers();
	}
}
