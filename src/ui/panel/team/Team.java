package ui.panel.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.panel.hot.LeftHotPanel;
import ui.panel.match.Match;
import ui.panel.player.Player;
import ui.panel.statics.Statics;
import ui.tool.button.MyButton;
import ui.tool.button.MyOldButton;
import ui.tool.frame.MyFrame;
import ui.tool.jcombox.ColorArrowUI;
import ui.tool.label.MyJLabel;
import ui.tool.picture.StaticImage;
import ui.tool.table.StatJTable;
import ui.tool.table.TeamHighJTable;
import ui.tool.table.TeamJTable;
import vo.TeamHighVO;
import vo.TeamTotalVO;
import blService.TeamBLService;
import blServiceImp.TeamBLImp;
import config.ConfigFactory;
import config.GameConfig;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4Teams;
import enumerate.TypeOfSort4TeamsHigh;
import enumerate.TypeOfStats;
import enumerate.TypeOfSumOrAvg;
import enumerate.TypeOfUpOrDown;

public class Team  extends JPanel{
	    // 获取配置
		GameConfig cfg = ConfigFactory.getGameConfig();
		/**
		 * 做选择赛季的下拉框
		 */
		SelectJComboBox<String> seasonsBox;
		JScrollPane jspane;
		StatJTable statTable;
		/**
		 * 热点部分
		 */
		private LeftHotPanel jLabelHot;
		/**
		 * 一些用户界面转换的panel，frame
		 */
		private JPanel nextJpanel;
		private JPanel currentJpanel;
		private Frame currentFrame;
		private JPanel lastJPanel;

		// 切换到球队界面按钮
		private JButton changeToPlayerPanel = new JButton();
		// 切换到比赛界面按钮
		private JButton changeToMatchPanel = new JButton();
		private JButton changeToStatPanel = new JButton();
		/**
		 * 看是哪个赛季
		 */
		private static String season="2014-15";
		
		/**
		 * 看是总数据还是平均数据的标志位,默认场均
		 */
		private static TypeOfSumOrAvg sumORavg = TypeOfSumOrAvg.AVERAGE;
		/**
		 * 看是升序还是降序,默认降序
		 */
		private static TypeOfUpOrDown upOrDown = TypeOfUpOrDown.DESCENDING_ORDER;
		/**
		 * 看是常规赛还是季后赛还是全明星,默认常规赛
		 */
		private static TypeOfMatch typeOfShow = TypeOfMatch.REGULAR;
		/**
		 * 看是基础还是高阶数据,默认基础数据
		 */
		private static TypeOfStats typeOfstat = TypeOfStats.TRADITIONAL;
		TeamBLService teamBLService=new TeamBLImp();
		
		
		public final static String[] ABB={"BOS","BKN","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHA","MIA","ORL","WAS",
            "DEN","MIN","OKC","POR","UTA","GSW","LAC","LAL","PHX","SAC","DAL","HOU","MEM","NOP","SAS"};
private static MyOldButton bos=new MyOldButton(StaticImage.backOfBOS2,StaticImage.backOfBOS2, 235, 94, 
StaticImage.backOfBOS2.getIconWidth(), StaticImage.backOfBOS2.getIconHeight());
private static MyOldButton bkn=new MyOldButton(StaticImage.backOfBKN2,StaticImage.backOfBKN2, 289, 97, 
StaticImage.backOfBKN2.getIconWidth(), StaticImage.backOfBKN2.getIconHeight());
private   static    MyOldButton nyk=new MyOldButton(StaticImage.backOfNYK2,StaticImage.backOfNYK2, 340, 97, 
StaticImage.backOfNYK2.getIconWidth(), StaticImage.backOfNYK2.getIconHeight());
private static  MyOldButton phi=new MyOldButton(StaticImage.backOfPHI2,StaticImage.backOfPHI2, 396, 95, 
StaticImage.backOfPHI2.getIconWidth(), StaticImage.backOfPHI2.getIconHeight());
private static  MyOldButton tor=new MyOldButton(StaticImage.backOfTOR2,StaticImage.backOfTOR2, 455, 98, 
StaticImage.backOfTOR2.getIconWidth(), StaticImage.backOfTOR2.getIconHeight());
private static MyOldButton chi=new MyOldButton(StaticImage.backOfCHI2,StaticImage.backOfCHI2, 570, 97, 45, 42);
private static  MyOldButton cle=new MyOldButton(StaticImage.backOfCLE2,StaticImage.backOfCLE2, 629, 94, 
StaticImage.backOfCLE2.getIconWidth(), StaticImage.backOfCLE2.getIconHeight());
private static  MyOldButton det=new MyOldButton(StaticImage.backOfDET2,StaticImage.backOfDET2, 715, 94, 
StaticImage.backOfDET2.getIconWidth(), StaticImage.backOfDET2.getIconHeight());
private static MyOldButton ind=new MyOldButton(StaticImage.backOfIND2,StaticImage.backOfIND2, 772, 94, 
StaticImage.backOfIND2.getIconWidth(), StaticImage.backOfIND2.getIconHeight());
private static  MyOldButton mil=new MyOldButton(StaticImage.backOfMIL2,StaticImage.backOfMIL2, 830,95, 
StaticImage.backOfMIL2.getIconWidth(), StaticImage.backOfMIL2.getIconHeight());
private static MyOldButton atl=new MyOldButton(StaticImage.backOfATL2,StaticImage.backOfATL2, 950,97, 
StaticImage.backOfATL2.getIconWidth(), StaticImage.backOfATL2.getIconHeight());
private static MyOldButton cha=new MyOldButton(StaticImage.backOfCHA2,StaticImage.backOfCHA2, 1032,97, 
StaticImage.backOfCHA2.getIconWidth(), StaticImage.backOfCHA2.getIconHeight());
private static MyOldButton mia=new MyOldButton(StaticImage.backOfMIA2,StaticImage.backOfMIA2, 1095,95, 
StaticImage.backOfMIA2.getIconWidth(), StaticImage.backOfMIA2.getIconHeight());
private static   MyOldButton orl=new MyOldButton(StaticImage.backOfORL2,StaticImage.backOfORL2, 1148,96, 
StaticImage.backOfORL2.getIconWidth(), StaticImage.backOfORL2.getIconHeight());
private static  MyOldButton was=new MyOldButton(StaticImage.backOfWAS2,StaticImage.backOfWAS2, 1207,99, 
StaticImage.backOfWAS2.getIconWidth(), StaticImage.backOfWAS2.getIconHeight());
private static  MyOldButton den=new MyOldButton(StaticImage.backOfDEN2,StaticImage.backOfDEN2, 228,143, 
StaticImage.backOfDEN2.getIconWidth(), StaticImage.backOfDEN2.getIconHeight());
private static MyOldButton min=new MyOldButton(StaticImage.backOfMIN2,StaticImage.backOfMIN2, 293,143, 
StaticImage.backOfMIN2.getIconWidth(), StaticImage.backOfMIN2.getIconHeight());
private static MyOldButton okc=new MyOldButton(StaticImage.backOfOKC2,StaticImage.backOfOKC2, 356,140, 
StaticImage.backOfOKC2.getIconWidth(), StaticImage.backOfOKC2.getIconHeight());
private static MyOldButton por=new MyOldButton(StaticImage.backOfPOR2,StaticImage.backOfPOR2, 414,143, 
StaticImage.backOfPOR2.getIconWidth(), StaticImage.backOfPOR2.getIconHeight());
private static MyOldButton uta=new MyOldButton(StaticImage.backOfUTA2,StaticImage.backOfUTA2, 455,145, 
StaticImage.backOfUTA2.getIconWidth(), StaticImage.backOfUTA2.getIconHeight());
private static  MyOldButton gsw=new MyOldButton(StaticImage.backOfGSW2,StaticImage.backOfGSW2,575,145, 
StaticImage.backOfGSW2.getIconWidth(), StaticImage.backOfGSW2.getIconHeight());
private static MyOldButton lac=new MyOldButton(StaticImage.backOfLAC2,StaticImage.backOfLAC2,633,143, 
StaticImage.backOfLAC2.getIconWidth(), StaticImage.backOfLAC2.getIconHeight());
private static   MyOldButton lal=new MyOldButton(StaticImage.backOfLAL2,StaticImage.backOfLAL2,699,145, 
StaticImage.backOfLAL2.getIconWidth(), StaticImage.backOfLAL2.getIconHeight());
private static   MyOldButton phx=new MyOldButton(StaticImage.backOfPHX2,StaticImage.backOfPHX2,762,147, 
StaticImage.backOfPHX2.getIconWidth(), StaticImage.backOfPHX2.getIconHeight());
private static MyOldButton sac=new MyOldButton(StaticImage.backOfSAC2,StaticImage.backOfSAC2,820,144, 
StaticImage.backOfSAC2.getIconWidth(), StaticImage.backOfSAC2.getIconHeight()); 
private static MyOldButton dal=new MyOldButton(StaticImage.backOfDAL2,StaticImage.backOfDAL2,960,141, 
StaticImage.backOfDAL2.getIconWidth(), StaticImage.backOfDAL2.getIconHeight());
private static MyOldButton hou=new MyOldButton(StaticImage.backOfHOU2,StaticImage.backOfHOU2,1018,145, 
StaticImage.backOfHOU2.getIconWidth(), StaticImage.backOfHOU2.getIconHeight());
private static MyOldButton mem=new MyOldButton(StaticImage.backOfMEM2,StaticImage.backOfMEM2,1069,142, 
StaticImage.backOfMEM2.getIconWidth(), StaticImage.backOfMEM2.getIconHeight());
private static    MyOldButton nop=new MyOldButton(StaticImage.backOfNOP2,StaticImage.backOfNOP2,1125,141, 
StaticImage.backOfNOP2.getIconWidth(), StaticImage.backOfNOP2.getIconHeight());
private static  MyOldButton sas=new MyOldButton(StaticImage.backOfSAS2,StaticImage.backOfSAS2,1193,143, 
StaticImage.backOfSAS2.getIconWidth(), StaticImage.backOfSAS2.getIconHeight());


private static JButton[] teamButtons;

private void getButtonInit() {
	/*
	 * public final static String[] ABB={"BOS","BKN","NYK","PHI","TOR","CHI","CLE","DET","IND","MIL","ATL","CHA","MIA","ORL","WAS",
	                               "DEN","MIN","OKC","POR","UTA","GSW","LAC","LAL","PHX","SAC","DAL","HOU","MEM","NOP","SAS"};
	 */
	teamButtons = new JButton[] {bos.jbutton,bkn.jbutton,nyk.jbutton,phi.jbutton,tor.jbutton,chi.jbutton,cle.jbutton,det.jbutton,ind.jbutton,mil.jbutton,atl.jbutton,cha.jbutton,mia.jbutton,orl.jbutton,was.jbutton,
			                     den.jbutton,min.jbutton,okc.jbutton,por.jbutton,uta.jbutton,gsw.jbutton,lac.jbutton,lal.jbutton,phx.jbutton,sac.jbutton,dal.jbutton,hou.jbutton,mem.jbutton,nop.jbutton,sas.jbutton};
	for (int i = 0; i <teamButtons.length; i++) {
		teamButtons[i].setVisible(true);
		teamButtons[i].addActionListener(new ButtonListener2(i, this));
		this.add(teamButtons[i]);
	}
}	// 鼠标监听
class ButtonListener2 implements ActionListener {
	private int buttonID;
	Team currentPanel;

	public ButtonListener2(int id, Team panel) {
		buttonID = id;
		currentPanel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if(buttonID==0||buttonID==1||buttonID==2||buttonID==3||buttonID==4||buttonID==5||buttonID==6||buttonID==7||buttonID==8||buttonID==9||buttonID==10
		||buttonID==11||buttonID==12||buttonID==13||buttonID==14||buttonID==15||buttonID==16||buttonID==17||buttonID==18||buttonID==19||buttonID==20
		||buttonID==21||buttonID==22||buttonID==23||buttonID==24||buttonID==25||buttonID==26||buttonID==27||buttonID==28||buttonID==29){
				nextJpanel=new TeamBasic(currentJpanel,ABB[buttonID],currentFrame);
		}
		MyFrame.getFrame().changePanel(nextJpanel);
	}
}
		private static String[] labelName = { "常规赛", "季后赛",  "总数", "平均", "基础", "高阶" };
	// 常规赛
	private static MyJLabel regular = new MyJLabel(207, 192, 84, 25,
			StaticImage.backOfREGULARbtn, StaticImage.backOfREGULARbtnPressed);
	// 季后赛
	private static MyJLabel playoff = new MyJLabel(310, 192, 84, 25,
			StaticImage.backOfPLAYOFFbtn, StaticImage.backOfPLAYOFFbtnPressed);

	// 总数
	private static MyJLabel zong = new MyJLabel(1111, 192, 56, 26,
			StaticImage.backOfSUMbtn, StaticImage.backOfSUMbtnPressed);
	// 平均
	private static MyJLabel average = new MyJLabel(1174, 192, 84, 25,
			StaticImage.backOfAVERAGEbtn, StaticImage.backOfAVERAGEbtnPressed);
	// 基础
	private static MyJLabel traditional = new MyJLabel(843, 192, 127, 26,
			StaticImage.backOfTRADITIONALbtn,
			StaticImage.backOfTRADITIONALbtnPressed);
	// 高阶
	private static MyJLabel high = new MyJLabel(970, 192, 56, 26,
			StaticImage.backOfHIGHbtn, StaticImage.backOfHIGHbtnPressed);
	/**
	 * 用于把这些按钮都加到界面上
	 */
	private static JLabel[] jLabelAsButtons;
	
	// 单件模式
		public static ArrayList<TeamTotalVO> arrTotalVOs = new ArrayList<TeamTotalVO>();
		public static ArrayList<TeamHighVO> arrhiHighVOs = new ArrayList<TeamHighVO>();
		
		/**
		 * 把那些按钮都加进去
		 */
		private void getLabelInit() {
			/*
			 * private static String
			 * []labelName={"球员姓名","球衣号码","所在位置","所在球队","前锋","中锋"
			 * ,"后卫","东部","西部","总数","平均"};
			 */
			jLabelAsButtons = new JLabel[] { regular.label, playoff.label,
					 zong.label, average.label,
					traditional.label, high.label };
			for (int i = 0; i < jLabelAsButtons.length; i++) {
				jLabelAsButtons[i].setVisible(true);
				jLabelAsButtons[i].addMouseListener(new ButtonListener(i, this));
				this.add(jLabelAsButtons[i]);
			}
		} // 鼠标监听

		class ButtonListener extends MouseAdapter {
			private int buttonID;
			Team currentPanel;

			public ButtonListener(int id, Team panel) {
				buttonID = id;
				currentPanel = panel;
			}

			public void mouseClicked(MouseEvent e) {
				season=seasonsBox.getSelectedItem().toString();
				if(labelName[buttonID].equals("常规赛")||labelName[buttonID].equals("季后赛")){
					season=seasonsBox.getSelectedItem().toString();
				if (labelName[buttonID].equals("常规赛")) {
					playoff.turnBack();
					
                  typeOfShow=TypeOfMatch.REGULAR;
				} else if (labelName[buttonID].equals("季后赛")) {
					regular.turnBack();

                   typeOfShow=TypeOfMatch.PLAYOFF;
				} 
				if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
					if(sumORavg.equals(TypeOfSumOrAvg.TOTAL)){
						arrTotalVOs=teamBLService.changeseasonsTypesTotal(arrTotalVOs, typeOfShow, season);
				        paintTotalTable();
					}else{
						arrTotalVOs=teamBLService.changeseasonsTypesTotal(arrTotalVOs, typeOfShow, season);
				        paintTotalAverageTable();
					}
				}else{
					arrhiHighVOs=teamBLService.changeseasonsTypesHigh(arrhiHighVOs, typeOfShow, season);
					 paintHighTable();
				}
				}else if (labelName[buttonID].equals("总数")) {
					average.turnBack();
					sumORavg=TypeOfSumOrAvg.TOTAL;
					if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
						paintTotalTable();
					}else{
						paintHighTable();
					}
				} else if (labelName[buttonID].equals("平均")) {
					zong.turnBack();
                    sumORavg=TypeOfSumOrAvg.AVERAGE;
                    if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
                    	paintTotalAverageTable();
                    }else{
                    	paintHighTable();
                    }
				}else if (labelName[buttonID].equals("基础")) {
					high.turnBack();
					typeOfstat=TypeOfStats.TRADITIONAL;
					arrTotalVOs=teamBLService.changeToLow(arrhiHighVOs);
					if(sumORavg.equals(TypeOfSumOrAvg.TOTAL)){
						paintTotalTable();
					}else {
						paintTotalAverageTable();
					}
				} else if (labelName[buttonID].equals("高阶")) {
					traditional.turnBack();
					typeOfstat=TypeOfStats.HIGH;
                    arrhiHighVOs=teamBLService.changeToHigh(arrTotalVOs);
					paintHighTable();
				}
				
			}
		}
		public Team(JPanel lastJPanel, Frame current) {
			setVisible(true);
			setLayout(null);
			this.currentFrame = current;
			this.lastJPanel = lastJPanel;
			this.currentJpanel = this;
			initialize();

		}
		private class SelectJComboBox<T> extends JComboBox<T> {
			SelectJComboBox() {
				this.setBackground(new Color(69, 69, 69));
				this.setForeground(new Color(162,210,227));
				this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                    
			}

		}
		private void initialize() {
			getButtonInit();
			this.setSize(cfg.getWidth(), cfg.getHeight());
			/**
			 * 初始化那些文字按钮们
			 */
			seasonsBox= new SelectJComboBox<String>();
			for(int i=14;i>=10;i--){
				seasonsBox.addItem("20"+i+"-"+(i+1));
			}
			seasonsBox.addItem("2009-10");
			for(int i=8;i>=0;i--){
				seasonsBox.addItem("200"+i+"-0"+(i+1));
			}
			seasonsBox.addItem("1999-00");
				
			for(int i=98;i>=46;i--){
				seasonsBox.addItem("19"+i+"-"+(i+1));
			
			}
			seasonsBox.setBounds(603,192, 141, 22);
			seasonsBox.setBackground(new Color(10, 54, 101));
			seasonsBox.setUI(ColorArrowUI.createUI(seasonsBox));
			this.add(seasonsBox);
			/**
			 * 初始化
			 */
			arrhiHighVOs=teamBLService.getAllHigh(typeOfShow, season);
			arrTotalVOs=teamBLService.getAllTotal(typeOfShow, season);
			
			getLabelInit();
			MyButton button3 = new MyButton(StaticImage.backOfqiudui_d,
					StaticImage.backOfqiudui_l,StaticImage.backOfqiudui_b, 930, 55, 76, 34);
		//	this.add(button3.jbutton);
			// 换界面的按钮
			MyButton button0 = new MyButton(StaticImage.backOfqiuyuani_d,
					StaticImage.backOfqiuyuan_l,StaticImage.backOfqiuyuan_b, 1020, 55, 76, 34);
			this.add(button0.jbutton);
			changeToPlayerPanel = button0.jbutton;
	          changeToPlayerPanel.addActionListener(new button0ActionListener());

			MyButton button1 = new MyButton(StaticImage.backOfmatch_d,
					StaticImage.backOfmatch_l,StaticImage.backOfmatch_b, 1110, 55, 76, 34);
			this.add(button1.jbutton);
			changeToMatchPanel = button1.jbutton;
			changeToMatchPanel.addActionListener(new button1ActionListener());
			MyButton button2 = new MyButton(StaticImage.backOftongji_d,
					StaticImage.backOftongji_l,StaticImage.backOftongji_b, 1200, 55, 76, 34);
			this.add(button2.jbutton);
			changeToStatPanel = button2.jbutton;
			changeToStatPanel.addActionListener(new button2ActionListener());
//		
//			TeamTotalVO teamTotalVO=new TeamTotalVO(TypeOfMatch.ALLSTAR, "22","hov", "3", 2, 4, 5,
//				6, 7, 8, "5", 1, 2, "5", 4, 5,"4", 7, 8, 2, 3,4, 5, 7,6);
//			for(int i=0;i<30;i++){
//			arrTotalVOs.add(teamTotalVO);
//			}
//			arrTotalVOs.add(teamTotalVO);

	   
        paintTotalTable();
        
   jLabelHot=new LeftHotPanel(currentJpanel, currentFrame);
  		
  		this.add(jLabelHot.hotPanel,0);

		}
		private void paintTotalTable(){
			if(jspane!=null){
				this.remove(jspane);}
			jspane = new JScrollPane();
			
			jspane.setBounds(0, 220, 1276, 536);
			jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			statTable = new TeamJTable(
			this,arrTotalVOs);
			
			statTable.setBounds(200, 200, 800, 600);
			statTable.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int c = statTable.columnAtPoint(e.getPoint());
				//	System.out.println(c);
					if(c>=3){
						if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){
							arrTotalVOs=teamBLService.sortBySelectedItem(typeOfShow,season,TypeOfSort4Teams.values()[c-3], TypeOfSort.ASCENDING_ORDER_TOTAL);
								upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
						}else{
							arrTotalVOs=teamBLService.sortBySelectedItem(typeOfShow,season,TypeOfSort4Teams.values()[c-3], TypeOfSort.DESCENDING_ORDER_TOTAL);
							upOrDown=TypeOfUpOrDown.ASCENDING_ORDER;
						}
						paintTotalTable();
					}
				}
			});
			statTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						if (statTable.getSelectedRow() == -1) {
							return;
						} else {

							nextJpanel=new TeamBasic(currentJpanel, statTable.getValueAt(statTable.getSelectedRow(), 2).toString(), currentFrame);
							

					MyFrame.getFrame().changePanel(nextJpanel);
						}

					}

				}
			});
			statTable.refresh();
			jspane.setViewportView(statTable);
			this.add(jspane);
			this.validate();
			this.repaint();
		}
		private void paintHighTable(){
			if(jspane!=null){
				this.remove(jspane);}
			jspane = new JScrollPane();
			
			jspane.setBounds(0, 220, 1276, 536);
			jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			statTable = new TeamHighJTable(
			this,arrhiHighVOs);
			
			statTable.setBounds(200, 200, 800, 600);
			statTable.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int c = statTable.columnAtPoint(e.getPoint());
		
					if(c>=5){
						if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){		
			arrhiHighVOs=teamBLService.sortBySelectedItemHigh(typeOfShow,season,TypeOfSort4TeamsHigh.values()[c-5], TypeOfSort.ASCENDING_ORDER_TOTAL);
						upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
						}else{
							arrhiHighVOs=teamBLService.sortBySelectedItemHigh(typeOfShow,season,TypeOfSort4TeamsHigh.values()[c-5], TypeOfSort.DESCENDING_ORDER_TOTAL);
							upOrDown=TypeOfUpOrDown.ASCENDING_ORDER;
						}
		paintHighTable();
					}
				}
			});
			statTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						if (statTable.getSelectedRow() == -1) {
							return;
						} else {
							nextJpanel=new TeamBasic(currentJpanel, statTable.getValueAt(statTable.getSelectedRow(), 2).toString(), currentFrame);
							

					MyFrame.getFrame().changePanel(nextJpanel);
						}

					}

				}
			});
			statTable.refresh();
			jspane.setViewportView(statTable);
			this.add(jspane);
			this.validate();
			this.repaint();
			
		}
		private void paintTotalAverageTable(){
			if(jspane!=null){
				this.remove(jspane);}
			jspane = new JScrollPane();
			jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			jspane.setBounds(0, 220, 1276, 536);
			ArrayList< TeamTotalVO> averageArrayList=new ArrayList<TeamTotalVO>();
             for(int i=0;i<arrTotalVOs.size();i++){
            	 int numbers =arrTotalVOs.get(i).getGP();
            	 TeamTotalVO tt=arrTotalVOs.get(i);
            	 TeamTotalVO averagevo=new TeamTotalVO(tt.getMatchType(),tt.getMatchSeason(),tt.getTeamID(),tt.getTeamName(),tt.getGP(),tt.getWIN(),tt.getLOSS(),tt.getPTS()/numbers,
            			 tt.getFGA()/numbers,tt.getFGM()/numbers,tt.getFGPer(),tt.getTPA()/numbers,tt.getTPM()/numbers,tt.getTPPer(),tt.getFTA()/numbers,tt.getFTM()/numbers,
            			 tt.getFTPer(),tt.getREB()/numbers,tt.getOREB()/numbers,tt.getDREB()/numbers,tt.getAST()/numbers,tt.getSTL()/numbers,tt.getBLK()/numbers,tt.getTO()/numbers,tt.getPF()/numbers);
            	 averageArrayList.add(averagevo);
             }
			statTable = new TeamJTable(
			this,averageArrayList);
			
			statTable.setBounds(200, 200, 800, 600);
			statTable.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int c = statTable.columnAtPoint(e.getPoint());
				//	System.out.println(c);
					if(c>=3){		
						if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){		
		arrTotalVOs=teamBLService.sortBySelectedItem(typeOfShow,season,TypeOfSort4Teams.values()[c-3], TypeOfSort.ASCENDING_ORDER_AVERAGE);
	upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
						}else{
							arrTotalVOs=teamBLService.sortBySelectedItem(typeOfShow,season,TypeOfSort4Teams.values()[c-3], TypeOfSort.DESCENDING_ORDER_AVERAGE);
							upOrDown=TypeOfUpOrDown.ASCENDING_ORDER;
						}
						
						paintTotalAverageTable();
					}
				}
			});
			statTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						if (statTable.getSelectedRow() == -1) {
							return;
						} else {
							nextJpanel=new TeamBasic(currentJpanel, statTable.getValueAt(statTable.getSelectedRow(), 2).toString(), currentFrame);
							

					MyFrame.getFrame().changePanel(nextJpanel);
						}

					}

				}
			});
			statTable.refresh();
			jspane.setViewportView(statTable);
			this.add(jspane);
			this.validate();
			this.repaint();
		}

		class button0ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				nextJpanel = new Player(Team.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}

		class button1ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				nextJpanel = new Match(Team.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}
		class button2ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
     		nextJpanel = new Statics(Team.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.drawImage(StaticImage.backOfnewTeamBk.getImage(), 0, 0, null);
		}
}
