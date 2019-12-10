package BusinessLayer;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ApplicationLayer.AppLayer;
import ExecutionLayer.TestSuiteRunner;

public class MT_Sanity_7 extends TestSuiteRunner {
	@Test
	public void MT_Sanity_007() throws Exception
	{
		//verify the Reset button  
		String TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		AppLayer aa = new AppLayer();
		aa.launchBrowser();
		aa.Login();
	    aa.blanksearch(false,false);
	    aa.Addmorefields();
	    aa.click_on_reset();
	    aa.blanksearch(false,false);
		aa.Logout();
		aa.closeBrowser();
		Reporter.log("Seventh sanity test passed");
	

	}	
}
