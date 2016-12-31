package wordLive;


public class GameLive {
	
	GameInfo gameInfo=new GameInfo("http://g.hupu.com/nba/daily/playbyplay_150122.html");
	
	private String webAddress="http://g.hupu.com/nba/daily/playbyplay_150122.html";
	
	Game4Live game4Live;

	public GameLive(String webAddress) {
		super();
//		!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.webAddress = webAddress;
		
		game4Live=new Game4Live(webAddress);
//		!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	public GameInfo getGameInfo() {
		return gameInfo;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public Game4Live getGame4Live() {
		return game4Live;
	}
	
	
	
	

}
