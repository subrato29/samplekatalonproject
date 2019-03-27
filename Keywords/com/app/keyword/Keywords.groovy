package com.app.keyword
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


class Keywords {
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
	def boolean people(){
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
}