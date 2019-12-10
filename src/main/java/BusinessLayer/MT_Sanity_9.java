package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_9 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_009() throws Exception
	{
		//invoice details page
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.Login();
		aa.clickoninvoice();
		aa.Logout();
		aa.closeBrowser();
		Reporter.log("Ninth sanity test passed");
	

	}	
}