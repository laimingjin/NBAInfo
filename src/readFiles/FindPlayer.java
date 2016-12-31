package readFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import po.PlayerPair;

public class FindPlayer {
public static ArrayList<PlayerPair> getPlayerPairs(String fileName) {
	ArrayList<PlayerPair> pairs=new ArrayList<PlayerPair>();
	BufferedReader br=null;
	try {
		br = new BufferedReader(new FileReader(fileName));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String line=null;
	String id="";
	String playerName="";
	try {
		while((line=br.readLine())!=null){
			String []info=line.split(",");
			id=info[0];
			playerName=info[1];
			pairs.add(new PlayerPair(id, playerName));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return pairs;
}
//public static void main(String []args) throws IOException{
//	ArrayList<PlayerPair>pairs=getPlayerPairs("D://player.out");
//System.out.println(pairs.get(1).getPlayerName());
//}
}
