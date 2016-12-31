package wordLive;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyLiveTable extends JTable {

	private DefaultTableModel tableModel; // 表格模型对象

	GameLive gameLive;//某比赛的直播
	
	Game4Live game4Live ;
	
	String currentScore_main ;// 主队当前比分


	String currentScore_customer ;// 可对当前比分

	public MyLiveTable(GameLive gameLive) {
		super();

		
		init(gameLive);
	}

	/**
	 * 初始化
	 * @param gameLive2 
	 */
	private void init(GameLive gameLive) {

		this.gameLive=gameLive;
		
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

		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //单选
		// table.addMouseListener(new MouseAdapter(){ //鼠标事件
		// public void mouseClicked(MouseEvent e){
		// int selectedRow = table.getSelectedRow(); //获得选中行索引
		//
		// }
		// });//没有获得索引的必要

		// String
		// [][]tableVales={{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}};
		// this.s
	}

	/**
	 * 
	 * 更新表格 ，更新下一行数据
	 * @return
	 */
	public boolean update() {
		
		
		
		
		int sid = getRowCount() + 2;
		
		
		if (sid>=15) {
			sid= getRowCount() + 4;
		}
		
		GameLiveItem gli = game4Live.getGameLiveItemUsingSid(null, sid);
		
		
		if (gli==null) {
			return false;
		}
		
		
		
		if (gli.stateOfGameLiveItem==StateOfGameLiveItem.FOUR) {
			currentScore_main=gli.gameItems[3].split("-")[0];
			currentScore_customer=gli.gameItems[3].split("-")[1];
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
	

 
//	public String getMainTeamScore(){
//		int i=1;
//		String scores;
//		while ( ( scores=(String)getValueAt(getRowCount()-i, 3))!="") {
//			
//			scores=scores.split("-")[0];
//		}
//		return scores;
//		
//	}
//	public String getCustomerTeamScore(){
//		int i=1;
//		String scores;
//		while ( ( scores=(String)getValueAt(getRowCount()-i, 3))!="") {
//			
//			scores=scores.split("-")[1];
//		}
//		return scores;
//		
//	}
	
	public GameInfo getGameInfo(){
		return gameLive.getGameInfo();
	}

	public String getCurrentScore_main() {
		return currentScore_main;
	}

	public String getCurrentScore_customer() {
		return currentScore_customer;
	}

}