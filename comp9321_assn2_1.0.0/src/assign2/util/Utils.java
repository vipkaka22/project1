package assign2.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}

	// Check if a string is numeric
	public static boolean isPositiveInteger(String str){
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true;
	}
	public static boolean isEmail(String email) {
		Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = emailPattern.matcher(email);
		if (matcher.find())
			return true;
		else
			return false;
	}
	
	public static boolean isAlphabetic(String string) {
		Pattern AlphPattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = AlphPattern.matcher(string);
		if (matcher.find())
			return true;
		else
			return false;
	}
	public static boolean isCreditCardNum(String string) {
		Pattern CreditPattern = Pattern.compile("[0-9]{16}");
		Matcher matcher = CreditPattern.matcher(string);
		if (matcher.find())
			return true;
		else
			return false;
	}
	public static boolean isBirthYear(String BirthYear) {
		int year = Integer.parseInt(BirthYear);
		Calendar cal = Calendar.getInstance();
		if (year < 1900 || year > cal.get(Calendar.YEAR))
			return false;
		else
			return true;
	}
	public static boolean isPrice(String Float) {
		Pattern FloatPattern = Pattern.compile("^(0|[1-9][0-9]{0,9})(.[0-9]{1,2})?$");
		Matcher matcher = FloatPattern.matcher(Float);
		if (matcher.find())
			return true;
		else
			return false;
	}
}
