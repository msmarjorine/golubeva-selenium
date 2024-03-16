package twentyFirstCentury.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /** Open page with given URL */
    protected void openUrl(String url) {
        driver.get(url);
    }

    //Get the page URL from the browser
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Get the current page title from the browser
    public String getCurrentPageTitle(){
        return driver.getTitle();
    }

    //Get the source of the current page from the browser
    public String getCurrentPageSource(){
        return driver.getPageSource();
    }

    //Get responseCode of the request
    public int getResponseCode(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Return -1 if there is an exception
        }
    }

    /** Find element using given locator */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**Find all elements using given locator */
    protected List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    /** Click on element with given locator when its visible */
    protected void click(By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).click();
    }

    /** Type given text into element with given locator */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).sendKeys(text);
    }

    /** Wait for specific ExpectedCondition for the given Duration*/
    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut){
        timeOut = timeOut != null ? timeOut : Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Duration... timeOut) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOut.length > 0 ? timeOut[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    //Wait for alert to display and then switch to it
    protected Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    //Switch to a new window
    public void switchToWindowWithTitle(String expectedTitle){
        String currentWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

        while(windowsIterator.hasNext()){
            String windowHandle = windowsIterator.next().toString();
            if(!windowHandle.equals(currentWindow)){
                driver.switchTo().window(windowHandle);
                if(getCurrentPageTitle().equals(expectedTitle)){
                    break;
                }
            }
        }

    }

    //Switch to iFrame using its locator
    protected void switchToFrame(By frameLocator){
        driver.switchTo().frame(find(frameLocator));
    }

    //Press Key on locator
    protected void pressKey(By locator, Keys key){
        find(locator).sendKeys(key);
    }
    //Press Key without a locator
    public void pressKeyWithActions(Keys key){
        log.info("Pressing " + key.name() + " using Actions class");
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }

    public void scrollToBottom(){
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    public void performDragAndDrop(By from, By to){
        log.info("Dragging from " + from + " and dropping to " + to);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(find(from), find(to)).build().perform();
    }

    public void hoverOverElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

    }

    //Add cookie
    public void setCookie(Cookie ck){
        log.info("Adding cookie " + ck.getName());
        driver.manage().addCookie(ck);
        log.info("Cookie added");
    }

    //Get cookie value by the cookie name
    public String getCookie(String name){
        log.info("Getting value of cookie " + name);
        return driver.manage().getCookieNamed(name).getValue();
    }

}
