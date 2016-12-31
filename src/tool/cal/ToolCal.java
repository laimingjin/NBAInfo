package tool.cal;

public  class ToolCal {
	public ToolCal(){
		
	}
	public static String change_to_percentage(double num){

		String g =new java.text.DecimalFormat("#.0").format(num*100);
		  
		return g+"%";
		}
	public static String change_to_twopercentage(double num){

		String g =new java.text.DecimalFormat("#.00").format(num*100);
		  
		return g+"%";
		}
	public static   String change_to_smallLength(double num){
		String g =new java.text.DecimalFormat("#.0").format(num);
			return g;
		}
}
