package config;

import org.dom4j.DocumentException;
//单件模式
public class ConfigFactory {
   private static GameConfig GAME_CONFIG=null;
   //satic先处理的
  static{
	 try {
		GAME_CONFIG=new GameConfig();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   }
  public static GameConfig getGameConfig(){
	  return GAME_CONFIG;
  }
}
