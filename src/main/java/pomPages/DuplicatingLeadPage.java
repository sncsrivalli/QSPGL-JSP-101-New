package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public void setNewLeadName(String leadLastName) {
		lastNameTextField.clear();
		lastNameTextField.sendKeys(leadLastName);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
	
}
