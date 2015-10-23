package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainArena{

	private WebDriver driver;
	
	public MainArena(WebDriver driver) {
		this.driver = driver;
	}

	public String getLoggedUser(){
		return driver.findElement(By.cssSelector("#header_nav_right>nav>ul>li>a")).getText();
	}

	public void logoutUser() {
		driver.findElement(By.cssSelector(".open.arrow_down_icon")).click();
		driver.findElement(By.cssSelector("#logout>a")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementValue(By.id("login"), "Zaloguj"));
	}

	public TestArenaBuildPage gotoBuild() {
//		driver.findElement(By.linkText("Wydania")).click();
//		driver.findElement(By.cssSelector(".menu>.item3>a")).click();
		driver.findElement(By.cssSelector(".menu>.item3>a")).click();
		return new TestArenaBuildPage(driver);
	}

	public MainArena selectProjectByText(String string) {
		Select select = new Select(driver.findElement(By.id("activeProject")));
		select.selectByVisibleText(string);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeSelected(By.id("activeProject")));
		return this;
	}

	public TestCaseHome gotoTestCase() {
		driver.findElement(By.linkText("Przypadki testowe")).click();
		return new TestCaseHome(driver);
	}
	
	public MainArena mainPage(){
		driver.navigate().to(driver.getCurrentUrl());
		return this;
	}
	
}
