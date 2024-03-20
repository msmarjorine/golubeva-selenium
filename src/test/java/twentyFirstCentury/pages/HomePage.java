package twentyFirstCentury.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By denyCookiesBtn = By.xpath("//button[@aria-label='Отклонить']");
    private By denyModalTitle = By.cssSelector("div[role='presentation'] h5");
    private By denyCookiesConfirmBtn = By.xpath("//button[@class='Button-module__button Button-module__gray-secondary']");
    private By accountMenu = By.className("userToolsText");
    private By accountBtn = By.cssSelector("#userToolsDropDown button.Button-module__blue-primary");
    private By emailInput = By.id("login-email");
    private By passwordInput = By.id("login-password");
    private By signInBtn = By.cssSelector("button[data-testid='loginSubmit']");
    private By userEmail = By.className("userToolsSubtitle");


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

    public boolean isCookieModalDisplayed(){
        log.info("Verifying that the cookie modal is displayed");
        return find(cookieModal).isDisplayed();
    }

    //Accept cookies
    public void acceptCookies(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(cookieModal));
        log.info("Clicking Accept button on the cookie modal");
        click(acceptCookiesBtn);
        wait.until(ExpectedConditions.stalenessOf(find(cookieModal)));

    }

    public boolean isCookieModalHidden(){
        log.info("Verifying that the cookie modal is hidden");
        List<WebElement> cookieModals = findAll(cookieModal);
        return cookieModals.isEmpty();
    }

    //Deny cookies
    public String denyCookies(){
        log.info("Clicking Deny button on the cookie modal");
        click(denyCookiesBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(denyModalTitle));
        return find(denyModalTitle).getText();
    }

    public void denyCookiesConfirm(){
        log.info("Clicking Deny button on the second cookie modal");
        click(denyCookiesConfirmBtn);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(find(denyCookiesConfirmBtn)));
    }

    public void login(String email, String password){
        log.info("Log in with the provided creds");
        click(accountMenu);
        click(accountBtn);
        type(email, emailInput);
        type(password, passwordInput);
        click(signInBtn);

        /*List <WebElement> accountMenuList = findAll(accountMenu);
        if(accountMenuList.isEmpty()){
            waitFor(ExpectedConditions.visibilityOfElementLocated(accountMenu), Duration.ofSeconds(10));
        }*/

    }

    public String getUserEmail() {
        log.info("Getting user email from the account dropdown");


        click(accountMenu);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userEmail));

        String currentEmail = find(userEmail).getText();
        return currentEmail;
    }










}
