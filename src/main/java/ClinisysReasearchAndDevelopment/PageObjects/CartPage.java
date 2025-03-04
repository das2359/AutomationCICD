package ClinisysReasearchAndDevelopment.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ClinisysResearchAndDevelopment.AbstractObjects.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutButton;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyCartProducts(String productName) {

		boolean isProductPresent = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return isProductPresent;
	}
	
	public CheckOutPage goToCheckOut() {
		
		checkOutButton.click();
		return new CheckOutPage(driver);
	}

}
