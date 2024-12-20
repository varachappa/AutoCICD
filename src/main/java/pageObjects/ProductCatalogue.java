package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;

	By prodLoc = By.cssSelector("#toast-container");

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductsList() {
		return products;
	}

	public WebElement getProductByName(String productName) {

		return getProductsList().stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);

	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToDisappear(prodLoc);

	}

}
