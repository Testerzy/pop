package pop.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public LoginPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().contains("Konta Google")){
			throw new IllegalStateException("This is not the Google LoginPage");
		}
	}
	
	public MainPage loginAccount(String email, String passwd){
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Passwd")).clear();
		driver.findElement(By.id("Passwd")).sendKeys(passwd);
		driver.findElement(By.id("signIn")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Google"));
		return new MainPage(driver);
	}
	
	private WebDriver driver;
	
}

