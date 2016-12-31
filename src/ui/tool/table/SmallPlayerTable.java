package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerBasicVO;
import vo.PlayerTotalVO;

public class SmallPlayerTable extends StatJTable {
	private static String[] averageColumn =  { "ID","Player"};
	private ArrayList<PlayerBasicVO> totalList=new ArrayList<PlayerBasicVO>();
	
	private JPanel content;
    private static int  width;
    public SmallPlayerTable(JPanel content,ArrayList<PlayerBasicVO> totalLists) {
		super();
        
		this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.content = content;
		this.totalList=totalLists;
		
	
	

	}


	@Override
	public void refresh() {
		String[] columnNames;
	     
    	columnNames = averageColumn;
    	width=82;

	this.selected = selected;

	// System.out.println(c.getClass());
	DefaultTableModel model = new DefaultTableModel(null, columnNames);
//Collections.sort(totalList, c);
	imageList = new ArrayList<Image>();
	int size=0;
	
 size = Math.min(showSize, totalList.size());
	
	
		for (int i = 0; i < size; i++) {
			String[] s = null;
			
				s = getAverageDataRow(totalList.get(i), i);
			
			// 添加数据到表格

			model.addRow(s);
			//imageList.add(totalList.get(i).getPortrait());

		}
	

	// 更新表格
	this.setModel(model);
	this.paintRow();
	this.updateRowHeights();
	this.resizeColumnWidth(width);
	this.validate();
	this.repaint();

	}

	private String[] getAverageDataRow(PlayerBasicVO p, int i) {
	
/** *  { “Team", "GP", "GS", "MIN", "WIN", "PTS",
		"FGA", "FGM", "FGPer", "TPA", "TPM", "TPPer", "FTA", "FTM", "FTPer",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF" };
 */
		String[] s = new String[2];
		//s[0] = (i + 1) + "";
		s[0] = p.getPlayerID() + "";
		s[1] = p.getPlayerName()+ "";
	
		return s;
	}
	
	private class MouseHandle extends MouseAdapter {

		public void mousePressed(PlayerTotalVO e) {


		}
	}


}
