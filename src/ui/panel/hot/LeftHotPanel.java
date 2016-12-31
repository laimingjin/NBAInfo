package ui.panel.hot;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.panel.player.PlayerBasic;
import ui.panel.team.TeamBasic;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.picture.StaticImage;
import ui.tool.table.SmallPlayerTable;
import ui.tool.table.SmallTeamJTable;
import ui.tool.table.StatJTable;
import ui.tool.textfield.MyTextField;
import vo.PlayerBasicVO;
import vo.TeamBasicVO;
import blService.HotBLService;
import blServiceImp.HotBLImp;
import enumerate.TypeOfSort4HotPointPlayer;
import enumerate.TypeOfSort4MostImprovedPlayer;
import enumerate.TypeOfSort4Teams;

public class LeftHotPanel {
	JScrollPane jspane;
	StatJTable statTable;
	JScrollPane jspaneSeason;
	StatJTable statTableSeason;
	JScrollPane jspaneTeam;
	StatJTable statTableTeam;
	JScrollPane jspaneMost;
	StatJTable statTableMost;
	/**
	 * 都是用于热点的......
	 */
	/**
	 * 以下用于显示侧边栏的热点信息
	 */
	private MyButton dailyPlayer;
	private MyButton bestPlayer;
	private MyButton seasonPlayer;
	private MyButton seasonTeam;
	public  JPanel hotPanel;
	private Frame currentFrame;
	private JPanel nextJpanel;
	private JPanel lastJpanel;
	private JPanel currentJpanel;

	private JScrollPane JSP;
	
	private MyTextField inputSearchField;
	private static int flagZuo = 0;
	MyButton buttonZuochange;
	HotBLService hotBLService=MyFrame.getHotBl();
	/**
	 * 表头
	 */
	public static Vector<String> columnNames1 = new Vector<String>();
	public static ArrayList<PlayerBasicVO> arrplayerDaliltHotPoints = new ArrayList<PlayerBasicVO>();
	public static ArrayList<PlayerBasicVO> arrplayerSeasonHotPoints = new ArrayList<PlayerBasicVO>();
	public static ArrayList<PlayerBasicVO> arrplayerMost_Improveds = new ArrayList<PlayerBasicVO>();
	public static ArrayList<TeamBasicVO> arrteamHotPoints = new ArrayList<TeamBasicVO>();
	public LeftHotPanel(JPanel lastJ, Frame current){
		this.lastJpanel=lastJ;
		this.currentFrame=current;
		
		initf();
	}
	public void initf(){
		arrplayerDaliltHotPoints=hotBLService.getHotPointPlayerToday(TypeOfSort4HotPointPlayer.SCORING);
		arrplayerSeasonHotPoints=hotBLService.getHotPointPlayerSeason(TypeOfSort4HotPointPlayer.SCORING);
		arrplayerMost_Improveds=hotBLService.getMostImprovedPlayer(TypeOfSort4MostImprovedPlayer.AVERAGE_PSCORING);
		arrteamHotPoints=hotBLService.getHotPointTeamSeason(TypeOfSort4Teams.PTS);
		/**
		 * 左侧滑动栏目:热点
		 */
		hotPanel = new JPanel();
		hotPanel.setBackground(new Color(255, 255, 255));
		// jLabel.setBounds(1100,175,175,580);
		hotPanel.setBounds(-170, 0, 175, 760);
		hotPanel.setLayout(null);
        

		dailyPlayer =  new MyButton(StaticImage.backOfdailyButton_d,
				StaticImage.backOfdailyButton_l,StaticImage.backOfdailyButton_b, 1, 0, 165, 20);
		hotPanel.add(dailyPlayer.jbutton);
		dailyPlayer.jbutton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent E) {
				/**
				 * 
				 * 需要调用
				 * 
				 */
//				PlayerBasicVO playerBasicVO=new PlayerBasicVO("11", "Alonzo Gee", "3","hh", "ff", "ss", "21","g", "11", "33", "1", "1122", "2222");
//				
//				for(int i=0;i<5;i++){
//					arrplayerDaliltHotPoints.add(playerBasicVO);		
//				}
//				
				nextJpanel=new DailyPanel(lastJpanel, currentFrame, arrplayerDaliltHotPoints);
//				nextJpanel = new dailyPlayerPanel(lastJpanel, currentFrame,
//						"20130101", 1);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		});

		seasonPlayer = new MyButton(StaticImage.backOfseasonButton_d,
				StaticImage.backOfseasonButton_l,StaticImage.backOfseasonButton_b, 1, 191, 165, 20);
		hotPanel.add(seasonPlayer.jbutton);
		seasonPlayer.jbutton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent E) {
			
				nextJpanel=new SeasonPanel(lastJpanel, currentFrame, arrplayerSeasonHotPoints);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		});
		seasonTeam = new MyButton(StaticImage.backOfteamButton_d,
				StaticImage.backOfteamButton_l,StaticImage.backOfteamButton_b, 1, 381, 165, 20);
		hotPanel.add(seasonTeam.jbutton);
		seasonTeam.jbutton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent E) {
				
				nextJpanel=new TeamSeasonPanel(lastJpanel, currentFrame, arrteamHotPoints);
				//nextJpanel = new seasonTeamPanel(lastJpanel, currentFrame, 16);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		});
		bestPlayer =new MyButton(StaticImage.backOfmostButton_d,
				StaticImage.backOfmostButton_l,StaticImage.backOfmostButton_b, 1, 571, 165, 20);
		hotPanel.add(bestPlayer.jbutton);
		bestPlayer.jbutton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent E) {

				nextJpanel=new MostPanel(lastJpanel, currentFrame, arrplayerMost_Improveds);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		});


		paintDailyTable();
		paintSeansonPlayerTable();
		paintTeamTable();
		paintMostTable();

		buttonZuochange = new MyButton(StaticImage.backOfzuo,
				StaticImage.backOfzuoPressed,StaticImage.backOfzuo, 170, 0, 13, 760);

		hotPanel.add(buttonZuochange.jbutton);
		buttonZuochange.jbutton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent E) {
				if (flagZuo == 0) {
					for (int j = 0; j <= 170; j++) {
						hotPanel.setBounds(-170 + j * 1, 0, 175, 760);
						hotPanel.updateUI();

						flagZuo = 1;
					}
					buttonZuochange.jbutton.setIcon(StaticImage.backOfzuo);

				} else {
					for (int j = 0; j <= 170; j++) {
						hotPanel.setBounds(0 - j * 1, 0, 175, 760);
						hotPanel.updateUI();

						flagZuo = 0;
					}
					buttonZuochange.jbutton.setIcon(StaticImage.backOfyou);

				}
			}
		});

	}
	private void paintDailyTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspane!=null){
		hotPanel.remove(jspane);
		}
		jspane = new JScrollPane();
		jspane.setBounds(0, 20, 167, 170);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
        statTable=new SmallPlayerTable(hotPanel, arrplayerDaliltHotPoints);
		statTable.setBounds(0, 20, 164, 170);
		
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
		hotPanel.add(jspane);
		hotPanel.validate();
		hotPanel.repaint();
	}
	private void paintSeansonPlayerTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspaneSeason!=null){
		hotPanel.remove(jspaneSeason);
		}
		jspaneSeason = new JScrollPane();
		jspaneSeason.setBounds(0, 209, 167, 170);
		jspaneSeason.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	       
        statTableSeason=new SmallPlayerTable(hotPanel,arrplayerSeasonHotPoints);
		statTableSeason.setBounds(0, 209, 164, 1700);
		
		statTableSeason.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (statTableSeason.getSelectedRow() == -1) {
						return;
					} else {

						nextJpanel = new PlayerBasic(currentJpanel,
								statTableSeason.getValueAt(
										statTableSeason.getSelectedRow(), 0)
										.toString(), currentFrame);

						MyFrame.getFrame().changePanel(nextJpanel);
					}

				}

			}
		});
	    statTableSeason.refresh();
		jspaneSeason.setViewportView(statTableSeason);
		hotPanel.add(jspaneSeason);
		hotPanel.validate();
		hotPanel.repaint();
	}
	private void paintTeamTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspaneTeam!=null){
		hotPanel.remove(jspaneTeam);
		}
		jspaneTeam = new JScrollPane();
		jspaneTeam.setBounds(0, 399, 167, 170);
		jspaneTeam.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	       
        statTableTeam=new SmallTeamJTable(hotPanel, arrteamHotPoints);
		statTableTeam.setBounds(0, 399, 164, 170);
		
		statTableTeam.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (statTableTeam.getSelectedRow() == -1) {
						return;
					} else {

						nextJpanel = new TeamBasic(currentJpanel,
								statTableTeam.getValueAt(
										statTableTeam.getSelectedRow(), 1)
										.toString(), currentFrame);

						MyFrame.getFrame().changePanel(nextJpanel);
					}

				}

			}
		});
	    statTableTeam.refresh();
		jspaneTeam.setViewportView(statTableTeam);
		hotPanel.add(jspaneTeam);
		hotPanel.validate();
		hotPanel.repaint();
	}
	private void paintMostTable(){
		/**
		 * 画下面的各种表格啊
		 */
		if(jspaneMost!=null){
		hotPanel.remove(jspaneMost);
		}
		jspaneMost = new JScrollPane();
		jspaneMost.setBounds(0, 589, 167, 170);
		jspaneMost.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        statTableMost=new SmallPlayerTable(hotPanel, arrplayerMost_Improveds);
		statTableMost.setBounds(0, 589, 164, 170);
		
		statTableMost.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (statTableMost.getSelectedRow() == -1) {
						return;
					} else {

						nextJpanel = new PlayerBasic(currentJpanel,
								statTableMost.getValueAt(
										statTableMost.getSelectedRow(), 0)
										.toString(), currentFrame);

						MyFrame.getFrame().changePanel(nextJpanel);
					}

				}

			}
		});
	    statTableMost.refresh();
		jspaneMost.setViewportView(statTableMost);
		hotPanel.add(jspaneMost);
		hotPanel.validate();
		hotPanel.repaint();
	}

}
