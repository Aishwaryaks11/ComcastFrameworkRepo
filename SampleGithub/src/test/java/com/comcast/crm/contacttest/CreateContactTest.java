package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass{      //individual is working but Run all is failing

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {
		
		//read testScript data from Excel file
		String lastName = eLib.getDataFromExcel("contact", 1, 2)+ jLib.getRandomNumber();
		
		//step:2
		ContactPage cp =new ContactPage(d);
		cp.getContactlink().click();
		
		//step:3
		cp.getCreateContactLink().click();
						
		//step:4
		cp.getLastnameEdt().sendKeys(lastName);	
		cp.getSavebtn().click();
		
		//verify Header message
		String actHearder = d.findElement(By.className("dvHeaderText")).getText();
		boolean status=actHearder.contains(lastName);
		Assert.assertEquals(status, true);
		
		String actLastName = d.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, lastName);
	}

	@Test(groups = "regressionTest")
	public void createContactWithsupportDateTest() throws Throwable
	{
		//read testScript data from Excel file
	    String lastName=eLib.getDataFromExcel("contact", 4, 2)	+ jLib.getRandomNumber();
				
		//step:2
		ContactPage cp=new ContactPage(d);
		cp.getContactlink().click();
		
		//step:3
		cp.getCreateContactLink().click();
				
		//step:4
		
		String startDate=jLib.getSystemDateYYYYDDMM();
		System.out.println(startDate);
		String endDate=jLib.getRequriedDateYYYYDDMM(30);
		System.out.println(endDate);
		cp.createcontactwithsupportdate(lastName, startDate, endDate);
				
		//verify Header msg
	
		String actStartDate = d.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate)){ 
			System.out.println(startDate + " is created==Pass");
		}
		else{
		System.out.println(startDate + " is not created==Fail");
		}
		
		String actEndDate = d.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate)){ 
			System.out.println(endDate + " is created==Pass");
		}
		else{
		System.out.println(endDate + " is not created==Fail");
		}
		
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable
	{
					 
		//read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("contact", 7, 2)	+ jLib.getRandomNumber();
		String shipping=eLib.getDataFromExcel("org", 1, 3)+jLib.getRandomNumber();
		String contactLastName=eLib.getDataFromExcel("contact", 7, 3)+ jLib.getRandomNumber();
						
		//step:2
		HomePage hp=new HomePage(d);
		hp.getOrgLink().click();
		
		//step:3
		OrganizationsPage op=new OrganizationsPage(d);
		op.getCreateNewOrgBtn().click();
						
		//step:4
		CreatingNewOrganizationPage cop=new CreatingNewOrganizationPage(d);
		cop.createOrg(orgName, shipping);
				
		//verify Header msg
		String headerInfo = d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{
		System.out.println(orgName + " is created==Pass");
		}
		else
		{
		System.out.println(orgName + " is not created==Fail");
		}
		
		ContactPage cp= new ContactPage(d);
		cp.createcontactwithorg(contactLastName, orgName);
						
		//switch to child window			
		cp.searchVerify(orgName);
		//verify Header message
						
		headerInfo = d.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(contactLastName))
		{
		System.out.println(contactLastName + " header verified==Pass");
		}
		else{
		System.out.println(contactLastName + " header not verified==Fail");
		}
						
		//verify Header orgName info Expected Result
		String actOrgName = d.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actOrgName.trim().equals(orgName))
		{
		System.out.println(orgName + "is created==Pass");
		}
		else{
		System.out.println(orgName + "is not created==Fail");
		}
	}

		
	}


