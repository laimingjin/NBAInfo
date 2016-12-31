package ui.tool.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class JiaButton {
	public JButton jbutton;
	
	Icon enter;
	Icon pressed;
	public JiaButton(Icon i,Icon i2,int x0,int y0,int x1,int y1){
		jbutton=new JButton("");
		jbutton.setBorderPainted(false);
		jbutton.setOpaque(false);
		jbutton.setIcon(i);
		jbutton.setBounds(x0, y0, x1, y1);
		jbutton.setBackground(new Color(2,2,2));
		setListern();
		enter=i;
	
		pressed=i2;
	}
	public void setListern(){
		jbutton.addMouseListener(new MouseAdapter() {
//			public void mouseEntered (MouseEvent e) {
//			jbutton.setIcon(enter);
//			}
//			
//			public void mouseExited (MouseEvent e) {
//				jbutton.setIcon(exit);
//			}
			public void mousePressed(MouseEvent e) {
				jbutton.setIcon(pressed);
			}
		});

	}
	public void  turnBack(){
		jbutton.setIcon(enter);
	}
}
