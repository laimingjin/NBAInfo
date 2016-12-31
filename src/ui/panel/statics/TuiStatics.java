package ui.panel.statics;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.tool.button.MyButton;
import ui.tool.frame.MyFrame;
import ui.tool.label.MyJLabel;
import ui.tool.picture.StaticImage;
import config.ConfigFactory;
import config.GameConfig;

public class TuiStatics extends JPanel{
	   // 获取配置
		GameConfig cfg = ConfigFactory.getGameConfig();
		// 返回按钮
		private JButton returnJButton = new JButton();// 存放按钮
		/**
		 * 一些用户界面转换的panel，frame
		 */
		private JPanel nextJpanel;
		private JPanel currentJpanel;
		private Frame currentFrame;
		private JPanel lastJPanel;
		public TuiStatics(JPanel lastJPanel, Frame current) {
			setVisible(true);
			setLayout(null);
			this.currentFrame = current;
			this.lastJPanel = lastJPanel;
			this.currentJpanel = this;
			initialize();

		}
		private void initialize() {
		
			this.setSize(cfg.getWidth(), cfg.getHeight());

			 MyButton returnButton = new MyButton(StaticImage.backOfback,StaticImage.backOfback,
			 StaticImage.backOfbackEntered, 9,149, 117, 31);
			 this.add(returnButton.jbutton);
			 returnJButton = returnButton.jbutton;
			 returnJButton.addActionListener(new returnButtonActionListener());
		
			ImageIcon imageIcon1=new  ImageIcon("stats/pp.png") ;
			ImageIcon imageIcon2=new  ImageIcon("stats/p3p.png") ;
			MyJLabel picture1 = new MyJLabel(122, 188,500, 375, imageIcon1, imageIcon1);
			 
		this.add(picture1.label);
		MyJLabel picture2 = new MyJLabel(682, 188,  500, 375, imageIcon2, imageIcon2);
		 
		this.add(picture2.label);
		this.repaint();
		JLabel	 oneJLabel=new JLabel(StaticImage.backOfoneTextForTongj4);
	       
	        oneJLabel.setBounds(244	, 600, 387, 130);
	        this.add(oneJLabel);
	        JLabel	 twpJLabel=new JLabel(StaticImage.backOfoneTextForTongj5);
		       
	        twpJLabel.setBounds(750	, 600, 387, 130);
	        this.add(twpJLabel);
	        
		}
		class returnButtonActionListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {

				MyFrame.getFrame().changePanel(lastJPanel);
	           
	      
			}

		}
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.drawImage(StaticImage.bacckofTuiStaticbk.getImage(), 0, 0, null);
		}

}
