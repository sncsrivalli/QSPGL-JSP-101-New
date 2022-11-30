package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrganizationTest extends BaseClass{

	@Test
	public void createOrganizationTest() throws IOException {
		SoftAssert soft = new SoftAssert();
		home.clickOrganizationsTab();

		soft.assertTrue(organizations.getPageHeader().contains("Organizations"));

		organizations.clickPlusButton();
		
		soft.assertTrue(createOrganization.getPageHeader().contains("Creating New Organization"));
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		
		String newOrganizationName = map.get("Organization Name")+javaUtility.generateRandomNumber(100);
				
		createOrganization.setOrganizationName(newOrganizationName);
		createOrganization.selectIndustry(webdriver, map.get("Industry"));
		createOrganization.clickGroupRadioButton();
		createOrganization.selectGroupFromDropdown(webdriver, map.get("Group"));
		createOrganization.clickSave();

		soft.assertTrue(newOrganizationInfo.getPageHeader().contains(newOrganizationName));

		newOrganizationInfo.clickOrganization();

		soft.assertTrue(organizations.getPageHeader().contains("Organizations"));

		soft.assertTrue(organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName));
		if (organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
		soft.assertAll();
			
	}

}
