package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OrgpopupPage {
	
	WebDriver d;
	public OrgpopupPage(WebDriver d)
	{
		this.d=d;
		PageFactory.initElements(d, this);
	}
   @FindBy(id="search_txt")
   private WebElement searchbtn;
   
   @FindBy(xpath="//select[@name='search_field']")
   private WebElement optionsdd;
   
   public WebElement getSearchbtn() {
	return searchbtn;
   }

   public WebElement getOptionsdd() {
	return optionsdd;
   }
   
   @FindBy(name="search")
   private WebElement searchnow;
   
   public WebElement getSearchnow() {
	return searchnow;
   }
   public void verifyorg(String orgname)
   {
	   d.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
   }
   
   
	  
  
   
}
