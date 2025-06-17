import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FourthAutomationTest {
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
            // driver = new EdgeDriver();
            // driver.get("https://google.com");
            // Thread.sleep(2000);
            // driver.navigate().to("https://yahoo.com");
            // Thread.sleep(2000);
            // driver.navigate().back();
            // Thread.sleep(2000);
            // driver.navigate().forward();
            // Thread.sleep(2000);
            // driver.navigate().refresh();
            // Like we can clearly see, here all things getting done on the same tab(of same session) but here we are getting lot of options like back(), refresh(), forward() and navigate.to() also.
            // Whereas, in the driver.get("url") we are doing the same thing, but it is not suggested, as it is not dynamic plus it doesn't provide you lot of features (like back(), navigate.to(), forward() and refresh())

            driver = new EdgeDriver();
            driver.get("https://google.com");
            Thread.sleep(2000);
            driver.get("https://yahoo.com");
            Thread.sleep(2000);
            driver.get("https://www.makemytrip.com");
            Thread.sleep(2000);
            driver.get("https://www.youtube.com");
            driver.navigate().refresh();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}