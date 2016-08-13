package seleniumLaunchers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import skiUtahPageClasses.*;

public class SeleniumLauncher {
	public HtmlUnitDriver driver;
	public ChromeDriver driver2;
	private SkiUtahPageWithLinks page;
	public SeleniumLauncher() {
/*		
		driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
*/
		
		
		ChromeOptions opts = new ChromeOptions();
		opts.addArguments("chrome.switches","--disable-extensions");
		driver2 = new ChromeDriver(opts);
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		page = new SkiUtahPageWithLinks(driver2);
		page.navigateToLink("http://www.skiutah.com");
		page.findAllLinksOnPage();
		//page.addCurrentPageLinksToHashmap();
		
		driver2.quit();
		
		
	}

	public void printMap(HashMap mp) {
	    
	   
	}
	
}
