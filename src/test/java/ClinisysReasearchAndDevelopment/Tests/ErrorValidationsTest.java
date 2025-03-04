package ClinisysReasearchAndDevelopment.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ClinisysReasearchAndDevelopment.PageObjects.CartPage;
import ClinisysReasearchAndDevelopment.PageObjects.CheckOutPage;
import ClinisysReasearchAndDevelopment.PageObjects.ConfirmationPage;
import ClinisysReasearchAndDevelopment.PageObjects.ProductCatalogues;
import ClinisysReasearchAndDevelopment.TestComponents.BaseTest;
import ClinisysReasearchAndDevelopment.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorTest"} ,retryAnalyzer=Retry.class)
	public void loginError() throws IOException, InterruptedException {

		loginPage.loginIntoApplication("saikatdas066@gmail.com", "me@Test0123");
		Thread.sleep(1000);
		String errorText = loginPage.getErrorMessage();
		Assert.assertEquals(errorText, "Incorrect email or password.");
		
		//Intentionally giving three XXX to fail this TC so that we can test screenshot method is working or not
		//Assert.assertEquals(errorText, "Incorrect emailXXX or password.");
	}
	
	@Test
	public void incorrectProductNameError() throws IOException, InterruptedException {

		final String productName = "ADIDAS ORIGINAL";

		ProductCatalogues productCatalogues = loginPage.loginIntoApplication("saikatdas066@gmail.com", "me@Test012");

		List<WebElement> products = productCatalogues.getProductList();

		WebElement ele = productCatalogues.selectProduct(productName);

		productCatalogues.addProductToCart(productName);

		CartPage cartPage = productCatalogues.goToCartPage();
		boolean isProductPresent = cartPage.verifyCartProducts("new adidas");
		Assert.assertFalse(isProductPresent);

	}
	
	//This test will be deleted
	/*
	 * @Test public void loginError2() throws IOException, InterruptedException {
	 * 
	 * loginPage.loginIntoApplication("saikatdas066@gmail.com", "me@Test0123");
	 * Thread.sleep(1000); String errorText = loginPage.getErrorMessage();
	 * System.out.println("Error text is - " + errorText);
	 * Assert.assertEquals(errorText, "Incorrect email or password.");
	 * 
	 * }
	 */

}
