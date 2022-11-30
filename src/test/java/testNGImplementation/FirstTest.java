package testNGImplementation;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstTest {
	
	@Test
	public void firstTest() {
		String s1 = "Hello";
		String s2 = "Hi";
		//Assert.assertEquals(s1,s2);
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(s1, s2);
		if(s1.equals(s2))
			System.out.println("Pass");
		else
			System.out.println("Fail");
		
		soft.assertAll();
		
	}

}
