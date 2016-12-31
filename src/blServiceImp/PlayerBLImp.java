package blServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import po.PlayerBasicPO;
import po.PlayerHighPO;
import po.PlayerTotalPO;
import vo.ContrastBoardVO;
import vo.DataKingVO;
import vo.PlayerBasicVO;
import vo.PlayerHighVO;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import vo.TeamBasicVO;
import bl.tool.NumString;
import bl.tool.PlayerHighDateSortTool;
import bl.tool.PlayerHighSortTool;
import bl.tool.PlayerTotalDateSortTool;
import bl.tool.PlayerTotalSortTool;
import blService.MatchBLService;
import blService.PlayerBLService;
import blService.TeamBLService;
import dataService.PlayerDataService;
import dataServiceImp.DatabaseException;
import dataServiceImp.PlayerDataServiceImp;
import enumerate.AreaOfPlayer;
import enumerate.PositionOfPlayer;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4PlayerHigh;
import enumerate.TypeOfSort4Players;

/**
 * @author:小春
 * @date:2015年5月29日下午8:54:22
 * @version
 */

public class PlayerBLImp implements PlayerBLService {

	PlayerDataService playerDataService;
	TeamBLService teamBLService;
	MatchBLService matchBLService;

	/**
	 * PlayerTotalVO是球员多个赛季的总数据 搜索框随意输入不再规定用户必须选择哪个模块，而由逻辑层自动分析先判断是否是数字，
	 * 是的话就代表是按照球衣号码，不是的话就是 姓名 位置 所在球队，返回的时候，就按照
	 * 姓名所在球队位置这样的顺序拼接成一整个PlayerTotalVO
	 * 
	 * @param word_Search搜索框的输入
	 * @param beforeOrAfter常规赛还是季后赛
	 *            ，常规赛是0，季后赛是1,全明星是2
	 * @param yearNumber年份选择
	 * @return
	 * @throws DatabaseException
	 */

	public PlayerBLImp() throws DatabaseException {
		playerDataService = new PlayerDataServiceImp();
		teamBLService = new TeamBLImp();
		matchBLService = new MatchBLImp();
		System.out.println("reader!");
	}

	public ArrayList<PlayerTotalVO> searchPlayers(String word_Search,
			TypeOfMatch typeOfShow, String yearNumber) {
		ArrayList<PlayerTotalPO> temp = null;
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		boolean isNum = NumString.isNumeric(word_Search);
		System.out.println(yearNumber);
		if (isNum) {// 搜索的是号码的情况
			try {
				temp = playerDataService.searchPlayersByNum(word_Search,
						typeOfShow, yearNumber);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (temp != null) {
				for (int i = 0; i < temp.size(); i++) {
					PlayerTotalPO tempPO = temp.get(i);
					// PlayerTotalVO.Builder builder = new
					// PlayerTotalVO.Builder(
					// tempPO.getMatchType(), tempPO.getMatchSeason(),
					// tempPO.getPlayerID(), tempPO.getPlayerName(),
					// tempPO.getTeamID(), tempPO.getTeamName());
					// builder.fromPO(tempPO);
					result.add(new PlayerTotalVO(tempPO));
				}
			}
		} else {
			// 搜索名字
			try {
				temp = playerDataService.searchPlayersByName(word_Search,
						typeOfShow, yearNumber);
				System.out.println(temp.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (temp != null) {
				for (int i = 0; i < temp.size(); i++) {
					// PlayerTotalPO tempPO = temp.get(i);
					// PlayerTotalVO.Builder builder = new
					// PlayerTotalVO.Builder(
					// tempPO.getMatchType(), tempPO.getMatchSeason(),
					// tempPO.getPlayerID(), tempPO.getPlayerName(),
					// tempPO.getTeamID(), tempPO.getTeamName());
					// builder.fromPO(tempPO);
					PlayerTotalVO tempVO = new PlayerTotalVO(temp.get(i));
					if (!result.contains(tempVO)) {
						result.add(tempVO);

					}
				}
			}

//			// 搜索位置
//			try {
//				temp = playerDataService.searchPlayersByPosition(word_Search,
//						typeOfShow, yearNumber);
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//			if (temp != null) {
//				for (int i = 0; i < temp.size(); i++) {
//					// PlayerTotalPO tempPO = temp.get(i);
//					// PlayerTotalVO.Builder builder = new
//					// PlayerTotalVO.Builder(
//					// tempPO.getMatchType(), tempPO.getPlayerID(),
//					// tempPO.getMatchSeason(), tempPO.getPlayerName(),
//					// tempPO.getTeamID(), tempPO.getTeamName());
//					// builder.fromPO(tempPO);
//					// result.add(new PlayerTotalVO(tempPO));
//					PlayerTotalVO tempVO = new PlayerTotalVO(temp.get(i));
//					if (!result.contains(tempVO)) {
//						result.add(tempVO);
//
//					}
//				}
//			}
//			// 搜索球队
//			try {
//				temp = playerDataService.searchPlayersByTeam(word_Search,
//						typeOfShow, yearNumber);
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//			if (temp != null) {
//				for (int i = 0; i < temp.size(); i++) {
//					// PlayerTotalPO tempPO = temp.get(i);
//					// PlayerTotalVO.Builder builder = new
//					// PlayerTotalVO.Builder(
//					// tempPO.getMatchType(), tempPO.getPlayerID(),
//					// tempPO.getMatchSeason(), tempPO.getPlayerName(),
//					// tempPO.getTeamID(), tempPO.getTeamName());
//					// builder.fromPO(tempPO);
//					// result.add(new PlayerTotalVO(tempPO));
//					PlayerTotalVO tempVO = new PlayerTotalVO(temp.get(i));
//					if (!result.contains(tempVO)) {
//						result.add(tempVO);
//
//					}
//				}
//			}
		}
		return result;
	}

	public ArrayList<PlayerHighVO> changeToHigh(
			ArrayList<PlayerTotalVO> playerTotalVOs) {
		PlayerHighPO tempHigh = null;
		PlayerTotalVO tempTotal;
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		for (int i = 0; i < playerTotalVOs.size(); i++) {
			tempTotal = playerTotalVOs.get(i);
			try {
				tempHigh = playerDataService.getPlayerHigh(
						tempTotal.getPlayerID(), tempTotal.getMatchType(),
						tempTotal.getMatchSeason());
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// PlayerHighVO.Builder builder = new PlayerHighVO.Builder(
			// tempTotal.getMatchType(),
			// tempTotal.getMatchSeason(),tempTotal.getPlayerID(),
			// tempTotal.getPlayerName(),
			// tempTotal.getTeamID(), tempTotal.getTeamName());
			// builder.fromPO(tempHigh);
			// result.add(builder.build());
			if (tempHigh != null) {
				result.add(new PlayerHighVO(tempHigh));
			}
		}
		return result;
	}

	public ArrayList<PlayerTotalVO> changeToLow(
			ArrayList<PlayerHighVO> playerHighVOs) {
		PlayerHighVO tempHigh;
		PlayerTotalPO tempTotal = null;
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		for (int i = 0; i < playerHighVOs.size(); i++) {
			tempHigh = playerHighVOs.get(i);
			try {
				tempTotal = playerDataService.getPlayerTotal(
						tempHigh.getPlayerID(), tempHigh.getMatchType(),
						tempHigh.getMatchSeason());
			} catch (SQLException e) {

				e.printStackTrace();
			}

			// PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
			// tempHigh.getMatchType(), tempHigh.getMatchSeason(),
			// tempHigh.getPlayerID(), tempHigh.getPlayerName(),
			// tempHigh.getTeamID(), tempHigh.getTeamName());
			// builder.fromPO(tempTotal);
			if (tempTotal != null) {
				result.add(new PlayerTotalVO(tempTotal));
			}
		}
		return result;
	}

	public PlayerBasicVO getPlayerDetail(String playerID) {
		PlayerBasicVO result = null;
		PlayerBasicPO temp = null;
		try {
			temp = playerDataService.getPlayerDetail(playerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PlayerBasicVO.Builder builder = null;
		if (temp != null) {
			builder = new PlayerBasicVO.Builder(playerID, temp.getPlayerName());
			builder.basic(temp);
			result = builder.build();
		}
		return result;
	}

	public ArrayList<PlayerTotalVO> sortBySelectedTotal(
			ArrayList<PlayerTotalVO> totalList, TypeOfSort4Players item,
			TypeOfSort typeOfSort) {
		typeOfSort = checkOfSortTotal(item, typeOfSort);
		PlayerTotalSortTool tool = new PlayerTotalSortTool(typeOfSort, item);
		Collections.sort(totalList, tool);
		return totalList;
	}

	public ArrayList<PlayerHighVO> sortBySelectedHigh(
			ArrayList<PlayerHighVO> highList, TypeOfSort4PlayerHigh item,
			TypeOfSort typeOfSort) {
		PlayerHighSortTool tool = new PlayerHighSortTool(typeOfSort, item);
		Collections.sort(highList, tool);
		return highList;
	}

	public ArrayList<PlayerTotalVO> shiftTotal(
			ArrayList<PlayerTotalVO> playerTotalVOs,
			PositionOfPlayer positionOfPlayer, AreaOfPlayer areaOfPlayer) {
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		if (positionOfPlayer != PositionOfPlayer.NULL) {
			String position = checkPosition(positionOfPlayer);
			for (int i = 0; i < playerTotalVOs.size(); i++) {
				PlayerTotalVO tempVO = playerTotalVOs.get(i);
				PlayerBasicPO tempPO = null;
				try {
					tempPO = playerDataService.getPlayerDetail(tempVO
							.getPlayerID());
				} catch (SQLException e) {

					e.printStackTrace();
				}
				if (tempPO != null) {
					if (tempPO.getPosition().contains(position)) {
						result.add(tempVO);
					}
				}
			}
		}
		if (areaOfPlayer != AreaOfPlayer.NULL) {
			if (result.size() > 0) {
				playerTotalVOs = result;
				result = new ArrayList<PlayerTotalVO>();
			}
			String area = checkArea(areaOfPlayer);
			for (int i = 0; i < playerTotalVOs.size(); i++) {
				PlayerTotalVO tempPlayer = playerTotalVOs.get(i);
				TeamBasicVO tempVO = teamBLService
						.get_from_abbreviation(tempPlayer.getTeamName());
				if (tempVO != null) {
					if (area.contains(tempVO.getTeam_partition())) {
						result.add(tempPlayer);
					}
				}
			}
		}
		return result;
	}

	public ArrayList<PlayerHighVO> shiftHigh(
			ArrayList<PlayerHighVO> playerHighVOs,
			PositionOfPlayer positionOfPlayer, AreaOfPlayer areaOfPlayer) {

		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		if (positionOfPlayer != PositionOfPlayer.NULL) {
			String position = checkPosition(positionOfPlayer);
			for (int i = 0; i < playerHighVOs.size(); i++) {
				PlayerHighVO tempVO = playerHighVOs.get(i);
				PlayerBasicPO tempPO = null;
				try {
					tempPO = playerDataService.getPlayerDetail(tempVO
							.getPlayerID());
				} catch (SQLException e) {

					e.printStackTrace();
				}
				if (tempPO != null) {
					if (tempPO.getPosition().contains(position)) {
						result.add(tempVO);
					}
				}
			}
		}
		if (areaOfPlayer != AreaOfPlayer.NULL) {
			if (result.size() > 0) {
				playerHighVOs = result;
				result = new ArrayList<PlayerHighVO>();
			}
			String area = checkArea(areaOfPlayer);
			for (int i = 0; i < playerHighVOs.size(); i++) {
				PlayerHighVO tempPlayer = playerHighVOs.get(i);
				TeamBasicVO tempVO = teamBLService
						.get_from_abbreviation(tempPlayer.getTeamName());
				if (tempVO != null) {
					if (area.contains(tempVO.getTeam_partition())) {
						result.add(tempPlayer);
					}
				}
			}
		}
		return result;

	}

	public ContrastBoardVO getContrastData(String playerID,
			TypeOfMatch typeOfShow, String yearNumber) {
		PlayerBasicPO tempPO = null;
		PlayerTotalPO tempTotal = null;
		try {
			tempPO = playerDataService.getPlayerDetail(playerID);
			tempTotal = playerDataService.getPlayerTotal(playerID, typeOfShow,
					yearNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (tempTotal == null) {
			return new ContrastBoardVO(0, 0, 0, 0, 0, 0, 0, 0, 0);// 无比赛记录的情况
		}
		if (tempPO != null) {
			ArrayList<PlayerTotalVO> tempList = getPlayerByArea(
					tempPO.getTeamName(), typeOfShow, yearNumber);// 得到所有的相关球员
			tempList = sortBySelectedTotal(tempList, TypeOfSort4Players.PTS,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 得分场均降序
			int num = tempList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double first_score = (double) tempList.get(0).getPTS()
					/ (double) num;

			tempList = sortBySelectedTotal(tempList, TypeOfSort4Players.REB,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 得分场均降序
			num = tempList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double first_rebound = (double) tempList.get(0).getREB()
					/ (double) num;

			tempList = sortBySelectedTotal(tempList, TypeOfSort4Players.AST,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 得分场均降序
			num = tempList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double first_assist = (double) tempList.get(0).getAST()
					/ (double) num;
			double assistTotal = 0;
			double reboundTotal = 0;
			double scoreTotal = 0;
			double gpTotal = 0;
			for (int i = 0; i < tempList.size(); i++) {
				PlayerTotalVO temp = tempList.get(i);
				assistTotal += temp.getAST();
				reboundTotal += temp.getREB();
				scoreTotal += temp.getPTS();
				gpTotal += temp.getGP();
			}
			if (gpTotal == 0) {
				gpTotal = 1;
			}
			double alliance_score = scoreTotal / gpTotal;// 联盟平均赛季场均得分
			double alliance_rebound = reboundTotal / gpTotal;// 联盟平均赛季场均篮板
			double alliance_assist = assistTotal / gpTotal;// 联盟平均赛季场均助攻

			int gp = tempTotal.getGP();
			if (gp == 0) {
				gp = 1;
			}
			double player_score = (double) tempTotal.getPTS() / (double) gp;// player的赛季场均得分
			double player_rebound = (double) tempTotal.getREB() / (double) gp;// player的赛季场均篮板
			double player_assist = (double) tempTotal.getAST() / (double) gp;// player的赛季场均助攻

			return new ContrastBoardVO(alliance_score, alliance_rebound,
					alliance_assist, first_score, first_rebound, first_assist,
					player_score, player_rebound, player_assist);
		}

		return new ContrastBoardVO(0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public DataKingVO getDataKing(String teamName, TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<PlayerTotalPO> tempList = null;
		ArrayList<PlayerTotalVO> totalList = new ArrayList<PlayerTotalVO>();
		try {
			tempList = playerDataService.searchPlayersByTeam(teamName,
					typeOfShow, yearNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				PlayerTotalPO temp = tempList.get(i);
				PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
						temp.getMatchType(), temp.getMatchSeason(),
						temp.getPlayerID(), temp.getPlayerName(),
						temp.getTeamID(), temp.getTeamName());
				builder.fromPO(temp);
				totalList.add(builder.build());
			}
			totalList = sortBySelectedTotal(totalList, TypeOfSort4Players.PTS,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 场均得分降序排序
			String scoreName = totalList.get(0).getPlayerName();// 得分王
			double num = (double) totalList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double score = (double) totalList.get(0).getPTS() / num;
			totalList = sortBySelectedTotal(totalList, TypeOfSort4Players.REB,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 场均篮板降序排序
			String rebName = totalList.get(0).getPlayerName();
			num = (double) totalList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double rebNum = (double) totalList.get(0).getREB() / num;
			totalList = sortBySelectedTotal(totalList, TypeOfSort4Players.AST,
					TypeOfSort.DESCENDING_ORDER_AVERAGE);// 助攻篮板降序排序
			String astName = totalList.get(0).getPlayerName();
			num = (double) totalList.get(0).getGP();
			if (num == 0) {
				num = 1;
			}
			double astNum = (double) totalList.get(0).getAST() / num;
			DataKingVO result = new DataKingVO(scoreName, rebName, astName,
					score, rebNum, astNum);
			return result;
		} else {
			return null;
		}

	}

	public ArrayList<PlayerHighVO> getAllHigh(TypeOfMatch typeOfShow,
			String yearNumber) {
		// ArrayList<PlayerHighPO> temp = null;
		// try {
		// temp = playerDataService.getAllHigh(typeOfShow, yearNumber);
		// } catch (SQLException e) {
		//
		// e.printStackTrace();
		// }
		// ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		// for (int i = 0; i < temp.size(); i++) {
		// PlayerHighPO tempHigh = temp.get(i);
		// PlayerHighVO.Builder builder = new PlayerHighVO.Builder(
		// tempHigh.getMatchType(), tempHigh.getMatchSeason(),
		// tempHigh.getPlayerID(), tempHigh.getPlayerName(),
		// tempHigh.getTeamID(), tempHigh.getTeamName());
		// builder.fromPO(tempHigh);
		// result.add(builder.build());
		// }
		String[] playersID = new String[] { "977", "1449", "787", "76375",
				"1495", "76681", "76673", "893", "77142", "2544", "397", "252",
				"165", "406", "56", "78049", "600015", "304", "78497" };
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		for (int i = 0; i < playersID.length; i++) {
			try {
				PlayerHighPO temp = playerDataService.getPlayerHigh(
						playersID[i], typeOfShow, yearNumber);
				if (temp != null) {
					result.add(new PlayerHighVO(temp));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<PlayerTotalVO> getAllTotal(TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<PlayerTotalVO> result=new ArrayList<PlayerTotalVO>();
		 ArrayList<PlayerTotalPO> temp = null;
		 try {
		 temp = playerDataService.getAllTotal(typeOfShow, yearNumber);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }
		 for (int i = 0; i < temp.size(); i++) {
		 PlayerTotalPO tempTotal = temp.get(i);
		if (tempTotal!=null) {	
			result.add(new PlayerTotalVO(tempTotal));
		}
		 }
		return result;
	}
	
	

	public ArrayList<PlayerTotalVO> changeseasonsTypesTotal(
			ArrayList<PlayerTotalVO> playerTotalVOs, TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		PlayerTotalPO tempPO = null;
		PlayerTotalVO tempVO = null;
		for (int i = 0; i < playerTotalVOs.size(); i++) {
			tempVO = playerTotalVOs.get(i);
			try {
				tempPO = playerDataService.getPlayerTotal(tempVO.getPlayerID(),
						typeOfShow, yearNumber);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			// PlayerTotalVO.Builder builder = null;
			// builder = new PlayerTotalVO.Builder(typeOfShow, yearNumber,
			// tempVO.getPlayerID(), tempVO.getPlayerName(),
			// tempVO.getTeamID(), tempVO.getTeamName());
			//
			// builder.fromPO(tempPO);
			if (tempPO != null) {
				result.add(new PlayerTotalVO(tempPO));
			}
		}
		return result;
	}

	public ArrayList<PlayerHighVO> changeseasonsTypesHigh(
			ArrayList<PlayerHighVO> playerHighVOs, TypeOfMatch typeOfShow,
			String yearNumber) {
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		PlayerHighPO tempPO = null;
		PlayerHighVO tempVO = null;
		for (int i = 0; i < playerHighVOs.size(); i++) {
			tempVO = playerHighVOs.get(i);
			try {
				tempPO = playerDataService.getPlayerHigh(tempVO.getPlayerID(),
						typeOfShow, yearNumber);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (tempPO != null) {
				result.add(new PlayerHighVO(tempPO));
			}
		}
		return result;
	}

	public ArrayList<PlayerTotalVO> getRegularByName(String playerID) {
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		ArrayList<PlayerTotalPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllTotal(playerID,
					TypeOfMatch.REGULAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		PlayerTotalPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
				// TypeOfMatch.REGULAR, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(new PlayerTotalVO(tempPO));
				result.add(new PlayerTotalVO(tempPO));
			}
		}
		PlayerTotalDateSortTool tool = new PlayerTotalDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerTotalVO> getPlayoffByName(String playerID) {
		ArrayList<PlayerTotalPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllTotal(playerID,
					TypeOfMatch.PLAYOFF);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		PlayerTotalPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
				// TypeOfMatch.PLAYOFF, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(builder.build());
				result.add(new PlayerTotalVO(tempPO));
			}
		}
		PlayerTotalDateSortTool tool = new PlayerTotalDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerTotalVO> getAllStarByName(String playerID) {
		ArrayList<PlayerTotalPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllTotal(playerID,
					TypeOfMatch.ALLSTAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		PlayerTotalPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
				// TypeOfMatch.ALLSTAR, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(builder.build());
				result.add(new PlayerTotalVO(tempPO));
			}
		}
		PlayerTotalDateSortTool tool = new PlayerTotalDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerHighVO> getRegularByNameHigh(String playerID) {
		ArrayList<PlayerHighPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllHigh(playerID,
					TypeOfMatch.REGULAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		PlayerHighPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerHighVO.Builder builder = new PlayerHighVO.Builder(
				// TypeOfMatch.REGULAR, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(builder.build());
				result.add(new PlayerHighVO(tempPO));
			}
		}

		PlayerHighDateSortTool tool = new PlayerHighDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerHighVO> getPlayoffByNameHigh(String playerID) {
		ArrayList<PlayerHighPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllHigh(playerID,
					TypeOfMatch.PLAYOFF);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		PlayerHighPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerHighVO.Builder builder = new PlayerHighVO.Builder(
				// TypeOfMatch.PLAYOFF, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(builder.build());
				result.add(new PlayerHighVO(tempPO));
			}
		}
		PlayerHighDateSortTool tool = new PlayerHighDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerHighVO> getAllStarByNameHigh(String playerID) {
		ArrayList<PlayerHighPO> tempList = null;
		try {
			tempList = playerDataService.getplayerAllHigh(playerID,
					TypeOfMatch.ALLSTAR);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<PlayerHighVO> result = new ArrayList<PlayerHighVO>();
		PlayerHighPO tempPO = null;
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				tempPO = tempList.get(i);
				// PlayerHighVO.Builder builder = new PlayerHighVO.Builder(
				// TypeOfMatch.ALLSTAR, tempPO.getMatchSeason(),
				// tempPO.getPlayerID(), tempPO.getPlayerName(),
				// tempPO.getTeamID(), tempPO.getTeamName());
				// builder.fromPO(tempPO);
				// result.add(builder.build());
				result.add(new PlayerHighVO(tempPO));
			}
		}
		PlayerHighDateSortTool tool = new PlayerHighDateSortTool();
		Collections.sort(result, tool);
		return result;
	}

	public ArrayList<PlayerInMatchVO> getRecentMatchesforPlayer(String playerID) {
		ArrayList<PlayerInMatchVO> result = matchBLService
				.getMatches4Player(playerID);
		return result;
	}

	private String checkArea(AreaOfPlayer area) {
		String areaOfPlayer = "";
		switch (area) {
		case SOUTHEAST_ATLANTIC_CENTRAL:
			areaOfPlayer = "Southeast_Atlantic_Central";
			break;
		case SOUTHWEST_NORTHWEST_PACIFIC:
			areaOfPlayer = "Southwest_Northwest_Pacific";
			break;
		case SOUTHEAST:
			areaOfPlayer = "Southeast";
			break;
		case ATLANTIC:
			areaOfPlayer = "Atlantic";
			break;
		case CENTRAL:
			areaOfPlayer = "Central";
			break;
		case SOUTHWEST:
			areaOfPlayer = "Southwest";
			break;
		case NORTHWEST:
			areaOfPlayer = "Northwest";
			break;
		case PACIFIC:
			areaOfPlayer = "Pacific";
			break;
		default:
			break;
		}
		return areaOfPlayer;
	}

	private String checkPosition(PositionOfPlayer position) {
		String positionOfPlayer = "";
		switch (position) {
		case F:
			positionOfPlayer = "F";
			break;
		case C:
			positionOfPlayer = "C";
			break;
		case G:
			positionOfPlayer = "G";
			break;
		default:
			break;
		}
		return positionOfPlayer;
	}

	private TypeOfSort checkOfSortTotal(TypeOfSort4Players item,
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

	private ArrayList<PlayerTotalVO> getPlayerByArea(String teamName,
			TypeOfMatch typeOfShow, String yearNumber) {
		ArrayList<PlayerTotalVO> resultList = new ArrayList<PlayerTotalVO>();
		TeamBasicVO tempTeamVO = teamBLService.get_from_abbreviation(teamName);// 得到球队详情
		ArrayList<PlayerTotalPO> tempPOList = null;
		if (tempTeamVO != null) {
			String area = tempTeamVO.getTeam_partition();
			// ArrayList<PlayerTotalPO> tempPlayerList = playerDataService
			// .getplayerTotal(typeOfShow, yearNumber);// 得到所有球员
			// for (int i = 0; i < tempPlayerList.size(); i++) {
			// PlayerTotalPO tempScores = tempPlayerList.get(i);
			// TeamBasicVO tempVO = teamBLService
			// .get_from_abbreviation(tempScores.getTeamName());
			// if (tempVO != null) {
			// if (tempVO.getTeam_partition().equals(area)) {
			// PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
			// TypeOfMatch.ALLSTAR, tempScores.getMatchSeason(),
			// tempScores.getPlayerName(), tempScores.getTeamName());
			// builder.fromPO(tempScores);
			// resultList.add(builder.build());
			// }
			// }
			// }
			ArrayList<TeamBasicVO> tempList = teamBLService
					.getTeamByPartition(area);// 得到同联盟的所有球队
			for (int i = 0; i < tempList.size(); i++) {
				try {
					tempPOList = playerDataService.searchPlayersByTeam(tempList
							.get(i).getAbbreviation(), typeOfShow, yearNumber);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int j = 0; j < tempPOList.size(); j++) {
					PlayerTotalPO tempPO = tempPOList.get(j);
					PlayerTotalVO.Builder builder = new PlayerTotalVO.Builder(
							TypeOfMatch.ALLSTAR, tempPO.getMatchSeason(),
							tempPO.getPlayerID(), tempPO.getPlayerName(),
							tempPO.getTeamID(), tempPO.getTeamName());
					builder.fromPO(tempPO);
					resultList.add(builder.build());
				}
			}
		}

		return resultList;
	}

	@Override
	public ArrayList<PlayerTotalVO> playerInit(TypeOfMatch typeOfMatch,String yearNumber) {
		String[] playersID = new String[] { "977", "1449", "787", "76375",
				"1495", "76681", "76673", "893", "77142", "2544", "397", "252",
				"165", "406", "56", "78049", "600015", "304", "78497" };
		ArrayList<PlayerTotalVO> result = new ArrayList<PlayerTotalVO>();
		for (int i = 0; i < playersID.length; i++) {
			try {
				PlayerTotalPO temp = playerDataService.getPlayerTotal(
						playersID[i], typeOfMatch, yearNumber);
				if (temp != null) {
					result.add(new PlayerTotalVO(temp));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
