package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver d)
	{
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver d, WebElement element) {
		WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		}
	
	public void switchToTabOnURL(WebDriver d, String partialURL )
	{
		Set<String> set=d.getWindowHandles();
		Iterator<String> it=set.iterator();
				
		while(it.hasNext()){
		 String windowID=it.next();
		 d.switchTo().window(windowID);
		 
		 String actUrl=d.getCurrentUrl();
		 if(actUrl.contains(partialURL))
		{
		break;}
		}
	}
	
	public void switchToTabOnTitle(WebDriver d, String partialTitle )
	{
		Set<String> set=d.getWindowHandles();
		Iterator<String> it=set.iterator();
				
		while(it.hasNext()){
		 String windowID=it.next();
		 d.switchTo().window(windowID);
		 
		 String actUrl=d.getTitle();
		 if(actUrl.contains(partialTitle))
		{
		break;}
		}
	}
	
	public void switchToFrame(WebDriver d, int index) {
		d.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver d, String nameId) {
		d.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver d, WebElement element) {
		d.switchTo().frame(element);
	}
	
	public void switchtoAlertAndAccept(WebDriver d) {
		d.switchTo().alert().accept();
	}
	
	public void switchtoAlertAndCancel(WebDriver d) {
		d.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);	
	}
	
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);	
	}
	
	public void mousemoveOnElement(WebDriver d, WebElement element)
	{
		Actions act=new Actions (d);
		act.moveToElement(element).perform();	
	}
	
	public void doubleclick(WebDriver d, WebElement element)
	{
		Actions act=new Actions (d);
		act.doubleClick(element).perform();	
	}
}
