package twentyFirstCentury.myTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;


public class HomePageTest extends TestUtilities {

    private String expectedHPTitle = "Онлайн-гипермаркет 21vek.by";

    @Test
    public void homePageLoadsTest(){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();

        String actualHPTitle = homePage.getCurrentPageTitle();
        Assert.assertEquals(actualHPTitle, expectedHPTitle, "The page title is not as expected.");

    }








}
