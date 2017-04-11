package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }
    
    @Given("^new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        driver.findElement(By.linkText("register new user")).click();          
    }
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void successful_user_creation(String username, String password) throws Throwable {
        createUser(username, password);
        pageHasContent("Welcome to Ohtu Application!");
        pageHasContent("info for newbie");
        pageHasContent("continue to application mainpage");
    }
    
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is unsuccessfully created$")
    public void unsuccessful_user_creation(String username, String password) throws Throwable {
        createUser(username, password);
        pageHasContent("Create username and give password");
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexisting username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexisting_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^valid username \"([^\"]*)\" and valid matching passwords \"([^\"]*)\" are given$")
    public void valid_username_and_passwords_are_given(String username, String password) throws Throwable {
        signupWith(username, password, password);
    }
    
    @When("^too short username \"([^\"]*)\" and valid matching passwords \"([^\"]*)\" are given$")
    public void too_short_username_and_passwords_are_given(String username, String password) throws Throwable {
        signupWith(username, password, password);
    }
    
    @When("^valid username \"([^\"]*)\" and too short matching passwords \"([^\"]*)\" are given$")
    public void username_and_too_short_passwords_are_given(String username, String password) throws Throwable {
        signupWith(username, password, password);
    }
    
    @When("^valid username \"([^\"]*)\" and letter-only matching passwords \"([^\"]*)\" are given$")
    public void username_and_letter_only_passwords_are_given(String username, String password) throws Throwable {
        signupWith(username, password, password);
    }
    
    @When("^taken username \"([^\"]*)\" and valid matching passwords \"([^\"]*)\" are given$")
    public void taken_username_and_valid_passwords_are_given(String username, String password) throws Throwable {
        signupWith(username, password, password);
    }
    
    @When("^valid username \"([^\"]*)\" and valid nonmatching passwords \"([^\"]*)\" and \"([^\"]*)\" are given$")
    public void username_and_valid_nonmatching_passwords_are_given(String username, String password, String passwordConfirmation) throws Throwable {
        signupWith(username, password, passwordConfirmation);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        pageHasContent("Create username and give password");        
        pageHasContent(error);
    }
    
    @Then("^user is created$")
    public void user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
        pageHasContent("info for newbie");
        pageHasContent("continue to application mainpage");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void signupWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(passwordConfirmation);
        driver.findElement(By.name("signup")).submit();  
    } 
    
    private void createUser(String username, String password) {
        driver.get(baseUrl);
        driver.findElement(By.linkText("register new user")).click();  
        signupWith(username, password, password);
    }
}
