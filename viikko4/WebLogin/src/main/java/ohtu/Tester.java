package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Onnistunut kirjautuminen
        driver.get("http://localhost:4567");
        sleep(2);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
        sleep(2);
        
        driver.findElement(By.linkText("logout")).click();
        sleep(2);
        
        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        driver.findElement(By.linkText("login")).click();
        sleep(2);
        driver.findElement(By.name("username")).sendKeys("pekka");
        driver.findElement(By.name("password")).sendKeys("vaara");
        sleep(2);
        driver.findElement(By.name("login")).submit();
        sleep(2);
        
        // epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
        driver.findElement(By.name("username")).sendKeys("vaara");
        driver.findElement(By.name("password")).sendKeys("vaara");
        sleep(2);
        driver.findElement(By.name("login")).submit();
        sleep(2);
        
        driver.get("http://localhost:4567");
        sleep(2);
        
        // uuden käyttäjätunnuksen luominen
        driver.findElement(By.linkText("register new user")).click();
        sleep(2);
        driver.findElement(By.name("username")).sendKeys("arto");
        driver.findElement(By.name("password")).sendKeys("salasana");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasana");
        sleep(2);
        driver.findElement(By.name("signup")).submit();
        sleep(2);
        
        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        driver.findElement(By.linkText("continue to application mainpage")).click();
        sleep(2);
        driver.findElement(By.linkText("logout")).click();
        sleep(2);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
