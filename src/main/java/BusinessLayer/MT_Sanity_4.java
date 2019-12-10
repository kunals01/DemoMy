package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_4 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_004() throws Exception
	{
		//verify the Login , check home page title and  close the Browser 
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.Login();
		aa.CheckSearchCriteriafields();
		aa.Logout();
		aa.closeBrowser();
		Reporter.log("Fourth sanity test passed");
	

	}	
}