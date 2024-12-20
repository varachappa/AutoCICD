package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));

	@FindBy(css = ".cart h3")
	List<WebElement> cartProducts;

	@FindBy(css = "li.totalRow .btn.btn-primary")
	WebElement checkoutButton;

	public boolean verifyProductDisplay(String productName) {
		return cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));

	}

	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}

}
