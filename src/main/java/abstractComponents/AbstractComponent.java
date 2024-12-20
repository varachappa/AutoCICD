package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button.btn.btn-custom i.fa.fa-shopping-cart")
	WebElement cartButton;

//	driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orders;

	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}

	public CartPage goTocartPage() {
		cartButton.click();
		return new CartPage(driver);
	}

	public OrderPage goToOrdersPage() {
		orders.click();
		return new OrderPage(driver);
	}

}
