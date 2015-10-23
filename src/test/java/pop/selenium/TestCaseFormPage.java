package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCaseFormPage {

	public TestCaseFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getContentTitle() {
		return driver.findElement(By.xpath("//*[@id='content']/article/h1")).getText();		
	}

	public TestCaseHome fillForm(String[] dane) {
		tcName.sendKeys(dane[0]);
		tcDesc.sendKeys(dane[1]);
		tcPrecon.sendKeys(dane[2]);
		tcResult.sendKeys(dane[3]);
		tcSaveButton.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("j_info_box")));
		return new TestCaseHome(driver);
	}

	private WebDriver driver;
	@FindBy(id="name")
	private WebElement tcName;
	@FindBy(id="description")
	private WebElement tcDesc;
	@FindBy(id="presuppositions")
	private WebElement tcPrecon;
	@FindBy(id="result")
	private WebElement tcResult;
	@FindBy(css="input#add")
	private WebElement tcSaveButton;
	@FindBy(css="#j_info_box")
	private WebElement infoBox;
	
}
