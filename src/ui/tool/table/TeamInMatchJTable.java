package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import enumerate.TypeOfMatch;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;
import vo.TeamInMatchVO;

public class TeamInMatchJTable extends StatJTable{
	protected static final String String = null;

	private static String[] averageColumn =  { "ID","Date", "Type","awyT", "homeT", 
		"numofP", "FGA", "FGM", "FG%", "TPA", "TPM", "TP%", "FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF","PTS","+/-" };
	private ArrayList<TeamInMatchVO> totalList=new ArrayList<TeamInMatchVO>();

	private JPanel content;
	private  static int width;
	

	public TeamInMatchJTable(JPanel content, ArrayList<TeamInMatchVO> totalListss) {
		super();
        
		this.portraitWidth =  80 ;
		this.portraitHeight =  70 ;
		this.content = content;
	
		this.totalList=totalListss;
		this.getTableHeader().addMouseListener(new MouseHandle());
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column;
                if (TeamInMatchJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=TeamInMatchJTable.this.getSelectedColumn()) == 1) {
                	int row = TeamInMatchJTable.this.getSelectedRow();
                	
                }
                if((column=TeamInMatchJTable.this.getSelectedColumn()) == 2){
                	int row = TeamInMatchJTable.this.getSelectedRow();
                	String teamName = (String)TeamInMatchJTable.this.getValueAt(row, column);

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
	public void refresh( ) {
		
		String[] columnNames;
		
			columnNames = averageColumn;
			width=44;
			
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
	    //Collections.sort(totalList, c);
		imageList = new ArrayList<Image>();
//		PlayerInMatchVO playerInMatchVO=new PlayerInMatchVO("20131313", "hov", "lal", "fff", "hov", 1, 2, 4, 5, "4", 6, 6, "5", 1,5, "6", 4, 4, 4, 4,1, 2, 3, 1,4, "5");
//		PlayerTotalVO playerTotalVO=new PlayerTotalVO(TypeOfMatch.PLAYOFF, "22", "22","33", 1, 2, 3, 4, 5,
//				6, 7, "4", 9, 1, "4", 3, 4, "6",6, 7, 8, 2, 3,4, 5, 7);
//	
//		for(int i=0;i<10;i++){
//		totalList.add(playerInMatchVO);
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
	private	String[] getAverageDataRow(TeamInMatchVO p, int i) {
			
			/**p"ID","Date", "Type","awyT", "homeT", 
		"numofP", "FGA", "FGM", "FG%", "TPA", "TPM", "TP%", "FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF","PTS","+/-" */
					String[] s = new String[25];
					//s[0] = (i + 1) + "";
					s[0]=p.getGameID();
					
					s[1] = p.getMatchDate() + "";
					s[2]=p.getMatchType()+"";
					
					s[3] = p.getAwayTeam() + "";
					s[4] = p.getHomeTeam() + "";
					s[5] = p.getNumOfPlayer() + "";
		
					s[6] = p.getFGA() + "";
					s[7] = p.getFGM() + "";
					s[8] = df.format(p.getFGPer()) + "";
					s[9]=p.getTPA() + "";
					s[10] = p.getTPM()+ "";
					s[11] =df.format( p.getTPPer() )+ "";
					s[12] = p.getFTA() + "";
					s[13] = p.getFTM() + "";
					s[14] = df.format(p.getFTPer() )+ "";
					s[15] = p.getREB() + "";

					s[16] = p.getOREB() + "";
					s[17] = p.getDREB() + "";
					s[18] = p.getAST() + ""; 
					s[19] = p.getSTL() + "";
					s[20] = p.getBLK() + "";
					s[21] = p.getTOV() + "";
					s[22] = p.getPF() + "";
					s[23]=p.getPTS()+"";
					s[24]=df.format(p.getPLUS_MINUS())+"";
					return s;
				}
	public String changeToShort(String s){
		if(s.length()>4){
			return s.substring(0,4)+"%";
		}
		return s;
	}
	private	class MouseHandle extends MouseAdapter {

			public void mousePressed(PlayerTotalVO e) {


			}
		}



}