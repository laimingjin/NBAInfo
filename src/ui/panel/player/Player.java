package ui.panel.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.panel.hot.LeftHotPanel;
import ui.panel.match.Match;
import ui.panel.statics.Statics;
import ui.panel.team.Team;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFailTipsPanel;
import ui.tool.frame.MyFrame;
import ui.tool.frame.MyTipsFrame;
import ui.tool.jcombox.ColorArrowUI;
import ui.tool.label.MyJLabel;
import ui.tool.picture.StaticImage;
import ui.tool.table.PlayerHighJTable;
import ui.tool.table.PlayerJTable;
import ui.tool.table.StatJTable;
import ui.tool.textfield.MyTextField;
import vo.PlayerHighVO;
import vo.PlayerTotalVO;
import blService.PlayerBLService;
import blServiceImp.PlayerBLImp;
import config.ConfigFactory;
import config.GameConfig;
import dataServiceImp.DatabaseException;
import enumerate.AreaOfPlayer;
import enumerate.PositionOfPlayer;
import enumerate.TypeOfMatch;
import enumerate.TypeOfSort;
import enumerate.TypeOfSort4PlayerHigh;
import enumerate.TypeOfSort4Players;
import enumerate.TypeOfStats;
import enumerate.TypeOfSumOrAvg;
import enumerate.TypeOfUpOrDown;

public class Player extends JPanel {

	// 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();

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
	/**
	 * 做选择赛季的下拉框
	 */
	SelectJComboBox<String> seasonsBox;
	/**
	 * 搜索用的小按钮
	 */
	private JButton searchJButton = new JButton();
	
	// 切换到球队界面按钮
	private JButton changeToTeamPanel = new JButton();
	// 切换到比赛界面按钮
	private JButton changeToMatchPanel = new JButton();
	// 切换到比赛界面按钮
		private JButton changeToStatPanel = new JButton();
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
	/**
	 * 看是哪个位置默认是Null
	 */
	private static PositionOfPlayer positionOfPlayer= PositionOfPlayer.NULL;
	/**
	 * 看是哪个赛区默认是Null
	 */
	private static AreaOfPlayer areaOfPlayer= AreaOfPlayer.NULL;
	
	/**
	 * 看是哪个赛季
	 */
	private static String seanson="2014-15";
	/**
	 * 选择显示的列用于显示不同列
	 */
	private JButton showJButton = new JButton();

	/**
	 * 右侧滑动按钮
	 */
	MyButton buttonchange;
	// 搜索输入框
	private MyTextField inputSearchField;
	private static String[] labelName = { "常规赛", "季后赛", "全明星", "前锋",
			"中锋", "后卫", "东部", "西部", "总数", "平均", "基础", "高阶" };
	// 常规赛
	private static MyJLabel regular = new MyJLabel(77, 144, 84, 25,
			StaticImage.backOfREGULARbtn, StaticImage.backOfREGULARbtnPressed);
	// 季后赛
	private static MyJLabel playoff = new MyJLabel(180, 144, 84, 25,
			StaticImage.backOfPLAYOFFbtn, StaticImage.backOfPLAYOFFbtnPressed);

	// 全明星
	private static MyJLabel allStar = new MyJLabel(286, 144, 84, 25,
			StaticImage.backOfALLSTARbtn, StaticImage.backOfALLSTARbtnPressed);
//
//	// 两双
//	private static MyJLabel twoDouble = new MyJLabel(1141, 105, 84, 25,
//			StaticImage.backOfDOUBLEbtn, StaticImage.backOfDOUBLEbtnPressed);

	// 前锋
	private static MyJLabel qian = new MyJLabel(867, 105, 21, 26,
			StaticImage.backOfFbtn, StaticImage.backOfFbtnPressed);
	private static MyJLabel zhong = new MyJLabel(934, 105, 21, 26,
			StaticImage.backOfCbtn, StaticImage.backOfCbtnPressed);
	// 后卫
	private static MyJLabel hou = new MyJLabel(1000, 105, 21, 26,
			StaticImage.backOfGbtn, StaticImage.backOfGbtnPressed);
	// 东部
	private static MyJLabel east = new MyJLabel(609, 105, 56, 26,
			StaticImage.backOfEASTbtn, StaticImage.backOfEASTbtnPressed);
	// 西部
	private static MyJLabel west = new MyJLabel(694, 105, 56, 26,
			StaticImage.backOfWESTbtn, StaticImage.backOfWESTbtnPressed);
	// 总数
	private static MyJLabel zong = new MyJLabel(981, 143, 56, 26,
			StaticImage.backOfSUMbtn, StaticImage.backOfSUMbtnPressed);
	// 平均
	private static MyJLabel average = new MyJLabel(1044, 143, 84, 25,
			StaticImage.backOfAVERAGEbtn, StaticImage.backOfAVERAGEbtnPressed);
	// 基础
	private static MyJLabel traditional = new MyJLabel(713, 145, 127, 26,
			StaticImage.backOfTRADITIONALbtn,
			StaticImage.backOfTRADITIONALbtnPressed);
	// 高阶
	private static MyJLabel high = new MyJLabel(840, 145, 56, 26,
			StaticImage.backOfHIGHbtn, StaticImage.backOfHIGHbtnPressed);
	/**
	 * 用于把这些按钮都加到界面上
	 */
	private static JLabel[] jLabelAsButtons;
	// 单件模式
	public static ArrayList<PlayerTotalVO> arrTotalVOs = new ArrayList<PlayerTotalVO>();
	public static ArrayList<PlayerHighVO> arrhiHighVOs = new ArrayList<PlayerHighVO>();
	
     private  PlayerBLService playerBLService;
     
  // 低阶数据的表头strings
	/*
	 * GP;//出场数 GS;//首发出场数 MIN;//出场时间WIN;//胜利场数PTS;//总得分 FGA;//投篮出手数 FGM;//投篮命中数
	 * FGPer;//投篮命中率 TPA;//三分出手数//原缩写为3PA TPM;//三分命中数//3PM TPPer;//三分命中率//3P%
	 * FTA;//罚球出手 FTM;//罚球命中数 FTPer;//罚球命中率 REB;//篮板数 OREB;//进攻篮板//前场篮板
	 * DREB;//防守篮板//后场篮板 AST;//助攻数 STL;//抢断数 BLK;//盖帽数TOV;//失误数PF;//犯规数
	 */

	public static String[] lowItemStrings = { "球员", "球队", "GP", "GS", "MIN",
			"WIN", "PTS", "FGA", "FGM", "FGPer", "TPA", "TPM", "TPPer", "FTA",
			"FTM", "FTPer", "REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV",
			"PF" };
	/**
	 * REBPer;//篮板率OREBPer;//进攻篮板率 DREBPer;//防守篮板率ASTPer;//助攻率STLPer;//抢断率
	 * BLKPer;//盖帽率 TOVPer;//失误率 UsagePer;//使用率OffEff;//进攻效率DefEff;//防守效率
	 * WS;//胜利贡献值WS OffWS;//进攻贡献值
	 * DefWS;//防守贡献值PER;//球员效率值JAM;//扣篮数AndOne;//2/3+1 BA;//被帽
	 */

	public static String[] highItemStrings = { "球员", "球队", "REBPer", "OREBPer",
			"DREBPer", "ASTPer", "STLPer", "BLKPer", "TOVPer", "UsagePer",
			"OffEff", "DefEff", "WS", "OffWS", "DefWS", "PER", "JAM", "AndOne",
			"BA" };

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
				allStar.label, qian.label, zhong.label,
				hou.label, east.label, west.label, zong.label, average.label,
				traditional.label, high.label };
		for (int i = 0; i < jLabelAsButtons.length; i++) {
			jLabelAsButtons[i].setVisible(true);
			jLabelAsButtons[i].addMouseListener(new ButtonListener(i, this));
			this.add(jLabelAsButtons[i]);
		}
	} // 鼠标监听

	class ButtonListener extends MouseAdapter {
		private int buttonID;
		Player currentPanel;

		public ButtonListener(int id, Player panel) {
			buttonID = id;
			currentPanel = panel;
		}

		public void mouseClicked(MouseEvent e) {
			if(seasonsBox.getSelectedItem()==null){
				
			}else{
			seanson=seasonsBox.getSelectedItem().toString();}
			System.out.println(seanson);
			if(labelName[buttonID].equals("常规赛")||labelName[buttonID].equals("季后赛")||labelName[buttonID].equals("全明星")){
			//	seanson=seasonsBox.getSelectedItem().toString();
				if (labelName[buttonID].equals("常规赛")) {
					 playoff.turnBack();
					 allStar.turnBack();
	                typeOfShow=TypeOfMatch.REGULAR;
				} else if (labelName[buttonID].equals("季后赛")) {
					 regular.turnBack();
					 allStar.turnBack();
					typeOfShow=TypeOfMatch.PLAYOFF;
				} else if (labelName[buttonID].equals("全明星")) {
					 playoff.turnBack();
					regular.turnBack();
					typeOfShow=TypeOfMatch.ALLSTAR;
				} 
				
				 if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
				      
		               if(sumORavg.equals(TypeOfSumOrAvg.TOTAL)){
		            	   /**
		            	    *选择的是低阶数据并且是看总数据三种赛季类型的切换
		            	    */
		         arrTotalVOs=playerBLService.changeseasonsTypesTotal(arrTotalVOs, typeOfShow, seanson);   
		            	   paintTotalTable();
		               }else{
		            arrTotalVOs=playerBLService.changeseasonsTypesTotal(arrTotalVOs, typeOfShow, seanson);   
			            	paintTotalAverageTable();
		               }

				 }else{
					 /**
					  * 高阶的没有全明星
					  */
					 if (typeOfShow.equals(TypeOfMatch.ALLSTAR)) {
						 MyTipsFrame mtf = new MyTipsFrame();
						 MyFailTipsPanel mtPanel = new MyFailTipsPanel(mtf,
						 StaticImage.backOfplayerFail.getImage());
						 mtf.getIni(mtPanel);
					return  ;
						 }
	          /**
	           * 高阶的话总数据和场均数据一样的painthightable()
	           */
              arrhiHighVOs=playerBLService.changeseasonsTypesHigh(arrhiHighVOs, typeOfShow, seanson);
					 paintHighTable();
						
				 }
			}
//			else if (labelName[buttonID].equals("两双")) {
//
//			} 
			else if (labelName[buttonID].equals("前锋")||(labelName[buttonID].equals("中锋"))||labelName[buttonID].equals("后卫")
					||labelName[buttonID].equals("东部")||labelName[buttonID].equals("西部")) {
				
				if (labelName[buttonID].equals("前锋")){
					zhong.turnBack();
					hou.turnBack();
					positionOfPlayer=PositionOfPlayer.F;
				}else if (labelName[buttonID].equals("中锋")) {
					hou.turnBack();
					qian.turnBack();
					positionOfPlayer=PositionOfPlayer.C;

				} else if (labelName[buttonID].equals("后卫")) {
					qian.turnBack();
					zhong.turnBack();
					positionOfPlayer=PositionOfPlayer.G;
				} else if (labelName[buttonID].equals("东部")) {
					west.turnBack();
					   areaOfPlayer=AreaOfPlayer.SOUTHEAST_ATLANTIC_CENTRAL;
				} else if (labelName[buttonID].equals("西部")) {
					east.turnBack();
	            areaOfPlayer=AreaOfPlayer.SOUTHWEST_NORTHWEST_PACIFIC;
				}
				
				if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
					if(sumORavg.equals(TypeOfSumOrAvg.TOTAL)){
					arrTotalVOs=playerBLService.shiftTotal(arrTotalVOs, positionOfPlayer, areaOfPlayer);
					paintTotalTable();}else{
					arrTotalVOs=playerBLService.shiftTotal(arrTotalVOs, positionOfPlayer, areaOfPlayer);
						paintTotalAverageTable();
					}
				}else{
				arrhiHighVOs=playerBLService.shiftHigh(arrhiHighVOs, positionOfPlayer, areaOfPlayer);
				paintHighTable();
				}
				
			} else if (labelName[buttonID].equals("总数")) {
				average.turnBack();
				sumORavg =TypeOfSumOrAvg.TOTAL;
			
				if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
					paintTotalTable();
				}else{
					paintHighTable();
				}
			} else if (labelName[buttonID].equals("平均")) {
				zong.turnBack();
				sumORavg =TypeOfSumOrAvg.AVERAGE;
				/**
				 * 写一个总数转为平均的方法
				 */
				if(typeOfstat.equals(TypeOfStats.TRADITIONAL)){
					paintTotalAverageTable();
				}else{
					//平均的高阶和总数没有变化
					paintHighTable();
				}
				
			}else if (labelName[buttonID].equals("基础")) {
				high.turnBack();
				typeOfstat=TypeOfStats.TRADITIONAL;
				arrTotalVOs=playerBLService.changeToLow(arrhiHighVOs);
				if(sumORavg.equals(TypeOfSumOrAvg.TOTAL)){
					paintTotalTable();
				}else {
					paintTotalAverageTable();
				}
			} else if (labelName[buttonID].equals("高阶")) {
				
				traditional.turnBack();
				typeOfstat=TypeOfStats.HIGH;

				/**
				  * 高阶的没有全明星
				  */
				 if (typeOfShow.equals(TypeOfMatch.ALLSTAR)) {
					 MyTipsFrame mtf = new MyTipsFrame();
					 MyFailTipsPanel mtPanel = new MyFailTipsPanel(mtf,
					 StaticImage.backOfplayerFail.getImage());
					 mtf.getIni(mtPanel);
				return  ;
					 }
			arrhiHighVOs=playerBLService.changeToHigh(arrTotalVOs);
			paintHighTable();
			}
			
			 
		}
	}

	public Player(JPanel lastJPanel, Frame current)  {
		setVisible(true);
		setLayout(null);
		this.currentFrame = current;
		this.lastJPanel = lastJPanel;
		this.currentJpanel = this;
		try {
			playerBLService=new PlayerBLImp();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();

	}

	private void initialize() {
		
		this.setSize(cfg.getWidth(), cfg.getHeight());
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
		seasonsBox.setBounds(473,144, 141, 22);
		seasonsBox.setBackground(new Color(10, 54, 101));
		seasonsBox.setUI(ColorArrowUI.createUI(seasonsBox));
		this.add(seasonsBox);
	
		/**
		 * 刚开始时得到所有的arr
		 */
		this.arrhiHighVOs=playerBLService.getAllHigh(TypeOfMatch.REGULAR,"2014-15");
		this.arrTotalVOs=playerBLService.getAllTotal(TypeOfMatch.REGULAR,"2014-15");
		// 搜索的按钮
				MyButton searchButton = new MyButton(	StaticImage.backOfsearchEntered,StaticImage.backOfsearch,
						StaticImage.backOfsearchEntered, 467, 109, 22, 16);
				this.add(searchButton.jbutton);
				searchJButton = searchButton.jbutton;
				searchJButton.addActionListener(new searchButtonActionListener());
		/**
		 * 初始化那些文字按钮们
		 */
		getLabelInit();
		// 搜索的输入框
		inputSearchField = new MyTextField(226, 109, 237, 18);
		this.add(inputSearchField.jtextfield);

		// 换界面的按钮
		MyButton button3 = new MyButton(StaticImage.backOfqiuyuani_d,
				StaticImage.backOfqiuyuan_l,StaticImage.backOfqiuyuan_b, 930, 55, 76, 34);
	//	this.add(button3.jbutton);
		
		
		MyButton button0 = new MyButton(StaticImage.backOfqiudui_d,
				StaticImage.backOfqiudui_l,StaticImage.backOfqiudui_b, 1020, 55, 76, 34);
		this.add(button0.jbutton);
		changeToTeamPanel = button0.jbutton;
          changeToTeamPanel.addActionListener(new button0ActionListener());

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
//		PlayerTotalVO playerTotalVO=new PlayerTotalVO(TypeOfMatch.ALLSTAR, "dd","ff","ff", "ff", "hov", 1, 2, 3, 4, 5, 6,"1",1, 2,"2",2,2, "3",6,3,3,3, 4,4,4,5,4.4);
//		
//	arrTotalVOs.add(playerTotalVO);
          paintTotalTable();
          
          jLabelHot=new LeftHotPanel(currentJpanel, currentFrame);
  		
  		this.add(jLabelHot.hotPanel,0);

  	
	}
	private class SelectJComboBox<T> extends JComboBox<T> {
			SelectJComboBox() {
				this.setBackground(new Color(69, 69, 69));
				this.setForeground(new Color(162,210,227));
				this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                    
			}

		}

	private void paintTotalTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspane!=null){
		this.remove(jspane);}
		jspane = new JScrollPane();
		jspane.setBounds(0, 176, 1276, 574);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
statTable=new PlayerJTable(this, arrTotalVOs);
		statTable.setBounds(200, 200, 800, 600);
//		 TableColumnModel columnModel = statTable.getColumnModel();
//	        TableColumn column = columnModel.getColumn(0);
//	        column.setMinWidth(0);
//	        column.setMaxWidth(0);
//	        column.setWidth(0);
//	        column.setPreferredWidth(0);
		statTable.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int c = statTable.columnAtPoint(e.getPoint());
				if(c>=4){
					if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){
						arrTotalVOs=playerBLService.sortBySelectedTotal(arrTotalVOs,TypeOfSort4Players.values()[c-4] , TypeOfSort.ASCENDING_ORDER_TOTAL);
						upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
					}else{
						arrTotalVOs=playerBLService.sortBySelectedTotal(arrTotalVOs,TypeOfSort4Players.values()[c-4] , TypeOfSort.DESCENDING_ORDER_TOTAL);
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
System.out.println(statTable.getValueAt(
		statTable.getSelectedRow(), 0)
		.toString());
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
	private void paintHighTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspane!=null){
			this.remove(jspane);}
		jspane = new JScrollPane();
		jspane.setBounds(0, 176, 1276, 574);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		statTable = new PlayerHighJTable(
			this,arrhiHighVOs);
		statTable.setBounds(200, 200, 800, 600);
		statTable.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int c = statTable.columnAtPoint(e.getPoint());
				if(c>=8){
					if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){
						arrhiHighVOs=playerBLService.sortBySelectedHigh(arrhiHighVOs,TypeOfSort4PlayerHigh.values()[c-8], TypeOfSort.ASCENDING_ORDER_TOTAL);
						upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
					}else{
						arrhiHighVOs=playerBLService.sortBySelectedHigh(arrhiHighVOs,TypeOfSort4PlayerHigh.values()[c-8], TypeOfSort.DESCENDING_ORDER_TOTAL);
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
		this.add(jspane);
		this.validate();
		this.repaint();
	}
	private void paintTotalAverageTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspane!=null){
			this.remove(jspane);}
		jspane = new JScrollPane();
		jspane.setBounds(0, 176, 1276, 574);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		ArrayList< PlayerTotalVO> averageArrayList=new ArrayList<PlayerTotalVO>();

		for(int i=0;i<arrTotalVOs.size();i++){
			int numbers=arrTotalVOs.get(i).getGP();
			 PlayerTotalVO averageVo=new PlayerTotalVO(arrTotalVOs.get(i).getMatchType(),arrTotalVOs.get(i).getMatchSeason(),arrTotalVOs.get(i).getPlayerID(),arrTotalVOs.get(i).getPlayerName(),arrTotalVOs.get(i).getTeamID(),arrTotalVOs.get(i).getTeamName(),
					 arrTotalVOs.get(i).getGP(),arrTotalVOs.get(i).getGS(),arrTotalVOs.get(i).getMIN(),arrTotalVOs.get(i).getPTS()/numbers,arrTotalVOs.get(i).getFGA()/numbers,
					 arrTotalVOs.get(i).getFGM()/numbers,arrTotalVOs.get(i).getFGPer(),arrTotalVOs.get(i).getTPA()/numbers,arrTotalVOs.get(i).getTPM()/numbers,arrTotalVOs.get(i).getTPPer(),arrTotalVOs.get(i).getFTA()/numbers,
					 arrTotalVOs.get(i).getFTM()/numbers,arrTotalVOs.get(i).getFTPer(),arrTotalVOs.get(i).getREB()/numbers,arrTotalVOs.get(i).getOREB()/numbers,arrTotalVOs.get(i).getDREB()/numbers,
					 arrTotalVOs.get(i).getAST()/numbers,arrTotalVOs.get(i).getSTL()/numbers,arrTotalVOs.get(i).getBLK()/numbers,arrTotalVOs.get(i).getTOV()/numbers,arrTotalVOs.get(i).getPF()/numbers,arrTotalVOs.get(i).getEffPer());
			  averageArrayList.add(averageVo);
		}
		
		statTable=new PlayerJTable(this, averageArrayList);
		statTable.setBounds(200, 200, 800, 600);
		statTable.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int c = statTable.columnAtPoint(e.getPoint());
				if(c>=4){
					if(upOrDown.equals(TypeOfUpOrDown.ASCENDING_ORDER)){
						arrTotalVOs=playerBLService.sortBySelectedTotal(arrTotalVOs,TypeOfSort4Players.values()[c-4] , TypeOfSort.ASCENDING_ORDER_AVERAGE);
						upOrDown=TypeOfUpOrDown.DESCENDING_ORDER;
					}else{
						arrTotalVOs=playerBLService.sortBySelectedTotal(arrTotalVOs,TypeOfSort4Players.values()[c-4] , TypeOfSort.DESCENDING_ORDER_AVERAGE);
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
		this.add(jspane);
		this.validate();
		this.repaint(); 
		
	}
	class searchButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			seanson=seasonsBox.getSelectedItem().toString();
	/**
	 * 根据赛季和种类调用
	 */
		west.turnBack();
		east.turnBack();
		qian.turnBack();
		hou.turnBack();
		zhong.turnBack();
		zong.setBack();
		average.turnBack();
		
		arrTotalVOs=playerBLService.searchPlayers(inputSearchField.jtextfield.getText(), typeOfShow,seanson);
	    paintTotalTable();
		checkTotalNull(arrTotalVOs);
		}

	}
	private void checkTotalNull(ArrayList<PlayerTotalVO> arrTotalVOs2){
		if(arrTotalVOs2.size()==0){
			 MyTipsFrame mtf = new MyTipsFrame();
			 MyFailTipsPanel mtPanel = new MyFailTipsPanel(mtf,
			 StaticImage.backOfplayerFail.getImage());
			 mtf.getIni(mtPanel);
		}
		
	}
	private void checkHighNull(ArrayList<PlayerHighVO> arrhighHighVOs){
		if(arrhighHighVOs.size()==0){
			 MyTipsFrame mtf = new MyTipsFrame();
			 MyFailTipsPanel mtPanel = new MyFailTipsPanel(mtf,
			 StaticImage.backOfplayerFail.getImage());
			 mtf.getIni(mtPanel);
		}
		
	}
	class button0ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Team(Player.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}

	class button1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Match(Player.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}
	class button2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) { 
			nextJpanel = new Statics(Player.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(StaticImage.backOfnewplayerBk.getImage(), 0, 0, null);
	}
}

