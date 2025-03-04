package ClinisysResearchAndDevelopment.Resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports exReport;

	public static ExtentReports config() {
		
		String filePath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Clinisys tests report");
		reporter.config().setDocumentTitle("Clinisys tests document");
		
		exReport = new ExtentReports();
		exReport.attachReporter(reporter);
		exReport.setSystemInfo("tester", "Saikat Das");
		
		return exReport;
	}

}
