package config;



import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//���ö�ȡ��

public class ConfigReader {
public  static void readConfig() throws DocumentException{
	SAXReader reader=new SAXReader();
	Document doc=reader.read("config/NewFile.xml");
//�����������Ԫ��
	Element game=doc.getRootElement();
	Element frame=game.element("frame");
	List<Element>layers= frame.elements("layer");
	for(Element layer:layers){
	layer.attributeValue("x");
	layer.attributeValue("y");
	layer.attributeValue("w");
	layer.attributeValue("h");
	}
	//���ݱ�ǩ�ҵ�����
	String string=frame.attributeValue("width");
	
}
public static void main(String[]args) throws DocumentException{
	readConfig();
}
}
