package com.example.Shop;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTests {
        @Test
        void MainPage() {
            ChromeDriver driver = new ChromeDriver();
            driver.get("http://localhost:8080/");
            assertEquals("Главная", driver.getTitle());
            driver.quit();
        }
        @Test
        void Header() {
            ChromeDriver driver = new ChromeDriver();
            driver.manage().window().setPosition(new Point(0,0));
            driver.manage().window().setSize(new Dimension(1024,768));
            driver.get("http://localhost:8080/");

            WebElement peopleButton = driver.findElement(By.id("b_allClients"));
            peopleButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals("Клиенты", driver.getTitle());

            WebElement rootButton = driver.findElement(By.id("b_Main"));
            rootButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals("Главная", driver.getTitle());

            WebElement placesButton = driver.findElement(By.id("b_allRequests"));
            placesButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            assertEquals("Заказы", driver.getTitle());

            driver.quit();
        }

    @Test
    void Catalogue() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement dropButton = driver.findElement(By.id("navbarDropdown"));
        dropButton.click();
        WebElement fridgeButton = driver.findElement(By.id("d_b_Холодильники"));
        fridgeButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement label = driver.findElement(By.id("goods_header"));
        assertEquals("Наши товары вида «Холодильник»:", label.getText());
        driver.quit();
    }

    @Test
    void addClient() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/allClients");

        WebElement editButton = driver.findElement(By.id("editButton"));
        editButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS); 
        WebElement loginField = driver.findElement(By.id("f_login"));
        loginField.sendKeys("Mr.Tester");
        WebElement passwordField = driver.findElement(By.id("f_pass"));
        passwordField.sendKeys("244466666");
        WebElement nameField = driver.findElement(By.id("f_name"));
        nameField.sendKeys("Selenium");
        WebElement saveButton = driver.findElement(By.id("saveButton"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.get("http://localhost:8080/allClients");
        driver.findElement(By.partialLinkText("Mr.Tester"));
        driver.quit();
    }

    @Test
    void addEditAndDeleteRequest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/allRequests");

        WebElement editButton = driver.findElement(By.id("editButton"));
        editButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement loginField = driver.findElement(By.id("f_creator"));
        loginField.sendKeys("Mr.Tester");
        WebElement saveButton = driver.findElement(By.id("saveButton"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement edit = driver.findElement(By.id("editButton"));
        edit.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        Select f_goods = new Select(driver.findElement(By.id("f_goods")));
        f_goods.selectByVisibleText("Galaxy GL 6126 Violet");
        WebElement f_add = driver.findElement(By.id("f_add"));
        f_add.sendKeys("4");
        WebElement b_add = driver.findElement(By.id("b_add"));
        b_add.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        saveButton = driver.findElement(By.id("saveButton"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement delete = driver.findElement(By.id("deleteButton"));
        delete.click();
        driver.get("http://localhost:8080/allRequests");
        boolean b = true;
        try{
            driver.findElement(By.partialLinkText("Mr.Tester"));
        }
        catch (Exception e){
            b = false;
        }
        assertEquals(b, false);
        driver.quit();
    }
    @Test
    void SearchAndEditGood() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement panel = driver.findElement(By.id("search-panel"));
        panel.sendKeys("redmond");
        WebElement button = driver.findElement(By.id("search-button"));
        button.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement label = driver.findElement(By.id("search_header"));
        assertEquals("Результаты поиска по запросу «redmond»:", label.getText());
        WebElement goodRef = driver.findElement(By.partialLinkText("Redmond RJ-980S"));
        goodRef.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement edit = driver.findElement(By.id("editButton"));
        edit.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement f_in_stock = driver.findElement(By.id("f_in_stock"));
        f_in_stock.clear();
        f_in_stock.sendKeys("18849");
        WebElement saveButton = driver.findElement(By.id("saveButton"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        f_in_stock = driver.findElement(By.id("f_in_stock"));
        assertEquals("В наличии: 18849 шт.", f_in_stock.getText());
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.get("http://localhost:8080/");
        panel = driver.findElement(By.id("search-panel"));
        panel.sendKeys("qwertyuiop");
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        button = driver.findElement(By.id("search-button"));
        button.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        label = driver.findElement(By.id("search_header2"));
        assertEquals("Пока ничего нет:(", label.getText());

    }
    @Test
    void TryEmptyGood() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/allGoods");
        WebElement edit = driver.findElement(By.id("editButton"));
        edit.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement saveButton = driver.findElement(By.id("saveButton"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(driver.getTitle(), "Ошибка!");

    }


}
