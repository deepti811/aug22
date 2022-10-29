
  package com.firebase.test.utility;
  
  import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
  
  public class GenerateReports {
  
  ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static GenerateReports ob;
	private GenerateReports()
	{
		
	}
public static GenerateReports getInstance() {
	if(ob==null) {
		ob=new GenerateReports();
	}
	return ob;	
}
  public void startExtentReport()
  {
	  htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "deepti@tekarch.com");
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("SalesForce Regression Test");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
  }
  public void startSingleTestReport(String testcaseName )
  {
	logger = extent.createTest(testcaseName);
  }
  public void logTestInfo(String message )
  {
	logger.log(Status.INFO, message);
  }
  public void logTestpassed(String message )
  {
	logger.log(Status.PASS, MarkupHelper.createLabel(message + "is passTest",ExtentColor.GREEN));
  }
  public void logTestFailed(String message )
  {
	logger.log(Status.FAIL, MarkupHelper.createLabel(message + "is not passTest",ExtentColor.RED));
  }
  public void logTestFailedWithExcetion(Exception e )
  {
		logger.log(Status.ERROR, e);
  }
  public void logTestSkipped(String message )
  {
	logger.log(Status.SKIP, MarkupHelper.createLabel(message + "skipped the Test",ExtentColor.GREEN));
  }
  public void endReport()
  {
	  extent.flush();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  }