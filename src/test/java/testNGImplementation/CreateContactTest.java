package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateContactTest extends BaseClass{
	@Test
	public void createContactTest() throws IOException {
		
		home.clickContactsTab();
		
		if (contacts.getPageHeader().contains("Contacts"))
			System.out.println("Pass : Contacts page displayed");
		else
			System.out.println("Fail : Contacts page not displayed");

		contacts.clickPlusButton();
		
		if (createNewContact.getPageHeader().contains("Creating New Contact"))
			System.out.println("Pass : Creating new Contact page is displayed");
		else
			System.out.println("Fail : Creating new Contact page is not displayed");

		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		
		createNewContact.selectFirstNameSalutation(webdriver, map.get("First Name Salutation"));
		String contactName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		createNewContact.setLastName(contactName);
		createNewContact.selectExistingOrganization(webdriver, map.get("Organization Name"), driver);
		createNewContact.uploadContactImage(map.get("Contact Image"));
		createNewContact.clickSaveButton();

		if (newContactInfo.getPageHeader().contains(contactName))
			System.out.println("Pass : New contact created successfully");
		else
			System.out.println("Fail : Contact is not created");

		newContactInfo.clickContactsLink();
		
		if (contacts.getPageHeader().contains("Contacts"))
			System.out.println("Pass : Contacts page displayed");
		else
			System.out.println("Fail : Contacts page is not displayed");

		if (contacts.getLastContactName().equalsIgnoreCase(contactName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
			
	}

}
