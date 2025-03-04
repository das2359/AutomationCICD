package ClinisysReasearchAndDevelopment.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClinisysReasearchAndDevelopment.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\ClinisysResearchAndDevelopment\\Resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		
		System.out.println("browser name - " + browserName);

		if (browserName.contains("chrome")) {

			// Implementing code for headless execution - using ChromeOptions class
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);

			// This line of code helps to redirect to full-screen mode
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("edge")) {

			// Edge code
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getDataFromJson() throws IOException {

		// Read JSON to String
		String dataFromJson = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")
						+ "\\src\\test\\java\\ClinisysResearchAndDevelopment\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// String to List of HashMaps using Jackson Databind
		ObjectMapper dMap = new ObjectMapper();
		List<HashMap<String, String>> list = dMap.readValue(dataFromJson,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return list;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToURL();
		return loginPage;

	}

	@AfterMethod(alwaysRun = true)
	public void quitBrowser() {

		driver.quit();
	}

	// Taking screenshot for test failure
	public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot shot = (TakesScreenshot) driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);

		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}

}
