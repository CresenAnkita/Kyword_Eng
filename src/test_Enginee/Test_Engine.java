package test_Enginee;


import java.awt.AWTException;
import org.testng.annotations.Test;
import TestRunner.Test_runner;

public class Test_Engine {
	
public Test_runner test;

   // @Test(priority = 1)
	public void Login_PositiveSceanrio() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("Login_PositiveSceanrio");
		Thread.sleep(2000);
	}
	//@Test(priority = 2)
	public void Login_NegativeSceanrio() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("Login_NegativeSceanrio");
		Thread.sleep(2000);
	}
	
	//@Test(priority = 3)
	public void CreateAct_CorpoMem_PositiveScenario() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("CreateAct_CorpoMem_PositiveSce");
		Thread.sleep(2000);
	}

	//@Test(priority = 4)
	public void CreateActID_CorpoMem_NegativeScenario() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("CreateAct_CorpoMem_NegativeSce");
		Thread.sleep(2000);
	}
	//@Test(priority = 5)
	public void CheckActAvailableForCloseOut() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("Check_Act_For_CloseOut");
		Thread.sleep(2000);
	}
	
	//@Test(dependsOnMethods ="CheckActAvailableForCloseOut")
	public void CloseOut_CorpoMem_PositiveScenario() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("CloseOut_CorpoMem_PositiveSce");
		Thread.sleep(2000);
	}
	
	//@Test(priority = 6)
	public void CloseOut_CorpoMem_NegativeScenario() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("CloseOut_CorpoMem_NegativeSce");
		Thread.sleep(2000);
		}
	
	//@Test(priority = 7)
	public void CrossBorder_CM() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("CrossBorder_CM");
		Thread.sleep(2000);
		}

	//@Test(priority = 8)
	public void New_Service_Provider() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("New_Service_Provider");
		Thread.sleep(2000);
		}
	//@Test(priority = 9)
	public void FMV_Rate() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("FMV_Rate");
		Thread.sleep(2000);
		}
	//@Test(priority = 10)
		public void FMV_OneTimeRate() throws InterruptedException, AWTException {
			test= new Test_runner();
			test.StartExecution("FMV_OneTimeRate");	
			Thread.sleep(2000);
			}
	@Test(priority = 11)
	public void FMV_Tier() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("FMV_Tier");
		Thread.sleep(2000);
		}
	//@Test(priority = 12)
	public void FMV_PreparationTime() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("FMV_PreparationTime");
		Thread.sleep(2000);
		}
	//@Test(priority = 13)
	public void FMV_TravelTime() throws InterruptedException, AWTException {
		test= new Test_runner();
		test.StartExecution("FMV_TravelTime");
		Thread.sleep(2000);
		}
	
	
	
	

}
