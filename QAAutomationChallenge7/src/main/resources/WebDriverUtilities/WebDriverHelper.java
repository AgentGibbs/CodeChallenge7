package WebDriverUtilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;

public class WebDriverHelper {

	public WebDriverHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public WebDriverHelper(WebDriver driver)
	{
		this.browserDriver = driver;
	}
	
	
	private WebDriver browserDriver;

	public WebDriver getBrowserDriver() {
		return browserDriver;
	}

	public void setBrowserDriver(WebDriver browserDriver) {
		this.browserDriver = browserDriver;
	}
	
	/**
	 * I got tired of typing out the same code to make the mouse move and hover. 
	 * @param targetElement The element that will be hovered over
	 * @param hoverInterval the length of time, in milliseconds, to hover
	 */
	public void mouseHover(WebElement targetElement, long hoverInterval) {
		Dimension elementSize = targetElement.getSize();
		int middleX = (elementSize.getWidth() / 2);
		int middleY = (elementSize.getHeight() / 2);
		Actions mouseActions = new Actions(browserDriver);
		mouseActions.moveToElement(targetElement,middleX,middleY);
		mouseActions.perform();
		try {
			synchronized (browserDriver) {
				browserDriver.wait(hoverInterval);
			}
		} catch (Exception E) {

		}
	}
	
	/**
	 * Creates an explicit wait in the WebDriver
	 * @param interval Length of desired wait, in milliseconds 
	 */
	public void waitForMilliseconds(long interval)
	{
		try {
			browserDriver.wait(interval);
		} catch (Exception e) {
		}
	}

	
	/**
	 *  BY AMIR GHAHRAI, http://www.testingexcellence.com/webdriver-wait-page-load-example-java/ 
	 * @param driver The Selenium WebDriver that is navigating to the web page
	 */
	public void waitForPageToLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

	/**
	 *  BY AMIR GHAHRAI, http://www.testingexcellence.com/webdriver-wait-page-load-example-java/ 
	 * @param driver The Selenium WebDriver that is navigating to the web page
	 */
	public int loadPageAndGetReturnCode(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
        return 0;
    }	

}
