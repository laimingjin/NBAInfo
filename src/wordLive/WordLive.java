package wordLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WordLive {
	
	
	
	public static void main (String[] args){
		
		WordLive wl=new WordLive();
		
		String str="01234567892307";
		
		String string2=str.substring(str.indexOf("23")+"23".length(),str.indexOf("7"));
		
		System.out.println(string2);
		
//		wl.readWeb("http://g.hupu.com/nba/daily/playbyplay_150120.html");
	}
}
 