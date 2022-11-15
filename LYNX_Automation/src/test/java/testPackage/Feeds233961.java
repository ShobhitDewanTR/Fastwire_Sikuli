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
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedsDropdown Method begin");
		String Feed1On,Feed2On;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Feeds");
			Feed1On=SelectFeed(test,Country1,Feed1);
			Feed2On=SelectFeed(test,Country2,Feed2);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			VerifyFeedinDDN(test,Feed2On,Feed2);
			//close open Feed Dropdown
			//s.find(GetProperty("FeedsDdnSlctd")).click();
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			ReverseFeedSelection(test,Country2,Feed2);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedsDropdown method end");
		}
	}
	@Parameters({"param0","param1","param2","param3","param4"})
	@Test
	public static void VerifyFeedinHeadline(String Country1, String Feed1,String Country2, String Feed2, String mode) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedinHeadline Method begin");
		String Feed1On,Feed2On,Feed1Hdln,Feed2Hdln;
		int found1=0,found2=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			Feed1Hdln=Feed1+"HdlnFeed";
			Feed2Hdln=Feed2+"HdlnFeed";
			OpenUserPrfrncs(test,"Feeds");
			Feed1On=SelectFeed(test,Country1,Feed1);
			Feed2On=SelectFeed(test,Country2,Feed2);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			VerifyFeedinDDN(test,Feed2On,Feed2);
			//close open Feed Dropdown
			//s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			pattern2 = new Pattern(GetProperty(Feed2Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
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
				Thread.sleep(8000);
				found1=0;
				found2=0;
				s.find(GetProperty("Date")).click();
				Thread.sleep(7000);
				Pattern scrollpatrn;
				scrollpatrn=new Pattern(GetProperty("EOHdlnScroll")).exact();
				while(s.exists(scrollpatrn)==null ) {
					System.out.println("Inside While");
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
				System.out.println("Found1: "+found1 +" Found2: "+found2);
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
			
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			ReverseFeedSelection(test,Country2,Feed2);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedinHeadline method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedRemoval(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRemoval Method begin");
		String Feed1On,Feed1Hdln;
		int found1=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			Feed1Hdln=Feed1+"HdlnFeed";
			OpenUserPrfrncs(test,"Feeds");
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			//close open Feed Dropdown
			//s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
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
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
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
			pattern = new Pattern(GetProperty("FeedsDdnSlctd")).exact();
			if (s.exists(pattern,5)!=null) {
				test.fail(Feed1+" feed still shown in Feed Dropdown");
			}
			else{
				test.pass(Feed1+" feed not shown in Feed Dropdown");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRemoval method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedDeselection(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedDeselection Method begin");
		String Feed1On,Feed1Hdln;
		int found1=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			Feed1Hdln=Feed1+"HdlnFeed";
			OpenUserPrfrncs(test,"Feeds");
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
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
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
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
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
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedDeselection method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyFeedReselection(String Country1, String Feed1) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedReselection Method begin");
		String Feed1On,Feed1Hdln;
		int found1=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			Feed1Hdln=Feed1+"HdlnFeed";
			OpenUserPrfrncs(test,"Feeds");
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
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
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
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
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
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedReselection method end");
		}
	}
	
	@Parameters({"param0","param1","param2"})
	@Test
	public static void VerifyFeedRelaunchReopen(String Country1, String Feed1, String Option) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRelaunch Method begin");
		String Feed1On,Feed1Hdln;
		int found1=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			Feed1Hdln=Feed1+"HdlnFeed";
			OpenUserPrfrncs(test,"Feeds");
			Feed1On=SelectFeed(test,Country1,Feed1);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyFeedinDDN(test,Feed1On,Feed1);
			//close open Feed Dropdown
			//s.find(GetProperty("FeedsDdnSlctd")).click();
			pattern1 = new Pattern(GetProperty(Feed1Hdln)).exact();
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
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
			
			RelaunchReopenFWTab(test,Option);
			VerifyFeedinDDN(test,Feed1On,Feed1);
			s.find(GetProperty("Date")).click();
			while(s.exists(GetProperty("EOHdlnScroll"))==null ) {
				if(s.exists(pattern1,5)!=null) {
					found1=1;
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			if (found1==1) {
				test.pass(Feed1+" feed shown in Headlines after "+Option);
			}
			else{
				test.fail(Feed1+" feed not shown in Headlines after "+Option);
			}
			//Reverse the selections made
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Feeds");
			ReverseFeedSelection(test,Country1,Feed1);
			pattern = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
			test.pass("Enable Filters turned off");
			s.click(pattern);
			Thread.sleep(4000);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Reversed the changes made and saved");
			RelaunchReopenFWTab(test,"Reopen");
			s.find(GetProperty("Date")).click();
			if(s.exists(pattern1,5)!=null) {
				test.fail(Feed1+" feed still shown in Headlines after unselecting feeds");
			}
			else{
				test.pass(Feed1+" feed not shown in Headlines after unselecting feeds");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedRelaunch method end");
		}
	}

	
	public static void VerifyFeedinDDN(ExtentTest test ,String Feed1On, String Feed1) {
		try{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedinDDN method begin");
			if (s.exists(GetProperty("FeedsDdnSlctd"),5)!=null) {
			s.find(GetProperty("FeedsDdnSlctd")).click();
			test.pass("Feeds Dropdown shown as selected with Feeds");
			Thread.sleep(4000);
			if (s.exists(GetProperty(Feed1On),5)!=null) {
				test.pass(Feed1+" feed shown as selected in Feeds Dropdown");
			}
			else{
				test.fail(Feed1+" feed not shown as selected in Feeds Dropdown");
			}
			s.find(GetProperty("FeedsDdnSlctd")).click();
		}
		else{
			test.fail("Feeds Dropdown not shown as selected with Feeds");
		}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyFeedinDDN method end");
		}
	}
	
	public static void ReverseFeedSelection(ExtentTest test,String Country, String Feed) {
		String CntryFeedSlctd,FeedOn;
		try {
			CntryFeedSlctd=Country+"FeedSlctd";
			FeedOn=Feed+"On";
			//Reverse the selections made
			test.log(com.aventstack.extentreports.Status.INFO,"ReverseFeedSelection method begin");
			s.find(GetProperty(CntryFeedSlctd)).click();
			test.pass("Selected "+Country+" Country Feed");
			Thread.sleep(3000);
			s.find(GetProperty(FeedOn)).offset(-80, 0).click();
			test.pass("Unselected "+Feed+" feed filter turning it off");
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"ReverseFeedSelection method end");
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
			test.log(com.aventstack.extentreports.Status.INFO,"SelectFeed method begin");
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
				//s.offset(-100,0).click(pattern2);
				s.find(pattern2).offset(-80,0).click();
				test.pass("Selected "+Feed+" feed");
				Thread.sleep(1000);
			}
			else {
				test.fail(Feed+" feed not found");
			}
			return FeedOn+"ddn";
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
			return FeedOn;
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"SelectFeed method end");
		}
//		finally {
//			return FeedOn;
//		}
	}	
}