package pomImplementation;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.CreateNewOrganizationPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException {
		
		WebDriverUtility webdriver = new WebDriverUtility();
		JavaUtility javaUtility = new JavaUtility();
		
		PropertyFileUtility property = new PropertyFileUtility();
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		
		ExcelUtility excel = new ExcelUtility();
		excel.excelFileInitialization(IConstantPath.EXCEL_FILE_PATH);
		
		String url = property.getDataFromPropertyFile("url");
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		long time = Long.parseLong(property.getDataFromPropertyFile("timeouts")); 
		
		WebDriver driver = webdriver.openBrowserAndApplication( url, time);
		
		LoginPage loginPage = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationsPage organizations = new OrganizationsPage(driver);
		CreateNewOrganizationPage createOrganization =	new CreateNewOrganizationPage(driver);
		NewOrganizationInfoPage newOrganizationInfo = new NewOrganizationInfoPage(driver);
		
		if (loginPage.getLogo().isDisplayed())
			System.out.println("Pass: Vtiger login page is diplayed");
		else
			System.out.println("Fail: Vtiger login page is not displayed");

		loginPage.loginToApplication(username, password);
//		driver.findElement(By.name("user_name")).sendKeys(username);
//		driver.findElement(By.name("user_password")).sendKeys(password);
//		driver.findElement(By.id("submitButton")).submit();

		if (home.getPageHeader().contains("Home"))
			System.out.println("Pass : Login successful");
		else
			System.out.println("Fail : Login not successful");
		
		home.clickOrganizationsTab();

		//driver.findElement(By.xpath("//a[.='Organizations']")).click();

		if (organizations.getPageHeader().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page not displayed");

		organizations.clickPlusButton();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//WebElement createOrgHeaderText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createOrganization.getPageHeader().contains("Creating New Organization"))
			System.out.println("Pass : Creating new organization page is displayed");
		else
			System.out.println("Fail : Creating new organization page is not displayed");
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		
		String newOrganizationName = map.get("Organization Name")+javaUtility.generateRandomNumber(100);
		//driver.findElement(By.name("accountname")).sendKeys(newOrganizationName);
		
		createOrganization.setOrganizationName(newOrganizationName);
		createOrganization.selectIndustry(webdriver, map.get("Industry"));
		createOrganization.clickGroupRadioButton();
		createOrganization.selectGroupFromDropdown(webdriver, map.get("Group"));
		createOrganization.clickSave();
//		WebElement industryDropdown = driver.findElement(By.name("industry"));
//		webdriver.dropDown(industryDropdown, map.get("Industry"));

//		driver.findElement(By.xpath("//input[@value='T']")).click();
//
//		WebElement groupDropdown = driver.findElement(By.name("assigned_group_id"));
//		webdriver.dropDown(groupDropdown, map.get("Group"));

		//driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		//WebElement organizationInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (newOrganizationInfo.getPageHeader().contains(newOrganizationName))
			System.out.println("Pass : New organization created successfully");
		else
			System.out.println("Fail : Organization is not created");

		newOrganizationInfo.clickOrganization();
//		driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']"))
//				.click();
		//WebElement organizationsPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (organizations.getPageHeader().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page is not displayed");

//		String organizationName = driver
//				.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]/a")).getText();
//		System.out.println(organizationName);
		
		if (organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
		home.signOutFromVtiger(webdriver);
//		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		webdriver.mouseHoverToElement(adminImage);
//		
//		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
//		
		webdriver.closeBrowser();
		excel.closeExcel();
	}

}
