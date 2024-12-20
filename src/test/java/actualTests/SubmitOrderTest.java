package actualTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import abstractComponents.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

//		LandingPage landingPage = launchApplication();
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/client");

//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
		ProductCatalogue productcatalague = landingPage.loginApplicaton(input.get("email"), input.get("password"));
//		driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("random1@gmail.com");
//		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Random@me1");
//		driver.findElement(By.id("login")).click();

//		ProductCatalogue productcatalague = new ProductCatalogue(driver);
//		List<WebElement> products = productcatalague.getProductsList();
//		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		productcatalague.addProductToCart(input.get("productName"));
//		WebElement prod = products.stream()
//				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

		CartPage cartPage = productcatalague.goTocartPage();
//		driver.findElement(By.cssSelector("button.btn.btn-custom i.fa.fa-shopping-cart")).click();

//		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
//		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		AssertJUnit.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
//		driver.findElement(By.cssSelector("li.totalRow .btn.btn-primary")).click();

		checkoutPage.enterEmailandCountry("random1@gmail.com", "ind");
//		driver.findElement(By.cssSelector(".text-validated.ng-pristine")).sendKeys("random1@gmail.com");
//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");

//		List<WebElement> countryList = driver.findElements(By.cssSelector("button.ta-item"));
//		countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();

		ConfirmationPage confirmationPage = checkoutPage.submit();
//		driver.findElement(By.cssSelector("a.btnn.action__submit")).click();

		String confirmMessage = confirmationPage.showMessage();
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");

//		driver.close();
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {

		ProductCatalogue productcatalague = landingPage.loginApplicaton("random1@gmail.com", "Random@me1");
		OrderPage orderPage = productcatalague.goToOrdersPage();
		boolean match = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getDataFromJson(
				System.getProperty("user.dir") + "/src/test/java/Data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "random1@gmail.com");
//		map.put("password", "Random@me1");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map.put("email", "random@email.com");
//		map.put("password", "Random@me0");
//		map.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map }, { map1 } };
//	}
//=============================
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "random1@gmail.com", "Random@me1", "ZARA COAT 3" },
//				{ "random@email.com", "Random@me0", "ADIDAS ORIGINAL" } };
//	}

}

//
//
//
//
//
//
//
//
//
//
//

// end of the file for Reference: 