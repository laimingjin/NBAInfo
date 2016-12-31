package ui.start;

import java.io.IOException;

import ui.panel.player.Player;
import ui.tool.frame.MyFrame;

public class start {

	public static void main(String[] args) throws IOException {
	//	MyFrame.getFrame().changePanel(new Team(null, MyFrame.getFrame()));;
//MyFrame.getFrame().changePanel(new PlayerBasic(null,"Alex Len", MyFrame.getFrame()));;
MyFrame.getFrame().changePanel(new Player(null, MyFrame.getFrame()));;
//MyFrame.getFrame().changePanel(new Statics(null, MyFrame.getFrame()));;
//		MyFrame.getFrame().changePanel(new Live(null, MyFrame.getFrame()));;
//MyFrame.getFrame().changePanel(new Live(null, MyFrame.getFrame()));;
//		MatchBasicVO matchBasicVO=new MatchBasicVO(TypeOfMatch.ALLSTAR, "2013","2013","HOU", "LAL", "44:44", "1:1", "2:2","3:3", "4:4");
//		
//		MyFrame.getFrame().changePanel(new MatchBasic(null,matchBasicVO, MyFrame.getFrame()));;
		//MyFrame.getFrame().changePanel(new TeamBasic(null, "HOU",MyFrame.getFrame()));
	}
}
