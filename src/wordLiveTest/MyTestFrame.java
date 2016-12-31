package wordLiveTest;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.tool.picture.StaticImage;
import ui.tool.textfield.BigBlackTextField;
import wordLive.GameLive;
import wordLive.MyLiveTable;
import wordLive.MyLiveTable2;

public class MyTestFrame extends JFrame{
	
	
	
	public static void main(String[] args){
		MyTestFrame myTestFrame=new MyTestFrame();
		myTestFrame.init();
		
		
	}
	
	public void init(){
		   setTitle("biao ge");
	        setBounds(0,0,1276,760);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	        
//	        JPanel jPanel=new JPanel();
//	        jPanel.setBounds(0,0,610,490);
//	        jPanel.setLayout(null);
//	        add(jPanel);
//	       &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 
	        JLabel jLabel=new JLabel(StaticImage.backOfLiveBk);
	        jLabel.setBounds(0, 0, 1276, 760);
	        this.add(jLabel);
	        MyLiveTable2 myLiveTable=new MyLiveTable2(new GameLive("http://g.hupu.com/nba/daily/playbyplay_150123.html"));
	        JScrollPane scrollPane = new JScrollPane(myLiveTable);   //֧�ֹ���
	        BigBlackTextField myTextField=new BigBlackTextField(15, 300, myLiveTable.getCurrentScore_customer());
			jLabel.add(myTextField.jtextfield);
			BigBlackTextField myTextField2=new BigBlackTextField(1188, 290, myLiveTable.getCurrentScore_main());
			jLabel.add(myTextField2.jtextfield);
			
	      //  getContentPane().add(scrollPane,BorderLayout.CENTER);
	        scrollPane.setBounds(315,184,660,490);
  	        myLiveTable.setBounds(315,184,660,490);
  	        jLabel.add(scrollPane);
//	        myLiveTable.setBounds(0,0,610,490);
	        
	        System.out.println(myLiveTable.getGameInfo());
	        
	        setVisible(true);
	        
	        while(!myLiveTable.isEnd()){
	        	myLiveTable.update();
	        	try {
	        		System.out.println(myLiveTable.getCurrentScore_customer()+"fffffffffffffffffffff");
	        		
//	    			BigBlackTextField myTextField=new BigBlackTextField(15, 270, myLiveTable.getCurrentScore_customer());
//	    			jLabel.add(myTextField.jtextfield);
	    			myTextField.jtextfield.setText(myLiveTable.getCurrentScore_customer());
//	    			BigBlackTextField myTextField2=new BigBlackTextField(1188, 270, myLiveTable.getCurrentScore_main());
//	    			jLabel.add(myTextField2.jtextfield);
	    			myTextField2.jtextfield.setText(myLiveTable.getCurrentScore_main());
	    			this.repaint();
	        		System.out.println(myLiveTable.getValueAt(myLiveTable.getRowCount()-1, 3)); ;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
//		       &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponents(g);

		g.drawImage(StaticImage.backOfLiveBk.getImage(), 0, 0, null);
	}
}
