import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FirstAutomationTest {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("First Automation");
//        // System.setProperty("webdriver.chrome.driver", "C:\\Work Material\\Web Drivers\\Chrome Driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//        // System.setProperty("webdriver.edge.driver", "C:\\Work Material\\Web Drivers\\Edge Driver\\edgedriver_win64\\msedgedriver.exe");
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//        driver.get("https://www.google.com");
//        driver.quit();
//
//
//        WebDriverManager.edgedriver().setup();
//        WebDriver driver1 = new EdgeDriver();
//        driver1.manage().window().maximize();
//        driver1.get("https://www.google.com");
//        driver1.quit();

        String browser = "Edge";
        if(browser.equals("Edge"))
        {
            WebDriverManager.edgedriver().setup();
        }
        else if (browser.equals("chrome"))
        {
            WebDriverManager.chromedriver().setup();
        }
        else if (browser.equals("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
        }
        else
        {
            System.out.println("Browser not found");
        }
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        Thread.sleep(2000);
        searchbox.sendKeys("YouTube");
        searchbox.sendKeys(Keys.ENTER);
        driver.close();
    }
}
