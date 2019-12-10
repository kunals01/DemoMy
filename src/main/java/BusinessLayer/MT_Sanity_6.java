package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_6 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_006() throws Exception
	{
		//verify the Login , check home page title and  close the Browser 
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.Login();
	  aa.blanksearch(false, false);
		aa.Logout();
		aa.closeBrowser();
		Reporter.log("Sixth sanity test passed");
	

	}	
}
