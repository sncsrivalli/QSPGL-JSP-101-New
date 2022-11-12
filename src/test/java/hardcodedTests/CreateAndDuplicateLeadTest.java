package hardcodedTests;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) {
		
		Random random = new Random();
		int randomNumber = random.nextInt(100);
		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		if (driver.getTitle().contains("vtiger"))
			System.out.println("Pass: Vtiger login page is diplayed");
		else
			System.out.println("Fail: Vtiger login page is not displayed");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
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
		
		WebElement firstNameSalutationDropdown = driver.findElement(By.name("salutationtype"));
		Select salutation = new Select(firstNameSalutationDropdown);
		salutation.selectByValue("Mrs.");
		
		String leadName = "Abc"+randomNumber;
		driver.findElement(By.name("lastname")).sendKeys(leadName);
		driver.findElement(By.name("company")).sendKeys("XYZ");
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
		
		String newLastName ="cde"+randomNumber;
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
		if(name.equals(newLastName)) 
			System.out.println("Test Case passed");
		else
			System.out.println("Test Case Failed");
		
		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminImage).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();
	}

}
