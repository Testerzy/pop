package pop.selenium.example;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestLogin {

	@BeforeClass
	public void setUp(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to("http://google.pl");
	}
	
	@Test
	public void login(){
		MainPage mPage = new MainPage(driver);
		LoginPage lPage = mPage.gotoLoginAccount();
		lPage.loginAccount("konto.email@gmail.com", "haslo123");
		Assert.assertEquals("konto email", driver.findElement(By.id("gbi4t")).getText());
		mPage.logout();
	}
	
//	@Test
	public void login_old(){
		driver.findElement(By.id("gb_70")).click();
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("konto.email@gmail.com");
		driver.findElement(By.id("Passwd")).clear();
		driver.findElement(By.id("Passwd")).sendKeys("haslo123");
		driver.findElement(By.id("signIn")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Google"));
		Assert.assertEquals("konto email", driver.findElement(By.id("gbi4t")).getText());
		driver.findElement(By.id("gbg4")).click();
		driver.findElement(By.id("gb_71")).click();
	}
	
	@AfterClass
	public void tearDown(){
		driver.close();
	}
	
	private WebDriver driver;
	
}
