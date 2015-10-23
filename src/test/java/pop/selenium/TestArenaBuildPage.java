package pop.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestArenaBuildPage {

	private WebDriver driver;
	
//	@FindBy(linkText="Utwórz wydanie")
//	@FindBy(xpath="//*[@id='content']/article/nav/ul/li/a")
//	private WebElement addBuildButton;
	@FindAll(
			{
				@FindBy(xpath="//a[@id='action_icon']")
			}
	)
	private List<WebElement> actionIcons;
	
	@FindBy(css="#j_info_box")
	@CacheLookup
	private WebElement infoBox;
	
	@FindBy(how = How.XPATH, using = "//*[@id='content']/article/nav/ul/li/a")
	private WebElement addBuildButton;
	
	public TestArenaBuildPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public String getContentTitle(){
		return driver.findElement(By.xpath("//*[@id='content']/article/h1")).getText();
	}

	public TestArenaBuildPageForm addBuild() {
		addBuildButton.click();
		return new TestArenaBuildPageForm(driver);
	}

	public TestArenaPhase setPhase() {
		actionIcons.get(actionIcons.size() - 1).click();
		driver.findElement(By.linkText("Fazy")).click();
		return new TestArenaPhase(driver);
	}

	public boolean checkAddedFormSuccess() {
		return infoBox.findElement(By.tagName("p")).isDisplayed();
	}

	public TestArenaBuildPage deleteLastBuild() {
		actionIcons.get(actionIcons.size() - 1).click();
		driver.findElement(By.className("j_delete_release")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ui-dialog-buttonset']/button[1]")));
		driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[1]")).click();
		
		return this;
	}
	
	
	
}
