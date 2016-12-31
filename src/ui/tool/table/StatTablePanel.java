package ui.tool.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
/**
 * 放带有图片表格的Panel
 * @author tset
 *
 */
public class StatTablePanel extends JPanel{
	protected static Image headBar;
	protected static Image filter;
	protected int width;
	protected int height;

	protected StatJTable statTable;
	protected DefaultTableModel model;
	//protected HeadPanel headPanel;
	protected JScrollPane jspane;


	protected int type;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, 2000, 50 * height / (1080));
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 50 * height / (1080), 2000, 66 * height / (1080));
		g.drawImage(headBar, 0, 0, this);
		g.drawImage(filter, 0, 50 * height / (1080), this);
		g.setFont(new Font("STHUPO", Font.PLAIN, 40 * 2 / 3));
	}

	public StatTablePanel(int width, int height) {
		this.width = width;
		this.height = height;
	
		this.type = 1;
		setLayout(null);

		BufferedImage bufferHeadBar = null;
		BufferedImage bufferedFilter = null;

		headBar = bufferHeadBar;
		filter = bufferedFilter;
		
	}


}
