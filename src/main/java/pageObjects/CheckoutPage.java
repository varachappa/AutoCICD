package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.cssSelector(".text-validated.ng-pristine")).sendKeys("random1@gmail.com");
	@FindBy(css = ".text-validated.ng-pristine")
	WebElement emailSection;

//	List<WebElement> countryList = driver.findElements(By.cssSelector("button.ta-item"));
//	countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement typeCountry;

	@FindBy(css = "button.ta-item")
	List<WebElement> countryList;

	@FindBy(css = "a.btnn.action__submit")
	WebElement submitEle;

	public void enterEmailandCountry(String email, String country) {
		emailSection.sendKeys(email);
		typeCountry.sendKeys(country);
		countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
	}

	public ConfirmationPage submit() {
		submitEle.click();
		return new ConfirmationPage(driver);
	}

}
