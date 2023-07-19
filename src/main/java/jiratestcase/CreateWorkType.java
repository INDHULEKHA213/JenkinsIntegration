package jiratestcase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateWorkType{
	
    ChromeDriver driver;
	
	@Parameters({"url","username","password"})
	
	@BeforeMethod
	public void preCondition(String url,String uname,String pword) throws InterruptedException
	{
	
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.id("Login")).click();
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
	  
	    WebElement element = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
	     Actions builder = new Actions(driver); 
	    builder.moveToElement(element).perform();
	    
	    driver.findElement(By.xpath("//p[text()='Work Type Groups']")).click();
	    Thread.sleep(3000);
	}
	  
	
	@Test
	public void createworktype() throws InterruptedException, IOException {
	    
		driver.findElement(By.xpath("//one-app-nav-bar-item-root/a/span[contains(text(),'Work Type Groups')]/following::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button")).click();
		    
		WebElement element2 = driver.findElement(By.xpath("//span[text()='New Work Type Group']"));
		driver.executeScript("arguments[0].click()",element2);
		
		driver.findElement(By.xpath("//label[text()='Work Type Group Name']/following-sibling::div")).click();
		driver.findElement(By.xpath("//label[text()='Work Type Group Name']/following-sibling::div")).sendKeys("Salesforce Automation by Indhulekha");
		  
		driver.findElement(By.xpath("//button[text()='Save']")).click();

	    String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(text);
		
		Thread.sleep(3000);

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/createWorkType.png");
		FileUtils.copyFile(source, dest);
		
		if(text.contains("created")) {
			System.out.println("The Work Type Group is created Successfully");
		} else {
			System.out.println("The Work Type Group is not created Successfully");
		}
	
	}
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}
	

}

