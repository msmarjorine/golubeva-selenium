package twentyFirstCentury.myTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;

public class PositiveLoginTest extends TestUtilities {

    String validEmail = "juliajuliazzo@yahoo.com";
    String validPassword = "92af7991";

    @Test
    public void positiveLoginTest(){
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();
        homePage.acceptCookies();

        homePage.login(validEmail, validPassword);

        String actualEmail = homePage.getUserEmail();
        Assert.assertEquals(actualEmail, validEmail);
    }
}
