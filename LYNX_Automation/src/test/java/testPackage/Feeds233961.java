package testPackage;

import java.awt.dnd.DragSourceDropEvent;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class Feeds233961 extends BasePackage.LYNXBase {
	static Pattern pattern, pattern1, pattern2;
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
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void VerifyFeedsDropdown(String Country1, String Feed1,String Country2, String Feed2) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedsDropdown Method called");
		String Feed1On,Feed2On;
		RelaunchReopenFWTab(test,"Reopen");
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			Feed2On=SelectFeed(test,Country2,Feed2);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
				s.find(GetProperty("FeedsDdnSlctd")).click();
				test.pass("Feeds Dropdown shown as selected with Feeds");
				Thread.sleep(1000);
				if (s.exists(GetProperty(Feed1On),5)!=null) {
					test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
				}
				if (s.exists(GetProperty(Feed2On),5)!=null) {
					test.pass(Feed2+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed2+" feed not shown as selected in Feeds Dropdown");
				}
				
			}
			else{
				test.fail("Feeds Dropdown not shown as selected with Feeds");
			}
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			ReverseFeedSelection(test,Country2,Feed2);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	@Parameters({"param0","param1","param2","param3","param4"})
	@Test
	public static void VerifyFeedinHeadline(String Country1, String Feed1,String Country2, String Feed2, String mode) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedinHeadline Method called");
		RelaunchReopenFWTab(test,"Reopen");
		String Feed1On,Feed2On,Feed1Hdln,Feed2Hdln;
		int found1=0,found2=0;
		Feed1Hdln=Feed1+"HdlnFeed";
		Feed2Hdln=Feed2+"HdlnFeed";
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			Feed2On=SelectFeed(test,Country2,Feed2);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
				s.find(GetProperty("FeedsDdnSlctd")).click();
				test.pass("Feeds Dropdown shown as selected with Feeds");
				Thread.sleep(1000);
				if (s.exists(GetProperty(Feed1On),5)!=null) {
					test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
				}
				if (s.exists(GetProperty(Feed2On),5)!=null) {
					test.pass(Feed2+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed2+" feed not shown as selected in Feeds Dropdown");
				}
			}
			else{
				test.fail("Feeds Dropdown not shown as selected with Feeds");
			}
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			pattern2 = new Pattern(GetProperty(Feed2Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
				}	
				if(s.exists(pattern2,5)!=null) {
					found2=1;
				}
				if (found1==1 && found2==1) {
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			//if (s.exists(pattern,5)!=null) {
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines");
			}
			
			//if (s.exists(pattern,5)!=null) {
			if (found2==1) {
				test.pass(Feed2+" feed shown in Headlines");
			}
			else{
				test.fail(Feed2+" feed not shown in Headlines");
			}
			if (mode.equals("torn")) {
				test.log(com.aventstack.extentreports.Status.INFO,"Tearing the Fastwire tab");
				s.dragDrop(GetProperty("Fastwiretab"),GetProperty("Newtab"));
				Thread.sleep(4000);
				s.find(GetProperty("Date")).click();
				while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
					if(s.exists(pattern1,5)!=null) {
						found1=1;
					}	
					if(s.exists(pattern2,5)!=null) {
						found2=1;
					}
					if (found1==1 && found2==1) {
						break;
					}
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
				}
				//if (s.exists(pattern,5)!=null) {
				if (found1==1) {
					test.pass(Feed1+" feed shown in Headlines in torn out tab");
				}
				else{
					test.fail(Feed1+" feed not shown in Headlines in torn out tab");
				}
				
				//if (s.exists(pattern,5)!=null) {
				if (found2==1) {
					test.pass(Feed2+" feed shown in Headlines in torn out tab");
				}
				else{
					test.fail(Feed2+" feed not shown in Headlines in torn out tab");
				}
				s.find(GetProperty("FWTabCloseRed")).click();
				Thread.sleep(3000);
				RelaunchReopenFWTab(test,"Reopen");
			}
			else {
				test.log(com.aventstack.extentreports.Status.INFO,"Inside else");
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			ReverseFeedSelection(test,Country2,Feed2);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedRemoval(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRemoval Method called");
		RelaunchReopenFWTab(test,"Reopen");
		String Feed1On,Feed1Hdln;
		int found1=0,found2=0;
		Feed1Hdln=Feed1+"HdlnFeed";
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
				s.find(GetProperty("FeedsDdnSlctd")).click();
				test.pass("Feeds Dropdown shown as selected with Feeds");
				Thread.sleep(1000);
				if (s.exists(GetProperty(Feed1On),5)!=null) {
					test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
				}
			}
			else{
				test.fail("Feeds Dropdown not shown as selected with Feeds");
			}
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines");
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			RelaunchReopenFWTab(test,"Reopen");
			s.find(GetProperty("Date")).click();
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed still shown in Headlines");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedDeselection(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedDeselection Method called");
		RelaunchReopenFWTab(test,"Reopen");
		String Feed1On,Feed1Hdln;
		int found1=0;
		Feed1Hdln=Feed1+"HdlnFeed";
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
				s.find(GetProperty("FeedsDdnSlctd")).click();
				test.pass("Feeds Dropdown shown as selected with Feeds");
				Thread.sleep(1000);
				if (s.exists(GetProperty(Feed1On),5)!=null) {
					test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
				}
			}
			else{
				test.fail("Feeds Dropdown not shown as selected with Feeds");
			}
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines");
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			RelaunchReopenFWTab(test,"Reopen");
			s.find(GetProperty("Date")).click();
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed still shown in Headlines");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedReselection(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedReselection Method called");
		RelaunchReopenFWTab(test,"Reopen");
		String Feed1On,Feed1Hdln;
		int found1=0;
		Feed1Hdln=Feed1+"HdlnFeed";
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
				s.find(GetProperty("FeedsDdnSlctd")).click();
				test.pass("Feeds Dropdown shown as selected with Feeds");
				Thread.sleep(1000);
				if (s.exists(GetProperty(Feed1On),5)!=null) {
					test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
				}
				else{
					test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
				}
			}
			else{
				test.fail("Feeds Dropdown not shown as selected with Feeds");
			}
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines");
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Deselcting the Feeds");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(6000);
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed shown in Headlines");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines");
			}
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			RelaunchReopenFWTab(test,"Reopen");
			s.find(GetProperty("Date")).click();
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed still shown in Headlines");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedRelaunch(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRelaunch Method called");
		RelaunchReopenFWTab(test,"Reopen");
		String Feed1On,Feed1Hdln;
		int found1=0;
		Feed1Hdln=Feed1+"HdlnFeed";
		OpenUserPrfrncsFeeds(test);
		try {
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			//close open Feed Dropdown
			s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines");
			}
			RelaunchReopenFWTab(test,"Relaunch");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))!=null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines after Relaunch");
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines after Relaunch");
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncsFeeds(test);
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrOn")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			RelaunchReopenFWTab(test,"Reopen");
			s.find(GetProperty("Date")).click();
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed still shown in Headlines");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}

	
	public static void VerifyFeedinDDN(ExtentTest test ,String Feed1On, String Feed1) {
		try{
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.pass("Feeds Dropdown shown as selected with Feeds");
			Thread.sleep(1000);
			if (s.exists(GetProperty(Feed1On),5)!=null) {
				test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
			}
			else{
				test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
			}
		}
		else{
			test.fail("Feeds Dropdown not shown as selected with Feeds");
		}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
	public static void ReverseFeedSelection(ExtentTest test,String Country, String Feed) {
		String CntryFeedSlctd,FeedOn;
		CntryFeedSlctd=Country+"FeedSlctd";
		FeedOn=Feed+"On";
		//Reverse the selections made
		try {	
				s.find(GetProperty(CntryFeedSlctd)).click();
				test.pass("Selected "+Country+" Country Feed");
				Thread.sleep(3000);
				s.find(GetProperty(FeedOn)).click();
				test.pass("Unselected "+Feed+" feed filter turning it off");
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	public static String SelectFeed(ExtentTest test,String Country, String Feed) {
		Pattern pattern1,pattern2;
		String CntryFeedUnSlctd,CntryFeedSlctd,FeedOn,FeedOff;
		CntryFeedUnSlctd=Country+"Feed";
		CntryFeedSlctd=Country+"FeedSlctd";
		FeedOn=Feed+"On";
		FeedOff=Feed+"Off";
		try {
			if (s.exists(GetProperty(CntryFeedUnSlctd),5)!=null) {
				s.find(GetProperty(CntryFeedUnSlctd)).click();
				test.pass("Found and Selected "+Country+" Country");
				Thread.sleep(1000);
			}
			else if (s.exists(GetProperty(CntryFeedSlctd),5)!=null) {
				s.find(GetProperty(CntryFeedSlctd)).click();
				test.pass(Country+" Country already selected");
				Thread.sleep(1000);
			}
			else {
				test.fail("Unable to select "+Country+" Country");
			}
			pattern1 = new Pattern(GetProperty(FeedOn)).exact();
			pattern2 = new Pattern(GetProperty(FeedOff)).exact();
			//if (s.exists(GetProperty("SydnyOn"))!=null) {
			if (s.exists(pattern1,5)!=null) {
				test.pass(Feed+" feed already selected");
			}
			//else if (s.exists(GetProperty("SydnyOff"))!=null) {
			else if(s.exists(pattern2,5)!=null) {
				s.click(pattern2);
				test.pass("Selected "+Feed+" feed");
				Thread.sleep(1000);
			}
			else {
				test.fail(Feed+" feed not found");
			}
			return FeedOn;
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
			return FeedOn;
		}
//		finally {
//			return FeedOn;
//		}
	}
	
	public static void OpenUserPrfrncsFeeds(ExtentTest test) {
		test.log(com.aventstack.extentreports.Status.INFO,"OpenUserPrfrncsFeeds Method called");
		Pattern pattern1,pattern2;
		try {
				s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
				test.pass("Right Clicked Lynx Fastwire icon");
				Thread.sleep(4000);
				s.find(GetProperty("Preferences")).click();
				test.pass("Clicked Preferences icon");
				Thread.sleep(8000);
				if (s.exists(GetProperty("LynxPreferences"),5)!=null) {
					test.pass("Fastwire Preference options window loaded");
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire Preference options window not loaded");
				}
				if (s.exists(GetProperty("FWUsrPrfrncs"),5)!=null) {
					s.find(GetProperty("FWUsrPrfrncs")).offset(-70,0).click();
					//ClickonOccurence(GetProperty("FWUsrPrfrncsarw"),3);
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire-User Preferences Option not found");
				}
				if (s.exists(GetProperty("FltrFeeds"),5)!=null) {
					test.pass("Found and expanded Fastwire-User Preferences Option");
					s.find(GetProperty("FltrFeeds")).click();
					test.pass("Found and clicked Filters-Feeds Option");
					Thread.sleep(3000);
				}
				else {
					test.fail("Filters-Feeds Option not found");
				}
				pattern1 = new Pattern(GetProperty("EnblFltrOff")).exact();
				pattern2 = new Pattern(GetProperty("EnblFltrOn")).exact();
				//if(s.exists(GetProperty("EnblFltrOff"))!=null) {
				//s.find(GetProperty("EnblFltrOff")).click();
				if(s.exists(pattern1,5)!=null) {
					s.click(pattern1);
					test.pass("Enabled Filters to select feeds");
					Thread.sleep(1000);
				}
				//else if (s.exists(GetProperty("EnblFltrOn"))!=null) {
				else if (s.exists(pattern2,5)!=null) {
					test.pass("Enable Filters already on");
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