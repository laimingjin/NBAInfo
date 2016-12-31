package ui.tool.textfield;

import java.awt.Font;

import javax.swing.JTextField;

import config.ConfigFactory;
import config.GameConfig;

public class MyTextField2 {
	// 获取配置
	GameConfig cfg = ConfigFactory.getGameConfig();
	public 	 JTextField jtextfield;
	public 	MyTextField2 (int x0,int y0,String text){
			jtextfield= new JTextField();
			jtextfield.setColumns(10);
			jtextfield.setOpaque(false);
			jtextfield.setBorder(null);
			jtextfield.setText(text);
			jtextfield.setEditable(false);
			jtextfield.setFont(new Font("Arial", 0,15));
			jtextfield.setBounds(x0, y0, cfg.getBasicTextFieldW(), cfg.getBasicTextFieldH());
		}
}
