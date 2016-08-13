package skiUtahPageClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.net.www.protocol.file.*;
public class SkiUtahPageWithLinks {

	// member variables
	private WebDriver driver;
	private ArrayList<String> visitedLinksList;
	

	private LinkedHashMap visitedLinks;
	public LinkedHashMap allURLs;

	private URL target;

	// Constructors
	public SkiUtahPageWithLinks(WebDriver driver) {
		this.driver = driver;
		allURLs = new LinkedHashMap();
		
		visitedLinks = new LinkedHashMap();	
	}

	// methods

	
	
	
	public void findAllLinksOnPage() {
		System.out.println("Adding links from " + driver.getTitle() + " page");
		List<WebElement> linksOnPage = driver.findElements(By.tagName("a"));
		for (int index = 0; index < linksOnPage.size(); index += 1)
		{
			WebElement elementToCheck = linksOnPage.get(index);
			String linkUrl = elementToCheck.getAttribute("href");
			if (isUrlOkay(linkUrl) == true) {
				allURLs.putIfAbsent(linkUrl.toLowerCase(), elementToCheck.getText().toLowerCase());
				//allURLs.
				//allUrlsList.add(linkUrl);
			} // end if
		} // end for

	}

	
	
	public void addCurrentPageLinksToHashmap(){
		
	Iterator it = allURLs.entrySet().iterator();
	boolean moreOn =it.hasNext(); 
	int hashIndex = 0;
	while (moreOn) {
    	it = allURLs.entrySet().iterator();
    	System.out.println("it.hasNext()");
    	
    	
//    	hashIndex =  
    	HashMap.Entry pair = (HashMap.Entry)it.next();
        System.out.println("HashMap.Entry pair = (HashMap.Entry)it.next();");	        
        
        System.out.println(pair.getKey() + " = " + pair.getValue());
        System.out.println("System.out.println(pair.getKey()  + pair.getValue())");
        String keyString = (String) pair.getKey();
        System.out.println("it.remove(); // avoids a ConcurrentModificationException");
        moreOn =it.hasNext();
        it.remove(); // avoids a ConcurrentModificationException
        //System.out.println("page.navigateToLink(keyString);");
        //navigateToLink(keyString);
        //System.out.println("page.navigateToLink(keyString);");
        
  
    }
    }
	
	
	private boolean isUrlOkay(String linkUrl) {
		boolean isOK = true;
		
		// already been visited.
		if (visitedLinks.containsKey(linkUrl.toLowerCase()) == true) {
			isOK = false;
		}
		// already on the list
		if (allURLs.containsKey(linkUrl.toLowerCase()) == true) {
			isOK = false;
		}
		// out of domain
		if (linkUrl.toLowerCase().contains("skiutah.com") == false) {
			isOK = false;
		}
		return isOK;
	}

	public void navigateToLink(String url) {
		System.out.println("Navigating to " + url);
		driver.get(url);
		//System.out.println(url);
		convertToUrlObject(url);
		visitedLinks.put(url.toLowerCase(), getHttpResponseCode());
		System.out.println("Response Code: " + getHttpResponseCode());
		findAllLinksOnPage();
	}

	private void convertToUrlObject(String url) {
		try {
			target = new URL(url);
		}

		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	private int getHttpResponseCode() {
		int httpCode = 00;
		if (target != null) {
			try {
			 HttpURLConnection connection = (HttpURLConnection) target.openConnection();
				//FileURLConnection connection = (FileURLConnection) target.openConnection();
				httpCode = connection.getResponseCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return httpCode;
	}

}
