package ui.panel.team;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.panel.player.PlayerBasic;
import ui.tool.button.JiaButton;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.jcombox.ColorArrowUI;
import ui.tool.label.MyJLabel;
import ui.tool.label.MyTextLabel;
import ui.tool.label.MyTextLabelShort;
import ui.tool.picture.StaticImage;
import ui.tool.table.PlayerBasicJTable;
import ui.tool.table.StatJTable;
import ui.tool.table.TeamHighJTable;
import ui.tool.table.TeamInMatchJTable;
import ui.tool.table.TeamJTable;
import ui.tool.textfield.PlayerNameTextField;
import ui.tool.textfield.SmallTextField;
import vo.DataKingVO;
import vo.MembersVO;
import vo.PlayerBasicVO;
import vo.TeamBasicVO;
import vo.TeamHighVO;
import vo.TeamInMatchVO;
import vo.TeamTotalVO;
import blService.PlayerBLService;
import blService.TeamBLService;
import blServiceImp.PlayerBLImp;
import blServiceImp.TeamBLImp;
import config.ConfigFactory;
import config.GameConfig;
import dataServiceImp.DatabaseException;
import enumerate.TypeOfMatch;

public class TeamBasic extends JPanel {
	// 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();
	/**
	 * 看是哪个赛季
	 */
	private static String season="2014-15";
	private MyTextLabelShort playerscore;
	private MyTextLabelShort playerassist;
	private MyTextLabelShort playerbound;
	DataKingVO dataKingVO;
	/**
	 * 放三个表格。并没有全明星,不过加一个他的所有球员
	 */ 
	JScrollPane jspane_players;
	PlayerBasicJTable statTable_players;
	JScrollPane jspane_regualr;
	StatJTable statTable_regualr;
	/**
	 * 做选择赛季的下拉框
	 */
	SelectJComboBox<String> seasonsBox;
	JScrollPane jspane_playoff;
	StatJTable statTable_playoff;
	JScrollPane jspane_recent;
	StatJTable statTable_recent;
	JPanel  tableJPanel;
	JScrollPane all;
	JLabel regularlJLabel;
	JLabel playersJLabel;
	JLabel playoffJLabel;
	JLabel recentJLabel;
	   /*
     *  用于判断要拉长还是缩短,0处于缩短状态，1表示处于拉长状态
     */
	private static int regularJia=0;
	private static int playoffJia=0;
	private static int playersJia=0;
	private static int recentJia=0;
	// 返回按钮
	private JButton returnJButton = new JButton();// 存放按钮
	// 基础
	private static MyJLabel traditional_playoff = new MyJLabel(1013, 7, 127, 26,
			StaticImage.backOfTRADITIONAL2btn,
			StaticImage.backOfTRADITIONAL3btn);
	// 高阶
	private static MyJLabel high_playoff = new MyJLabel(1140, 7, 56, 26,
			StaticImage.backOfHIGH2btn, StaticImage.backOfHIGH3btn);

	// 基础
			private static MyJLabel traditional_regular = new MyJLabel(1013, 7, 127, 26,
					StaticImage.backOfTRADITIONAL2btn,
					StaticImage.backOfTRADITIONAL3btn);
			// 高阶
			private static MyJLabel high_regular = new MyJLabel(1140, 7, 56, 26,
					StaticImage.backOfHIGH2btn, StaticImage.backOfHIGH3btn);
	/**
	 * 三个下拉按钮
	 */
		private JButton regularButton = new JButton();// 存放按钮
		private JButton playoffButton = new JButton();// 存放按钮
		private JButton playersButton = new JButton();// 存放按钮
		private JButton recentButton= new JButton();// 存放按钮
		JiaButton button0 ;
		JiaButton button1 ;
		JiaButton button2 ;
		JiaButton button3 ;
	private Frame currentFrame;
	private JPanel lastJPanel;
	private JPanel nextJpanel;
	
	/**
	 * 界面传来的球员名字
	 */
	private static String teamID;
	private static String teamName;
	TeamBasicVO teamBasicVO;
	private JPanel currentJpanel;
	TeamBLService teamBLService=new TeamBLImp();
	PlayerBLService playerBLService;
	ArrayList<PlayerBasicVO> playerBasicVOs=new ArrayList<PlayerBasicVO>();
	/**
	 * 常规赛的数据list
	 */
	ArrayList<TeamTotalVO> teamVO_scores_regular = new ArrayList<TeamTotalVO>();
	/**
	 * 季后赛的数据list
	 */
	ArrayList<TeamTotalVO> teamVO_scores_playoff = new ArrayList<TeamTotalVO>();
	/**
	 * 常规赛的数据list
	 */
	ArrayList<TeamHighVO> teamVO_high_regular = new ArrayList<TeamHighVO>();
	/**
	 * 季后赛的数据list
	 */
	ArrayList<TeamHighVO> teamVO_high_playoff = new ArrayList<TeamHighVO>();
	
	/**
	 * 最近五场比赛
	 */
	ArrayList<TeamInMatchVO> teamVO_inMatchs = new ArrayList<TeamInMatchVO>();
	MembersVO membersVO;
	public TeamBasic(JPanel lastJPanel, String name, Frame current) {
		setVisible(true);
		setLayout(null);
		this.teamName = name;
		this.lastJPanel = lastJPanel;

		this.currentFrame = current;
		this.currentJpanel = this;
		initialize();

	}
	private class SelectJComboBox<T> extends JComboBox<T> {
		SelectJComboBox() {
			this.setBackground(new Color(69, 69, 69));
			this.setForeground(new Color(10, 54, 101));
			this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                
		}

	}


	public String changeToShort(String s){
		if(s.length()>4){
			return s.substring(0,4);
		}
		return s;
	}
	private void initialize() {
		this.setSize(cfg.getWidth(), cfg.getHeight());
		try {
			playerBLService=new PlayerBLImp();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//season=seasonsBox.getSelectedItem().toString();
		teamBasicVO=teamBLService.get_from_abbreviation(teamName);
		teamID=teamBasicVO.getTeamID();
		teamVO_high_playoff=teamBLService.getPlayoffByTeamNameHigh(teamName);
		teamVO_high_regular=teamBLService.getRegularByTeamNameHigh(teamName);
		teamVO_inMatchs=teamBLService.getRecentMatchesforTeam(teamName);
		teamVO_scores_playoff=teamBLService.getPlayoffByTeamName(teamName);
		teamVO_scores_regular=teamBLService.getRegularByTeamName(teamName);
		membersVO=teamBLService.getMemberOfTeam(teamID, season);
		 MyButton returnButton = new MyButton(StaticImage.backOfback,StaticImage.backOfback,
				 StaticImage.backOfbackEntered, 9,9, 117, 31);
				 this.add(returnButton.jbutton);
		 returnJButton = returnButton.jbutton;
		 returnJButton.addActionListener(new returnButtonActionListener());
	
		 ArrayList<String> playerIDs=membersVO.getPlayerIDs();

		 for(int i=0;i<playerIDs.size();i++){
			 playerBasicVOs.add(playerBLService.getPlayerDetail(playerIDs.get(i))); 
		 }
		  SmallTextField coach=new SmallTextField(1220, 2, membersVO.getChiefCoach());
//		   this.add(coach.jtextfield);
	//	teamBasicVO=new TeamBasicVO("111", "hov", "hov", "w", "ww", "w", "hh", 1232);
		PlayerNameTextField playerFirstName =new PlayerNameTextField(511, 74,teamBasicVO.getTeamName());
		//playerFirstName.jtextfield.setBounds(512, 65,200, 25);
		this.add(playerFirstName.jtextfield);
		PlayerNameTextField playerLastName =new PlayerNameTextField(396,74,teamBasicVO.getAbbreviation());
	//	playerLastName.jtextfield.setBounds(512, 100,200, 25);
		this.add(playerLastName.jtextfield);
		SmallTextField postion=new SmallTextField(511, 137, teamBasicVO.getLocation());
	   this.add(postion.jtextfield);
	   SmallTextField team=new SmallTextField(496, 158, teamBasicVO.getZone());
	   this.add(team.jtextfield);
	   SmallTextField weight=new SmallTextField(513, 179, teamBasicVO.getTeam_partition());
	   this.add(weight.jtextfield);
	   SmallTextField height=new SmallTextField(532, 199,teamBasicVO.getHomeField());
	   this.add(height.jtextfield);
	   SmallTextField born=new SmallTextField(546, 217, teamBasicVO.getTime_setUp()+"");
	   this.add(born.jtextfield);
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
		seasonsBox.setBounds(1047,7, 141, 22);
		seasonsBox.setBackground(new Color(156,156,156));
		seasonsBox.setUI(ColorArrowUI.createUI(seasonsBox));
  seasonsBox.addMouseListener(new MouseAdapter() {
			  

			public void mouseEntered(MouseEvent arg0) {
				season=seasonsBox.getSelectedItem().toString();
				membersVO=teamBLService.getMemberOfTeam(teamID, season);
				 ArrayList<String> playerIDs=membersVO.getPlayerIDs();
                  playerBasicVOs.clear();
				 for(int i=0;i<playerIDs.size();i++){
					 playerBasicVOs.add(playerBLService.getPlayerDetail(playerIDs.get(i))); 
					 
				 }
				 if(jspane_players!=null){
					 tableJPanel.remove(jspane_players);
               	 }
				 jspane_players = new JScrollPane();
        		statTable_players = new PlayerBasicJTable(TeamBasic. this,playerBasicVOs);
        		statTable_players.setBounds(200, 200, 800, 600);
        		statTable_players.refresh();
        		jspane_players.setViewportView(statTable_players);
        		jspane_players.validate();
        		jspane_players.repaint();
        		tableJPanel.add(jspane_players, new GridBagConstraints(0, 1, 0, 1, 0, 0,
        				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

			}
		});
	
		this.add(seasonsBox);
		 button0 = new JiaButton(StaticImage.backOfdetailbtn,
				StaticImage.backOfminusbtn, 1229, 2, 42, 37);
		//this.add(button0.jbutton);
		regularButton = button0.jbutton;
		regularButton.addActionListener(new button0ActionListener());

		 button1 = new JiaButton(StaticImage.backOfdetailbtn,
				StaticImage.backOfminusbtn, 1229, 2, 42, 37);
	//	this.add(button1.jbutton);
		playoffButton = button1.jbutton;
		playoffButton.addActionListener(new button1ActionListener());
		 button2 = new JiaButton(StaticImage.backOfdetailbtn,
					StaticImage.backOfminusbtn, 1229, 2, 42, 37);
			//this.add(button0.jbutton);
			playersButton = button2.jbutton;
			playersButton.addActionListener(new button2ActionListener());
		button3 = new JiaButton(StaticImage.backOfdetailbtn,StaticImage.backOfminusbtn, 1229,2, 42, 37);
	//	this.add(button3.jbutton);
		recentButton = button3.jbutton;
		recentButton.addActionListener(new button3ActionListener());
		dataKingVO=playerBLService.getDataKing(teamName,TypeOfMatch.REGULAR,"2014-15");
		if(dataKingVO!=null){
			
			
			playerscore=new MyTextLabelShort(862, 136,dataKingVO.getPlayerName_score());
		

			
			playerbound=new MyTextLabelShort(997, 136,dataKingVO.getPlayerName_rebound());

			playerassist=new MyTextLabelShort(1141, 136,dataKingVO.getPlayerName_assist());

		this.add(playerscore.label);
		this.add(playerbound.label);
		this.add(playerassist.label);
		this.add(new MyTextLabel(862, 188,this.changeToShort(dataKingVO.getScore()+"")).label);
		this.add(new MyTextLabel(997, 188,this.changeToShort(dataKingVO.getRebound()+"")).label);
		this.add(new MyTextLabel(1141, 188,this.changeToShort(dataKingVO.getAssist()+"")).label);
		}
					/**
					 * 显示头像
					 */

					String portraitAddress = "png";
           
					ImageIcon portait = new ImageIcon(portraitAddress + "/" + teamName
							+ ".png");
					MyJLabel picture = new MyJLabel(20, 56, portait.getIconWidth(),portait.getIconHeight(), portait, portait);

					this.add(picture.label);
					/**
					 * 常规赛和季后赛有低阶和高阶的区别
					 */
			         traditional_regular .label.addMouseListener(new MouseAdapter() {
			        		public void mouseClicked(MouseEvent arg0) {
			    				
			                  	 high_regular.turnBack();
			                  	 if(jspane_regualr!=null){
			                  		tableJPanel.remove(jspane_regualr);
			                  	 }
			                 	jspane_regualr = new JScrollPane();
			            		statTable_regualr = new TeamJTable( TeamBasic.this,teamVO_scores_regular);
			            		statTable_regualr.setBounds(200, 200, 800, 600);
			            		statTable_regualr.refresh();
			            		jspane_regualr.setViewportView(statTable_regualr);
			            		 jspane_regualr.validate();
			                     jspane_regualr.repaint();
			                	 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 3, 0, 1, 0, 0,
			          				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			          	
			   			}
			         });
			         high_regular .label.addMouseListener(new MouseAdapter() {
			     		public void mouseClicked(MouseEvent arg0) {
			 				
			               	traditional_regular.turnBack();
			               	 if(jspane_regualr!=null){
			               		tableJPanel.remove(jspane_regualr);
			               	 }
			              	jspane_regualr = new JScrollPane();
			         		statTable_regualr = new TeamHighJTable( TeamBasic.this,teamVO_high_regular);
			         		statTable_regualr.setBounds(200, 200, 800, 600);
			         		statTable_regualr.refresh();
			         		jspane_regualr.setViewportView(statTable_regualr);
			         		 jspane_regualr.validate();
			                    jspane_regualr.repaint();
			               	 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 3, 0, 1, 0, 0,
			         				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			         	
						}
			      });
			         traditional_playoff .label.addMouseListener(new MouseAdapter() {
			     		public void mouseClicked(MouseEvent arg0) {
			 				
			               	 high_playoff.turnBack();
			               	 if(jspane_playoff!=null){
			               		tableJPanel.remove(jspane_playoff);
			               	 }
			               	jspane_playoff = new JScrollPane();
			        		statTable_playoff = new TeamJTable(TeamBasic. this,teamVO_scores_playoff);
			        		statTable_playoff.setBounds(200, 200, 800, 600);
			        		statTable_playoff.refresh();
			        		jspane_playoff.setViewportView(statTable_playoff);
			        		jspane_playoff.validate();
			        		jspane_playoff.repaint();
			        		tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 5, 0, 1, 0, 0,
			        				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

						}
			      });
			      high_playoff.label.addMouseListener(new MouseAdapter() {
			  		public void mouseClicked(MouseEvent arg0) {
							
			            	traditional_playoff.turnBack();
			            	 if(jspane_playoff!=null){
			            		 tableJPanel.remove(jspane_playoff);
			               	 }
			               	jspane_playoff = new JScrollPane();
			        		statTable_playoff = new TeamHighJTable(TeamBasic. this,teamVO_high_playoff);
			        		statTable_playoff.setBounds(200, 200, 800, 600);
			        		statTable_playoff.refresh();
			        		jspane_playoff.setViewportView(statTable_playoff);
			        		jspane_playoff.validate();
			        		jspane_playoff.repaint();
			        		tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 5, 0, 1, 0, 0,
			        				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

						}
			   });

					/**
					 * 画下面的各种表格啊
					 */
					all=new JScrollPane();
					all.setBounds(0,310,1276,438);
					regularlJLabel=new JLabel(StaticImage.backOfREGULARSEANSONbtn);
					regularlJLabel.setLayout(null);
					regularlJLabel.add(regularButton);
					regularlJLabel.add(traditional_regular.label);
					regularlJLabel.add(high_regular.label);
					playoffJLabel=new JLabel(StaticImage.backOfPOSTSEASONbtn);
					playoffJLabel.setLayout(null);
					playoffJLabel.add(playoffButton);
					playoffJLabel.add(traditional_playoff.label);
					playoffJLabel.add(high_playoff.label);
					playersJLabel=new JLabel(StaticImage.backOfTEAMROSTER);
					playersJLabel.setLayout(null);
					playersJLabel.add(playersButton);
					playersJLabel.add(seasonsBox);
					
					playersJLabel.add(coach.jtextfield);
						recentJLabel=new JLabel(StaticImage.backOfRECENTbtn);
					recentJLabel.setLayout(null);
					recentJLabel.add(recentButton);

					/**
					 * 初始化所有的jsapne
					 */
						
					jspane_players=new JScrollPane();
					statTable_players=new PlayerBasicJTable(this, playerBasicVOs);
					statTable_players.setBounds(200, 200, 800, 400);
					statTable_players.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent arg0) {
							if (arg0.getClickCount() == 2) {
								if (statTable_players.getSelectedRow() == -1) {
									return;
								} else {

									nextJpanel = new PlayerBasic(currentJpanel,
											statTable_players.getValueAt(
													statTable_players.getSelectedRow(), 0)
													.toString(), currentFrame);

									MyFrame.getFrame().changePanel(nextJpanel);
								}

							}

						}
					});
					statTable_players.refresh();
					jspane_players.setViewportView(statTable_players);
					
					jspane_regualr = new JScrollPane();
				
					
			
					statTable_regualr = new TeamJTable( this,teamVO_scores_regular);
					
					statTable_regualr.setBounds(200, 200, 800, 400);
					statTable_regualr.refresh();
					jspane_regualr.setViewportView(statTable_regualr);
//				
//						for(int i=0;i<10;i++){
//							teamVO_scores_playoff.add(teamTotalVO2);
//							teamVO_scores_playoff.add(teamTotalVO2);
//							teamVO_scores_playoff.add(teamTotalVO2);
//							teamVO_scores_playoff.add(teamTotalVO2);					teamVO_scores_playoff.add(teamTotalVO2);
//							teamVO_scores_playoff.add(teamTotalVO2);					teamVO_scores_playoff.add(teamTotalVO2);
//							teamVO_scores_playoff.add(teamTotalVO2);	
//						}
//						
						
					jspane_playoff = new JScrollPane();
	
					statTable_playoff = new TeamJTable( this,teamVO_scores_playoff);
					statTable_playoff.setBounds(200, 200, 800, 100);
					statTable_playoff.refresh();
					jspane_playoff.setViewportView(statTable_playoff);

					jspane_recent = new JScrollPane();
				
					statTable_recent = new TeamInMatchJTable( this,teamVO_inMatchs);
					statTable_recent.setBounds(200, 200, 800, 100);
					statTable_recent.refresh();
					jspane_recent.setViewportView(statTable_recent);

					tableJPanel=new JPanel();
					tableJPanel.setBounds(0,0,1276,408);
					//tableJPanel.setPreferredSize(new Dimension(tableJPanel.getWidth(),tableJPanel.getHeight()));
					tableJPanel.setLayout(new GridBagLayout());


					tableJPanel.add(playersJLabel, new GridBagConstraints(0, 0, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
				
					 jspane_players.setSize(1276, 95);
					 jspane_players.setPreferredSize(new Dimension(jspane_players.getWidth(),jspane_players.getHeight()));
					 jspane_players.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						
					 tableJPanel.add(jspane_players, new GridBagConstraints(0, 1, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
					jspane_playoff.setSize(1276, 63);
					jspane_playoff.setPreferredSize(new Dimension(jspane_playoff.getWidth(),jspane_playoff.getHeight()));
					jspane_playoff.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					
					tableJPanel.add(regularlJLabel, new GridBagConstraints(0, 2, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
				
					 jspane_regualr.setSize(1276, 63);
					 jspane_regualr.setPreferredSize(new Dimension(jspane_regualr.getWidth(),jspane_regualr.getHeight()));
					 jspane_regualr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						
					 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 3, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
					jspane_playoff.setSize(1276, 63);
					jspane_playoff.setPreferredSize(new Dimension(jspane_playoff.getWidth(),jspane_playoff.getHeight()));
					jspane_playoff.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					
					tableJPanel.add(playoffJLabel, new GridBagConstraints(0, 4, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
				
					tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 5, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
					tableJPanel.add(recentJLabel, new GridBagConstraints(0, 6, 0, 1, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
					jspane_recent.setSize(1276, 63);
					jspane_recent.setPreferredSize(new Dimension(jspane_recent.getWidth(),jspane_recent.getHeight()));
					jspane_recent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					
					tableJPanel.add(jspane_recent, new GridBagConstraints(0, 7, 0, 0, 0, 0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		    		all.setViewportView(tableJPanel);
		    		all.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    		
					this.add(all);
					this.validate();
					this.repaint();
					
	}
	class returnButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			MyFrame.getFrame().changePanel(lastJPanel);

		}

	}
	class button0ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			  if(regularJia==0){
				 regularJia=1;
		            jspane_regualr.setSize(new Dimension(1276,38+ teamVO_scores_regular.size()*25));
					 jspane_regualr.setPreferredSize(new Dimension(1276,38+ teamVO_scores_regular.size()*25));
					 jspane_regualr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
					
		        }else{
		         button0.turnBack();
		        	 regularJia=0;
		        	 jspane_regualr.setSize(new Dimension(1276,63));
					 jspane_regualr.setPreferredSize(new Dimension(1276,63));
					 jspane_regualr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
		        }
				
		}
	}

	class button1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(playoffJia==0){
				 playoffJia=1;
		            jspane_playoff.setSize(new Dimension(1276,38+ teamVO_scores_playoff.size()*25));
		            jspane_playoff.setPreferredSize(new Dimension(1276,38+ teamVO_scores_playoff.size()*25));
		            jspane_playoff.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
					
		        }else{
		         button1.turnBack();
		         playoffJia=0;
		         jspane_playoff.setSize(new Dimension(1276,63));
		         jspane_playoff.setPreferredSize(new Dimension(1276,63));
		         jspane_playoff.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
		        }
		}
	}
	class button2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(playersJia==0){
				playersJia=1;
		            jspane_players.setSize(new Dimension(1276,38+ playerBasicVOs.size()*83));
		            jspane_players.setPreferredSize(new Dimension(1276,38+ playerBasicVOs.size()*83));
		            jspane_players.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
					
		        }else{
		         button2.turnBack();
		         playersJia=0;
		         jspane_players.setSize(new Dimension(1276,95));
		         jspane_players.setPreferredSize(new Dimension(1276,95));
		         jspane_players.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
		        }
		}
	}

	class button3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(recentJia==0){
				recentJia=1;
		            jspane_recent.setSize(new Dimension(1276,38+ teamVO_inMatchs.size()*25));
		            jspane_recent.setPreferredSize(new Dimension(1276,38+ teamVO_inMatchs.size()*25));
		            jspane_recent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
					
		        }else{
		         button3.turnBack();
		         recentJia=0;
		         jspane_recent.setSize(new Dimension(1276,63));
		         jspane_recent.setPreferredSize(new Dimension(1276,63));
		         jspane_recent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
		        }		
		}
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(StaticImage.backOfteamBasic.getImage(), 0, 0, null);
	}
}
