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
import org.sikuli.script.Region;
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
			//htmlReport = new ExtentHtmlReporter("LYNX_Automation_Report "+java.time.LocalDate.now()+".html");
			htmlReport = new ExtentHtmlReporter("LYNX_Automation_Report "+java.time.LocalDate.now()+".html");
			htmlReport.setAppendExisting(true);
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
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try{
			lynxapp.focus();
			int count=0;
			//Thread.sleep(4000);
			if(option.equals("Reopen") && s.exists(GetProperty("LYNXEDITORLOGO"))!=null) {
				
	//				if(s.exists(GetProperty("LYNXEDITORLOGO"))!=null) {
					if(s.exists(GetProperty("Fastwiretab"))!=null) {
						while (s.exists(GetProperty("Fastwiretab"))!=null) {
							if (s.exists(GetProperty("FWTabClose"))!=null) {
								s.click(GetProperty("FWTabClose"));
								lynxapp.focus();
							}
							if (s.exists(GetProperty("FWTabCloseunfocused")	)!=null) {
								s.click(GetProperty("FWTabCloseunfocused"));
								lynxapp.focus();
							}
						}
					}
					lynxapp.focus();
				    s.keyDown(Key.CTRL);
					s.keyDown(Key.SHIFT);
					s.type("F");
					s.keyUp(Key.CTRL);
					s.keyUp(Key.SHIFT);
					if(s.wait(GetProperty("Fastwiretab"),10)!=null ) {
						test.pass("Successfully opened new Fastwire tab");
					}
					else {
						test.fail("Unable to relaunch Fastwire tab");
					}
					Thread.sleep(10000);
					//test.log(com.aventstack.extentreports.Status.INFO,"RelaunchReopenFWTab Method end");
	//				}
	//				else {
	//					test.fail("Unable to relaunch Fastwire tab as application is not opened");
	//				}
			 }
			 else if ((option.equals("Relaunch")) || (option.equals("Reopen") && s.exists(GetProperty("LYNXEDITORLOGO"))==null)) {
				 	Runtime runtime = Runtime.getRuntime();     //getting Runtime object
					try
			        {	runtime.exec("taskkill /F /IM LYNX.exe");
			            Thread.sleep(2000);
			            lynxapp.open();
					Thread.sleep(10000);
					if (s.wait(GetProperty("iUser"),30) != null) {
						s.find(GetProperty("iUser")).offset(80, 0).click();
						Thread.sleep(2000);
						s.keyDown(Key.CTRL);
						s.type("a");
						Thread.sleep(2000);
						s.keyDown(Key.DELETE);
						s.keyUp(Key.CTRL);
						s.keyUp(Key.DELETE);
						s.type(GetProperty("iUser"), LYNXProp.getProperty("tUser"));
						test.pass("Entered User name");
					} else {
						test.fail("User name field doesnot exist");
					}
					if (s.wait(GetProperty("iPass"),30) != null) {
						test.pass("Launched LYNX Application");
						s.type(GetProperty("iPass"), LYNXProp.getProperty("tPass"));
						test.pass("Entered Password");
					} else {
						test.fail("Password field doesnot exist");
					}
					if (s.exists(GetProperty("iSignOn")) != null) {
						s.find(GetProperty("iSignOn")).click();
						test.pass("Clicked on Sign on button");
					} else {
						test.fail("Sign on button doesnot exist");
					}
					while(s.exists(GetProperty("Controls"))==null && count!=5) {
						Thread.sleep(5000);
						count++;
					}
					//s.wait(GetProperty("Home"),50);
					Thread.sleep(5000);
					if(s.exists(GetProperty("Home1"))!=null || s.exists(GetProperty("Home2"))!=null ) {
						test.pass("Successfully logged into Fastwire");
						s.keyDown(Key.CTRL);
						s.keyDown(Key.SHIFT);
						s.type("F");
						s.keyUp(Key.CTRL);
						s.keyUp(Key.SHIFT);
						//Thread.sleep(5000);
						if(s.exists(GetProperty("Fastwiretab"),15)!=null || s.exists(GetProperty("Fastwiretabunfocused"),15)!=null ) {
							test.pass("Successfully relaunched and opened new Fastwire tab");
							Thread.sleep(5000);
						}
						else {
							test.fail("Unable to relaunch Fastwire tab");
						}
					}
					else {
						test.fail("Unable to login to Fastwire");
					}
			      }
					catch(Exception e) {
						test.fail("Error Occured: "+e.getLocalizedMessage());
					}
			 }
			 else {
				 System.out.println("Issue.. You Entered Else!!!");
			 }
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	public static String GetProperty(String Prop) {
		return folder+LYNXProp.getProperty(Prop);		
	}
	public static void UncheckBoxes(ExtentTest test) {
		Pattern pattern;
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
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
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	public static void ValidateObjectDisplayed(ExtentTest test, String Objectref,String Objectname, int Click, String mode) {
		Pattern pattern;
		int count=0,found=0;
		try {
			//test.log(com.aventstack.extentreports.Status.INFO,"ValidateObjectDisplayed method begin");
			pattern = new Pattern(GetProperty(Objectref)).exact();
			while (s.exists(pattern) == null && count<=5) {
				if(s.exists(pattern) != null) {
					found=1;
					break;
				}
				else {
					count++;
					Thread.sleep(2000);
				}
			}
			switch (mode)
			{
			case "Normal":
							if((found==1 || s.exists(pattern) != null) && Click==1) {
								s.click(pattern);
								test.pass("Clicked on "+Objectname);
									//Thread.sleep(2000);
							}
							else if((found==1 || s.exists(pattern) != null) && Click==0) {
								test.pass(Objectname +" is displayed correctly on screen");
							}
							else {
								test.fail(Objectname+" is not displayed on the screen");
							}
							break;
			case "Reverse":
				
				if((found==1 || s.exists(pattern) != null)) {
					test.fail(Objectname +" is displayed on screen");
				}
				else {
					test.pass(Objectname+" is not displayed on the screen");
				}
				break;
			}
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			//test.log(com.aventstack.extentreports.Status.INFO,"ValidateObjectDisplayed method end");
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
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		try{
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
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	public static void OpenUserPrfrncs(ExtentTest test, String Option) {
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
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
				 
				switch(Option) {
					case "Feeds":
								OpenFilterSources(test);
								if (s.exists(GetProperty("FeedsSlctd"),5)!=null) {
									test.pass("Filters Sources - Feeds Option already selected");
								}
								else if(s.exists(GetProperty("Feeds"),5)!=null) {
									s.find(GetProperty("Feeds")).click();
									test.pass("Found and clicked Filters Sources - Feeds Option");
									Thread.sleep(7000);
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
								OpenFilterSources(test);
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
					case "HeadlineAlarm":
						if (s.exists(GetProperty("HdlnAlrmsred"),5)!=null) {
							test.pass("Headline Alarms Option already selected");
						}
						else if(s.exists(GetProperty("HdlnAlrms"),5)!=null) {
							s.find(GetProperty("HdlnAlrms")).click();
							test.pass("Found and clicked Headline Alarms Option");
							Thread.sleep(3000);
						}
						else {
							test.fail("Headline Alarms Option not found");
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

	public static void  OpenFilterSources(ExtentTest test) {
		try {
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
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());
		}
	}
	public static void ClearMetaData() {
		String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
		test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" Method begin");
		Region r;
		try {
			if(s.exists(Patternise("Show"))!=null) {
				s.wait(Patternise("Show"),5).click();
			}
			s.find(GetProperty("AlertEditorTab")).click();
			if(s.exists(Patternise("BlankAEMetadata"))!=null) {
				test.pass("Alert editor Metadata already empty");
				return;
			}
			for (int i=s.find(Patternise("RIC_Unslctd")).getY()+10; i>= 455;i-=18) {
				//System.out.println(s.find(GetProperty("RIC")).getY());
				r=new Region(980, i, 1, 1);
				System.out.println("0");
				r.click();
				for (int j=0;j<20;j++) {
					s.keyDown(Key.BACKSPACE);
					s.keyUp(Key.BACKSPACE);
				}
				Thread.sleep(5000);
				if(s.exists(Patternise("RIC"))!=null) {
						if (i>s.find(Patternise("RIC")).getY()) {
							i=s.find(Patternise("RIC")).getY();
						}
				}
				else if(s.exists(Patternise("RIC_Unslctd"))!=null) {
						if (i>s.find(Patternise("RIC_Unslctd")).getY()) {
							i=s.find(Patternise("RIC_Unslctd")).getY();
						}
				}
				
			}
			r=new Region(s.find(Patternise("GetUSN")).getX()-25,s.find(Patternise("GetUSN")).getY()+10 , 1, 1);
			System.out.println("1");
			r.click();
			for (int j=0;j<15;j++) {
				s.keyDown(Key.BACKSPACE);
				s.keyUp(Key.BACKSPACE);
			}
			Thread.sleep(4000);
			r=new Region(s.find(Patternise("GetUSN")).getX()-15,s.find(Patternise("GetUSN")).getY()+40, 1, 1);
			System.out.println("2");
			r.click();
			for (int j=0;j<10;j++) {
				s.keyDown(Key.BACKSPACE);
				s.keyUp(Key.BACKSPACE);
			}
			s.find(GetProperty("AlertEditorTab")).click();
			Thread.sleep(6000);
			
			if (s.exists(Patternise("BlankProducts")) != null) {
				test.pass("Product cleared");
			}
			else {
				test.fail("Product not cleared");
			}
			if (s.exists(Patternise("BlankTopics")) != null) {
				test.pass("Topics Cleared");
			}
			else {
				test.fail("Topics not cleared");
			}
			if (s.exists(Patternise("BlankRICS")) != null) {
				test.pass("RICs Cleared");
			}
			else {
				test.fail("RICs not cleared");
			}
			if (s.exists(Patternise("BlankUSN")) != null) {
				test.pass("USN Cleared");
			}
			else {
				test.fail("USN not cleared");
			}
			if (s.exists(Patternise("BlankNamedItems")) != null) {
				test.pass("NamedItems Cleared");
			}
			else {
				test.fail("NamedItems not cleared");
			}
			
		} 
		catch (Exception e) {
			test.error("Error occured :"+ e.getMessage());
		}
		finally {
			test.log(com.aventstack.extentreports.Status.INFO,nameofCurrMethod+" method end");
		}
	}
	
	@SuppressWarnings("finally")
	public static Pattern Patternise(String Obj) {
		Pattern pattern = null;
		try {
			pattern=new Pattern(GetProperty(Obj)).exact();
			return pattern;
		}
		catch(Exception e) {
			test.fail("Error Occured: "+e.getLocalizedMessage());			
		}
		finally {
			return pattern;
		}
	}
	
}
