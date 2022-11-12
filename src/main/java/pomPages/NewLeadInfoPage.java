package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//input[@value='Duplicate']")
	private WebElement duplicateButton;
	
	@FindBy(xpath = "//a[@href='index.php?action=ListView&module=Leads&parenttab=Marketing']")
	private WebElement leadsLink;

	// Initialization
	public NewLeadInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

	// Business Libraries

	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickDuplicateButton() {
		duplicateButton.click();
	}

	/**
	 * This method is used to click on the organizations link
	 */
	public void clickLeads() {
		leadsLink.click();
	}

}
