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
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.lang.Thread as Thread

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

Thread.sleep(2000)

WebUI.maximizeWindow()

WebUI.click(findTestObject('Page_CURA Healthcare Service/a_Make Appointment'))

WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username_username'), varUsername)

WebUI.setEncryptedText(findTestObject('Page_CURA Healthcare Service/input_Password_password'), 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')

WebUI.click(findTestObject('Page_CURA Healthcare Service/button_Login'))

WebUI.click(findTestObject('Page_CURA Healthcare Service/label_Apply for hospital readm'))

WebUI.click(findTestObject('Page_CURA Healthcare Service/div_Visit Date (Required)_inpu'))

WebUI.click(findTestObject('Page_CURA Healthcare Service/td_24'))

WebUI.setText(findTestObject('Page_CURA Healthcare Service/textarea_Comment_comment'), 'thus is comment')

WebUI.click(findTestObject('Page_CURA Healthcare Service/button_Book Appointment'))

WebUI.click(findTestObject('Page_CURA Healthcare Service/a_Go to Homepage'))

WebUI.closeBrowser()

