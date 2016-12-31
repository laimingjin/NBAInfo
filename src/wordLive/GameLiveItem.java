package wordLive;


import java.util.Arrays;

public class GameLiveItem {

	int sid;
	
	StateOfGameLiveItem stateOfGameLiveItem;
	String[] gameItems;
	
	public GameLiveItem(int sid, StateOfGameLiveItem stateOfGameLiveItem,
			String[] gameItems) {
		super();
		this.sid = sid;
		this.stateOfGameLiveItem = stateOfGameLiveItem;
		this.gameItems = gameItems;
	}

	@Override
	public String toString() {
		return "GameLiveItem [sid=" + sid + ", stateOfGameLiveItem="
				+ stateOfGameLiveItem + ", gameItems="
				+ Arrays.toString(gameItems) + "]";
	}
	
	public String[] toStrings(){
		
		
		return gameItems;
//		return String[] a=
	}

	public boolean endGame() {
		if (stateOfGameLiveItem==StateOfGameLiveItem.ONE && gameItems[0].equals("±ÈÈü½áÊø")) {
			return true;
		}
		return false;
	}
	
	
	
	
	
}
