package org.myorg.initial.roo.ui.web.mvc.controller.model;


import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

/**
 * Converted from the Firefox Selenium IDE Plugin
 * Fie -->Export File as -->Junit4 Webdriver Backed
 * (test-login-logout.xhtml)
 * Only works if the Application is running, run jetty:run first
 * @author Ramon
 *
 */
public class LoginLogoutSeleniumWebdriverBackedTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testLoginLogpout() throws Exception {
		selenium.open("http://localhost:8080/initial-mvc/login;lang=en_US");
		selenium.click("id=j_username");
		selenium.type("id=j_username", "admin");
		selenium.click("id=j_password");
		selenium.type("id=j_password", "admin");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Initial-mvc", selenium.getText("_title_title_id_pane"));
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertEquals("", selenium.getText("j_username"));
		assertEquals("", selenium.getText("j_password"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
