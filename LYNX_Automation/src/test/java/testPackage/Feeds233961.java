package testPackage;

import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class Feeds233961 extends BasePackage.LYNXBase {
	public Feeds233961() {
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
	public static void VerifyFeedsDropdown() throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedsDropdown Method called");
		//RelaunchFWTab(test);
		OpenUserPrfrncsFeeds(test);
		try {
			if (s.exists(GetProperty("Australia"))!=null) {
				s.find(GetProperty("Australia")).click();
				test.pass("Fastwire Preference options window loaded");
				Thread.sleep(1000);
			}
			else {
				test.fail("Fastwire Preference options window not loaded");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	public static void OpenUserPrfrncsFeeds(ExtentTest test) {
		test.log(com.aventstack.extentreports.Status.INFO,"OpenUserPrfrncsFeeds Method called");
		try {
				s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
				test.pass("Right Clicked Lynx Fastwire icon");
				Thread.sleep(4000);
				s.find(GetProperty("Preferences")).click();
				test.pass("Clicked Preferences icon");
				Thread.sleep(8000);
				if (s.exists(GetProperty("LynxPreferences"))!=null) {
					test.pass("Fastwire Preference options window loaded");
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire Preference options window not loaded");
				}
				if (s.exists(GetProperty("FWUsrPrfrncs"))!=null) {
					s.find(GetProperty("FWUsrPrfrncs")).click();
					test.pass("Found and clicked Fastwire-User Preferences Option");
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire-User Preferences Option not found");
				}
				if (s.exists(GetProperty("FltrFeeds"))!=null) {
					s.find(GetProperty("FltrFeeds")).click();
					test.pass("Found and clicked Filters-Feeds Option");
					Thread.sleep(1000);
				}
				else {
					test.fail("Filters-Feeds Option not found");
				}
				if (s.exists(GetProperty("EnblFltrOn"))!=null) {
				}
				else if(s.exists(GetProperty("EnblFltrOff"))!=null) {
					s.find(GetProperty("EnblFltrOff")).click();
					test.pass("Enabled Filters to select feeds");
					Thread.sleep(1000);
				}
				else {
					test.fail("Enable Filters option not found");
				}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
}