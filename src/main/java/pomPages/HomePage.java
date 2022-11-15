package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;



public class HomePage {
	
	//Declaration
	@FindBy(xpath = "//a[@class='hdrLink']") private WebElement pageHeader;
	@FindBy(xpath = "//a[.='Leads']") private WebElement leadsTab;
	@FindBy(xpath = "//a[.='Contacts']") private WebElement contactsTab;
	@FindBy(xpath = "//a[.='Organizations']") private WebElement organizationsTab;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") private WebElement administratorImage;
	@FindBy(xpath = "//a[.='Sign Out']") private WebElement signOutButton;
	
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Business Libraries
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickOrganizationsTab() {
		organizationsTab.click();
	}
	
	public void clickContactsTab() {
		contactsTab.click();
	}
	
	public void clickLeadsTab() {
		leadsTab.click();
	}	
	
	public void signOutFromVtiger(WebDriverUtility webdriver) {
		webdriver.mouseHoverToElement(administratorImage);
		signOutButton.click();
	}
}
