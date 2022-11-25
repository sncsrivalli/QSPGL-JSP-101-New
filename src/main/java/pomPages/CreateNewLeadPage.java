package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;


/**
 * This class contains the web elements and business libraries of Create New Lead Page
 * @author sncsr
 *
 */

public class CreateNewLeadPage {


	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;


	@FindBy(name = "salutationtype")
	private WebElement firstNameSalutationDropdown;
	@FindBy(name = "lastname")
	private WebElement leadLastNameTextField;
	@FindBy(name = "company")
	private WebElement companyTextField;
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;

	// Initialization
	public CreateNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Libraries
	
	public void selectSalutation(WebDriverUtility webdriver,String salutation) {
		webdriver.dropDown(firstNameSalutationDropdown, salutation);
	}
	
	public void setLastName(String lastName) {
		leadLastNameTextField.sendKeys(lastName);
	}
	
	public void setCompany(String company) {
		companyTextField.sendKeys(company);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
	

	public String getPageHeader() {
		return pageHeader.getText();
	}

}
