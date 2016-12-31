package ui.tool.label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

public class MyJLabel2 {
	public JLabel label;
	Icon old;
	Icon pressed;
	public MyJLabel2 (int x0,int y0,int x1,int y1,final Icon i1,final Icon i2){
		 label=new JLabel(i1);
		 old=i1;
		 pressed=i2;
			label.setBounds(x0,y0,x1,y1);
			
			label.addMouseListener(new MouseAdapter() {
		
				public void mouseEntered(MouseEvent E){
					label.setIcon(i2);
				}
				public void mousePressed(MouseEvent e) {
					label.setIcon(i2);
				}
				public void mouseExited(MouseEvent e) {
					label.setIcon(i1);
				}
			});
		
	}
}
