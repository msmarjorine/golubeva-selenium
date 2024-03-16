package twentyFirstCentury.myTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;

import java.time.Duration;


public class HomePageTest extends TestUtilities {

    private String expectedHPTitle = "Онлайн-гипермаркет 21vek.by";
    private int expectedResponseCode = 200;


    @Test (priority = 1)
    public void homePageResponseCodeTest(){
        HomePage homePage = new HomePage(driver, log);
        int actualResponseCode = homePage.getRequest();
        Assert.assertEquals(actualResponseCode, expectedResponseCode, "The response code is not as expected.");

    }

    @Test (priority = 2)
    public void homePageTitleTest(){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();

        String actualHPTitle = homePage.getCurrentPageTitle();
        Assert.assertEquals(actualHPTitle, expectedHPTitle, "The page title is not as expected.");

    }

    @Test (priority = 3)
    public void cookieModalTest(){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();

        Assert.assertTrue(homePage.isCookieModalDisplayed(), "Cookie Modal is not displayed");

        homePage.acceptCookies();

        Assert.assertTrue(homePage.isCookieModalHidden(), "The cookie modal is still displayed");


    }








}
