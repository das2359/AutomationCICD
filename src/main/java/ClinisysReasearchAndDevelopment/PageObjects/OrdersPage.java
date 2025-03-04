package ClinisysReasearchAndDevelopment.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ClinisysResearchAndDevelopment.AbstractObjects.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsOnOrdersPage;

	public OrdersPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyProductOnOrdersPage(String productName) {
		
		boolean isProductAvailable = productsOnOrdersPage.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return isProductAvailable;
	}
	
	

}
