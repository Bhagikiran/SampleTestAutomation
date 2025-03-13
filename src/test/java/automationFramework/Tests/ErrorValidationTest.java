package automationFramework.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import automationFramework.TestComponents.BaseTest;
import automationFramework.pageObjects.CartPage;
import automationFramework.pageObjects.CheckoutPage;
import automationFramework.pageObjects.ConfirmationPage;
import automationFramework.pageObjects.ProductCatalogue;
import automationFramework.TestComponents.Retry;
public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws Exception
	{
		landingPage.loginApplication("TestRahulShettyAcademyy@gmail.com", "Test@12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test(groups = {"ErrorHandling"})
	public void ProductErrorValidation() throws Exception
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
