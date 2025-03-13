package automationFramework.Tests;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import automationFramework.TestComponents.BaseTest;
import automationFramework.pageObjects.CartPage;
import automationFramework.pageObjects.CheckoutPage;
import automationFramework.pageObjects.ConfirmationPage;
import automationFramework.pageObjects.OrderPage;
import automationFramework.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws Exception
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() throws Exception
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("TestRahulShettyAcademy@gmail.com", "Test@1234");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		Thread.sleep(5000);
	}
	
	
	@DataProvider
	public Object[][] getData() throws Exception {
		
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"//src//test//java//automationFramework//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	
	
	
	
	
	
	//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "TestRahulShettyAcademy@gmail.com");
//		map.put("password", "Test@1234");
//		map.put("product", "ZARA COAT 3");
//		HashMap<String, String> map1=new HashMap<String, String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
		
	
	/*
	 * need to change this in  SubmitOrder(String email,String Password,String productName) method
	@DataProvider
	public Object[][] getData1() {
		return new Object[][] {{"TestRahulShettyAcademy@gmail.com","Test@1234","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}
	*/
}
