package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import enumerate.TypeOfMatch;
import vo.PlayerInMatchVO;
import vo.PlayerTotalVO;

public class PlayerInMatchJTable extends StatJTable{
	protected static final String String = null;
	private static String[] averageColumn =  {"ID", "Date","awyTeam", "homeTeam","Name", "isGS", "MIN",
		"FGA", "FGM", "FG%", "TPA", "TPM", "TP%", "FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", "TOV", "PF","PTS","+/-" };
	private ArrayList<PlayerInMatchVO> totalList=new ArrayList<PlayerInMatchVO>();

	private JPanel content;
	private  static int width;
	

	public PlayerInMatchJTable(JPanel content, ArrayList<PlayerInMatchVO> totalListss) {
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
                if (PlayerInMatchJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=PlayerInMatchJTable.this.getSelectedColumn()) == 1) {
                	int row = PlayerInMatchJTable.this.getSelectedRow();
                	
                }
                if((column=PlayerInMatchJTable.this.getSelectedColumn()) == 2){
                	int row = PlayerInMatchJTable.this.getSelectedRow();
                	String teamName = (String)PlayerInMatchJTable.this.getValueAt(row, column);

                }
            }
        });
	}
	private	String[] getAverageDataRow(PlayerInMatchVO p, int i) {
			
			/**"ID", "Date", "Type","awyTeam", "homeTeam", 
			 * "isGS", "MIN",
		"FGA", "FGM", "FG%", "TPA", "TPM", "TP%", 
		"FTA", "FTM", "FT%",
		"REB", "OREB", "DREB", "AST", "STL", "BLK", 
		"TOV", "PF","PTS","+/-" };
	 */
					String[] s = new String[26];
					//s[0] = (i + 1) + "";
					s[0]=p.getPlayerID();
					s[1] = p.getMatchDate() + "";
					
					s[2] = p.getAwayTeam() + "";
					s[3] = p.getHomeTeam() + "";
					s[4]=p.getPlayerName();
					s[5] = p.getIsGS() + "";
					s[6] = p.getMIN() + "";

					s[7] = p.getFGA() + "";
					s[8] = p.getFGM() + "";
					s[9] = changeToShort(p.getFGPer() )+ "";
					s[10]=p.getTPA() + "";
					s[11] = p.getTPM()+ "";
					s[12] = changeToShort(p.getTPPer() )+ "";
					s[13] = p.getFTA() + "";
					s[14] = p.getFTM() + "";
					s[15] = changeToShort(p.getFTPer() )+ "";
					s[16] = p.getREB() + "";
					s[17] = p.getOREB() + "";
					s[18] = p.getDREB() + "";
					s[19] = p.getAST() + ""; 
					s[20] = p.getSTL() + "";
					s[21] = p.getBLK() + "";
					s[22] = p.getTOV() + "";
					s[23] = p.getPF() + "";
					s[24]=p.getPTS()+"";
					s[25]=df.format(p.getPLUS_MINUS())+"";
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
		this.selected = selected;
		String[] columnNames;
		
			columnNames = averageColumn;
			width=46;
			
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

}
