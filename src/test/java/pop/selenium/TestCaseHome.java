package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCaseHome {

	private WebDriver driver;
	
	public TestCaseHome(WebDriver driver) {
		this.driver = driver;
	}

	public String getContentTitle() {
		return driver.findElement(By.xpath("//*[@id='content']/article/h1")).getText();
	}

	public TestCaseFormPage addTestCase() {
		driver.findElement(By.className("button_link")).click();
		return new TestCaseFormPage(driver);
	}
	
}
