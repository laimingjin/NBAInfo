package analyser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dataServiceImp.DatabaseException;
import blService.MatchBLService;
import blService.PlayerBLService;
import blServiceImp.MatchBLImp;
import blServiceImp.PlayerBLImp;
import vo.PlayerBasicVO;
import vo.PlayerInMatchVO;

/**
 * @author:小春
 * @date:2015年6月9日下午11:58:55
 * @version
 */

public class PlayerDate {
	static MatchBLService matchBLService;

	public static void main(String[] args) {
		// 201142,2104-15
		// matchBLService=new MatchBLImp();
		// getPlayMatchDate();
		// PlayerAnalysis analysis=new PlayerAnalysis();
		// analysis.getPlayerEff();
		// analysis.getTeamAndPlayer();
//		TeamAnalysis analiysis = new TeamAnalysis();
				//analiysis.getTeamAllWin();
		PlayerDate dater=new PlayerDate();
		dater.getPlayerName();
	}

//	/**
//	 * 
//	 * @author:小春
//	 * @data:2015年6月10日上午9:51:53 void 用于得到报销球星的最后一场比赛！
//	 */
//	private static void getPlayMatchDate() {
//		BufferedWriter writer = null;
//		String player = null;
//		String season = null;
//		ArrayList<PlayerInMatchVO> tempList = null;
//		try {
//			writer = new BufferedWriter(new FileWriter(
//					"D:\\source\\analyer\\playerDate.txt", true));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		ArrayList<String> playerList = getPlayerDate();// 得到球员和赛季
//		System.out.println(playerList.size());
//		for (int i = 14; i < playerList.size(); i++) {
//			System.out.println(i);
//			player = playerList.get(i).split(",")[0];
//			season = playerList.get(i).split(",")[1].trim();
//			tempList = matchBLService.getPlayerBySeason(player, season);
//			if (tempList.size() > 0) {
//				try {
//					writer.write(player + ";" + season + ";"
//							+ tempList.get(0).getMatchDate() + ";"
//							+ tempList.get(0).getTeamOfPlayer() + "\r\n");
//					writer.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//				System.out.println("size=0" + playerList.get(i));
//			}
//			System.out.println(i);
//		}
//		try {
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 * @author:小春
//	 * @data:2015年6月10日上午12:01:12 void
//	 */
//	private static ArrayList<String> getPlayerDate() {
//
//		ArrayList<String> result = new ArrayList<String>();
//		String temp;
//
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(
//					"D:\\source\\playersBaoxiao.txt"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			while ((temp = reader.readLine()) != null) {
//				result.add(temp);
//			}
//			reader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public void getPlayerName(){
		PlayerBLService playerBLService=null;
		try {
			playerBLService = new PlayerBLImp();
		} catch (DatabaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String temp;
		try {
			writer = new BufferedWriter(new FileWriter(
					"D:\\source\\analyer\\playerNameDate.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader = new BufferedReader(new FileReader(
					"D:\\source\\analyer\\playerDate.txt"));//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((temp = reader.readLine()) != null) {
				String playerID=temp.split(";")[0];
				System.out.println(playerID);
				PlayerBasicVO tempVO=playerBLService.getPlayerDetail(playerID);
				if (tempVO!=null) {
					System.out.println(tempVO.getPlayerName());
				writer.write(tempVO.getPlayerName()+";"+temp+"\r\n");
				writer.flush();
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
