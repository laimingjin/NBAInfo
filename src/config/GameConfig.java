package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	/*
	 * 窗口标题
	 */
  private String title;
  /*
	 * 窗口拔高
	 */
private int windowUp;
	/*
	 * 窗口宽度
	 */
  private int width;
  /*
	 * 窗口高度
	 */
  private int height;


private int  changeButtonX;
private int changeButtonY;
private int changeButtonW;
private int changeButtonH;
private int BasicTextFieldW;
private int BasicTextFieldH;
  public GameConfig() throws DocumentException{
	  //创建xml读取器
	  SAXReader reader=new SAXReader();
	  //读取xml
	  //读本地磁盘十分浪费效率，为了防止多次读取，单件模式
		Document doc=reader.read("config/NewFile.xml");
	//获得整个根的元素
		Element game=doc.getRootElement();
		
		//配置窗口参数
		this.setUiConfig(game.element("frame"));
//		//配置系统参数
//		this.setSystemConfig(game.element("system"));
//		//配置数据参数
//		this.setDataConfig(game.element("data"));
		
  }
  /**
   * 配置窗口
   * @param frame 配置文件窗口配置元素
   */
  private void setUiConfig(Element frame){
	  //获取窗口标题
	  this.title=frame.attributeValue("title");
	  //获取窗口拔高
	  this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
	  //获取窗口宽度
	  this.width=Integer.parseInt(frame.attributeValue("width"));
	  //获取窗口高度
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.changeButtonH=Integer.parseInt(frame.attributeValue("changeButtonH"));
		this.changeButtonW=Integer.parseInt(frame.attributeValue("changeButtonW"));
		this.changeButtonX=Integer.parseInt(frame.attributeValue("changeButtonX"));
		this.changeButtonY=Integer.parseInt(frame.attributeValue("changeButtonY"));
		
		this.BasicTextFieldH=Integer.parseInt(frame.attributeValue("BasicTextFieldH"));
		this.BasicTextFieldW=Integer.parseInt(frame.attributeValue("BasicTextFieldW"));
  }
  /**
   * 配置系统参数
   
   */
  private void setSystemConfig(Element system){
	 //TODO
  }
  /**
   * 配置数据参数

   */
  private void setDataConfig(Element data){
	  //TODO
  }
public int getWidth() {
	return width;
}
public int getHeight() {
	return height;
}

public String getTitle() {
	return title;
}
public int getWindowUp() {
	return windowUp;
}
public int getChangeButtonX() {
	return changeButtonX;
}
public int getChangeButtonY() {
	return changeButtonY;
}
public int getChangeButtonW() {
	return changeButtonW;
}
public int getChangeButtonH() {
	return changeButtonH;
}
public int getBasicTextFieldW() {
	return BasicTextFieldW;
}
public int getBasicTextFieldH() {
	return BasicTextFieldH;
}
  
}
