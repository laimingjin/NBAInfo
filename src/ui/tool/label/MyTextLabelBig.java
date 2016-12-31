package ui.tool.label;

import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;

import config.ConfigFactory;
import config.GameConfig;

public class MyTextLabelBig {
	// 获取配置
			GameConfig cfg = ConfigFactory.getGameConfig();
		public JLabel label;
		public MyTextLabelBig (int x0,int y0,String name){
			 label=new JLabel();
			// label.setIcon(StaticImage.backOfbestPlayerbtn);
			label.setText(name);
			label.setFont(new Font("Hobo std", 0,25));
	  label.setBorder(null);
	  label.setOpaque(false);
	  label.setBounds(x0,y0,cfg.getBasicTextFieldW(), cfg.getBasicTextFieldH());
				
				label.addMouseListener(new MouseAdapter() {
					
				});
			
		}
}
