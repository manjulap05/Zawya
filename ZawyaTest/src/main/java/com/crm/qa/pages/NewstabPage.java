package com.crm.qa.pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class NewstabPage extends TestBase {
	
	TestUtil testUtil;
	
	
	@FindBy(xpath = "//*[@id='websiteMenu']//button[contains(text(),'+menuname+')]")
	WebElement Mtab;
	
	public NewstabPage() {
		PageFactory.initElements(driver, this);
	}
		
	
		// method to verify title 
	public String verifyHomePageTitle(){
		try {
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
				} catch (NoSuchElementException e) {
			System.out.println("search button avaliable");
		}
		return driver.getTitle();
		}

	public boolean verifyCorrectUserName(String mtab){
		return Mtab.isDisplayed();
	}
}
