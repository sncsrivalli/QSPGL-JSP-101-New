package genericLibraries;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains java reusable methods
 * @author sncsr
 *
 */

public class JavaUtility {
	
	/**
	 * This method generates random number
	 * @param limit 
	 */
	public int generateRandomNumber(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}
	
	/**
	 * This method is used to generate current time
	 */
	public String currentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_sss");
		String actualDate = sdf.format(date);
		return actualDate;
	}

}
