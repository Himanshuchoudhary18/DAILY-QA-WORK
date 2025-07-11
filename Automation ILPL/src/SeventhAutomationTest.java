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

public class SeventhAutomationTest {
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
            driver.get("https://www.sugarcrm.com/au/request-demo/");
            driver.manage().window().maximize();
            // Cookies Accepted or handled
            WebElement cookies = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
            cookies.click();

            // Filling the Form
            driver.findElement(By.id("input_1_1")).sendKeys("himanshupro83@gmail.com");
            driver.findElement(By.id("input_1_3_3")).sendKeys("himanshu_testing");
            driver.findElement(By.id("input_1_3_6")).sendKeys("choudhary_test");
            driver.findElement(By.id("input_1_4")).sendKeys("7669966400");
            driver.findElement(By.id("input_1_5")).sendKeys("QA Engineer");
            driver.findElement(By.id("input_1_6")).sendKeys("Infinite Locus Pvt Limited");
            // Handling Dropdown
            WebElement dropdown_companysize = driver.findElement(By.id("input_1_8"));
            Select select = new Select(dropdown_companysize);
            // select.selectByValue("level4");
            // OR //
            select.selectByVisibleText("251 - 500 employees");
            // OR //
            // select.selectByIndex(3);
            // driver.quit();
            WebElement country = driver.findElement(By.id("input_1_84"));
            Select selectCountry = new Select(country);
            // selectCountry.selectByValue("IN");
            // OR //
            // selectCountry.selectByIndex();
            // OR //
            selectCountry.selectByVisibleText("India");

            driver.findElement(By.xpath("//*[@id=\"input_1_10\"]")).sendKeys("Company");

            // Using Actions
            WebElement submitBtn = driver.findElement(By.xpath("//*[@id='gform_submit_button_1']"));
            Actions actions = new Actions(driver);
            actions.moveToElement(submitBtn).perform();
            submitBtn.click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
