package blServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import po.MembersPO;
import po.PlayerTotalPO;
import po.TeamBasicPO;
import po.TeamHighPO;
import po.TeamTotalPO;
import vo.MembersVO;
import vo.PlayerTotalVO;
import vo.TeamBasicVO;
import vo.TeamHighVO;
import vo.TeamInMatchVO;
import vo.TeamTotalVO;
import bl.tool.PlayerTotalDateSortTool;
import bl.tool.TeamHighDateSortTool;
import bl.tool.TeamHighSortTool;
import bl.tool.TeamTotalDateSortTool;
import bl.tool.TeamTotalSortTool;
import blService.MatchBLService;
import blService.TeamBLService;
import dataService.TeamDataService;
import dataServiceImp.DatabaseException;
import dataServiceImp.TeamDataServiceImp;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4Teams;
import enumerate.TypeOfSort4TeamsHigh;

/**
 * @author:小春
 * @date:2015年5月30日上午11:12:39
 * @version
 */

public class TeamBLImp implements TeamBLService {

	TeamDataService teamDataService;
	MatchBLService matchBLService;

	public TeamBLImp() {
		try {
			teamDataService = new TeamDataServiceImp();
		} catch (DatabaseException e) {

			e.printStackTrace();
		}
		matchBLService = new MatchBLImp();
	}

	public TeamBasicVO getTeamDetail(String teamName) {
		TeamBasicPO temp = null;
		try {
			temp = teamDataService.getTeamDetail(teamName);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		TeamBasicVO.Builder builder = null;
		if (temp != null) {
			builder = new TeamBasicVO.Builder(temp.getTeamID(), teamName,
					temp.getAbbreviation());
			builder.fromPO(temp);
			return builder.build();
		} 
		return null;
	}

	public ArrayList<TeamBasicVO> getTeamByPartition(String partition) {
		ArrayList<TeamBasicPO> tempList = null;
		ArrayList<TeamBasicVO> result = new ArrayList<TeamBasicVO>();
		try {
			tempList = teamDataService.getTeamByPartition(partition);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				TeamBasicPO temp = tempList.get(i);
				TeamBasicVO.Builder builder = new TeamBasicVO.Builder(
						temp.getTeamID(), temp.getTeamName(),
						temp.getAbbreviation());
				builder.fromPO(temp);
				result.add(builder.build());
			}
		}
		return result;
	}

	public TeamBasicVO get_from_abbreviation(String abbreviation) {
		TeamBasicPO temp = null;
		try {
			temp = teamDataService.get_from_abbreviation(abbreviation);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		TeamBasicVO.Builder builder = null;
		if (temp != null) {
			builder = new TeamBasicVO.Builder(temp.getTeamID(),
					temp.getTeamName(), abbreviation);
			builder.fromPO(temp);
			return builder.build();
		}
		return null;
	}

	public TeamTotalVO getTeamDetail_whole(String abbreviation,
			int beforeOrAfter, String yearNumber) {
		TypeOfMatch typeOfMatch = TypeOfMatch.values()[beforeOrAfter];
		TeamTotalPO temp = null;
		try {
			temp = teamDataService.getTeamDetail_whole(abbreviation,
					typeOfMatch, yearNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TeamTotalVO.Builder builder = null;
		if (temp != null) {
			builder = new TeamTotalVO.Builder(typeOfMatch, yearNumber,
					temp.getTeamID(), abbreviation);
			builder.fromPO(temp);
			return builder.build();
		} 
		return null;
	}

	public ArrayList<TeamTotalVO> changeseasonsTypesTotal(
			ArrayList<TeamTotalVO> teamTotalVOs, TypeOfMatch typeOfShow,
			String yearNumber) {

		ArrayList<TeamTotalVO> result = new ArrayList<TeamTotalVO>();
		TeamTotalPO tempPO = null;
		TeamTotalVO tempVO = null;
		for (int i = 0; i < teamTotalVOs.size(); i++) {
			tempVO = teamTotalVOs.get(i);
			try {
				tempPO = teamDataService.getTeamDetail_whole(
						tempVO.getTeamName(), typeOfShow, yearNumber);
			} catch (SQLException e) {

				e.printStackTrace();
			}
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(typeOfShow,
//					yearNumber, tempVO.getTeamID(), tempVO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			if (tempPO!=null) {
				result.add(new TeamTotalVO(tempPO));
			}
		}
		return result;

	}

	public ArrayList<TeamHighVO> changeseasonsTypesHigh(
			ArrayList<TeamHighVO> teamTotalVOs, TypeOfMatch typeOfShow,
			String yearNumber) {

		ArrayList<TeamHighVO> result = new ArrayList<TeamHighVO>();
		TeamHighPO tempPO = null;
		TeamHighVO tempVO = null;
		for (int i = 0; i < teamTotalVOs.size(); i++) {
			tempVO = teamTotalVOs.get(i);
			try {
				tempPO = teamDataService.changeToHigh(typeOfShow, yearNumber,
						tempVO.getTeamName());
			} catch (SQLException e) {

				e.printStackTrace();
			}
//			TeamHighVO.Builder builder = new TeamHighVO.Builder(typeOfShow,
//					yearNumber, tempVO.getTeamID(), tempVO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
				if (tempPO!=null) {
					result.add(new TeamHighVO(tempPO));
				}
		}
		return result;
	}

	public ArrayList<TeamTotalVO> sortBySelectedItem(TypeOfMatch typeOfShow,
			String matchSeason, TypeOfSort4Teams item, TypeOfSort typeOfSort) {
		ArrayList<TeamTotalPO> tempList = null;
		try {
			tempList = teamDataService.getAllTotal(typeOfShow, matchSeason);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamTotalVO> totalList = new ArrayList<TeamTotalVO>();
		for (int i = 0; i < tempList.size(); i++) {
			TeamTotalPO temp = tempList.get(i);
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(
//					temp.getMatchType(), temp.getMatchSeason(),
//					temp.getTeamID(), temp.getTeamName());
//			builder.fromPO(temp);
//			totalList.add(builder.build());
			totalList.add(new TeamTotalVO(temp));
		}
		typeOfSort = checkOfSortTotal(item, typeOfSort);
		TeamTotalSortTool tool = new TeamTotalSortTool(typeOfSort, item);
		Collections.sort(totalList, tool);
		return totalList;
	}

	public ArrayList<TeamHighVO> sortBySelectedItemHigh(TypeOfMatch typeOfShow,
			String matchSeason, TypeOfSort4TeamsHigh item, TypeOfSort typeOfSort) {
		ArrayList<TeamHighPO> tempList = null;
		try {
			tempList = teamDataService.getAllHigh(typeOfShow, matchSeason);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamHighVO> totalList = new ArrayList<TeamHighVO>();
		for (int i = 0; i < tempList.size(); i++) {
			TeamHighPO temp = tempList.get(i);
//			TeamHighVO.Builder builder = new TeamHighVO.Builder(
//					temp.getMatchType(), temp.getMatchSeason(),
//					temp.getTeamID(), temp.getTeamName());
//			builder.fromPO(temp);
//			totalList.add(builder.build());
			totalList.add(new TeamHighVO(temp));
		}
		TeamHighSortTool tool = new TeamHighSortTool(typeOfSort, item);
		Collections.sort(totalList, tool);
		return totalList;
	}

	public ArrayList<TeamHighVO> changeToHigh(
			ArrayList<TeamTotalVO> teamTotalVOs) {
		ArrayList<TeamHighVO> result = new ArrayList<TeamHighVO>();
		for (int i = 0; i < teamTotalVOs.size(); i++) {
			TeamTotalVO tempVO = teamTotalVOs.get(i);
			TeamHighPO tempPO = null;
			try {
				tempPO = teamDataService.changeToHigh(tempVO.getMatchType(),
						tempVO.getMatchSeason(), tempVO.getTeamName());
			} catch (SQLException e) {

				e.printStackTrace();
			}
//			TeamHighVO.Builder builder = new TeamHighVO.Builder(
//					tempVO.getMatchType(), tempVO.getMatchSeason(),
//					tempVO.getTeamID(), tempVO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			if (tempPO!=null) {
				result.add(new TeamHighVO(tempPO));
			}
		}
		return result;
	}

	public ArrayList<TeamTotalVO> changeToLow(ArrayList<TeamHighVO> teamHighVOs) {
		ArrayList<TeamTotalVO> result = new ArrayList<TeamTotalVO>();
		for (int i = 0; i < teamHighVOs.size(); i++) {
			TeamHighVO tempVO = teamHighVOs.get(i);
			TeamTotalPO tempPO = null;
			try {
				tempPO = teamDataService.getTeamDetail_whole(
						tempVO.getTeamName(), tempVO.getMatchType(),
						tempVO.getMatchSeason());
			} catch (SQLException e) {

				e.printStackTrace();
			}
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(
//					tempVO.getMatchType(), tempVO.getMatchSeason(),
//					tempVO.getTeamID(), tempVO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			if (tempPO!=null) {
				result.add(new TeamTotalVO(tempPO));
			}
		}
		return result;
	}

	public ArrayList<TeamTotalVO> getRegularByTeamName(String name) {

		ArrayList<TeamTotalPO> tempList = null;
		try {
			tempList = teamDataService.getTeamAllTotal(name,
					TypeOfMatch.REGULAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamTotalVO> result = new ArrayList<TeamTotalVO>();
		TeamTotalPO tempPO = null;
		for (int i = 0; i < tempList.size(); i++) {
			tempPO = tempList.get(i);
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(
//					TypeOfMatch.REGULAR, tempPO.getMatchSeason(),
//					tempPO.getTeamID(), tempPO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			result.add(new TeamTotalVO(tempPO));
		}
		TeamTotalDateSortTool tool = new TeamTotalDateSortTool();
		Collections.sort(result, tool);
		return result;

	}

	public ArrayList<TeamTotalVO> getPlayoffByTeamName(String name) {

		ArrayList<TeamTotalPO> tempList = null;
		try {
			tempList = teamDataService.getTeamAllTotal(name,
					TypeOfMatch.PLAYOFF);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamTotalVO> result = new ArrayList<TeamTotalVO>();
		TeamTotalPO tempPO = null;
		for (int i = 0; i < tempList.size(); i++) {
			tempPO = tempList.get(i);
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(
//					TypeOfMatch.PLAYOFF, tempPO.getMatchSeason(),
//					tempPO.getTeamID(), tempPO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			result.add(new TeamTotalVO(tempPO));
		}
		TeamTotalDateSortTool tool = new TeamTotalDateSortTool();
		Collections.sort(result, tool);
		return result;

	}

	public ArrayList<TeamHighVO> getRegularByTeamNameHigh(String name) {

		ArrayList<TeamHighPO> tempList = null;
		try {
			tempList = teamDataService
					.getTeamAllHigh(name, TypeOfMatch.REGULAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamHighVO> result = new ArrayList<TeamHighVO>();
		TeamHighPO tempPO = null;
		for (int i = 0; i < tempList.size(); i++) {
			tempPO = tempList.get(i);
//			TeamHighVO.Builder builder = new TeamHighVO.Builder(
//					TypeOfMatch.REGULAR, tempPO.getMatchSeason(),
//					tempPO.getTeamID(), tempPO.getTeamName());
//			builder.fromPO(tempPO);
//			result.add(builder.build());
			result.add(new TeamHighVO(tempPO));
		}
		TeamHighDateSortTool tool = new TeamHighDateSortTool();
		Collections.sort(result, tool);
		return result;

	}

	public ArrayList<TeamHighVO> getPlayoffByTeamNameHigh(String name) {

		ArrayList<TeamHighPO> tempList = null;
		try {
			tempList = teamDataService
					.getTeamAllHigh(name, TypeOfMatch.PLAYOFF);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<TeamHighVO> result = new ArrayList<TeamHighVO>();
		TeamHighPO tempPO = null;
		for (int i = 0; i < tempList.size(); i++) {
			tempPO = tempList.get(i);
//			TeamHighVO.Builder builder = new TeamHighVO.Builder(
//					TypeOfMatch.PLAYOFF, tempPO.getMatchSeason(),
//					tempPO.getTeamID(), tempPO.getTeamName());
//			builder.fromPO(tempPO);
			result.add(new TeamHighVO(tempPO));
		}
		TeamHighDateSortTool tool = new TeamHighDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<TeamInMatchVO> getRecentMatchesforTeam(String name) {
		ArrayList<TeamInMatchVO> result = matchBLService.getMatches4Team(name);
		return result;
	}

	private TypeOfSort checkOfSortTotal(TypeOfSort4Teams item,
			TypeOfSort typeOfSort) {
		switch (item) {
		case FGPer:
		case TPPer:
		case FTPer:
			switch (typeOfSort) {
			case ASCENDING_ORDER_AVERAGE:

				return TypeOfSort.ASCENDING_ORDER_TOTAL;
			case DESCENDING_ORDER_AVERAGE:
				return TypeOfSort.DESCENDING_ORDER_TOTAL;
			default:
				return typeOfSort;
			}
		default:
			return typeOfSort;
		}
	}

	public MembersVO getMemberOfTeam(String teamID, String matchSeason) {
		MembersPO temPo;
		try {
			temPo = teamDataService.getMemberOfTeam(teamID, matchSeason);
			if (temPo != null) {
				return new MembersVO(temPo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<TeamHighVO> getAllHigh(TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<TeamHighPO> temp = null;
		try {
			temp = teamDataService.getAllHigh(typeOfShow, yearNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<TeamHighVO> result = new ArrayList<TeamHighVO>();
		for (int i = 0; i < temp.size(); i++) {
			TeamHighPO tempTotal = temp.get(i);
			TeamHighVO.Builder builder = new TeamHighVO.Builder(typeOfShow,
					tempTotal.getMatchSeason(), tempTotal.getTeamID(),
					tempTotal.getTeamName());
			builder.fromPO(tempTotal);
			result.add(builder.build());
		}
		return result;
	}

	public ArrayList<TeamTotalVO> getAllTotal(TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<TeamTotalPO> temp = null;
		try {
			temp = teamDataService.getAllTotal(typeOfShow, yearNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<TeamTotalVO> result = new ArrayList<TeamTotalVO>();
		for (int i = 0; i < temp.size(); i++) {
			TeamTotalPO tempTotal = temp.get(i);
//			TeamTotalVO.Builder builder = new TeamTotalVO.Builder(typeOfShow,
//					tempTotal.getMatchSeason(), tempTotal.getTeamID(),
//					tempTotal.getTeamName());
//			builder.fromPO(tempTotal);
			result.add(new TeamTotalVO(tempTotal));
		}
		return result;
	}

}
