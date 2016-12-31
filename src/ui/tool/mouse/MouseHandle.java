package ui.tool.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MouseHandle extends MouseAdapter {
    
	ImageIcon newIcon;
	ImageIcon oldIcon;
	ImageIcon selIcon;
	int type;
   private static int selectedNumber=-1;
	public MouseHandle(ImageIcon newIm, ImageIcon oldIm,
			ImageIcon selectIm, int x) {
		newIcon = newIm;
		oldIcon = oldIm;
		selIcon = selectIm;
		type = x;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((selectedNumber != type) && type != 8) {
			((JButton) e.getSource()).setIcon(selIcon);
			clearImage(selectedNumber);
			selectedNumber = type;
		}

		if (type == 1) {
//
//			PlayerStatTablePanel teamRankTablePanel = new PlayerStatTablePanel(
//					width * 10, height, bl, content);
//			teamRankTablePanel.setBounds(0, 0, width * 9, height);
//			content.removeAll();
//			content.add(teamRankTablePanel);
//			content.updateUI();
//			currentType=1;

		}

		if (type == 2) {
//			TeamStatTablePanel teamRankTablePanel = new TeamStatTablePanel(
//					width * 10, height, bl, content);
//			teamRankTablePanel.setBounds(0, 0, width * 9, height);
//			content.removeAll();
//			content.add(teamRankTablePanel);
//			content.updateUI();
//			currentType=2;
			// teamRankTablePanel.refreshTablePanel(type);

		}
	
	}

	public void mouseEntered(MouseEvent e) {

		if (selectedNumber != type) {
			((JButton) e.getSource()).setIcon(newIcon);
		}
	}

	public void mouseExited(MouseEvent e) {

		if (selectedNumber != type) {
			((JButton) e.getSource()).setIcon(oldIcon);
		}
	}

	public void mouseClicked(MouseEvent e) {

	}
	private void clearImage(int type) {
		switch (type) {
//		case 0:
//			statistics.setIcon(statisticsIcon);
//			break;
//		case 1:
//			playerStat.setIcon(playerStatisticsIcon);
//			break;
		
		}

	}
}
