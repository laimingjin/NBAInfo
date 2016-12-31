package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerTotalVO;
import vo.TeamHighVO;

public class TeamHighJTable  extends StatJTable {
	private static String[] highColumn={"ID","Season","Team", 
	 "GP","MIN","OffRtg","DefRtg","NetRtg","AST%",
	 "AST/TO","ASTRatio","OREB%","DREB%","REB%",
	 "TORatio","eFG%","TS%","PACE","PIE"};
	private ArrayList<TeamHighVO> highlist=new ArrayList<TeamHighVO>();
	
	private JPanel content;
 private static int width;
	public TeamHighJTable( JPanel content,ArrayList<TeamHighVO> highlists) {
		super();
        
		this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.content = content;
	
	this.highlist=highlists;
		this.getTableHeader().addMouseListener(new MouseHandle());
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column;
                if (TeamHighJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=TeamHighJTable.this.getSelectedColumn()) == 1) {
                	int row = TeamHighJTable.this.getSelectedRow();
                	
                }
                if((column=TeamHighJTable.this.getSelectedColumn()) == 2){
                	int row = TeamHighJTable.this.getSelectedRow();
                	String teamName = (String)TeamHighJTable.this.getValueAt(row, column);

                }
            }
        });

	}

	@Override
	public void refresh() {
		this.selected = selected;

		String[] columnNames;
		columnNames =highColumn;
    	width=60;
    	DefaultTableModel model = new DefaultTableModel(null, columnNames);
    	//Collections.sort(totalList, c);
    		imageList = new ArrayList<Image>();
    		int size=Math.min(showSize, highlist.size());
    		for (int i = 0; i < size; i++) {
				String[] s = null;
			
					s = getHighDataRow(highlist.get(i), i);
				
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
	private String[] getHighDataRow(TeamHighVO p, int i) {
		String[] s = new String[19];
		//s[0] = (i + 1) + "";
	
		s[0] = p.getTeamID()+ "";
		s[1]=p.getMatchSeason();
		s[2] = p.getTeamName() + "";
		s[3]=p.getGP()+"";
		s[4]=p.getMIN()+"";
		s[5] = df.format(p.getOffRtg()*100)+ "";
		s[6] = df.format(p.getDefRtg()*100) + "";
		s[7] = df.format(p.getNetRtg()*100) + "";
		s[8] = df.format(p.getASTPER()*100) + "";
		s[9] = df.format(p.getAST_TO() * 100) + "";
		s[10] = df.format(p.getASTRatio() * 100) + "";
		s[11] = df.format(p.getOREBPer() * 100) + "";
		s[12] = df.format(p.getDREBPer()*100)+ "";
		s[13] = df.format(p.getREBPer()*100) + "";
		s[14] = df.format(p.getTORatio()*100) + "";
		s[15] = df.format(p.getEFGPer()*100) + "";
		s[16] = df.format(p.getTSPer() * 100) + "";
		s[17] = df.format(p.getPACE()* 100) + "";
		s[18] = df.format(p.getPIE() * 100) + "";
	
		return s;

	}
	private class MouseHandle extends MouseAdapter {

		public void mousePressed(PlayerTotalVO e) {


		}
	}
}
