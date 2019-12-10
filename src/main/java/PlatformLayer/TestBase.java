package PlatformLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;



public class TestBase {
	
	public static   WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		
		try {
			prop =  new Properties();
			FileInputStream ip = new FileInputStream ("C:/Users/srivakun/eclipse-workspace/VnMyTrans/src/main/java/PlatformLayer/config.properties");
		
			prop.load(ip);
		} catch (FileNotFoundException e)
		
		{
			e.printStackTrace();
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
	
		
		} 
	
	public static void initalization() {
		
		
		String browsername= prop.getProperty("browser");
		if(browsername.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "C:/Users/srivakun/Downloads/chromedriver_win32/chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if (browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:/Users/srivakun/Downloads/geckodriver-v0.26.0-win64/");
			 driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
		
		public static void teardown() {
			
			driver.close();
			driver.quit();
		}
		
		
		public static void Loginmt() throws InterruptedException{
			
			WebElement username = driver.findElement(By.id("username"));
			
			Thread.sleep(5000);
			username.sendKeys("shrinabh");
			
			WebElement password = driver.findElement(By.id("password"));
			Thread.sleep(5000);
			password.sendKeys("Doit1234");
			
			WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"command\"]/div[4]/input"));
			Thread.sleep(5000);
			loginbtn.click();
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
		
		
		public void enter_Text(String locatorType, String value, String text, WebDriver driver) {
			By locator;
            locator = locatorValue(locatorType, value);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
	 }
		
		public void click_On_Button(String locatorType, String value, WebDriver driver) {
	      
	               By locator;
	               locator = locatorValue(locatorType, value);
	               WebElement element = driver.findElement(locator);
	               element.click();
	               System.out.println("Success");
      
	 }
		
		public String gettitle(WebDriver driver) {
			String str = driver.getTitle();
			
			return str;
		}
		
		public void check_element(String locatorType, String value, WebDriver driver) {
	      
	               By locator;
	               locator = locatorValue(locatorType, value);
	               WebElement element = driver.findElement(locator);
	               
	     
	 }
		
		
		public String captureScreen(String filename) {
		    String path;
		    try {
		        WebDriver augmentedDriver = new Augmenter().augment(driver);
		        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		        path = "C:\\Users\\srivakun\\eclipse-workspace\\VnMyTrans\\test-output\\Screenshots\\" + filename;
		        FileUtils.copyFile(source, new File(path)); 
		    }
		    catch(IOException e) {
		        path = "Failed to capture screenshot: " + e.getMessage();
		    }
		    return path;
		}
		
		public void NewDate() {
			 Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		       
		        cal.add(Calendar.DATE,-60);
		        String wantedDate = sdf.format(cal.getTime());

		    System.out.println("now date is " +wantedDate);
		}
	}


