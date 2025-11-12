import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Paths

import javax.xml.xpath.XPath

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys








WebUI.openBrowser('https://sentinal.jalin.co.id/login')

//Login
WebUI.waitForElementPresent(findTestObject('Object Repository/Input Email'), 10)
WebUI.click(findTestObject('Object Repository/Input Email'))
WebUI.setText(findTestObject('Object Repository/Input Email'), "apis@jalin.co.id")
WebUI.click(findTestObject('Object Repository/Input Password'))
WebUI.setText(findTestObject('Object Repository/Input Password'), "!Medan123456")
WebUI.click(findTestObject('Object Repository/Button Submit Login'))

//2fa
WebUI.waitForElementPresent(findTestObject('Object Repository/Input 2fa'), 10)
WebUI.setText(findTestObject('Object Repository/Input 2fa'), "382769")
WebUI.click(findTestObject('Object Repository/Button 2fa'))

//Set Ke menu Client
WebUI.click(findTestObject('Object Repository/Registrasi Client/Navbar Regis client'))


//Start loop


String projectDir = RunConfiguration.getProjectDir()
String pathDirectory = Paths.get(projectDir,"Data Files", "listRegisterMerchant.csv")
File file = new File(pathDirectory)

file.readLines().drop(1).each { line ->
	// Split the line by comma
	String[] values = line.split(',')
	
	WebUI.click(findTestObject('Object Repository/Registrasi Client/Button Create Merchant'))
	
	//Set RegisteredName
	setText("registeredName", values[0])
	setText("registrationNumber", values[1])
	setText("currency", values[2])
	
	//setText("email", values[3])
	//setText("phoneNumber", values[4])
	
	
	setText("mccCode", values[5])
	setText("organizationTin", values[6])
	
	//Input merchant Classification
	WebUI.waitForElementVisible(findTestObject('Object Repository/Registrasi Client/Form input/Drop down Classification'), 1)
	WebUI.click(findTestObject('Object Repository/Registrasi Client/Form input/Drop down Classification'))
	WebUI.click(new TestObject().addProperty("xpath", ConditionType.EQUALS, "//li[@data-value='${values[7]}']"))
	
	
	setText("merchantName", values[8])
	setText("merchantCity", values[9])
	setText("merchantCountryCode", values[10])
	//setText("merchantId", values[11])
	
	//setText("acquirerInstitutionId", values[12])
	//setText("merchantGroup", values[13])
	
	//Submit Merchant
	WebUI.click(findTestObject('Object Repository/Registrasi Client/Form input/Submit Merchant'))
}







def setText(name,text) {
	WebUI.setText(new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@name='${name}']"),text)
}







