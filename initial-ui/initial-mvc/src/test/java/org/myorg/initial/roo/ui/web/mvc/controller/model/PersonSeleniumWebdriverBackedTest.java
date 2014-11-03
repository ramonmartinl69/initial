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

public class PersonSeleniumWebdriverBackedTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testPersonSeleniumWebdriverBacked() throws Exception {
		selenium.open("http://localhost:8080/initial-mvc/login;lang=en_US");
		selenium.type("id=j_username", "admin");
		selenium.type("id=j_password", "admin");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome to Initial-mvc", selenium.getText("_title_title_id_pane"));
		selenium.click("link=Create new Person");
		selenium.waitForPageToLoad("30000");
		selenium.type("_firstName_id", "someFirstName");
		selenium.type("_lastName_id", "someLastName");
		selenium.type("_lastName2_id", "someLastName2");
		selenium.type("_birthDate_id", "Oct 30, 2014");
		selenium.type("_nIF_id", "15256696T");
		selenium.type("_photoFile_id", "");
		selenium.type("_phone_id", "356956956");
		selenium.type("_response_id", "someResponse");
		selenium.type("_alternateQuestion_id", "someAlternateQuestion");
		selenium.type("_alternateResponse_id", "someAlternateResponse");
		selenium.click("id=proceed");
		selenium.waitForPageToLoad("30000");
		assertEquals("someFirstName", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_firstName_firstName_id"));
		assertEquals("someLastName", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_lastName_lastName_id"));
		assertEquals("someLastName2", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_lastName2_lastName2_id"));
		assertEquals("Oct 30, 2014", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_birthDate_birthDate_id"));
		assertEquals("15256696T", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_nIF_NIF_id"));
		assertEquals("", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_photoFile_photoFile_id"));
		assertEquals("356956956", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_phone_phone_id"));
		assertEquals("someResponse", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_response_response_id"));
		assertEquals("someAlternateQuestion", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_alternateQuestion_alternateQuestion_id"));
		assertEquals("someAlternateResponse", selenium.getText("_s_org_myorg_initial_roo_core_domain_model_Person_alternateResponse_alternateResponse_id"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
