Application > Native Built-in Clock Application (Available on all Andriod Emulators)>> com.google.android.deskclock

Setup:

	1. Appium Server (Config.properties>> AppiumURL=http://127.0.0.1:4723/wd/hub (defaut ip & port); Incase using different ip & port; adjust accordngly under Config.properties>>AppiumURL)
	2. Android Studio
	3. Create AVD (Config. properties>> avd=Nexus_4_API_30 ; please add name of avd you created)
	

App Testing Framework: 

	>Appium (Page Object Model)>>Package: src/test/java/Pages_Elements
	>Language: Java (TestNG) >> src/main/java/TestNG.xml
	>Gradle: Dependancies>> JavaClient, testng
	>Screenshots on testfailure ./Media/Screenshots/Andriod_11
	>Videos recordings for the test execution from first-last testcase ./Media/Screenshots/Andriod_11
	>>TestNG builtin reports >>Emailable reports available under ./testoutput/emailablereport.html (added screen-shot on failure)
	
Framework Walkthrough:

    1. src/test/java>> DriverConfig.java (TestNG class) >>(Base Class)
    		>> Driver is initialized in Before test; desired capabilities needed are derived from Config.properties file.
    		>> Driver is quit in After test 
    		>> Functions are written to perform actions and are tied first with Synchronization (Explicit Wait: until visibility of element)
    	2. src/test/java/Pages_Element:
    	    >> Elements from page are identified and are formulated to use Page Objects
    	3. src/test/java/tests/AlarmTest.java
    		>> Tests: 
			> Verify Analog alarm added: Assertion to validate alarm icon is enabled on Clock tab
			> Verify Digital Alarm is added by passing Dynamic values from Properties file: Assertion on Message alarm is set 
			> Verify Invalid time does not add alarm >> Dynamic invalid is passed from Properties file: Assertion on Message Enter a 	    valid time
	4. src/main/java>> TestNG.xml
		>> Suite/ tests under AlarmTest.java >> RightClick Run as TestNG suite