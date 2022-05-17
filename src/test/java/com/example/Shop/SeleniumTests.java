package com.example.Shop;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class SeleniumTests {
        @Test
        void MainPage() {
            ChromeDriver driver = new ChromeDriver();
            driver.get("http://localhost:8080/");
            assertEquals("Главная", driver.getTitle());
            driver.quit();
        }

        @Test
        void Header(){
            ChromeDriver driver = new ChromeDriver();
            driver.manage().window().setPosition(new Point(0,0));
            driver.manage().window().setSize(new Dimension(1024,768));
            driver.get("http://localhost:8080/");

            /*WebElement CoffeeButton = driver.findElement(By.id("b_Кофеварки"));
            CoffeeButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals(, driver.getTitle());

            WebElement rootButton = driver.findElement(By.id("rootLink"));
            rootButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals(rootTitle, driver.getTitle());

            WebElement placesButton = driver.findElement(By.id("placesListLink"));
            placesButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals(placesTitle, driver.getTitle());

            rootButton = driver.findElement(By.id("rootLink"));
            rootButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals(rootTitle, driver.getTitle());
*/
            driver.quit();


        }
}
