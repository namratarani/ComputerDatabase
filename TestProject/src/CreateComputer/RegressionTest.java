package CreateComputer;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class RegressionTest {
	WebDriver driver;
	String addnewcompbuttonid ="add";
	String computernameid ="name";
	String introductiondateid="introduced";
	String discontinueddateid="discontinued";
	String companynameid="company";
	String savebuttonxpath="//div[@class='actions']/input[@class='btn primary']";
	String messagenewxpath="//div[@class='alert-message warning']";
	String deletebuttonxpath="//*[@id='main']/form[2]/input[@class='btn danger']";
	String compname="testnam";
	  
	
	@Test
	  public void addComputer() throws InterruptedException {
			
			WebElement addbutton = driver.findElement(By.id(addnewcompbuttonid));
			addbutton.click();
			Thread.sleep(500); // added some wait command
			
			WebElement computername = driver.findElement(By.id(computernameid));
			computername.sendKeys(compname);
			
			WebElement savebutton = driver.findElement(By.xpath(savebuttonxpath));
			savebutton.click(); 
			
			Thread.sleep(1000);
			
			//completion message
			WebElement messagenew = driver.findElement(By.xpath(messagenewxpath));
			String textnew= messagenew.getText();
			String expectedmessage ="Done! Computer testnam has been created";
			Assert.assertEquals(textnew, expectedmessage, "Computer not created");
			System.out.println(textnew);	
			
		}

	  
	  @Test(dependsOnMethods="addComputer")
	  public void searchAndUpdateComputer() throws InterruptedException
	  {
		    WebElement searchfield = driver.findElement(By.id("searchbox"));
		    searchfield.sendKeys(compname);
		    
		    WebElement searchbutton = driver.findElement(By.id("searchsubmit"));
			searchbutton.click();
			
			Thread.sleep(500);
			
			WebElement resultset = driver.findElement(By.linkText(compname));
			resultset.click();
			
			Thread.sleep(500);
			WebElement introduced = driver.findElement(By.id(introductiondateid));
			introduced.clear();
			introduced.sendKeys("1995-12-12");

			WebElement discontinued = driver.findElement(By.id(discontinueddateid));
			discontinued.clear();
			discontinued.sendKeys("1996-12-12");
			
			Select dropdown = new Select(driver.findElement(By.id(companynameid)));
			dropdown.selectByVisibleText("RCA");
			
			WebElement savebutton = driver.findElement(By.xpath(savebuttonxpath));
			savebutton.click();
			
			Thread.sleep(500); // added some wait command
			
			//update message
			WebElement messagenew = driver.findElement(By.xpath(messagenewxpath));
			String textnew= messagenew.getText();
			String expectedmessage ="Done! Computer testnam has been updated";
			Assert.assertEquals(textnew, expectedmessage, "Computer not updated");
			System.out.println(textnew);
		    
		    
	  }
	  
	  @Test(dependsOnMethods="searchAndUpdateComputer")
	  public void searchAndDeleteComputer() throws InterruptedException
	  {
		    WebElement searchfield = driver.findElement(By.id("searchbox"));
		    searchfield.sendKeys(compname);
		    
		    WebElement searchbutton = driver.findElement(By.id("searchsubmit"));
			searchbutton.click();
			
			Thread.sleep(500); // added some wait command
			
			WebElement resultset = driver.findElement(By.linkText(compname));
			resultset.click();
			
			Thread.sleep(500);
			
			WebElement deletebutton = driver.findElement(By.xpath(deletebuttonxpath));
			deletebutton.click();
			
			Thread.sleep(500);
			
			//Computer has been deleted
			WebElement messagenew = driver.findElement(By.xpath(messagenewxpath));
			String textnew= messagenew.getText();
			String expectedmessage ="Done! Computer has been deleted";
			Assert.assertEquals(textnew, expectedmessage, "Computer not deleted");
			System.out.println(textnew);
		    
		    
	  }
	  
	 
	  @BeforeClass
	  public void startFireFox() {
		    System.setProperty("webdriver.gecko.driver", "C:\\LearnSelenium\\geckodriver-v0.13.0-win64\\geckodriver.exe");
			driver =new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://computer-database.herokuapp.com/computers");
		}

	  @AfterClass
	  	public void cleaupProc() {
			System.out.print("\nBrowser close");
			driver.quit();
		}

}
