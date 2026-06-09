package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
//@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)

public class CreateOrganizationTest extends BaseClass {    //individual and run all is working

      @Test(groups = "smokeTest")
      public void createOrg() throws Throwable {
    	  
    	  UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
    	  
		//read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 1, 2)+ jLib.getRandomNumber() ;
		String shipping=eLib.getDataFromExcel("org", 1, 3);
		
		//step:2
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
        HomePage hp=new HomePage(d);	
        hp.getOrgLink().click();
        
		//step:3
        UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
        OrganizationsPage cnp=new OrganizationsPage(d);
        cnp.getCreateNewOrgBtn().click();
        
		//step:4
        UtilityClassObject.getTest().log(Status.INFO, "create new org");
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(d);
		cnop.createOrg(orgName, shipping);
		UtilityClassObject.getTest().log(Status.INFO, "======>create new org");
        
		//verify Header msg
		
		OrganizationInfoPage oip=new OrganizationInfoPage(d);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName + " is verified==Pass");
		}
		else{
			System.out.println(orgName + " is not verified==Fail");
		}
		Thread.sleep(2000);
		
		//step-5 logout
		//d.quit();
	}
      
      
      @Test(groups = "regressionTest")
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
      
      @Test(groups = "regressionTest")
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
  		else{
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
  		//d.quit();								
  	}
}

