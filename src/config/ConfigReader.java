package config;



import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//配置读取器

public class ConfigReader {
public  static void readConfig() throws DocumentException{
	SAXReader reader=new SAXReader();
	Document doc=reader.read("config/NewFile.xml");
//获得整个根的元素
	Element game=doc.getRootElement();
	Element frame=game.element("frame");
	List<Element>layers= frame.elements("layer");
	for(Element layer:layers){
	layer.attributeValue("x");
	layer.attributeValue("y");
	layer.attributeValue("w");
	layer.attributeValue("h");
	}
	//根据标签找到属性
	String string=frame.attributeValue("width");
	
}
public static void main(String[]args) throws DocumentException{
	readConfig();
}
}
