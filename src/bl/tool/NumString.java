package bl.tool;
/**
 *@author:小春
 *@date:2015年5月29日下午9:30:02
 *@version
 */

public class NumString {
	/**
	 * 
	 *@author:小春
	 *@data:2015年5月29日下午9:30:21
	 *@param str 需要判断的字符串
	 *@return
	 *boolean
	 */
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
}
