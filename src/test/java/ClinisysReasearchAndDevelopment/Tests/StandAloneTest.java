package ClinisysReasearchAndDevelopment.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ClinisysReasearchAndDevelopment.PageObjects.LoginPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Adding comment to test cicd pipeline via webhook

		final String product = "IPHONE 13 PRO";
		final String exactMessage = "Thankyou for the order.";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LoginPage loginPage = new LoginPage(driver);

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("saikatdas066@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("me@Test012");
		driver.findElement(By.name("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		WebElement product1 = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(product)).findFirst()
				.orElse(null);

		product1.findElement(By.cssSelector(".card-body button:last-child")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// .ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// System.out.println(cartProducts.size());

		boolean isProductPresent = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(product));
		Assert.assertTrue(isProductPresent);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user__address")));
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "ind").build().perform();
		
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String outcome = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(outcome.equalsIgnoreCase(exactMessage));

		driver.quit();
	}

}
