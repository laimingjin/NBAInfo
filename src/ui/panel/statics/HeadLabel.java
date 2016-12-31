package ui.panel.statics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.tool.picture.StaticImage;

public class HeadLabel extends JLabel {

//	public JPanel label;
	String times;
	JButton kobe=new JButton("ccc");
	JButton thom=new JButton("ffff");
	public  HeadLabel (String time){
		this.times=time;
		

	//	label.setOpaque(false);
		

		initialize();
		
	}
	private void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(475, 39));
		this.setVisible(true);
		kobe.setSize(100,20);
		kobe.setOpaque(false);
		kobe.setBorderPainted(false);
	
		kobe.setBackground(new Color(2,2,2));
		thom.setSize(new Dimension(100,20));
		thom.setOpaque(false);
		thom.setBorderPainted(false);
		thom.setSize(100,20);
	//	thom.setBounds(100, 20, 100, 20);
		this.add(thom);
		this.add(kobe);
		this.repaint();

		kobe.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				
               	 System.out.println(times+"kobe");

			}
		});
		if(times.equals("2014-15")){
			
			this.add(thom);
			this.add(kobe);
			this.add(thom);
			this.add(kobe);
			this.add(thom);
			this.add(kobe);
			
		}
	}
}
