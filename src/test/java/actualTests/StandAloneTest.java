package actualTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class StandAloneTest {
	//added comment here
	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.cssSelector("input[id='userEmail']")).sendKeys("random1@gmail.com");
		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Random@me1");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));

		driver.findElement(By.cssSelector("button.btn.btn-custom i.fa.fa-shopping-cart")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("li.totalRow .btn.btn-primary")).click();

		driver.findElement(By.cssSelector(".text-validated.ng-pristine")).sendKeys("random1@gmail.com");

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countryList = driver.findElements(By.cssSelector("button.ta-item"));
		countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();

		driver.findElement(By.cssSelector("a.btnn.action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");

		driver.close();
	}
}
