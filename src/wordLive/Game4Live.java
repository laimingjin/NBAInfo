package wordLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game4Live {
	private String webAddress = "http://g.hupu.com/nba/daily/playbyplay_150120.html";

	public Game4Live(String webAddress) {
		super();
		this.webAddress = webAddress;
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

	private String getGameLiveTable(String webInfo) {

		String result = webInfo.substring(webInfo
				.indexOf(StaticString.headOfTable));

		// System.out.println(result);

		return result;

	}

	private boolean isSidNotExist(String webInfo, int i) {

		return !webInfo.contains(StaticString.sid + i + "\"");

	}

	public ArrayList<GameLiveItem> getAllLiveItems() {
		ArrayList<GameLiveItem> gameLiveItems = new ArrayList<GameLiveItem>();

		String str = getGameLiveTable(readWeb());
		int sid = 2;

		while (!isSidNotExist(str, sid)) {
			gameLiveItems.add(getGameLiveItemUsingSid(str, sid));

			sid++;
		}

		return gameLiveItems;

	}

	public ArrayList<GameLiveItem> getAllLiveItems4End() {

		ArrayList<GameLiveItem> gameLiveItems = new ArrayList<GameLiveItem>();

		String info = readWeb();

		info = getGameLiveTable(info);

		// System.out.println(info);
		// System.out.println("_________________________");

		int i = 2;

		while (!isSidNotExist(info, i)) {

			// System.out.println("_________________________");

			String itemInfo = info.substring(
					info.indexOf(StaticString.sid + i),
					info.indexOf(StaticString.endOfLiveItem));
			info = info.substring(info.indexOf(StaticString.endOfLiveItem)
					+ StaticString.endOfLiveItem.length());

			// System.out.println("*******************************\n"+itemInfo+"\n************************************");

			GameLiveItem gli = getGameLiveItem(itemInfo, i);

			gameLiveItems.add(gli);
			// System.out.println(gli);

			i++;
		}

		return gameLiveItems;

	}

	private GameLiveItem getGameLiveItem4ONE(String itemInfo, int i) {
		String[] gameItems = new String[3];
		gameItems[0]="";
		gameItems[1]="";
		gameItems[2] = itemInfo.substring(
				itemInfo.indexOf(StaticString.startOfBold)
						+ StaticString.startOfBold.length(),
				itemInfo.indexOf(StaticString.endOfBold));

		return new GameLiveItem(i, StateOfGameLiveItem.ONE, gameItems);
	}


	private GameLiveItem getGameLiveItem4FOUR(String itemInfo, int i) {
		String[] gameItems = new String[4];

		String temp;

		gameItems[0] = itemInfo.substring(
				itemInfo.indexOf(StaticString.startOfItem)
						+ StaticString.startOfItem.length(),
				itemInfo.indexOf(StaticString.endOfItem));

		temp = itemInfo.substring(itemInfo.indexOf(StaticString.endOfItem)
				+ StaticString.endOfItem.length());

		gameItems[1] = temp.substring(temp.indexOf(StaticString.startOfItem)
				+ StaticString.startOfItem.length(),
				temp.indexOf(StaticString.endOfItem));

		temp = temp.substring(temp.indexOf(StaticString.endOfItem)
				+ StaticString.endOfItem.length());

		gameItems[2] = temp.substring(temp.indexOf(StaticString.startOfItem2)
				+ StaticString.startOfItem2.length(),
				temp.indexOf(StaticString.endOfItem2));

		gameItems[2] = gameItems[2].replace(" ", "");
		gameItems[2] = gameItems[2].replace("<b>", "");
		gameItems[2] = gameItems[2].replace("</b>", "");

		temp = temp.substring(temp.indexOf(StaticString.endOfItem)
				+ StaticString.endOfItem.length());

		gameItems[3] = temp.substring(temp.indexOf(StaticString.startOfItem3)
				+ StaticString.startOfItem3.length(),
				temp.indexOf(StaticString.endOfItem3));

		return new GameLiveItem(i, StateOfGameLiveItem.FOUR, gameItems);

	}

	private GameLiveItem getGameLiveItem(String itemInfo, int i) {
		if (itemInfo.contains("pause")) {// �������pause����ô��˵���Ƕ�����һ��

			return getGameLiveItem4ONE(itemInfo, i);

		}
		// ���û�о�˵������������ʱ��
		// ȡ�������������͵ķ���
		return getGameLiveItem4FOUR(itemInfo, i);
	}

	// public GameLiveItem

	public GameLiveItem getGameLiveItemUsingSid(String info, int sid) {

		if (info == null) {
			info = getGameLiveTable(readWeb());
		}

		if (isSidNotExist(info, sid)) {// ������������
			return null;
		}

		info = info.substring(info.indexOf(StaticString.sid + sid + "\""));

		String itemInfo = info.substring(
				info.indexOf(StaticString.sid + sid + "\""),
				info.indexOf(StaticString.endOfLiveItem));

		return getGameLiveItem(itemInfo, sid);
	}

	public static void main(String[] args) {
		Game4Live g4l = new Game4Live(
				"http://g.hupu.com/nba/daily/playbyplay_150121.html");

		ArrayList<GameLiveItem> gameLiveItems = new ArrayList<GameLiveItem>();
		// gameLiveItems.add(g4l.getGameLiveItemUsingSid(2));
		while (gameLiveItems.size() == 0) {
			GameLiveItem gli = null;
			if ((gli = g4l.getGameLiveItemUsingSid(
					g4l.getGameLiveTable(g4l.readWeb()), 2)) != null) {
				gameLiveItems.add(gli);

				System.out.println(gli);

			}
		}

		// while

		// while
		//
		// for (int i = 0; i < gameLiveItems.size(); i++) {
		// System.out.println(gameLiveItems.get(i));
		// }
		//
		// System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		//

		// if (gameLiveItems.get(gameLiveItems.size()-1).endGame()) {
		// return;
		// }

		gameLiveItems = g4l.getAllLiveItems();
		for (int i = 0; i < gameLiveItems.size(); i++) {
			System.out.println("Exist^^^" + gameLiveItems.get(i));
		}
		//
		int sid = gameLiveItems.size() + 2;
		// String str=g4l.getGameLiveTable(g4l.readWeb());

		// while (!g4l.isSidNotExist( str, sid)) {
		// gameLiveItems.add(g4l.getGameLiveItemUsingSid( str,sid));
		//
		//
		// // System.out.println(g4l.getGameLiveItemUsingSid( sid));
		// sid++;
		// }
		// //

		//
		while (!gameLiveItems.get(gameLiveItems.size() - 1).endGame()) {

			GameLiveItem gli = g4l.getGameLiveItemUsingSid(
					g4l.getGameLiveTable(g4l.readWeb()), sid);

			// System.out.println(gli);
			if (gli != null) {
				gameLiveItems.add(gli);

				System.out.println(gli);

				sid++;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		// GameLiveItem gli =g4l.getGameLiveItemUsingSid(sid);
		//
		// System.out.println(gli);

	}
}
