package wordLiveTest;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

/** 
 * JTable������ 
 */
public class MyTestTable {
    
    private JFrame frame = null;

    private JTable table = null;

    private Table_Model model = null;

    private JScrollPane s_pan = null;

    public MyTestTable() {
        frame = new JFrame("Test");
        model = new Table_Model(20);
        table = new JTable(model);
        String[] age = { "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
        JComboBox com = new JComboBox(age);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(2).setCellEditor(new DefaultCellEditor(com)); // ����ĳ�в���JComboBox��� 


        model.addRow("�ν�", true, "30");
        model.addRow("�����", false, "21");
        model.addRow("����", true, "24");
        
        s_pan = new JScrollPane(table);

        frame.getContentPane().add(s_pan, BorderLayout.CENTER);
        
        frame.setSize(300, 200);
        frame.setVisible(true);

        //model.addRow(2); // ��ĳ������һ���� 

        //table.updateUI(); // ˢ�� 


    }

    public static void main(String args[]) {
        new MyTestTable();
    }

}

class Table_Model extends AbstractTableModel {
    private static final long serialVersionUID = -3094977414157589758L;

    private Vector content = null;

    private String[] title_name = { "����", "�Ա�", "����" };

    public Table_Model() {
        content = new Vector();
    }

    public Table_Model(int count) {
        content = new Vector(count);
    }

    /** 
     * ����һ���� 
     * @param row �к� 
     */
    public void addRow(int row) {
        Vector v = new Vector(3);
        v.add(0, null);
        v.add(1, null);
        v.add(2, null);
        content.add(row, v);
    }

    /** 
     * ����һ������ 
     */
    public void addRow(String name, boolean a, String age) {
        Vector v = new Vector(3);
        v.add(0, name);
        v.add(1, new Boolean(a)); // JCheckBox��Boolean��Ĭ����ʾ������������Ϊ�˿�Ч������ʵ��JComboBox��ʾ***������ 

        v.add(2, age); // ������ǰ���Ѿ����ó���JComboBox����������������ʲô�ַ�����û��ϵ 

        content.add(v);
    }

    public void removeRow(int row) {
        content.remove(row);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex == 2) {
            return false;
        }
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return title_name[col];
    }

    public int getColumnCount() {
        return title_name.length;
    }

    public int getRowCount() {
        return content.size();
    }

    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

}