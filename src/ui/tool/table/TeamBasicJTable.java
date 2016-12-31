package ui.tool.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerBasicVO;
import vo.PlayerTotalVO;
import vo.TeamBasicVO;

public class TeamBasicJTable extends ImageJTable {

	private static String[] averageColumn =  {"ID", "Pic","Name", "Abb", 
		"Location", "Zone", "Partition",  "HomeField",

	"Time_setup" };
	private ArrayList<TeamBasicVO> totalList=new ArrayList<TeamBasicVO>();
	
	private JPanel content;
    private static int  width;

	public TeamBasicJTable(JPanel content,ArrayList<TeamBasicVO> totalLists) {
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
                if (TeamBasicJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=TeamBasicJTable.this.getSelectedColumn()) == 1) {
                	int row = TeamBasicJTable.this.getSelectedRow();
                	
                }
                if((column=TeamBasicJTable.this.getSelectedColumn()) == 2){
                	int row = TeamBasicJTable.this.getSelectedRow();
                	String teamName = (String)TeamBasicJTable.this.getValueAt(row, column);

                }
            }
        });

	}

	@Override
	public void refresh() {
		String[] columnNames;
	     
    	columnNames = averageColumn;
    	width=100;
    	imageList = new ArrayList<ImageIcon>();
	this.selected = selected;

	// System.out.println(c.getClass());
	DefaultTableModel model = new DefaultTableModel(null, columnNames);
//Collections.sort(totalList, c);
	
	int size=0;
	
 size = Math.min(showSize, totalList.size());
	
	
		for (int i = 0; i < size; i++) {
			String[] s = null;
			
				s = getAverageDataRow(totalList.get(i), i);
			
			// 添加数据到表格

			model.addRow(s);
			
		imageList.add(new ImageIcon("png/"+totalList.get(i).getAbbreviation()+".png") );
		

		}


	// 更新表格
	this.setModel(model);
	this.paintRow();
	this.updateRowHeights();
	this.resizeColumnWidth(width);
	this.validate();
	this.repaint();
		
	}
	private String[] getAverageDataRow(TeamBasicVO p, int i) {
		
		/** 
	private static String[] averageColumn =  { "Type", "Seanson", "Date", "awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		{"ID", "Type", "Seanson", "Date", 
		"awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		 */
				String[] s = new String[9];
				//s[0] = (i + 1) + "";
				s[0]=p.getTeamID();
			
				s[2] = p.getTeamName() + "";
				s[3]=p.getAbbreviation()+"";
				s[4] = p.getLocation()+ "";
				s[5] = p.getZone()+ "";
	             s[6]=p.getTeam_partition()+"";
	        
				s[7] = p.getHomeField()+ "";
				s[8] = p.getTime_setUp()+ "";
			
				return s;
			}

			
			private class MouseHandle extends MouseAdapter {

				public void mousePressed(PlayerTotalVO e) {


				}
			}

}