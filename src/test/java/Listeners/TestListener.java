package Listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import Android_Clock.DriverConfig;

public class TestListener implements ITestListener {
	public void onTestFailure(ITestResult result)
	{
		if(result.getThrowable()!= null) {
			StringWriter sw= new StringWriter();
			PrintWriter pw= new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}
		
	 DriverConfig driver = new DriverConfig();
	 File file = driver.getDriver().getScreenshotAs(OutputType.FILE);
	 
	 //Properties prop = new Properties();
	 
	 String imagePath = "Media" + File.separator + "ScreenShots" + File.separator + driver.getProp("platformName") + "_" +
			 driver.getProp("platformVersion") + File.separator + driver.getDateTime() + ".png";
	 
	 String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
	 
	 
	 try {
		 FileUtils.copyFile(file, new File(imagePath));
		 Reporter.log("This is error ScreeShot");
		 Reporter.log("<a href='"+ completeImagePath + "'><img scr='"+ completeImagePath + "' height='100' width='100' /></a>");

	 } catch (IOException e) {		 
		 
		 e.printStackTrace();
	 }
	 
	}

}
