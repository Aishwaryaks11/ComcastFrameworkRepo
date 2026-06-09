package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> d=new ThreadLocal<WebDriver>();
	
public static ExtentTest getTest()
{
	return test.get();
}

public static void setTest(ExtentTest actTest)
{
	test.set(actTest);
}
public static WebDriver getDriver()
{
	return d.get();
}
public static void setDriver(WebDriver actDriver)
{
	d.set(actDriver);
}
}

