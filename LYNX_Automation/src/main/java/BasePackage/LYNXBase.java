package BasePackage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LYNXBase {

	public static FileReader LYNXReader;
	public static Properties LYNXProp;
	public static Screen s;
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static App lynxapp;
	public static String folder;
	
	public  LYNXBase() {
		
		try {
			//folder="C:\\Users\\X023840\\eclipse-workspace\\LYNX_Automation\\src\\test\\resources\\";
			folder="C:\\Users\\X023840\\git\\Fastwire\\LYNX_Automation\\src\\test\\resources\\";
			LYNXReader = new FileReader(folder+"TestData\\LYNX.properties");
			LYNXProp = new Properties();
			LYNXProp.load(LYNXReader);
			s = new Screen();
			lynxapp = new App(LYNXProp.getProperty("iLYNX"));
			htmlReport = new ExtentHtmlReporter("LYNX_Automation_Report.html");
			ExtentHtmlReporterConfiguration config = htmlReport.config();
			config.setTheme(Theme.DARK);
			config.setReportName("LYNX Fastwire Automation Test Report");
			config.setDocumentTitle(LYNXProp.getProperty("LynxVersion"));
			config.setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ExtentReports getInstance() {
        if(extent == null) {
            InitialiseLYNXBase();
        }   
        return extent;
    }

	private static void InitialiseLYNXBase() {
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
	}
	
	public static void RelaunchReopenFWTab(ExtentTest test, String option) throws FindFailed, InterruptedException {
//		Pattern pattern;
//		pattern = new Pattern(GetProperty("LynxTskBr")).exact();
//		if(s.exists(pattern)!=null) {
//			s.click(pattern);
//		}
		lynxapp.focus();
		Thread.sleep(4000);
		if(option=="Reopen" && s.exists(GetProperty("LYNXEDITORLOGO"))!=null) {
			
//				if(s.exists(GetProperty("LYNXEDITORLOGO"))!=null) {
				if(s.exists(GetProperty("Fastwiretab"))!=null) {
					while (s.exists(GetProperty("Fastwiretab"))!=null) {
						if (s.exists(GetProperty("FWTabClose"))!=null) {
							s.click(GetProperty("FWTabClose"));
						}
						if (s.exists(GetProperty("FWTabCloseunfocused")	)!=null) {
							s.click(GetProperty("FWTabCloseunfocused"));
						}
					}
				}
			    s.keyDown(Key.CTRL);
				s.keyDown(Key.SHIFT);
				s.type("F");
				s.keyUp(Key.CTRL);
				s.keyUp(Key.SHIFT);
				if(s.wait(GetProperty("Fastwiretab"),5)!=null ) {
					test.pass("Successfully opened new Fastwire tab");
				}
				else {
					test.fail("Unable to relaunch Fastwire tab");
				}
				Thread.sleep(10000);
//				}
//				else {
//					test.fail("Unable to relaunch Fastwire tab as application is not opened");
//				}
		 }
		 else if (option=="Relaunch" || (option=="Reopen" && s.exists(GetProperty("LYNXEDITORLOGO"))==null)) {
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
				if(s.wait(GetProperty("Fastwiretab"),5)!=null ) {
					test.pass("Successfully opened new Fastwire tab");
				}
				else {
					test.fail("Unable to relaunch Fastwire tab");
				}
				Thread.sleep(10000);
		 }
	}
	public static String GetProperty(String Prop) {
		return folder+LYNXProp.getProperty(Prop);		
	}
	public static void UncheckBoxes(ExtentTest test) {
		Pattern pattern;
		try {
			pattern = new Pattern(GetProperty("CheckedBox")).exact();
			while (s.exists(pattern) != null) {
					s.click(pattern);
					Thread.sleep(4000);
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	public static void ClickonOccurence(String imagename, int occurence) {
		int i=1;
		try {	
		Iterator<Match> it = s.findAll(imagename);
		    while(it.hasNext()){
		    	if (i==occurence) {
		    	//it.next().highlight(i);
		    	System.out.println(i);
		    	it.next().click(occurence);
		        }
		        i++;
		        
		    }
		}
		catch(Exception e) {
			test.fail("Unable to find selected occurence of object to click");
		}
	}
	public static void Scrolltoend(String Value) {
		test.log(com.aventstack.extentreports.Status.INFO,"Scrolltoend Method called");
		while(s.exists(GetProperty("EndOfDownScroll"))!=null ) {
			s.keyDown(Key.PAGE_DOWN);
			s.keyUp(Key.PAGE_DOWN);
			if(s.exists(GetProperty("UpdateAlarm"))!=null && Value=="Update") {
				test.pass("Scrolled to end of Page");
				break;
					
			}
		    if(s.exists(GetProperty("CreateAlarm"))!=null && Value=="Create") {
		    	test.pass("Scrolled to end of Page");
				break;
			}
		}
	}
	public static void OpenUserPrfrncs(ExtentTest test, String Option) {
		test.log(com.aventstack.extentreports.Status.INFO,"OpenUserPrfrncsFeeds Method called");
		Pattern pattern1,pattern2;
		try {
				s.find(GetProperty("LYNXEDITORLOGO")).rightClick();
				test.pass("Right Clicked Lynx Fastwire icon");
				Thread.sleep(4000);
				s.find(GetProperty("Fastwire_Preferences")).click();
				test.pass("Clicked Fastwire Preferences icon");
				Thread.sleep(8000);
				if (s.exists(GetProperty("FWPrfrncstab"),5)!=null) {
					test.pass("Fastwire Preference options window loaded");
					Thread.sleep(1000);
				}
				else {
					test.fail("Fastwire Preference options window not loaded");
				}
				 if (s.exists(GetProperty("FltrSrcsArwClsd"),5)!=null) {
					 	if (s.exists(GetProperty("FltrSrcsSlctd"),5)!=null) {
					 		s.find(GetProperty("FltrSrcsSlctd")).click();
							test.pass("Found and expanded Filters Sources Link");
							Thread.sleep(2000);
						}
					 	else if(s.exists(GetProperty("FltrSrcs"),5)!=null) {
					 		s.find(GetProperty("FltrSrcs")).click();
							test.pass("Found and expanded Filters Sources Link");
							Thread.sleep(2000);
					 	}
						else {
							test.fail("Filter Sources Option not found");
						}
						
				 }
				switch(Option) {
					case "Feeds":
								
								if (s.exists(GetProperty("FeedsSlctd"),5)!=null) {
									test.pass("Filters Sources - Feeds Option already selected");
								}
								else if(s.exists(GetProperty("Feeds"),5)!=null) {
									s.find(GetProperty("Feeds")).click();
									test.pass("Found and clicked Filters Sources - Feeds Option");
									Thread.sleep(3000);
								}
								else {
									test.fail("Filters Sources - Feeds Option not found");
								}
								pattern1 = new Pattern(GetProperty("EnblFltrs")).exact();
								pattern2 = new Pattern(GetProperty("EnblFltrsSlctd")).exact();
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
				                break;
					case "Automations":
								if (s.exists(GetProperty("AutmtnSlctd"),5)!=null) {
									test.pass("Filters Sources - Automations Option already selected");
								}
								else if(s.exists(GetProperty("Autmtn"),5)!=null) {
									s.find(GetProperty("Autmtn")).click();
									test.pass("Found and clicked Filters Sources - Automations Option");
									Thread.sleep(3000);
								}
								else {
									test.fail("Filters Sources - Automations Option not found");
								}
								
				                break;
				}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}

	
}
