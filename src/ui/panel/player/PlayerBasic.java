package ui.panel.player;

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
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tool.cal.ToolCal;
import ui.panel.statics.Statics;
import ui.panel.team.TeamBasic;
import ui.tool.button.JiaButton;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.label.MyJLabel;
import ui.tool.label.MyTextLabel;
import ui.tool.label.MyTextLabelShort;
import ui.tool.picture.StaticImage;
import ui.tool.table.PlayerHighJTable;
import ui.tool.table.PlayerInMatchJTable;
import ui.tool.table.PlayerJTable;
import ui.tool.table.StatJTable;
import ui.tool.textfield.BlueTextField;
import ui.tool.textfield.NumberTextField;
import ui.tool.textfield.PlayerNameTextField;
import ui.tool.textfield.SmallTextField;
import vo.ContrastBoardVO;
import vo.PlayerBasicVO;
import vo.PlayerHighVO;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import blService.PlayerBLService;
import blServiceImp.PlayerBLImp;
import config.ConfigFactory;
import config.GameConfig;
import dataServiceImp.DatabaseException;
import enumerate.TypeOfMatch;

public class PlayerBasic extends JPanel {
	// 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();
	/**
	 * 放四个表格
	 */
	JScrollPane jspane_regualr;
	StatJTable statTable_regualr;
	JScrollPane jspane_allstar;
	StatJTable statTable_allstar;
	JScrollPane jspane_playoff;
	StatJTable statTable_playoff;
	JScrollPane jspane_recent;
	StatJTable statTable_recent;
	JPanel  tableJPanel;
	JScrollPane all;
	JLabel regularlJLabel;
	JLabel playoffJLabel;
	JLabel allStarJLabel;
	JLabel recentJLabel;
     /*
      *  用于判断要拉长还是缩短,0处于缩短状态，1表示处于拉长状态
      */
	private static int regularJia=0;
	private static int playoffJia=0;
	private static int allStarJia=0;
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
 * 四个下拉按钮
 */
	private JButton regularButton = new JButton();// 存放按钮
	private JButton allStarButton = new JButton();// 存放按钮
	private JButton playoffButton = new JButton();// 存放按钮
	private JButton recentButton= new JButton();// 存放按钮
	JiaButton button0 ;
	JiaButton button1 ;
	JiaButton button2 ;
	JiaButton button3 ;
	private Frame currentFrame;
	private JPanel lastJPanel;
	private JPanel nextJpanel;
	/**
	 * 界面传来的球员ID
	 */
	private static String playerID;
	PlayerBasicVO playerVO_basic;
	private JPanel currentJpanel;
	PlayerBLService playerBLService;
	/**
	 * 比分板块
	 */
	private ContrastBoardVO contrase;
	
	
	/**
	 * 常规赛的数据list
	 */
	ArrayList<PlayerTotalVO> playerVO_scores_regular = new ArrayList<PlayerTotalVO>();
	/**
	 * 季后赛的数据list
	 */
	ArrayList<PlayerTotalVO> playerVO_scores_playoff = new ArrayList<PlayerTotalVO>();
	/**
	 * 常规赛的数据list
	 */
	ArrayList<PlayerHighVO> playerVO_high_regular = new ArrayList<PlayerHighVO>();
	/**
	 * 季后赛的数据list
	 */
	ArrayList<PlayerHighVO> playerVO_high_playoff = new ArrayList<PlayerHighVO>();
	/**
	 * 全明星的数据list
	 */
	ArrayList<PlayerTotalVO> playerVO_scores_allstar = new ArrayList<PlayerTotalVO>();
	/**
	 * 最近五场比赛
	 */
	ArrayList<PlayerInMatchVO> playerVO_inMatchs = new ArrayList<PlayerInMatchVO>();

	public PlayerBasic(JPanel lastJPanel, String ID, Frame current) {
		setVisible(true);
		setLayout(null);
		this.playerID = ID;
		this.lastJPanel = lastJPanel;

		this.currentFrame = current;

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
		/**
		 * 通过名字得到所有想要的
		 */
	     playerVO_basic=playerBLService.getPlayerDetail(playerID);
	     playerVO_high_playoff=playerBLService.getPlayoffByNameHigh(playerID);
	     playerVO_high_regular=playerBLService.getRegularByNameHigh(playerID);
	     playerVO_inMatchs=playerBLService.getRecentMatchesforPlayer(playerID);
	     playerVO_scores_allstar=playerBLService.getAllStarByName(playerID);
	     playerVO_scores_playoff=playerBLService.getPlayoffByName(playerID);
	     playerVO_scores_regular=playerBLService.getRegularByName(playerID);
	     
		this.setSize(cfg.getWidth(), cfg.getHeight());
		
		 MyButton returnButton = new MyButton(StaticImage.backOfback,StaticImage.backOfback,
		 StaticImage.backOfbackEntered, 9,9, 117, 31);
		 this.add(returnButton.jbutton);
		 returnJButton = returnButton.jbutton;
		 returnJButton.addActionListener(new returnButtonActionListener());
	
			
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
		StaticImage.backOfminusbtn,1229,2, 42, 37);
			//this.add(button2.jbutton);
			allStarButton = button2.jbutton;
			allStarButton.addActionListener(new button2ActionListener());
			 button3 = new JiaButton(StaticImage.backOfdetailbtn,StaticImage.backOfminusbtn, 1229,2, 42, 37);
		//	this.add(button3.jbutton);
			recentButton = button3.jbutton;
			recentButton.addActionListener(new button3ActionListener());
			 contrase=playerBLService.getContrastData(playerVO_basic.getPlayerID(),TypeOfMatch.REGULAR,"2014-15");
			 if(contrase!=null){
				 this.add(new MyTextLabel(905, 138,ToolCal.change_to_smallLength(contrase.getFirst_score())).label);
					this.add(new MyTextLabel(1045, 138,ToolCal.change_to_smallLength(contrase.getFirst_rebound())).label);
					this.add(new MyTextLabel(1176, 138,ToolCal.change_to_smallLength(contrase.getFirst_assist())).label);
					 this.add(new MyTextLabel(905, 164,ToolCal.change_to_smallLength(contrase.getAlliance_score())).label);
						this.add(new MyTextLabel(1045, 164,ToolCal.change_to_smallLength(contrase.getAlliance_rebound())).label);
						this.add(new MyTextLabel(1176, 164,ToolCal.change_to_smallLength(contrase.getAlliance_assist())).label);
						 this.add(new MyTextLabel(905, 190,ToolCal.change_to_smallLength(contrase.getPlayer_score())).label);
							this.add(new MyTextLabel(1045, 190,ToolCal.change_to_smallLength(contrase.getPlayer_rebound())).label);
							this.add(new MyTextLabel(1176, 190,ToolCal.change_to_smallLength(contrase.getPlayer_assist())).label);
			 }
			/**
		 * 显示头像
		 */
			tableJPanel=new JPanel();
			tableJPanel.setBounds(0,0,1270,408);
			
			tableJPanel.setLayout(new GridBagLayout());
			
		String portraitAddress = "portrait";

		ImageIcon portait = new ImageIcon(portraitAddress + "/" + playerVO_basic.getPlayerName()
				+ ".png");
		MyJLabel picture = new MyJLabel(12, 53, 230, 185, portait, portait);

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
            		statTable_regualr = new PlayerJTable( PlayerBasic.this,playerVO_scores_regular);
            		statTable_regualr.setBounds(200, 200, 800, 600);
            		statTable_regualr.refresh();
            		jspane_regualr.setViewportView(statTable_regualr);
                    jspane_regualr.validate();
                    jspane_regualr.repaint();
               	 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 1, 0, 1, 0, 0,
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
         		statTable_regualr = new PlayerHighJTable( PlayerBasic.this,playerVO_high_regular);
         		statTable_regualr.setBounds(200, 200, 800, 600);
         		statTable_regualr.refresh();
         		jspane_regualr.setViewportView(statTable_regualr);
         		  jspane_regualr.validate();
                  jspane_regualr.repaint();
             	 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 1, 0, 1, 0, 0,
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
        		statTable_playoff = new PlayerJTable(PlayerBasic. this,playerVO_scores_playoff);
        		statTable_playoff.setBounds(200, 200, 800, 600);
        		statTable_playoff.refresh();
        		jspane_playoff.setViewportView(statTable_playoff);
        		jspane_playoff.validate();
        		jspane_playoff.repaint();
        		tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 3, 0, 1, 0, 0,
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
        		statTable_playoff = new PlayerHighJTable(PlayerBasic. this,playerVO_high_playoff);
        		statTable_playoff.setBounds(200, 200, 800, 600);
        		statTable_playoff.refresh();
        		jspane_playoff.setViewportView(statTable_playoff);
        		jspane_playoff.validate();
        		jspane_playoff.repaint();
        		tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 3, 0, 1, 0, 0,
        				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

			}
   });
		/**
		 * 画下面的各种表格啊
		 */
		all=new JScrollPane();
		all.setBounds(0,312,1276,408);
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
		allStarJLabel=new JLabel(StaticImage.backOfALLSTARSEASONbtn);
		allStarJLabel.setLayout(null);
		allStarJLabel.add(allStarButton);
		recentJLabel=new JLabel(StaticImage.backOfRECENTbtn);
		recentJLabel.setLayout(null);
		recentJLabel.add(recentButton);
/**
 * 初始化所有的jsapne
 */
	
	 	jspane_regualr = new JScrollPane();
		statTable_regualr = new PlayerJTable( PlayerBasic.this,playerVO_scores_regular);
		statTable_regualr.setBounds(200, 200, 800, 600);
		statTable_regualr.refresh();
		jspane_regualr.setViewportView(statTable_regualr);
		
		jspane_playoff = new JScrollPane();
		statTable_playoff = new PlayerJTable( this,playerVO_scores_playoff);
		statTable_playoff.setBounds(200, 200, 800, 600);
		statTable_playoff.refresh();
		jspane_playoff.setViewportView(statTable_playoff);
	
		jspane_allstar = new JScrollPane();
		statTable_allstar = new PlayerJTable( this,playerVO_scores_allstar);
		statTable_allstar.setBounds(200, 200, 800, 600);
		statTable_allstar.refresh();
		jspane_allstar.setViewportView(statTable_allstar);
		
		jspane_recent = new JScrollPane();
		statTable_recent = new PlayerInMatchJTable( this,playerVO_inMatchs);
		statTable_recent.setBounds(200, 200, 800, 600);
		statTable_recent.refresh();
		jspane_recent.setViewportView(statTable_recent);
		
		NumberTextField number =new NumberTextField(419, 78,playerVO_basic.getNumberSquad() );
		number.jtextfield.setBounds(443, 75,64, 60);
		this.add(number.jtextfield);
		String names[]=playerVO_basic.getPlayerName().split(" ");
		System.out.println(playerVO_basic.getPlayerName());
		String firstName=names[0];
		String lastName="";
		if(names.length>=2){
			for(int i=1;i<names.length;i++){
				lastName=lastName+names[i];
			}
		}
		PlayerNameTextField playerFirstName =new PlayerNameTextField(512, 63,firstName);
		//playerFirstName.jtextfield.setBounds(512, 65,200, 25);
		this.add(playerFirstName.jtextfield);
		PlayerNameTextField playerLastName =new PlayerNameTextField(512,98,lastName);
	//	playerLastName.jtextfield.setBounds(512, 100,200, 25);
		this.add(playerLastName.jtextfield);
		SmallTextField postion=new SmallTextField(416, 136, playerVO_basic.getPosition());
	   this.add(postion.jtextfield);
	   MyTextLabelShort team=new MyTextLabelShort(566, 136, playerVO_basic.getTeamName());
	   this.add(team.label);
	   team.label.addMouseListener(new MouseAdapter() {
	 
		
		public void mouseClicked(MouseEvent e) {
			nextJpanel = new TeamBasic(PlayerBasic.this,playerVO_basic.getTeamName(), currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
			
		}
	});
	   
	   SmallTextField weight=new SmallTextField(499, 158, playerVO_basic.getWeight());
	   this.add(weight.jtextfield);
	   SmallTextField height=new SmallTextField(549, 158, playerVO_basic.getHeight());
	   this.add(height.jtextfield);
	   SmallTextField born=new SmallTextField(684, 177, playerVO_basic.getCountry());
	   this.add(born.jtextfield);
	   SmallTextField exp=new SmallTextField(684, 197, playerVO_basic.getFromYear()+"-"+playerVO_basic.getToYear());
	   this.add(exp.jtextfield); 
	   SmallTextField school=new SmallTextField(699 , 214, playerVO_basic.getSchool());
	   this.add(school.jtextfield);
	   /**
	    * 这里的数字要改火
	    * 不知道是从大到小还是从小到大
	    */
	   PlayerNameTextField pts =new PlayerNameTextField(365, 200,playerVO_scores_regular.get(0).getPTS()+"");
		this.add(pts.jtextfield);
		PlayerNameTextField reb =new PlayerNameTextField(470, 200,playerVO_scores_regular.get(0).getREB()+"");
		this.add(reb.jtextfield);
		PlayerNameTextField ast =new PlayerNameTextField(563, 200,playerVO_scores_regular.get(0).getAST()+"");
		this.add(ast.jtextfield);
		
		
		
		
		tableJPanel.add(regularlJLabel, new GridBagConstraints(0, 0, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
	
		 jspane_regualr.setSize(1270, 63);
		 jspane_regualr.setPreferredSize(new Dimension(jspane_regualr.getWidth(),jspane_regualr.getHeight()));
		 jspane_regualr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    
		 tableJPanel.add(jspane_regualr, new GridBagConstraints(0, 1, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jspane_playoff.setSize(1270, 63);
		jspane_playoff.setPreferredSize(new Dimension(jspane_playoff.getWidth(),jspane_playoff.getHeight()));
		jspane_playoff.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
		tableJPanel.add(playoffJLabel, new GridBagConstraints(0, 2, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
	
		tableJPanel.add(jspane_playoff, new GridBagConstraints(0, 3, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));

		jspane_allstar.setSize(1270, 63);
		jspane_allstar.setPreferredSize(new Dimension(jspane_allstar.getWidth(),jspane_allstar.getHeight()));
		jspane_allstar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
		tableJPanel.add(allStarJLabel, new GridBagConstraints(0, 4, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		tableJPanel.add(jspane_allstar, new GridBagConstraints(0, 5, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		
		tableJPanel.add(recentJLabel, new GridBagConstraints(0, 6, 0, 1, 0, 0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jspane_recent.setSize(1270, 63);
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
		            jspane_regualr.setSize(new Dimension(1276,38+ playerVO_scores_regular.size()*25));
					 jspane_regualr.setPreferredSize(new Dimension(1276,38+ playerVO_scores_regular.size()*25));
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
		            jspane_playoff.setSize(new Dimension(1276,38+ playerVO_scores_playoff.size()*25));
		            jspane_playoff.setPreferredSize(new Dimension(1276,38+ playerVO_scores_playoff.size()*25));
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
			if(allStarJia==0){
				allStarJia=1;
		            jspane_allstar.setSize(new Dimension(1276,38+ playerVO_scores_allstar.size()*25));
		            jspane_allstar.setPreferredSize(new Dimension(1276,38+ playerVO_scores_allstar.size()*25));
		            jspane_allstar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 all.repaint();
					 all.validate();
					 validate();
					 repaint();
					
		        }else{
		         button2.turnBack();
		         allStarJia=0;
		         jspane_allstar.setSize(new Dimension(1276,63));
		         jspane_allstar.setPreferredSize(new Dimension(1276,63));
		         jspane_allstar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		            jspane_recent.setSize(new Dimension(1276,38+ playerVO_inMatchs.size()*25));
		            jspane_recent.setPreferredSize(new Dimension(1276,38+ playerVO_inMatchs.size()*25));
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

		g.drawImage(StaticImage.backOfpalyerBasic.getImage(), 0, 0, null);
	}

}
