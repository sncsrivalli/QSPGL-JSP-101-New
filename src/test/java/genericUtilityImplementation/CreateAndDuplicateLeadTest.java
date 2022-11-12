package genericUtilityImplementation;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;

public class CreateAndDuplicateLeadTest {

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
		
		driver.findElement(By.xpath("//a[.='Leads']")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement createLeadHeaderText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createLeadHeaderText.getText().contains("Creating New Lead"))
			System.out.println("Pass : Creating new lead page is displayed");
		else
			System.out.println("Fail : Creating new lead page is not displayed");
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		
		WebElement firstNameSalutationDropdown = driver.findElement(By.name("salutationtype"));
		webdriver.dropDown(firstNameSalutationDropdown, map.get("First Name Salutation"));
		
		String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(leadName);
		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
		driver.findElement(By.xpath(" //input[contains(@value,'Save')]")).click();
		
		WebElement leadInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (leadInfoPageHeader.getText().contains(leadName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		driver.findElement(By.xpath("//input[@value='Duplicate']")).click();
		WebElement duplicatePage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(duplicatePage.getText().contains(leadName)) {
			System.out.println("Pass : Duplicating page displayed");
		}
		else
			System.out.println("Fail : Duplicating page is not displayed");
		
		String newLastName =map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys(newLastName);
		driver.findElement(By.xpath(" //input[contains(@value,'Save')]")).click();
		WebElement leadInfoPage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (leadInfoPage.getText().contains(newLastName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Leads&parenttab=Marketing']")).click();
		String name = driver.findElement(By.xpath("//table[@class='lvtBg']/descendant::div/table/tbody/tr[last()]/td[3]/a")).getText();
		if(name.equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		
		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mouseHoverToElement(adminImage);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excel.closeExcel();
		webdriver.closeBrowser();
	}

}
