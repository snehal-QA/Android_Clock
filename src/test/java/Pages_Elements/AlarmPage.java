package Pages_Elements;

import Android_Clock.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlarmPage extends DriverConfig{
	
	/* Elements from page are identified and are formulated to use Page Objects
	 * */
	
	static final String H= prop.getProperty("hour");
	@AndroidFindBy(xpath="//rk[@content-desc=\"Alarm\"]/android.widget.TextView") private MobileElement alarm_icon;
	@AndroidFindBy(accessibility="Add alarm") private MobileElement add_alarm;
	@AndroidFindBy(accessibility="5") private MobileElement hour;
	@AndroidFindBy(accessibility="30") private MobileElement minutes;
	@AndroidFindBy(id="android:id/button1") private MobileElement ok;
	@AndroidFindBy(id="com.google.android.deskclock:id/snackbar_text") private MobileElement text_notify;
	@AndroidFindBy(accessibility="Switch to text input mode for the time input.") private MobileElement AlarmKeyboard;
	@AndroidFindBy(id="android:id/input_hour") private MobileElement sethour;
	@AndroidFindBy(id="android:id/input_minute") private MobileElement setmins;
	@AndroidFindBy(id="android:id/label_error") private MobileElement error;
	@AndroidFindBy(xpath="//rk[@content-desc=\"Clock\"]/android.widget.TextView") private MobileElement clock_icon;	
	@AndroidFindBy(id="com.google.android.deskclock:id/nextAlarmIcon") private MobileElement alarm_enabled;
	
	/* Methods to take action on various page elements.
	 * */
	public AlarmPage gotoAlarmPage() {
		click(alarm_icon);
		return this;
	}
	
	public AlarmPage addAlarm() {
		click(add_alarm);
		return this;
	}
	
	public AlarmPage setHour() {
		click(hour);
		return this;
	}
	
	public AlarmPage setMinute() {
		click(minutes);
		return this;
	}
	
	public AlarmPage pressOk() {
		click(ok);
		return this;
	}
	
	public String getNotification() {
		return getText(text_notify);
	}
	
	public AlarmPage ClickAlarmKeyboard() {
		click(AlarmKeyboard);
		return this;
	}
	public AlarmPage inputhours(String text) {
		sendKeys(sethour,text);
		return this;
	}
	public AlarmPage inputmins(String text) {
		sendKeys(setmins,text);
		return this;
	}
	public String error_msg() {
		return getText(error);
	}
	public boolean alarm_isenabled() {
		return isEnabled(alarm_enabled);
	}
	public AlarmPage ClickClockTab() {
		click(clock_icon);
		return this;
	}
	
}

