package analyser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dataServiceImp.DatabaseException;
import vo.MembersVO;
import vo.PlayerBasicVO;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import vo.TeamTotalVO;
import bl.tool.TimeChangeTool;
import blService.MatchBLService;
import blService.PlayerBLService;
import blService.TeamBLService;
import blServiceImp.MatchBLImp;
import blServiceImp.PlayerBLImp;
import blServiceImp.TeamBLImp;

/**
 * @author:小春
 * @date:2015年6月10日下午3:25:16
 * @version
 */

public class PlayerAnalysis {

	BufferedReader reader;
	BufferedWriter writerBefore;
	BufferedWriter writerAfter;
	BufferedWriter writerName;
	static TeamBLService teamBLService;
	static PlayerBLService playerBLService;
	static MatchBLService matchBLService;

	public PlayerAnalysis() {
		teamBLService = new TeamBLImp();
		matchBLService = new MatchBLImp();
		try {
			playerBLService = new PlayerBLImp();
		} catch (DatabaseException e) {

			e.printStackTrace();
		}
	}

	public void getPlayerEff() {
		ArrayList<String> playerID = new ArrayList<String>();
		ArrayList<String> season = new ArrayList<String>();
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<String> teamName = new ArrayList<String>();
		ArrayList<String> teamID = new ArrayList<String>();
		String temp;
		String[] container;
		try {
			reader = new BufferedReader(new FileReader(
					"D:\\source\\analyer\\playerDate.txt"));
			while ((temp = reader.readLine()) != null) {
				container = temp.split(";");
				playerID.add(container[0]);
				season.add(container[1]);
				date.add(container[2]);
				teamName.add(container[3]);
				teamID.add(container[4]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 33; i < playerID.size(); i++) {
			// writerName=new BufferedWriter(new
			// FileWriter("D:\\source\\analyer\\playerDate.txt"));
			System.out.println(i);
			MembersVO tempMember = teamBLService.getMemberOfTeam(teamID.get(i),
					season.get(i));
			savePlayersInfo(tempMember, playerID.get(i), season.get(i),
					date.get(i));
		}
	}

	private void savePlayersInfo(MembersVO members, String playerID,
			String season, String date) {
		PlayerBasicVO temp = playerBLService.getPlayerDetail(playerID);
		String playerName = temp.getPlayerName();
		Date current = TimeChangeTool.strToDate(date);
		try {
			writerName = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\" + playerName + "_" + season
							+ "_playersName.txt", true));
			writerBefore = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\" + playerName + "_" + season
							+ "_perBefor.txt", true));
			writerAfter = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\" + playerName + "_" + season
							+ "_perAfter.txt", true));
		} catch (IOException e) {

			e.printStackTrace();
		}
		ArrayList<String> playersID = members.getPlayerIDs();
		for (int i = 0; i < playersID.size(); i++) {
			int numBefor = 0;
			int numAfter = 0;
			int matchBefor = 0;
			int matchAfter = 0;
			double resultBefor = 0;
			double resultAfter = 0;
			String theName;
			ArrayList<PlayerInMatchVO> tempList = matchBLService
					.getPlayerBySeason(playersID.get(i), season);
			// (得分+篮板+助攻+抢断+封盖)-(投篮次数-投篮命中次数)-(罚球次数-罚球命中次数)-失误] /比赛场次
			if (tempList.size()<=0) {
				continue;
			}
			theName = tempList.get(0).getPlayerName();
			for (int j = 0; j < tempList.size(); j++) {
				PlayerInMatchVO tempVO = tempList.get(j);
				Date tempDate = TimeChangeTool.strToDate(tempVO.getMatchDate());
				if (!tempDate.after(current)) {// 报销前
					numBefor = tempVO.getPTS() + tempVO.getREB()
							+ tempVO.getAST() + tempVO.getBLK()
							+ tempVO.getFGM() - tempVO.getFGA()
							+ tempVO.getFTM() - tempVO.getFTA()
							- tempVO.getTOV();
					matchBefor += 1;
				} else {
					numAfter = tempVO.getPTS() + tempVO.getREB()
							+ tempVO.getAST() + tempVO.getBLK()
							+ tempVO.getFGM() - tempVO.getFGA()
							+ tempVO.getFTM() - tempVO.getFTA()
							- tempVO.getTOV();
					matchAfter += 1;
				}

			}
			resultBefor = (double) numBefor / (double) matchBefor;
			resultAfter = (double) numAfter / (double) matchAfter;
			if (matchBefor==0) {
				resultBefor=0;
			}
			if (matchAfter==0) {
				resultAfter=0;
			}
			try {
				writerName.write(theName + "\r\n");
				writerName.flush();
				writerBefore.write(String.valueOf(resultBefor) + "\r\n");
				writerBefore.flush();
				writerAfter.write(String.valueOf(resultAfter) + "\r\n");
				writerAfter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writerName.close();
			writerBefore.close();
			writerAfter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(playerID);
	}

	public void getTeamAndPlayer() {
		String temp;
		String[] container;
		ArrayList<String> playersID=new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(
					"D:\\source\\analyer\\playerDate.txt"));
			while ((temp = reader.readLine()) != null) {
				container = temp.split(";");
				if (!playersID.contains(container[0])) {
					playersID.add(container[0]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(playersID.size());
		for (int i = 0; i < playersID.size(); i++) {
			
			saveTeamInfo(playersID.get(i));
			System.out.println(i);
		}
	}

	private void saveTeamInfo(String playerID) {
		PlayerBasicVO temp = playerBLService.getPlayerDetail(playerID);
		String playerName = temp.getPlayerName();
		try {
			writerName = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\All\\" + playerName
							+ "_perSeason.txt", true));
			writerBefore = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\All\\" + playerName + "_perStar.txt",
					true));
			writerAfter = new BufferedWriter(
					new FileWriter("D:\\source\\analyer\\All\\" + playerName
							+ "_perTeams.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<PlayerTotalVO> tempList=playerBLService.getRegularByName(playerID);//得到所有比赛总数据
//		System.out.println("size"+tempList.size());
		for (int i = 0; i < tempList.size(); i++) {
			PlayerTotalVO tempVO=tempList.get(i);
			if (tempVO.getGP()<35) {
				continue;
			}
			double result=tempVO.getEffPer();
//			System.out.println(result);
			TeamTotalVO tempTeam=teamBLService.getTeamDetail_whole(tempVO.getTeamName(), 0, tempVO.getMatchSeason());
			if (tempTeam==null) {
				System.out.println("null");
				continue;
			}
			double winRate=(double)tempTeam.getWIN()/(double)tempTeam.getGP();
			try {
				writerName.write(tempVO.getMatchSeason() + "\r\n");
				writerName.flush();
				writerBefore.write(String.valueOf(result) + "\r\n");
				writerBefore.flush();
				writerAfter.write(String.valueOf(winRate) + "\r\n");
				writerAfter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
