package testPackage;

import java.io.IOException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.OCR;
import org.testng.IDynamicGraph.Status;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Headline_Alarm extends BasePackage.LYNXBase {
	public Headline_Alarm() {
		super();
	}
	@BeforeClass
	public void setup(){
	        extent = BasePackage.LYNXBase.getInstance();
	}
	@AfterTest
	public void flushReportData() {
		extent.flush();
	}
	@Test
	public static void Login() throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"Login Method called");
		
		Runtime runtime = Runtime.getRuntime();     //getting Runtime object
		try
        {	runtime.exec("taskkill /F /IM LYNX.exe");
            Thread.sleep(2000);
            lynxapp.open();
			
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		Thread.sleep(10000);
		if (s.wait(GetProperty("iPass"),30) != null) {
			
			test.pass("Launched LYNX Application");
			s.type(GetProperty("iPass"), LYNXProp.getProperty("tPass"));
			test.pass("Provided User name and Password");
		} else {
			test.fail("Password field doesnot exist");
		}
		if (s.exists(GetProperty("iSignOn")) != null) {
			s.find(GetProperty("iSignOn")).click();
			test.pass("Clicked on Sign on button");
		} else {
			test.fail("Sign on button doesnot exist");
		}
		s.wait(GetProperty("Home"),50);
		Thread.sleep(5000);
		if(s.exists(GetProperty("Home"))!=null ) {
			test.pass("Successfully logged into Fastwire");
		}
		else {
			test.fail("Unable to login to Fastwire");
		}
		s.keyDown(Key.CTRL);
		s.keyDown(Key.SHIFT);
		s.type("F");
		s.keyUp(Key.CTRL);
		s.keyUp(Key.SHIFT);
		Thread.sleep(4000);
	}
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public void ValidateAddAlarm(String Alarmname,String Keyword1,String Keyword2,String Sourcenm) {
		//test = extent.createTest(BasePackage.MainRunner.TestID, BasePackage.MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"ValidateAddAlarm Method called");
		try {
			s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
			test.pass("Right Clicked Fastwire Preferences icon");
			Thread.sleep(4000);
			s.find(GetProperty("Fastwire_Preferences")).click();
			test.pass("Clicked Fastwire Preferences icon");
			Thread.sleep(10000);
			s.find(GetProperty("Menu_FWPrfrncs")).click();
			test.pass("Clicked Menu Preferences");
			Thread.sleep(5000);
			if (s.exists(GetProperty("HdlnAlrms"))!=null) {
				s.find(GetProperty("HdlnAlrms")).click();
				test.pass("Clicked Headline Alarms link");
				Thread.sleep(1000);
			}
			else if(s.exists(GetProperty("HdlnAlrmsred"))!=null) {
				s.find(GetProperty("HdlnAlrmsred")).click();
				test.pass("Clicked Headline Alarms link");
				Thread.sleep(1000);
			}
			else {
				test.fail("Headline Alarms link not found");
			}
			s.find(GetProperty("AddHdlnAlrm")).click();
			test.pass("Clicked Add Headline Alarms link");
			Thread.sleep(4000);
			s.type(GetProperty("AlarmName"), Alarmname);
			test.pass("Entered Alarm Name");
			s.find(GetProperty("AlarmKeywords")).click();
			Thread.sleep(2000);
			s.type(GetProperty("AlarmKeywords"), Keyword1);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			Thread.sleep(3000);
			s.find(GetProperty("AlarmKeywordEntered")).click();
			s.type(GetProperty("AlarmKeywordEntered"), Keyword2);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			Thread.sleep(3000);
			test.pass("Entered Keywords for Alarms");
			while(s.exists(GetProperty("EndOfDownScroll"))!=null) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
				if(s.exists(GetProperty("HdlnAlrmSrcs"))!=null) {
					break;
				}
			}
			if (s.exists(GetProperty("HdlnAlrmSrcs"))==null) {
				test.fail("Source text box not found");
			}
			else
			{	
				Thread.sleep(2000);
				//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
				s.type(GetProperty("HdlnAlrmSrcs"), Sourcenm);
				test.pass("Source text box found and value entered");
				Thread.sleep(2000);
				if(s.exists(GetProperty("BSESourceList"))!=null ) {
					test.pass("Matching Source value list found");
					Thread.sleep(6000);	
					s.find(GetProperty("SourceCheckBox")).click();
					Thread.sleep(3000);
					s.find(GetProperty("AddSelected")).click();
					Thread.sleep(5000);
					test.pass("Added a Source");
					Thread.sleep(5000);
					s.find(GetProperty("BGColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("BGSlctclr")).click();
					Thread.sleep(3000);
					test.pass("Selected a background color");
					s.find(GetProperty("TextColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("TxtSlctClr")).click();
					test.pass("Selected a text color");
					Thread.sleep(3000);
					s.find(GetProperty("CreateAlarm")).click();
					test.pass("Clicked on CreateAlarm Button");
					if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
						test.pass("Alarm Created Successfully");
					}
					else {
						test.fail("Unable to find newly created Alarm");
					}
				}
				else {
					test.fail("Matching Source value list not found");
				}
			}
			s.find(GetProperty("FWTabClose")).doubleClick();
			s.find(GetProperty("FWTabClose")).doubleClick();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
}
