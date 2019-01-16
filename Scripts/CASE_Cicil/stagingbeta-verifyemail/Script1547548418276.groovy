import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.List

import org.openqa.selenium.WebDriver
import org.testng.Assert

import com.google.gson.JsonObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('staging-beta.cicil.co.id')

WebUI.maximizeWindow()

// tutup pop up
WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/div_tutup'), 5)

WebUI.waitForElementVisible(findTestObject('Page_CICIL_login/div_tutup'), 5)

//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/span_tutup'), 5)
WebUI.click(findTestObject('Page_CICIL_login/div_tutup'))

Thread.sleep(1500)

WebUI.verifyElementNotVisible(findTestObject('Page_CICIL_login/div_tutup'))

//WebUI.verifyElementNotPresent(findTestObject('Page_CICIL_login/span_tutup'), 1)
// klik masuk
WebUI.click(findTestObject('Page_CICIL_login/a_Masuk'))

// input uname, pass, press masuk
WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/input_username'), 10)

WebUI.setText(findTestObject('Page_CICIL_login/input_username'), 'testing+katalonia5@cicil.co.id')

WebUI.setText(findTestObject('Page_CICIL_login/input_password'), '1234567A')

WebUI.click(findTestObject('Page_CICIL_login/button_Masuk'))

// should be landed on email verification page
Thread.sleep(3000)
WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/h4_Verifikasi Email'), 10)
println '>>> landed on verification link page'

// setting up login request
//JsonObject loginParam = new JsonObject()
//loginParam.addProperty('username', 'testing+katalonia5@cicil.co.id')
//loginParam.addProperty('password', '1234567A')
//println '>>> the loginParam JSON value is: ' + loginParam.toString()

RequestObject loginRequest = findTestObject('Object Repository/REST_Cicil/Hit Login API - Staging Main')
testObjectUsername = new TestObjectProperty('username', ConditionType.EQUALS, 'testing+katalonia5@cicil.co.id')
testObjectPassword = new TestObjectProperty('password', ConditionType.EQUALS, '1234567A')
loginRequest.getRestParameters().add(testObjectUsername)
loginRequest.getRestParameters().add(testObjectPassword)


// hit login 
println '>>> hitting login API'
loginResponse = WS.sendRequest(loginRequest)
WS.verifyResponseStatusCode(loginResponse, 200)
println '>>> login status code is: ' + loginResponse.statusCode

// get token
println '>>> response: ' + loginResponse.getResponseBodyContent()

//
//// set login cookie (https://docs.katalon.com/katalon-studio/docs/set-cookies-for-browsers.html)
//WebDriver webDriver = DriverFactory.getWebDriver();
//webDriver.manage().addCookie(null);
//
//// hit the activation link
//println '>>> hitting activation API'
//verifResponse = WS.sendRequest(findTestObject('Object Repository/REST_Cicil/Hit Email Verification API - Staging Main'))
//WS.verifyResponseStatusCode(verifResponse, 200)
//
//
