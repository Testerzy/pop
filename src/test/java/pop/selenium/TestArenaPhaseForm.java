package pop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestArenaPhaseForm {

	private WebDriver driver;
	@FindBy(id="name")
	private WebElement phaseName;
	@FindBy(id="startDate")
	private WebElement phaseStartDate;
	@FindBy(id="endDate")
	private WebElement phaseEndDate;
	@FindBy(id="description")
	private WebElement phaseDesc;
	@FindBy(css="input#save")
	private WebElement phaseSaveButton;
	@FindBy(how = How.NAME, using = "q")
	private WebElement phaseSaveButton2;
	
	public TestArenaPhaseForm(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	public TestArenaPhase fillForm(String[] dane) {
		phaseName.sendKeys(dane[0]);
		phaseStartDate.sendKeys(dane[1]);
		phaseEndDate.sendKeys(dane[2]);
		phaseDesc.sendKeys(dane[3]);
		phaseSaveButton.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("j_info_box")));
		return new TestArenaPhase(driver);
	}

}
