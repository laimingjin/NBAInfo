package ui.panel.hot;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import enumerate.TypeOfSort4HotPointPlayer;
import ui.panel.player.PlayerBasic;
import ui.tool.frame.MyFrame;
import ui.tool.table.PlayerBasicJTable;
import vo.PlayerBasicVO;
import blService.HotBLService;
import blServiceImp.HotBLImp;

public class SeasonPanel extends Hot {
	
	JScrollPane jspane;
	PlayerBasicJTable statTable;

// 单件模式
public static ArrayList<PlayerBasicVO> arrTotalVOs = new ArrayList<PlayerBasicVO>();
HotBLService hotBLService=MyFrame.getHotBl();
private void getLabelInit() {
	/*
	 * private static String
	 * []labelName={"球员姓名","球衣号码","所在位置","所在球队","前锋","中锋"
	 * ,"后卫","东部","西部","总数","平均"};
	 */
	jLabelAsButtons = new JLabel[] { scoring.label, rebound.label,
			assist.label, block.label, steal.label, percent_three.label,percent_shoting.label,
			 percent_freethrow.label };
	for (int i = 0; i < jLabelAsButtons.length; i++) {
		jLabelAsButtons[i].setVisible(true);
		jLabelAsButtons[i].addMouseListener(new ButtonListener(i, this));
		this.add(jLabelAsButtons[i]);
	}
} // 鼠标监听

class ButtonListener extends MouseAdapter {
	private int buttonID;
	SeasonPanel currentPanel;

	public ButtonListener(int id, SeasonPanel panel) {
		buttonID = id;
		currentPanel = panel;
		
	}
/**
 * "得分"
	,"篮板"
	,"助攻"
	,"盖帽"
     ,"抢断 "
	,"三分命中率"
,"投篮命中率"
		,"罚球命中率"};
 */
	public void mouseClicked(MouseEvent e) {
		if(jspane!=null){
			SeasonPanel.this.remove(jspane);}
		if(labelName[buttonID].equals("得分")){
			rebound.turnBack();
			assist.turnBack();
			block.turnBack();
		   steal.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		   percent_three.turnBack();
      arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.SCORING);
      paintTotalTable();
			
		}else if(labelName[buttonID].equals("篮板")){
			scoring.turnBack();
			assist.turnBack();
			block.turnBack();
		   steal.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		   percent_three.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.REBOUND);
			   paintTotalTable();	
		}else if(labelName[buttonID].equals("助攻")){
			scoring.turnBack();
			rebound.turnBack();
			block.turnBack();
		   steal.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		   percent_three.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.ASSIST);
			   paintTotalTable();
		}else if(labelName[buttonID].equals("盖帽")){
			scoring.turnBack();
			rebound.turnBack();
			assist.turnBack();
		   steal.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		   percent_three.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.BLOCK);
			   paintTotalTable();
		}else if(labelName[buttonID].equals("抢断")){
			scoring.turnBack();
			rebound.turnBack();
			assist.turnBack();
		 block.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		   percent_three.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.STLSv_STEALS);
			   paintTotalTable();
		}else if(labelName[buttonID].equals("三分命中率")){
			scoring.turnBack();
			rebound.turnBack();
			assist.turnBack();
		 block.turnBack();
		 steal.turnBack();
		   percent_freethrow.turnBack();
		   percent_shoting.turnBack();
		
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.TPPer);
			   paintTotalTable();
		}else if(labelName[buttonID].equals("投篮命中率")){
			scoring.turnBack();
			rebound.turnBack();
			assist.turnBack();
		 block.turnBack();
		 steal.turnBack();
		   percent_freethrow.turnBack();
		percent_three.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.FGPer);
			   paintTotalTable();
		}else if(labelName[buttonID].equals("罚球命中率")){
			scoring.turnBack();
			rebound.turnBack();
			assist.turnBack();
		 block.turnBack();
		 steal.turnBack();
		   percent_three.turnBack();
		   percent_shoting.turnBack();
			   arrTotalVOs=  hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.FTPer);
			   paintTotalTable();
		}
	}
}

	public SeasonPanel(JPanel lastJPanel, Frame current, ArrayList<PlayerBasicVO> playerBasicVOs) {
		super(lastJPanel, current);
		this.arrTotalVOs=playerBasicVOs;
		getLabelInit();
		initialize();
		// TODO Auto-generated constructor stub
	}
	private void initialize() {
	 /**
	  * 调用一个让他有一个默认的
	  */
		
		paintTotalTable();
	}
	private void paintTotalTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspane!=null){
		this.remove(jspane);}
		jspane = new JScrollPane();
		jspane.setBounds(40, 176, 1199, 474);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
statTable=new PlayerBasicJTable(this, arrTotalVOs);
		statTable.setBounds(200, 200, 800, 600);

		statTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (statTable.getSelectedRow() == -1) {
						return;
					} else {

						nextJpanel = new PlayerBasic(currentJpanel,
								statTable.getValueAt(
										statTable.getSelectedRow(), 0)
										.toString(), currentFrame);

						MyFrame.getFrame().changePanel(nextJpanel);
					}

				}

			}
		});
	    statTable.refresh();
		jspane.setViewportView(statTable);
		this.add(jspane,1);
		this.validate();
		this.repaint();
	}
}
