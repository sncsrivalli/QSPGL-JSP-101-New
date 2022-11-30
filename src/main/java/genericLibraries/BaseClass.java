package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pomPages.ContactsPage;
import pomPages.CreateNewContactPage;
import pomPages.CreateNewLeadPage;
import pomPages.CreateNewOrganizationPage;
import pomPages.DuplicatingLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewContactInfoPage;
import pomPages.NewLeadInfoPage;
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
	protected ContactsPage contacts;
	protected CreateNewContactPage createNewContact;
	protected NewContactInfoPage newContactInfo;
	protected LeadsPage leadsPage;
	protected CreateNewLeadPage createNewLead;
	protected DuplicatingLeadPage duplicatingLead;
	protected NewLeadInfoPage newLeadInfo;
	
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
		Assert.assertTrue(loginPage.getLogo().isDisplayed());

		home = new HomePage(driver);
		organizations = new OrganizationsPage(driver);
		createOrganization = new CreateNewOrganizationPage(driver);
		newOrganizationInfo = new NewOrganizationInfoPage(driver);
		contacts = new ContactsPage(driver);
		createNewContact = new CreateNewContactPage(driver);
		newContactInfo = new NewContactInfoPage(driver);
		leadsPage = new LeadsPage(driver);
		createNewLead = new CreateNewLeadPage(driver);
		duplicatingLead = new DuplicatingLeadPage(driver);
		newLeadInfo = new NewLeadInfoPage(driver);
	}
	
	@BeforeMethod
	public void methodSetUp() {
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		loginPage.loginToApplication(username, password);
		
		Assert.assertTrue(home.getPageHeader().contains("Home"));
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
