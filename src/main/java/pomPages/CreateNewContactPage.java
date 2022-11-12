package pomPages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.ExcelUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class CreateNewContactPage {
	
	//Declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name="salutationtype") private WebElement firstNameSalutationDropdown;
	@FindBy(name="lastname") private WebElement lastNameTextField;
	@FindBy(xpath="//img[@title='Select' and contains(@onclick,'Accounts&action')]")
	private WebElement organizationPlusButton;
	
	private String organizationTableRowPath = "//div[@id='ListViewContents']/descendant::table[@cellpadding=\"5\"]/tbody/tr[%d]/td[1]/a";
	
	@FindBy(xpath="//div[@id='ListViewContents']/descendant::table[@cellpadding=\"5\"]/tbody/tr")
	private List<WebElement> organizationList;
	
	@FindBy(xpath="//input[@name='imagename']") private WebElement contactImage;
	
	@FindBy(xpath="//input[contains(@value,'Save')]") private WebElement saveButton;
	
	//Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Utilisation
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public String createContactWithExistingOrganization(WebDriver driver, WebDriverUtility webdriver, JavaUtility javaUtility,ExcelUtility excel) {
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		
		webdriver.dropDown(firstNameSalutationDropdown, map.get("First Name Salutation"));

		String contactName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		lastNameTextField.sendKeys(contactName);
		organizationPlusButton.click();

		String parentWindow = webdriver.getParentWindow();
		webdriver.handleChildBrowserPopup("Accounts&action");

		String requiredOrganizationName = map.get("Organization Name");
		
		for (int i = 2; i < organizationList.size(); i++) {
			String requiredPath = String.format(organizationTableRowPath, i);
			WebElement organization = driver.findElement(By.xpath(requiredPath));
			String organizationName = organization.getText();
			if (organizationName.equals(requiredOrganizationName)) {
				organization.click();
				break;
			}
		}

		webdriver.switchToWindow(parentWindow);
		
		contactImage.sendKeys(map.get("Contact Image"));
		saveButton.click();
		
		return contactName;
		
	}
}
