package Android_Clock;

import Utility.basicUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class DriverConfig {
	
  protected static AppiumDriver driver;
  protected static Properties prop;
  protected static String dateTime;
  InputStream inputStream;
  basicUtilities utils;
  
  
  public DriverConfig() {
	  PageFactory.initElements(new AppiumFieldDecorator(driver),this);
  }
	
  
  @BeforeMethod
  public void beforeMethod() {

  }
  
  @AfterMethod
  public void afterMethod() {
 
  }
  
  @BeforeTest
  public void beforeTest() {
	   utils = new basicUtilities();
	   dateTime = utils.getDateTime();
	  
	   try {
		    prop = new Properties();
		    String propFileName="config.properties";
		    inputStream=getClass().getClassLoader().getResourceAsStream(propFileName);
		    prop.load(inputStream);
		    
		    /*  Read the device properties from config.properties file and set the related
		     *  desiredCapabilities 
		     *     */
		    
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", prop.getProperty("platformName"));
			desiredCapabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
			desiredCapabilities.setCapability("deviceName", prop.getProperty("deviceName"));
			desiredCapabilities.setCapability("automationName", prop.getProperty("automationName"));
		    desiredCapabilities.setCapability("appPackage", prop.getProperty("appPackage"));
			desiredCapabilities.setCapability("appActivity", prop.getProperty("appActivity"));
			desiredCapabilities.setCapability("avd", prop.getProperty("avd"));
			desiredCapabilities.setCapability("avdLaunchTimeout", prop.getProperty("avdLaunchTimeout"));
			
		    /*  Connecting to Appium Server (Config.properties>> AppiumURL=http://127.0.0.1:4723/wd/hub 
		     *     */
			
			URL url = new URL(prop.getProperty("AppiumURL"));
			
			driver = new AndroidDriver(url, desiredCapabilities);
			String sessionId = driver.getSessionId().toString();
			System.out.println("Session Id : " + sessionId);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			/*  Start Test execution video recording for screen.
		     *     */
			
			((CanRecordScreen) driver).startRecordingScreen(); 
		   
	   }
	   catch (Exception e) {
		   e.printStackTrace();
	   }
	  
//	    MobileElement alarm_icon = (MobileElement) driver.findElementByXPath("//rk[@content-desc=\"Alarm\"]/android.widget.TextView");
//		alarm_icon.click();
//		ArrayList<MobileElement> alarms = new ArrayList<>();
//		alarms = (ArrayList<MobileElement>) driver.findElementsById("com.google.android.deskclock:id/digital_clock");
//		if (alarms.size() > 0) {
//			for(MobileElement a : alarms) {
//				System.out.println(a.getText());
//				System.out.println("******************");
//				
//			}
//			
//		}	
  }
  
  /* Get AppiumDriver Object for Tests */
  public AppiumDriver getDriver() {
	  return driver;
  }
  
  /* Common synchronization with explicit wait for all page actions */
  public void waitForVisibility(MobileElement m) {
	  WebDriverWait wait = new WebDriverWait(driver,basicUtilities.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(m));
  }
  
  /* All below page element actions are validated for visibility on page and specific 
   * action is performed */
  public void click(MobileElement m) {
	  waitForVisibility(m);
	  m.click();
  }
  
  public void sendKeys(MobileElement m, String text) {
	  waitForVisibility(m);
	  m.sendKeys(text);
  }
  
  public String getText(MobileElement m) {
	  waitForVisibility(m);
	  return m.getText();
  }
  
  public boolean isEnabled(MobileElement m)
  {
	  waitForVisibility(m);
	  return m.isEnabled();
  }
  
 /* Getter for DateTime variable  */ 
 public String getDateTime() {
	 return dateTime;
 }
 
 /* Getter for Property (To send config.properties)  */ 
 public String getProp(String text) {
	 return prop.getProperty(text);
 }

  @AfterTest
  public void afterTest() {
	  /* Stop Video recording and save file at ./Media/Videos/Android_11 */
	  
	  String screenVideo = ((CanRecordScreen) driver).stopRecordingScreen(); 
			  
	  String videoPath = "Media" + File.separator + "Videos" + File.separator + prop.getProperty("platformName") 
	                    + "_" + prop.getProperty("platformVersion") ;
	  
	  File videoDir = new File(videoPath);
	  
	  if(!videoDir.exists()) {
		  videoDir.mkdirs();
	  }
	  
	  try {
	     FileOutputStream stream = new FileOutputStream(videoDir + File.separator  +  dateTime + ".mp4");
	     stream.write(Base64.decodeBase64(screenVideo));
	     
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	/* Quit driver at end of test */  
	  driver.quit();
  }

}
