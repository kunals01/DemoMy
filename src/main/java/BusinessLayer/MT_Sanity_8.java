package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_8 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_008() throws Exception
	{
		//search using different criteria
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.Login();
		aa.searchwithcriteriafields();
		aa.ValidateSearchResult();
		aa.Logout();
		aa.closeBrowser();
		Reporter.log("Eighth sanity test passed");
	

	}	
}