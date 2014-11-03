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

public class WorkAddressSeleniumWebdriverBackedTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testWorkAddressSeleniumWebdriverBacked() throws Exception {
		selenium.open("http://localhost:8080/initial-mvc/login;lang=en_US");
		selenium.type("id=j_username", "admin");
		selenium.type("id=j_password", "admin");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Initial-mvc", selenium.getText("_title_title_id_pane"));
		selenium.click("link=Create new Work Address");
		selenium.waitForPageToLoad("30000");
		selenium.type("_address_id", "someAddress");
		selenium.type("_postalCode_id", "28224");
		selenium.type("_population_id", "somePopulation");
		selenium.type("_addresNumber_id", "33");
		selenium.type("_enterpriseName_id", "someEnterpriseName");
		selenium.type("_postalAddress_id", "somePostalAddress");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("someAddress", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_address_address_id"));
		assertEquals("28224", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_postalCode_postalCode_id"));
		assertEquals("somePopulation", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_population_population_id"));
		assertEquals("33", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_addresNumber_addresNumber_id"));
		assertEquals("someEnterpriseName", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_enterpriseName_enterpriseName_id"));
		assertEquals("somePostalAddress", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_WorkAddress_postalAddress_postalAddress_id"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
