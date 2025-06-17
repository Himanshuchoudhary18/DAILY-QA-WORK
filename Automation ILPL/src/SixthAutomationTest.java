import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class SixthAutomationTest {
    public static String browser = "Edge";
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, NoSuchElementException {
        try {
            switch (browser)
            {
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
            driver.get("https://www.saucedemo.com");
            driver.manage().window().maximize();
            // WebElement
            WebElement username = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement button = driver.findElement(By.className("btn_action"));
            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            button.click();
            WebElement search = driver.findElement(By.className("inventory_item_description"));
            /* In the console we got "Inventory_item_description" so, it got verified that it is fetching the correct attribute */
            System.out.println(search.getAttribute("class")); // carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.
            System.out.println(search.getText()); // Add to cart
            System.out.println(search.getCssValue("color")); // rgba(19, 35, 34, 1)
            System.out.println(search.getTagName()); // div
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
