import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Paths
import java.util.concurrent.locks.Condition

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://sentinal-web-dev-749998763474.asia-southeast2.run.app/login')
WebUI.maximizeWindow()

//Login
WebUI.waitForElementPresent(findTestObject('Object Repository/Input Email'), 2)
WebUI.click(findTestObject('Object Repository/Input Email'))
WebUI.setText(findTestObject('Object Repository/Input Email'), "apis@jalin.co.id")
WebUI.click(findTestObject('Object Repository/Input Password'))
WebUI.setText(findTestObject('Object Repository/Input Password'), "")
WebUI.click(findTestObject('Object Repository/Button Submit Login'))

//2fa
WebUI.waitForElementPresent(findTestObject('Object Repository/Input 2fa'), 2)
WebUI.setText(findTestObject('Object Repository/Input 2fa'), "002622")
WebUI.click(findTestObject('Object Repository/Button 2fa'))

//Direct to Client Menu
WebUI.click(findTestObject('Object Repository/Menu Client'))

//Click Input Merchant Name
WebUI.click(findTestObject('Object Repository/InputMerchantName'))
processCsv()

WebUI.closeBrowser()

public List<List<String>> processCsv(){
	
	String projectDir = RunConfiguration.getProjectDir()
	String pathDirectory = Paths.get(projectDir,"Data Files", "PMSE211.csv")
	
	
	BufferedReader br = new BufferedReader(new FileReader(pathDirectory))
	String line
	
	

	
	while ((line = br.readLine())!=null) {
		//Input Merchant Name
		WebUI.waitForElementVisible(findTestObject('Object Repository/InputMerchantName'),2)
		WebUI.setText(findTestObject('Object Repository/InputMerchantName'), line)
		
		//Checklist
		def elementMerchantPMSE = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//h6[.='"+line.replaceAll("[^a-zA-Z0-9 ]", "")+"']")
		WebUI.waitForElementVisible(elementMerchantPMSE, 5)
		WebUI.click(elementMerchantPMSE)
		
		String triggerDropdown = 'Object Repository/Configurable/dropDownStatusCollected'
		
		
		/*
		if(WebUI.verifyElementChecked(findTestObject(nonVatChecklist), 10)==false) {
			WebUI.click(findTestObject(nonVatChecklist))
			//Dialog confirmaski
			WebUI.waitForElementVisible(findTestObject('Object Repository/accVAT'),10)
			WebUI.click(findTestObject('Object Repository/accVAT'))
		}
		*/
		
		def buttonPottentialCollected = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//span[.='Potentially Collected']")
		def klikSembarangTempat = findTestObject('Object Repository/outsideLayer')
		
		def confirmpt  = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//button[.='Mark as Potentially Collected']") 

		
		WebUI.click(findTestObject(triggerDropdown))
		
		
		
		//WebUI.click(findTestObject('Object Repository/Configurable/dropDownStatusCollected'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Configurable/buttonPottentialCollected'),2)
		WebUI.click(buttonPottentialCollected)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Configurable/confirmPotentialCollected'),2)
		WebUI.click(confirmpt)
		WebUI.click(klikSembarangTempat)
		//WebUI.click(findTestObject('Object Repository/Configurable/markAsPotentialCollected'))
		//WebUI.click(klikSembarangTempat)
		//Dialog confirmaski
		//WebUI.waitForElementVisible(findTestObject('Object Repository/accVAT'),10)
		//WebUI.click(findTestObject('Object Repository/accVAT'))
		
		
		//Do Potential Collected
		
		
		//ScreenShoot(line)
		
		//Ulang dari awal
		WebUI.click(findTestObject('Object Repository/Menu Client'))
	}
	
	
	return null
}



public void ScreenShoot(String filename) {
	String dirscreenshoot = Paths.get(RunConfiguration.getProjectDir(),"Data Files/Screenshoot","${filename}.png")
	WebUI.takeScreenshot(dirscreenshoot)
}











