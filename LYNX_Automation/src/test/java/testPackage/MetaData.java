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
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void Verify_Alert_Publish(String DirectRun, String Option,String Alerttext, String USN) throws FindFailed, InterruptedException {
		if(DirectRun.equalsIgnoreCase("YES")) {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		}
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		Region r;
		String timeNdate;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			if(s.exists(Patternise("NoHeadlines","Moderate"),4)!=null || s.exists(Patternise("NoHeadlinesHC","Moderate"),4)!=null ) {
				test.skip("No headlines found unable to proceed");
				return;
			}
			ClearMetaData();
			s.wait(Patternise("BlankRICS","Easy"),5).click();
			Thread.sleep(2000);
			EnterMetadata("H.N");
			test.pass("Entered RIC");
			s.wait(Patternise("GetUSN","Easy"),5).offset(-50,0).click();
			s.type(USN);
			test.pass("Entered Custom USN");
			Thread.sleep(5000);
			/*r=new Region(s.find(GetProperty("chars")).getX()-80, s.find(GetProperty("chars")).getY()+32, 1, 1);
			r.click();
			Thread.sleep(3000);
			s.type("a", KeyModifier.CTRL);
			Thread.sleep(3000);
			s.keyDown(Key.BACKSPACE);
			s.keyUp(Key.BACKSPACE);
			Thread.sleep(3000);*/
			switch(Option.toUpperCase()) {
			case "PUBLISH":
				/*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				timeNdate=(timestamp+"").replace(":","");
				r.type(Alerttext+" "+timeNdate);
				test.pass("Entered custom Alert text");*/
				EnterAlert(Alerttext);
				//r=new Region(s.find(GetProperty("charsselected")).getX()+80, s.find(GetProperty("charsselected")).getY()+10, 1, 1);
				//r.click();
				s.keyDown(Key.TAB);
				s.keyUp(Key.TAB);
				//Move To Publish
				Thread.sleep(2000);
				s.keyDown(Key.ENTER);
				s.keyUp(Key.ENTER);
				//s.wait(Patternise("Publish","Strict"),5).click();
				test.pass("Clicked Publish Button");
				NavigatetoPublishHistory();
				if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)&& USN_Flavors(test,USN)) {
					test.pass("Alert successfully Published");
				}
				else {
						test.fail("Alert not Published");
				}
				break;
			case "BLANKALERTTEXT":
				EnterAlert("");
				if(s.exists(Patternise("PublishBtnDsbld","Strict"),5)!=null) {
					test.pass("Publish button is disabled if no alert text is entered");
				}
				else {
					test.fail("Publish button is enabled if no alert text is entered");
				}
				break;
			case "PLACEHOLDER":
				//r.type(Alerttext);
				EnterAlert(Alerttext);
				test.pass("Entered custom Alert text "+Alerttext);
				if(s.exists(Patternise("PublishBtnDsbld","Strict"),5)!=null) {
					test.pass("Publish button disabled after entering place holders in alert text");
				}
				else {
					test.fail("Publish button not disabled after entering place holders in alert text");
				}
				break;
			case "USNFAILS":
				/*Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
				timeNdate=(timestamp2+"").replace(":","");
				r.type(Alerttext+" "+timeNdate);
				test.pass("Entered custom Alert text");*/
				EnterAlert(Alerttext);
				s.keyDown(Key.TAB);
				s.keyUp(Key.TAB);
				//Move To Publish
				Thread.sleep(2000);
				s.keyDown(Key.ENTER);
				s.keyUp(Key.ENTER);
				//s.wait(Patternise("Publish","Strict"),5).click();
				test.pass("Clicked Publish Button");
				NavigatetoPublishHistory();
				if(s.exists(Patternise("PublishedAlert","Strict"),5)!=null && USN_Flavors(test,USN)) {
					test.fail("Alert successfully Published");
				}
				else {
						test.pass("Alert not Published");
				}
				break;
			case "HIGHCONTRAST":
				SetHighContrast("ON");
				EnterAlert(Alerttext);
				SetHighContrast("OFF");
				break;
			case "HIGHCONTRASTPUBLISH":
				//s.wait(Patternise("PublishHC","Easy"),5).click();
				SetHighContrast("ON");
				EnterAlert(Alerttext);
				s.keyDown(Key.TAB);
				s.keyUp(Key.TAB);
				//Move To Publish
				Thread.sleep(2000);
				s.keyDown(Key.ENTER);
				s.keyUp(Key.ENTER);
				test.pass("Clicked Publish Button");
				NavigatetoPublishHistory();
				if(s.exists(Patternise("PublishedAlertHC","Easy"),5)!=null || s.exists(Patternise("PublishedAlert_UnselectedHC","Easy"),5)!=null) {
						test.pass("Alert successfully Published in High Contrast");
				}
				else {
						test.fail("Alert not Published in High Contrast");
				}
				SetHighContrast("OFF");
				break;
			case "HIGHCONTRASTQUICKPUBLISH":
				SetHighContrast("ON");
				s.wait(Patternise("ReleaseBody","Easy"),5).offset(0,34).rightClick();
				Thread.sleep(3000);
				EnterAlert(Alerttext);
				s.keyDown(Key.TAB);
				s.keyUp(Key.TAB);
				//Move To Publish
				Thread.sleep(2000);
				s.keyDown(Key.ENTER);
				s.keyUp(Key.ENTER);
				//s.wait(Patternise("QPublishHC","Easy"),5).click();
				test.pass("Clicked Quick Publish Button");
				NavigatetoPublishHistory();
				if(s.exists(Patternise("PublishedAlertHC","Easy"),5)!=null || s.exists(Patternise("PublishedAlert_UnselectedHC","Easy"),5)!=null) {
						test.pass("Alert successfully Published in High Contrast using Quick Publish");
				}
				else {
						test.fail("Alert not Published in High Contrast using Quick Publish");
				}
				SetHighContrast("OFF");
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
	@Parameters({"param0"})
	@Test
	public static void Verify_StoryID_Format(String mode) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		int found=0;
		String StoryIDFormat;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			if(mode.toUpperCase().equals("HIGHCONTRAST")) {
				StoryIDFormat=GetProperty("StoryIDFormatHC");
				SetHighContrast("ON");
			}
			else {
				StoryIDFormat=GetProperty("StoryIDFormat");
			}
			
			s.wait(Patternise("ID","Strict"),5).offset(0,30).click();
			while(s.exists(Patternise("EOHdlnScroll","Strict"))==null) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
				if(s.exists(StoryIDFormat,2)!=null) {
					found=1;
					break;
				}
			}
			if(mode.toUpperCase().equals("HIGHCONTRAST")) {
				SetHighContrast("OFF");
			}
			if(found==1) {
				test.pass("Story ID in format 0 to 999 in story list in "+mode+" mode");
			}
			else {
				test.fail("Story ID not in format 0 to 999 in story list in "+mode+" mode");
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
		String[] arrOfStr=null;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ClearMetaData();
			if(validation.toUpperCase().contains("HIGHCONTRAST")) {
				SetHighContrast("ON");
				Thread.sleep(2000);
			}
			s.wait(Patternise("Blank"+Metadata,"Strict"),5).click();
			Thread.sleep(2000);
			if(Data.contains(";")) {
				arrOfStr = Data.split(";", 0);
				for (String a : arrOfStr) {
					EnterMetadata(a.toLowerCase());
					Thread.sleep(2000);
				}
				test.pass("Entered multiple "+Metadata);
			}
			else {
					EnterMetadata(Data.toLowerCase());
					test.pass("Entered data");
					Thread.sleep(2000);
			}
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
						if(s.exists(Patternise("AplhanumericCharacter","Strict"),5)!=null || s.exists(Patternise("AplhanumericCharacter_1","Easy"),5)!=null || s.exists(Patternise("AplhanumericCharacter_2","Strict"),5)!=null) {
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
					case"Publish":
						if(s.exists(Patternise("BlankRICS","Strict"),5)!=null) {
							s.click(Patternise("BlankRICS","Strict"));
							EnterMetadata("H.N");
							test.pass("Entered RIC");
							Thread.sleep(5000);
						}
						EnterAlert("TEST PUBLISH ");
						if(Metadata.equals("NamedItems")) {
							s.click(Patternise("BlankNamedItems","Strict"));
							for (String a : arrOfStr) {
								EnterMetadata(a.toLowerCase());
								Thread.sleep(2000);
							}
						}
						s.wait(Patternise("Publish","Strict"),5).click();
						test.pass("Clicked Publish Button");
						if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)) {
							test.pass("Alert successfully Published");
						}
						else {
								test.fail("Alert not Published");
						}
						break;
					case"ShortcutPublishF12":
						EnterAlert("TEST PUBLISH ");
						s.keyDown(Key.F12);
						s.keyUp(Key.F12);
						test.pass("Clicked F12 key to initiate publish");
						if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)) {
							test.pass("Alert successfully Published");
						}
						else {
								test.fail("Alert not Published");
						}
						break;
		
					case"ShortcutPublishSF12":
						EnterAlert("TEST PUBLISH ");
						s.keyDown(Key.SHIFT);
						s.keyDown(Key.F12);
						s.keyUp(Key.F12);
						s.keyUp(Key.SHIFT);
						test.pass("Clicked Shift F12 key to initiate publish");
						if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)) {
							test.pass("Alert successfully Published");
						}
						else {
								test.fail("Alert not Published");
						}
						break;
					case"BasketPublish":
						EnterAlert("TEST PUBLISH ");
						s.wait(Patternise("Publish","Strict"),5).click();
						test.pass("Clicked Publish Button");
						if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)) {
							test.pass("Alert successfully Published in Publish history window");
						}
						else {
								test.fail("Alert not Published in Publish history window");
						}
						break;
						//VerifyInBasket("TEST PUBLISH ");
					case "HIGHCONTRAST":
						if(s.exists(Patternise(Metadata,"Easy"),5)!=null) {
							test.pass("Custom "+Metadata.replace("HC","")+" entered successfully in Dark Mode");
						}
						else {
								test.fail("Custom "+Metadata.replace("HC","")+" entered not found in Dark Mode");
						}
						SetHighContrast("OFF");
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
	
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void Verify_Lang_Publish(String Option,String Alerttext, String USN, String language) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Preferences","Application");
			SelectLanguage(test,language);
			Verify_Alert_Publish("NO",Option,Alerttext,USN);
			test.log(com.aventstack.extentreports.Status.INFO,"Reversing to default language English US");
			OpenUserPrfrncs(test,"Preferences","Application");
			SelectLanguage(test,"EnglishUS");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	public static void SelectLanguage(ExtentTest test, String language)
	{ 	
		int count=0;
		String nameofCurrMethod = new Throwable()
	            .getStackTrace()[0]
	            .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try{
				if(s.exists(Patternise("DefaultLanguage","Strict"),5)!=null) {
					s.wait(Patternise("DefaultLanguage","Strict"),5).offset(80,0).click();
					Thread.sleep(3000);
					s.keyDown(Key.HOME);
					s.keyUp(Key.HOME);
					while(count<2) {
						if(s.exists(Patternise(language,"Strict"),5)!=null) {
							s.click(Patternise(language,"Strict"),5);
							s.wait(Patternise("Save","Strict"),5).click();
							break;
						}
						s.keyDown(Key.END);
						s.keyUp(Key.END);
						count++;
					}
					if(count==2) {
							s.click(Patternise("Cancel","Strict"),5);
							s.click(Patternise("Cancel","Strict"),5);
							test.fail("Language "+language + " not found in Default Language dropdown");
					}
					else {
							test.pass("Default Language dropdown found and language "+language + " selected and saved");
					}
				}
				else {
					test.fail("Default Language dropdown not found");
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
	@Parameters({"param0","param1","param2"})
	@Test
	public static void Verify_RIC_Correction(String RIC,String Mode,String Publish) {
		if(Mode.equals("NewTest")) {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		}
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			if(Mode.equals("NewTest")) {
				RelaunchReopenFWTab(test,"Reopen");
				ClearMetaData();
			}
			if(Publish.equals("HCVerify") || Publish.equals("HCPublish")) {
				SetHighContrast("ON");
				s.wait(Patternise("BlankRICSHC","Strict"),5).click();
				
			}
			else {
					s.wait(Patternise("BlankRICS","Strict"),5).click();
			}
			Thread.sleep(2000);
			s.type(RIC);
			Thread.sleep(2000);
			if(s.exists(Patternise("HNRIC_AE","Strict"),3)!=null || s.exists(Patternise("HNRIC_AEHC","Strict"),3)!=null  ) {
				test.pass("Company shown correctly in Alert Editor for typed RIC");
			}
			else {
				test.fail("Company not shown correctly in  Alert Editor for typed RIC");
			}
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			test.pass("Entered RIC");
			Thread.sleep(2000);
			if(s.exists(Patternise("HNRIC","Strict"),3)!=null || s.exists(Patternise("HNRICHC","Strict"),3)!=null) {
				test.pass("Company shown correctly in Story header for entered RIC");
			}
			else {
				test.fail("Company not shown correctly in Story header for entered RIC");
			}
			if(Publish.equals("Publish")) {
				EnterAlert("TEST PUBLISH ");
				s.wait(Patternise("Publish","Strict"),5).click();
				test.pass("Clicked Publish Button");
				if((s.exists(Patternise("PublishedAlert","Strict"),5)!=null || s.exists(Patternise("PublishedAlert_Unselected","Strict"),5)!=null)) {
					test.pass("Alert successfully Published in Publish history window");
				}
				else {
						test.fail("Alert not Published in Publish history window");
				}
			}
			if(Publish.equals("HCVerify")) {
				SetHighContrast("OFF");
			}
			if(Publish.equals("HCPublish")) {
				EnterAlert("TEST PUBLISH HIGHCONTRAST");
				s.wait(Patternise("PublishHC","Easy"),5).click();
				test.pass("Clicked Publish Button");
				if((s.exists(Patternise("PublishedAlertHC","Easy"),5)!=null || s.exists(Patternise("PublishedAlert_UnselectedHC","Easy"),5)!=null)) {
					test.pass("Alert successfully Published in Publish history window");
				}
				else {
						test.fail("Alert not Published in Publish history window");
				}
				SetHighContrast("OFF");
			}
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
	public static void Verify_NonEnglish_Publish_BSKT(String RIC,String Language,String Publish) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Preferences","Application");
			SelectLanguage(test,Language);
			ClearMetaData();
			Verify_RIC_Correction(RIC,"ExistingTest",Publish);
			OpenUserPrfrncs(test,"Preferences","Application");
			SelectLanguage(test,"EnglishUS");
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
	public static void Verify_MoreActions(String Option,String mode, String type) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		String Shortcut="";
		Pattern window=null;
		Pattern window2=null;
		Pattern ddnoption=null;
		Pattern ddnoption1=null;
		try {
			switch(type.toUpperCase().trim()) {
			case"TEMPLATE":
				Shortcut="t";
				window=Patternise("TemplateEditorWindow","Easy");
				ddnoption=Patternise("DDNOptn-ShowTemplates","Easy");
				break;
			case"PUBLISHHISTORY":
				Shortcut="y";
				window=Patternise("PublishHistoryWindow","Easy");
				window2=Patternise("PublishHistoryWindow_1","Easy");
				ddnoption=Patternise("DDNOptn-PublishedHistory","Easy");
				ddnoption1=Patternise("DDNOptn-PublishedHistory_1","Easy");
				break;
			case"MOREACTIONS":
				window=Patternise("MoreActionsWindow","Easy");
				Shortcut="r";
				break;
			case"COMPANYLIST":
				window=Patternise("CompanyListsWindow","Easy");
				Shortcut="l";
				break;
			case"NEWSFEED":
				window=Patternise("FeedsWindow","Easy");
				Shortcut="f";
				break;
			case"HEADLINE":
				window=Patternise("HeadlineActivityWindow","Easy");
				ddnoption=Patternise("DDNOptn-HeadlineActivity","Easy");
				break;			
			}
			RelaunchReopenFWTab(test,"Reopen");
			SelectLiveFeedOrFullsearch(Option);
			if(Option.toUpperCase().equals("FULLSEARCH")){
				ClickFullSearchbutton();
			}
			switch(mode.toUpperCase().trim()){
			case"SHORTCUT":
				s.type(Shortcut,Key.ALT);
				if(s.exists(window,5) != null || s.exists(window2,5) != null) {
					test.pass("Successfully launched "+ type+" Window using Alt + "+Shortcut+" shortcut in "+Option);
					if(s.exists(Patternise("WindowClose","Easy"),5) != null) {
						s.find(Patternise("WindowClose","Easy")).offset(7,0).click();
					}
				}
				else {
					test.fail("Unable to launch "+type+" Window using Alt + "+Shortcut+" shortcut in "+Option);
				}
				Thread.sleep(3000);
				break;
			case"MOREACTION":
				if(s.exists(Patternise("MoreActions","Easy"),5) != null) {
					s.click(Patternise("MoreActions","Easy"),3);
					if(s.exists(ddnoption)!=null) {
						s.click(ddnoption,3);
					}
					else if(s.exists(ddnoption1)!=null)
					{
						s.click(ddnoption1,3);
					}
				}
				else {
					test.fail("MoreActions dropdown not found");
				}
				if(s.exists(window,5) != null || s.exists(window2,5) != null) {
					test.pass("Successfully launched "+type+" Window using more actions in "+Option+ " mode");
					if(s.exists(Patternise("WindowClose","Easy"),5) != null) {
						s.find(Patternise("WindowClose","Easy")).offset(7,0).click();
					}
				}
				else {
					test.fail("Unable to launch "+type+" Window using more actions in "+Option+" mode");
				}
				Thread.sleep(3000);
				break;
			case "COLUMN":
				if(s.exists(Patternise(type.toUpperCase(),"Easy")) != null ) {
					test.pass("Able to find column "+type+" in "+Option+ " mode");
				}
				else {
					test.fail("Column "+type+" not found in "+Option+ " mode");
				}
				break;
			case "ADDCOLUMN":
				s.find(Patternise("DATE","Easy")).rightClick();
				Thread.sleep(1000);
				s.wait(Patternise(type.toUpperCase(),"Easy")).click();
				if(s.exists(Patternise(type.toUpperCase()+"icon","Easy")) != null ) {
					test.pass("Able to find column "+type+" in "+Option+ " mode");
				}
				else {
					test.fail("Column "+type+" not found in "+Option+ " mode");
				}
				s.find(Patternise("DATE","Easy")).rightClick();
				Thread.sleep(1000);
				s.wait(Patternise(type.toUpperCase(),"Easy")).click();
				break;
			default:
				test.fail("Invalid option passed- Pass either SHORTCUT or MOREACTION or COLUMN or ADDCOLUMN");
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
	public static void Verify_Topics_FullSearch(String topic) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			SelectLiveFeedOrFullsearch("FULLSEARCH");
			s.find(Patternise("topiccodes","Easy")).offset(50,0).click();
			s.type(topic);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			ClickFullSearchbutton();
			if(s.exists(Patternise("SearchResultBlue","Easy"),6) != null) {
				test.pass("Able to search with the topic code "+topic+" in Full Search");
			}
			else {
				test.fail("Unable to search with the topic code "+topic+"  in Full Search");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void VerifyPNAC_UCDP(String Country,String Feed, String RIC, String BriefPublish) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			SelectFeed(test,Country,Feed);
			if (s.exists(Patternise("SaveFeed","Easy"),3) != null) {
				s.find(GetProperty("SaveFeed")).click();
			}
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			Verify_Alert_Publish("No","PUBLISH","TEST PUBLISH","TESTUSN");
			if(BriefPublish.toUpperCase().equals("YES")) {
					s.wait(Patternise("Brief","Strict"),8).click();
					s.wait(Patternise("PublishBrief","Strict"),8).click();
					if(s.exists(Patternise("ViewBrief","Strict"),20)!=null) {
						s.click(Patternise("ViewBrief","Strict"));
						if(s.exists(Patternise("PNACID","Strict"),10)!=null) {
							test.fail("PNAC ID is not generated for published Brief for UCDP Feed");
						}
						else {
							test.pass("PNAC ID is generated successfully for published Brief for UCDP Feed");
						}
					}
					else
					{
						test.fail("Published Brief (View Brief) not seen in Publish History");
					}
			}
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			ReverseFeedSelection(test,Country,Feed);
			s.click(Patternise("EnblFltrsSlctd","Exact"));
			test.pass("Enable Filters turned off");
			s.wait(GetProperty("SaveFeed"),5).click();
			test.pass("Reversed the changes made and saved");
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void VerifyMetadata_BAE(String Metadata,String Code, String mode, String Option) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Preferences","DefaultCodesBAE");
			ManipulateBAEmetadata(Metadata,Code);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user entered data");
			Thread.sleep(1000);
			SelectLiveFeedOrFullsearch(mode);
			switch(Option) {
			case "MOREACTION":
				if(s.exists(Patternise("MoreActions","Easy"),5) != null) {
					s.click(Patternise("MoreActions","Easy"),3);
					s.click(Patternise("DDNOptnOpnBAE","Easy"),3);
				}
				else {
					test.fail("MoreActions dropdown not found");
				}
				break;
			case "SHORTCUT":
				s.keyDown(Key.SHIFT);
				s.keyDown(Key.F2);
				s.keyUp(Key.F2);
				s.keyUp(Key.SHIFT);
				Thread.sleep(3000);
				break;
			}
			if(s.exists(Patternise("BAE"+Code+Metadata,"Easy"),5) != null) {
				test.pass("Default "+Metadata+" code: "+Code+" found in Blank Alert Editor");
			}
			else {
				test.fail("Default "+Metadata+" code: "+Code+"  not found");
			}
			s.click(Patternise("FWTabCloseRed","Easy"),3);
			OpenUserPrfrncs(test,"Preferences","DefaultCodesBAE");
			ManipulateBAEmetadata(Metadata,"");
			test.pass("Deleted entered data");
			s.wait(GetProperty("SaveFeed"),5).click();
			test.pass("Reversed the changes made and saved");
			
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
	public static void VerifyFeedFilterCountryCode(String Feed,String Country, String Code) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			SelectFeed(test,Country,Feed);
			if (s.exists(Patternise("SaveFeed","Easy"),3) != null) {
				s.find(GetProperty("SaveFeed")).click();
			}
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			OpenUserPrfrncs(test,"Preferences","Fastwire-UserPreferences");
			s.find(Patternise("NoodlCntryCd","Moderate")).offset(100,15).click();
			for (int i=0;i<10;i++) {
				s.keyDown(Key.BACKSPACE);
				s.keyUp(Key.BACKSPACE);
			}
			test.pass("Cleared existing Country Codes");
			s.type(Code.toUpperCase());
			s.keyDown(Key.TAB);
			s.keyUp(Key.TAB);
			s.wait(Patternise("Save","Strict"),5).click();
			RelaunchReopenFWTab(test,"Reopen");
			if (s.exists(Patternise("NOODLFilterby"+Code,"Moderate"),5)!=null){
				test.pass("Filter by Country Code "+Code + " seen on Story body");
			}
			else {
				test.fail("Filter by Country Code "+Code + " not seen on Story body");
			}
			//Reverse the changes
			OpenUserPrfrncs(test,"Preferences","Fastwire-UserPreferences");
			s.find(Patternise("NoodlCntryCd","Moderate")).offset(100,15).click();
			for (int i=0;i<10;i++) {
				s.keyDown(Key.BACKSPACE);
				s.keyUp(Key.BACKSPACE);
			}
			test.pass("Cleared entered Country Code");
			s.wait(Patternise("Save","Strict"),5).click();
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			ReverseFeedSelection(test,Country,Feed);
			s.click(Patternise("EnblFltrsSlctd","Exact"));
			test.pass("Enable Filters turned off");
			s.wait(GetProperty("SaveFeed"),5).click();
			test.pass("Reversed the changes made and saved");
			
			
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void VerifyShortCmpnyName(String RIC,String Shortname) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		int status,found=0;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Preferences","ShortCompanyNames");
			status=ManipulateShrtCmpnyData(RIC,Shortname,"DELETE");
			if (status==1) {
				s.find(GetProperty("SaveFeed")).click();
				OpenUserPrfrncs(test,"Preferences","ShortCompanyNames");
			}
			ManipulateShrtCmpnyData(RIC,Shortname,"ADD");
			Thread.sleep(3000);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user entered data");
			Thread.sleep(1000);
			OpenUserPrfrncs(test,"Preferences","ShortCompanyNames");
			ManipulateShrtCmpnyData(RIC,Shortname,"VERIFY");
			s.find(GetProperty("CancelShrtCmpnyName")).click();
			ClearMetaData();
			if (s.exists(Patternise("BlankRICS","Easy"),5) != null) {
					s.type(Patternise("BlankRICS","Easy"),RIC.replace("_", " "));
					s.keyDown(Key.ENTER);
					s.keyUp(Key.ENTER);
					test.pass("Entered RIC in RIC Field");
					s.find(Patternise("RIC","Moderate")).offset(0,-20).click();
					for (int i=0;i<6;i++) {
							s.keyDown(Key.PAGE_UP);
							s.keyUp(Key.PAGE_UP);
					}
					for (int i=0;i<6;i++) {
							if (s.exists(Patternise(RIC+"AE","Moderate"))!=null){
										test.pass("Short Company Name Found in Alert Editor");
										found=1;
										for (int j=0;j<6;j++) {
											s.keyDown(Key.PAGE_UP);
											s.keyUp(Key.PAGE_UP);
										}
										break;
							}
							s.keyDown(Key.PAGE_DOWN);
							s.keyUp(Key.PAGE_DOWN);
					}	
					for (int i=0;i<6;i++) {
							s.keyDown(Key.PAGE_UP);
							s.keyUp(Key.PAGE_UP);
					}
					if(found==0) {
						test.fail("Short Company Name not found in Alert Editor");
					}
			}
			else {
					test.fail("RIC not blank, unable to enter in RIC field");
			}
			//Reverse the changes
			OpenUserPrfrncs(test,"Preferences","ShortCompanyNames");
			ManipulateShrtCmpnyData(RIC,Shortname,"DELETE");
			Thread.sleep(3000);
			s.find(GetProperty("SaveFeed")).click();
			test.pass("Saved the user entered data");
			Thread.sleep(1000);
			test.pass("Reversed the changes made and saved");
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	@Parameters({"param0","param1","param2","param3"})
	@Test
	public static void VerifyDefaultcodefeeds(String Country,String Feed, String Product, String Topic) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		int status;
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"Preferences","DefaultCodesFeeds");
			status=ManipulateDefaultFeedsData(Feed,"DELETE", Product, Topic);
			if (status==1) {
				s.find(GetProperty("SaveLynxPreferencesDark")).click();
				OpenUserPrfrncs(test,"Preferences","DefaultCodesFeeds");
			}
			ManipulateDefaultFeedsData(Feed,"ADD", Product, Topic);
			Thread.sleep(3000);
			s.find(GetProperty("SaveLynxPreferencesLight")).click();
			test.pass("Saved the user entered data");
			Thread.sleep(1000);
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			SelectFeed(test,Country,Feed);
			if (s.exists(Patternise("SaveFeed","Easy"),3) != null) {
				s.find(GetProperty("SaveFeed")).click();
			}
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			if(s.exists(Patternise("NoHeadlines","Moderate"),4)!=null || s.exists(Patternise("NoHeadlinesHC","Moderate"),4)!=null ) {
				test.skip("No headlines found,unable to proceed");
			}
			else {
					if(s.exists(Patternise(Product+"_AE","Moderate"))!=null && s.exists(Patternise(Topic+"_AE","Moderate"))!=null) {
						test.pass("Default Product Code and Default Topic Code found");
					}
					else if(s.exists(Patternise(Product+"_AE","Moderate"))!=null && s.exists(Patternise(Topic+"_AE","Moderate"))==null) {
						test.fail("Default Product Code found, but default Topic Code not found");
					}
					else if(s.exists(Patternise(Product+"_AE","Moderate"))==null && s.exists(Patternise(Topic+"_AE","Moderate"))!=null) {
						test.fail("Default Topic Code found, but Default Product Code not found");
					}
					else {
						test.fail("Default Product Code and Default Topic Code not found");
					}
			}
			//Reversing the changes
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			ReverseFeedSelection(test,Country,Feed);
			s.click(Patternise("EnblFltrsSlctd","Exact"));
			test.pass("Enable Filters turned off");
			s.wait(GetProperty("SaveFeed"),5).click();
			OpenUserPrfrncs(test,"Preferences","DefaultCodesFeeds");
			ManipulateDefaultFeedsData(Feed,"DELETE", Product, Topic);
			Thread.sleep(3000);
			s.find(GetProperty("SaveLynxPreferencesDark")).click();
			test.pass("Reversed the changes made and saved");
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
	public static void VerifyIBESEPS_Estimate(String RIC) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			ClearMetaData();
			if (s.exists(Patternise("BlankRICS","Easy"),5) != null) {
				s.type(Patternise("BlankRICS","Easy"),RIC);
				s.keyDown(Key.ENTER);
				s.keyUp(Key.ENTER);
				test.pass("Entered RIC in RIC Field");
			}
			else {
				test.fail("RIC not blank, unable to enter in RIC field");
			}
			s.find(Patternise("RIC","Moderate")).offset(0,-20).click();
			for (int i=0;i<10;i++) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			s.click(Patternise("GetEstimates","Moderate"));
			Thread.sleep(2000);
			for (int i=0;i<10;i++) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
			}
			s.find(Patternise("GetEstimates","Easy")).offset(0,93).click();
			Thread.sleep(3000);
			s.find(Patternise("GetEstimates","Easy")).offset(0,33).click();
			for (int i=0;i<10;i++) {
				s.keyDown(Key.PAGE_UP);
				s.keyUp(Key.PAGE_UP);
			}
			Thread.sleep(3000);
			s.click(Patternise("Home1","Moderate"));
			Thread.sleep(3000);
			//Safer side paging up in case control is somewhere down
			s.find(Patternise("Home2","Moderate")).offset(0,57).click();
			for (int i=0;i<10;i++) {
				s.keyDown(Key.PAGE_UP);
				s.keyUp(Key.PAGE_UP);
			}
			s.type(Patternise("HomeSearch","Moderate"),"AMERS -Companies-Alerting-Tools");
			Thread.sleep(3000);
			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);
			if (s.exists(Patternise("GooGL1","Easy"),5) != null || s.exists(Patternise("GooGL2","Easy"),5) != null) {
				test.pass("Published Alert found in Basket");
			}
			else {
				test.fail("Published Alert not found in Basket");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@Parameters({"param0","param1"})
	@Test
	public static void Verify_Auto_Alerts(String Country,String Feed) {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try {
			RelaunchReopenFWTab(test,"Reopen");
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			SelectFeed(test,Country,Feed);
			if (s.exists(Patternise("SaveFeed","Easy"),3) != null) {
				s.find(GetProperty("SaveFeed")).click();
			}
			test.pass("Saved the user selected feed");
			Thread.sleep(1000);
			RelaunchReopenFWTab(test,"Reopen");
			s.find(Patternise("StatusDdn","Moderate")).click();
			Thread.sleep(3000);
			s.find(Patternise("AutoAlerted","Moderate")).click();
			s.find(Patternise("ApplyButton","Moderate")).click();
			Thread.sleep(3000);
			s.find(Patternise("DATE","Moderate")).offset(0,50).click();
			Thread.sleep(3000);
			if(s.exists(Patternise("NoHeadlines","Moderate"),4)!=null || s.exists(Patternise("NoHeadlinesHC","Moderate"),4)!=null ) {
				test.skip("No headlines found,unable to proceed");
			}
			else {
					if(s.exists(Patternise("AutomatedAlerts","Moderate"),4)!=null) {
						test.pass("Automated alert found in publish history");
					}
					else {
						test.fail("Automated alert not found in publish history");
					}
			}
			//Reversing the changes
			OpenUserPrfrncs(test,"FastwirePreferences","Feeds");
			ReverseFeedSelection(test,Country,Feed);
			s.click(Patternise("EnblFltrsSlctd","Exact"));
			test.pass("Enable Filters turned off");
			s.wait(GetProperty("SaveFeed"),5).click();
			test.pass("Reversed the changes made and saved");
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	
	public static int ManipulateDefaultFeedsData(String Feed,String Mode, String Product, String Topic) {
		try {
			switch(Mode.toUpperCase()) {
			case "ADD":
				if (s.exists(Patternise("NewDefaultCodeFeed","Moderate"),5)!=null){
					s.click(Patternise("NewDefaultCodeFeed","Moderate"));
					s.type(Patternise("NewFeedTxtbox","Moderate"),Feed);
					s.keyDown(Key.ENTER);
					s.keyUp(Key.ENTER);
					test.pass("Entered the Feed name");
					s.type(Patternise("NewFeedProductCode","Moderate"),Product);
					s.keyDown(Key.ENTER);
					s.keyUp(Key.ENTER);
					test.pass("Entered the Product Code");
					s.type(Patternise("NewFeedTopicCode","Moderate"),Topic);
					s.keyDown(Key.ENTER);
					s.keyUp(Key.ENTER);
					test.pass("Entered the Topic Code");
					s.click(Patternise("AddShrtcmpny","Moderate"));
					test.pass("Clicked Add Button");
				}
				else {
					test.fail("New button not found");
				}
				break;
			case "DELETE":
				s.find(Patternise("NewDefaultCodeFeed","Moderate")).offset(0,-50).click();
				for (int i=0;i<5;i++) {
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
				}
				s.mouseMove(Patternise("NewDefaultCodeFeed","Moderate").targetOffset(0,15));
				for (int i=0;i<4;i++) {
					if (s.exists(Patternise(Feed+"Delete","Moderate"))!=null){
						s.click(Patternise(Feed+"Delete","Moderate"));
						s.click(Patternise("DeleteShrtcmpny","Moderate"));
						s.click(Patternise("DeleteConfirmYes","Moderate"),5);
						test.pass("Deleted the Feed from Saved list");
						return 1;
					}
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
					
				}
				break;
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		return 0;
	}
	
	public static int ManipulateShrtCmpnyData(String RIC,String Shortname, String Mode) {
		try {
			switch(Mode.toUpperCase()) {
			case "ADD":
				if (s.exists(Patternise("AddNewNameShrtcmpny","Moderate"),5)!=null){
					s.click(Patternise("AddNewNameShrtcmpny","Moderate"));
					s.find(Patternise("FullNameShrtcmpny","Moderate")).offset(100,0).click();
					s.type(RIC.replace("_", " "));
					s.keyDown(Key.ENTER);
					s.keyUp(Key.ENTER);
					test.pass("Entered the Full name");
					s.find(Patternise("ShortNameShrtcmpny","Moderate")).offset(100,0).click();
					s.type(Shortname);
					s.click(Patternise("AddShrtcmpny","Moderate"));
					test.pass("Entered the Short name");
				}
				else {
					test.fail("Add New Name button not found");
				}
				break;
			case "DELETE":
				s.find(Patternise("AddNewNameShrtcmpny","Moderate")).offset(0,-200).click();
				for (int i=0;i<5;i++) {
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
				}
				s.mouseMove(Patternise("AddNewNameShrtcmpny","Easy").targetOffset(0,15));
				for (int i=0;i<4;i++) {
					if (s.exists(Patternise(RIC,"Moderate"))!=null){
						s.click(Patternise(RIC,"Moderate"));
						s.click(Patternise("DeleteShrtcmpny","Moderate"));
						test.pass("Deleted the RIC from Saved list");
						return 1;
					}
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
					
				}
				break;
			case "VERIFY":
				s.find(Patternise("AddNewNameShrtcmpny","Moderate")).offset(0,-200).click();
				for (int i=0;i<6;i++) {
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
				}
				s.mouseMove(Patternise("AddNewNameShrtcmpny","Easy").targetOffset(0,15));
				for (int i=0;i<6;i++) {
					System.out.println("Value of i "+i);
					if (s.exists(Patternise(RIC,"Moderate"))!=null){
						test.pass("Short Company Name Found in Saved list");
						return 1;
					}
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					s.keyDown(Key.PAGE_UP);
					s.keyUp(Key.PAGE_UP);
					
				}
				test.fail("Short Company Name not found in Saved list");
				break;
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		return 0;
	}
	public static void ManipulateBAEmetadata(String Metadata,String Code) {
		try {
			
			switch(Metadata.toUpperCase()) {
			case "PRODUCT":
				if (s.exists(Patternise("EmptyBAEProduct","Moderate"),5)!=null){
					s.find(Patternise("ProductBAE","Moderate")).offset(300,0).click();
					test.pass("Product field already empty");
				}
				else {
					s.find(Patternise("ProductBAE","Moderate")).offset(300,0).click();
					for (int i=0;i<10;i++) {
						s.keyDown(Key.BACKSPACE);
						s.keyUp(Key.BACKSPACE);
					}
					test.pass("Cleared existing products from Product field");
				}
				if(!Code.equals("")) {
					EnterMetadata(Code);
					test.pass("Entered Data in Product field");
				}
				break;
			case "TOPIC":
				if (s.exists(Patternise("EmptyBAETopic","Moderate"),5)!=null){
					s.find(Patternise("TopicBAE","Moderate")).offset(300,0).click();
					test.pass("Topic field already empty");
				}
				else {
					s.find(Patternise("TopicBAE","Moderate")).offset(300,0).click();
					for (int i=0;i<10;i++) {
						s.keyDown(Key.BACKSPACE);
						s.keyUp(Key.BACKSPACE);
					}
					test.pass("Cleared existing topics from Topic field");
				}
				if(!Code.equals("")) {
					EnterMetadata(Code);
					test.pass("Entered Data in Topic field");
				}
				break;
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	public static void SelectLiveFeedOrFullsearch(String Option) {
		try {
			switch(Option.toUpperCase().trim()){
			case"LIVEFEEDS":
				if(s.exists(Patternise("LiveFeedsUnslctd","Strict")) != null) {
					s.click(Patternise("LiveFeedsUnslctd","Strict"));
					Thread.sleep(2000);
					test.pass("Switched to Live Feeds");
				}
				else if(s.exists(Patternise("LiveFeeds","Strict")) != null) {
					s.click(Patternise("LiveFeeds","Strict"));
					test.pass("Live Feeds already displayed");
					Thread.sleep(2000);
				}
				else {
					test.fail("Live Feeds not found");
				}
				Thread.sleep(3000);
				break;
			case"FULLSEARCH":
				if(s.exists(Patternise("FullSearchUnslctd","Strict")) != null) {
					s.click(Patternise("FullSearchUnslctd","Strict"));
					Thread.sleep(2000);
					test.pass("Switched to Full Search");
				}
				else if(s.exists(Patternise("FullSearch","Strict")) != null) {
					s.click(Patternise("FullSearch","Strict"));
					test.pass("Full Search already displayed");
					Thread.sleep(2000);
				}
				else {
					test.fail("Full Search not found");
				}
				Thread.sleep(3000);
				break;
			default:
				test.fail("Invalid option passed, please pass either of LIVEFEEDS or FULLSEARCH");
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	public static void ClickFullSearchbutton(){
		try {
			int Count=0;
			s.click(Patternise("SearchBtnFullSearch","Strict"));
			while(s.exists(Patternise("SearchResultBlue","Strict")) != null) {
				Thread.sleep(2000);
				Count++;
				if(Count>6) {
					break;
				}
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
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
	public static void SetHighContrast(String OnOff) {
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		try {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			switch(OnOff.toUpperCase()) {
			case "ON":
				if(s.exists(Patternise("HighContrastOFF","Strict")) != null) {
					s.click(Patternise("HighContrastOFF","Strict"));
					test.pass("High Contrast checkbox turned ON");
				}
				else if(s.exists(Patternise("HighContrastON","Strict")) != null) {
					test.pass("High Contrast checkbox already ON");
				}
				else {
					test.fail("High Contrast checkbox not found");
				}
				Thread.sleep(3000);
				break;
			case "OFF":
				if(s.exists(Patternise("HighContrastON","Strict")) != null) {
					s.click(Patternise("HighContrastON","Strict"));
					test.pass("High Contrast checkbox turned OFF");
				}
				else if(s.exists(Patternise("HighContrastOFF","Strict")) != null) {
					test.pass("High Contrast checkbox already OFF");
				}
				else {
					test.fail("High Contrast checkbox not found");
				}
				Thread.sleep(3000);
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
	public static void EnterAlert(String Alerttext) {
		try {
				Region r;
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String timeNdate=(timestamp+"").replace(":","");
				if(s.exists(Patternise("NoHeadlines","Moderate"),4)!=null || s.exists(Patternise("NoHeadlinesHC","Moderate"),4)!=null ) {
					test.fail("No headlines found, No templates seen to enter Alert text");
				}
				else {
						if(Alerttext.toUpperCase().contains("HIGHCONTRASTQUICKPUBLISH")) {
							r=new Region(s.find(Patternise("QPcharsHC","Strict")).getX()-80, s.find(Patternise("QPcharsHC","Strict")).getY()+5, 1, 1);
						}
						else if(Alerttext.toUpperCase().contains("HIGHCONTRAST")) {
							 r=new Region(s.find(GetProperty("charsHC")).getX()-80, s.find(GetProperty("charsHC")).getY()+32, 1, 1);	
						}
						else {
							r=new Region(s.find(GetProperty("chars")).getX()-80, s.find(GetProperty("chars")).getY()+32, 1, 1);
						}
						r.click();
						Thread.sleep(2000);
						s.type("a", KeyModifier.CTRL);
						Thread.sleep(2000);
						s.keyDown(Key.BACKSPACE);
						s.keyUp(Key.BACKSPACE);
						if(Alerttext.equals("")) {
							s.type(Alerttext);
							test.pass("Entered Alert text: "+Alerttext);
						}
						else {
							s.type(Alerttext+timeNdate);
							test.pass("Entered Alert text: "+Alerttext+timeNdate);
						}
						Thread.sleep(3000);
				}
			}
			
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	

	
	
}
