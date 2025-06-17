import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ThirdAutomationTest {
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
                // This below method is old method of selenium -4-0-0 version for relative locators.
                // WebElement radiobutton = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div/div[1]/ul/li[1]"));
                // WebElement radio1 = driver.findElement(withTagName("input")).above(radiobutton);
                // radio1.click();

                // .above()
                // .toLeftOf()
                // .toRightOf()
                // .below()

                // Modern Relative Locators in Selenium 4
                // WebElement reference = driver.findElement(By.id("submitBtn"));
                // By inputToLeft = RelativeLocator.with(By.tagName("input")).toLeftOf(reference);
                // WebElement findingElement1 = driver.findElement(inputToLeft);
                // findingElement1.sendKeys("himanshu_sdet");

                // One more way of doing it
                // WebElement findingElement = driver.findElement(By.id("submitBtn"));
                // driver.findElement(RelativeLocator.with(By.tagName("input")).toLeftOf(findingElement)).sendKeys("Good");
                // Here, we didn't have to write or create seperate webElement for everything

                // Now breaking the above 2 lines of code line by line (to make it more simplified
                // WebElement good = driver.findElement(By.id("good"));
                // By anyname = RelativeLocator.with(By.tagName("input")).toLeftOf(good);
                // WebElement foundElement = driver.findElement(anyname);
                // foundElement.sendKeys("Great");

                // WebElement good1 = driver.findElement(By.className("first_name"));
                // WebElement good2 = driver.findElement(withTagName("input")).above(good1); //.click();
                // OR //
                // good2.sendKeys("Good2");

                // Similarly we can use other relative locators also
                // For, Ex -> driver.findElement(withTagName("input")).below(reference);
                // .toLeftOf();
                // .toRightOf();
                // .below();
                // .near(reference) but the distance of the reference variable must be within 50px only
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
}
