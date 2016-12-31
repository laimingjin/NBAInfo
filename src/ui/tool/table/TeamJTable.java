package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerHighVO;
import vo.PlayerTotalVO;
import vo.TeamHighVO;
import vo.TeamTotalVO;
import enumerate.TypeOfMatch;

public class TeamJTable extends StatJTable {

	
	private static String[] averageColumn =  { "ID","Season","Team", "GP",  "WIN",
		"LOSS","PTS",
		"FGA", "FGM", "FG%", "TPA", "TPM", "TP%", "FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TO", "PF" };


	private ArrayList<TeamTotalVO> totalList=new ArrayList<TeamTotalVO>();
	
	private JPanel content;
 private static int width;
	
	public TeamJTable( JPanel content,ArrayList<TeamTotalVO> totalLists) {
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
                if (TeamJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=TeamJTable.this.getSelectedColumn()) == 1) {
                	int row = TeamJTable.this.getSelectedRow();
                	
                }
                if((column=TeamJTable.this.getSelectedColumn()) == 2){
                	int row = TeamJTable.this.getSelectedRow();
                	String teamName = (String)TeamJTable.this.getValueAt(row, column);

                }
            }
        });

	}

	/**
	 * 刷新方法
	 * 
	 * @param selected
	 *            平均数还是总数，平均数==true，总数==false
	 * @param c
	 *            比较类，参见compare包
	 * @param order
	 *            顺序还是逆序，顺序==true，逆序==false
	 */
	public void refresh() {

		this.selected = selected;

		String[] columnNames;
       
        	columnNames = averageColumn;
        	width=48;
        
      

		// System.out.println(c.getClass());
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
	//Collections.sort(totalList, c);
		imageList = new ArrayList<Image>();
//		TeamTotalVO teamTotalVO=new TeamTotalVO(TypeOfMatch.ALLSTAR, "22","hov",  3, 4, 5,
//				6, 7, 8, 9, 1, 2, 3, 4, 5,6, 7, 8, 2, 3,4, 5, 7);
//	
//		for(int i=0;i<30;i++){
//		totalList.add(teamTotalVO);
//		}
		int size = Math.min(showSize, totalList.size());
		
	
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


	private String[] getAverageDataRow(TeamTotalVO p, int i) {
	
/** *  { "球员", "球队", "GP", "GS", "MIN", "WIN", "PTS",
		"FGA", "FGM", "FGPer", "TPA", "TPM", "TPPer", "FTA", "FTM", "FTPer",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF" };
 */
		String[] s = new String[24];
		//s[0] = (i + 1) + "";
	  
		s[0] = p.getTeamID() + "";
		s[1]=p.getMatchSeason();
		s[2] = p.getTeamName() + "";
		s[3] = p.getGP()+ "";
		s[4] = p.getWIN() + "";
		s[5] = p.getLOSS() + "";
		s[6] = p.getPTS() + "";	
		s[7] = p.getFGA() + "";
		s[8] = p.getFGM() + "";
		s[9] = changeToShort(p.getFGPer());
		s[10] = p.getTPA() + "";
		s[11] = p.getTPM() + "";
		s[12] = changeToShort(p.getTPPer());
		s[13] = p.getFTA() + "";
		s[14] = p.getFTM() + "";
		s[15] =changeToShort(p.getFTPer());
		s[16] = p.getREB() + "";
		s[17] = p.getOREB() + "";
		s[18] = p.getDREB() + "";
		s[19] = p.getAST() + "";
		s[20] = p.getSTL()+ "";
		s[21] = p.getBLK() + "";
		s[22] = p.getTO() + "";
		s[23] = p.getPF() + "";
	
		return s;
	}

	public String changeToShort(String s){
		if(s.length()>4){
			return s.substring(0,4)+"%";
		}
		return s;
	}

	private class MouseHandle extends MouseAdapter {

		public void mousePressed(PlayerTotalVO e) {


		}
	}
}
