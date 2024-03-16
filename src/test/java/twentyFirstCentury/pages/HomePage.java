package twentyFirstCentury.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePageObject{

    public HomePage(WebDriver driver, Logger log){
        super(driver,log);
    }

    private String homePageUrl = "https://www.21vek.by/";

    private By cookieModal = By.id("modal-cookie");
    private By acceptCookiesBtn = By.cssSelector("#modal-cookie .Button-module__blue-primary");


    //Open 21vek.by Homepage
    public void openHomepage(){
        log.info("Opening page: " + homePageUrl);
        openUrl(homePageUrl);
    }

    //Send GET Http request and get the code
    public int getRequest(){
        log.info("Sending GET request to: " + homePageUrl);
        int homeResponseCode = getResponseCode(homePageUrl);
        return homeResponseCode;
    }

    //Verify if cookie modal is displayed on the page
    public boolean isCookieModalDisplayed(){
        log.info("Verifying that the cookie modal is displayed");
        return find(cookieModal).isDisplayed();
    }

    //Accept cookies
    public void acceptCookies(){
        log.info("Clicking Accept button on the cookie modal");
        click(acceptCookiesBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(find(cookieModal)));
    }

    public boolean isCookieModalHidden(){
        log.info("Verifying that the cookie modal is hidden");
        List<WebElement> cookieModals = findAll(cookieModal);
        return cookieModals.isEmpty();
    }










}
