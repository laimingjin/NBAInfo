package blServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import po.MatchBasicPO;
import po.PlayerInMatchPO;
import po.TeamInMatchPO;
import vo.MatchBasicVO;
import vo.PlayerInMatchVO;
import vo.TeamInMatchVO;
import bl.tool.PlayerMatchSortTool;
import bl.tool.PlayerMatchVOSortTool;
import bl.tool.SeasonCheckTool;
import bl.tool.TeamMatchSortTool;
import blService.MatchBLService;
import dataService.MatchDataService;
import dataServiceImp.DatabaseException;
import dataServiceImp.MatchDataServiceImp;

/**
 * @author:小春
 * @date:2015年6月2日下午11:13:08
 * @version
 */

public class MatchBLImp implements MatchBLService {

	private MatchDataService matchDataService;

	public MatchBLImp() {
		try {
			matchDataService = new MatchDataServiceImp();
		} catch (DatabaseException e) {

			e.printStackTrace();
		}
	}

	public ArrayList<MatchBasicVO> searchMatchByTime(String matchTime,
			String teamName) {
		ArrayList<MatchBasicVO> result = new ArrayList<MatchBasicVO>();
		ArrayList<MatchBasicPO> tempList = new ArrayList<MatchBasicPO>();
		if (!matchTime.equals("")) {
			try {
				tempList = matchDataService.getBasicByTime(matchTime);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (!teamName.equals("")) {
			if (tempList.size() > 0) {// 同时检索两个条件的情况
				for (int i = 0; i < tempList.size(); i++) {
					MatchBasicPO temp = tempList.get(i);
					if (temp.getAwayTeam().equals(teamName)
							|| temp.getHomeTeam().equals(teamName)) {
						result.add(new MatchBasicVO(temp));
					}
				}
				return result;
			} else {
				try {
					tempList = matchDataService.getBasicByTeam(teamName);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		for (int i = tempList.size()-1; i >-1 ; i--) {
			result.add(new MatchBasicVO(tempList.get(i)));
		}
		return result;
	}

	public ArrayList<PlayerInMatchVO> getMatches4Player(String playerID) {
		ArrayList<PlayerInMatchPO> tempList = null;
		try {
			tempList = matchDataService.getPlayer5Matches(playerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}// 得到所有
		ArrayList<PlayerInMatchVO> result = new ArrayList<PlayerInMatchVO>();
		PlayerMatchSortTool tool = new PlayerMatchSortTool();
		Collections.sort(tempList, tool);// 排序
		int num = 5;
		if (tempList.size() < num) {
			num = tempList.size();
		}
		for (int i = 0; i < num; i++) {// 控制数量
			result.add(new PlayerInMatchVO(tempList.get(i)));
		}
		return result;
	}

	public ArrayList<TeamInMatchVO> getMatches4Team(String team) {

		ArrayList<TeamInMatchPO> tempList = null;
		try {
			tempList = matchDataService.getTeam5Matcher(team);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamInMatchVO> result = new ArrayList<TeamInMatchVO>();
		TeamMatchSortTool tool = new TeamMatchSortTool();
		Collections.sort(tempList, tool);
		int num = 5;
		if (tempList.size() < num) {
			num = tempList.size();
		}
		for (int i = 0; i < num; i++) {
			result.add(new TeamInMatchVO(tempList.get(i)));
		}
		return result;

	}

	public ArrayList<PlayerInMatchVO> getPlayerInMatchVOs4Match(
			String matchTime, String teamAway, String teamHome,String theTeam) {
		ArrayList<PlayerInMatchPO> tempList = null;
		try {
			tempList = matchDataService.getPlayerInMatchVOs4Match(matchTime,
					teamAway, teamHome);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerInMatchVO> result = new ArrayList<PlayerInMatchVO>();

		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getTeamOfPlayer().equals(theTeam)) {
					result.add(new PlayerInMatchVO(tempList.get(i)));				
				}
			}
		}

		return result;
	}

	public ArrayList<TeamInMatchVO> getTeamInMatchVOs4Match(String matchTime,
			String teamAway, String teamHome,String theTeam) {
		ArrayList<TeamInMatchPO> tempList = null;
		try {
			tempList = matchDataService.getTeamInMatchVOs4Match(matchTime,
					teamAway, teamHome);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamInMatchVO> result = new ArrayList<TeamInMatchVO>();

		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getTeamName().equals(theTeam)) {
				result.add(new TeamInMatchVO(tempList.get(i)));
				}
			}
		}

		return result;
	}

	public ArrayList<PlayerInMatchVO> getPlayerByDate(String time) {
		ArrayList<PlayerInMatchPO> tempList = null;
		try {
			tempList = matchDataService.getPlayerByDate(time);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerInMatchVO> result = new ArrayList<PlayerInMatchVO>();
		for (int i = 0; i < tempList.size(); i++) {
			result.add(new PlayerInMatchVO(tempList.get(i)));
		}
		return result;
	}

	public ArrayList<PlayerInMatchVO> getPlayerBySeason(String playerID,
			String matchSeason) {
		boolean isStart=false;
		ArrayList<PlayerInMatchPO> tempList = null;
		ArrayList<PlayerInMatchVO> result = new ArrayList<PlayerInMatchVO>();
		try {
			tempList = matchDataService.getAllPlayer(playerID);// 得到该球员所有的比赛
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PlayerMatchSortTool tool = new PlayerMatchSortTool();
		Collections.sort(tempList, tool);// 排序
		for (int i = 0; i < tempList.size(); i++) {
			PlayerInMatchPO temp=tempList.get(i);
			if (temp.getTeamOfPlayer().equals("WST")||temp.getTeamOfPlayer().equals("EST")) {//如果是全明星
				continue;
			}
			boolean isCurrent = SeasonCheckTool.checkSeason(temp
					.getMatchDate(), matchSeason);
			if (isCurrent) {//处于所要赛季
				isStart=true;
				result.add(new PlayerInMatchVO(tempList.get(i)));
			} else if (isStart) {
				break;
			}
		}
		return result;
	}

	public ArrayList<TeamInMatchVO> getTeamBySeason(String teamName,
			String matchSeason) {
		boolean isStart=false;
		ArrayList<TeamInMatchPO> tempList = null;
		ArrayList<TeamInMatchVO> result = new ArrayList<TeamInMatchVO>();
		try {
			tempList = matchDataService.getAllTeam(teamName);// 得到该球员所有的比赛
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TeamMatchSortTool tool = new TeamMatchSortTool();
		Collections.sort(tempList, tool);// 排序
		for (int i = 0; i < tempList.size(); i++) {
			TeamInMatchPO temp=tempList.get(i);
			
			boolean isCurrent = SeasonCheckTool.checkSeason(temp
					.getMatchDate(), matchSeason);
			if (isCurrent) {
				isStart=true;
				result.add(new TeamInMatchVO(tempList.get(i)));
			} else if (isStart) {
				break;
			}
		}
		return result;
	}

	public MatchBasicVO getMatchById(String matchID) {
		MatchBasicPO temp;
		try {
			temp = matchDataService.getMatchById(matchID);
			if (temp!=null) {
				return new MatchBasicVO(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
