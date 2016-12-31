package ui.panel.live;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.tool.label.MyJLabel;
import ui.tool.label.MyJLabel2;
import ui.tool.picture.StaticImage;
import ui.tool.textfield.BigBlackTextField;
import ui.tool.textfield.MyTextField2;
import ui.tool.textfield.NumberTextField;
import ui.tool.textfield.PlayerNameTextField;
import wordLive.GameInfo;
import wordLive.GameLive;
import wordLive.MyLiveTable;
import config.ConfigFactory;
import config.GameConfig;

public class Live extends  JPanel{
	 // 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();
//	GameInfo gameInfo=new GameInfo();
	/**
	 * 一些用户界面转换的panel，frame
	 */
	private JPanel nextJpanel;
	private JPanel currentJpanel;
	private Frame currentFrame;
	private JPanel lastJPanel;
	/**
	 * 到时候去填的
	 */
	//MyLiveTable myLiveTable=new MyLiveTable(new GameLive(""));
	
//	private static MyJLabel2 boston = new MyJLabel2(24, 56, 150, 27,
//			StaticImage.backOfCLE2, StaticImage.backOfCLE2);
	
	public Live(JPanel lastJPanel, Frame current) {
		setVisible(true);
		setLayout(null);
		this.currentFrame = current;
		this.lastJPanel = lastJPanel;
		this.currentJpanel = this;
		initialize();

	}
	
	
	private void initialize() {
	GameInfo	gameInfo=new GameInfo("骑士");
		
		this.setSize(cfg.getWidth(), cfg.getHeight());
		
		if(gameInfo.getTeam_customer().equals("骑士")){

   
			ImageIcon portait = new ImageIcon("png/CLE.png");
			MyJLabel cle = new MyJLabel(91, 206, portait.getIconWidth(),portait.getIconHeight(), portait, portait);
			
			this.add(cle.label);
		}else {
			ImageIcon portait = new ImageIcon("png/GSW.png");
			MyJLabel cle = new MyJLabel(91, 206, portait.getIconWidth(),portait.getIconHeight(), portait, portait);
			
			this.add(cle.label);
		}
		if(gameInfo.getTeam_main().equals("骑士")){
			ImageIcon portait = new ImageIcon("png/CLE.png");
			MyJLabel cle = new MyJLabel(983, 206, portait.getIconWidth(),portait.getIconHeight(), portait, portait);
			
			this.add(cle.label);
		}else{
			ImageIcon portait = new ImageIcon("png/GSW.png");
			MyJLabel cle = new MyJLabel(983, 206, portait.getIconWidth(),portait.getIconHeight(), portait, portait);
			
			this.add(cle.label);
		}
		BigBlackTextField myTextField=new BigBlackTextField(15, 270, gameInfo.getCurrentScore_customer());
		this.add(myTextField.jtextfield);
		BigBlackTextField myTextField2=new BigBlackTextField(1188, 270, gameInfo.getCurrentScore_main());
		this.add(myTextField2.jtextfield);
		
		 MyLiveTable myLiveTable=new MyLiveTable(new GameLive("http://g.hupu.com/nba/daily/boxscore_150123.html"));
	        JScrollPane scrollPane = new JScrollPane(myLiveTable);   //֧�ֹ���
	       scrollPane.setBounds(335,184,610,490);
  	        myLiveTable.setBounds(335,184,610,490);
  	        this.add(scrollPane);
  	        
	        
  	      scrollPane. setVisible(true);
	        
	        while(!myLiveTable.isEnd()){
	        	myLiveTable.update();
	        	try {
	        		System.out.println(myLiveTable.getValueAt(myLiveTable.getRowCount()-1, 3)); ;
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
//		       &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 
	
	
	}
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(StaticImage.backOfLiveBk.getImage(), 0, 0, null);
	}
}
