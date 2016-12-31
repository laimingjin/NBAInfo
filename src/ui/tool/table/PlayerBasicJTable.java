package ui.tool.table;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import vo.PlayerBasicVO;
import vo.PlayerTotalVO;

public class PlayerBasicJTable  extends ImageJTable {

	private static String[] averageColumn =  {"ID","Pic", "Name", "Team", 
		"Position", "School", "Country",  "Birthday",

		"Height", "Weight", "Number", "EXP" };
	private ArrayList<PlayerBasicVO> totalList=new ArrayList<PlayerBasicVO>();
	
	private JPanel content;
    private static int  width;

	public PlayerBasicJTable(JPanel content,ArrayList<PlayerBasicVO> totalLists) {
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
                if (PlayerBasicJTable.this.getSelectedRow() == -1) {
                    return;
                }
                if ((column=PlayerBasicJTable.this.getSelectedColumn()) == 1) {
                	int row = PlayerBasicJTable.this.getSelectedRow();
                	
                }
                if((column=PlayerBasicJTable.this.getSelectedColumn()) == 2){
                	int row = PlayerBasicJTable.this.getSelectedRow();
                	String teamName = (String)PlayerBasicJTable.this.getValueAt(row, column);

                }
            }
        });

	}

	@Override
	public void refresh() {
		String[] columnNames;
	     
    	columnNames = averageColumn;
    	width=80;
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
			ImageIcon imageIcon=new  ImageIcon("portrait/"+totalList.get(i).getPlayerName()+".png") ;
		if(imageIcon.getIconWidth()<5){
			imageList.add(new  ImageIcon("portrait/default.png"));
			
		}else {
			imageList.add(imageIcon );
		}
			
		

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
		
		/** 
	private static String[] averageColumn =  { "Type", "Seanson", "Date", "awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		{"ID", "Type", "Seanson", "Date", 
		"awayTeam", "homeTeam", "F", "Q1",
		"Q2", "Q3", "Q4" };
		 */
				String[] s = new String[12];
				//s[0] = (i + 1) + "";
				s[0]=p.getPlayerID();
				s[2] = p.getPlayerName() + "";
				s[3] = p.getTeamName() + "";
				s[4]=p.getPosition()+"";
				s[5] = p.getSchool() + "";
				s[6] = p.getCountry() + "";
	             s[7]=p.getBirthday()+"";
	        
				s[8] = p.getHeight()+ "";
				s[9] = p.getWeight() + "";
				s[10] = p.getNumberSquad() + "";
				s[11] = p.getFromYear()+ "-" +p.getToYear();

	          
				return s;
			}

			
			private class MouseHandle extends MouseAdapter {

				public void mousePressed(PlayerTotalVO e) {


				}
			}

}