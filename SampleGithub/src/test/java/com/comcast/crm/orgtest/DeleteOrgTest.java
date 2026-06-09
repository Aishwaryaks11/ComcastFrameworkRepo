package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

public static void main(String[] args) throws Throwable {
		
		//create object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//read common data
	    String BROWSER=fLib.getDataFromPropertiesFile("browser");
	    String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
	    String PASSWORD=fLib.getDataFromPropertiesFile("password");
		 
		//read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 10, 2)+ jLib.getRandomNumber() ;
		String shipping=eLib.getDataFromExcel("org", 1, 3);
		 
		WebDriver d=null;
		if(BROWSER.equals("chrome")) {
			d=new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			d=new EdgeDriver();
		}
		else
		{
			d=new ChromeDriver();
		}
		
		wLib.waitForPageToLoad(d);
		Thread.sleep(10000);
		
		d.get(URL);
		
		//step:1 login
		LoginPage lp=new LoginPage(d);
		lp.loginToapp(URL,USERNAME,PASSWORD);		
		//step:2
        HomePage hp=new HomePage(d);	
        hp.getOrgLink().click();
        
		//step:3
        OrganizationsPage cnp=new OrganizationsPage(d);
        cnp.getCreateNewOrgBtn().click();
        
		//step:4
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(d);
		cnop.createOrg(orgName, shipping);
        
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
		
		//go back to organization page
        hp.getOrgLink().click();
        
		//search for organization
        cnp.getSearchEdt().sendKeys(orgName);
        wLib.select(cnp.getSearchDD(), "Organization Name");
        cnp.getSearchBtn().click();
        
        d.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
        System.out.println("=========================");
        
		//in dynamic webtable select and delete all
		//step-5 logout
		//hp.logout();
		//d.quit();
 
	}


}
