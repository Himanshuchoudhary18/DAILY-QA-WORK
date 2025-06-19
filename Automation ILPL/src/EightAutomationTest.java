import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EightAutomationTest {
    public static String browser = "Edge";
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, NoSuchElementException {
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
            driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
            driver.manage().window().maximize();

            WebElement find = driver.findElement(By.id("multi-select"));
            Select select = new Select(find);
            select.selectByValue("California");
            select.selectByIndex(5);

            List<WebElement> selectedItems = select.getAllSelectedOptions();
            System.out.println("Selected Items : " + selectedItems.size());
            select.deselectAll();
            List<WebElement> deselctItems = select.getAllSelectedOptions();
            System.out.println(deselctItems.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


