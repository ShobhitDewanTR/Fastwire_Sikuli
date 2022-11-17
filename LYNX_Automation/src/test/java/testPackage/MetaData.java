package testPackage;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class MetaData extends BasePackage.LYNXBase {
	static Pattern pattern, pattern1, pattern2;
	public MetaData() {
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
	@Parameters({"param0"})
	@Test
	public static void VerifyAlertEditor(String Market) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ValidateObjectDisplayed(test,"AlertEditorTab","Alert Editor section",0,"Normal");
			ValidateObjectDisplayed(test,"Market","Market Dropdown",1,"Normal");
			ValidateObjectDisplayed(test,"Australia","Australia CheckBox",1,"Normal");
			ValidateObjectDisplayed(test,"Apply","Apply Button",1,"Normal");
			Thread.sleep(2000);
			ValidateObjectDisplayed(test,"MarketsChkd","Market Dropdown shown as checked",0,"Normal");
			if(s.exists(GetProperty("Show"),5)!=null) {
				s.find(GetProperty("Show")).click();
			}
			ValidateObjectDisplayed(test,"BlankProducts","Blank Products textbox",0,"Reverse");
			ValidateObjectDisplayed(test,"BlankTopics","Blank Topic textbox",0,"Reverse");
			ValidateObjectDisplayed(test,"BlankRICS","Blank RIC textbox",0,"Reverse");

			test.log(com.aventstack.extentreports.Status.INFO,"Reversing User made Changes");
			ValidateObjectDisplayed(test,"MarketsChkd","Market Dropdown",1,"Normal");
			ValidateObjectDisplayed(test,"AustraliaChkd","Australia Checkbox making it unchecked",1,"Normal");
			ValidateObjectDisplayed(test,"Apply","Apply Button",1,"Normal");
			ValidateObjectDisplayed(test,"Market","Market Dropdown shown as unchecked",0,"Normal");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@Parameters({"param0"})
	@Test
	public static void Verify_Storylist(String Option) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			if(s.exists(GetProperty(Option),20)!=null) {
				test.pass(Option+" column found in story list");
			}
			else {
				test.fail(Option+" column not found in story list");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	@Parameters({"param0"})
	@Test
	public static void Verify_Display_Metadata_RIC(String Option) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			//RelaunchReopenFWTab(test,"Reopen");
			if(Option.equals("Company_RIC") && s.exists(GetProperty("SelectCompany"),7)==null) {
				test.pass("Company and RIC displayed in story body");
				return;
			}
			if (s.exists(Patternise("BlankRICS"),3)==null) {
			ClearMetaData();
			}
			//s.wait(GetProperty("BlankRICS"),5).click();
			s.wait(Patternise("BlankRICS"),5).click();
			Thread.sleep(2000);
			s.type("H.N");
			test.pass("Entered RIC");
			Thread.sleep(2000);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			ValidateMetadata(Option);
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	public static void ValidateMetadata(String Option) {
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			switch(Option) {
			case"Company_RIC":
				if(s.exists(Patternise("HNRIC"),7)!=null) {
					test.pass("Company and RIC are displayed in story body after entering RIC");
				}
				else {
					test.fail("Company and RIC not displayed in story body even after entering RIC");
				}
				break;
			case"Product_Topic":
				Thread.sleep(3000);
				if(s.exists(Patternise("BlankProducts"),3)==null) {
					test.pass("Products are displayed after entering RIC");
				}
				else {
					test.fail("No Products are displayed after entering RIC");
				}
				if(s.exists(Patternise("BlankTopics"),3)==null) {
					test.pass("Topics are displayed after entering RIC");
				}
				else {
					test.fail("No Topics are displayed after entering RIC");
				}
				break;
			}
		}	
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	
}
