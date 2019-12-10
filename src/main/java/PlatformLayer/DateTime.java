package PlatformLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime 
{
	
	/**
     * This method gives current Date & Time in dd/mm/yyyy HH:mm:ss formate.
     * 
     * @return date
     */
	public static String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}		 
	 /**
	  * This method gives current Date & Time in yyyy-MM-dd-HH-mm-ss formate.
	  * 
	  * @return date
	  */
	 // now() returns the current time in yyyy-MM-dd-HH-mm-ss format
	 public static final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm-ss";
	 public static final String BC_DATE_FORMAT_NOW = "dd.MM.yyyy";
	 public static String now() 
	 {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());
	  }	
	 public static String getCurrentTime() 
	 {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(BC_DATE_FORMAT_NOW);
	    return  sdf.format(cal.getTime());
	    		
	  }	
	 public static void main(String[] args) {
		getCurrentTime();
	}
		public static String getDate(String format) 
		
		{
		 
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat(BC_DATE_FORMAT_NOW);
		    return  sdf.format(cal.getTime());
		 	
		}
		
		public static String getNextDate(int days){
			 SimpleDateFormat formattedDate = new SimpleDateFormat(BC_DATE_FORMAT_NOW);            
			  Calendar ca = Calendar.getInstance();        
			  ca.add(Calendar.DATE, days);  // number of days to add      
			  return  (String)(formattedDate.format(ca.getTime()));
		}
			
}
