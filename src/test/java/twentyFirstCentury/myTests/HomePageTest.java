package twentyFirstCentury.myTests;

import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;


public class HomePageTest extends TestUtilities {

    @Test
    public void homePageLoadsTest(){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();
    }








}
