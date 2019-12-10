package ApplicationLayer;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import PlatformLayer.DateTime;
import PlatformLayer.Keyword;
import PlatformLayer.TestBase;

/**
 * This Class contains method which are Application Functionality.
 *
 *
 */

public class AppLayer {
	static WebDriver driver1 = null;
	Keyword kw = new Keyword();
	DateTime dt = new DateTime();
	TestBase tb = new TestBase();
	boolean refresh = false;
	String invnumber = null;
	boolean isinvnumber = false;
	boolean isesentdate = false;
	String sentdate = null;
	private WebDriver webdriver;

	public void launchBrowser() {
		tb.initalization();

	}

	public void Login() throws Exception {

		try {
			driver1 = tb.driver;

			WebElement username = driver1.findElement(By.id("username"));

			Thread.sleep(2000);
			tb.enter_Text("id", "username", tb.prop.getProperty("uname"), driver1);

			Thread.sleep(2000);
			tb.enter_Text("id", "password", tb.prop.getProperty("pword"), driver1);

			Thread.sleep(2000);

			// LogIn

			// driver1.findElement(By.name("LogIn")).click();
			tb.click_On_Button("name", "LogIn", driver1);

			String Currenttitle = tb.gettitle(driver1);
			Assert.assertEquals(Currenttitle, tb.prop.getProperty("homepagetitle"));

			Thread.sleep(5000);

			Reporter.log(driver1.getTitle());
			Reporter.log(driver1.getTitle());

			Reporter.log("Sign In Successful |Title matched |home page displayed | ");

		} catch (Exception e) {
			Reporter.log("User could not login successfully | " + AppLayer.extractMsg(e, e.getMessage()));

			Assert.fail("Login Unsuccessful");

		}
	}

	public void Logout() throws InterruptedException {

		try {
			Thread.sleep(3000);

			tb.click_On_Button("name", "Logout", driver1);
			Thread.sleep(3000);

			Reporter.log("User logged out successfully|");
		}

		catch (Exception e) {
			Reporter.log("User could not logout  | " + AppLayer.extractMsg(e, e.getMessage()));
			Assert.fail("Logout Unsuccessful");
		}

	}

	public void closeBrowser() {
		tb.teardown();

	}

	public void CheckSearchCriteriafields() {

		try {
			// check if Allinvoices dropdown is selected or not
			List<WebElement> rad_btn = driver1.findElements(By.name("serviceType"));

			if (rad_btn.get(0).isSelected()) {
				// check all fields
				tb.check_element("id", "invoice_number_textbox", driver1);
				tb.check_element("id", "receiver_name_textbox", driver1);
				tb.check_element("id", "sender_name_textbox", driver1);
				tb.check_element("id", "invoice_type_select", driver1);
				tb.check_element("id", "invoice_status_select", driver1);
				tb.check_element("id", "invoice_from_date_textbox", driver1);
				tb.check_element("id", "invoice_to_date_textbox", driver1);
				tb.check_element("id", "due_from_date_textbox", driver1);
				tb.check_element("id", "due_to_date_textbox", driver1);
				tb.check_element("id", "sent_from_date_textbox", driver1);
				tb.check_element("id", "sent_to_date_textbox", driver1);

				tb.captureScreen("homepage.png");
				Reporter.log("All Search Criteria Fields are present |");

			}

			else {
				Assert.fail("This is not HomePage |");
			}

		} catch (Exception e) {
			Reporter.log("All Search Criteria Fields could not be found |" + AppLayer.extractMsg(e, e.getMessage()));
			Assert.fail("All Search Criteria Fields could not be found |");

		}

	}

	public void Addmorefields() {
		try {
			Select oSelect = new Select(driver1.findElement(By.id("s-Category_1-options")));
			List<WebElement> elementCount = oSelect.getOptions();

			System.out.println("count is" + elementCount);
			int iSize = elementCount.size();

			System.out.println("size is" + iSize);

			for (int i = 0; i < iSize; i++) {
				String sValue = elementCount.get(i).getText();
				elementCount.get(i).click();
				System.out.println(sValue);
			}

			tb.check_element("id", "receiver_business_id_textbox", driver1);
			tb.check_element("id", "invoice_from_total_textbox", driver1);
			tb.check_element("id", "invoice_to_total_textbox", driver1);
			tb.check_element("id", "invoice_recipient_textbox", driver1);
			tb.check_element("id", "partner_eaddress_textbox", driver1);
			tb.check_element("id", "currencyDropdownlist", driver1);

			tb.captureScreen("AddedFields.png");

			Reporter.log("Fields added from addfield dropdown |");

		} catch (Exception e) {

			Reporter.log("Fields could not be added from addfield dropdown |" + AppLayer.extractMsg(e, e.getMessage()));
			Assert.fail("Fields could not be added |");
		}

	}

	public void blanksearch(boolean isinvnumber, boolean issentdate) throws InterruptedException {
		if (isinvnumber == false && issentdate == false) {

			// basic search
			Thread.sleep(4000);
			tb.enter_Text("id", "sent_from_date_textbox", DateTime.getNextDate(-89), driver1);

			tb.click_On_Button("id", "invoice_number_textbox", driver1);

			tb.click_On_Button("name", "searchresult", driver1);

			List<WebElement> cols = driver1.findElements(By.id("tablePagination"));

			WebElement failedsearch = driver1.findElement(By.cssSelector("div.error-des"));

			boolean errorfield = failedsearch.isDisplayed();

			System.out.println("heyyyy" + errorfield);
			System.out.println("row count is" + cols.size());

			if (errorfield == false) {
				if ((cols.size() > 0)) {
					for (int i = 0; i <= cols.size(); i++) {
						if (cols.get(i).getText().contains("found, displaying")) {
							tb.captureScreen("SearchResult.png");
							Reporter.log("search result displayed|");

						}
						break;
					}

				}

				else if (cols.size() == 0) {
					Reporter.log("No Result found|");
				}

			}

			else {
				Reporter.log("Search not available|");
				Assert.fail("Search not available");

			}

		}

		else if (isinvnumber == true && issentdate == false) {
			// search with invoice number
			tb.enter_Text("id", "sent_from_date_textbox", DateTime.getNextDate(-89), driver1);

			tb.enter_Text("id", "invoice_number_textbox", invnumber, driver1);

			tb.click_On_Button("id", "invoice_number_textbox", driver1);

			tb.click_On_Button("name", "searchresult", driver1);

			List<WebElement> cols = driver1.findElements(By.id("tablePagination"));

			WebElement failedsearch = driver1.findElement(By.cssSelector("div.error-des"));

			boolean errorfield = failedsearch.isDisplayed();

			System.out.println("heyyyy" + errorfield);
			System.out.println("row count is" + cols.size());

			if (errorfield == false) {
				if ((cols.size() > 0)) {
					for (int i = 0; i <= cols.size(); i++) {
						if (cols.get(i).getText().contains("found, displaying")) {
							tb.captureScreen("SearchResult.png");

							Reporter.log("search result displayed using Invoice number|");

						}
						break;
					}

				}

				else if (cols.size() == 0) {
					Reporter.log("No Result found|");
				}

			}

			else {
				Reporter.log("Search not available|");
				Assert.fail("Search not available");

			}
		}

		else {

// search with sent date

			tb.enter_Text("id", "sent_from_date_textbox", sentdate, driver1);
			tb.enter_Text("id", "sent_to_date_textbox", sentdate, driver1);

			tb.click_On_Button("id", "invoice_number_textbox", driver1);

			tb.click_On_Button("name", "searchresult", driver1);

			List<WebElement> cols = driver1.findElements(By.id("tablePagination"));

			WebElement failedsearch = driver1.findElement(By.cssSelector("div.error-des"));

			boolean errorfield = failedsearch.isDisplayed();

			System.out.println("heyyyy" + errorfield);
			System.out.println("row count is" + cols.size());

			if (errorfield == false) {
				if ((cols.size() > 0)) {
					for (int i = 0; i <= cols.size(); i++) {
						if (cols.get(i).getText().contains("found, displaying")) {
							tb.captureScreen("SearchResult.png");
							Reporter.log("search result displayed using set date criteria|");

						}
						break;
					}

				}

				else if (cols.size() == 0) {
					Reporter.log("No Result found|");
				}

			}

			else {
				Reporter.log("Search not available|");
				Assert.fail("Search not available");

			}
		}

	}

	public void click_on_reset() {
		try {
			// click on reset form button
			tb.click_On_Button("xpath", "//a[@class='btn btn-info mr-1']/span", driver1);
			Reporter.log("Reset button clicked |");
		}

		catch (Exception e) {
			Reporter.log("Reset button functionality not working " + AppLayer.extractMsg(e, e.getMessage()));
			Assert.fail("Reset failed");
		}

	}

	public void searchwithcriteriafields() throws InterruptedException {

		Thread.sleep(4000);
		tb.enter_Text("id", "sent_from_date_textbox", DateTime.getNextDate(-89), driver1);

		tb.click_On_Button("id", "invoice_number_textbox", driver1);

		tb.click_On_Button("name", "searchresult", driver1);

		List<WebElement> cols = driver1.findElements(By.id("tablePagination"));

		WebElement failedsearch = driver1.findElement(By.cssSelector("div.error-des"));

		boolean errorfield = failedsearch.isDisplayed();

		System.out.println("heyyyy" + errorfield);
		System.out.println("row count is" + cols.size());

		if (errorfield == false) {
			if ((cols.size() > 0)) {
				for (int i = 0; i <= cols.size(); i++) {
					if (cols.get(i).getText().contains("found, displaying")) {
						tb.captureScreen("SearchResult.png");

						List<WebElement> cols1 = driver1.findElements(By.id("search_items_table"));
						System.out.println("now search result size is" + cols1.size());
						// String sCellValue =
						// driver1.findElement(By.xpath("//*[@id=\"search_items_table\"]/tbody/tr[2]/tr[8]")).getText();
						String sCellValue = driver1
								.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td[9]")).getText();
						System.out.println("cell value is " + sCellValue);

						if (sCellValue.equals("SUCCESS")) {
							invnumber = driver1.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td[3]"))
									.getText();
							System.out.println("invoice number is" + invnumber);
							Reporter.log("Invoice number fetched from the search Result|");
							blanksearch(true, false);

						} else {
							sentdate = driver1.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td[1]"))
									.getText();
							System.out.println("Sent date search criteria is" + sentdate);
							Reporter.log("Invoice sent date  fetched from the search Result|");
							blanksearch(true, true);
						}

					}
					break;
				}

			}

			else if (cols.size() == 0) {
				Reporter.log("No Result found|");
			}

		}

		else {
			Reporter.log("Search not available|");
			Assert.fail("Search not available");

		}

	}

	public void clickoninvoice() throws InterruptedException {
		Thread.sleep(4000);
		tb.enter_Text("id", "sent_from_date_textbox", DateTime.getNextDate(-89), driver1);

		tb.click_On_Button("id", "invoice_number_textbox", driver1);

		tb.click_On_Button("name", "searchresult", driver1);

		List<WebElement> cols = driver1.findElements(By.id("tablePagination"));

		WebElement failedsearch = driver1.findElement(By.cssSelector("div.error-des"));

		boolean errorfield = failedsearch.isDisplayed();

		System.out.println("row count is" + cols.size());

		if (errorfield == false) {
			if ((cols.size() > 0)) {
				for (int i = 0; i <= cols.size(); i++) {
					if (cols.get(i).getText().contains("found, displaying")) {
						tb.captureScreen("SearchResult.png");

						List<WebElement> cols1 = driver1.findElements(By.id("search_items_table"));
						System.out.println("now search result size is" + cols1.size());

						String sCellValue = driver1
								.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td[9]")).getText();

						System.out.println("cell value is " + sCellValue);

						List rowcountdetails = driver1.findElements(By.xpath("/html/body/div[2]/table[2]/tbody/tr"));

						int rowcount = rowcountdetails.size();

						System.out.println("row count details " + rowcount);

						if (sCellValue.equals("SUCCESS")) {

							Thread.sleep(3000);

							for (i = 2; i <= rowcount; i++) {

								WebElement invlink = driver1
										.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[" + i + "]/td[3]"));
								invnumber = invlink.getText();

								WebElement clickableinvnumber = driver1
										.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[" + i
												+ "]/td/button[contains(text(), invnumber)]"));

								System.out.println("invoice number is" + invnumber);
								if (invnumber != null) {

									JavascriptExecutor js = (JavascriptExecutor) driver1;
									// use executeScript() method and pass the arguments
									// Here i pass values based on css style. Yellow background color with solid red
									// color border.
									js.executeScript(
											"arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
											invlink);
									Thread.sleep(2000);

									js.executeScript("arguments[0].click();", clickableinvnumber);
									Thread.sleep(4000);

									System.out.println("current url is " + driver1.getCurrentUrl());
									Reporter.log("Invoice Number clicked  |");
									break;

								} else {
									System.out.println("invoicve number link not found");
								}

							}

						}

					}
					break;
				}

			}

			else if (cols.size() == 0) {
				Reporter.log("No Result found|");
			}

		}

		else {
			Reporter.log("Search not available|");
			Assert.fail("Search not available");

		}

	}

	public void checkinvoicedetails() throws InterruptedException {

		String Invoicedetails_url = driver1.getCurrentUrl();

		// click on Attachments
		tb.click_On_Button("id", "downloads", driver1);

		// click on message(xmls)
		tb.click_On_Button("id", "message-xmls", driver1);

		Thread.sleep(3000);

		Assert.assertEquals(Invoicedetails_url, "http://10.32.111.238:12090/mytransactions/invoicedetails");
		Reporter.log("Invoice details found |");

	}

	public static String extractMsg(Exception e, String input) {
		if (input == null) {
			input = e.getLocalizedMessage();
			if (input == null) {
				System.out.println("Inner: " + e.getStackTrace()[0]);
				return e.getClass().toString();
			}
		}
		return input.substring(0, input.indexOf("(Session info"));
	}

	public void ValidateSearchResult() throws InterruptedException {

		Thread.sleep(3000);
		List<WebElement> cols1 = driver1.findElements(By.id("search_items_table"));
		System.out.println("now search result size is::::" + cols1.size());

		String sCellValue = driver1.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td[9]")).getText();

		System.out.println("cell value is :::" + sCellValue);

		List rowcountdetails = driver1.findElements(By.xpath("/html/body/div[2]/table[2]/tbody/tr"));

		int rowcount = rowcountdetails.size();

		System.out.println("row count details ::: " + rowcount);

		for (int j = 2; j <= rowcount; j++) {

			List<WebElement> listOfElements = driver1
					.findElements(By.xpath("/html/body/div[2]/table[2]/tbody/tr[" + j + "]/td[3]"));

			String textofelement = listOfElements.get(0).getText();

			System.out.println("text of element is :: " + textofelement);

			System.out.println("invoice number is ::" + invnumber);

			if (!invnumber.equals(textofelement)) {

				Reporter.log("Search result is incorrect , does not matches with input search Criteria |");
				Assert.fail();
				break;
			}
			
			else {
				Reporter.log("Search Result matches with search criteria |");
			}

		}
	
		
	}
}