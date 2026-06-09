package practice.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class CreateOrgWithPhoneNumberTest extends BaseClass {
	
	@Test
	public void createOrgWithPhone() throws Throwable
	
	{
			 	 
	    //read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 7, 2)+ jLib.getRandomNumber();
		String shipping=eLib.getDataFromExcel("org", 1, 3)+ jLib.getRandomNumber();
		String phoneNumber =eLib.getDataFromExcel("org", 7, 3);
				
		//step:2
		d.findElement(By.linkText("Organizations")).click();
				
		//step:3
		d.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//step:4
		d.findElement(By.name("accountname")).sendKeys(orgName);
		d.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipping);
		d.findElement(By.id("phone")).sendKeys(phoneNumber);
		d.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				
		//verify header phone
		String actphoneNumber=d.findElement(By.id("dtlview_Phone")).getText();
		if(actphoneNumber.equals(phoneNumber)){
		System.out.println(phoneNumber + "is created==Pass");
		}
		else{
		System.out.println(phoneNumber + "is not created==Fail");
		}
				
	}

}
