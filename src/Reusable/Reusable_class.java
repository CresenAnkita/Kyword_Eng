package Reusable;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Reusable_class {
	WebDriver driver;
	static String ActvityId;
	static String Act_ID;
	static  String ActvityIdCloseOut;
	public String Act_ID_CloseOut;
	JavascriptExecutor js;
	static List<String> reviewerslist;
	
	Actions act ;
	WebDriverWait wait;
	static String ActCustomerStatus;
	static String ActivityStatus;
	static String Text;
	public String SubRole;
	static String SUBROLE;
	static String ActID;
	static String ActivitySubmittion;
	static String ActivityExceution;
	static String ActvityCountry;
	static String NomineeCountry;
	static String Rate;
	static int RATE;
	
	
	
	public String[][] fetchDataFromExcel(String sheetName) {

		Workbook wb = null;
		String[][] data = null;
		try {
			String path = fetchprop("KEYWORD_PATH");
			File excel = new File(path);
			FileInputStream file = new FileInputStream(excel);

			System.out.println(path);
			String extn = path.substring(path.indexOf(".") + 1);

			System.out.println(extn);
			if (extn.equals("xlsx")) {
				wb = new XSSFWorkbook(file);
			} else {
				wb = new HSSFWorkbook(file);
			}
			Sheet sheet = wb.getSheet(sheetName);
			int rownum = sheet.getLastRowNum();
			System.out.println("Rows: " + rownum);
			int column = sheet.getRow(0).getLastCellNum();

			data = new String[rownum][column];

			for (int i = 0; i < rownum; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < column; j++) {
					Cell cell = row.getCell(j);
					String value = cell.toString();
					data[i][j] = value;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	public String fetchprop(String text) {
		Properties prop = new Properties();
		FileInputStream input;
		//C:\Users\Cresen\eclipse-workspace\Enagemate_Keyword\src\Reusable\object.properties
		try {
		//C:\Users\Cresen\eclipse-workspace\Enagemate_Keyword\src\Reusable\object.properties	
			input = new FileInputStream("C:\\Users\\Cresen\\git\\Engagemate_keywordDRiven\\Enagemate_Keyword\\src\\Reusable\\object.properties");
			prop.load(input);
		} catch (Exception ex) {

		}

		return prop.getProperty(text);
	}

	public void LaunchAppl() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\\\Users\\\\Cresen\\\\Desktop\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(fetchprop("URL"));
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	@Test
	//@SuppressWarnings("deprecation")
	public void FillText(String locatorBy, String locatorValue, String text) {
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).sendKeys(text);
			break;
		case "name":
			driver.findElement(By.name(locatorValue)).sendKeys(text);
			break;
		case "id":
			driver.findElement(By.id(locatorValue)).sendKeys(text);
			break;

		}
	}
	public void ClearText(String locatorBy, String locatorValue) {
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).clear();
			break;
		}}
	@SuppressWarnings("deprecation")
	public void FillNumber(String locatorBy, String locatorValue, String text) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		double d = Double.parseDouble(text);
		int i =(int) d;
		String str = Integer.toString(i);
		
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).sendKeys(str);
			break;
		case "name":
			driver.findElement(By.name(locatorValue)).sendKeys(str);
			break;
		case "id":
			driver.findElement(By.id(locatorValue)).sendKeys(str);
			break;

		}
	}
	public void click1(String locatorBy, String locatorElement) throws InterruptedException {
   
		wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        switch (locatorBy) {
        case "xpath":
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorElement)));
            driver.findElement(By.xpath(locatorElement)).click();
            break;
        case "name":
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorElement)));
            driver.findElement(By.name(locatorElement)).click();
            break;
        case "id":
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorElement)));
            driver.findElement(By.id(locatorElement)).click();
            break;
        }
    }
	//@SuppressWarnings("deprecation")
	@Test
	public void click(String locatorBy, String locatorElement) throws InterruptedException {

		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorElement)).click();
			break;
		case "name":
			driver.findElement(By.name(locatorElement)).click();
			break;
		case "id":
			driver.findElement(By.id(locatorElement)).click();
			break;
		case "CSS":
			driver.findElement(By.cssSelector(locatorElement)).click();
			break;

		}
	}

	public void isPresent(String locatorBy, String locatorElement) {
		switch (locatorBy) {
		case "xpath":
			driver.findElement(By.xpath(locatorElement)).isDisplayed();
			break;
		case "name":
			driver.findElement(By.name(locatorElement)).isDisplayed();
			break;
		case "id":
			driver.findElement(By.id(locatorElement)).isDisplayed();
			break;
		}
	}
	public void Scroll_Ele_click(String locatorBy, String locatorElement) {
		Actions act = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		switch (locatorBy) {
		case "xpath":
			WebElement c = driver.findElement(By.xpath(locatorElement));
			act.scrollToElement(c).click().build().perform();
			break;
		case "name":
			WebElement c1 = driver.findElement(By.name(locatorElement));
			act.scrollToElement(c1).click().build().perform();
			break;
		case "id":
			WebElement c2 = driver.findElement(By.id(locatorElement));
			act.scrollToElement(c2).click().build().perform();
			break;

		}}
		
	
	@Test
	public void validateValue(String locatorBy, String locatorElement, String content_param) {
		switch (locatorBy) {
		case "xpath":
			String Tmq=driver.findElement(By.xpath(locatorElement)).getAttribute("value");
			String A=content_param;
//			String  a=A;
//			String s=String.valueOf(a);  
//
//			s=s.replace(".", "");
//			s=s.replace(s.substring(s.length()-1), "");
			
			double d = Double.parseDouble(A);
			int i =(int) d;
			String str = Integer.toString(i);
			System.out.println( "Expected Number of Ques: "+ str);
			System.out.println( "Actual Number of Ques: "+ Tmq);

			Assert.assertEquals(str, Tmq);
			break;
		}
		
	}
	public void CloseAppl() {
		driver.quit();
	}
	@Test
	public void Wait() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void Wait_5() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void Wait_7() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void Wait_10() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void Wait_15() {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickon(WebDriver d, WebElement locatorValue,Duration timeout) 
	{
        new WebDriverWait(d, timeout).ignoring(StaleElementReferenceException.class)
        .until(ExpectedConditions.elementToBeClickable(locatorValue));
        locatorValue.click();
    }
	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(200,1200)");
	}
	
	public void Scroll_Top() throws InterruptedException {
		JavascriptExecutor Js = (JavascriptExecutor)driver;
	    Js.executeScript("document.querySelector(\"div[class='container max-height-of-form ng-star-inserted']\").scrollTop=0");    
    }
	 public void escape() { 
    	 Actions act = new Actions(driver);
 	     act.sendKeys(Keys.ESCAPE).build().perform();
 	}
 
	public void enter(){ 
		Actions act = new Actions(driver);
	    act.sendKeys(Keys.ENTER).perform();
	}
	public void refresh_browser(){ 
	    driver.navigate().refresh();
	}
	public void Implicit_wait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void explicit_wait(String locatorValue) {
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	 }
   
   public void Get_Text(String locatorBy, String locatorElement) {
		
		switch (locatorBy) {
		case "xpath":
		WebElement element =driver.findElement(By.xpath(locatorElement));
		Text = element.getText();
		System.out.println("text is "+Text);
		break;
		  }
		}
   public void Send_Text(String locatorBy, String locatorValue) {
	 	
	    switch (locatorBy) {
	    case "xpath":
		driver.findElement(By.xpath(locatorValue)).sendKeys(Text);
		break;
	     }
	   }
   public void GetTextForActivityCountry(String locatorBy, String locatorElement) {
		
		switch (locatorBy) {
		case "xpath":
		WebElement element =driver.findElement(By.xpath(locatorElement));
		ActvityCountry = element.getText();
		System.out.println("Activity Country is "+ActvityCountry);
		break;
		  }
		}
   public void GetTextForNomineeCountry(String locatorBy, String locatorElement) {
		
		switch (locatorBy) {
		case "xpath":
		WebElement element =driver.findElement(By.xpath(locatorElement));
		NomineeCountry = element.getText();
		System.out.println("Nominee Country is "+NomineeCountry);
		break;
		  }
		}
   
    
   public void Get_SubRole(String locatorBy, String locatorElement) {
	   switch (locatorBy) {
		case "xpath":
		WebElement element =driver.findElement(By.xpath(locatorElement));
		SUBROLE = element.getText();
		System.out.println(SUBROLE );
		break;
		  }
		}
   
	public void Get_Activity_ID(String locatorBy, String locatorElement) {
		
		switch (locatorBy) {
		case "xpath":
		WebElement c = driver.findElement(By.xpath(locatorElement));
		ActvityId =c.getText();
		Act_ID=ActvityId.substring(13);
		System.out.println(Act_ID);
		break;
			}
		}
	
	public void Send_Activity_ID(String locatorBy, String locatorValue) {
		 	
 	    switch (locatorBy) {
 	    case "xpath":
 		driver.findElement(By.xpath(locatorValue)).sendKeys(Act_ID);
 		break;
 	     }
 	   }
  
	 public void GetActivityId1(String locatorBy, String locatorElement) {
			
			switch (locatorBy) {
			case "xpath":
			try{WebElement element =driver.findElement(By.xpath(locatorElement));
			ActID= element.getText();
			System.out.println(ActID);
			break;
			}
			catch (NoSuchElementException e) {
				System.out.println("There is no Activity for CloseOut");
	            driver.close();
	            break;
		          }
			   }
			}
  
   public void VerifyText(String locatorBy, String locatorElement, String text) throws InterruptedException {
	       Thread.sleep(1000);
		   String Actual = driver.findElement(By.xpath(locatorElement)).getText();
		   System.out.println(Actual);
		   Assert.assertEquals(Actual, text);
    }
   
   public void SendProposedRate(String locatorBy, String locatorValue) throws InterruptedException {
	      RATE =Integer.parseInt(Text);
	      int ProposedRate = RATE +1000;
	      System.out.println("Proposed Rate "+ProposedRate);
          String FinalProposedRate =Integer.toString(ProposedRate);
		    switch (locatorBy) {
		    case "xpath":
			driver.findElement(By.xpath(locatorValue)).sendKeys(FinalProposedRate);
			break;
	}
}
  
   public void Scroll_Horizontal() throws InterruptedException {
  	   JavascriptExecutor Js = (JavascriptExecutor)driver;
  	   Js.executeScript("document.querySelector(\"ag-grid-angular[id='hcpGridNGRD'] div[name='center'] div[role='presentation']\").scrollLeft=1500");
     }
   
  public void ClickOnTier() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
		String Validation_Message=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText();
		System.out.println("message is "+Validation_Message);
		
  	  if(Validation_Message.contains("Current and proposed tier cannot be same for FMV Modification!")) 
  	  {	  System.out.println(Validation_Message);
  	      Thread.sleep(2000);
  		  driver.findElement(By.xpath("//*[@id='24']/div/div[12]/div/div/app-select-search/mat-form-field/div/div[1]/div")).click();
  		  Thread.sleep(2000);
  		  driver.findElement(By.xpath("(//span[@class='mat-option-text'])[4]")).click();
  		  Thread.sleep(2000);
  		  driver.findElement(By.xpath("//span[text()='Submit']")).click();

  	      }
     }  
  
	   public void toastmessage() throws InterruptedException {
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='toast-container']")));
		String Validation_Message=driver.findElement(By.xpath("//div[@id='toast-container']")).getText();
		System.out.println("message is "+Validation_Message);
   }
	    
  	 public void UploadDocuments() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		WebElement fileUpload = driver.findElement(By.xpath("//mat-label[contains(text(),'choose file')]/../../..//input[@formcontrolname='documentName']"));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", fileUpload);

		Robot rb = new Robot();
		rb.delay(2000);
		StringSelection ss = new StringSelection("C:\\Users\\Cresen\\Pictures\\ABC.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.delay(1000);
		
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.delay(1000);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void OrignatorApproval() {
		try {
			driver.findElement(By.xpath("//div[@class='login-image']")).isDisplayed();
	    	   {  Thread.sleep(2000);
				List<WebElement> listOfLogIn = driver.findElements(By.xpath("/html/body/app-login/div/div/form/mat-list/mat-list-item/div"));
				 for(WebElement List:listOfLogIn)
				 {
			   	    	 if(List.getText().contains(SUBROLE)) 
			   	    	 {
			   	    		  List.click();
			   	         }
				   }
	    	    }
			}
			catch (Exception e) {
			    }
			}
	
    public void CrossBorderValidation() {
    	if(driver.findElement(By.xpath("//label[contains(text(),'Cross-Border Approval')]")).isDisplayed()) {
			Assert.assertNotEquals(ActvityCountry, NomineeCountry);
			System.out.println("Activity Country and Nominee Country are Different");
		}
    }
    public void ServiceProviderApproval() throws InterruptedException
	//List of Reviwers in Audit Trial
		{List<WebElement> listOfRev = driver.findElements(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='name'])"));

		reviewerslist=new ArrayList<String>(); 
		
		for(WebElement ReviwerList:listOfRev ) 
		{
			reviewerslist.add(ReviwerList.getText());  
		}
     
		for (int i=1;i<reviewerslist.size();i++ ){
			
		System.out.println(reviewerslist.get(i));
		//clicking on Reviewer option for getting matching mail ID
		driver.findElement(By.xpath("(//span[text()='Reviewers'])[1]")).click();
		
		//clicking on the filter option
		driver.findElement(By.xpath("//*[@id='hcpGridAPGRD']/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[3]/span")).click();
	    driver.findElement(By.xpath("//span[@aria-label='filter']")).click();
	    
	    //clicking on searchbox
	    WebElement searchFilter = driver.findElement(By.xpath("//input[@aria-label='Search filter values']"));
	    Actions actt = new Actions(driver);
		Thread.sleep(2000);
		
		//sending the reviewer name in searchbox
        driver.findElement(By.xpath("(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked'])[1]")).click();
		searchFilter.sendKeys(reviewerslist.get(i));
		actt.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		
		//copying Username and Subrole
		String UserName = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='userName'])[2]")).getText();
		SubRole = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='4' and @col-id='subRole'])[2]")).getText();
		
		//clicking on cancel button
		driver.findElement(By.xpath("//span[text()='X']")).click();
		Thread.sleep(5000);
		
		//logging out and clicking on NavBar
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	 
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(3000);
		
		//sending UserName(mail ID)for Approval
		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(UserName);	
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");
		Thread.sleep(2000);
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(2000);
		
		try {
		driver.findElement(By.xpath("//div[@class='login-image']")).isDisplayed();
    	   {  
			List<WebElement> listOfLogIn = driver.findElements(By.xpath("/html/body/app-login/div/div/form/mat-list/mat-list-item/div"));
			 for(WebElement List:listOfLogIn)
			   {
		   	        System.out.println(List.getText());
		   	    	 if(List.getText().contains(SubRole)) 
		   	    	 {
		   	    		  List.click();
		   	         }
			   }
    	    }
		}
		catch (Exception e) {
		
		}		
			Thread.sleep(4000);	
			//clicking on My New Service Provider
			driver.findElement(By.xpath("//div[@class='card1-block p-3 alignment']")).click();	
			Thread.sleep(7000);
			
			//Searching for the First Name(English) and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@role='presentation'])[14]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click(); 
			
			//sending First Name(English)
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Text);
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			//clicking on edit pencil Icon
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-customer-master/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div/div[1]/div/a")).click(); 	    		  
	        Thread.sleep(10000);
	                
	        try {
	        WebElement Tier = driver.findElement(By.xpath("//span[text()='Tier']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",Tier );
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[text()='Tier']")).click(); 	    		 
			driver.findElement(By.xpath("//input[@placeholder='Tier']")).sendKeys("Global - Tier 1 (Generalist)"); 	    		 
			driver.findElement(By.xpath("//span[text()=' Global - Tier 1 (Generalist) ']")).click(); 	    		 
			Thread.sleep(7000);
	           }
	        catch (Exception e) {
	    		
			}

	        driver.findElement(By.xpath("//span[text()='Approve']")).click();
			Thread.sleep(1000);	
			
			//sending Approve Comment
			driver.findElement(By.xpath("//mat-label[text()='Leave a comment']/../../..//textarea[@rows='1']")).sendKeys("approve");
			Thread.sleep(1000);	
			
			//clicking on approve button
			driver.findElement(By.xpath("(//span[text()='Approve'])[2]")).click();
			Thread.sleep(2000);
			
			//validating popup message
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
			String Actual=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText();
			System.out.println(Actual);
			String expected="Nominee has been approved successfully!";
			Assert.assertEquals(Actual, expected);
			Thread.sleep(10000);
			
			//logging out and clicking on Navbar
			driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
			Thread.sleep(2000);
			
			//logging as a Submitter
			driver.findElement(By.id("userId")).sendKeys("rnd_sub@mailinator.com");  
			driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");    
			driver.findElement(By.id("loginBtn")).click();
			Thread.sleep(3000);
			
			//clicking on My In Progress Activity
			
			driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[2]")).click();
			Thread.sleep(4000);	
			
			//Searching for the Activity ID and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			Thread.sleep(2000);	
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			
			WebElement pageCount = driver.findElement(By.xpath("//span[@class='ag-paging-description']"));
		    System.out.println( pageCount.getText());
		       
				if(pageCount.getText().contains("Page 0 of 0"))
				{
					driver.findElement(By.xpath("(//a[contains(@href,'/home')])[2]")).click();
					Thread.sleep(7000);
					driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[1]")).click();
					Thread.sleep(7000);
					driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
					driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
					driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
					Thread.sleep(2000);	
					
					//clicking on Apply button
					driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
					Thread.sleep(4000);
					driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
					Thread.sleep(15000);
					}
					
					else {
						driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
						Thread.sleep(15000);
					}
					
			
			//clicking on Audit trial
			driver.findElement(By.xpath("//span[text()='Audit Trail']")).click();
			driver.findElement(By.xpath("//label[text()='Service Provider']")).click();
		    driver.findElement(By.xpath("(//em[@class='fa fa-plus-circle'])[3]")).click();
		   }
		   System.out.println("Approval is completed");
		   Thread.sleep(2000);
	       driver.findElement(By.xpath("//span[text()='X']")).click();
	       Thread.sleep(5000);
	}  
			

	public void approval() throws InterruptedException
	    //List of Reviwers in Audit Trial
		{List<WebElement> listOfRev = driver.findElements(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='name'])"));

		reviewerslist=new ArrayList<String>(); 
		
		for(WebElement ReviwerList:listOfRev ) 
		{
			reviewerslist.add(ReviwerList.getText());  
		}
     
		for (int i=1;i<reviewerslist.size();i++ ){
			
		System.out.println(reviewerslist.get(i));
		//clicking on Reviewer option for getting matching mail ID
		driver.findElement(By.xpath("(//span[text()='Reviewers'])[1]")).click();
		
		//clicking on the filter option
		driver.findElement(By.xpath("//*[@id='hcpGridAPGRD']/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[3]/span")).click();
	    driver.findElement(By.xpath("//span[@aria-label='filter']")).click();
	    
	    //clicking on searchbox
	    WebElement searchFilter = driver.findElement(By.xpath("//input[@aria-label='Search filter values']"));
	    Actions actt = new Actions(driver);
		Thread.sleep(2000);
		
		//sending the reviewer name in searchbox
        driver.findElement(By.xpath("(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked'])[1]")).click();
		searchFilter.sendKeys(reviewerslist.get(i));
		actt.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		
		//copying Username and Subrole
		String UserName = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='userName'])[2]")).getText();
		SubRole = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='4' and @col-id='subRole'])[2]")).getText();
		
		//clicking on cancel button
		driver.findElement(By.xpath("//span[text()='X']")).click();
		Thread.sleep(5000);
		
		//logging out and clicking on NavBar
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	 
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(3000);
		
		//sending UserName(mail ID)for Approval
		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(UserName);	
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");
		Thread.sleep(2000);
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(2000);
		
		try {
		driver.findElement(By.xpath("//div[@class='login-image']")).isDisplayed();
    	   {  
			List<WebElement> listOfLogIn = driver.findElements(By.xpath("/html/body/app-login/div/div/form/mat-list/mat-list-item/div"));
			 for(WebElement List:listOfLogIn)
			   {
		   	        System.out.println(List.getText());
		   	    	 if(List.getText().contains(SubRole)) 
		   	    	 {
		   	    		  List.click();
		   	         }
			   }
    	    }
		}
		catch (Exception e) {
		
		}		
			Thread.sleep(4000);	
			//clicking on My Activity Actions
			driver.findElement(By.xpath("//div[@class='card1-block p-3']")).click();	
			Thread.sleep(4000);
			
			//Searching for the Activity ID and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click(); 
			
			//sending ActivityId
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			//clicking on edit pencil Icon
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
	        Thread.sleep(10000);
	        
	        //clicking on Nominee for Approval Process
			driver.findElement(By.xpath("//span[contains(text(),'Nominees')]")).click(); 	    			    		 
			driver.findElement(By.xpath("//span[text()='Approve']")).click();
			Thread.sleep(1000);	
			
			//sending Approve Comment
			driver.findElement(By.xpath("//mat-label[text()='Leave a comment']/../../..//textarea[@rows='1']")).sendKeys("approve");
			Thread.sleep(1000);	
			
			//clicking on approve button
			driver.findElement(By.xpath("(//span[text()='Approve'])[2]")).click();
			Thread.sleep(2000);
			
			//validating popup message
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
			String Actual=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText();
			System.out.println(Actual);
			String expected="Activity has been approved successfully!";
			Assert.assertEquals(Actual, expected);
			Thread.sleep(10000);
			
			//logging out and clicking on Navbar
			driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
			Thread.sleep(2000);
			
			//logging as a Submitter
			driver.findElement(By.id("userId")).sendKeys("rnd_sub@mailinator.com");  
			driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");    
			driver.findElement(By.id("loginBtn")).click();
			Thread.sleep(3000);
			
			//clicking on My In Progress Activity
			driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[2]")).click();
			Thread.sleep(4000);	
			
			//Searching for the Activity ID and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			Thread.sleep(2000);	
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			//clicking on edit pencil Icon
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
			Thread.sleep(12000);
			
			//clicking on Audit trial
			driver.findElement(By.xpath("//span[text()='Audit Trail']")).click();
			
			//clicking on to expand Reviwer option for Reviewer List
		try {
			if(driver.findElement(By.xpath("//label[contains(text(),'Cross-Border Approval')]")).isDisplayed())
			{
			   driver.findElement(By.xpath("//label[contains(text(),'Cross-Border Approval')]")).click();
			   driver.findElement(By.xpath("(//em[@class='fa fa-plus-circle'])[5]")).click();
			}
			else
			{
			   driver.findElement(By.xpath("//label[contains(text(),'Reviewers')]")).click();
			}}
		catch (Exception e) {
				
			}
			
		}
	   System.out.println("Approval is completed");
	   Thread.sleep(2000);
       driver.findElement(By.xpath("//span[text()='X']")).click();
       Thread.sleep(5000);
		}  

		
	public void EditServiceProvderApproval() throws InterruptedException
	    //List of Reviwers in Audit Trial
		{List<WebElement> listOfRev = driver.findElements(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='name'])"));

		reviewerslist=new ArrayList<String>(); 
		
		for(WebElement ReviwerList:listOfRev ) 
		{
			reviewerslist.add(ReviwerList.getText());  
		}
     
		for (int i=1;i<reviewerslist.size();i++ ){
			
		System.out.println(reviewerslist.get(i));
		//clicking on Reviewer option for getting matching mail ID
		driver.findElement(By.xpath("(//span[text()='Reviewers'])[1]")).click();
		
		//clicking on the filter option
		driver.findElement(By.xpath("//*[@id='hcpGridAPGRD']/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[3]/span")).click();
	    driver.findElement(By.xpath("//span[@aria-label='filter']")).click();
	    
	    //clicking on searchbox
	    WebElement searchFilter = driver.findElement(By.xpath("//input[@aria-label='Search filter values']"));
	    Actions actt = new Actions(driver);
		Thread.sleep(2000);
		
		//sending the reviewer name in searchbox
        driver.findElement(By.xpath("(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked'])[1]")).click();
		searchFilter.sendKeys(reviewerslist.get(i));
		actt.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		
		//copying Username and Subrole
		String UserName = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='userName'])[2]")).getText();
		SubRole = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='4' and @col-id='subRole'])[2]")).getText();
		
		//clicking on cancel button
		driver.findElement(By.xpath("//span[text()='X']")).click();
		Thread.sleep(5000);
		
		//logging out and clicking on NavBar
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	 
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(3000);
		
		//sending UserName(mail ID)for Approval
		driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(UserName);	
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");
		Thread.sleep(2000);
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(2000);
		
		try {
		driver.findElement(By.xpath("//div[@class='login-image']")).isDisplayed();
    	   {  
			List<WebElement> listOfLogIn = driver.findElements(By.xpath("/html/body/app-login/div/div/form/mat-list/mat-list-item/div"));
			 for(WebElement List:listOfLogIn)
			   {
		   	        System.out.println(List.getText());
		   	    	 if(List.getText().contains(SubRole)) 
		   	    	 {
		   	    		  List.click();
		   	         }
			   }
    	    }
		}
		catch (Exception e) {
		
		}		
			Thread.sleep(4000);	
			//clicking on My Activity Actions
			driver.findElement(By.xpath("//div[@class='card1-block p-3']")).click();	
			Thread.sleep(4000);
			
			//Searching for the Activity ID and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click(); 
			
			//sending ActivityId
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			//clicking on edit pencil Icon
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
	        Thread.sleep(10000);
	        
	        //clicking on Nominee for Approval Process
			driver.findElement(By.xpath("//span[contains(text(),'Nominees')]")).click(); 
			driver.findElement(By.xpath("//*[@id='hcpGridNGRD']/div/div[2]/div[2]/div[3]/div[1]/div/div[2]/div")).click();
			Thread.sleep(10000);	
			driver.findElement(By.xpath("//span[contains(text(),'Edit Service Provider')]")).click();
			Thread.sleep(10000);		
			driver.findElement(By.xpath("//span[text()='Approve']")).click();
			Thread.sleep(1000);	
			
			//sending Approve Comment
			driver.findElement(By.xpath("//mat-label[text()='Leave a comment']/../../..//textarea[@rows='1']")).sendKeys("approve");
			Thread.sleep(1000);	
			
			//clicking on approve button
			driver.findElement(By.xpath("(//span[text()='Approve'])[2]")).click();
			Thread.sleep(2000);
			
			//validating popup message
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
			String Actual=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText();
			System.out.println(Actual);
			String expected="Nominee has been approved successfully!";
			Assert.assertEquals(Actual, expected);
			Thread.sleep(10000);
			
			//logging out and clicking on Navbar
			driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
			Thread.sleep(2000);
			
			//logging as a Submitter
			driver.findElement(By.id("userId")).sendKeys("rnd_sub@mailinator.com");  
			driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");    
			driver.findElement(By.id("loginBtn")).click();
			Thread.sleep(3000);
			
			//clicking on My In Progress Activity
			driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[2]")).click();
			Thread.sleep(4000);	
			
			//Searching for the Activity ID and clicking on 3 lines ,filter
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			Thread.sleep(2000);	
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);	
			
			
			WebElement pageCount = driver.findElement(By.xpath("//span[@class='ag-paging-description']"));
		    System.out.println( pageCount.getText());
		       
		if(pageCount.getText().contains("Page 0 of 0"))
		{
			driver.findElement(By.xpath("(//a[contains(@href,'/home')])[2]")).click();
			Thread.sleep(7000);
			driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[1]")).click();
			Thread.sleep(7000);
			driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
			driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
			driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
			Thread.sleep(2000);	
			
			//clicking on Apply button
			driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
			Thread.sleep(4000);
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
			Thread.sleep(15000);
			}
			
			else {
				driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
				Thread.sleep(15000);
			}
			
				
			//clicking on Audit trial
			driver.findElement(By.xpath("//span[text()='Audit Trail']")).click();
			
			//clicking on to expand Reviwer option for Reviewer List
			 driver.findElement(By.xpath("//label[contains(text(),'Edit Service Provider')]")).click();
			
			
			
		}
	   System.out.println("Approval is completed");
	   Thread.sleep(2000);
       driver.findElement(By.xpath("//span[text()='X']")).click();
       Thread.sleep(5000);
		}
	
	public void FMV_Approval() throws InterruptedException
    //List of Reviwers in Audit Trial
	{List<WebElement> listOfRev = driver.findElements(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='name'])"));

	reviewerslist=new ArrayList<String>(); 
	
	for(WebElement ReviwerList:listOfRev ) 
	{
		reviewerslist.add(ReviwerList.getText());  
	}
 
	for (int i=1;i<reviewerslist.size();i++ ){
		
	System.out.println(reviewerslist.get(i));
	//clicking on Reviewer option for getting matching mail ID
	driver.findElement(By.xpath("(//span[text()='Reviewers'])[1]")).click();
	
	//clicking on the filter option
	driver.findElement(By.xpath("//*[@id='hcpGridAPGRD']/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div[3]/span")).click();
    driver.findElement(By.xpath("//span[@aria-label='filter']")).click();
    
    //clicking on searchbox
    WebElement searchFilter = driver.findElement(By.xpath("//input[@aria-label='Search filter values']"));
    Actions actt = new Actions(driver);
	Thread.sleep(2000);
	
	//sending the reviewer name in searchbox
    driver.findElement(By.xpath("(//div[@class='ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper ag-checked'])[1]")).click();
	searchFilter.sendKeys(reviewerslist.get(i));
	actt.sendKeys(Keys.ENTER).perform();
	Thread.sleep(1000);
	
	//copying Username and Subrole
	String UserName = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='1' and @col-id='userName'])[2]")).getText();
	SubRole = driver.findElement(By.xpath("(//div[@tabindex='-1' and @aria-colindex='4' and @col-id='subRole'])[2]")).getText();
	
	//clicking on cancel button
	driver.findElement(By.xpath("//span[text()='X']")).click();
	Thread.sleep(5000);
	
	//logging out and clicking on NavBar
	driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
	Thread.sleep(2000);	 
	driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
	Thread.sleep(3000);
	
	//sending UserName(mail ID)for Approval
	driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(UserName);	
	driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");
	Thread.sleep(2000);
	driver.findElement(By.id("loginBtn")).click();
	Thread.sleep(2000);
	
	try {
	driver.findElement(By.xpath("//div[@class='login-image']")).isDisplayed();
	   {  
		List<WebElement> listOfLogIn = driver.findElements(By.xpath("/html/body/app-login/div/div/form/mat-list/mat-list-item/div"));
		 for(WebElement List:listOfLogIn)
		   {
	   	        System.out.println(List.getText());
	   	    	 if(List.getText().contains(SubRole)) 
	   	    	 {
	   	    		  List.click();
	   	         }
		   }
	    }
	}
	catch (Exception e) {
	
	}		
		Thread.sleep(4000);	
		//clicking on My Activity Actions
		driver.findElement(By.xpath("//div[@class='card1-block p-3']")).click();	
		Thread.sleep(4000);
		
		//Searching for the Activity ID and clicking on 3 lines ,filter
		driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
		driver.findElement(By.xpath("//span[@aria-label='filter']")).click(); 
		
		//sending ActivityId
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
		
		//clicking on Apply button
		driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
		Thread.sleep(4000);	
		
		//clicking on edit pencil Icon
		driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
        Thread.sleep(10000);
        
        //clicking on Nominee for Approval Process
		driver.findElement(By.xpath("//span[contains(text(),'Nominees')]")).click(); 
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//a[@title='FMV Modification']")).click();
		Thread.sleep(10000);			
		driver.findElement(By.xpath("//span[text()='Approve']")).click();
		Thread.sleep(1000);	
		
		//sending Approve Comment
		driver.findElement(By.xpath("//mat-label[text()='Leave a comment']/../../..//textarea[@rows='1']")).sendKeys("approve");
		Thread.sleep(1000);	
		
		//clicking on approve button
		driver.findElement(By.xpath("(//span[text()='Approve'])[2]")).click();
		Thread.sleep(2000);
		
		//validating popup message
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toast-message')]")));
		String Actual=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).getText();
		System.out.println(Actual);
		String expected="FMV Modification has been approved successfully!";
		Assert.assertEquals(Actual, expected);
		Thread.sleep(10000);
		
		//logging out and clicking on Navbar
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click(); 
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//b[text()=' LOG OUT ']")).click();
		Thread.sleep(2000);
		
		//logging as a Submitter
		driver.findElement(By.id("userId")).sendKeys("rnd_sub@mailinator.com");  
		driver.findElement(By.id("userPwd")).sendKeys("Cresen123!");    
		driver.findElement(By.id("loginBtn")).click();
		Thread.sleep(3000);
		
		//clicking on My In Progress Activity
		driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[2]")).click();
		Thread.sleep(4000);	
		
		//Searching for the Activity ID and clicking on 3 lines ,filter
		driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
		driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
		Thread.sleep(2000);	
		
		//clicking on Apply button
		driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
		Thread.sleep(4000);	
		
		
		WebElement pageCount = driver.findElement(By.xpath("//span[@class='ag-paging-description']"));
	    System.out.println( pageCount.getText());
	       
	if(pageCount.getText().contains("Page 0 of 0"))
	{
		driver.findElement(By.xpath("(//a[contains(@href,'/home')])[2]")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("(//div[@class='card1-block p-3'])[1]")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("(//span[@class='ag-header-icon ag-header-cell-menu-button'])[1]")).click();	    		 
		driver.findElement(By.xpath("//span[@aria-label='filter']")).click();    		 
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Filter')])[1]")).sendKeys(Act_ID);
		Thread.sleep(2000);	
		
		//clicking on Apply button
		driver.findElement(By.xpath("//button[@ref='applyFilterButton']")).click(); 	    		 
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
		Thread.sleep(15000);
		}
		
		else {
			driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-activity/div/div[2]/div/ag-grid-angular/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/div/a")).click(); 	    		  
			Thread.sleep(15000);
		}
			
		//clicking on Audit trial
		driver.findElement(By.xpath("//span[text()='Audit Trail']")).click();
		
		//clicking on to expand Reviwer option for Reviewer List
		 driver.findElement(By.xpath("//label[contains(text(),'FMV Modification')]")).click();
		
	}
   System.out.println("Approval is completed");
   Thread.sleep(2000);
   driver.findElement(By.xpath("//span[text()='X']")).click();
   Thread.sleep(5000);
	}

}
