package wordLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameInfo {
	
	String webAddress;
	String team_main = "骑士";// 主场队伍名字
	String team_customer = "勇士";// 客场队伍名字

	String record_mainTeam = "主队（53胜29负）";// 主场队伍以前战绩
	String record_customerTeam = "客队（67胜15负）";// 客场队伍以前战绩

	String currentScore_main = "82";// 主队当前比分


	String currentScore_customer = "103";// 可对当前比分

	String startTime = "2015年6月12日 9:00";// 开赛时间
	String totalTime = "2:37";// 总共耗时

	String location = "速贷中心";// 地点
	String numberOfpeople = "20562人";// 上座人数

	
	public GameInfo(String webAddress) {
		this.webAddress=webAddress;
	}

	public void getInfo(){
		
		String webInfo= readWeb();
		
		
		
		
		
	}
	
	
	private String readWeb() {

		String info = null;

		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(webAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {

				sb.append("\n");

				sb.append(line);
			}

			info = sb.toString();

			in.close();
		} catch (Exception e) {
			sb.append(e.toString());
			System.err.println(e);
		}

		// new CharSetChanger().changeCharset(info, "UTF-8");

		// System.out.println(info);

		return info;
	}
//	public GameInfo(String team_main, String team_customer,
//			String record_mainTeam, String record_customerTeam,
//			String currentScore_main, String currentScore_customer,
//			String startTime, String totalTime, String location,
//			String numberOfpeople) {
//	
//		this.team_main = team_main;
//		this.team_customer = team_customer;
//		this.record_mainTeam = record_mainTeam;
//		this.record_customerTeam = record_customerTeam;
//		this.currentScore_main = currentScore_main;
//		this.currentScore_customer = currentScore_customer;
//		this.startTime = startTime;
//		this.totalTime = totalTime;
//		this.location = location;
//		this.numberOfpeople = numberOfpeople;
//	}

	public String getTeam_main() {
		return team_main;
	}

	public String getTeam_customer() {
		return team_customer;
	}

	public String getRecord_mainTeam() {
		return record_mainTeam;
	}

	public String getRecord_customerTeam() {
		return record_customerTeam;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public String getLocation() {
		return location;
	}

	public String getNumberOfpeople() {
		return numberOfpeople;
	}
	
	



	@Override
	public String toString() {
		return "GameInfo [team_main=" + team_main + ", team_customer="
				+ team_customer + ", record_mainTeam=" + record_mainTeam
				+ ", record_customerTeam=" + record_customerTeam
				+ ", currentScore_main=" + currentScore_main
				+ ", currentScore_customer=" + currentScore_customer
				+ ", startTime=" + startTime + ", totalTime=" + totalTime
				+ ", location=" + location + ", numberOfpeople="
				+ numberOfpeople + "]";
	}
	
	
	
	public String getCurrentScore_main() {
		return currentScore_main;
	}

	public String getCurrentScore_customer() {
		return currentScore_customer;
	}

}
