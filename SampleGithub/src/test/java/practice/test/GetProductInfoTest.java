//package practice.test;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.comcast.crm.generic.fileutility.ExcelUtility;
//
//public class GetProductInfoTest {
//	
//	@Test(dataProvider="getData")
//	public void getProductInfoTest(String brandName,String productName)
//	{
//		WebDriver d=new ChromeDriver();
//		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		d.get("http://amazon.com");
//		
//		//search product
//		d.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
//		
//		
//		//capture product info
//		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]/span[2]";
//		String price=d.findElement(By.xpath(x)).getText();
//		System.out.println(price);
//				
//		d.quit();
//	}
//	
//	@DataProvider
//	public Object[][] getData() throws Throwable
//	{
//		ExcelUtility eLib=new ExcelUtility();
//		int rowCount = eLib.getRowcount("product");
//		
//		Object[][] objArr=new Object[rowCount][2];
//		
//		for(int i=0;i<rowCount;i++)
//		{
//		objArr[i][0]=eLib.getDataFromExcel("product",i+1,0);
//		objArr[i][1]=eLib.getDataFromExcel("product",i+1,1);
//		}
//		return objArr;
//		
//	}
//}
