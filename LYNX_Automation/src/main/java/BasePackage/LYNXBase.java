package BasePackage;
import java.io.FileReader;
import java.util.Properties;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
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
	
	public static void RelaunchFWTab(ExtentTest test) throws FindFailed, InterruptedException {
		
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
	}
	public static String GetProperty(String Prop) {
		return folder+LYNXProp.getProperty(Prop);		
	}
}
