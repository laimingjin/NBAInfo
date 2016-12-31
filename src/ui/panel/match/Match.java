package ui.panel.match;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.omg.CORBA.MARSHAL;

import blService.MatchBLService;
import blServiceImp.MatchBLImp;
import ui.panel.hot.LeftHotPanel;
import ui.panel.player.Player;
import ui.panel.statics.Statics;
import ui.panel.team.Team;
import ui.tool.button.DateChooserJButton;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.label.MyJLabel2;
import ui.tool.picture.StaticImage;
import ui.tool.table.MatchJTable;
import ui.tool.table.StatJTable;
import vo.MatchBasicVO;
import vo.PlayerTotalVO;
import config.ConfigFactory;
import config.GameConfig;
import enumerate.TypeOfMatch;

public class Match  extends JPanel{
	  // 获取配置
			GameConfig cfg = ConfigFactory.getGameConfig();
			 ImageIcon images[];
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
			private JLabel teamsJpanel;
			DateChooserJButton buttonDate;
			MatchBLService matchBLService=new MatchBLImp();
			// 单件模式
			public static ArrayList<MatchBasicVO> matchBasicVOs = new ArrayList<MatchBasicVO>();
			/**
			 * 球队默认选择是""
			 */
			private static String teamName = "";
			public final static String[] ABB={
				"BOS","BKN","NYK","PHI","TOR",
				"CHI","CLE","DET","IND","MIL",
				"ATL","CHA","MIA","ORL","WAS",
				 "DAL","HOU","MEM","NOP","SAS",
                "DEN","MIN","OKC","POR","UTA",
                "GSW","LAC","LAL","PHX","SAC"
               };

			/*
			 * aaaaa
			 */
			private static MyJLabel2 boston = new MyJLabel2(24, 56, 150, 27,
					StaticImage.backOfbostonbtn, StaticImage.backOfbostonPressedbtn);
			private static MyJLabel2 nets = new MyJLabel2(24, 90, 150, 27,
					StaticImage.backOfnetsbtn, StaticImage.backOfnetsPressedbtn);
			private static MyJLabel2 nyk = new MyJLabel2(24, 122, 150, 27,
					StaticImage.backOfnykbtn, StaticImage.backOfnykPressedbtn);
			private static MyJLabel2 ers= new MyJLabel2(24, 156, 150, 27,
					StaticImage.backOfersbtn, StaticImage.backOfersPressedbtn);
			private static MyJLabel2 tr= new MyJLabel2(24, 190, 150, 27,
					StaticImage.backOftrbtn, StaticImage.backOftrPressedbtn);
			
			private static MyJLabel2 bulls= new MyJLabel2(24, 268, 150, 27,
					StaticImage.backOfbullsbtn, StaticImage.backOfbullsPressedbtn);
			private static MyJLabel2 cc= new MyJLabel2(24, 302, 150, 27,
					StaticImage.backOfccbtn, StaticImage.backOfccPressedbtn);
			private static MyJLabel2 dp= new MyJLabel2(24, 336, 150, 27,
					StaticImage.backOfdpbtn, StaticImage.backOfdpPressedbtn);
			private static MyJLabel2 ip= new MyJLabel2(24, 370, 150, 27,
					StaticImage.backOfipbtn, StaticImage.backOfipPressedbtn);
			private static MyJLabel2 mb= new MyJLabel2(24, 404, 150, 27,
					StaticImage.backOfmbbtn, StaticImage.backOfmbPressedbtn);
			
			private static MyJLabel2 ah= new MyJLabel2(24,494, 150, 27,
					StaticImage.backOfahbtn, StaticImage.backOfahPressedbtn);
			private static MyJLabel2 ch= new MyJLabel2(24, 528, 150, 27,
					StaticImage.backOfchbtn, StaticImage.backOfchPressedbtn);
			private static MyJLabel2 heat= new MyJLabel2(24, 562, 150, 27,
					StaticImage.backOfheatbtn, StaticImage.backOfheatPressedbtn);
			private static MyJLabel2 magic= new MyJLabel2(24, 596, 150, 27,
					StaticImage.backOfmagicbtn, StaticImage.backOfmagicPressedbtn);
			private static MyJLabel2 ww= new MyJLabel2(24, 630, 175, 27,
					StaticImage.backOfwwbtn, StaticImage.backOfwwPressedbtn);
			
			private static MyJLabel2 dm= new MyJLabel2(224, 56, 150, 27,
					StaticImage.backOfdmbtn, StaticImage.backOfdmPressedbtn);
			private static MyJLabel2 hou = new MyJLabel2(224, 90, 150, 27,
					StaticImage.backOfhoubtn, StaticImage.backOfhouPressedbtn);
			private static MyJLabel2 mg= new MyJLabel2(225, 122, 150, 27,
					StaticImage.backOfmgbtn, StaticImage.backOfmgPressedbtn);
			private static MyJLabel2 nop= new MyJLabel2(225, 156, 175, 27,
					StaticImage.backOfnopbtn, StaticImage.backOfnopPressedbtn);
			private static MyJLabel2 sas= new MyJLabel2(225, 190, 150, 27,
					StaticImage.backOfsasbtn, StaticImage.backOfsasPressedbtn);
			
			private static MyJLabel2 dn= new MyJLabel2(224, 268, 150, 27,
					StaticImage.backOfdnbtn, StaticImage.backOfdnPressedbtn);
			private static MyJLabel2 mt= new MyJLabel2(225, 302, 175, 27,
					StaticImage.backOfmtbtn, StaticImage.backOfmtPressedbtn);
			private static MyJLabel2 oct= new MyJLabel2(225, 336, 175, 27,
					StaticImage.backOfoctbtn, StaticImage.backOfoctPressedbtn);
			private static MyJLabel2 ptb= new MyJLabel2(225, 370, 175, 27,
					StaticImage.backOfptbbtn, StaticImage.backOfptbPressedbtn);
			private static MyJLabel2 jazz= new MyJLabel2(225, 404, 150, 27,
					StaticImage.backOfjazzbtn, StaticImage.backOfjazzPressedbtn);
			
			private static MyJLabel2 gsw= new MyJLabel2(224,494, 175, 27,
					StaticImage.backOfgswbtn, StaticImage.backOfgswPressedbtn);
			private static MyJLabel2 lac= new MyJLabel2(224, 528, 175, 27,
					StaticImage.backOflacbtn, StaticImage.backOflacPressedbtn);
			private static MyJLabel2 lal= new MyJLabel2(224, 562, 175, 27,
					StaticImage.backOflalbtn, StaticImage.backOflalPressedbtn);
			private static MyJLabel2 suns= new MyJLabel2(224, 596, 150, 27,
					StaticImage.backOfsunsbtn, StaticImage.backOfsunsPressedbtn);
			private static MyJLabel2 kings= new MyJLabel2(224, 630, 150, 27,
					StaticImage.backOfkingsbtn, StaticImage.backOfkingsPressedbtn);
			
			private static JLabel[] jLabelAsButtons;
			
			// 切换到球队界面按钮
			private JButton changeToPlayerPanel = new JButton();
			// 切换到比赛界面按钮
			private JButton changeToTeamPanel = new JButton();
			private JButton changeToStatPanel = new JButton();
			private JButton teams= new JButton();
		//	public static ArrayList<MatchBasicVO> arrMatchBasicVOs= new ArrayList<MatchBasicVO>(); 
			public Match(JPanel lastJPanel, Frame current) {
				setVisible(true);
				setLayout(null);
				this.currentFrame = current;
				this.lastJPanel = lastJPanel;
				this.currentJpanel = this;
				initialize();

			}
			class ButtonListener implements MouseListener{
				private int buttonID;
				Match currentPanel;

				public ButtonListener(int id, Match panel) {
					buttonID = id;
					currentPanel = panel;
				}

			

				public void mouseClicked(MouseEvent e) {
					teamName=ABB[buttonID];
					//调用方法～～
					//System.out.println(teamName);
				matchBasicVOs=	matchBLService.searchMatchByTime("", teamName);
				  paintTotalTable();
				}



				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}



				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}



				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}



				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			}
			private void initialize() {
				this.setSize(cfg.getWidth(), cfg.getHeight());
				 jLabelHot=new LeftHotPanel(currentJpanel, currentFrame);
				  matchBasicVOs=        matchBLService.searchMatchByTime("2015-06-04", "");
			  		this.add(jLabelHot.hotPanel,0);

				// 换界面的按钮
				MyButton button3 = new MyButton(StaticImage.backOfmatch_d,
						StaticImage.backOfmatch_l,StaticImage.backOfmatch_b, 930, 55, 76, 34);
				//this.add(button3.jbutton);
				// 换界面的按钮
				MyButton button0 = new MyButton(StaticImage.backOfqiuyuani_d,
						StaticImage.backOfqiuyuan_l,StaticImage.backOfqiuyuan_b, 1020, 55, 76, 34);
				this.add(button0.jbutton);
				changeToPlayerPanel = button0.jbutton;
		          changeToPlayerPanel.addActionListener(new button0ActionListener());

				MyButton button1 = new MyButton(StaticImage.backOfqiudui_d,
						StaticImage.backOfqiudui_l,StaticImage.backOfqiudui_b, 1110, 55, 76, 34);
				this.add(button1.jbutton);
				changeToTeamPanel = button1.jbutton;
				changeToTeamPanel.addActionListener(new button1ActionListener());
				MyButton button2 = new MyButton(StaticImage.backOftongji_d,
						StaticImage.backOftongji_l,StaticImage.backOftongji_b, 1200, 55, 76, 34);
				this.add(button2.jbutton);
				changeToStatPanel = button2.jbutton;
				changeToStatPanel.addActionListener(new button2ActionListener());
			
				buttonDate=new DateChooserJButton ();
				 this.add(buttonDate);
				 buttonDate.setBounds(311,104,150,19);
				 buttonDate.setVisible(true);
				  buttonDate.addMouseListener(new MouseAdapter() {
						
						public void mouseEntered(MouseEvent E){
							
						 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
						  String str=sdf.format(buttonDate.getDate());  
						  matchBasicVOs=        matchBLService.searchMatchByTime(str, "");
					         
          paintTotalTable();

					}
				});
				teamsJpanel=new JLabel(StaticImage.backOfteamsComBoxbtn);
				teamsJpanel.setLayout(null);
				teamsJpanel.setBounds(700, 98,410 , 685);
				jLabelAsButtons = new JLabel[] { boston.label,nets.label,nyk.label,ers.label,tr.label,
						bulls.label,cc.label,dp.label,ip.label,mb.label,
						ah.label,ch.label,heat.label,magic.label,ww.label,
						dm.label,hou.label,mg.label,nop.label,sas.label,
						dn.label,mt.label,oct.label,ptb.label,jazz.label,
						gsw.label,lac.label,lal.label,suns.label,kings.label};
				for (int i = 0; i < jLabelAsButtons.length; i++) {
					jLabelAsButtons[i].setVisible(true);
				jLabelAsButtons[i].addMouseListener(new ButtonListener(i, this));
					teamsJpanel.add(jLabelAsButtons[i]);
				}
				teamsJpanel.setVisible(false);
				this.add(teamsJpanel);
				teamsJpanel.addMouseListener(new MouseListener() {
					
				
					
			
					public void mouseExited(MouseEvent e) {
						  Dimension dim =teamsJpanel.getSize();

                          if( e.getX() < 0 || e.getY() < 0 ||

                                  e.getX() >= dim.getWidth() || e.getY() >= dim.getHeight() )
					  {

						teamsJpanel.setVisible(false);
					    }
					}
					
			
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				MyButton teamsButton=new MyButton(StaticImage.backOfteamsbtn,StaticImage.backOfteamsbtn,StaticImage.backOfteamsPressedbtn , 642, 102,58 , 30);
//				
				this.add(teamsButton.jbutton);
				teams=teamsButton.jbutton;
				teams.addMouseListener(new MouseListener() {
					
				
					
					public void mouseEntered(MouseEvent arg0) {
						teamsJpanel.setVisible(true);
						
					}

					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				
				});


				    paintTotalTable();
			}
			private void paintTotalTable(){
				/**
				 * 画下面的各种表格啊
				 */
				if(jspane!=null){
				this.remove(jspane);}
				jspane = new JScrollPane();
				jspane.setBounds(0, 137, 1276, 574);
				jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		        statTable=new MatchJTable(this, matchBasicVOs);
				statTable.setBounds(200, 200, 800, 600);
				
				statTable.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2) {
							if (statTable.getSelectedRow() == -1) {
								return;
							} else {
							String ID=	statTable.getValueAt(
									statTable.getSelectedRow(), 0)
									.toString();
							
									MatchBasicVO matchBasicVO=matchBLService.getMatchById(ID);
								nextJpanel=new MatchBasic(currentJpanel, matchBasicVO, currentFrame);

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
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);

				g.drawImage(StaticImage.backOfnewMatchBk.getImage(), 0, 0, null);
			}
			class button0ActionListener implements ActionListener {
				public void actionPerformed(ActionEvent arg0) {
					nextJpanel = new Player(Match.this, currentFrame);
					MyFrame.getFrame().changePanel(nextJpanel);
				}
			}

			class button1ActionListener implements ActionListener {
				public void actionPerformed(ActionEvent arg0) {
					nextJpanel = new Team(Match.this, currentFrame);
					MyFrame.getFrame().changePanel(nextJpanel);
				}
			}
			class button2ActionListener implements ActionListener {
				public void actionPerformed(ActionEvent arg0) {
				nextJpanel = new Statics(Match.this, currentFrame);
					MyFrame.getFrame().changePanel(nextJpanel);
				}
			}

}


class PicComboBoxRenderer
extends JLabel
implements ListCellRenderer {
public PicComboBoxRenderer() {
setOpaque(true);
setHorizontalAlignment(LEFT);
setVerticalAlignment(CENTER);
}

public Component getListCellRendererComponent(
  JList list,
  Object value,
  int index,
  boolean isSelected,
  boolean cellHasFocus) {
if (isSelected) {
  setBackground(list.getSelectionBackground());
  setForeground(list.getSelectionForeground());
}
else {
  setBackground(list.getBackground());
  setForeground(list.getForeground());
}

ImageIcon icon = (ImageIcon) value;
setText(icon.getDescription());
setIcon(icon);
return this;
}
}
