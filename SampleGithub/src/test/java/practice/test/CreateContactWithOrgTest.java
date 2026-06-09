package practice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithOrgTest {

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
		String orgName=eLib.getDataFromExcel("contact", 7, 2)	+ jLib.getRandomNumber();
		String shipping=eLib.getDataFromExcel("org", 1, 3)+jLib.getRandomNumber();
		String contactLastName=eLib.getDataFromExcel("contact", 7, 3)+ jLib.getRandomNumber();
		 
		
		WebDriver d=null;
		if(BROWSER.equals("chrome")) {
		d=new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
		d=new EdgeDriver();
		}
		else{
		d=new ChromeDriver();
		}
		 
		wLib.waitForPageToLoad(d);
		Thread.sleep(10000);
				
		d.get(URL);
				
		//step:1 login
		LoginPage lp=new LoginPage(d);
		lp.loginToapp(URL,USERNAME,PASSWORD);
				
		//step:2
		d.findElement(By.linkText("Organizations")).click();
				
		//step:3
		d.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//step:4
		d.findElement(By.name("accountname")).sendKeys(orgName);
		d.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shipping);
				
		d.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
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
				
        //step:5
		d.findElement(By.linkText("Contacts")).click();
				
		//step:6
		d.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//step:7
		d.findElement(By.name("lastname")).sendKeys(contactLastName);
		d.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
		//switch to child window
		wLib.switchToTabOnURL(d, "module=Accounts");
		
				
		d.findElement(By.name("search_text")).sendKeys(orgName);
		d.findElement(By.name("search")).click();
		d.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				
		//switch to parent window
		wLib.switchToTabOnURL(d, "Contacts&action");
		
			
		d.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
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
