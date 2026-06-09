package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)     //remove this if any error comes bcz added 
                                                                     //it seeing chatgpt
public class BaseClass {

	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public DataBaseUtility dLib = new DataBaseUtility();
	public WebDriver d = null;
	public static WebDriver sd=null;
	
	

	@BeforeSuite(groups= {"smokeTest", "regressionTest"})
	public void configBS() throws Throwable {
		System.out.println("===connect to DB, Report Config====");
		dLib.getDbconnection();
		
		
	}

	@Parameters("BROWSER")
	@BeforeClass(alwaysRun=true)
	public void configBC(@Optional("chrome")String browser) throws Throwable {
		System.out.println("===Launch the Browser==");
		String BROWSER = browser;
		
				//fLib.getDataFromPropertiesFile("browser");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			d = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			d = new EdgeDriver();
		} else {
			d = new ChromeDriver();
		}
		sd=d;
		UtilityClassObject.setDriver(d);
	}

	@BeforeMethod(groups= {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==Login===");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(d);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}

//	@AfterMethod(groups= {"smokeTest", "regressionTest"})
//	public void config() {
//		System.out.println("==Logout==");
//		HomePage hp = new HomePage(d);
//		hp.logout();
//	}
	
	@AfterMethod(alwaysRun = true)
	public void config() {
	    System.out.println("==Logout==");

	    try {
	        if (d != null) {
	            HomePage hp = new HomePage(d);
	            hp.logout();
	        }
	    } catch (Exception e) {
	        System.out.println("Logout skipped due to invalid session: " + e.getMessage());
	    }
	}

	@AfterClass(groups= {"smokeTest", "regressionTest"})
	public void configAC() {
		System.out.println("===Close the Browser====");
	   // if (d != null) {    //added this line from chatgpt
		d.quit();
	}
	
	@AfterSuite(groups= {"smokeTest", "regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("===Close DB, Report backup");
		dLib.closeDbconnection();
		
	}

}
