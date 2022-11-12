package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement contactsLink;

	// Initialization
	public NewContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilisation

	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickContactsLink() {
		contactsLink.click();
	}

}
