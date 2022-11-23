package testPackage;

import java.sql.Timestamp;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
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
			RelaunchReopenFWTab(test,"Reopen");
			if(Option.equals("Company_RIC") && s.exists(GetProperty("SelectCompany"),7)==null) {
				test.pass("Company and RIC displayed in story body");
				return;
			}
			if (s.exists(Patternise("BlankRICS","Strict"),3)==null) {
			ClearMetaData();
			}
			//s.wait(GetProperty("BlankRICS"),5).click();
			s.wait(Patternise("BlankRICS","Strict"),5).click();
			Thread.sleep(2000);
			EnterMetadata("H.N");
			test.pass("Entered RIC");
			ValidateMetadata(Option);
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	@Parameters({"param0","param1","param2"})
	@Test
	public static void Verify_Alert_Publish(String Option,String Alerttext, String USN) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		Region r;
		String timeNdate;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ClearMetaData();
			s.wait(Patternise("BlankRICS","Strict"),5).click();
			Thread.sleep(2000);
			EnterMetadata("H.N");
			test.pass("Entered RIC");
			s.wait(Patternise("GetUSN","Strict"),5).offset(-50,0).click();
			s.type(USN);
			test.pass("Entered Custom USN");
			Thread.sleep(2000);
			r=new Region(s.find(GetProperty("chars")).getX()-80, s.find(GetProperty("chars")).getY()+32, 1, 1);
			r.click();
			Thread.sleep(3000);
			s.type("a", KeyModifier.CTRL);
			Thread.sleep(3000);
			s.keyDown(Key.BACKSPACE);
			s.keyUp(Key.BACKSPACE);
			Thread.sleep(3000);
			switch(Option) {
			case "Publish":
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				timeNdate=(timestamp+"").replace(":","");
				r.type(Alerttext+" "+timeNdate);
				test.pass("Entered custom Alert text");
				s.wait(Patternise("Publish","Strict"),5).click();
				test.pass("Clicked Publish Button");
				if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)&& USN_Flavors(test,USN)) {
					test.pass("Alert successfully Published");
				}
				else {
						test.fail("Alert not Published");
				}
				break;
			case "BlankAlerttext":
				if(s.exists(Patternise("Publish","Strict"),5)==null) {
					test.pass("Publish button is disabled if no alert text is entered");
				}
				else {
					test.fail("Publish button is enabled if no alert text is entered");
				}
				break;
			case "PlaceHolder":
				r.type(Alerttext+"~");
				test.pass("Entered custom Alert text with special characters");
				if(s.exists(Patternise("PublishBtnDsbld","Strict"),5)!=null) {
					test.pass("Publish button disabled after entering place holders in alert text");
				}
				else {
					test.fail("Publish button not disabled after entering place holders in alert text");
				}
				break;
			case "USNFAILS":
				Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
				timeNdate=(timestamp2+"").replace(":","");
				r.type(Alerttext+" "+timeNdate);
				test.pass("Entered custom Alert text");
				s.wait(Patternise("Publish","Strict"),5).click();
				test.pass("Clicked Publish Button");
				if(s.exists(Patternise("PublishedAlert","Strict"),5)!=null && USN_Flavors(test,USN)) {
					test.fail("Alert successfully Published");
				}
				else {
						test.pass("Alert not Published");
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
	
	@Test
	public static void Verify_StoryID_Format() throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		int found=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			s.wait(Patternise("ID","Strict"),5).offset(0,30).click();
			while(s.exists(GetProperty("EndOfDownScroll"))!=null) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
				if(s.exists(GetProperty("StoryIDFormat"),5)!=null) {
					found=1;
					break;
				}
			}
			if(found==1) {
				test.pass("Story ID in format 0 to 999 in story list");
			}
			else {
				test.fail("Story ID not in format 0 to 999 in story list");
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
	public static void Verify_Multiplecodes(String Metadatatype) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ClearMetaData();
			s.wait(Patternise("Blank"+Metadatatype,"Strict"),5).click();
			Thread.sleep(2000);
			EnterMetadata("AAA");
			test.pass("Entered "+Metadatatype+" AAA");
			EnterMetadata("BA");
			test.pass("Entered "+Metadatatype+" BA");
			
			if(s.exists(Patternise("Multiple"+Metadatatype,"Strict"))!=null) {
				test.pass("User is able to enter multiple "+Metadatatype);
			}
			else {
				test.fail("User is unable to enter multiple "+Metadatatype);
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@Test
	public static void Verify_USN() throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			s.wait(Patternise("GetUSN","Strict"),5).click();
			s.find(GetProperty("AlertEditorTab")).click();
			Thread.sleep(2000);
			ValidateMetadata("USN");
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	@Parameters({"param0","param1","param2"})
	@Test
	public static void Verify_MetaData_Inputs(String Metadata,String Data, String validation) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ClearMetaData();
			s.wait(Patternise("Blank"+Metadata,"Strict"),5).click();
			Thread.sleep(2000);
			EnterMetadata(Data.toLowerCase());
			test.pass("Entered data");
			
			switch(validation) {
					case"lesslimit":
						if(s.exists(Patternise("Lesslimit","Strict"),5)!=null || s.exists(Patternise("Lesslimit_1","Strict"),5)!=null) {
							test.pass("Less limit characters allowed in "+Metadata+" field");
						}
						else {
								test.fail("Less limit characters not allowed in "+Metadata+" field");
						}
						break;
					case"exactlimit":
						if(s.exists(Patternise("Exactlimit","Strict"),5)!=null) {
							test.pass("Exact limit characters allowed in "+Metadata+" field");
						}
						else {
								test.fail("Exact limit characters not allowed in "+Metadata+" field");
						}
						break;
					case"Numeric":
						if(s.exists(Patternise("Numeric","Easy"),5)!=null || s.exists(Patternise("Numeric_1","Easy"),5)!=null ) {
							test.pass("Numerics are allowed in "+Metadata+" field");
						}
						else {
								test.fail("Numerics are not allowed in "+Metadata+" field");
						}
						break;
					case"Character":
						s.wait(Patternise("AlertEditorTab","Strict"),5).click();
						if(s.exists(Patternise("Character","Strict"),5)!=null || s.exists(Patternise("Character_1","Easy"),5)!=null) {
							test.pass("Characters are allowed in "+Metadata+" field");
						}
						else {
								test.fail("Characters are not allowed in "+Metadata+" field");
						}
						break;
					case"Alphanumeric":
						s.wait(Patternise("AlertEditorTab","Strict"),5).click();
						if(s.exists(Patternise("AplhanumericCharacter","Strict"),5)!=null || s.exists(Patternise("AplhanumericCharacter_1","Easy"),5)!=null) {
							test.pass("Alphanumeric Characters are allowed in "+Metadata+" field");
						}
						else {
								test.fail("Alphanumeric Characters are not allowed in "+Metadata+" field");
						}
						break;
					case"Special":
						s.wait(Patternise("AlertEditorTab","Strict"),5).click();
						if(s.exists(Patternise("SpecialCharacter","Strict"),5)!=null || s.exists(Patternise("SpecialCharacter_1","Easy"),5)!=null) {
							test.pass("Special Characters are allowed in "+Metadata+" field");
						}
						else {
								test.fail("Special Characters are not allowed in "+Metadata+" field");
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
	
	@SuppressWarnings("finally")
	public static boolean USN_Flavors(ExtentTest test, String USN) {
		boolean val=false;
		try {
			switch(USN) {
			case"TESTUSN":
				if(s.exists(Patternise("PublishedUSN","Strict"),5)!=null) {
					val=true;
				}
				break;
			case"1234567890":
				Thread.sleep(3000);
				System.out.println("inside 10char");
				if(s.exists(Patternise("10CHARUSN","Strict"),3)!=null) {
					val=true;
				}
				break;
			case"123456789":
				Thread.sleep(3000);
				System.out.println("inside 9char");
				if(s.exists(Patternise("9CHARUSN","Strict"),3)!=null) {
					val=true;
					System.out.println("inside 9char if condition");
				}
				break;
			case"12345678":
				System.out.println("inside 8char");
				if(s.exists(Patternise("8CHARUSN","Strict"),5)!=null) {
					val=true;
				}
				break;
			case "!@#$%^&":
				System.out.println("inside specialchar");
				if(s.exists(Patternise("SPCLUSN","Strict"),5)!=null) {
					val=true;
				}
				break;
			}
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			return val;
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
				if(s.exists(Patternise("HNRIC","Strict"),7)!=null) {
					test.pass("Company and RIC are displayed in story body after entering RIC");
				}
				else {
					test.fail("Company and RIC not displayed in story body even after entering RIC");
				}
				break;
			case"Product_Topic":
				Thread.sleep(3000);
				if(s.exists(Patternise("BlankProducts","Strict"),3)==null) {
					test.pass("Products are displayed after entering RIC");
				}
				else {
					test.fail("No Products are displayed after entering RIC");
				}
				if(s.exists(Patternise("BlankTopics","Strict"),3)==null) {
					test.pass("Topics are displayed after entering RIC");
				}
				else {
					test.fail("No Topics are displayed after entering RIC");
				}
				break;
			
			case"USN":
				if(s.exists(Patternise("BlankUSN","Strict"),5)==null) {
					test.pass("Valid USN number is displayed after clicking Get USN button");
				}
				else {
					test.fail("USN field is empty even after clicking Get USN button");
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
	public static void EnterMetadata(String Code) {
		try {
			s.type(Code);
			Thread.sleep(2000);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	
}
