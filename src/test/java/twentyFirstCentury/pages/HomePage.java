package twentyFirstCentury.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject{

    public HomePage(WebDriver driver, Logger log){
        super(driver,log);
    }

    private String homePageUrl = "https://www.21vek.by/";

    //Open 21vek.by Homepage
    public void openHomepage(){
        log.info("Opening page: " + homePageUrl);
        openUrl(homePageUrl);
    }









}
