package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestArenaBuildPageForm {

	private WebDriver driver;
	
	@FindBy(xpath="//*[@id='content']/article/h1")
	private WebElement contentTitle;
	
	@FindBy(id="name")
	private WebElement nazwa;
	
	@FindBy(id="startDate")
	private WebElement startDate;
	
	@FindBy(id="endDate")
	private WebElement endDate;
	
	@FindBy(id="description")
	private WebElement opis;
	
//	@FindBy(HOW.id="save")
	@FindBy(css="#save > #save")
	private WebElement saveButton;
	
	public TestArenaBuildPageForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getContentTitle(){
		return contentTitle.getText();
	}

	public TestArenaBuildPage fillForm(String[] dane) {
		nazwa.sendKeys(dane[0]);
		startDate.sendKeys(dane[1]);
		endDate.sendKeys(dane[2]);
		opis.sendKeys(dane[3]);
		saveButton.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("j_info_box")));
		return new TestArenaBuildPage(driver);
	}
	
}
