package readFiles;

import javax.net.ssl.SSLContext;

public class NumberFormatChange {
public static int changeToInt(String num){
	if(num.equals(" ")||num.equals("")||num.equals("null")){
		return 0;
	}else if(num.contains("]")){
		//System.out.println("yes");
		char[]ss=num.toCharArray();
		int index=0;
		for(int i=0;i<ss.length;i++){
			if(ss[i]==']'){
				index=i;
				break;
			}
		}
		String res="";
		for(int j=0;j<index;j++){
			res=res+ss[j];
		}
		return Integer.parseInt(res);
	}
	else if(num.contains(".")){
//		String nn[]=num.split(".");
//		num=nn[0];
		double nn=Double.parseDouble(num);
		return (int)nn;
	}
	return Integer.parseInt(num);
			
}
public static String changeToPer(String num){
	if(num.equals(" ")||num.equals("")||num.equals("null")){
		return "0.0%";
	}else if(!(num.contains("%"))){
		if(num.contains("]")){
			//System.out.println("yes");
			char[]ss=num.toCharArray();
			int index=0;
			for(int i=0;i<ss.length;i++){
				if(ss[i]==']'){
					index=i;
					break;
				}
			}
			String res="";
			for(int j=0;j<index;j++){
				res=res+ss[j];
			}
			return Double.parseDouble(res)*100+"%";
		}
		return Double.parseDouble(num)*100+"%";
	}
	return num;
}
public static double changeToDouble(String num){
	if(num.equals(" ")||num.equals("") ||num.equals("null") ||num.equals(null)){
		return 0.0;
	}else if(num.contains("]")){
		//System.out.println("yes");
		char[]ss=num.toCharArray();
		int index=0;
		for(int i=0;i<ss.length;i++){
			if(ss[i]==']'){
				index=i;
				break;
			}
		}
		String res="";
		for(int j=0;j<index;j++){
			res=res+ss[j];
		}
		return Double.parseDouble(res);
	}
	return Double.parseDouble(num);
}
public static int changeTimeForm(String time){
	if(time.equals("0") ||time.equals("null")||time.equals("")||time.equals("ul")){
		return 0;
	}
	String []s=time.split(":");
	if(s.length<2){
		return Integer.parseInt(s[0]);
	}else if(s.length==2){
		int sec=Integer.parseInt(s[0]);
		int min=Integer.parseInt(s[1]);
		return sec+60*min;
	}else{
		int sec=Integer.parseInt(s[0]);
		int min=Integer.parseInt(s[1]);
		int hou=Integer.parseInt(s[2]);
		return sec+60*min+3600*hou;
	}
}
public static int changeAvgToTotal(String number,int GP){
	double num=Double.parseDouble(number);
	double res=GP*num;
	return (int)res;
}
}
