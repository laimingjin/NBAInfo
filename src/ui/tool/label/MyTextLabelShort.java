package ui.tool.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import config.ConfigFactory;
import config.GameConfig;

public class MyTextLabelShort {
	// 获取配置
				GameConfig cfg = ConfigFactory.getGameConfig();
			public JLabel label;
			public MyTextLabelShort (int x0,int y0,String name){
				 label=new JLabel();
				// label.setIcon(StaticImage.backOfbestPlayerbtn);
				label.setText(name);
				label.setFont(new Font("Arial", 0,15));
		  label.setBorder(null);
		  label.setOpaque(false);
		  label.setBounds(x0,y0, 150,27);
					
					label.addMouseListener(new MouseAdapter() {
						public void mouseEntered(MouseEvent E){
							label.setForeground(Color.BLUE);
						}
						public void mouseExited(MouseEvent e) {
							label.setForeground(Color.BLACK);
						}
					});
				
			}
}
