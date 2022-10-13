package testPackage;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
			test.log(com.aventstack.extentreports.Status.INFO,"SetHeadlineTextColor Method called-To verify that user is able to set text color for the headline");
			//RelaunchFWTab(test);
			RelaunchFWTab(test);
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
				s.click(GetProperty("CanclAlrm"));
				Thread.sleep(3000);
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
				}
				catch(Exception e) {
						test.fail("Error Occured: "+e.getLocalizedMessage());
					}
		}
		
		@Test
		public static void SetHeadlineBGColor() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			test.log(com.aventstack.extentreports.Status.INFO,"SetHeadlineBGColor Method called- To verify that user is able to set background color for the headline");
			//RelaunchFWTab(test);
			RelaunchFWTab(test);
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
				s.click(GetProperty("CanclAlrm"));
				Thread.sleep(3000);
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
		}

		@Test
		public static void SetHeadlineBold() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			test.log(com.aventstack.extentreports.Status.INFO,"SetHeadlineBold Method called- To verify that user is able to set headline in bold");
			//RelaunchFWTab(test);
			RelaunchFWTab(test);
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
					if(s.exists(GetProperty("BGClrPrvw"),20)!=null ) {
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
				if(s.exists(GetProperty("PrvBold"),20)!=null ) {
					test.pass("User selected Bold font applied to Headline Alarm");
					s.click(GetProperty("DeleteAlrm"));
					Thread.sleep(2000);
					s.click(GetProperty("CnfrmDelAlrm"));
					Thread.sleep(2000);
				}
				else {
					test.fail("User selected Bold not font applied to Headline Alarm");
				}
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
		}
		
		@Test
		public static void EditExistingHeadlineHighlights() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			test.log(com.aventstack.extentreports.Status.INFO,"EditExistingHeadlineHighlights Method called- To verify that user is able to set color,font options and preview headline for an existing headline alarm ");
			//RelaunchFWTab(test);
			RelaunchFWTab(test);
			CreateAlarm(test);
			try {
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
					if(s.exists(GetProperty("BGClrPrvw"),20)!=null ) {
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
					s.click(GetProperty("DeleteAlrm"));
					Thread.sleep(2000);
					s.click(GetProperty("CnfrmDelAlrm"));
					Thread.sleep(2000);
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
		}
		@Test
		public static void SetHeadlineOptions() throws FindFailed, InterruptedException {
			test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
			test.log(com.aventstack.extentreports.Status.INFO,"SetHeadlineOptions Method called");
			//RelaunchFWTab(test);
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
					s.click(GetProperty("DeleteAlrm"));
					Thread.sleep(2000);
					s.click(GetProperty("CnfrmDelAlrm"));
					Thread.sleep(2000);
				}
				s.find(GetProperty("AddHdlnAlrm")).click();
				test.pass("Clicked Add Headline Alarms link");
				Thread.sleep(4000);
				s.type(GetProperty("AlarmName"), "TestAlarm");
				test.pass("Entered Alarm Name");
				while(s.exists(GetProperty("EndOfDownScroll"))!=null) {
					s.keyDown(Key.PAGE_DOWN);
					s.keyUp(Key.PAGE_DOWN);
					if(s.exists(GetProperty("CreateAlarm"))!=null) {
						break;
					}
				}
				//Set Text Color
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
				s.find(GetProperty("CreateAlarm")).click();
				test.pass("Clicked on CreateAlarm Button");
				if(s.exists(GetProperty("AddedAlarm"),20)!=null ) {
					test.pass("Alarm Created Successfully");
				}
				else {
					test.fail("Unable to find newly created Alarm");
				}
				if(s.exists(GetProperty("PrvTxtClr"),20)!=null ) {
					test.pass("User selected text color added to Headline Alarm");
					s.click(GetProperty("DeleteAlrm"));
					Thread.sleep(2000);
					s.click(GetProperty("CnfrmDelAlrm"));
					Thread.sleep(2000);
				}
				else {
					test.fail("User selected text color not added to Headline Alarm");
				}
				s.find(GetProperty("FWTabClose")).doubleClick();
				s.find(GetProperty("FWTabClose")).doubleClick();
				Thread.sleep(2000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
		}
		public static void CreateAlarm(ExtentTest test) {
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
						s.click(GetProperty("DeleteAlrm"));
						Thread.sleep(2000);
						s.click(GetProperty("CnfrmDelAlrm"));
						Thread.sleep(2000);
					}
					s.find(GetProperty("AddHdlnAlrm")).click();
					test.pass("Clicked Add Headline Alarms link");
					Thread.sleep(4000);
			}
			catch(Exception e) {
				test.fail("Error Occured: "+e.getLocalizedMessage());
			}
		}
		public static void Scrolltoend(String Value) {
			while(s.exists(GetProperty("EndOfDownScroll"))!=null) {
				s.keyDown(Key.PAGE_DOWN);
				s.keyUp(Key.PAGE_DOWN);
				if(s.exists(GetProperty("UpdateAlarm"))!=null && Value=="Update") {
						break;
				}
			    if(s.exists(GetProperty("CreateAlarm"))!=null && Value=="Create") {
						break;
				}
				
			}
		}
	}
