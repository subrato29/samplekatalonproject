package com.util.keyword

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
import java.io.FileInputStream;
import java.util.Properties;

import internal.GlobalVariable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.*;


public class Util {

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : getPropertyVal
	 # Description   : get the value from .properties file
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static String getPropertyVal(String key) throws IOException{
		String path=System.getProperty("user.dir")+"\\EnvironmentDetails\\config.properties";
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(path);
		prop.load(fs);
		System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : roundOffTo2DecPlaces
	 # Description   :
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static String roundOffTo2DecPlaces(String value){
		try{
			DecimalFormat f = new DecimalFormat("##.00");
			return f.format(Double.parseDouble(value));
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : printDoubleQuotes
	 # Description   :
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static String printDoubleQuotes(){
		return ("\"");
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : generateRandomInt
	 # Description   :
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static int generateRandomInt(int upperRange){
		Random random = new Random();
		return random.nextInt(upperRange);
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : generateRandomString
	 # Description   :
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static String generateRandomString(int targetStringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		//int targetStringLength = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)
			(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : getCurrentMilliSeconds
	 # Description   : Return time in milliseconds
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static long getCurrentMilliSeconds(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}

	/*
	 ####################################################################################
	 ##############################
	 # Function Name : getRandomInteger
	 # Description   : Return random integer within a certain range
	 # Developed on : 04/02/2019
	 # Author : Subrato Sarkar
	 ####################################################################################
	 ##############################
	 */
	@Keyword
	public static int getRandomInteger(int aStart, int aEnd){
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		Random random=new Random();
		int range = aEnd-aStart;
		int randomNumber =  aStart+random.nextInt(range);
		return randomNumber;
	}




}
