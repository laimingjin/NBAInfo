package ui.tool.frame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.tool.picture.StaticImage;
import ui.tool.table.PlayerInMatchJTable;
import ui.tool.table.StatJTable;
import ui.tool.table.TeamInMatchJTable;
import vo.PlayerInMatchVO;
import vo.TeamInMatchVO;

public class MyTeamInMatchTipsPanel extends JPanel{


	JScrollPane jspane;
	StatJTable statTable;
//	private static final long serialVersionUID = 1L;
	private Frame currentFrame;
	private Image currentImage;
   private	ArrayList<TeamInMatchVO> arrTotalVO ;
	public MyTeamInMatchTipsPanel(Frame f,Image image,ArrayList<TeamInMatchVO> arrTotalVOs ) {
		currentFrame=f;
		initialize();
		this.arrTotalVO=arrTotalVOs;
		setLayout(null);
		currentImage=image;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setSize(1266,380);
		JButton mybuButton=new JButton(StaticImage.backOfjbu_click);
		mybuButton.setBounds(1200, 350, StaticImage.backOfjbu_click.getIconWidth(), StaticImage.backOfjbu_click.getIconHeight());
		mybuButton.setBorderPainted(false);
		mybuButton.setOpaque(false);
		mybuButton.setVisible(true);
		mybuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				currentFrame.dispose();	
			
			}
		});
		this.add(mybuButton);
		
		/**
		 * 画下面的各种表格啊
		 */
		jspane = new JScrollPane();
		jspane.setBounds(0, 0, 1276, 374);
		statTable = new TeamInMatchJTable(
				this,arrTotalVO);
		statTable.setBounds(200, 200, 800, 600);
		statTable.refresh();
		jspane.setViewportView(statTable);
		this.add(jspane);
		this.validate();
		this.repaint();

	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(currentImage, 0, 0, null);
	}


}

