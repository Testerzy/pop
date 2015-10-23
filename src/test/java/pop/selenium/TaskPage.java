package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TaskPage {

	private WebDriver driver;
	
	public TaskPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	public TaskFormPage addNewTask(){
		driver.findElement(By.className("button_link")).click();
		return new TaskFormPage(driver);
	}

	public String getContentTitle() {
		return driver.findElement(By.xpath("//*[@id='content']/article/h1")).getText();
	}

}
