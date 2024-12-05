package steps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class CartSteps {

	WebDriver driver = null;
	@SuppressWarnings("deprecation")
	@Given("browser is opening")
	public void browser_is_open() {

		System.out.print("Inside Step - browser is opening");
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is : "+projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@And("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		driver.navigate().to("http://127.0.0.1:8000/user/login");
	}

	@When("^user enter (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws InterruptedException  {
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn[type='submit']")).click();
		Thread.sleep(2000);
	}


	@And("the user adds random products to the cart")
    public void the_user_adds_random_products_to_the_cart() throws InterruptedException {
        Random rand = new Random();
        int minProductsToAdd = 1;
        int maxProductsToAdd = 4; // Số lượng sản phẩm tối đa cần thêm vào giỏ hàng
        // Đảm bảo maxProductsToAdd luôn lớn hơn hoặc bằng minProductsToAdd
        if (maxProductsToAdd < minProductsToAdd) {
            int temp = minProductsToAdd;
            minProductsToAdd = maxProductsToAdd;
            maxProductsToAdd = temp;
        }
        // Sử dụng bound là maxProductsToAdd - minProductsToAdd + 1 để đảm bảo bound là một số dương
        int numberOfProductsToAdd = rand.nextInt(maxProductsToAdd - minProductsToAdd + 1) + minProductsToAdd;
        Actions actions = new Actions(driver);
        for (int i = 0; i < numberOfProductsToAdd; i++) {
            try {
                // Tìm tất cả các sản phẩm trên trang web
                List<WebElement> productList = driver.findElements(By.cssSelector(".isotope-item"));
               // Kiểm tra nếu danh sách sản phẩm không rỗng
                if (!productList.isEmpty()) {
                    int randomIndex = rand.nextInt(productList.size());
                    WebElement product = productList.get(randomIndex);
                    // Di chuyển chuột đến sản phẩm
                    actions.moveToElement(product).perform();
                    // Chờ một chút sau mỗi lần di chuyển chuột để tránh lỗi StaleElementReferenceException
                    Thread.sleep(1000);
                    // Click vào nút "Add to cart" cho sản phẩm được chọn ngẫu nhiên
                    product.findElement(By.xpath(".//div[@class='button-head']//div[@class='product-action-2']//a[text()='Add to cart']")).click();
                    // Chờ một chút sau mỗi lần thêm sản phẩm để tránh lỗi StaleElementReferenceException
                    Thread.sleep(1000);
                } else {
                    // Nếu danh sách sản phẩm rỗng, dừng vòng lặp
                    break;
                }
            } catch (NoSuchWindowException e) {
                // Xử lý nếu cửa sổ trình duyệt đã đóng
                System.out.println("Browser window is closed. Exiting...");
                break;
            }
        }
        Thread.sleep(3000);
    }

	@And("the user proceeds to checkout")
	public void the_user_proceeds_to_checkout() throws InterruptedException {
		driver.navigate().to("http://127.0.0.1:8000/checkout");
		Thread.sleep(2000);
	}
	@And("user enters info")
	public void user_enters_information() throws InterruptedException {
		WebElement firstNameInput = driver.findElement(By.name("first_name"));
		firstNameInput.sendKeys("Thanh");
		
		WebElement lastNameInput = driver.findElement(By.name("last_name"));
		lastNameInput.sendKeys("Test");
		Thread.sleep(2000);
		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.sendKeys("test@gmail.com");
		WebElement phoneInput = driver.findElement(By.name("phone"));
		phoneInput.sendKeys("01234");
		Thread.sleep(2000);
		WebElement addressInput = driver.findElement(By.name("address1"));
		addressInput.sendKeys("123 Vietnam");
		
		Thread.sleep(2000);

//		WebElement niceSelect = driver.findElement(By.xpath("(//div[@class='nice-select'])[3]"));

//        // Tạo đối tượng Actions để thực hiện hành động chuột
//        Actions actions = new Actions(driver);
//
//        // Di chuyển chuột tới phần tử và click vào nó
//        actions.moveToElement(niceSelect).click().perform();
//     // Tìm và nhấp vào phần tử li có giá trị dữ liệu là "2"
//        WebElement listItem = driver.findElement(By.xpath("//li[@data-value='2']"));
//        listItem.click();
//
//
//	    Thread.sleep(2000);
//	    
//	 // Tìm phần tử radio button dựa trên thuộc tính name và value
//	    WebElement radioButton = driver.findElement(By.cssSelector("input[name='payment_method'][value='cod']"));
//
//	    // Nhấp vào radio button
//	    radioButton.click();
	    Thread.sleep(2000);
	}
	
	@And("user choose with (.*) and (.*)$")
	public void user_choose(String skipListItemClick, String skipRadioButtonClick) throws InterruptedException {
	    boolean shouldSkipListItemClick = Boolean.parseBoolean(skipListItemClick);
	    boolean shouldSkipRadioButtonClick = Boolean.parseBoolean(skipRadioButtonClick);

	    if (!shouldSkipListItemClick) {
	        WebElement niceSelect = driver.findElement(By.xpath("(//div[@class='nice-select'])[3]"));
	        niceSelect.click(); // Mở danh sách dropdown
	        Thread.sleep(1000); // Chờ cho danh sách dropdown xuất hiện
	        WebElement listItem = driver.findElement(By.xpath("//li[@data-value='2']"));
	        listItem.click(); // Chọn một giá trị từ danh sách
	        Thread.sleep(2000);
	    }

	    if (!shouldSkipRadioButtonClick) {
	        WebElement radioButton = driver.findElement(By.cssSelector("input[name='payment_method'][value='cod']"));
	        radioButton.click();
	        Thread.sleep(2000);
	    }
	}



	@Then("the user completes the checkout process")
	public void the_user_completes_the_checkout_process() throws InterruptedException {
		
		// Tìm phần tử button dựa trên lớp CSS của nó
		WebElement proceedButton = driver.findElement(By.cssSelector("button.btn"));

		// Nhấn vào nút "proceed to checkout"
		proceedButton.click();
        Thread.sleep(6000);
		//driver.close();
		driver.quit();	}
}
