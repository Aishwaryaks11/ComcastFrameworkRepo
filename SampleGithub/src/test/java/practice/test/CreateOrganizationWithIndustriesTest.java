package practice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class CreateOrganizationWithIndustriesTest extends BaseClass{

	@Test
	public void createOrgWithIndustries() throws Throwable
	{		 		
		//read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 4, 2)+ jLib.getRandomNumber();
		String shipping=eLib.getDataFromExcel("org", 1, 3)+ jLib.getRandomNumber();
		String induestry=eLib.getDataFromExcel("org", 4, 3);
		String type=eLib.getDataFromExcel("org", 4, 4);
				
					
		//step:2
		d.findElement(By.linkText("Organizations")).click();
					
		//step:3
		d.findElement(By.xpath("//img[@title='Create Organization...']")).click();
					
		//step:4
		d.findElement(By.name("accountname")).sendKeys(orgName);
		d.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipping);
		WebElement wbsele=d.findElement(By.name("industry"));
		Select sel=new Select(wbsele);
		sel.selectByVisibleText(induestry);
					
		WebElement wbsele1=d.findElement(By.name("accounttype"));
		Select sel1=new Select(wbsele1);
		sel1.selectByVisibleText(type);
		d.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
					
                 
	    //verify the industries and type info
		String actIndutries=d.findElement(By.id("dtlview_Industry")).getText();
		if(actIndutries.equals(induestry))
		{
		System.out.println(induestry + "is Verifed==Pass");
		}
		else
		{
		System.out.println(induestry + "is not Verifed==Fail");
		}
				
		//verify type
		String actType=d.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type))
		{
		System.out.println(type + " is Verifed==Pass");
		}
		else{
		System.out.println(type + " is not Verifed==Fail");
		}
		d.quit();	
									
	}

}
