package ui.tool.picture;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ui.tool.frame.MyFrame;
public class GridBagWindow extends JPanel {
  private JButton searchBtn;
  private JComboBox modeCombo;
  private JLabel tagLbl;
  private JLabel tagModeLbl;
  private JLabel previewLbl;
  private JTable resTable;
  private JTextField tagTxt;
 public GridBagWindow() {
   Container contentPane = MyFrame.getFrame().getContentPane();
   GridBagLayout gridbag = new GridBagLayout();
   contentPane.setLayout(gridbag);
   GridBagConstraints c = new GridBagConstraints();
   //setting a default constraint value
   c.fill =GridBagConstraints.HORIZONTAL;
   tagLbl = new JLabel("Tags");
   c.gridx = 0; //x grid position
   c.gridy = 0; //y grid position
   gridbag.setConstraints(tagLbl, c); //associate the label with a constraint object 
   contentPane.add(tagLbl); //add it to content pane
   /**
    * 
    Gridx——组件的横向坐标
    Girdy——组件的纵向坐标
    Gridwidth——组件的横向宽度，也就是指组件占用的列数，这与HTML的colspan类似
    Gridheight——组件的纵向长度，也就是指组件占用的行数，这与HTML的rowspan类似
    Weightx——指行的权重，告诉布局管理器如何分配额外的水平空间
    Weighty——指列的权重，告诉布局管理器如何分配额外的垂直空间
    Anchor——告诉布局管理器组件在表格空间中的位置
    Fill——如果显示区域比组件的区域大的时候，可以用来控制组件的行为。控制组件是垂直填充，还是水平填充，或者两个方向一起填充
    Insets——指组件与表格空间四周边缘的空白区域的大小
    Ipadx—— 组件间的横向间距,组件的宽度就是这个组件的最小宽度加上ipadx值
    ipady—— 组件间的纵向间距,组件的高度就是这个组件的最小高度加上ipady值
1：gridx，gridy:

这个表示的组件的横向索引和纵向索引，int类型，索引指向的值就是对应的单元格。

2：gridwidth，gridheight

这个表示的是一个组件占据的单元格的数目，int类型。首先，占据的数目大不意味着组件大，因为它周边可能是空白。其次，要注意的是，这里gridwidth，gridheight的值可以超过行最大组件数，列最大组件数，但是超过之后也没意义，超过之后系统会把它变成行或者列的最大值。例如GridBagLayout是M×N格子，那么Gridwidth最大有效值就是M，gridheight的最大有效值就是N。

默认值是1。

3：weightx，weighty

指定如何分布额外的水平，竖直空间。这个意思也很简单，权重越大，在窗口面积发生变化时，组件周围占据的空白就越大，就好比膨胀一样，原本组件1原本占据的单元格比组件2大，那么在膨胀后，它占据的空白就比组件2更多。

默认值是0

4：fill

这个指定怎么填充组件占据的空白，有HORIZENTAL，VERTICAL，BOTH，NULL。

默认值是NULL

5：anchor

这个主要是规定组件在它显示区域内应该以何种方式显示，说白了就是各种对齐的问题。

6：insets

这个是一个Inset类，指定组件与其上，左，底，右之间的距离，单位是像素，不是单元格。默认值是（0,0,0,0）

Insets（int top,int left,int bottom,int right）

7：ipadx，ipady

指定组件内部的宽，高，组件的大小等于组件的最小宽，高加上对应的ipadx，ipady。

默认值都是0.
    */
   tagModeLbl = new JLabel("Tag Mode");
   c.gridx = 0;
   c.gridy = 1;
   gridbag.setConstraints(tagModeLbl, c);
   contentPane.add(tagModeLbl);
   tagTxt = new JTextField("plinth");
   c.gridx = 1;
   c.gridy = 0;
   c.gridwidth = 2;
   gridbag.setConstraints(tagTxt, c);
   contentPane.add(tagTxt);
   String[] options = {"all", "any"};
   modeCombo = new JComboBox(options);
   c.gridx = 1;
   c.gridy = 1;
   c.gridwidth = 1;
   gridbag.setConstraints(modeCombo, c);
   contentPane.add(modeCombo);
   searchBtn = new JButton("Search");
   c.gridx = 1;
   c.gridy = 2;
   gridbag.setConstraints(searchBtn, c);
   contentPane.add(searchBtn);
   resTable = new JTable(5,3);
   c.gridx = 0;
   c.gridy = 3;
   c.gridwidth = 3;
   gridbag.setConstraints(resTable, c);
   contentPane.add(resTable);
   previewLbl = new JLabel("Preview goes here");
   c.gridx = 0;
   c.gridy = 4;
   gridbag.setConstraints(previewLbl, c);
   contentPane.add(previewLbl);
//  addWindowListener(new WindowAdapter() {
//    public void windowClosing(WindowEvent e) {
//     System.exit(0);
//    }
//  });
}

}