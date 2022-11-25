package pomImplementation;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.CreateNewLeadPage;
import pomPages.DuplicatingLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewLeadInfoPage;

public class CreateAndDuplicateLeadTest {

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
		
		WebDriver driver = webdriver.openBrowserAndApplication(url, time);
		
		LoginPage loginPage = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage leadsPage = new LeadsPage(driver);
		CreateNewLeadPage createNewLead = new CreateNewLeadPage(driver);
		NewLeadInfoPage newLead = new NewLeadInfoPage(driver);
		DuplicatingLeadPage duplicatingPage = new DuplicatingLeadPage(driver);
		
		if (loginPage.getLogo().isDisplayed())
			System.out.println("Pass: Vtiger login page is diplayed");
		else
			System.out.println("Fail: Vtiger login page is not displayed");

		loginPage.loginToApplication(username, password);

		if (home.getPageHeader().contains("Home"))
			System.out.println("Pass : Login successful");
		else
			System.out.println("Fail : Login not successful");
		
		home.clickLeadsTab();
		
		leadsPage.clickPlusButton();
		
		if (createNewLead.getPageHeader().contains("Creating New Lead"))
			System.out.println("Pass : Creating new lead page is displayed");
		else
			System.out.println("Fail : Creating new lead page is not displayed");
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		
		createNewLead.selectSalutation(webdriver, map.get("First Name Salutation"));
		
		String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		createNewLead.setLastName(leadName);
		createNewLead.setCompany(map.get("Company"));
		createNewLead.clickSaveButton();
		
		if (newLead.getPageHeader().contains(leadName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		newLead.clickDuplicateButton();
		
		if(duplicatingPage.getPageHeader().contains(leadName)) {
			System.out.println("Pass : Duplicating page displayed");
		}
		else
			System.out.println("Fail : Duplicating page is not displayed");
		
		
		String newLastName =map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		duplicatingPage.setNewLeadName(newLastName);
		duplicatingPage.clickSaveButton();
		
		if (newLead.getPageHeader().contains(newLastName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		newLead.clickLeads();
		if(leadsPage.getLastLeadName().equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		home.signOutFromVtiger(webdriver);
		
		excel.closeExcel();
		webdriver.closeBrowser();
	}

}
