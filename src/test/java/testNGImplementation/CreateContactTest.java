package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateContactTest extends BaseClass{
	@Test
	public void createContactTest() throws IOException {
		SoftAssert soft = new SoftAssert();
		home.clickContactsTab();
		
		soft.assertTrue(contacts.getPageHeader().contains("Contacts"));
		
		contacts.clickPlusButton();
		soft.assertTrue(createNewContact.getPageHeader().contains("Creating New Contact"));
		
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		
		createNewContact.selectFirstNameSalutation(webdriver, map.get("First Name Salutation"));
		String contactName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		createNewContact.setLastName(contactName);
		createNewContact.selectExistingOrganization(webdriver, map.get("Organization Name"), driver);
		createNewContact.uploadContactImage(map.get("Contact Image"));
		createNewContact.clickSaveButton();
		
		soft.assertTrue(newContactInfo.getPageHeader().contains(contactName));
		
		newContactInfo.clickContactsLink();
		soft.assertTrue(contacts.getPageHeader().contains("Contacts"));
		
		soft.assertTrue(contacts.getLastContactName().equalsIgnoreCase(contactName));
		if (contacts.getLastContactName().equalsIgnoreCase(contactName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}
		soft.assertAll();	
	}

}
