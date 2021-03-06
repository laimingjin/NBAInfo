package ui.tool.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/*
 * 自己重写的一个表格jtable，能够自动调整宽度高度，并且不能被修改
 */

public abstract class BaseJTable extends JTable {

	private static final long serialVersionUID = 1L;

	protected void adjustHeader() {
		JTableHeader tableH = this.getTableHeader();

		tableH.setBackground(new Color(154, 154, 154));
		tableH.setForeground(new Color(255, 255, 255));
		tableH.setPreferredSize(new Dimension(100, 35));
		tableH.setReorderingAllowed(false);
		tableH.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

	}

	// 自动调整行高
	protected void updateRowHeights() {
		try {

			for (int row = 0; row < this.getRowCount(); row++) {
				int rowHeight = this.getRowHeight();

				for (int column = 0; column < this.getColumnCount(); column++) {
					Component comp = this.prepareRenderer(
							this.getCellRenderer(row, column), row, column);
					rowHeight = Math.max(rowHeight,
							comp.getPreferredSize().height);
				}

				this.setRowHeight(row, rowHeight);
			}
		} catch (ClassCastException e) {
		}
	}

	// 自动调整列宽
	protected void resizeColumnWidth(int w) {

		for (int column = 0; column < this.getColumnCount(); column++) {
			int width =w; // Min width
			for (int row = 0; row < this.getRowCount(); row++) {
				TableCellRenderer renderer = this.getCellRenderer(row, column);
				Component comp = this.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void setTableHeaderColor(Color c) {

		TableColumn column;
		for (int columnIndex = 0; columnIndex < this.getColumnCount(); columnIndex++) {
			column = this.getTableHeader().getColumnModel()
					.getColumn(columnIndex);
			DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
			cellRenderer.setBackground(c);
			
			column.setHeaderRenderer(cellRenderer);
		}

	}
}
