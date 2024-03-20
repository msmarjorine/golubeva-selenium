package twentyFirstCentury.myTests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import twentyFirstCentury.base.TestUtilities;
import twentyFirstCentury.pages.HomePage;

public class SearchTest extends TestUtilities {

    @Parameters({"goods"})
    @Test
    public void searchGoods(String goodsName){
        String expectedGoodsName = goodsName;

        HomePage homePage = new HomePage(driver, log);
        homePage.openHomepage();
        homePage.acceptCookies();

        homePage.searchFor(goodsName);

    }

}
