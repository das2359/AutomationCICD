package ClinisysReasearchAndDevelopment.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ClinisysReasearchAndDevelopment.PageObjects.CartPage;
import ClinisysReasearchAndDevelopment.PageObjects.CheckOutPage;
import ClinisysReasearchAndDevelopment.PageObjects.ConfirmationPage;
import ClinisysReasearchAndDevelopment.PageObjects.LoginPage;
import ClinisysReasearchAndDevelopment.PageObjects.ProductCatalogues;
import ClinisysReasearchAndDevelopment.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LoginPage loginPage;
	public ProductCatalogues productCatalogues;
	public ConfirmationPage confirmationPage;
	public WebElement ele;

	@Given("I logged in the website")
	public void I_logged_in_the_website() throws IOException {
		
		loginPage = launchApplication();
	}
	
	//Given I logged in with valid <username> & <password>
	@Given("^I logged in with valid (.+) & (.+)$")
	public void I_logged_in_with_valid_credentials(String username, String password) {
		
		productCatalogues = loginPage.loginIntoApplication(username, password);
	}
	
	//When I select <productName> in cart page
	@When("^I select (.+) in cart page$")
	public void I_select_productname_in_cart_page(String productName) throws InterruptedException {
		
		List<WebElement> products = productCatalogues.getProductList();
		ele = productCatalogues.selectProduct(productName);
		productCatalogues.addProductToCart(productName);
		
	}
	
	//And submit the order with <productName>
	@When("^submit the order with (.+)$")
	public void I_submit_the_order_with_productName(String productName) {
		
		CartPage cartPage = productCatalogues.goToCartPage();
		boolean isProductPresent = cartPage.verifyCartProducts(productName);
		Assert.assertTrue(isProductPresent);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.goToConfirmationPage();
	}
	
	//Then "THANKYOU FOR THE ORDER." message should be displayed
	@Then("{string} message should be displayed")
	public void message_should_be_displayed(String string) {
		
		String outcome = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(outcome.equalsIgnoreCase(string));
		driver.quit();
	}
	
	//Then I verify the <statusMessage> 
	
	/*
	 * @Then("Then I verify the {string}") public void
	 * I_verify_the_errorMessage(String message) throws InterruptedException {
	 * 
	 * Assert.assertEquals(message, loginPage.getErrorMessage()); driver.quit(); }
	 */
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
		Thread.sleep(1000);
    	Assert.assertEquals(strArg1, loginPage.getErrorMessage());
    	driver.quit();
    }
	

}
