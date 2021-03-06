package org.commons.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commons.constants.DIConstants;
import org.commons.logger.DILogger;
import org.commons.models.DIWebElements;
import org.commons.properties.DIProperties;
import org.openqa.selenium.WebDriver;

public class DIWebPageActions extends DIWebPage {

	private static final Logger logger = LogManager.getLogger(DILogger.class);
	
	
	public DIWebPageActions(WebDriver driver) {
		super(driver);
	}

	public void load() {
		load(DIProperties.getInstance().getProperty(DIConstants.URL_PROPERTY));
	}

	public void load(String url) {
		logger.info("Loading the webpage: " + url);
		getDriver().get(url);
	}

	public void click(DIWebElements guiElement) {
		waitUntilElementAvailable(guiElement);
		try {
			getElement(guiElement).click();
		} catch (Exception e) {
			logger.fatal("Element not Present: " + guiElement);
			throw (e);
		}

	}
	
	
	public void useValue(DIWebElements guiElement, String text) {
		waitUntilElementAvailable(guiElement);
		try {
			getElement(guiElement).sendKeys(text);
		} catch (Exception e) {
			logger.fatal("Element not Present: " + guiElement);
			throw (e);
		}
	}

	public void quit() {
		getDriver().quit();
	}
	
}
