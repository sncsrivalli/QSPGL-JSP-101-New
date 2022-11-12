package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to perform actions on property file
 * @author sncsr
 *
 */

public class PropertyFileUtility {
	
	Properties property;
	
	/**
	 * This method initializes property file
	 * @param filePath 
	 */
	public void propertyFileInitialization(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to fetch data from property file
	 * @throws IOException 
	 */
	
	public String getDataFromPropertyFile(String key) {
		return property.getProperty(key);
	}

}
