import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class SecondAutomationTest {
    public static String browser = "Edge";
    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException, NoSuchElementException
    {
        try {
            switch (browser) {
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    System.out.println("Chrome Initialized");
                    break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    System.out.println("Firefox Initialized");
                    break;
                case "Safari":
                    WebDriverManager.safaridriver().setup();
                    System.out.println("Safari Initialized");
                    break;
                case "Edge":
                    WebDriverManager.edgedriver().setup();
                    System.out.println("Edge Initialized");
                    break;
                default:
                    System.out.println("No Browser Initialized");
                    break;
                // We can have switch statement without having default in the switch case like in if-else.
                // It is not necessary to have else in "if" statement. Same we don't need break statement everytime in every switch case but if it not present, then the purpose of switch-case will be violated because switch means multiple cases and break will work when switch will satisfy the condition as it is present in it and then break; is also within it so it will also need to break the flow o/w all will be executed.
            }
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.makemytrip.com");
            Thread.sleep(2000);
            // Not a suggested method to delay anything while performing testing (because it is like hard coding the automation test cases)
            driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/span")).click();
            driver.quit();
            // driver.quit() for closing the entire session (which includes all the test browsers that got open while performing testing
            // Hence, instead of doing driver.quit() if we just wanted to close a single browser or single tab that got open because of testing so we will choose driver.close() for that

            // This below method is old method of selenium -4-0-0 version for relative locators.
            // WebElement radiobutton = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div/div[1]/ul/li[1]"));
            // WebElement radio1 = driver.findElement(withTagName("input")).above(radiobutton);
            // radio1.click();

            // Modern Relative Locators in Selenium 4
            WebElement reference = driver.findElement(By.id("submitBtn"));
            By inputToLeft = RelativeLocator.with(By.tagName("input")).toLeftOf(reference);
            WebElement findingElement = driver.findElement(inputToLeft);
            findingElement.sendKeys("himanshu_sdet");


            // Similarly we can use other relative locators also
            // For Ex -> driver.findElement(withTagName("input")).below(reference);
            // .toLeftOf();
            // .toRightOf();
            // .near(reference) but the distance of the reference variable must be within 50px only.

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}