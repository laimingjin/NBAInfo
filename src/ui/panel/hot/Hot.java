package ui.panel.hot;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.panel.match.Match;
import ui.panel.player.Player;
import ui.panel.statics.Statics;
import ui.panel.team.Team;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.label.MyJLabel;
import ui.tool.picture.StaticImage;
import blService.HotBLService;
import blServiceImp.HotBLImp;
import config.ConfigFactory;
import config.GameConfig;

public class Hot extends JPanel{
	// 获取配置
		GameConfig cfg = ConfigFactory.getGameConfig();
		private static final long serialVersionUID = 1L;
		// 切换界面了按钮
		/**
		 * 热点部分
		 */
		private LeftHotPanel jLabelHot;
		// 切换到球队界面按钮
		private JButton changeToTeamPanel = new JButton();
		// 切换到比赛界面按钮
		private JButton changeToMatchPanel = new JButton();
		// 切换到比赛界面按钮
			private JButton changeToStatPanel = new JButton();
			private JButton changeToPlayerPanel = new JButton();		
	
		JPanel nextJpanel;
	 JPanel currentJpanel;
		 Frame currentFrame;
	JPanel lastJPanel;
	static String[] labelName = { "得分"
	,"篮板"
	,"助攻"
	,"盖帽"
     ,"抢断 "
	,"三分命中率"
,"投篮命中率"
		,"罚球命中率"};
// 常规赛
 static MyJLabel  scoring =new  MyJLabel(231, 98, 43, 28,StaticImage.backOfPTSbtn,StaticImage.backOfPTSPbtn);
 static MyJLabel rebound =new  MyJLabel(353, 98, 43, 28,StaticImage.backOfREBbtn,StaticImage.backOfREBPbtn);

 static MyJLabel  assist =new  MyJLabel(475, 98, 43, 28,StaticImage.backOfASTbtn,StaticImage.backOfASTPbtn);

 static MyJLabel  block =new  MyJLabel(597, 98, 43, 28,StaticImage.backOfBLKbtn,StaticImage.backOfBLKPbtn);
 static MyJLabel steal =new  MyJLabel(720, 98, 43, 28,StaticImage.backOfSTLbtn,StaticImage.backOfSTLPbtn);
static MyJLabel  percent_shoting =new  MyJLabel(840, 98,81, 28,StaticImage.backOfFGPbtn,StaticImage.backOfFGPPbtn);
static MyJLabel percent_three =new  MyJLabel(960, 98,81, 28,StaticImage.backOf3PPbtn,StaticImage.backOf3PPPbtn);
 static MyJLabel  percent_freethrow =new  MyJLabel(1080, 98, 81, 28,StaticImage.backOfFTPbtn,StaticImage.backOfFTPPbtn);
/**
 * 用于把这些按钮都加到界面上
 */
static JLabel[] jLabelAsButtons;
/**
 * 把那些按钮都加进去
 */

private void getLabelInit() {
	/*
	 * private static String
	 * []labelName={"球员姓名","球衣号码","所在位置","所在球队","前锋","中锋"
	 * ,"后卫","东部","西部","总数","平均"};
	 */
	jLabelAsButtons = new JLabel[] { scoring.label, rebound.label,
			assist.label, block.label, steal.label, percent_shoting.label,
			percent_three.label, percent_freethrow.label };
	for (int i = 0; i < jLabelAsButtons.length; i++) {
		jLabelAsButtons[i].setVisible(true);
		jLabelAsButtons[i].addMouseListener(new ButtonListener(i, this));
		this.add(jLabelAsButtons[i]);
	}
} // 鼠标监听		

class ButtonListener extends MouseAdapter {
	private int buttonID;
	  Hot currentPanel;

	public ButtonListener(int id, Hot panel) {
	}

}
public Hot(JPanel lastJPanel, Frame current) {
			setVisible(true);
			setLayout(null);
			this.currentFrame = current;
			this.lastJPanel = lastJPanel;
			this.currentJpanel = this;
			initialize();

		}
		private void initialize() {
			//getLabelInit();
			this.setSize(cfg.getWidth(), cfg.getHeight());
			 jLabelHot=new LeftHotPanel(currentJpanel, currentFrame);
		  		
		  		this.add(jLabelHot.hotPanel,0);
			// 换界面的按钮
			MyButton button3 = new MyButton(StaticImage.backOfqiuyuani_d,
					StaticImage.backOfqiuyuan_l,StaticImage.backOfqiuyuan_b, 930, 55, 76, 34);
			changeToPlayerPanel=button3.jbutton;
			this.add(button3.jbutton);
			changeToPlayerPanel.addActionListener(new button3ActionListener());
			
			
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
		}
		class button0ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				nextJpanel = new Team(Hot.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}

		class button1ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				nextJpanel = new Match(Hot.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}
		class button2ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Statics(Hot.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}
		class button3ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
		nextJpanel = new Player(Hot.this, currentFrame);
				MyFrame.getFrame().changePanel(nextJpanel);
			}
		}
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.drawImage(StaticImage.backOfHotBk.getImage(), 0, 0, null);
		}
}
