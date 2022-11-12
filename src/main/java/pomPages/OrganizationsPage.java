package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all the web elements and business libraries of Organizations page
 * @author sncsr
 *
 */
public class OrganizationsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement plusButton;
	
	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//table[@class='lvt small']/descendant::tr[last()]/td[3]/a")
	private WebElement newOrganization;
	
		
	//Initialization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//Business libraries
	
	/**
	 * This method is used to click create organization's plus button
	 */
	public void clickPlusButton() {
		plusButton.click();
	}
	
	/**
	 * This method is used to get the newly created organization from table 
	 * @return
	 */
	public String getNewOrganization() {
		return newOrganization.getText();
	}

	/**
	 * This method is used to get the organizations page header
	 * @return
	 */
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	
}
