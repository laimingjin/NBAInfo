package ui.tool.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 * 做得那个有带有第一列为图片的表格
 * @author tset
 *
 */
public abstract class StatJTable extends BaseJTable {
	protected DecimalFormat df = new DecimalFormat("#0.0");
	protected int showSize = 10000;
	protected ArrayList<Image> imageList;
	// 设置头像大小
	protected int portraitWidth;
	protected int portraitHeight;
	//选中平均还是总数
	protected boolean selected;
	//选中列号
	protected int selectColumn = -1;
	
	protected boolean clicked = false;

	public StatJTable() {
		super();
		this.imageList = new ArrayList<Image>();
		this.setShowGrid(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		adjustHeader();	
		this.setTableHeaderColor(new Color(158,158,158));

	}
	
	
	protected void paintRow() {
		TableColumnModel tcm = this.getColumnModel();
		for (int i = 0, n = tcm.getColumnCount(); i < n; i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setCellRenderer(new RowRenderer());
		}
	}

	public void setImageList(ArrayList<Image> portraitsList) {
		this.imageList = portraitsList;
	}

	// 重写行方法(paintRow())具体对应的类
	protected class RowRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {

			// 设置内容居中
//			if(column==1){
//				setHorizontalAlignment(SwingConstants.LEFT);
//			}else{
				setHorizontalAlignment(SwingConstants.CENTER);
		//	}
			

			// 设置奇偶行的背景色，可在此根据需要进行修改
			if (row % 2 == 0)
				setBackground(new Color(255, 255, 255));
			else
				setBackground(new Color(218, 218, 218));

			// 设置图片
		/**if (column == 1) {

				ImageIcon icon = new ImageIcon(UIUtils.resize(imageList.get(row),
						portraitWidth, portraitHeight));
				setIcon(icon);

			}**/

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);

		}

		

	}
	

	protected void refreshBySelectedColumn(int j, Comparator c) {
	//	refresh(selected, c,(j==selectColumn&&clicked)||(j!=selectColumn));
		if(selectColumn == j){
			clicked = !clicked;
		}else{
			clicked = false;
		}
		selectColumn = j;
		
	}
	abstract public void refresh();



//	abstract void refreshByScreening(PalyerScreening palyerSelect);
	

}
