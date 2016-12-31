package ui.tool.table;
/***
 * 具体画某一个表格并且对其中的做了监听
 */
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import vo.PlayerTotalVO;

public class PlayerJTable extends StatJTable {
	
	private static String[] averageColumn =  {"ID", "Season","Player", "Team", "GP",
		"GS",
		"MIN", "PTS",
		"FGA", "FGM", "FG%", "TPA", "TPM", "TP%", "FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF" };
	
	private ArrayList<PlayerTotalVO> totalList=new ArrayList<PlayerTotalVO>();
	
	private JPanel content;
    private static int  width;

	public PlayerJTable(JPanel content,ArrayList<PlayerTotalVO> totalLists) {
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
                if (PlayerJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=PlayerJTable.this.getSelectedColumn()) == 1) {
                	int row = PlayerJTable.this.getSelectedRow();
                	
                }
                if((column=PlayerJTable.this.getSelectedColumn()) == 2){
                	int row = PlayerJTable.this.getSelectedRow();
                	String teamName = (String)PlayerJTable.this.getValueAt(row, column);

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
		String[] columnNames;
     
        	columnNames = averageColumn;
        	width=45;
    
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
//		 TableColumnModel columnModel =this.getColumnModel();
//		   TableColumn column = columnModel.getColumn(0);
//	        column.setMinWidth(0);
//	        column.setMaxWidth(0);
	    
		this.paintRow();
		this.updateRowHeights();
		this.resizeColumnWidth(width);
		this.validate();
		this.repaint();

	}


	private String[] getAverageDataRow(PlayerTotalVO p, int i) {
	
/** *  { “Team", "GP", "GS", "MIN", "WIN", "PTS",
		"FGA", "FGM", "FGPer", "TPA", "TPM", "TPPer", "FTA", "FTM", "FTPer",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF" };
 */
		String[] s = new String[25];
		//s[0] = (i + 1) + "";
		s[0] = p.getPlayerID()+ "";
		s[1]=p.getMatchSeason();
		s[2] = p.getPlayerName() + "";
		s[3] = p.getTeamName() + "";
		s[4] = p.getGP() + "";
		s[5]=p.getGS()+"";
		s[6] = p.getMIN()+ "";
		s[7] = p.getPTS() + "";
		s[8] = p.getFGA() + "";
		s[9] = p.getFGM()+ "";
		
		s[10]=changeToShort(p.getFGPer());
		s[11] = p.getTPA()+ "";
		s[12] = p.getTPM() + "";
		s[13] = changeToShort(p.getTPPer());
		s[14] = p.getFTA() + "";
		s[15]=p.getFTM()+"";
		s[16] = changeToShort(p.getFTPer());
		s[17] = p.getREB()+ "";
		s[18] = p.getOREB()+ "";
		s[19] = p.getDREB()+ "";
		s[20]=p.getAST()+"";
		s[21] = p.getSTL()+ "";
		s[22] = p.getBLK()+ "";
		s[23] = p.getTOV()+ "";
		s[24]=p.getPF()+"";
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



	
