import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Test1 {

    WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        //Локализация
        options.addArguments("lang=en-GB" /*"lang=es-ES*/ /*"lang=ru-RU"*/);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://store.steampowered.com/");
    }

    @DataProvider(name = "data-provider")
    public Object[][] games() {
        return new Object[][]{{"The Witcher"}, {"Fallout"}};
    }

    @Test
    public void checkMainPage() {
        MainPage mp = new MainPage(driver);
        Assert.assertTrue(mp.checkPage(), "Main page not found");
    }

    @Test(dataProvider = "data-provider")
    public void checkSearchPage(String game) {
        MainPage mp = new MainPage(driver);
        mp.enterGameName(game);

        SearchPage sp = new SearchPage(driver, wait);
        Assert.assertTrue(sp.checkPage(), "Search page not found");
        Assert.assertTrue(sp.checkNotEmptyListGames(), "Game list is empty");
    }

    @Test
    public void checkSortingGames() {
        SearchPage sp = new SearchPage(driver, wait);
        Assert.assertTrue(sp.sortingByPriceDesc(), "Games are not sorted in descending order");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}