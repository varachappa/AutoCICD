package actualTests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;
import TestComponents.BaseTest;
import TestComponents.Retry;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;

public class ErrorvalidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void errorValidationTest() throws IOException {
		String productName = "ZARA COAT 3";

		ProductCatalogue productcatalague = landingPage.loginApplicaton("random1@gmail.com", "Random@me1");

//		List<WebElement> products = productcatalague.getProductsList();
		productcatalague.addProductToCart(productName);

		CartPage cartPage = productcatalague.goTocartPage();

		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");

		Assert.assertTrue(match);

	}

	@Test
	public void fakeTest() {
		Assert.assertTrue(true);
	}
}
