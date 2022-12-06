package testNGImplementation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//Test Using DataProvider 
public class DataProviderExampleTest {
	
	@Test(dataProvider = "dataToBookTicket")
	public void bookTicketTest(String source, String destination) {
		System.out.println("From: "+ source +"  To: "+ destination);
	}
	
	@DataProvider
	public Object[][] dataToBookTicket(){
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "Bangalore";
		obj[0][1] = "Hyderabad";
		
		obj[1][0] ="India";
		obj[1][1] = "US";
		
		obj[2][0] = "India";
		obj[2][1] = "UK";
		
		return obj;
	}

}
