package twentyFirstCentury.myTests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;


public class PositiveLoginTest extends TestUtilities {

    @Parameters({"validEmail", "validPassword"})
    @Test
    public void positiveLoginTest(String validEmail, String validPassword ){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();
        homePage.acceptCookies();

        homePage.login(validEmail, validPassword);
        //Haven't found a way to wait for some expected conditions, so added explicit wait
        sleep(3000);

        String actualEmail = homePage.getUserEmail();
        Assert.assertEquals(actualEmail, validEmail);
    }
}
