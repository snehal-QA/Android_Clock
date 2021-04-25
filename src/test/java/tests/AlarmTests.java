package tests;

import org.testng.annotations.Test;

import Android_Clock.DriverConfig;
import Pages_Elements.AlarmPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AlarmTests extends DriverConfig {
	
  AlarmPage alarmPage; 

  @BeforeMethod
  public void beforeMethod() {
	  alarmPage = new AlarmPage();  
  }

//  This test adds a static alarm with 5hours 30mins for the same day.
// Validates if alarm was successfully added by asserting the Alarm set text 
  @Test
  public void addAlarm_analog_validtime() {

	    alarmPage.gotoAlarmPage(); // Opens Alarm Tab
	    alarmPage.addAlarm(); //Click + button to add alarm
	    alarmPage.setHour();  // Clicks hour analog clock
	    alarmPage.setMinute(); //Clicks minutes analog clock
	    alarmPage.pressOk();   // Clicks ok 
	    alarmPage.ClickClockTab(); //Navigates to Clock tab
	    //Asserts if alarm is set in Clock tab
	    Assert.assertTrue(alarmPage.alarm_isenabled()); 
}

  
//This test adds alarm dynamically with hours & mins values passed from properties file  for the same day.
//Validates if alarm was successfully added by asserting the Alarm set text  
  @Test
  public void addAlarm_digital_validtime() {

	    alarmPage.gotoAlarmPage(); // Opens Alarm Tab
	    alarmPage.addAlarm();      //Click + button to add alarm
	    alarmPage.ClickAlarmKeyboard(); //Clicks Keyboard 
	    alarmPage.inputhours(prop.getProperty("hour")); // Adds hours from properties file.
	    alarmPage.inputmins(prop.getProperty("mins"));  // Adds minutes from properties file
	    alarmPage.pressOk();                            //Clicks ok 
	  //Asserts text in message contains expected text Alarm set  
		Assert.assertTrue( alarmPage.getNotification().contains("Alarm set")); 
}
  
// This test validates that an alarm is not set when entered incorrect time. Invalid Hours are passed through properties file 
  @Test
  public void set_invalid_time() {

	    alarmPage.gotoAlarmPage();       // Opens Alarm Tab
	    alarmPage.addAlarm();            //Click + button to add alarm
	    alarmPage.ClickAlarmKeyboard();  //Clicks Keyboard 
	    alarmPage.inputhours(prop.getProperty("invalid_hour")); // Adds hours from properties file.
	    alarmPage.pressOk();   //Clicks ok 
		Assert.assertEquals(alarmPage.error_msg(), "Enter a valid time"); //Asserts text in message contains expected text Alarm set

}
 
}
