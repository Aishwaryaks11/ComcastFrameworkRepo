package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
WebDriver d;
	
	public OrganizationInfoPage(WebDriver d)         //Rule-3 Object Initialization
	{
		this.d=d; 
		PageFactory.initElements(d,this);
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	
	
}
