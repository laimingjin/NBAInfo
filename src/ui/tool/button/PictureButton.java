package ui.tool.button;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;

public class PictureButton {
	public JButton jbutton;
	public PictureButton(Icon i,int x0,int y0,int x1,int y1){
		jbutton=new JButton("");
		jbutton.setBorderPainted(false);
		jbutton.setOpaque(false);
		jbutton.setIcon(i);
		jbutton.setBounds(x0, y0, x1, y1);
		jbutton.setBackground(new Color(2,2,2));
	}
}
