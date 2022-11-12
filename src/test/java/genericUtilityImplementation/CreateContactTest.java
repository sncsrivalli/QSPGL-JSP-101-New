package genericUtilityImplementation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		
		WebDriverUtility webdriver = new WebDriverUtility();
		JavaUtility javaUtility = new JavaUtility();
		
		PropertyFileUtility property = new PropertyFileUtility();
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		
		ExcelUtility excel = new ExcelUtility();
		excel.excelFileInitialization(IConstantPath.EXCEL_FILE_PATH);
		
		String browser = property.getDataFromPropertyFile("browser");
		String url = property.getDataFromPropertyFile("url");
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		long time = Long.parseLong(property.getDataFromPropertyFile("timeouts")); 
		
		WebDriver driver = webdriver.openBrowserAndApplication(browser, url, time);
		
		if (driver.getTitle().contains("vtiger"))
			System.out.println("Pass: Vtiger login page is diplayed");
		else
			System.out.println("Fail: Vtiger login page is not displayed");

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Administrator"))
			System.out.println("Pass : Login successful");
		else
			System.out.println("Fail : Login not successful");

		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		if (driver.getTitle().contains("Contacts"))
			System.out.println("Pass : Contacts page displayed");
		else
			System.out.println("Fail : Contacts page not displayed");

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		WebElement createContactHeaderText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createContactHeaderText.getText().contains("Creating New Contact"))
			System.out.println("Pass : Creating new Contact page is displayed");
		else
			System.out.println("Fail : Creating new Contact page is not displayed");

		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		
		WebElement firstNameSalutationDropdown = driver.findElement(By.name("salutationtype"));
		webdriver.dropDown(firstNameSalutationDropdown, map.get("First Name Salutation"));

		String contactName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//img[@title='Select' and contains(@onclick,'Accounts&action')]")).click();

		String parentWindow = webdriver.getParentWindow();
		webdriver.handleChildBrowserPopup("Accounts&action");

		String requiredOrganizationName = map.get("Organization Name");
		String commonPath = "//div[@id='ListViewContents']/descendant::table[@cellpadding=\"5\"]/tbody/tr";
		List<WebElement> organizationList = driver.findElements(By.xpath(commonPath));
		for (int i = 2; i < organizationList.size(); i++) {
			WebElement organization = driver.findElement(By.xpath(commonPath + "[" + i + "]/td[1]/a"));
			String organizationName = organization.getText();
			if (organizationName.equals(requiredOrganizationName)) {
				organization.click();
				break;
			}
		}

		webdriver.switchToWindow(parentWindow);
		
		WebElement contactImage = driver.findElement(By.xpath("//input[@name='imagename']"));
		contactImage.sendKeys(map.get("Contact Image"));
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		WebElement contactInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (contactInfoPageHeader.getText().contains(contactName))
			System.out.println("Pass : New contact created successfully");
		else
			System.out.println("Fail : Contact is not created");

		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		WebElement contactsPageHeader = driver
				.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']"));
		if (contactsPageHeader.getText().contains("Contacts"))
			System.out.println("Pass : Contacts page displayed");
		else
			System.out.println("Fail : Contacts page is not displayed");

		String newContactName = driver
				.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[4]/a")).getText();
		System.out.println(newContactName);

		if (newContactName.equalsIgnoreCase(contactName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
			

		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		webdriver.mouseHoverToElement(adminImage);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excel.closeExcel();
		webdriver.closeBrowser();

	}

}
