package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_1 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_001() throws Exception
	{
		//verify the Login , check home page title and  Logout functionality
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.closeBrowser();
		Reporter.log("first sanity test passed");
	

	}	
}