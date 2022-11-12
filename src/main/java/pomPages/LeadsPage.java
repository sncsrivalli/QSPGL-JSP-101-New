package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	//Declaration
	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement plusButton;

	@FindBy(xpath="//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")
	private WebElement pageHeader;

	@FindBy(xpath="//table[@class='lvtBg']/descendant::div/table/tbody/tr[last()]/td[3]/a")
	private WebElement lastContactInList;

	//Initialization
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Business libraries
	public void clickPlusButton() {
		plusButton.click();
	}

	public String getPageHeader() {
		return pageHeader.getText();
	}

	public String getLastLeadName() {
		return lastContactInList.getText();
	}


}
