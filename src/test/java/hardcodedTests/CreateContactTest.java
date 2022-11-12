package hardcodedTests;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

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

		WebElement firstNameSalutationDropdown = driver.findElement(By.name("salutationtype"));
		Select salutation = new Select(firstNameSalutationDropdown);
		salutation.selectByVisibleText("Ms.");
		String contactName = "Sri" + randomNumber;
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//img[@title='Select' and contains(@onclick,'Accounts&action')]")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			String expectedTitle = "Accounts&action";
			driver.switchTo().window(window);
			String actualTitle = driver.getTitle();
			if (actualTitle.contains(expectedTitle)) {
				break;
			}
		}
		String requiredOrganizationName = "TYSS4";
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

		driver.switchTo().window(parentWindow);
		
		WebElement contactImage = driver.findElement(By.xpath("//input[@name='imagename']"));
		contactImage.sendKeys(
				"E:\\srivalli\\Advance Selenium Batches\\WCSM18 batch\\Attendance\\Screenshot 2022-10-03.png");
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

		if (newContactName.equalsIgnoreCase(contactName))
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
