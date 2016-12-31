package ui.tool.button;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class MyStarButton {
	public JButton jbutton;
	Icon exit;
	Icon enter;

	public MyStarButton(Icon i,Icon i2,int x0,int y0){
		jbutton=new JButton("");
		jbutton.setBorderPainted(false);
		jbutton.setOpaque(false);
		jbutton.setIcon(i);
		jbutton.setSize(x0, y0);
		jbutton.setBackground(new Color(2,2,2));
		setListern();
		enter=i2;
		exit=i;

	}
	public void setListern(){
		jbutton.addMouseListener(new MouseAdapter() {
//			public void mouseEntered (MouseEvent e) {
//				jbutton.setIcon(enter);
//			}
			
			public void mouseExited (MouseEvent e) {
				jbutton.setIcon(exit);
			}
			public void mouseClicked(MouseEvent e) {
				jbutton.setIcon(enter);
			}
		});

	}
	public void  turnBack(){
		jbutton.setIcon(exit);
	}
	
}
