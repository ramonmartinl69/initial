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
 * (test-homeaddress.xhtml)
 * Only works if the Application is running, run jetty:run first
 * @author Ramon
 *
 */
public class HomeAddressSeleniumWebdriverBackedTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testHomeAddressSeleniumWebdriverBacked() throws Exception {
		selenium.open("http://localhost:8080/initial-mvc/login;lang=en_US");
		selenium.type("id=j_username", "admin");
		selenium.type("id=j_password", "admin");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Initial-mvc", selenium.getText("_title_title_id_pane"));
		selenium.click("link=Create new Home Address");
		selenium.waitForPageToLoad("30000");
		selenium.type("_address_id", "someAddress");
		selenium.type("_postalCode_id", "28224");
		selenium.type("_population_id", "somePopulation");
		selenium.type("_addresNumber_id", "33");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("someAddress", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_HomeAddress_address_address_id"));
		assertEquals("28224", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_HomeAddress_postalCode_postalCode_id"));
		assertEquals("somePopulation", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_HomeAddress_population_population_id"));
		assertEquals("33", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_HomeAddress_addresNumber_addresNumber_id"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
