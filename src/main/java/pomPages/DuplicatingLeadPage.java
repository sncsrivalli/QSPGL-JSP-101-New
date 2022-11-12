package pomPages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.ExcelUtility;
import genericLibraries.JavaUtility;

public class DuplicatingLeadPage {
	
	//Declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	

	// Initialization
	public DuplicatingLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	//Business Libraries
	public String getPageHeader() {
		return pageHeader.getText();
	}

	public String duplicatingLead(ExcelUtility excel, JavaUtility javaUtility) {
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		String leadLastName =map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		lastNameTextField.clear();
		lastNameTextField.sendKeys(leadLastName);
		saveButton.click();
		return leadLastName;
	}
	
}
