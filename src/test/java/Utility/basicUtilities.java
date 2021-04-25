package Utility;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class basicUtilities {
	
	public static final long WAIT = 20;

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	
	
}
