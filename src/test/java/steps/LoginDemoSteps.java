package steps;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

public class LoginDemoSteps {

	WebDriver driver = null;
	@SuppressWarnings("deprecation")
	@Given("browser is open")
	public void browser_is_open() {	
		System.out.print("Inside Step - browser is open");
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is : "+projectPath);	
		System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}

	@And("user is on login page")
	public void user_is_on_login_page() {
				driver.navigate().to("http://127.0.0.1:8000/user/login");
	}

	@When("^user enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws InterruptedException  {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// Thực thi mã JavaScript để kiểm tra xem phần tử cụ thể đã xuất hiện trong DOM hay chưa
		Boolean isElementVisible = (Boolean) jsExecutor.executeScript("return document.getElementById('myElement') !== null");
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(2000);
	}
	
	@And("user clicks on login")
	public void user_clicks_on_login() {
	    // Nhấn nút đăng nhập
		driver.findElement(By.cssSelector("button.btn[type='submit']")).click();	
	}

	@And("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() throws InterruptedException {
	    
	    Thread.sleep(2000);
	}
	
	@Then("user clicks log out")
	public void user_clicks_log_out() throws InterruptedException {
		driver.findElement(By.linkText("Logout")).click();
	    Thread.sleep(3000);
	    driver.quit();;
	}
}
