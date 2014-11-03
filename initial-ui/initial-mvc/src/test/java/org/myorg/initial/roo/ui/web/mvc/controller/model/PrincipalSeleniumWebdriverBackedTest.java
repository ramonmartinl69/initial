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
 * (test-principal.xhtml)
 * Only works if the Application is running, run jetty:run first
 * @author Ramon
 *
 */
public class PrincipalSeleniumWebdriverBackedTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testPrincipalSeleniumWebdriverBacked() throws Exception {
		selenium.open("http://localhost:8080/initial-mvc/login;lang=en_US");
		selenium.type("id=j_username", "admin");
		selenium.type("id=j_password", "admin");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Initial-mvc", selenium.getText("_title_title_id_pane"));
		selenium.click("link=Create new Principal");
		selenium.waitForPageToLoad("30000");
		selenium.type("_userName_id", "userName@myorg.org");
		selenium.type("_password_id", "somePassword1");
		selenium.type("_enabled_id", "false");
		selenium.type("_activationKey_id", "someActivationKey1");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("userName@myorg.org", selenium.getText("_s_org_myorg_initial_roo_core_domain_security_Principal_userName_userName_id"));
		assertEquals("somePassword1", selenium.getText("_s_org_myorg_initial_roo_core_domain_security_Principal_password_password_id"));
		assertEquals("false", selenium.getText("_s_org_myorg_initial_roo_core_domain_security_Principal_enabled_enabled_id"));
		assertEquals("someActivationKey1", selenium.getText("_s_org_myorg_initial_roo_core_domain_security_Principal_activationKey_activationKey_id"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
