package testPackage;

import java.io.IOException;

import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class HeadLineSettings22797 extends BasePackage.LYNXBase {
		public HeadLineSettings22797() {
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
		public static void SetHeadlineTextColor() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			//RelaunchFWTab(test);
			RelaunchReopenFWTab(test,"Reopen");
			CreateAlarm(test);
			try {
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				Scrolltoend("Create");
				if (s.exists(GetProperty("TextColorDDN"))==null) {
					test.fail("Text Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("TextColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("TxtSlctClr")).click();
					test.pass("User able to Select a text color");
					Thread.sleep(3000);
				}
				if(s.exists(GetProperty("TxtClrPrvw"),20)!=null ) {
						test.pass("User selected Text Color seen successfully in preview");
				}
				else {
						test.fail("User selected Text Color not seen in preview");
				}
				//s.click(Patternise("CanclAlrm","Exact"));
				//Thread.sleep(3000);
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
			}
			catch(Exception e) {
					test.fail("Error Occured: "+e.getLocalizedMessage());
			}
			finally {
				test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
			}
		}
		
		@Test
		public static void SetHeadlineBGColor() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			//RelaunchFWTab(test);
			RelaunchReopenFWTab(test,"Reopen");
			CreateAlarm(test);
			try {
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				Scrolltoend("Create");
				//Set Background Color
				if (s.exists(GetProperty("BGColorDDN"))==null) {
					test.fail("Background Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BGColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("BGSlctclr")).click();
					test.pass("User able to Select a background color");
					Thread.sleep(3000);
				}
				if(s.exists(GetProperty("BGClrPrvw"),20)!=null ) {
					test.pass("User selected Background Color seen successfully in preview");
				}
				else {
					test.fail("User selected Background Color not seen in preview");
				}
				//s.click(GetProperty("CanclAlrm"));
				//s.click(Patternise("CanclAlrm","Exact"));
				//Thread.sleep(3000);
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
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
		public static void SetHeadlineBold(String Boldonoff) throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			//RelaunchFWTab(test);
			try {
				RelaunchReopenFWTab(test,"Reopen");
				CreateAlarm(test);
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				Scrolltoend("Create");
				//Set Background Color
				if (s.exists(GetProperty("BGColorDDN"))==null) {
					test.fail("Background Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BGColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("BGSlctclr")).click();
					test.pass("User able to Select a background color");
					Thread.sleep(3000);
				}
				//Set Bold
				if (s.exists(GetProperty("BoldOFF"))==null) {
					test.fail("Bold selector not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					if(Boldonoff=="ON") {
						s.find(GetProperty("BoldOFF")).click();
						Thread.sleep(2000);
						if(s.exists(GetProperty("BGClrPrvw"),20)==null ) {
						test.pass("User able to turn on Bold selector");
						}
						else{
							test.fail("User unable to turn on Bold selector");
							
						}
					}
				}
				s.find(GetProperty("CreateAlarm")).click();
				test.pass("Clicked on CreateAlarm Button");
				if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
					test.pass("Alarm Created Successfully");
				}
				else {
					test.fail("Unable to find newly created Alarm");
				}
				if(Boldonoff=="ON") {
					if(s.exists(GetProperty("PrvBoldOn"),20)!=null ) {
						test.pass("User selected Bold font applied to Headline Alarm");
						DeleteAlarm(test);
					}
					else {
						test.fail("User selected Bold not font applied to Headline Alarm");
					}
				}
				else if(Boldonoff=="OFF") {
					if(s.exists(GetProperty("PrvBoldOff"),20)!=null ) {
						test.pass("User able to create Headline Alarm without Bold");
						DeleteAlarm(test);
					}
					else {
						test.fail("User unable to create Headline Alarm without Bold");
					}
				}
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
			finally {
				test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
			}
		}
		
		@Test
		public static void EditExistingHeadlineHighlights() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			//RelaunchFWTab(test);
			try {
				RelaunchReopenFWTab(test,"Reopen");
				CreateAlarm(test);
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				Scrolltoend("Create");
				//Set Text Color
				if (s.exists(GetProperty("TextColorDDN"))==null) {
					test.fail("Text Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					s.find(GetProperty("TextColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("TxtSlctClr")).click();
					test.pass("User able to Select a text color");
					Thread.sleep(3000);
				}
				if(s.exists(GetProperty("TxtClrPrvw"),20)!=null ) {
						test.pass("User selected Text Color seen successfully in preview");
				}
				else {
						test.fail("User selected Text Color not seen in preview");
				}
				//Set Background Color
				if (s.exists(GetProperty("BGColorDDN"))==null) {
					test.fail("Background Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BGColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("BGSlctclr")).click();
					test.pass("User able to Select a background color");
					Thread.sleep(3000);
				}
				//Set Bold
				if (s.exists(GetProperty("BoldOFF"))==null) {
					test.fail("Bold selector not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BoldOFF")).click();
					Thread.sleep(3000);
					if(s.exists(GetProperty("BGClrPrvw"),20)==null ) {
					test.pass("User able to turn on Bold selector");
					}
					else{
						test.fail("User unable to turn on Bold selector");
						
					}
				}
				s.find(GetProperty("CreateAlarm")).click();
				test.pass("Clicked on CreateAlarm Button");
				if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
					test.pass("Alarm Created Successfully");
				}
				else {
					test.fail("Unable to find newly created Alarm");
				}
				Thread.sleep(8000);
				s.find(GetProperty("AddedAlarm")).click();
				Thread.sleep(10000);
				s.mouseDown(Button.LEFT);
				s.mouseUp(Button.LEFT);
				
				Scrolltoend("Update");
				//Edit BG Color
				s.find(GetProperty("BGColorDDN_Slctd")).click();
				Thread.sleep(3000);
				s.find(GetProperty("TxtSlctClr")).click();
				test.pass("User edited a background color");
				Thread.sleep(3000);
				//Edit Text Color
				s.find(GetProperty("TextColorDDN_Slctd")).click();
				Thread.sleep(3000);
				s.find(GetProperty("BGSlctclr")).click();
				test.pass("User edited a text color");
				//Edit Bold Property
				s.find(GetProperty("BoldON")).click();
				Thread.sleep(3000);
				s.find(GetProperty("UpdateAlarm")).click();
				Thread.sleep(3000);
				if(s.exists(GetProperty("AftrEdtPrvw"),20)!=null ) {
					test.pass("User edits to Highlights updated to Headline Alarm successfully");
					DeleteAlarm(test);
				}
				else {
					
					test.fail("User edits to Highlights not updated to Headline Alarm");
				}
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
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
		public static void HeadlineOptionReopenRelaunch(String option) throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			//RelaunchFWTab(test);
			try {
				RelaunchReopenFWTab(test,"Reopen");
				CreateAlarm(test);
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				Scrolltoend("Create");
				//Set Text Color
				if (s.exists(GetProperty("TextColorDDN"))==null) {
					test.fail("Text Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					s.find(GetProperty("TextColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("TxtSlctClr")).click();
					test.pass("User able to Select a text color");
					Thread.sleep(3000);
				}
				if(s.exists(GetProperty("TxtClrPrvw"),20)!=null ) {
						test.pass("User selected Text Color seen successfully in preview");
				}
				else {
						test.fail("User selected Text Color not seen in preview");
				}
				//Set Background Color
				if (s.exists(GetProperty("BGColorDDN"))==null) {
					test.fail("Background Color Dropdown not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BGColorDDN")).click();
					Thread.sleep(3000);
					s.find(GetProperty("BGSlctclr")).click();
					test.pass("User able to Select a background color");
					Thread.sleep(3000);
				}
				//Set Bold
				if (s.exists(GetProperty("BoldOFF"))==null) {
					test.fail("Bold selector not found");
				}
				else
				{	
					Thread.sleep(2000);
					//s.type(getElement("HdlnAlrmSrcs"), getElement("Source"));
					s.find(GetProperty("BoldOFF")).click();
					Thread.sleep(3000);
					if(s.exists(GetProperty("BGClrPrvw"),20)==null ) {
					test.pass("User able to turn on Bold selector");
					}
					else{
						test.fail("User unable to turn on Bold selector");
						
					}
				}
				s.find(GetProperty("CreateAlarm")).click();
				test.pass("Clicked on CreateAlarm Button");
				if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
					test.pass("Alarm Created Successfully");
				}
				else {
					test.fail("Unable to find newly created Alarm");
				}
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(4000);
				if (option=="Relaunch") {
					RelaunchReopenFWTab(test,"Relaunch");
				}
				s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
				test.pass("Right Clicked Fastwire Preferences icon");
				Thread.sleep(4000);
				s.find(GetProperty("Fastwire_Preferences")).click();
				test.pass("Clicked Fastwire Preferences icon");
				Thread.sleep(10000);
				if (s.exists(GetProperty("Menu_FWPrfrncsred"))!=null) {
				}
				else if(s.exists(GetProperty("Menu_FWPrfrncs"))!=null) {
					s.find(GetProperty("Menu_FWPrfrncs")).click();
					test.pass("Expanded Fastwire Preferences icon");
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire Preferences icon not found");
				}
				
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
				if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
					test.pass("Alarm found Successfully after "+option+" preferences");
				}
				else {
					test.fail("Unable to find newly created Alarm after "+option+" preferences");
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
		public static void Verify_Headline_Search(String Searchtext) throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			try {
				if(s.exists(GetProperty("Fastwiretab"))==null && s.exists(GetProperty("LYNXEDITORLOGO"))!=null) {
					RelaunchReopenFWTab(test,"Reopen");
				}
				else if(s.exists(GetProperty("Fastwiretab"))==null && s.exists(GetProperty("LYNXEDITORLOGO"))==null) {
					RelaunchReopenFWTab(test,"Relaunch");
				}
				if(s.exists(GetProperty("LiveFeeds"))==null) {
					s.click(Patternise("LiveFeedsUnslctd","Strict"));
					Thread.sleep(2000);
				}
				s.click(Patternise("FindAStory","Strict"));
				s.type(Searchtext.replace("\"",""));
				Thread.sleep(3000);
				if(s.exists(Patternise("DATE","Strict"))==null) {
					test.pass("Able to search successfully");
				}
				else {
					test.fail("Unable to search using search text: "+ Searchtext);
				}
				s.click(Patternise("LiveFeeds","Strict"));
				Thread.sleep(2000);
				
			}
			catch(Exception e) {
					test.fail("Error Occured: "+e.getLocalizedMessage());
			}
			finally {
				test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
			}
		}
		
		
		public static void DeleteAlarm(ExtentTest test) {
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			try {
				s.click(GetProperty("DeleteAlrm"));
				Thread.sleep(2000);
				s.click(GetProperty("CnfrmDelAlrm"));
				Thread.sleep(2000);
				test.pass("Successfully deleted the alarm");
			} catch (Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
			finally {
				test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
			}			
		}
		
		public static void CreateAlarm(ExtentTest test) {
			String nameofCurrMethod = new Throwable()
	                 .getStackTrace()[0]
	                 .getMethodName();
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
			try {
					s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
					test.pass("Right Clicked Fastwire Preferences icon");
					Thread.sleep(4000);
					s.find(GetProperty("Fastwire_Preferences")).click();
					test.pass("Clicked Fastwire Preferences icon");
					Thread.sleep(10000);
					if (s.exists(GetProperty("Menu_FWPrfrncsred"))!=null) {
					}
					else if(s.exists(GetProperty("Menu_FWPrfrncs"))!=null) {
						s.find(GetProperty("Menu_FWPrfrncs")).click();
						test.pass("Expanded Fastwire Preferences icon");
						Thread.sleep(1000);
					}
					else {
						test.fail("Fastwire Preferences icon not found");
					}
					
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
					while (s.exists(GetProperty("DeleteAlrm"))!=null) {
						DeleteAlarm(test);
					}
					s.find(GetProperty("AddHdlnAlrm")).click();
					test.pass("Clicked Add Headline Alarms link");
					Thread.sleep(4000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
			finally {
				test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
			}
		}
		
	}
