package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateAndDuplicateLeadTest extends BaseClass{

	@Test
	public void createAndDuplicateLeadTest() throws IOException {
		
		home.clickLeadsTab();
		
		leadsPage.clickPlusButton();
		
		if (createNewLead.getPageHeader().contains("Creating New Lead"))
			System.out.println("Pass : Creating new lead page is displayed");
		else
			System.out.println("Fail : Creating new lead page is not displayed");
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		
		createNewLead.selectSalutation(webdriver, map.get("First Name Salutation"));
		
		String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		createNewLead.setLastName(leadName);
		createNewLead.setCompany(map.get("Company"));
		createNewLead.clickSaveButton();
		
		if (newLeadInfo.getPageHeader().contains(leadName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		newLeadInfo.clickDuplicateButton();
		
		if(duplicatingLead.getPageHeader().contains(leadName)) {
			System.out.println("Pass : Duplicating page displayed");
		}
		else
			System.out.println("Fail : Duplicating page is not displayed");
		
		
		String newLastName =map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		duplicatingLead.setNewLeadName(newLastName);
		duplicatingLead.clickSaveButton();
		
		if (newLeadInfo.getPageHeader().contains(newLastName))
			System.out.println("Pass : New lead created successfully");
		else
			System.out.println("Fail : Lead is not created");
		
		newLeadInfo.clickLeads();
		if(leadsPage.getLastLeadName().equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
	}

}
