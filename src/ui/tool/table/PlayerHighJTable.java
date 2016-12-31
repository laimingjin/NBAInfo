package ui.tool.table;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerHighVO;
import vo.PlayerTotalVO;

public class PlayerHighJTable extends StatJTable {
	private static String[] highColumn={"ID","Season","Player", "Team", 
		"GP", "MIN", "WIN", "LOSS", "W_PCT",
		"OffRtg", "DefRtg", "NetRtg", "AST%", "AST/TO", "ASTRatio",
		"OREB%", "DREB%","REB%","TORatio","eFG%","TS%",
		"USG%","PACE","PIE"};
	private ArrayList<PlayerHighVO> highlist=new ArrayList<PlayerHighVO>();

	private JPanel content;
    private static int  width;
    
	public PlayerHighJTable(JPanel content,ArrayList<PlayerHighVO> highlists) {
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
                if (PlayerHighJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=PlayerHighJTable.this.getSelectedColumn()) == 1) {
                	int row = PlayerHighJTable.this.getSelectedRow();
                	
                }
                if((column=PlayerHighJTable.this.getSelectedColumn()) == 2){
                	int row = PlayerHighJTable.this.getSelectedRow();
                	String teamName = (String)PlayerHighJTable.this.getValueAt(row, column);

                }
            }
        });

	}
	@Override
	public void refresh() {
		String[] columnNames;
		columnNames =highColumn;
    	width=50;
    	this.selected = selected;

		// System.out.println(c.getClass());
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
	//Collections.sort(totalList, c);
		imageList = new ArrayList<Image>();
		int size=0;
		
	 
			size=Math.min(showSize, highlist.size());
		
		
		
			for (int i = 0; i < size; i++) {
				String[] s = null;
			
			
					s = getHighDataRow(highlist.get(i), i);
			
				//添加数据到表格

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

	private String[] getHighDataRow(PlayerHighVO p, int i) {
		String[] s = new String[24];
		//s[0] = (i + 1) + "";
		
		s[0]=p.getPlayerID()+ "";
		s[1]=p.getMatchSeason();
		s[2] = p.getPlayerName()+ "";
		s[3] = p.getTeamName() + "";
		
		s[4] =p.getGP()+ "";
		s[5] =  df.format(p.getMIN())+ "";
		s[6] = p.getWIN() + "";
		s[7] = p.getLOSS()+ "";
		s[8] =  df.format(p.getW_PCT())+ "";
		s[9] = df.format( p.getOffRtg() )+ "";
		s[10] =  df.format(p.getDefRtg()  )+ "";
		s[11] =  df.format(p.getNetRtg() )+ "";
		s[12] =  df.format(p.getASTPER())+ "";
		s[13] = df.format( p.getAST_TO() )+ "";
		s[14] = df.format( p.getASTRatio()) + "";
		s[15] = df.format( p.getOREBPer())+ "";
		s[16] = df.format( p.getDREBPer())+ "";
		s[17] = df.format( p.getREBPer())+ "";
		s[18] = df.format( p.getTORatio()) + "";
		s[19] = df.format( p.getEFGPer() )+ "";
		s[20] = df.format( p.getTSPer() )+ "";
		s[21] = df.format( p.getUSGPer() )+ "";
		s[22] = df.format( p.getPACE() )+ "";
		s[23] = df.format( p.getPIE()) + "";
		
		return s;

	}
	private class MouseHandle extends MouseAdapter {

		public void mousePressed(PlayerTotalVO e) {


		}
	}
}
