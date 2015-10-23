package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestArenaHomePage {

	private WebDriver driver;

	public TestArenaHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String string) {
		driver.navigate().to(string);
	}

	public MainArena loginUser(String email, String passwd) {
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys(passwd);
		driver.findElement(By.cssSelector("#login")).click();
		return new MainArena(driver);
	}

	
}
