package steps;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.*;

public class RegisterSteps {

    WebDriver driver = null;
    @Given("google is open")
    public void browser_is_open() {
        // Khởi tạo trình duyệt và mở trang web
        System.out.print("Inside Step - google is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : " + projectPath);
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("user is on the registration page")
    public void user_navigates_to_registration_page() {
        // Chuyển đến trang đăng ký
        driver.navigate().to("http://127.0.0.1:8000/admin/users/create");      
    }
    
    @And("user login admin account")
    public void user_login_admin_account() throws InterruptedException {
        // Kiểm tra xem phần tử email có hiển thị hay không
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Boolean isElementVisible = (Boolean) jsExecutor.executeScript("return document.getElementById('exampleInputEmail') !== null");
        // Nếu phần tử email đã xuất hiện, nhập giá trị vào trường email
        if (isElementVisible) {
            WebElement emailInput = driver.findElement(By.name("email"));
            emailInput.sendKeys("admin@gmail.com");
        } else {
            // Nếu không, bạn có thể thực hiện các hành động khác hoặc chờ cho đến khi phần tử xuất hiện
            // Ví dụ: WebDriverWait wait = new WebDriverWait(driver, 10);
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleInputEmail")));
        }
        // Tìm trường input password và nhập dữ liệu
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword"));
        passwordInput.sendKeys("1111");
        // Sau khi nhập username và password, click vào nút đăng nhập
        WebElement loginButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        loginButton.click();
        Thread.sleep(2000);
    }

    @When("^user enter (.*) , (.*) , (.*)$")
    public void user_enters__name_username_and_password(String name, String username, String password) throws InterruptedException {
       
    	// Nhập thông tin đăng ký
    	driver.findElement(By.id("inputTitle")).sendKeys(name);
    	driver.findElement(By.id("inputEmail")).sendKeys(username);
        driver.findElement(By.id("inputPassword")).sendKeys(password); 
        // Tạo một đối tượng Select từ WebElement của dropdown
        WebElement dropdown = driver.findElement(By.name("role"));
        Select select = new Select(dropdown);
        // Chọn tùy chọn "user" từ dropdown
        select.selectByValue("user");
        Thread.sleep(2000);
    }

    @And("clicks on the register button")
    public void user_clicks_on_register() throws InterruptedException {
        // Nhấp vào nút đăng ký
    	driver.findElement(By.cssSelector("button.btn.btn-success[type='submit']")).click();
    	Thread.sleep(2000);
    }

    @Then("user succesfully register")
    public void user_successfully_register() throws InterruptedException {
//    	driver.findElement(By.cssSelector("a.nav-link.dropdown-toggle")).click();
//    	 Thread.sleep(2000);
//    	driver.findElement(By.cssSelector("a[href='http://127.0.0.1:8000/logout']")).click();

        // Thực hiện xác nhận cho việc đăng ký thành công và chuyển hướng
        // Ví dụ, bạn có thể kiểm tra rằng một thông báo thành công hoặc một phần tử cụ thể được hiển thị trên trang
        Thread.sleep(2000);
	//	driver.close();
		driver.quit();
    }
}
