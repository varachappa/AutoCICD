package abstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "table.table-bordered td:nth-child(3)")
	List<WebElement> listOfOrders;

	public boolean verifyOrderDisplay(String productName) {
		return listOfOrders.stream().anyMatch(p -> p.getText().equals(productName));
	}

}
