package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//Declaration
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement plusButton;
	
	@FindBy(xpath="//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//table[@class='lvt small']/descendant::tr[last()]/td[4]/a")
	private WebElement lastContactInList;
	
	//Initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	//Business libraries
	public void clickPlusButton() {
		plusButton.click();
	}

	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public String getLastContactName() {
		return lastContactInList.getText();
	}

}
