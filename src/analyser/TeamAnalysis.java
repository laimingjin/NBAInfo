package analyser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vo.PlayerBasicVO;
import vo.TeamInMatchVO;
import blService.MatchBLService;
import blService.PlayerBLService;
import blService.TeamBLService;
import blServiceImp.MatchBLImp;
import blServiceImp.PlayerBLImp;
import blServiceImp.TeamBLImp;
import dataServiceImp.DatabaseException;

/**
 * @author:小春
 * @date:2015年6月11日下午11:41:38
 * @version
 */

public class TeamAnalysis {
	BufferedReader reader;
	BufferedWriter playersTime;
	BufferedWriter playersTeamWin;
	BufferedWriter playersTeamLose;
	BufferedWriter playersTeamPer;
	TeamBLService teamBLService;
	PlayerBLService playerBLService;
	MatchBLService matchBLService;

	public TeamAnalysis() {

		teamBLService = new TeamBLImp();
		matchBLService = new MatchBLImp();
		try {
			playerBLService = new PlayerBLImp();
		} catch (DatabaseException e) {

			e.printStackTrace();
		}

	}

	public void getTeamAllWin() {
		String temp;
		String[] container;
		try {
			reader = new BufferedReader(new FileReader(
					"D:\\source\\analyer\\playerDate.txt"));
			while ((temp = reader.readLine()) != null) {
				container = temp.split(";");
				System.out.println("ndd"+container[0]);
				saveTeamInfo(container[0], container[1], container[3]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveTeamInfo(String playerID, String season, String teamName) {
		PlayerBasicVO temp = playerBLService.getPlayerDetail(playerID);
		System.out.println(temp.getPlayerName());
//		DecimalFormat dcmFmt = new DecimalFormat("0.00");
//		System.out.println(dcmFmt.format(db));
		try {
			playersTime = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\Team\\" + temp.getPlayerName() + "_"
							+ season + "_playersTime.txt",true));
			playersTeamWin= new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\Team\\" + temp.getPlayerName() + "_"
							+ season + "_playersTeamWin.txt",true));
			playersTeamLose= new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\Team\\" + temp.getPlayerName() + "_"
							+ season + "_playersTeamLose.txt",true));
			playersTeamPer= new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\Team\\" + temp.getPlayerName() + "_"
							+ season + "_playersTeamPer.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<TeamInMatchVO> tempList=matchBLService.getTeamBySeason(teamName, season);
		int num_win=0;
		int num_lose=0;
		double winRate=0;
		for (int i = tempList.size()-1; i >=0; i--) {
			TeamInMatchVO tempMatch=tempList.get(i);
			if (tempMatch.getIsWin()>0) {//赢
				num_win+=1;	
			}else {
				num_lose+=1;
			}
			winRate=(double)num_win/(double)(num_lose+num_win);
			try {
				playersTime.write(tempMatch.getMatchDate()+"\r\n");
				playersTime.flush();
				playersTeamWin.write(num_win+"\r\n");
				playersTeamWin.flush();
				playersTeamLose.write(num_lose+"\r\n");
				playersTeamLose.flush();
				playersTeamPer.write(winRate+"\r\n");
				playersTeamPer.flush();
				System.out.println("sle"+playerID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			playersTime.close();
			playersTeamWin.close();
			playersTeamLose.close();
			playersTeamPer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
