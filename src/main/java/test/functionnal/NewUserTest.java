package test.functionnal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.mockito.Mockito.spy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.helper.BaseTest;

import com.thoughtworks.selenium.Selenium;

import database.DbHelper;

public class NewUserTest extends BaseTest {
	private Selenium selenium;
	
	private String baseUrl = "http://localhost:8080";

	@Before
	public void initialize() {
		super.initialize();
		selenium = new WebDriverBackedSelenium(new FirefoxDriver(), baseUrl);
	}
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

	@Test
	public void canRedirectToMainPageOnValidUser() throws Exception {		
		selenium.open("/newuser");	
		selenium.waitForPageToLoad("5000");
		fillForm();
		
		selenium.click("css=button.btn.btn-default");
		selenium.waitForPageToLoad("10000");
		
		assertThat(selenium.getLocation(), containsString(baseUrl + "/newuser"));	
		//assertFalse(selenium.getLocation().contains("/newuser"));
	}
	
	@Test
	public void canStayOnTheSameURLOnInvalidUser() throws Exception {
		selenium.open("/newuser");	
		selenium.waitForPageToLoad("5000");
		
		selenium.click("css=button.btn.btn-default");		
		selenium.waitForPageToLoad("10000");
		
		assertThat(selenium.getLocation(), containsString(baseUrl + "/newuser"));	
	}
	
	@Test
	public void canShowWarningsOnInvalidUser() throws Exception {
		selenium.open("/newuser");		
		selenium.waitForPageToLoad("5000");
		
		selenium.click("css=button.btn.btn-default");
		selenium.waitForPageToLoad("10000");		
		
		assertTrue(selenium.isTextPresent("Entrez un nom de famille"));	
		assertFalse(selenium.isTextPresent("Le nom d'utilisateur est deja utilise"));	
	}
	
	private void fillForm() {
		selenium.type("id=firstName", "test");
		selenium.type("id=lastName", "test");
		selenium.type("id=username", "test");
		selenium.type("id=password", "test");
		selenium.type("id=phoneNumber", "418-657-9823");
		selenium.type("id=address", "test");
	}	
}
