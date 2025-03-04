package ClinisysResearchAndDevelopment.AbstractObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ClinisysReasearchAndDevelopment.PageObjects.CartPage;
import ClinisysReasearchAndDevelopment.PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
	}

	public void waitForElementVisibility(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementInvisibility(WebElement element) throws InterruptedException {

		Thread.sleep(3000);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(element));
		 */
	}
	
	public CartPage goToCartPage() {
		
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		
		ordersButton.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

}
