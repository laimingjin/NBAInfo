package ui.tool.frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTipsFrame extends JFrame{
//private static final long serialVersionUID = 1L;
	
	//private static MyInputFrame MY_INPUTFRAME = new MyInputFrame();
	public final static int FRAME_WIDTH = 1266;// 固定窗口的宽
	public final static int FRAME_HEIGHT =380;// 固定窗口的高
	// 用于检测显示器的大小
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screen = toolkit.getScreenSize();

	// 当前界面的JPanel
	private JPanel currentPanel;

//	public MyTipsFrame() {// 构造器
////		this.getIni(currentpanel);
//	}
	

	 enum X {LEFT, RIGHT};
	 enum Y {UP, DOWN};
	// 初始化的方法
	public void getIni(JPanel currentpanel) {
		int x_bounds = (screen.width - FRAME_WIDTH) / 2;
		int y_bounds = (screen.height - FRAME_HEIGHT) / 2 - 32;
		
		this.setSize( FRAME_WIDTH, FRAME_HEIGHT);// 设置窗口位置和大小
		this.setLocation(x_bounds, y_bounds);
		this.setLayout(null);// 关闭布局管理器
		this.setUndecorated(true);//使窗口无边框
		this.setBackground(null);//设置背景为空
	
		this.setResizable(false);//固定窗口大小
		
		
	
		currentPanel=currentpanel;  
		currentPanel.setVisible(true);
		this.add(currentPanel);
		
		
				this.setVisible(true);//设置窗口可见
				
				 
	}
	//传递窗口控制的方法
//	public static MyInputFrame getFrame(){
//		return MY_INPUTFRAME;
	//}
	
	//转换当前窗口的方法
	public void changePanel(JPanel thePanel){
		thePanel.setLayout(null);
		this.add(thePanel);
		thePanel.setVisible(true);
		
		this.remove(currentPanel);
		currentPanel=thePanel;
		currentPanel.updateUI();	
	}
	
	
		 
}
