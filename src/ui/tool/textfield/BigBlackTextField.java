package ui.tool.textfield;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import config.ConfigFactory;
import config.GameConfig;

public class BigBlackTextField {
	// 获取配置
			GameConfig cfg = ConfigFactory.getGameConfig();
			public 	 JTextField jtextfield;
			public 	BigBlackTextField (int x0,int y0,String text){
					jtextfield= new JTextField();
					jtextfield.setColumns(10);
					jtextfield.setOpaque(false);
					jtextfield.setBorder(null);
					jtextfield.setText(text);
					jtextfield.setEditable(false);
					jtextfield.setFont(new Font("Oswald-Bold", 0,50));
					jtextfield.setForeground(new Color(0, 0, 0));
					jtextfield.setBounds(x0, y0, 211, 54);
			
				}
}
