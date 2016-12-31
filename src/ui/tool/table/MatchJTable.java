package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.MatchBasicVO;
import vo.PlayerTotalVO;
import enumerate.TypeOfMatch;

public class MatchJTable extends StatJTable {

	private static String[] averageColumn =  {"ID", "Type", "Seanson", "Date", "awayTeam", "homeTeam",  "Q1A",
		 "Q1H",
		"Q2A", "Q2H", "Q3A", "Q3H", "Q4A" , "Q4H", "TotalA", "TotalH"};
	private ArrayList<MatchBasicVO> totalList=new ArrayList<MatchBasicVO>();
	
	private JPanel content;
    private static int  width;

	public MatchJTable(JPanel content,ArrayList<MatchBasicVO> totalLists) {
		super();
        
		this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.content = content;
		this.totalList=totalLists;
		
	
		this.getTableHeader().addMouseListener(new MouseHandle());
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column;
                if (MatchJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=MatchJTable.this.getSelectedColumn()) == 1) {
                	int row = MatchJTable.this.getSelectedRow();
                	
                }
                if((column=MatchJTable.this.getSelectedColumn()) == 2){
                	int row = MatchJTable.this.getSelectedRow();
                	String teamName = (String)MatchJTable.this.getValueAt(row, column);

                }
            }
        });

	}

	@Override
	public void refresh() {
		String[] columnNames;
	     
    	columnNames = averageColumn;
    	width=79;

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
	private String[] getAverageDataRow(MatchBasicVO p, int i) {
		
		/** 
	private static String[] averageColumn =  { "Type", "Seanson", "Date", "awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		{"ID", "Type", "Seanson", "Date", 
		"awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		 */
				String[] s = new String[16];
				//s[0] = (i + 1) + "";
				s[0]=p.getGameID();
				s[1] = p.getMatchType() + "";
				s[2] = p.getMatchSeason() + "";
				s[3] = p.getMatchDate() + "";
				s[4] = p.getAwayTeam() + "";
				s[5] = p.getHomeTeam() + "";
	             s[6]=p.getFirstPoints_awayTeam()+"";
	         	s[7]=p.getFirstPoints_homeTeam()+"";
				s[8] = p.getSecondPoints_awayTeam() + "";
				s[9] = p.getSecondPoints_homeTeam() + "";
				s[10] = p.getThirdPoints_awayTeam() + "";
				s[11] = p.getThirdPoints_homeTeam()+ "";
				s[12] = p.getForthPoints_awayTeam()+ "";
	             s[13]=p.getForthPoints_homeTeam()+"";     
	             s[14] = p.getTotalPoints_awayTeam()+ "";
	             s[15]=p.getTotalPoints_homeTeam()+"";     
				
				return s;
			}

			
			private class MouseHandle extends MouseAdapter {

				public void mousePressed(PlayerTotalVO e) {


				}
			}


}
