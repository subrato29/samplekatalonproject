package com.app.keyword
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
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.util.keyword.Xls_Reader
import com.driver.script.DriverScript

class Keywords extends DriverScript{

	def Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data Files\\testdatasheet.xlsx");
	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}


	/**
	 * ####################################################################################
	 * ##############################
	 * Keyword name: start
	 * Description: Start the application
	 * Developed on: 03/18/2019
	 * Author: Subrato Sarkar
	 * ####################################################################################
	 * ##############################
	 */
	@Keyword
	def boolean start(){
		boolean start=false;
		String county=findTestData('testdatasheet').getValue("County", 1);
		try{
			String url="https://coloradopeak.secure.force.com/AC_Welcome?Language=EN";
			WebUI.openBrowser(url);
			WebUI.maximizeWindow();
			WebUI.click(findTestObject('WelcomePage/btnNext'))
			WebUI.selectOptionByValue(findTestObject('WelcomePage/selectCounty'),county, true)
			WebUI.click(findTestObject('WelcomePage/btnNextCountyPage'))
			start=true
		}catch(Throwable t){
			t.printStackTrace();
			KeywordUtil.markFailed("startPEAK keyword is failed")
			start=false;
		}
		return start;
	}

	/**
	 * ####################################################################################
	 * ##############################
	 * Keyword name: people
	 * Description: Filling up people information
	 * Developed on: 03/18/2019
	 * Author: Subrato Sarkar
	 * ####################################################################################
	 * ##############################
	 */
	@Keyword
	def boolean people(TestObject to){
		boolean people=false;
		String firstName=findTestData('testdatasheet').getValue("FirstName", 1);
		String age=findTestData('testdatasheet').getValue("Age", 1);
		try{
			WebUI.setText(findTestObject('WelcomePage/txtFirstName'), firstName)
			WebUI.setText(findTestObject('WelcomePage/txtAge'), age)
			people=true
		}catch(Throwable t){
			t.printStackTrace();
			KeywordUtil.markFailed("startPEAK keyword is failed")
			people=false;
		}
		return people;
	}


	//****************** Keywords for Colorado PEAK Report******************************************

	/**
	 * ####################################################################################
	 * ##############################
	 * Keyword name: peakReport
	 * Description: Fetching up Colorado PEAK report
	 * Developed on: 03/27/2019
	 * Author: Subrato Sarkar
	 * ####################################################################################
	 * ##############################
	 */
	@Keyword
	def boolean peakReport(){
		boolean peakReport=false;
		try{
			/*String url="https://coloradopeak.secure.force.com/RPTSS";
			 WebUI.openBrowser(url);
			 WebUI.maximizeWindow();
			 WebDriver driver = DriverFactory.getWebDriver();*/
			int countOfReportLinks=driver.findElements(By.xpath("//ol/li/a")).size()-1;
			println("No of links: "+countOfReportLinks)
			for(int i=1;i<=countOfReportLinks;i++){
				driver.findElement(By.xpath("//ol/li["+(i+1)+"]/a")).click();
				String report=driver.findElement(By.xpath("//ol/li["+(i+1)+"]/a")).getText().trim()
				KeywordUtil.markPassed("Report Name: ***************************************"+report+"**********************************************************")
				if(i<6){
					//println("Count of row: "+driver.findElements(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr")).size())
					//	String report=driver.findElement(By.xpath("//ol/li["+(i+1)+"]/a")).getText().trim()
					//println("Report:ooooooooooooooo"+report)
					//	KeywordUtil.markPassed("Report Name: ***************************************"+report+"**********************************************************")
					int countOfTblRow=driver.findElements(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr")).size()
					for(int j=1;j<=countOfTblRow;j++){
						String statistic=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[1]")).getText().trim();
						String count=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[2]")).getText().trim();
						//print("Statistic:"+j+" "+statistic)
						//print("--------------Count: "+count)
						//println();
						KeywordUtil.markPassed("Statistic:"+j+" "+statistic+"--------------Count: "+count);
					}
				}
				if(i==6){
					int countOfTblRow=driver.findElements(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr")).size()
					//KeywordUtil.
					for(int j=1;j<=countOfTblRow;j++){
						String countyName=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[1]")).getText().trim();
						String selfAssessments=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[2]")).getText().trim();
						String applicationsInMonth=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[3]")).getText().trim();
						String applicationsToDate=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[4]")).getText().trim();
						String changesInMonth=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[5]")).getText().trim();
						String changesToDate=driver.findElement(By.xpath("//table[@class='AFBSummaryTable']/tbody/tr["+j+"]/td[6]")).getText().trim();
						KeywordUtil.markPassed("Row:"+j+"*******"+countyName+"-------"+selfAssessments+"--------"+applicationsInMonth+"--------"+applicationsToDate+"--------"+changesInMonth+"--------"+changesToDate);
					}

				}
				if(i>6){
					int countOfTblRowMonthlyReports=driver.findElements(By.xpath("//span[text()='Monthly Reports']/../../../../../..//table[@class='AFBSummaryTable']/tbody/tr")).size()
					KeywordUtil.markPassed("*******************************************************"+"Monthly Reports"+"**********************************************************")
					for(int j=1;j<=countOfTblRowMonthlyReports;j++){
						String monthlyReport=driver.findElement(By.xpath("//span[text()='Monthly Reports']/../../../../../..//table[@class='AFBSummaryTable']/tbody/tr["+j+"]//a")).getText().trim();
						KeywordUtil.markPassed(monthlyReport);
					}
					if(i==7||i==10||i==12){
						KeywordUtil.markPassed("*******************************************************"+"Yearly Reports"+"**********************************************************");
						int countOfYearlyReports=driver.findElements(By.xpath("//div[text()='Yearly Reports']/../../../..//tr/td/a")).size()
						for(int j=1;j<=countOfYearlyReports;j++){
							String yearlyReport=driver.findElement(By.xpath("//div[text()='Yearly Reports']/../../../..//tr["+j+"]/td/a")).getText().trim();
							KeywordUtil.markPassed(yearlyReport);
						}
					}
					if(i==8){
						KeywordUtil.markPassed("*******************************************************"+"AFB Reports by Year"+"**********************************************************");
						int countOfYearlyReports=driver.findElements(By.xpath("//div[text()='AFB Reports by Year']/../../../..//tr/td/a")).size()
						for(int j=1;j<=countOfYearlyReports;j++){
							String yearlyReport=driver.findElement(By.xpath("//div[text()='AFB Reports by Year']/../../../..//tr["+j+"]/td/a")).getText().trim();
							KeywordUtil.markPassed(yearlyReport);
						}
					}
					if(i==9){
						KeywordUtil.markPassed("*******************************************************"+"Yearly Page Report"+"**********************************************************");
						int countOfYearlyReports=driver.findElements(By.xpath("//div[text()='Yearly Page Report']/../../../..//tr/td/a")).size()
						for(int j=1;j<=countOfYearlyReports;j++){
							String yearlyReport=driver.findElement(By.xpath("//div[text()='Yearly Page Report']/../../../..//tr["+j+"]/td/a")).getText().trim();
							KeywordUtil.markPassed(yearlyReport);
						}
					}


				}

			}
			//driver.quit();
			peakReport=true
		}catch(Throwable t){
			t.printStackTrace();
			KeywordUtil.markFailed("peakReport keyword is failed")
			peakReport=false;
		}
		return peakReport;
	}


}