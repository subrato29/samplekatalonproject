package com.driver.script

//****************************************Selenium webdriver***********************************************
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory
//****************************************Selenium webdriver***********************************************
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.util.keyword.Xls_Reader
import com.util.keyword.Util

import internal.GlobalVariable

public class DriverScript extends Util{
	int rowNum=1;
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data Files\\testdatasheet.xlsx");
	public static WebDriver driver=null;

	@Keyword
	public int countOfExecutableTC(){
		int count=0
		int totRowCount=findTestData('testdatasheet').getRowNumbers();
		for (int rowNum=1;rowNum<=totRowCount;rowNum++){
			if(findTestData('testdatasheet').getValue("Runmode", rowNum).toUpperCase().equals("Y")){
				count=count+1
			}
		}
		return count
	}


	@Keyword
	public boolean isTestCaseRunnable(String tcId){
		boolean isTestCaseRunnable=false;
		rowNum=xls.getCellRowNum("TestData", "TCID", tcId)-1
		if(findTestData('testdatasheet').getValue("Runmode", rowNum).toUpperCase().equals("Y")){
			//String url="https://coloradopeak.secure.force.com/RPTSS";
			driver=null;
			if(driver==null){
					String url=getPropertyVal("URL")
					WebUI.openBrowser(url);
					WebUI.waitForPageLoad(Integer.parseInt(getPropertyVal("PageLoadTimeOut")));
					WebUI.maximizeWindow();
					driver = DriverFactory.getWebDriver();
					isTestCaseRunnable=true;
			}
			
		}
		return isTestCaseRunnable;
	}

	@Keyword
	public def listOfRunnableTCs(){
		List list = new ArrayList();
		int totRowCount=findTestData('testdatasheet').getRowNumbers();
		for (int rowNum=1;rowNum<=totRowCount;rowNum++){
			if(findTestData('testdatasheet').getValue("Runmode", rowNum).toUpperCase().equals("Y")){
				String tcId=findTestData('testdatasheet').getValue("TCID", rowNum)
				list.add(tcId);
			}
		}
		return list
	}
}
