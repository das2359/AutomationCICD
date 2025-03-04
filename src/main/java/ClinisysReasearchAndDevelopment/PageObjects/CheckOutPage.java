package ClinisysReasearchAndDevelopment.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ClinisysResearchAndDevelopment.AbstractObjects.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	By address = By.cssSelector(".user__address");

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submitButton;

	

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		waitForElementVisibility(address);
		act.sendKeys(country, countryName).build().perform();
		selectCountry.click();
	}

	public ConfirmationPage goToConfirmationPage() {

		submitButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	

}
