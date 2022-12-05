package testNGImplementation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrganizationUsingDataProviderTest extends BaseClass {

	@Test(dataProvider = "dataToCreateOrgTest")
	public void createOrgTest(String newOrganizationName, String industry, String group) {
		SoftAssert soft = new SoftAssert();
		home.clickOrganizationsTab();

		soft.assertTrue(organizations.getPageHeader().contains("Organizations"));

		organizations.clickPlusButton();

		soft.assertTrue(createOrganization.getPageHeader().contains("Creating New Organization"));

//		Map<String, String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
//
//		String newOrganizationName = map.get("Organization Name") + javaUtility.generateRandomNumber(100);

		createOrganization.setOrganizationName(newOrganizationName);
		createOrganization.selectIndustry(webdriver, industry);
		createOrganization.clickGroupRadioButton();
		createOrganization.selectGroupFromDropdown(webdriver, group);
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
	
	@DataProvider
	public Object[][] dataToCreateOrgTest(){
		Object[][] obj = new Object[2][3];
		
		obj[0][0] = "Clariyovant";
		obj[0][1] = "Electronics";
		obj[0][2] = "Team Selling";
		
		obj[1][0] = "AmDocs";
		obj[1][1] = "Electronics";
		obj[1][2] = "Support Group";
		
		return obj;
	}

}
