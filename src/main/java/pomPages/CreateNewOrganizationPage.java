package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

/**
 * This class contains the web elements and business libraries of Create New Organization Page
 * @author sncsr
 *
 */
public class CreateNewOrganizationPage {
	// Declaration
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	@FindBy(name = "accountname")
	private WebElement organizationNameTextField;
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupRadioButton;
	@FindBy(name = "assigned_group_id")
	private WebElement assignedToGroupDropdown;
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;

	// Initialization
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Libraries
	public void setOrganizationName(String organizationName) {
		organizationNameTextField.sendKeys(organizationName);
	}
	
	public void selectIndustry(WebDriverUtility webdriver, String industry) {
		webdriver.dropDown(industryDropdown, industry);
	}
	
	public void clickGroupRadioButton() {
		groupRadioButton.click();
	}
	
	public void selectGroupFromDropdown(WebDriverUtility webdriver,String group) {
		webdriver.dropDown(assignedToGroupDropdown, group);
	}
	
	public void clickSave() {
		saveButton.click();
	}
	public String getPageHeader() {
		return pageHeader.getText();
	}
}
