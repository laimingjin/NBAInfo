package readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;





import po.PlayerBasicPO;


public class Player_basic_read {
	
	
	public static PlayerBasicPO read(String fileName) throws IOException {
		String playerID="";
		String teamID="";
		String playerName="";
		String teamName="";
		String number="";
		String position="";
		String height="";
		String weight="";
		String birthday="";
		String school="";
		String country="";
		String fromYear="";
		String toYear="";
		BufferedReader br=new BufferedReader(new FileReader(fileName));
        String line=br.readLine();
        String result=line;
		while((line=br.readLine())!=null){
		result=result+line;
			}
		
			String[]temp=result.split("\\[\\[");
			String []infos=temp[1].split(",");
			System.out.println(infos.length);
			playerID=infos[0];
		    teamID=infos[17];
		 playerName=infos[3].substring(1, infos[3].length()-1);
		 if(infos[19].length()>2){
			 teamName=infos[19].substring(1, infos[19].length()-1);
		 }else{
			 teamName="";
		 }
		 
if(infos[14].length()>2){
			 number=infos[14].substring(1, infos[14].length()-1);
		 }else{
			number ="";
		 }
		 
if(infos[15].length()>2){
			  position=infos[15].substring(1, infos[15].length()-1);
		 }else{
			 position="";
		 }
		
if(infos[11].length()>2){
	System.out.println(infos[11]);
			 height=infos[11].substring(1, infos[11].length()-1);
		 }else{
			 height="";
		 }
		 
if(infos[12].length()>2){
			  weight=infos[12].substring(1, infos[12].length()-1);
		 }else{
			 weight="";
		 }
		
if(infos[7].length()>2){
	System.out.println(playerID);
	System.out.println(infos[7]);
			 birthday=infos[7].substring(1, 10);
		 }else{
			birthday ="";
		 }
		 
if(infos[8].length()>2){
			 school=infos[8].substring(1, infos[8].length()-1);
		 }else{
			school ="";
		 }
		 
if(infos[9].length()>2){
			 country=infos[9].substring(1, infos[9].length()-1);
		 }else{
			country ="";
		 }
		
		if(infos[23].length()>2){
			 fromYear=infos[23].substring(1, infos[23].length()-1);
		 }else{
			fromYear ="";
		 }
		
		if(infos[24].length()>2){
			 toYear=infos[24].substring(1, infos[24].length()-1);
		 }else{
			 toYear="";
		 }
		
//		for(int i=0;i<infos.length;i++){
//			System.out.println(i+":"+infos[i]);
//		}
		
		return new PlayerBasicPO(playerID,playerName,teamID,teamName, school, country, birthday, position, height, weight, number, fromYear, toYear);
	}
public static ArrayList<PlayerBasicPO> read_all_basic(String fileDir) throws IOException{
	ArrayList<PlayerBasicPO>pos=new ArrayList<PlayerBasicPO>();
	File file=new File(fileDir);
	String info_list[]=file.list();
	for(int i=0;i<info_list.length;i++){
		String fileName=fileDir+"/"+info_list[i];
		PlayerBasicPO po=read(fileName);
		pos.add(po);
	}
	return pos;
}	
//public static void main(String[]args) throws IOException{
//	PlayerBasicPO po=read("D://2.txt");
//	System.out.println(po.getPlayerName());
//	System.out.println(po.getTeamName());
//	System.out.println(po.getSchool());
//	System.out.println(po.getCountry());
//	System.out.println(po.getBirthday());
//	System.out.println(po.getPosition());
//	System.out.println(po.getHeight());
//	System.out.println(po.getWeight());
//	System.out.println(po.getNumberSquad());
//	System.out.println(po.getFromYear());
//	System.out.println(po.getToYear());
//	
//}

public static void main(String[]args) throws IOException{
ArrayList<String>list=new ArrayList<String>();
String fileDir="D://nba_data/playerBasic";
File file=new File(fileDir);
String info_list[]=file.list();
for(int i=0;i<info_list.length;i++){
	String fileName=fileDir+"/"+info_list[i];
	BufferedReader br=new BufferedReader(new FileReader(fileName));
	String line=br.readLine();
	String result=line;
	while((line=br.readLine())!=null){
	result=result+line;
		}

		String[]temp=result.split("\\[\\[");
		String []infos=temp[1].split(",");
//		System.out.println(infos.length);
		if(infos.length>35){
			String l=fileName.substring(0,fileName.length()-4)+","+infos.length;
			list.add(l);
		}
}
for(int i=0;i<list.size();i++){
	System.out.println(list.get(i));
}

}

}
