package pomPages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.ExcelUtility;
import genericLibraries.JavaUtility;
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
	public String createLead(WebDriverUtility webdriver,ExcelUtility excel, JavaUtility javaUtility) {
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");

		webdriver.dropDown(firstNameSalutationDropdown, map.get("First Name Salutation"));

		String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		leadLastNameTextField.sendKeys(leadName);
		companyTextField.sendKeys(map.get("Company"));
		saveButton.click();

		return leadName;

	}

	public String getPageHeader() {
		return pageHeader.getText();
	}

}
