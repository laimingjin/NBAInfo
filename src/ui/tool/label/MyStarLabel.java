package ui.tool.label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

public class MyStarLabel {

	public JLabel label;
	Icon old;
	Icon pressed;
	public MyStarLabel (int x0,int y0,final Icon i1,final Icon i2){
		 label=new JLabel(i1);
		 old=i1;
		 pressed=i2;
			label.setSize(x0,y0);
			
			label.addMouseListener(new MouseAdapter() {
		
				public void mouseEntered(MouseEvent E){
					
					//label.setIcon(i2);
				}
				public void mousePressed(MouseEvent e) {
					label.setIcon(i2);
				}
//				public void mouseExited(MouseEvent e) {
//					label.setIcon(i1);
//				}
			});
		
	}
	public void turnBack(){
		 label.setIcon(old);
	}
	public void setBack(){
		 label.setIcon(pressed);
	}
	}

