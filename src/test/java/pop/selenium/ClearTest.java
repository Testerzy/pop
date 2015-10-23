package pop.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClearTest {

	@BeforeClass
	public void setUp(){
//		System.setProperty("webdriver.ie.driver", "c:\\selenium\\webdriver\\IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "c:\\selenium\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		arenaHomePage = new TestArenaHomePage(driver);
		arena = new MainArena(driver);
		
		driver.navigate().to("http://demo.testarena.pl/login");
	}
	
	@Test(description="Kasowanie builda")
	public void testClearBuild(){
		arena = arenaHomePage.loginUser("administrator@testarena.pl", "sumXQQ72$L");
		build = arena.gotoBuild();
		build.deleteLastBuild();
	}
	
//	@Test(description="Koasowanie Przypadku testowego", dependsOnMethods={"testClearBuild"})
	public void testClearTestCase(){
		tcHomePage = arena.gotoTestCase();
		List<WebElement> actions = driver.findElements(By.xpath("//a[@id='action_icon']"));
		actions.get(actions.size() - 1).click();
		driver.findElement(By.className("j_delete_role")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ui-dialog-buttonset']/button[1]")));
		driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[1]")).click();
	}
	
	@Test(description="Wylogowanie", dependsOnMethods = {"testClearBuild"})
	public void testLogout(){
		arena.logoutUser();
		Assert.assertTrue(driver.findElement(By.id("login")).getAttribute("value").equals("Zaloguj"));
	}
	
	@AfterTest
	public void tearDown(){
		driver.close();
//		driver.quit();
	}
	
	private WebDriver driver;
	private MainArena arena;
	private TestArenaHomePage arenaHomePage;
	private TestArenaBuildPage build;
	private TestCaseHome tcHomePage;
	
}
