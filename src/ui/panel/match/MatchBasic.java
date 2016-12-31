package ui.panel.match;

import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.panel.player.PlayerBasic;
import ui.panel.team.TeamBasic;
import ui.tool.button.JiaButton;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.label.MyJLabel;
import ui.tool.picture.StaticImage;
import ui.tool.table.PlayerInMatchJTable;
import ui.tool.table.StatJTable;
import ui.tool.table.TeamInMatchJTable;
import ui.tool.textfield.BigBlackTextField;
import ui.tool.textfield.BigRedTextField;
import ui.tool.textfield.SmallTextField;
import vo.MatchBasicVO;
import vo.PlayerInMatchVO;
import vo.TeamInMatchVO;
import blService.MatchBLService;
import blServiceImp.MatchBLImp;
import config.ConfigFactory;
import config.GameConfig;

public class MatchBasic extends JPanel{
	
	// 获取配置
		GameConfig cfg = ConfigFactory.getGameConfig();
		/**
		 * 放四个表格
		 */
		JScrollPane jspane_firstPlayers;
		StatJTable statTable_firstPlayers;
		JScrollPane jspane_secondPlayers;
		StatJTable statTable_secondPlayers;
		JScrollPane jspane_firstTeam;
		StatJTable statTable_firstTeam;
		JScrollPane jspane_secondTeam;
		StatJTable statTable_secondTeam;
		JPanel  tableJPanel;
		JScrollPane all;
		JLabel firstPlayersJLabel;
		JLabel secondPlayersJLabel;
		JLabel firstTeamJLabel;
		JLabel secondTeamJLabel;
		   /*
	     *  用于判断要拉长还是缩短,0处于缩短状态，1表示处于拉长状态
	     */
		private static int playerAJia=0;
		private static int playerHJia=0;
	
		/**
		 * 两个下拉按钮
		 */
			private JButton playerAButton = new JButton();// 存放按钮
			private JButton playerHButton = new JButton();// 存放按钮
		
			JiaButton button0 ;
			JiaButton button1 ;
			MatchBLService matchBLService=new MatchBLImp();
		// 返回按钮
		private JButton returnJButton = new JButton();// 存放按钮
		private Frame currentFrame;
		private JPanel lastJPanel;
		private JPanel nextJpanel;
		/**
		 * 界面传来的时间和比赛对阵  客队-主队
		 */
		private static MatchBasicVO matchBasicVO;
		
		private JPanel currentJpanel;
	    /**
		 * 常规赛的数据list
		 */
		ArrayList<PlayerInMatchVO> playerVO_ke = new ArrayList<PlayerInMatchVO>();
		/**
		 * 季后赛的数据list
		 */
		ArrayList<PlayerInMatchVO> playerVO_zhu= new ArrayList<PlayerInMatchVO>();
		/**
		 * 全明星的数据list
		 */
		ArrayList<TeamInMatchVO> teamVos_ke = new ArrayList<TeamInMatchVO>();
		/**
		 * 最近五场比赛
		 */
		ArrayList<TeamInMatchVO> teamVos_zhu = new ArrayList<TeamInMatchVO>();

		public MatchBasic(JPanel lastJPanel, MatchBasicVO mBasicVO, Frame current) {
			setVisible(true);
			setLayout(null);
		this.matchBasicVO=mBasicVO;
			this.lastJPanel = lastJPanel;

			this.currentFrame = current;

			this.currentJpanel = this;

			initialize();

		}

		private void initialize() {
			
			this.setSize(cfg.getWidth(), cfg.getHeight());
			
			playerVO_ke=matchBLService.getPlayerInMatchVOs4Match(matchBasicVO.getMatchDate(), matchBasicVO.getAwayTeam(), 
					matchBasicVO.getHomeTeam(),matchBasicVO.getAwayTeam());
			playerVO_zhu=matchBLService.getPlayerInMatchVOs4Match(matchBasicVO.getMatchDate(), matchBasicVO.getAwayTeam(),
					matchBasicVO.getHomeTeam(),matchBasicVO.getHomeTeam());
			teamVos_ke=matchBLService.getTeamInMatchVOs4Match(matchBasicVO.getMatchDate(), matchBasicVO.getAwayTeam(),
					matchBasicVO.getHomeTeam(), matchBasicVO.getAwayTeam());
			teamVos_zhu=matchBLService.getTeamInMatchVOs4Match(matchBasicVO.getMatchDate(), matchBasicVO.getAwayTeam(),
					matchBasicVO.getHomeTeam(), matchBasicVO.getHomeTeam());
			
//			System.out.println(teamVos_zhu.size());
//			System.out.println(teamVos_ke.size());
//			System.out.println(playerVO_zhu.size());
//			System.out.println(playerVO_ke.size());
			/**
			 * 显示头像
			 */
			 MyButton returnButton = new MyButton(StaticImage.backOfback,StaticImage.backOfback,
					 StaticImage.backOfbackEntered, 9,9, 117, 31);
					 this.add(returnButton.jbutton);
					 returnJButton = returnButton.jbutton;
					 returnJButton.addActionListener(new returnButtonActionListener());
			String portraitAddress = "png";

			ImageIcon portait = new ImageIcon(portraitAddress + "/" + matchBasicVO.getAwayTeam()
					+ ".png");
			MyJLabel picture = new MyJLabel(138, 55, portait.getIconWidth(),portait.getIconHeight(), portait, portait);

			this.add(picture.label);
			picture.label.addMouseListener(new MouseAdapter() {
				 
				
				public void mouseClicked(MouseEvent e) {
					nextJpanel = new TeamBasic(currentJpanel,matchBasicVO.getAwayTeam(), currentFrame);
					MyFrame.getFrame().changePanel(nextJpanel);
					
				}
			});
			ImageIcon portait2 = new ImageIcon(portraitAddress + "/" + matchBasicVO.getHomeTeam()
					+ ".png");
			MyJLabel picture2 = new MyJLabel(853, 55, portait2.getIconWidth(),portait2.getIconHeight(), portait2, portait2);
			picture2.label.addMouseListener(new MouseAdapter() {
				 
				
				public void mouseClicked(MouseEvent e) {
					nextJpanel = new TeamBasic(currentJpanel,matchBasicVO.getHomeTeam(), currentFrame);
					MyFrame.getFrame().changePanel(nextJpanel);
					
				}
			});
			this.add(picture2.label);
			
			if(matchBasicVO.getTotalPoints_awayTeam()>matchBasicVO.getTotalPoints_homeTeam()){
				BigRedTextField wimteam =new BigRedTextField(480,114,matchBasicVO.getAwayTeam());
				this.add(wimteam.jtextfield);
				BigBlackTextField lossteam =new BigBlackTextField(697,114,matchBasicVO.getHomeTeam());
				  
					this.add(lossteam.jtextfield);
					BigRedTextField winNumber =new BigRedTextField(67,113,matchBasicVO.getTotalPoints_awayTeam()+"");
					this.add(winNumber.jtextfield);
					BigBlackTextField lossNumber =new BigBlackTextField(1080,113,matchBasicVO.getTotalPoints_homeTeam()+"");
					  
						this.add(lossNumber.jtextfield);
			}else{
				BigBlackTextField wimteam =new BigBlackTextField(480,114,matchBasicVO.getAwayTeam());
				this.add(wimteam.jtextfield);
				BigRedTextField lossteam =new BigRedTextField(697,114,matchBasicVO.getHomeTeam());
				  
					this.add(lossteam.jtextfield);
					BigBlackTextField winNumber =new BigBlackTextField(67,113,matchBasicVO.getTotalPoints_awayTeam()+"");
					this.add(winNumber.jtextfield);
					BigRedTextField lossNumber =new BigRedTextField(1080,113,matchBasicVO.getTotalPoints_homeTeam()+"");
					  
						this.add(lossNumber.jtextfield);
			}
			SmallTextField team1=new SmallTextField(423, 248, matchBasicVO.getAwayTeam());
			   this.add(team1.jtextfield);
				SmallTextField team2=new SmallTextField(423, 280, matchBasicVO.getHomeTeam());
				   this.add(team2.jtextfield);
				   
			   SmallTextField fisrt1=new SmallTextField(492, 251, matchBasicVO.getFirstPoints_awayTeam()+"");
			   this.add(fisrt1.jtextfield);
				SmallTextField fisrt2=new SmallTextField(492, 280, matchBasicVO.getFirstPoints_homeTeam()+"");
			   this.add(fisrt2.jtextfield);
			   SmallTextField second1=new SmallTextField(582, 250, matchBasicVO.getSecondPoints_awayTeam()+"");
			   this.add(second1.jtextfield);
			   SmallTextField second2=new SmallTextField(582, 280,matchBasicVO.getSecondPoints_homeTeam()+"");
			   this.add(second2.jtextfield);
			   SmallTextField third1=new SmallTextField(676, 250,matchBasicVO.getThirdPoints_awayTeam()+"");
			   this.add(third1.jtextfield);
			   SmallTextField third2=new SmallTextField(676, 280, matchBasicVO.getThirdPoints_homeTeam()+"");
			   
			   this.add(third2.jtextfield);
			   SmallTextField forth1=new SmallTextField(768, 250,matchBasicVO.getThirdPoints_awayTeam()+"");
			   this.add(forth1.jtextfield);
			   SmallTextField forth2=new SmallTextField(768, 280, matchBasicVO.getThirdPoints_homeTeam()+"");
			   
			   
			   this.add(forth2.jtextfield);
			   int totalAway=matchBasicVO.getFirstPoints_awayTeam()+matchBasicVO.getSecondPoints_awayTeam()+matchBasicVO.getThirdPoints_awayTeam()+matchBasicVO.getForthPoints_awayTeam();
			int totalHome=matchBasicVO.getFirstPoints_homeTeam()+matchBasicVO.getSecondPoints_homeTeam()+matchBasicVO.getThirdPoints_homeTeam()+matchBasicVO.getFirstPoints_homeTeam();
			   if(totalAway<matchBasicVO.getTotalPoints_awayTeam()||totalHome<matchBasicVO.getTotalPoints_homeTeam()){
				   SmallTextField total1=new SmallTextField(831, 250,(matchBasicVO.getTotalPoints_awayTeam()-totalAway)+"");
				   this.add(total1.jtextfield);
				   SmallTextField total2=new SmallTextField(831, 280, (matchBasicVO.getTotalPoints_homeTeam()-totalHome)+"");
				   this.add(total2.jtextfield);
			   }
			  
				 button0 = new JiaButton(StaticImage.backOfdetailbtn,
							StaticImage.backOfminusbtn, 1229, 2, 42, 37);
					//this.add(button0.jbutton);
					playerAButton = button0.jbutton;
					playerAButton.addActionListener(new button0ActionListener());

					 button1 = new JiaButton(StaticImage.backOfdetailbtn,
							StaticImage.backOfminusbtn, 1229, 2, 42, 37);
				//	this.add(button1.jbutton);
					playerHButton = button1.jbutton;
					playerHButton.addActionListener(new button1ActionListener());
				 
			   
			//MyTextField3 myTextField3=new MyTextField3(63, 112,matchBasicVO.getTotalPoints());
			
			
			/**
			 * 画下面的各种表格啊
			 */
			all=new JScrollPane();
			all.setBounds(0,312,1276,408);
			firstPlayersJLabel=new JLabel(StaticImage.backOfAWAYPLAYERSbtn);
			firstPlayersJLabel.setLayout(null);
			firstPlayersJLabel.add(playerAButton);
			firstTeamJLabel=new JLabel(StaticImage.backOfAWAYTEAMbtn);
			secondPlayersJLabel=new JLabel(StaticImage.backOfHOMEPLAYERSbtn);
			secondPlayersJLabel.setLayout(null);
			secondPlayersJLabel.add(playerHButton);
			
			secondTeamJLabel=new JLabel(StaticImage.backOfHOMETEAMbtn);
			
			jspane_firstPlayers = new JScrollPane();
			statTable_firstPlayers = new PlayerInMatchJTable( this,playerVO_ke);
			statTable_firstPlayers.setBounds(200, 200, 800, 600);
			statTable_firstPlayers.refresh();
			statTable_firstPlayers.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						if (statTable_firstPlayers.getSelectedRow() == -1) {
							return;
						} else {

							nextJpanel = new PlayerBasic(currentJpanel,
									statTable_firstPlayers.getValueAt(
											statTable_firstPlayers.getSelectedRow(), 0)
											.toString(), currentFrame);

							MyFrame.getFrame().changePanel(nextJpanel);
						}

					}

				}
			});
			jspane_firstPlayers.setViewportView(statTable_firstPlayers);
		
			
			jspane_firstTeam= new JScrollPane();
			statTable_firstTeam = new TeamInMatchJTable( this,teamVos_ke);
			statTable_firstTeam.setBounds(200, 200, 800, 600);
			statTable_firstTeam.refresh();
			
			jspane_firstTeam.setViewportView(statTable_firstTeam);
		
			jspane_secondPlayers = new JScrollPane();
			statTable_secondPlayers = new PlayerInMatchJTable( this,playerVO_zhu);
			statTable_secondPlayers.setBounds(200, 200, 800, 600);
			statTable_secondPlayers.refresh();
			statTable_secondPlayers.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					if (arg0.getClickCount() == 2) {
						if (statTable_secondPlayers.getSelectedRow() == -1) {
							return;
						} else {

							nextJpanel = new PlayerBasic(currentJpanel,
									statTable_secondPlayers.getValueAt(
											statTable_secondPlayers.getSelectedRow(), 0)
											.toString(), currentFrame);

							MyFrame.getFrame().changePanel(nextJpanel);
						}

					}

				}
			});
			jspane_secondPlayers.setViewportView(statTable_secondPlayers);
			
			jspane_secondTeam= new JScrollPane();
			statTable_secondTeam = new TeamInMatchJTable( this,teamVos_zhu);
			statTable_secondTeam.setBounds(200, 200, 800, 600);
			statTable_secondTeam.refresh();
			jspane_secondTeam.setViewportView(statTable_secondTeam);
			tableJPanel=new JPanel();
			tableJPanel.setBounds(0,0,1270,408);
			
			tableJPanel.setLayout(new GridBagLayout());
			
			tableJPanel.add(firstPlayersJLabel, new GridBagConstraints(0, 0, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
			 jspane_firstPlayers.setSize(1270, 63);
			 jspane_firstPlayers.setPreferredSize(new Dimension(jspane_firstPlayers.getWidth(),jspane_firstPlayers.getHeight()));
			 jspane_firstPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    		
			 tableJPanel.add(jspane_firstPlayers, new GridBagConstraints(0, 1, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			jspane_firstTeam.setSize(1270, 63);
			jspane_firstTeam.setPreferredSize(new Dimension(jspane_firstTeam.getWidth(),jspane_firstTeam.getHeight()));
			jspane_firstTeam.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		
			tableJPanel.add(firstTeamJLabel, new GridBagConstraints(0, 2, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
			tableJPanel.add(jspane_firstTeam, new GridBagConstraints(0, 3, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

			jspane_secondPlayers.setSize(1270, 63);
			jspane_secondPlayers.setPreferredSize(new Dimension(jspane_secondPlayers.getWidth(),jspane_secondPlayers.getHeight()));
			jspane_secondPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		
			tableJPanel.add(secondPlayersJLabel, new GridBagConstraints(0, 4, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			
			tableJPanel.add(jspane_secondPlayers, new GridBagConstraints(0, 5, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			
			
			tableJPanel.add(secondTeamJLabel, new GridBagConstraints(0, 6, 0, 1, 0, 0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
			jspane_secondTeam.setSize(1270, 63);
			jspane_secondTeam.setPreferredSize(new Dimension(jspane_secondTeam.getWidth(),jspane_secondTeam.getHeight()));
			jspane_secondTeam.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		
			tableJPanel.add(jspane_secondTeam, new GridBagConstraints(0, 7, 0, 0, 0, 0,
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
				  if(playerAJia==0){
					  playerAJia=1;
			            jspane_firstPlayers.setSize(new Dimension(1276,38+ playerVO_ke.size()*25));
			            jspane_firstPlayers.setPreferredSize(new Dimension(1276,38+ playerVO_ke.size()*25));
			            jspane_firstPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						 all.repaint();
						 all.validate();
						 validate();
						 repaint();
						
			        }else{
			         button0.turnBack();
			         playerAJia=0;
			         jspane_firstPlayers.setSize(new Dimension(1276,63));
			         jspane_firstPlayers.setPreferredSize(new Dimension(1276,63));
			         jspane_firstPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						 all.repaint();
						 all.validate();
						 validate();
						 repaint();
			        }
					
			}
		}

		class button1ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				if(playerHJia==0){
					playerHJia=1;
			            jspane_secondPlayers.setSize(new Dimension(1276,38+ playerVO_zhu.size()*25));
			            jspane_secondPlayers.setPreferredSize(new Dimension(1276,38+ playerVO_zhu.size()*25));
			            jspane_secondPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						 all.repaint();
						 all.validate();
						 validate();
						 repaint();
						
			        }else{
			         button1.turnBack();
			         playerHJia=0;
			         jspane_secondPlayers.setSize(new Dimension(1276,63));
			         jspane_secondPlayers.setPreferredSize(new Dimension(1276,63));
			         jspane_secondPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						 all.repaint();
						 all.validate();
						 validate();
						 repaint();
			        }
			}
		}
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(StaticImage.backOfmatchBasic.getImage(), 0, 0, null);
	}
}
