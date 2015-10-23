package pop.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestArenaPhase {

	private WebDriver driver;
	
	@FindBy(css="#j_info_box")
	private WebElement infoBox;
	@FindAll(
			{
				@FindBy(xpath="//a[@id='action_icon']")
			}
	)
	private List<WebElement> actionIcons;
	
	public TestArenaPhase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public TestArenaPhaseForm addPhase() {
		driver.findElement(By.className("button_link")).click();
		return new TestArenaPhaseForm(driver);
	}
	
	public boolean checkAddedFormSuccess() {
		return infoBox.findElement(By.tagName("p")).isDisplayed();
	}
	
	public TaskPage setTask(){
		actionIcons.get(actionIcons.size() - 1).click();
		driver.findElement(By.linkText("Zadania")).click();
		return new TaskPage(driver);
	}
}
