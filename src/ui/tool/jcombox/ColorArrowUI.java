package ui.tool.jcombox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;



public //重写JComboBox的UI，createUI()方法为静态方法，写成内部类报错，故这样书写
class ColorArrowUI extends BasicComboBoxUI {
	static int width;
	static int height;

	public static ComboBoxUI createUI(JComponent c) {
		width = c.getWidth();
		height = c.getHeight();
		return new ColorArrowUI();
	}

	@Override
	protected JButton createArrowButton() {
		JButton btn = new JButton();
		btn.setBorder(BorderFactory.createLineBorder(new Color(69, 69, 69)));
		try {
			btn.setIcon(new ImageIcon(resize(
					ImageIO.read(new File("image/arrowbutton.png")),
					width / 10, height)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return btn;
	}

	// 调整图片大小
	public BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}