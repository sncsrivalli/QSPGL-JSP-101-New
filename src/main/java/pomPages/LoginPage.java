package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all the web elements and business libraries of login page of vtiger application
 * @author sncsr
 *
 */

public class LoginPage {
	
	//Declaration
	@FindBy(xpath="//img[@alt='logo']") private WebElement logo;
	@FindBy(name="user_name") private WebElement usernameTextField;
	@FindBy(name="user_password") private WebElement passwordTextField;
	@FindBy(id="submitButton") private WebElement loginButton;
	
	//Initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Business Libraries
	
	/**
	 * This method is used to get logo of vtiger application
	 * @return
	 */
	public WebElement getLogo() {
		return logo;
	}
	
	/**
	 * This method is used to login to the vtiger application
	 * @param username
	 * @param password
	 */
	public void loginToApplication(String username, String password) {
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}

}
