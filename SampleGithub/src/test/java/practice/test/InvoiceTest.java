package practice.test;

import org.testng.annotations.Test;


public class InvoiceTest{
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void activateSim() {
		System.out.println("Execute createInvoiceTest");
		//Assert.assertEquals("","Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
