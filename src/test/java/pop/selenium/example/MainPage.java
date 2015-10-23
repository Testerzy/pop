package pop.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

	private WebDriver driver;
	
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().contains("Google")){
			throw new IllegalStateException("This is not the Google MainPage");
		}
	}
	
	public LoginPage gotoLoginAccount(){
		driver.findElement(By.id("gb_70")).click();
		return new LoginPage(driver);
	}

	public MainPage logout() {
		driver.findElement(By.id("gbg4")).click();
		WebElement element = driver.findElement(By.id("gb_71"));
		element.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.stalenessOf(element));
		return new MainPage(driver);
	}

}

