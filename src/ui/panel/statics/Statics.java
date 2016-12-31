package ui.panel.statics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.panel.hot.LeftHotPanel;
import ui.panel.match.Match;
import ui.panel.player.Player;
import ui.panel.statics.TuiStatics.returnButtonActionListener;
import ui.panel.team.Team;
import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.jcombox.ColorArrowUI;
import ui.tool.label.MyJLabel;
import ui.tool.label.MyStarLabel;
import ui.tool.picture.StaticImage;
import wordLive.MyLiveTable;
import config.ConfigFactory;
import config.GameConfig;

public class Statics extends JPanel{
    // 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();
	/**
	 * 做选择赛季的下拉框
	 */
	SelectJComboBox<String> seasonsBox;
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
	private static MyJLabel picture ;
	private static boolean oneOrTwo=true ;
	// 切换到比赛界面按钮
	private JButton changeToMatchPanel = new JButton();
	private JButton changeToTeamPanel = new JButton();
	private static JLabel namesJpanel;
	private static String[] labelName = { "Al Horford", "Alec Burks",
		"Anderson Varejao", "Andrea Bargnani", "Andrew Bogut",
		"Andrew Bynum", "Arron Afflalo", "Brandon Jennings", "Brandon Roy",
		"Brook Lopez", "Carmelo Anthony", "Chauncey Billups", "Chris Bosh",
		"Danny Granger", "Derrick Rose", "Elton Brand", "Eric Maynor", "Gilbert Arenas",
		"JaVale McGee", "Jermaine O'Neal", "Josh McRoberts", "Kevin Durant",
		"Kevin LoveP", "Kobe Bryant", "Martell Webster", "Nate Robinson",
		"Nick Young", "Rajon Rondo", "Ricky Rubio", "Speedy Claxton", "Steve Nash",
		"Tracy McGrady", "Wesley Matthews", "Xavier Henry" };
	MyStarLabel AlecBurk=new MyStarLabel( 84, 25,StaticImage.backOfAlecBurksbtn, StaticImage.backOfAlecBurksPbtn);
	MyStarLabel AlHorford=new MyStarLabel( 84, 25,StaticImage.backOfAlHorfordbtn, StaticImage.backOfAlHorfordPbtn);
	MyStarLabel AndersonVarejao=new MyStarLabel( 84, 25,StaticImage.backOfAndersonVarejaobtn, StaticImage.backOfAndersonVarejaoPbtn);
	MyStarLabel AndreaBargnani=new MyStarLabel( 84, 25,StaticImage.backOfAndreaBargnanibtn, StaticImage.backOfAndreaBargnaniPbtn);
	MyStarLabel AndrewBogu=new MyStarLabel( 84, 25,StaticImage.backOfAndrewBogutbtn, StaticImage.backOfAndrewBogutPbtn);
	MyStarLabel AndrewBynum=new MyStarLabel( 84, 25,StaticImage.backOfAndrewBynumbtn, StaticImage.backOfAndrewBynumPbtn);
	MyStarLabel ArronAfflalo=new MyStarLabel( 84, 25,StaticImage.backOfArronAfflalobtn, StaticImage.backOfArronAfflaloPbtn);
	MyStarLabel BrandonJennings=new MyStarLabel( 84, 25,StaticImage.backOfBrandonJenningsbtn, StaticImage.backOfBrandonJenningsPbtn);
	MyStarLabel BrandonRoy=new MyStarLabel( 84, 25,StaticImage.backOfBrandonRoybtn, StaticImage.backOfBrandonRoyPbtn);
	MyStarLabel BrookLopez=new MyStarLabel( 84, 25,StaticImage.backOfBrookLopezbtn, StaticImage.backOfBrookLopezPbtn);
	MyStarLabel CarmeloAnthony=new MyStarLabel( 84, 25,StaticImage.backOfCarmeloAnthonybtn, StaticImage.backOfCarmeloAnthonyPbtn);
	MyStarLabel ChaunceyBillups=new MyStarLabel( 84, 25,StaticImage.backOfChaunceyBillupsbtn, StaticImage.backOfChaunceyBillupsPbtn);
	MyStarLabel ChrisBosh=new MyStarLabel( 84, 25,StaticImage.backOfChrisBoshbtn, StaticImage.backOfChrisBoshPbtn);
	MyStarLabel DannyGranger=new MyStarLabel( 84, 25,StaticImage.backOfDannyGrangerbtn, StaticImage.backOfDannyGrangerPbtn);
	MyStarLabel DerrickRose=new MyStarLabel( 84, 25,StaticImage.backOfDerrickRosebtn, StaticImage.backOfDerrickRosePbtn);
	MyStarLabel EltonBrand=new MyStarLabel( 84, 25,StaticImage.backOfEltonBrandbtn, StaticImage.backOfEltonBrandPbtn);
	MyStarLabel EricMaynor=new MyStarLabel( 84, 25,StaticImage.backOfEricMaynorbtn, StaticImage.backOfEricMaynorPbtn);
	MyStarLabel GilbertArenas=new MyStarLabel( 84, 25,StaticImage.backOfGilbertArenasbtn, StaticImage.backOfGilbertArenasPbtn);
	MyStarLabel JaValeMcGee=new MyStarLabel( 84, 25,StaticImage.backOfJaValeMcGeebtn, StaticImage.backOfJaValeMcGeePbtn);
	MyStarLabel JermaineONeal=new MyStarLabel( 84, 25,StaticImage.backOfJermaineONealbtn, StaticImage.backOfJermaineONealPbtn);
	MyStarLabel JoshMcRoberts=new MyStarLabel( 84, 25,StaticImage.backOfJoshMcRobertsbtn, StaticImage.backOfJoshMcRobertsPbtn);
	MyStarLabel KevinDurant=new MyStarLabel( 84, 25,StaticImage.backOfKevinDurantbtn, StaticImage.backOfKevinDurantPbtn);
	MyStarLabel KevinLove=new MyStarLabel( 84, 25,StaticImage.backOfKevinLovebtn, StaticImage.backOfKevinLovePbtn);
	MyStarLabel KobeBryant=new MyStarLabel( 84, 25,StaticImage.backOfKobeBryantbtn, StaticImage.backOfKobeBryantPbtn);
	MyStarLabel MartellWebster=new MyStarLabel( 84, 25,StaticImage.backOfMartellWebsterbtn, StaticImage.backOfMartellWebsterPbtn);
	MyStarLabel NateRobinson=new MyStarLabel( 84, 25,StaticImage.backOfNateRobinsonbtn, StaticImage.backOfNateRobinsonPbtn);
	MyStarLabel NickYoung=new MyStarLabel( 84, 25,StaticImage.backOfNickYoungbtn, StaticImage.backOfNickYoungPbtn);
	MyStarLabel RajonRondo=new MyStarLabel( 84, 25,StaticImage.backOfRajonRondobtn, StaticImage.backOfRajonRondoPbtn);
	MyStarLabel RickyRubio=new MyStarLabel( 84, 25,StaticImage.backOfRickyRubiobtn, StaticImage.backOfRickyRubioPbtn);
	MyStarLabel SpeedyClaxton=new MyStarLabel( 84, 25,StaticImage.backOfSpeedyClaxtonbtn, StaticImage.backOfSpeedyClaxtonPbtn);
	MyStarLabel SteveNash=new MyStarLabel( 84, 25,StaticImage.backOfSteveNashbtn, StaticImage.backOfSteveNashPbtn);
	MyStarLabel TracyMcGrady=new MyStarLabel( 84, 25,StaticImage.backOfTracyMcGradybtn, StaticImage.backOfTracyMcGradyPbtn);
	MyStarLabel WesleyMatthews=new MyStarLabel( 84, 25,StaticImage.backOfWesleyMatthewsbtn, StaticImage.backOfWesleyMatthewsPbtn);
	MyStarLabel XavierHenry=new MyStarLabel( 84, 25,StaticImage.backOfXavierHenrybtn, StaticImage.backOfXavierHenryPbtn);
	/**
	 * 小圆圈换三个图
	 */
	MyJLabel myJLabel1=new MyJLabel(550, 707, 9, 9, StaticImage.backOfsmallballbtn, StaticImage.backOfsmallballPbtn);
	MyJLabel myJLabel2=new MyJLabel(565, 707, 9, 9, StaticImage.backOfsmallballbtn, StaticImage.backOfsmallballPbtn);
	MyJLabel myJLabel3=new MyJLabel(580, 707, 9, 9, StaticImage.backOfsmallballbtn, StaticImage.backOfsmallballPbtn);
	private static  JLabel oneJLabel;
	/**
	 * 用于把这些按钮都加到界面上
	 */
	public static String fileNmaeString="Martell Webster_2008-09_";
	public static String NmaeString="Martell Webster_";
	private static 	ArrayList<MyStarLabel> myJLabels=new ArrayList<MyStarLabel>();
	private static JLabel[] jLabelsmall;
	private static String[] labelName2 = { "1","2","3" };
	private void getLabelInit2() {
		/*
		 * private static String
		 * []labelName={"球员姓名","球衣号码","所在位置","所在球队","前锋","中锋"
		 * ,"后卫","东部","西部","总数","平均"};
		 */
		jLabelsmall = new JLabel[] { myJLabel1.label,myJLabel2.label,myJLabel3.label};
		for (int i = 0; i < jLabelsmall.length; i++) {
			jLabelsmall[i].setVisible(true);
			jLabelsmall[i].addMouseListener(new ButtonListener2(i, this));
			this.add(jLabelsmall[i]);
		}
	} // 鼠标监听
   
	class ButtonListener2 extends MouseAdapter {
		private int buttonID;
		Statics currentPanel;

		public ButtonListener2(int id, Statics panel) {
			buttonID = id;
			currentPanel = panel;
		}

		public void mouseClicked(MouseEvent e) {
		if(labelName2[buttonID].equals("1")){
			myJLabel2.turnBack();
			myJLabel3.turnBack();
		
			ImageIcon imageIcon=new  ImageIcon("stats/"+fileNmaeString+"playersBar.png") ;
          
			if(picture!=null){
	
				Statics.this.remove(picture.label);
				}
			
		picture = new MyJLabel(122, 188,  700, 500, imageIcon, imageIcon);
				 
		Statics.this.add(picture.label);
		Statics.this.repaint();
		if(oneJLabel!=null){
			Statics.this.remove(oneJLabel);
		}
         oneJLabel=new JLabel(StaticImage.backOfoneTextForTongji);
        oneJLabel.setBounds(780	, 348, 424, 162);
        Statics.this.add(oneJLabel);
        
		}else if(labelName2[buttonID].equals("2")){
			myJLabel1.turnBack();
			myJLabel3.turnBack();
			ImageIcon imageIcon=new  ImageIcon("stats/"+NmaeString+"playerPlot.png") ;
          
			if(picture!=null){
	
				Statics.this.remove(picture.label);
				}
			
		picture = new MyJLabel(122, 188,  700, 500, imageIcon, imageIcon);
				 
		Statics.this.add(picture.label);
		Statics.this.repaint();
		if(oneJLabel!=null){
			Statics.this.remove(oneJLabel);
		}
         oneJLabel=new JLabel(StaticImage.backOfoneTextForTongj2);
       
        oneJLabel.setBounds(780	, 348, 346, 107);
        Statics.this.add(oneJLabel);
        
        
		}else{
			myJLabel1.turnBack();
			myJLabel2.turnBack();
			ImageIcon imageIcon=new  ImageIcon("stats/"+fileNmaeString+"playersTeam.png") ;
          
			if(picture!=null){
	
				Statics.this.remove(picture.label);
				}
			
		picture = new MyJLabel(122, 188, 700, 500, imageIcon, imageIcon);
				 
		Statics.this.add(picture.label);
		Statics.this.repaint();
		if(oneJLabel!=null){
			Statics.this.remove(oneJLabel);
		}
         oneJLabel=new JLabel(StaticImage.backOfoneTextForTongj3);  
         oneJLabel.setLayout(null);
         TextField textField=new TextField(Statics.this.getTimeLeave(fileNmaeString));
         textField.setBounds(125, 96, 120, 20);
         oneJLabel.add(textField);
        oneJLabel.setBounds(780	, 348, 387, 130);
        Statics.this.add(oneJLabel);
		}
			
			 
		}
	}
	public String  getTimeLeave(String name) {
		if(name.equals("Kevin Durant_2014-15_")){
			return "2015-02-19";
		}else if(name.equals("Kobe Bryant_2014-15_")){
			return "2015-01-21";
		}else if(name.equals("Steve Nash_2014-15_")){
			return "2014-10-12";
		}else if(name.equals("Xavier Henry_2014-15_")){
			return "2014-11-21";
		}else if(name.equals("Paul George_2014-15_")){
			return "2015-04-15";
		}else if(name.equals("Carmelo Anthony_2014-15_")){
			return "2015-02-09";
		}else if(name.equals("Wesley Matthews_2014-15_")){
			return "2015-03-05";
		}else if(name.equals("Josh McRoberts_2014-15_")){
			return "2014-12-09";
		}else if(name.equals("Chris Bosh_2014-15_")){
			return "2015-02-11";
		}else if(name.equals("Brandon Jennings_2014-15_")){
			return "2015-01-24";
		}else if(name.equals("Alec Burks_2014-15_")){
			return "2014-12-22";
		}else if(name.equals("Anderson Varejao_2014-15_")){
			return "2014-12-23";
		}else if(name.equals("Kobe Bryant_2013-14_")){
			return "2013-12-17";
		}else if(name.equals("Derrick Rose_2013-14_")){
			return "2013-11-22";
		}else if(name.equals("Nate Robinson_2013-14_")){
			return "2014-01-29";
		}else if(name.equals("JaVale McGee_2013-14_")){
			return "2013-11-08";
		}else if(name.equals("Al Horford_2013-14_")){
			return "2013-12-26";
		}else if(name.equals("Steve Nash_2013-14_")){
			return "2014-04-08";
		}else if(name.equals("Nick Young_2013-14_")){
			return "2014-04-16";
		}else if(name.equals("Brook Lopez_2013-14_")){
			return "2013-12-20";
		}else if(name.equals("Kobe Bryant_2012-13_")){
			return "2013-04-12";
		}else if(name.equals("Rajon Rondo_2012-13_")){
			return "2013-01-25";
		}else if(name.equals("Anderson Varejao_2012-13_")){
			return "2012-12-18";
		}else if(name.equals("Kevin Love_2012-13_")){
			return "2013-01-03";
		}else if(name.equals("Danny Granger_2012-13_")){
			return "2013-03-03";
		}else if(name.equals("Andrea Bargnani_2012-13_")){
			return "2013-03-08";
		}else if(name.equals("Brandon Roy_2012-13_")){
			return "2012-11-09";
		}else if(name.equals("Arron Afflalo_2012-13_")){
			return "2013-03-22";
		}else if(name.equals("Eric Maynor_2011-12_")){
			return "2012-01-07";
		}else if(name.equals("Al Horford_2011-12_")){
			return "2012-01-11";
		}else if(name.equals("Chauncey Billups_2011-12_")){
			return "2012-02-06";
		}else if(name.equals("Ricky Rubio_2011-12_")){
			return "2012-03-09";
		}else if(name.equals("Jermaine O'Neal_2011-12_")){
			return "2012-02-20";
		}else if(name.equals("Tracy McGrady_2008-09_")){
			return "2009-02-09";
		}else if(name.equals("Gilbert Arenas_2008-09_")){
			return "2009-04-02";
		}else if(name.equals("Elton Brand_2008-09_")){
			return "2009-02-03";
		}else if(name.equals("Speedy Claxton_2008-09_")){
			return "2009-04-19";
		}else if(name.equals("Martell Webster_2008-09_")){
			return "2008-12-07";
		}else if(name.equals("Andrew Bogut_2008-09_")){
			return "2009-01-31";
		}else if(name.equals("Andrew Bynum_2008-09_")){
			return "2009-04-27";
		}
		
		return name;
		
	}

	/**
	 * 看是哪个赛季
	 */
	private static String season;
	public Statics(JPanel lastJPanel, Frame current) {
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
	/**
	 * 把那些按钮都加进去
	 */
	private void getLabelInit() {
		/*
		 * private static String
		 * []labelName={"球员姓名","球衣号码","所在位置","所在球队","前锋","中锋"
		 * ,"后卫","东部","西部","总数","平均"};
		 */
	
	
		myJLabels.add(AlHorford);myJLabels.add(AlecBurk);myJLabels.add(AndersonVarejao);myJLabels.add(AndreaBargnani);
		myJLabels.add(AndrewBogu);myJLabels.add(AndrewBynum);myJLabels.add(ArronAfflalo);myJLabels.add(BrandonJennings);
		myJLabels.add(BrandonRoy);myJLabels.add(BrookLopez);myJLabels.add(CarmeloAnthony);myJLabels.add(ChaunceyBillups);
		myJLabels.add(ChrisBosh);myJLabels.add(DannyGranger);myJLabels.add(DerrickRose);myJLabels.add(EltonBrand);
		myJLabels.add(EricMaynor);myJLabels.add(GilbertArenas);myJLabels.add(JaValeMcGee);myJLabels.add(JermaineONeal);
		myJLabels.add(JoshMcRoberts);myJLabels.add(KevinDurant);myJLabels.add(KevinLove);myJLabels.add(KobeBryant);
		
		myJLabels.add(MartellWebster);myJLabels.add(NateRobinson);myJLabels.add(NickYoung);myJLabels.add(RajonRondo);
		myJLabels.add(RickyRubio);myJLabels.add(SpeedyClaxton);myJLabels.add(SteveNash);myJLabels.add(TracyMcGrady);
		myJLabels.add(WesleyMatthews);myJLabels.add(XavierHenry);
		for (int i = 0; i < myJLabels.size(); i++) {
			myJLabels.get(i).label.setVisible(true);
			myJLabels.get(i).label.addMouseListener(new ButtonListener(i, this));
			//this.add(jLabelAsButtons[i]);
		}
	} // 鼠标监听

	class ButtonListener extends MouseAdapter {
		private int buttonID;
		Statics currentPanel;

		public ButtonListener(int id, Statics panel) {
			buttonID = id;
			currentPanel = panel;
		}

		public void mouseClicked(MouseEvent e) {
			String seanson=seasonsBox.getSelectedItem().toString();
			 String filePath = labelName[buttonID]+"_"+seanson+"_";
			 NmaeString=labelName[buttonID]+"_";
			 fileNmaeString=filePath;
				myJLabel2.turnBack();
				myJLabel3.turnBack();
				ImageIcon imageIcon=new  ImageIcon("stats/"+fileNmaeString+"playersBar.png") ;
	          
				if(picture!=null){
		
					Statics.this.remove(picture.label);
					}
				
			picture = new MyJLabel(122, 188, 700, 500, imageIcon, imageIcon);
					 
			Statics.this.add(picture.label);
			Statics.this.repaint();
			if(oneJLabel!=null){
				Statics.this.remove(oneJLabel);
			}
	         oneJLabel=new JLabel(StaticImage.backOfoneTextForTongji);
	        oneJLabel.setBounds(780	, 348, 424, 162);
	        Statics.this.add(oneJLabel);
        for(int i=0;i<myJLabels.size();i++){
        	if(buttonID!=i){
        	  myJLabels.get(i).turnBack();
        	}
        }
//			String seanson=seasonsBox.getSelectedItem().toString();
//			 String filePath = labelName[buttonID]+"_"+seanson;
//
//		        System.out.println(filePath+"_playersName.txt");
//		      readTxtFile("data/"+filePath+"_playersName.txt","c:/playersName.txt");
//		      readTxtFile("data/"+filePath+"_perAfter.txt","c:/perAfter.txt");
//		      readTxtFile("data/"+filePath+"_perBefor.txt","c:/perBefore.txt");
//		       readRT();
////		       Timer timer = new Timer();
////		       timer.schedule(new MyTask(), 5000);
////                timer.cancel();
//          //      draws();
//		       
//		       File fileOld=new File("C:\\nameSeason.txt");
//               fileOld.delete();
//                
//                try {
//                	  FileWriter file1 = new FileWriter("C:\\nameSeason.txt",true);
//					 file1.write(filePath+"playerBar.png");
//					 file1.flush();
//		                file1.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
               
	
		}
			
			 
		
	}
//	
//	private void draws(){
//		   
//		ImageIcon	portait=null;
//		if(oneOrTwo==true){
//		portait=new ImageIcon("c:/playersBar.png");
//		oneOrTwo=false;
//		}else {
//				portait=new ImageIcon("c:/playerBar1.png");
//			oneOrTwo=true;
//		}
//					if(picture!=null){
//					System.out.println("sfdsvd");
//						this.remove(picture.label);
//						}
//					
//				picture = new MyJLabel(122, 153, 800, 600, portait, portait);
//						 
//				this.add(picture.label);
//						this.repaint();
//	
//	}

	private void getStarEight(){
		if(namesJpanel!=null){
			this.remove(namesJpanel);
		}
		namesJpanel=new JLabel();
		namesJpanel.setLayout(new FlowLayout());
		namesJpanel.setBounds(496, 103,800,70);
		
		namesJpanel.add(TracyMcGrady.label);
	    namesJpanel.add(GilbertArenas.label);
	    namesJpanel.add(EltonBrand.label);
	    namesJpanel.add(MartellWebster.label);
	    namesJpanel.add(SpeedyClaxton.label);
	    namesJpanel.add(AndrewBogu.label);
	    namesJpanel.add(AndrewBynum.label);

		this.add(namesJpanel);
		this.revalidate();
		this.repaint();
	
	}
	private void getStarEleven(){
		if(namesJpanel!=null){
			this.remove(namesJpanel);
		}
		namesJpanel=new JLabel();
		namesJpanel.setLayout(new FlowLayout());
		namesJpanel.setBounds(496, 103,800,70);
		
		namesJpanel.add(AlHorford.label);
	    namesJpanel.add(ChaunceyBillups.label);
	    namesJpanel.add(EricMaynor.label);
	    namesJpanel.add(RickyRubio.label);
	    namesJpanel.add(JermaineONeal.label);
	 

		this.add(namesJpanel);
		this.revalidate();
		this.repaint();
	
	}
	private void getStarTwelf(){
		if(namesJpanel!=null){
			this.remove(namesJpanel);
		}
		namesJpanel=new JLabel();
		namesJpanel.setLayout(new FlowLayout());
		namesJpanel.setBounds(496, 103,800,70);
		
		namesJpanel.add(AndersonVarejao.label);
	    namesJpanel.add(AndreaBargnani.label);
	    namesJpanel.add(ArronAfflalo.label);
	    namesJpanel.add(BrandonRoy.label);
	    namesJpanel.add(DannyGranger.label);
	    namesJpanel.add(KevinLove.label);
	    namesJpanel.add(KobeBryant.label);
	    namesJpanel.add(RajonRondo.label);

	 

		this.add(namesJpanel);
		this.revalidate();
		this.repaint();
	
	}
	private void getStarThirteen(){
		if(namesJpanel!=null){
			this.remove(namesJpanel);
		}
		namesJpanel=new JLabel();
		namesJpanel.setLayout(new FlowLayout());
		namesJpanel.setBounds(496, 103,800,70);
		
		namesJpanel.add(AlHorford.label);
	    namesJpanel.add(BrookLopez.label);
	    namesJpanel.add(DerrickRose.label);
	    namesJpanel.add(JaValeMcGee.label);
	    namesJpanel.add(KobeBryant.label);
	    namesJpanel.add(NateRobinson.label);
	    namesJpanel.add(NickYoung.label);
	    namesJpanel.add(SteveNash.label);

	 

		this.add(namesJpanel);
		this.revalidate();
		this.repaint();
	
	}
	private void getStarFourteen(){
		if(namesJpanel!=null){
			this.remove(namesJpanel);
		}
		
		namesJpanel=new JLabel();
		namesJpanel.setLayout(new FlowLayout());
		namesJpanel.setBounds(496, 103,800,70);
		
		namesJpanel.add(AlecBurk.label);
	    namesJpanel.add(AndersonVarejao.label);
	    namesJpanel.add(BrandonJennings.label);
	    namesJpanel.add(CarmeloAnthony.label);
	    namesJpanel.add(ChrisBosh.label);
	    namesJpanel.add(JoshMcRoberts.label);
	    namesJpanel.add(KevinDurant.label);
	    namesJpanel.add(KobeBryant.label);
	    namesJpanel.add(SteveNash.label);
	    namesJpanel.add(WesleyMatthews.label);
	    namesJpanel.add(XavierHenry.label);

	 

		this.add(namesJpanel);
		this.revalidate();
		this.repaint();
	
	}
	private void initialize() {
		getLabelInit();
		this.setSize(cfg.getWidth(), cfg.getHeight());
		 getLabelInit2();
//		ImageIcon  portait = new ImageIcon("c:/playersBar.png");
//				picture = new MyJLabel(112, 153, 800, 600, portait, portait);
//				 
//			this.add(picture.label);
//				this.repaint();
		/**
		 * 初始化那些文字按钮们
		 */
	MyButton nextButton=new MyButton(StaticImage.bacckofnext, StaticImage.bacckofnext, StaticImage.bacckofnext, 1141, 676,100 , 62);
	this.add(nextButton.jbutton);
	nextButton.jbutton.addActionListener(new returnButtonActionListener());
		
		seasonsBox= new SelectJComboBox<String>();
		
			seasonsBox.addItem("2008-09");
			seasonsBox.addItem("2011-12");
			seasonsBox.addItem("2012-13");
			seasonsBox.addItem("2013-14");
			seasonsBox.addItem("2014-15");
		

		seasonsBox.setBounds(204,110, 141, 22);
		seasonsBox.setBackground(new Color(10, 54, 101));
		seasonsBox.setUI(ColorArrowUI.createUI(seasonsBox));
		
		seasonsBox.addMouseListener(new MouseAdapter() {
			

			public void mouseEntered(MouseEvent arg0) {
				 if(seasonsBox.getSelectedIndex()==0){
					 getStarEight();
            }else if(seasonsBox.getSelectedIndex()==1){
            	getStarEleven();
            }else if(seasonsBox.getSelectedIndex()==2){
            	getStarTwelf();
            }else if(seasonsBox.getSelectedIndex()==3){
            	getStarThirteen();
            }else if(seasonsBox.getSelectedIndex()==4){
            	getStarFourteen();
            }
			}
		});
		this.add(seasonsBox);
		// 换界面的按钮
		MyButton button0 = new MyButton(StaticImage.backOfqiuyuani_d,
				StaticImage.backOfqiuyuan_l,StaticImage.backOfqiuyuan_b, 1020, 55, 76, 34);
		this.add(button0.jbutton);
		changeToPlayerPanel = button0.jbutton;
          changeToPlayerPanel.addActionListener(new button0ActionListener());

		MyButton button1 = new MyButton(StaticImage.backOfmatch_d,
				StaticImage.backOfmatch_l,StaticImage.backOfmatch_b, 1200, 55, 76, 34);
		this.add(button1.jbutton);
		changeToMatchPanel = button1.jbutton;
		changeToMatchPanel.addActionListener(new button1ActionListener());
		MyButton button2 = new MyButton(StaticImage.backOfqiudui_d,
				StaticImage.backOfqiudui_l,StaticImage.backOfqiudui_b, 1110, 55, 76, 34);
		this.add(button2.jbutton);
		changeToTeamPanel = button2.jbutton;
		changeToTeamPanel.addActionListener(new button2ActionListener());
//		  jLabelHot=new LeftHotPanel(currentJpanel, currentFrame);
//	  		
//	  		this.add(jLabelHot.hotPanel,0);
	}
	class returnButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
         nextJpanel=new TuiStatics(currentJpanel, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
           
      
		}

	}
	class button0ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Player(Statics.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}

	class button1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Match(Statics.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}
	class button2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			nextJpanel = new Team(Statics.this, currentFrame);
			MyFrame.getFrame().changePanel(nextJpanel);
		}
	}
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(StaticImage.backOfstatisticsbk.getImage(), 0, 0, null);
	}
	  public static void readTxtFile(String filePath,String toPath){
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                     
	                    File fileOld=new File(toPath);
	                    fileOld.delete();
	                    FileWriter file1 = new FileWriter(toPath,true);
	                     
	                    file1.write(bufferedReader.readLine());
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                       // System.out.println(lineTxt);
	                    	
	                    	  file1.write("\r\n"+lineTxt);
	                    }
	                    read.close();
	                    file1.flush();
	                    file1.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	     
	    }

public static void readRT(){
	  try{  
          System.out.println("start");  
          Process pr = Runtime.getRuntime().exec("python rt.py");  
            
          BufferedReader in = new BufferedReader(new  
                  InputStreamReader(pr.getInputStream()));  
          String line;  
          while ((line = in.readLine()) != null) {  
              System.out.println(line);  
          }  
          in.close();  
          pr.waitFor();  
          System.out.println("end");  
  } catch (Exception e){  
              e.printStackTrace();  
          }  
  }  

}
