package pop.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TaskFormPage {

	private WebDriver driver;
	private WebElement name;
	private WebElement description;
	private WebElement releaseId;
	private WebElement phaseId;
	@FindAll(
		{
			@FindBy(xpath="//ul[@id='testCaseSource']/li")
		}
	)
	private List<WebElement> testCases;
	private WebElement testCaseDestiny;
	private WebElement add;
	
	public TaskFormPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	public String getContentTitle() {
		return driver.findElement(By.xpath("//*[@id='content']/article/h1")).getText();
	}

	public TaskPage fillForm(String[] dane) {
		name.sendKeys(dane[0]);
		description.sendKeys(dane[1]);
		Select buildSelect = new Select(releaseId);
		List<WebElement> options = buildSelect.getOptions();
		buildSelect.selectByIndex(options.size()-1);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(phaseId));
		Select phaseSelect = new Select(phaseId);
		phaseSelect.selectByIndex(phaseSelect.getOptions().size()-1);
		Reporter.log(String.valueOf(testCases.size()));
		WebElement dragSrc = testCases.get(0);
		Actions action = new Actions(driver);
//		Reporter.log(testCaseDestiny.getTagName());
		action.dragAndDrop(dragSrc, testCaseDestiny).build().perform();
//		action.perform();
//		action.clickAndHold(dragSrc).moveToElement(testCaseDestiny).release(testCaseDestiny).build().perform();
		add.click();
		return new TaskPage(driver);
	}
	
	

}
