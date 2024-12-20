package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.cssSelector("input[id='userEmail']"));
//	WebElement userPassword = driver.findElement(By.cssSelector("input[id='userPassword']"));

	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "login")
	WebElement submit;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue loginApplicaton(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

}
