package PlatformLayer;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.Select;



public class Keyword {	
	
	
	
	
	/**
	 * 
	 * @param locatorType
	 * @param value
	 * @param text
	 * @param driver
	 */
	public void enter_Text(String locatorType, String value, String text, WebDriver driver) {
        try {
               By locator;
               locator = locatorValue(locatorType, value);
               WebElement element = driver.findElement(locator);
               element.clear();
               element.sendKeys(text);
        } catch (NoSuchElementException e) {
               System.err.format("No Element Found to enter text" + e);
        }
 }

	/**
	 * 
	 * @param locatorType
	 * @param value
	 * @param driver
	 */
	
    public void click_On_Button(String locatorType, String value, WebDriver driver) {
        try {
               By locator;
               locator = locatorValue(locatorType, value);
               WebElement element = driver.findElement(locator);
               element.click();
  }catch (NoSuchElementException e) {
               System.err.format("No Element Found to perform click" + e);
        }
 }

  

	
	/**
     * This method click the specified LINK.
     * 
     * @returns void
     */	

	public void clickLink(String objectProperty, WebDriver driver)
	{

		driver.findElement(By.xpath(objectProperty)).click();
		
	}

	
	/**
     * This method select the specified value from the DROPDOWN.
     * 
     * @returns void
     */
	public void selectDropDown(String locatorType,String value, String objectPropertyValue, WebDriver driver)
	{
		try{

			By locator;
			locator = locatorValue(locatorType, value);
			WebElement element = driver.findElement(locator);
			Select select  = new Select(element);
			select.selectByVisibleText(objectPropertyValue);
			}

	catch (NoSuchElementException e) {
	               System.err.format("No Element Found to perform click" + e);
	        }

		}
	
	/**
     * This method will wait for the specified object to load till the given time or up to the Selenium MAX timeout.
	 * @return 
     * 
     * @returns void
     */
	
	public boolean waitTillObjectPresent(String locatorType, String value, WebDriver driver)
	{
			
               By locator;
               locator = locatorValue(locatorType, value);
               WebElement element = driver.findElement(locator);
              return element.isDisplayed();
        }
	

	
	/**
     * This method will check for the object present on the web page.
     * If present then returns True else False
     * 
     * @returns boolean
     */
	public boolean isObjectPresent(String objectProperty, WebDriver driver)
	{
		
		driver.findElement(By.xpath(objectProperty)).isDisplayed();
		return true;
		
	}
	
	/**
     * This method will check for the object visible on the web page.
     * If visible then returns True else False
     * 
     * @returns boolean
     */	
	public boolean isObjectVisible(String objectProperty, WebDriver driver)
	{
		
		boolean visible = driver.findElement(By.xpath(objectProperty)).isDisplayed();
		return visible;
		
	}
	
	/**
     * This method will give the TEXT in the Specified cell of the Table.
     * Table class value need to pass.
     * 
     * @return String
     */	
	public String getTableCellData(String objectProperty, WebDriver driver)
	{
		String value = driver.findElement(By.xpath(objectProperty)).getText();
		return value;
		
	}
	
	/**
     * This method will wait for the given time or up to the Selenium MAX timeout.
     * 
     * @returns void
     */	
	public void wait(int timeOut, WebDriver driver)
	{
		
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		
	}
	
	/**
     * This method click the specified Radio Button.
     * 
     * @returns void
     */	
	public void selectRadioButtonusingxpath(String objectProperty, WebDriver driver)
	{
		
		driver.findElement(By.xpath(objectProperty)).isSelected();
		
	}
		
	public void selectRadioButtonusingid(String objectProperty, WebDriver driver)
	{
		
		driver.findElement(By.id(objectProperty)).isSelected();
		
	}
		/**
     * This method gets the text.
     * 
     * @returns void
     */	
	public String getText(String objectProperty, WebDriver driver)
	{
		
		String text = driver.findElement(By.xpath(objectProperty)).getText();
		return text;
		
	}	
	
/**
 * This method Clicks on a link, button, check box or radio button using the X, Y co-ordinates. 
 * 
 * @returns void
 */
	public void clickAt(String objectProperty, WebDriver driver)
	{
	
		driver.findElement(By.xpath(objectProperty)).click();
	
	}

/**
 * This method switches between more than one Tabs
 * 
 */
	
	public boolean VerifyHelp(String objectProperty, WebDriver driver)
	{
		
		boolean b = driver.findElement(By.xpath(objectProperty)).isDisplayed();
		return b;
		
	}
	

/**
 * 
 * This method clears the data in text box
 */
	public void cleartext(String objectProperty, WebDriver driver)
	{
		
		driver.findElement(By.xpath(objectProperty)).clear();
		
	}

/**
 * 
 * This method checks if the data exists in the text box. Needed specifically for Edit invoice case
 * @return 
 */
	
	public boolean isEmpty(String objectProperty, WebDriver driver)
	{
		String val = driver.findElement(By.xpath(objectProperty)).getText();
		
		if(val.equals("")){
			return true;
		}else
		{
			return false;
		}
		
		
	}
	

	public int tablerowcount(String objectProperty, WebDriver driver)
	{
		
			List<WebElement> rows = driver.findElements(By.xpath(objectProperty));
			return rows.size();
			
		
	}
	
	
	public void actionOnPopup(String parentwindow, String childwindow, WebDriver driver)
	{
	
				
	}
	
	public By locatorValue(String locatorTpye, String value) {
        By by;
        switch (locatorTpye) {
        case "id":
               by = By.id(value);
               break;
        case "name":
               by = By.name(value);
               break;
        case "xpath":
               by = By.xpath(value);
               break;
        case "css":
               by = By.cssSelector(value);
               break;
        case "linkText":
               by = By.linkText(value);
               break;
        case "partialLinkText":
               by = By.partialLinkText(value);
        case "className":
        	   by = By.className(value);
        	   break;
        default:
               by = null;
               break;
        }
        return by;
 }

	
	  

		public void clear_text(String locatorType, String value, WebDriver driver) {
	        try {
	               By locator;
	               locator = locatorValue(locatorType, value);
	               WebElement element = driver.findElement(locator);
	               element.clear();
	        } catch (NoSuchElementException e) {
	               System.err.format("No Element Found to enter text" + e);
	        }
	 }

		
		public  void DateandTime() throws ParseException{

		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SS");
		    String strDate = sdf.format(cal.getTime());
		    System.out.println("Current date in String Format: "+strDate);

		    SimpleDateFormat sdf1 = new SimpleDateFormat();
		    sdf1.applyPattern("dd.MM.yyyy HH:mm:ss.SS");
		    Date date = (Date) sdf1.parse(strDate);
		    System.out.println("Current date in Date Format: "+date);

		}
		
		public boolean existsElement(String locatorType, String value, WebDriver driver) {
		    try {
		    	By locator;
	               locator = locatorValue(locatorType, value);
	               WebElement element = driver.findElement(locator);
		    } catch (NoSuchElementException e) {
		        return false;
		    }
		    return true;
		}
}
