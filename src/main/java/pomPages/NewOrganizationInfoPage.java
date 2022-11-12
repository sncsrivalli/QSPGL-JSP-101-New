package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganizationInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']")
	private WebElement organizationsLink;

	// Initialization
	public NewOrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Business Libraries
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	/**
	 * This method is used to click on the organizations link
	 */
	public void clickOrganization() {
		organizationsLink.click();
	}

	
}
