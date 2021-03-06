import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('staging-beta.cicil.co.id')

WebUI.maximizeWindow()

// tutup pop up
//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/span_tutup'), 5)

//WebUI.waitForElementVisible(findTestObject('Page_CICIL_login/span_tutup'), 5)

//WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/span_tutup'), 5)
//WebUI.click(findTestObject('Page_CICIL_login/span_tutup'))

Thread.sleep(1500)

//WebUI.verifyElementNotVisible(findTestObject('Page_CICIL_login/span_tutup'))

//WebUI.verifyElementNotPresent(findTestObject('Page_CICIL_login/span_tutup'), 1)
// klik masuk
WebUI.click(findTestObject('Page_CICIL_login/a_Masuk'))

// input uname, pass, press masuk
WebUI.waitForElementPresent(findTestObject('Page_CICIL_login/input_username'), 10)

WebUI.setText(findTestObject('Page_CICIL_login/input_username'), varEmail)

WebUI.setText(findTestObject('Page_CICIL_login/input_password'), varPass)

WebUI.click(findTestObject('Page_CICIL_login/button_Masuk'))

if ((varScope == 'admin') || (varScope == 'ambassador')) {
    //if(varScope == 'admin'){
    Thread.sleep(7000)

    //} 
    println(('>>> the scope is ' + varScope) + '. verifying elements')

    // create dynamic xpath (refer to https://dzone.com/articles/handling-static-and-dynamic-test-objects-katalon-s)
//    WebUI.verifyElementPresent(findTestObject('Page_CICIL_adminDashboard/span_Dash', [('username') : 'dev@cicil.co']), 3)
	String xpath_spanDash = "(.//*[normalize-space(text()) and normalize-space(.)='" + varEmail + "'])[1]/following::span[1]"
	println '>>> the span dash xpath is: ' + xpath_spanDash
	TestObject toSpanDash = new TestObject("span_Dash2")
	toSpanDash.addProperty("xpath", ConditionType.EQUALS, xpath_spanDash)
	
	// verifying elements
	WebUI.verifyElementPresent(toSpanDash, 3)

    WebUI.verifyElementVisible(findTestObject('Page_CICIL_adminDashboard/button_Add Success'))

    WebUI.verifyElementVisible(findTestObject('Page_CICIL_adminDashboard/button_Add Danger'))
} else if (varScope == 'student') {
    println('>>> the scope is student. verifying homepage elements')

    WebUI.waitForElementVisible(findTestObject('Page_CICIL_studentDashboard/strong_HeaderText'), 3)

    WebUI.verifyElementPresent(findTestObject('Page_CICIL_studentDashboard/button_AkunSaya'), 0)

    WebUI.verifyElementPresent(findTestObject('Page_CICIL_studentDashboard/button_Logout'), 0)

    WebUI.verifyElementPresent(findTestObject('Page_CICIL_studentDashboard/strong_HeaderText'), 0)
} else {
    println('UNDEFINED YET SCOPE ON SYSTEM')
}

println('going to sleep')

Thread.sleep(7000)

println('i am awake')

def webUrl = WebUI.getUrl()

println((('>>> value webUrl: ' + webUrl) + ', scope: ') + varScope)

