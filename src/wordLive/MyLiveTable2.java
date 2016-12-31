package wordLive;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import ui.tool.table.StatJTable;

public class MyLiveTable2 extends StatJTable {
	
	String currentScore_main="0" ;// 主队当前比分


	String currentScore_customer ="0";// 可对当前比分
	private DefaultTableModel tableModel; // 表格模型对象

	GameLive gameLive;//某比赛的直播
	
	Game4Live game4Live ;

    private static int  width;
    public MyLiveTable2(GameLive gameLive){
    	this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.gameLive=gameLive;
		refresh();
    }
	@Override
	public void refresh() {

		width=85;
		
		game4Live=gameLive.getGame4Live();
		
		ArrayList<GameLiveItem> gameLiveItems = game4Live.getAllLiveItems();// 取得已经有的表格

		String[] columnNames = { "时间", "球队", "事件", "比分" };

//		String[][] tableVales={{"啥都没有"}};
		String[][] tableVales = new String[gameLiveItems.size()][];

		for (int i = 0; i < gameLiveItems.size(); i++) {// 复制tableVales[]
			
			
			tableVales[i] = gameLiveItems.get(i).toStrings();
		} 

		tableModel = new DefaultTableModel(tableVales, columnNames);
		
		setModel(tableModel);

		this.resizeColumnWidth(width);
	}
	int sidCounter=2;
public boolean update() {
		
		
		
		
		int sid = getRowCount() + sidCounter;
		
		
//		if (sid>=15) {
//			sid= getRowCount() + 4;
//		}
//		if (sid>=88) {
//			sid= getRowCount() + 6;
//		}
//		if (sid>=91) {
//			sid= getRowCount() + 7;
//		}
//		if (sid>=106) {
//			sid=getRowCount()+11;
//		}
//		if (sid>=117) {
//			sid=getRowCount()+13;
//		}
		
		
		
		GameLiveItem gli = game4Live.getGameLiveItemUsingSid(null, sid);
		
		
		if (gli==null) {
			GameLiveItem gli2;
			for (int i = 1; i <=10; i++) {
				if (( gli2= game4Live.getGameLiveItemUsingSid(null, sid+i))!=null) {
					sidCounter=sidCounter+i;
					tableModel.addRow(gli2.toStrings());
					return true;
				}
			}
			
			
			
			return false;
			
		}
		
		
		
		if (gli.stateOfGameLiveItem==StateOfGameLiveItem.FOUR) {
			currentScore_main=gli.gameItems[3].split("-")[0];
			currentScore_customer=gli.gameItems[3].split("-")[1];
			System.out.println(currentScore_main);
			System.out.println(currentScore_customer);
		}
		
		tableModel.addRow(gli.toStrings());
		return true;
	}

	/**
	 * 检查比赛是否结束，原理就是，检查表格最后一行是否为 比赛结束
	 * @return
	 */
	public boolean isEnd(){
		if (getValueAt(getRowCount()-1, 0).equals("比赛结束")) {
			return true;
		}
		return false;
	}
	

	public String getCurrentScore_main() {
		return currentScore_main;
	}

	public String getCurrentScore_customer() {
		return currentScore_customer;
	}
	
	public GameInfo getGameInfo(){
		return gameLive.getGameInfo();
	}
}
