package ExecutionLayer;

//import org.junit.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class TestSuiteRunner {
	
	 @BeforeSuite
	 public static void setUp() {      
		 
		/*
		 * FileHandling.config = new Hashtable<String,String>(); FileHandling.tcname =
		 * new Hashtable<String,String>(); FileHandling.tcdesc = new
		 * Hashtable<String,String>(); FileHandling.tctestdata = new
		 * Hashtable<String,String>(); //FileHandling.XlsToXml(
		 * "C:\\Projects\\Selenium\\SeleniumAutomation\\Config.xlsx",
		 * "C:\\Projects\\Selenium\\SeleniumAutomation\\Config.xml"); // Convert XLSX to
		 * XML FileHandling.ReadConfig(); FileHandling.doc =
		 * FileHandling.CreateResultXML();
		 */

	    }

	  @AfterSuite
	    public static void tearDown() { 
	   		// SendMail.sendMail("C:\\Automation\\ICToWeb\\test-output\\emailable-report.html"); 
			 System.out.println("mail sent");
	    }
  
	
	
}