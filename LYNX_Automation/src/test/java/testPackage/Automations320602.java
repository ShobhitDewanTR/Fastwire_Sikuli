package testPackage;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class Automations320602 extends BasePackage.LYNXBase {
	static Pattern pattern, pattern1, pattern2;
	public Automations320602() {
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
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyAutomationsDropdown(String Automation1, String Automation2) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationsDropdown Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(3000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationsDropdown method end");
		}
	}
	
	
	@Parameters({"param0","param1","param2"})
	@Test
	public static void VerifyAutomationinHeadline(String Automation1, String Automation2,String mode) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationinHeadline Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1 );
			if (mode.equals("torn")) {
				test.log(com.aventstack.extentreports.Status.INFO,"Tearing the Fastwire tab");
				s.dragDrop(GetProperty("Fastwiretab"),GetProperty("Newtab"));
				Thread.sleep(4000);
				s.find(GetProperty("maximize")).click();
				Thread.sleep(4000);
				s.find(GetProperty("Date")).click();
				test.log(com.aventstack.extentreports.Status.INFO,"Verifying in torn out tab now");
				FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1 );
				s.find(GetProperty("FWTabCloseRed")).click();
				Thread.sleep(3000);
				RelaunchReopenFWTab(test,"Reopen");
			}
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(4000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationinHeadline method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyAutomationRemoval(String Automation1, String Automation2) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationRemoval Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1 );
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,1);
			test.pass("Deleted one Automation and Saved");
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","DeleteVerify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).similar(0.7f);
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Verifying Automations in Headlines after deletion");
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,0);
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(4000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationRemoval method end");
		}
	}
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyAutomationDeselection(String Automation1, String Automation2) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationDeselection Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1 );
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			s.find(GetProperty(Automation2+"DDN")).click();
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(6000);
			//pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			//pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Verifying Automations in Headlines after deletion");
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,0);
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(4000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationDeselection method end");
		}
	}
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyAutomationReselection(String Automation1, String Automation2) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationReselection Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1);
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			s.find(GetProperty(Automation2+"DDN")).click();
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(6000);
			//pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			//pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Verifying Automations after deselection now");
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,0);
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			s.find(GetProperty(Automation2+"DDN")).click();
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(6000);
			//pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			//pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Verifying Automations after reselection now");
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1);
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing the selections made");
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(4000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationReselection method end");
		}
	}
	
	@Parameters({"param0","param1","param2"})
	@Test
	public static void VerifyAutomationRelaunchReopen(String Automation1, String Automation2,String Option) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationRelaunchReopen Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			EnterAutomation(test,Automation1);
			Thread.sleep(1000);
			EnterAutomation(test,Automation2);
			Thread.sleep(1000);
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Clicked Save button");
			ValidateAutomationSave(test,Automation1);
			ValidateAutomationSave(test,Automation2);
			RelaunchReopenFWTab(test,"Reopen");
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1);
			RelaunchReopenFWTab(test,Option);
			VerifyAutomationDDN(test,Automation1,Automation2,"Verify","Verify");
			//pattern1 = new Pattern(GetProperty(Automation1+"HDLN")).exact();
			//pattern2 = new Pattern(GetProperty(Automation2+"HDLN")).exact();
			s.find(GetProperty("Date")).click();
			test.log(com.aventstack.extentreports.Status.INFO,"Verifying Automations after "+Option);
			FindAutomationsinHeadline(test,pattern1,pattern2,Automation1,Automation2,1,1);
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			UncheckBoxes(test);
			s.find(GetProperty("ApplyButton")).click();
			Thread.sleep(3000);
			OpenUserPrfrncs(test,"Automations");
			ClearAutomationSelection(test,50);
			Thread.sleep(4000);
			if(s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Reversed the changes made and saved");
			}
			else {
				test.fail("Reversal and saving Failed.");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationRelaunchReopen method end");
		}
	}
	public static void EnterAutomation(ExtentTest test,String Automation) {
		test.log(com.aventstack.extentreports.Status.INFO,"EnterAutomation method begin");
		try {
				pattern=new Pattern(GetProperty("AutmtnArea")).exact();
				s.click(pattern);
				Thread.sleep(2000);
				s.type(Automation);
				Thread.sleep(3000);
				if(s.exists(GetProperty(Automation),5)!=null) {
					s.find(GetProperty(Automation)).offset(-100,0).click();
					s.find(GetProperty("AddSlctdBtn")).click();
					test.pass("Added the user entered "+ Automation +" automation");
				}
				else {
					test.fail("Entered Automation ("+ Automation +") not found in dropdown options");
				}
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"EnterAutomation method end");
		}
	}
	public static void VerifyAutomationinDDN(ExtentTest test ,String Automation,String Mode) {
		try{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationinDDN method begin");
			switch(Mode){
			case "Verify":
			if (s.exists(GetProperty(Automation+"DDN"),5)!=null) {
				test.pass(Automation+" automation shown as selected in Automations Dropdown");
			}
			else{
				test.fail(Automation+" automation not shown in Automations Dropdown");
			}
			break;
			case "DeleteVerify":
				if (s.exists(GetProperty(Automation+"DDN"),5)!=null) {
					test.fail(Automation+" automation shown as selected in Automations Dropdown even after deletion");					
				}
				else{
					test.pass(Automation+" automation not shown in Automations Dropdown afer deletion");
				}
				break;
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationinDDN method end");
		}
	}
	public static void VerifyAutomationDDN(ExtentTest test,String Automation1, String Automation2, String DDNOptionA1,String DDNOptionA2) {
		try{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationDDN method begin");
			if (s.exists(GetProperty("AutmtnDdnSlctd"),5)!=null) {
			test.pass("Automations Dropdown shown as selected with Automations");
			s.find(GetProperty("AutmtnDdnSlctd")).click();
			Thread.sleep(2000);
			VerifyAutomationinDDN(test,Automation1,DDNOptionA1);
			VerifyAutomationinDDN(test,Automation2,DDNOptionA2);
			}
			else
			{
				test.fail("Automations Dropdown not shown as selected with automations");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"VerifyAutomationDDN method end");
		}
	}
	public static void FindAutomationsinHeadline(ExtentTest test, Pattern pattern1, Pattern pattern2, String Automation1, String Automation2, int Aval1, int Aval2 ) {
		try {
			test.log(com.aventstack.extentreports.Status.INFO,"FindAutomationsinHeadline method begin");
			int found1=0,found2=0;
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
				if (found1==Aval1 && found2==Aval2) {
					break;
				}
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			//if (s.exists(pattern,5)!=null) {
			System.out.println("Found1: "+found1 +" Found2: "+found2);
			if (found1==Aval1) {
				test.pass(Automation1+" automation shown in Headlines");
			}
			else{
				test.fail(Automation1+" automation not shown in Headlines");
			}
			if (found2==Aval2) {
				test.pass(Automation2+" automation shown in Headlines");
			}
			else{
				test.fail(Automation2+" automation not shown in Headlines");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"FindAutomationsinHeadline method end");
		}
	}
	public static void ValidateAutomationSave(ExtentTest test,String Automation) {
		try {
			test.log(com.aventstack.extentreports.Status.INFO,"ValidateAutomationSave method begin");
			if(s.exists(GetProperty(Automation+"Delete"),5)!=null && s.exists(GetProperty("SVDsabld"),5)!=null) {
				test.pass("Saved "+Automation+" automation");
			}
			else {
				test.fail(Automation + "not saved");
			}
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"ValidateAutomationSave method end");
		}
	}	
	public static void ClearAutomationSelection(ExtentTest test, int NbrOfRows) {
		try {
			test.log(com.aventstack.extentreports.Status.INFO,"ClearAutomationSelection method begin");
			pattern1 = new Pattern(GetProperty("AutmtnArea")).exact();
			s.click(pattern1);
			Thread.sleep(2000);
			for(int i=0;i<NbrOfRows;i++) {
				s.type(Key.BACKSPACE);
			}
			s.find(GetProperty("SVEnbld")).click();
			test.pass("Cleared existing Automations and saved.");
			Thread.sleep(3000);
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally{
			test.log(com.aventstack.extentreports.Status.INFO,"ClearAutomationSelection method end");
		}
	}	
}