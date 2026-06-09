package com.comcast.crm.objectrepositoryutility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver d;
	
	public HomePage(WebDriver d)         //Rule-3 Object Initialization
	{
		this.d=d; 
		PageFactory.initElements(d,this);
	}
	
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;

	@FindBy(linkText="Contacts")
	private WebElement contactlnk;

	@FindBy(linkText="Campaigns")
	private WebElement campaignslnk;
	
	@FindBy(linkText="More")
	private WebElement morelnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutlnk;
	
	
	//Rule-4 Object Encapsulation
	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}

	public WebElement getCampaignslnk() {
		return campaignslnk;
	}
	
	public WebElement getMorelnk() {
		return morelnk;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutlnk() {
		return signOutlnk;
	}
	

	public void navigateToCampaignPage()
	{
		Actions act=new Actions(d);
		act.moveToElement(morelnk).perform();
		campaignslnk.click();
	}
	
	public void logout()
	{
		Actions act=new Actions(d);
		act.moveToElement(adminImg).perform();
		signOutlnk.click();
	}

	
}
