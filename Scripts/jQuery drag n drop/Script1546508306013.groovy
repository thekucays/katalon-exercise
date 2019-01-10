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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.lang.Thread
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.openBrowser('')

WebUI.navigateToUrl('http://jqueryui.com/droppable/#default')

WebUI.maximizeWindow()

// test printing
Thread.sleep(1000)
System.out.println('testing printing something here')

// variables / elements
dropPoint = findTestObject('Page_jQueryTest/div_Drop2')
target = findTestObject('Page_jQueryTest/div_Drag me to my target')
iframeContainer = findTestObject('Page_jQueryTest/iframe_Visual feedback_demo-fr')

// check if element is visible
WebUI.verifyElementPresent(dropPoint, 2000)
WebUI.verifyElementPresent(target, 2000)
WebUI.verifyElementPresent(iframeContainer, 2000)

// start dragging
WebUI.dragAndDropToObject(target, dropPoint)
//WebUI.dragAndDropByOffset(findTestObject('Page_jQueryTest/div_Drag me to my target'), 200, 0)
Thread.sleep(5000)

//kelar, close browser
WebUI.closeBrowser()

CustomKeywords.'com.luki.PrintOutTest.startPrint'('hayyyy')
