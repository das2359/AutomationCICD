package ClinisysReasearchAndDevelopment.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ClinisysResearchAndDevelopment.AbstractObjects.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//one way of keeping e-mail
	//WebElement email = driver.findElement(By.id("userEmail"));
	
	//Other way of keeping e-mail - Using Page Object
	@FindBy(id="userEmail")
	WebElement newEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(name="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public void goToURL() {

		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogues loginIntoApplication(String email, String password) {
		
		newEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogues productCatalogues = new ProductCatalogues(driver);
		return productCatalogues;
		
	}
	
	public String getErrorMessage() throws InterruptedException {
		
		return errorMessage.getText();
	}

	
	
	

}
