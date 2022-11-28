package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pomPages.CreateNewOrganizationPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;

public class BaseClass {
	protected ExcelUtility excel;
	protected PropertyFileUtility property;
	protected JavaUtility javaUtility;
	protected WebDriverUtility webdriver;
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected HomePage home;
	protected OrganizationsPage organizations;
	protected CreateNewOrganizationPage createOrganization;
	protected NewOrganizationInfoPage newOrganizationInfo;
	
	//@BeforeSuite
	
	@BeforeTest
	public void testSetUp() {
		excel = new ExcelUtility();
		property = new PropertyFileUtility();
		javaUtility =new JavaUtility();
		webdriver = new WebDriverUtility();
		
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(IConstantPath.EXCEL_FILE_PATH);
	}
	
	@BeforeClass
	public void classSetUp() {
		String url = property.getDataFromPropertyFile("url");
		String time = property.getDataFromPropertyFile("timeouts");
		long timeouts = Long.parseLong(time);
		
		driver = webdriver.openBrowserAndApplication(url, timeouts);
		loginPage = new LoginPage(driver);
		if (loginPage.getLogo().isDisplayed())
			System.out.println("Pass: Vtiger login page is diplayed");
		else
			System.out.println("Fail: Vtiger login page is not displayed");
		
		home = new HomePage(driver);
		organizations = new OrganizationsPage(driver);
		createOrganization = new CreateNewOrganizationPage(driver);
		newOrganizationInfo = new NewOrganizationInfoPage(driver);
	}
	
	@BeforeMethod
	public void methodSetUp() {
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		loginPage.loginToApplication(username, password);
		if (home.getPageHeader().contains("Home"))
			System.out.println("Pass : Login successful");
		else
			System.out.println("Fail : Login not successful");
	}
	
	//@Test --> Test Script Executes
	
	@AfterMethod
	public void methodTearDown() {
		home.signOutFromVtiger(webdriver);
	}
	
	@AfterClass
	public void classTearDown() {
		webdriver.closeBrowser();
	}
	
	@AfterTest
	public void testTearDown() {
		excel.closeExcel();
	}
	
	//@AfterSuite

}
