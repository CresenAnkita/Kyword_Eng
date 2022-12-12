package TestRunner;

import java.awt.AWTException;
import org.testng.annotations.Test;
import Reusable.Reusable_class;

public class Test_runner {
	@Test
	public void StartExecution (String sheetN) throws InterruptedException,AWTException  {

		Reusable_class func=new Reusable_class();
		String[][] keywordsData=func.fetchDataFromExcel(sheetN);

		int rownum=keywordsData.length;
		int colnum=keywordsData[0].length;

		String TC_Name=keywordsData[0][0];
		String TC_Step_No=keywordsData[0][1];
		String TC_Step_Name=keywordsData[0][2];
		String TC_Func=keywordsData[0][3];
		String TC_Location=keywordsData[0][4];
		String TC_LV=keywordsData[0][5];
		String TC_Param=keywordsData[0][6];
		String TC_Execute=keywordsData[0][7];

		for (int counter =1; counter < rownum; counter++) {
			String function = keywordsData[counter][3];
			String LocatorBy = keywordsData[counter][4];
			String locatorElement = func.fetchprop(keywordsData[counter][5]);
			String content_param = keywordsData[counter][6];
			String execute=keywordsData[counter][7];
			if (execute.equals("Y")) {
				System.out.println(function + "\t" + LocatorBy + "\t" + locatorElement + "\t" + content_param);

				switch (function) {
				case "LaunchAppl" :
					func.LaunchAppl();
					break;
				case "ClearText":
					func.ClearText(LocatorBy, locatorElement);
					break;
				case "FillNumber":
					func.FillNumber(LocatorBy, locatorElement, content_param);
					break;
				case "click":
					func.click(LocatorBy, locatorElement);
					break;
				case "isPresent":
					func.isPresent(LocatorBy, locatorElement);
					break;
				case "Scroll_Ele_click":
					func.Scroll_Ele_click(LocatorBy, locatorElement);
					break;
				case "CloseAppl":
					func.CloseAppl();
					break;
				case "FillText":
					func.FillText(LocatorBy, locatorElement, content_param);
					break;
				case "Wait":
					func.Wait();
					break;
				case "Wait_5":
					func.Wait_5();
					break;
				case "Wait_7":
					func.Wait_7();
					break;
				case "Wait_10":
					func.Wait_10();
					break;
				case "Wait_15":
					func.Wait_15();
					break;
				case "Implicit_wait":
					func.Implicit_wait();
					break;
				case "explicit_wait":
					func.explicit_wait(locatorElement);
					break;
				case "scroll":
					func.scroll();
					break;
				case "Scroll_Top":
					func.Scroll_Top();
					break;
				case "Scroll_Horizontal":
					func.Scroll_Horizontal();
					break;	
				case "enter":
					func.enter();
					break;
				case "escape":
					func.escape();
					break;
				case "refresh_browser":
					func.refresh_browser();
					break;	
				case "Get_Text":
					func.Get_Text(LocatorBy, locatorElement);
					break;	
				case "Send_Text":
					func.Send_Text(LocatorBy, locatorElement);
					break;		
				case "VerifyText":
					func.VerifyText(LocatorBy, locatorElement, content_param);
					break;
				case "GetTextForActivityCountry":
					func.GetTextForActivityCountry(LocatorBy, locatorElement);
					break;
				case "GetTextForNomineeCountry":
					func.GetTextForNomineeCountry(LocatorBy, locatorElement);
					break;
				case "Get_SubRole":
					func.Get_SubRole(LocatorBy, locatorElement);
					break;
				case "Get_Activity_ID":
					func.Get_Activity_ID(LocatorBy, locatorElement);
					break;
				case "Send_Activity_ID":
					func.Send_Activity_ID(LocatorBy, locatorElement);
					break;
				case "clickon":
					func.clickon(null, null, null);
					break;
				case "UploadDocuments":
					func.UploadDocuments();
					break;
				case "toastmessage":
					func.toastmessage();
					break;
				case "ClickOnTier":
					func.ClickOnTier();
					break;
				case "SendProposedRate":
					func.SendProposedRate(LocatorBy, locatorElement);
					break;
				case "approval":
					func.approval();
					break;
				case "OrignatorApproval":
					func.OrignatorApproval();
					break;
				case "CrossBorderValidation":
					func.CrossBorderValidation();
					break;
				case "ServiceProviderApproval":
					func.ServiceProviderApproval();
					break;
				case "EditServiceProvderApproval":
					func.EditServiceProvderApproval();
					break;
				case "FMV_Approval":
					func.FMV_Approval();
					break;			
				case "validateValue":
					func.validateValue(LocatorBy, locatorElement, content_param);

				}
			}

		}
	}


}
