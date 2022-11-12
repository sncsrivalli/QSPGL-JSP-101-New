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

public class CreateOrganizationTest {

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

		driver.findElement(By.xpath("//a[.='Organizations']")).click();

		if (driver.getTitle().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page not displayed");

		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		WebElement createOrgHeaderText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createOrgHeaderText.getText().contains("Creating New Organization"))
			System.out.println("Pass : Creating new organization page is displayed");
		else
			System.out.println("Fail : Creating new organization page is not displayed");
		
		String newOrganizationName = "TYSS"+randomNumber;
		driver.findElement(By.name("accountname")).sendKeys(newOrganizationName);

		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select industry = new Select(industryDropdown);
		industry.selectByVisibleText("Electronics");

		driver.findElement(By.xpath("//input[@value='T']")).click();

		WebElement groupDropdown = driver.findElement(By.name("assigned_group_id"));
		Select group = new Select(groupDropdown);
		group.selectByVisibleText("Support Group");

		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		WebElement organizationInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (organizationInfoPageHeader.getText().contains(newOrganizationName))
			System.out.println("Pass : New organization created successfully");
		else
			System.out.println("Fail : Organization is not created");

		driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']"))
				.click();
		WebElement organizationsPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (organizationsPageHeader.getText().contains("Organizations"))
			System.out.println("Pass : Organizations page displayed");
		else
			System.out.println("Fail : Organizations page is not displayed");

		String organizationName = driver
				.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]/a")).getText();
		System.out.println(organizationName);
		
		if (organizationName.equalsIgnoreCase(newOrganizationName))
			System.out.println("Test Case Passed");
		else
			System.out.println("Test Case Failed");
		
		WebElement adminImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminImage).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();

	}

}
