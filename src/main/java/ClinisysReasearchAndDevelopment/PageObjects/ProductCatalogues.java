package ClinisysReasearchAndDevelopment.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ClinisysResearchAndDevelopment.AbstractObjects.AbstractComponent;

public class ProductCatalogues extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogues(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> product;

	/*
	 * @FindBy(css = ".card-body button:last-child") WebElement addToCartButton;
	 */
	
	By addToCartButton = By.cssSelector(".card-body button:last-child");

	By locator = By.cssSelector(".col-lg-4");

	By toast = By.cssSelector("#toast-container");

	@FindBy(css=".ng-animating")
	WebElement invisibleElement;
	
	//For doubt clearing - will be deleted
	//WebElement invisibleElement2 = driver.findElement(By.cssSelector(".ng-animating"));

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;

	public List<WebElement> getProductList() {

		waitForElementVisibility(locator);
		return product;
	}

	public WebElement selectProduct(String productName) {

		WebElement prodToBeAdded = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prodToBeAdded;
	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prodToBeAdded = selectProduct(productName);
		prodToBeAdded.findElement(addToCartButton).click();
		waitForElementVisibility(toast);
		waitForElementInvisibility(invisibleElement);
	}

}
