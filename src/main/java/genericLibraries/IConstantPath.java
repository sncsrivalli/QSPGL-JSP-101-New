package genericLibraries;

/**
 * This Interface contains all the property file path, excel file path and database URL
 * @author sncsr
 *
 */

public interface IConstantPath {
	
	String PROPERTY_FILE_PATH = "./src/test/resources/commonData.properties";
	String EXCEL_FILE_PATH = "./src/test/resources/VtigerCRMTestData.xlsx";
	String DATABASE_URL="jdbc:mysql://localhost:3306/advsel";

}
