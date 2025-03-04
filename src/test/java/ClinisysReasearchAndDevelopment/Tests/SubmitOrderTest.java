package ClinisysReasearchAndDevelopment.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ClinisysReasearchAndDevelopment.PageObjects.CartPage;
import ClinisysReasearchAndDevelopment.PageObjects.CheckOutPage;
import ClinisysReasearchAndDevelopment.PageObjects.ConfirmationPage;
import ClinisysReasearchAndDevelopment.PageObjects.LoginPage;
import ClinisysReasearchAndDevelopment.PageObjects.OrdersPage;
import ClinisysReasearchAndDevelopment.PageObjects.ProductCatalogues;
import ClinisysReasearchAndDevelopment.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

//	final String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = "singleTestUsingDataProvider")
	public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {

		final String exactMessage = "Thankyou for the order.";

		ProductCatalogues productCatalogues = loginPage.loginIntoApplication(map.get("email"), map.get("pass"));

		List<WebElement> products = productCatalogues.getProductList();

		WebElement ele = productCatalogues.selectProduct(map.get("productName"));

		productCatalogues.addProductToCart(map.get("productName"));

		CartPage cartPage = productCatalogues.goToCartPage();
		boolean isProductPresent = cartPage.verifyCartProducts(map.get("productName"));
		Assert.assertTrue(isProductPresent);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.goToConfirmationPage();

		String outcome = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(outcome.equalsIgnoreCase(exactMessage));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void verifyProductPresence() throws IOException, InterruptedException {

		ProductCatalogues productCatalogues = loginPage.loginIntoApplication("saikatdas066@gmail.com", "me@Test012");

		OrdersPage ordersPage = productCatalogues.goToOrdersPage();
		// Assert.assertTrue(ordersPage.verifyProductOnOrdersPage(productName));

	}

	// This is one way of keeping and using data
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return new Object[][]
	 * {{"saikatdas066@gmail.com","me@Test012","IPHONE 13 PRO"},
	 * {"sdas6355@gmail.com","me@Test012","Banarsi Saree"}}; }
	 */

	// This is another way of keeping and using data
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * HashMap<String, String> hmap1 = new HashMap<String, String>();
	 * HashMap<String, String> hmap2 = new HashMap<String, String>();
	 * 
	 * hmap1.put("email", "saikatdas066@gmail.com"); hmap1.put("pass",
	 * "me@Test012"); hmap1.put("productName", "IPHONE 13 PRO");
	 * 
	 * hmap2.put("email", "sdas6355@gmail.com"); hmap2.put("pass", "me@Test012");
	 * hmap2.put("productName", "Banarsi Saree");
	 * 
	 * return new Object[][] {{hmap1}, {hmap2}}; }
	 */
	
	//This is 3rd way of transporting data into Framework using above two
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> dataList = getDataFromJson();
		return new Object [][] {{dataList.get(0)},{dataList.get(1)}};
		
	}
	
	

}
