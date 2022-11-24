package testPackage;

import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TEMP extends BasePackage.LYNXBase {
	public TEMP() {
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
	public void ValidatePassword(String pwd) throws FindFailed, InterruptedException {
		test = extent.createTest(MainRunner.TestID,MainRunner.TestDescription);
		test.log(com.aventstack.extentreports.Status.INFO,"ValidatePassword Method called");
		if (s.exists(GetProperty("iPass")) != null) {
				s.type(GetProperty("iPass"),pwd);
				test.pass("Provided User name and Password");
		} 
		 else {
				test.fail("Password field doesnot exist");
		}
		//Headline_Alarm.Login();

	}

}
