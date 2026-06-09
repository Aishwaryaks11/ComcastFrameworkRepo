package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ContactPage extends JavaUtility{
	
WebDriverUtility wLib=new WebDriverUtility();
	WebDriver d;
	public ContactPage(WebDriver d)
	{
		this.d=d;
		PageFactory.initElements(d,this);
	}
	@FindBy(xpath="(//a[text()='Contacts'][1])")
	private WebElement contactLink;
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createContactLink;
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebtn;
	
	@FindBy(id="jscal_field_support_start_date")
	private WebElement supportStartdateDD;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement supportEnddateDD;
	
	@FindBy(className="dvHearderText")
	private WebElement headerMsg;
	
	
	 
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	public WebElement getContactLink() {
		return contactLink;
	}
	public WebElement getCreateContactLink() {
		return createContactLink;
	}
	public WebElement getLastnameEdt()
	{
		return lastnameEdt;
	}
	public WebElement getSavebtn()
	{
		return savebtn;
	}
	
	public WebElement getSupportStartdateDD()
	{
		return supportStartdateDD;
	}

	public WebElement getSupportEnddateDD() {
		return supportEnddateDD;
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement contactOrgSearch;
	
	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getContactOrgSearch() {
		return contactOrgSearch;
	}
	
	public void createcontactwithoutsave(String lastname)
	{
		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
	}
	public void createcontact(String lastname)
	{
		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
		savebtn.click();
	}
	
	public void createcontactwithsupportdate(String lastname, String startdate, String enddate ) throws Throwable
	{
		
		getContactLink().click();
		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
		getSupportStartdateDD().clear();
		startdate=getSystemDateYYYYDDMM();
		getSupportStartdateDD().sendKeys(startdate);
		getSupportEnddateDD().clear();
		enddate=getRequriedDateYYYYDDMM(+30);
		getSupportEnddateDD().sendKeys(enddate);
		savebtn.click();
		Thread.sleep(2000);
	}
	
	public void createcontactwithorg(String lastname, String orgname) throws Throwable
	{

		getContactLink().click();
		getCreateContactLink().click();
		lastnameEdt.sendKeys(lastname);
		Thread.sleep(2000);
		getContactOrgSearch().click();
		WebDriverUtility wLib= new WebDriverUtility();
		wLib.switchToTabOnTitle(d, "module=Accounts");
		
	}
		//switch to child window
		 public void searchVerify(String orgname)
		   {
			
		OrgpopupPage opop=new OrgpopupPage(d);
	 
		opop.getSearchbtn().sendKeys(orgname);
		opop.getSearchnow().click();
		opop.verifyorg(orgname);
		wLib.switchToTabOnTitle(d, "module=Contacts");
		getSavebtn().click();
		
		   }
	}
	


