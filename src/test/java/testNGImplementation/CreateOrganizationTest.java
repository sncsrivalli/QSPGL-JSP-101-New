package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrganizationTest extends BaseClass{

	@Test
	public void createOrganizationTest() throws IOException {
		
//		WebDriverUtility webdriver = new WebDriverUtility();
//		JavaUtility javaUtility = new JavaUtility();
//		
//		PropertyFileUtility property = new PropertyFileUtility();
//		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
//		
//		ExcelUtility excel = new ExcelUtility();
//		excel.excelFileInitialization(IConstantPath.EXCEL_FILE_PATH);
//		
//		String url = property.getDataFromPropertyFile("url");
//		String username = property.getDataFromPropertyFile("username");
//		String password = property.getDataFromPropertyFile("password");
//		long time = Long.parseLong(property.getDataFromPropertyFile("timeouts")); 
//		
//		WebDriver driver = webdriver.openBrowserAndApplication( url, time);
//		
//		LoginPage loginPage = new LoginPage(driver);
//		HomePage home = new HomePage(driver);
//		OrganizationsPage organizations = new OrganizationsPage(driver);
//		CreateNewOrganizationPage createOrganization =	new CreateNewOrganizationPage(driver);
//		NewOrganizationInfoPage newOrganizationInfo = new NewOrganizationInfoPage(driver);
		
		

		//loginPage.loginToApplication(username, password);

		
		
		home.clickOrganizationsTab();

		if (organizations.getPageHeader().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page not displayed");

		organizations.clickPlusButton();
		
		if (createOrganization.getPageHeader().contains("Creating New Organization"))
			System.out.println("Pass : Creating new organization page is displayed");
		else
			System.out.println("Fail : Creating new organization page is not displayed");
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		
		String newOrganizationName = map.get("Organization Name")+javaUtility.generateRandomNumber(100);
				
		createOrganization.setOrganizationName(newOrganizationName);
		createOrganization.selectIndustry(webdriver, map.get("Industry"));
		createOrganization.clickGroupRadioButton();
		createOrganization.selectGroupFromDropdown(webdriver, map.get("Group"));
		createOrganization.clickSave();

		if (newOrganizationInfo.getPageHeader().contains(newOrganizationName))
			System.out.println("Pass : New organization created successfully");
		else
			System.out.println("Fail : Organization is not created");

		newOrganizationInfo.clickOrganization();

		if (organizations.getPageHeader().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page is not displayed");

		if (organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
//		home.signOutFromVtiger(webdriver);
//
//		webdriver.closeBrowser();
//		excel.closeExcel();
	}

}
