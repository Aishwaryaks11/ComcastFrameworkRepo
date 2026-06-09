package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

    WebDriver d;
	
	public CreatingNewOrganizationPage(WebDriver d)         //Rule-3 Object Initialization
	{
		this.d=d; 
		PageFactory.initElements(d,this);
	}
	
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//textarea[@name='ship_street']")
	private WebElement ship;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getShip() {
		return ship;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName,String orgShip)
	{
		orgNameEdt.sendKeys(orgName);
		ship.sendKeys(orgShip);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String orgShip, String industry)
	{
		orgNameEdt.sendKeys(orgName);
		ship.sendKeys(orgShip);
		Select sel=new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	
}
