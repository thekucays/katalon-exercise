import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper
import internal.GlobalVariable

//WebUI.openBrowser('staging-beta.cicil.co.id')
//
//WebUI.maximizeWindow()
//
//// tutup pop up
//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/div_tutup'), 5)
//
//WebUI.waitForElementVisible(findTestObject('Page_CICIL_login/div_tutup'), 5)
//
////WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/span_tutup'), 5)
//WebUI.click(findTestObject('Page_CICIL_login/div_tutup'))
//
//Thread.sleep(1500)
//
//WebUI.verifyElementNotVisible(findTestObject('Page_CICIL_login/div_tutup'))
//
////WebUI.verifyElementNotPresent(findTestObject('Page_CICIL_login/span_tutup'), 1)
//// klik masuk
//WebUI.click(findTestObject('Page_CICIL_login/a_Masuk'))
//
//// input uname, pass, press masuk
//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/input_username'), 10)
//WebUI.setText(findTestObject('Page_CICIL_login/input_username'), 'testing+lukiverif5@cicil.co.id')
//WebUI.setText(findTestObject('Page_CICIL_login/input_password'), '1234567A')
//WebUI.click(findTestObject('Page_CICIL_login/button_Masuk'))
//
//// should be landed on email verification page
//Thread.sleep(3000)
//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/h4_Verifikasi Email'), 10)
//println '>>> landed on verification link page'

// setting up login request (https://stackoverflow.com/questions/50049056/how-to-use-setbodycontenthttpbodycontent-in-katalon)
RequestObject loginRequest = findTestObject('Object Repository/REST_Cicil/Hit Login API - Staging Main')
String requestBody = '{   "username": "testing+lukiverif5@cicil.co.id",   "password": "1234567A" }'
loginRequest.setBodyContent(new HttpTextBodyContent(requestBody))

// hit login 
println '>>> hitting login API'
loginResponse = WS.sendRequest(loginRequest)
WS.verifyResponseStatusCode(loginResponse, 200)
println '>>> login status code is: ' + loginResponse.statusCode

// get token (https://docs.katalon.com/katalon-studio/tutorials/parse_json_responses.html#jsonslurper)
println '>>> response: ' + loginResponse.getResponseBodyContent()
String responseString = loginResponse.getResponseBodyContent()
JsonSlurper slurper = new JsonSlurper()
Map responseParsed = slurper.parseText(responseString)
println '>>> token: ' + responseParsed.token

// manually create the request
////RequestObject requestActivate = new RequestObject('activator')
//requestActivate.setBodyContent(null)
//TestObjectProperty propCookie = new TestObjectProperty("token", ConditionType.EQUALS, responseParsed.token)
//TestObjectProperty propPath = new TestObjectProperty("path", ConditionType.EQUALS, '/')
//TestObjectProperty propDomain = new TestObjectProperty("domain", ConditionType.EQUALS, '.api-staging.cicil.co.id')
////TestObjectProperty propToken = new TestObjectProperty('Authorization', ConditionType.EQUALS, responseParsed.token.toString())
////ArrayList headers = Arrays.asList(propToken)

//requestActivate.setHttpHeaderProperties(headers)
GlobalVariable.authToken = responseParsed.token
//requestActivate.setRestUrl('http://api-staging.cicil.co.id/v1/registration/activate_email')
//requestActivate.setRestRequestMethod('GET')

//// set login cookie (https://docs.katalon.com/katalon-studio/docs/set-cookies-for-browsers.html)
//println '>>> setting login cookie'
//Cookie ckToken = new Cookie("token", responseParsed.token);
//WebDriver webDriver = DriverFactory.getWebDriver();
//webDriver.manage().addCookie(ckToken)
//
// hit the activation link
println '>>> hitting activation API'
verifResponse = WS.sendRequest(findTestObject('Object Repository/REST_Cicil/Hit Email Verification API - Staging Main'))
try{
	WS.verifyResponseStatusCode(verifResponse, 200)
	println '>>> activation response: ' + verifResponse.getResponseBodyContent()
	// close
	WebUI.closeBrowser()
} catch(StepFailedException se){
	println '>>> failed. closing browser'
	println '>>> activation response: ' + verifResponse.getResponseBodyContent()
	WebUI.closeBrowser()
}
