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
println 'landed on verification link page'

// hit the activation link
