package pop.selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {

	private WebDriver driver;
	private MainArena arena;
	private TestArenaBuildPage build;
	private TestArenaBuildPageForm buildForm;
	private TestArenaHomePage arenaHomePage;
	private TestArenaPhase phaseHome;
	private TestArenaPhaseForm phaseForm;
	private TestCaseHome testCaseHome; 
	private TestCaseFormPage testCaseForm;
	
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver", "c:\\selenium\\webdriver\\IEDriverServer.exe");
//		System.setProperty("webdriver.chrome.driver", "c:\\selenium\\webdriver\\chromedriver.exe");
//		DesiredCapabilities cp = new DesiredCapabilities();
//		cp.setCapability("nativeEvents", true);
//		driver = new ChromeDriver(cp);
		driver = new InternetExplorerDriver();
//		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		arenaHomePage = new TestArenaHomePage(driver);
		arena = new MainArena(driver);
		
		driver.navigate().to("http://testarena.pl/demo");
//		driver.navigate().to("http://demo.testarena.pl");
		driver.findElement(By.cssSelector(".h_text>p>a")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> win = driver.getWindowHandles();
		for(String h: win){
			if (!h.equals(mainWindow)){
				driver.switchTo().window(h);
			}
		}
	}
	
	@Test(description="Logowanie do aplikacji", priority=1)
	public void testLogin(){
		arena = arenaHomePage.loginUser("administrator@testarena.pl", "sumXQQ72$L");
		Assert.assertTrue(arena.getLoggedUser().toLowerCase().equals("adam adminowski"));
	}
	
//	@Test(description="Dodawanie nowego builda (Wydania)",dependsOnMethods={"testLogin"})
//	@Test(description="Dodawanie nowego builda (Wydania)",priority=2)
	public void testAddBuild(){
		arena.selectProjectByText("Selenium szkolenie");
		build = arena.gotoBuild();
		Assert.assertTrue(build.getContentTitle().equals("Wydania"));
		buildForm = build.addBuild();
		Assert.assertTrue(buildForm.getContentTitle().equals("Tworzenie wydania"));
		int index = (int) (Math.random()*100);
		String dane[] = {"Selenium"+index, "2014-06-13", "2014-06-25", "opis txt"};
		build = buildForm.fillForm(dane);
		Assert.assertTrue(build.checkAddedFormSuccess());
	}
	
//	@Test(description="Dodawanie nowej fazy dla builda", dependsOnMethods={"testLogin"})
//	@Test(description="Dodawanie nowej fazy dla builda", priority=3)
	public void testAddPhase() throws InterruptedException{
		phaseHome = arena.gotoBuild().setPhase();
		phaseForm = phaseHome.addPhase();
		int index = (int) (Math.random()*100);
		String dane[] = {"Selenium Faza "+index, "2014-06-13", "2014-06-25", "opis txt"};
		phaseForm.fillForm(dane);
		Assert.assertTrue(phaseHome.checkAddedFormSuccess());
	}
	
//	@Test(description="Dodawanie przypadku testowego", dependsOnMethods={"testLogin"})
//	@Test(description="Dodawanie przypadku testowego", priority=4)
	public void testAddTestCase(){
		testCaseHome = arena.gotoTestCase();
		Assert.assertTrue(testCaseHome.getContentTitle().contains("Lista przypadk"));
		testCaseForm = testCaseHome.addTestCase();
		Assert.assertTrue(testCaseForm.getContentTitle().equals("Dodaj przypadek testowy"));
		int index = (int) (Math.random()*100);
		String dane[] = {"Selenium TestCase nr."+index, "opis "+index, "zalozenia "+index, "rezultat "+index};
		testCaseForm.fillForm(dane);
	}
	
//	@Test(description="Dodawanie zadania dla fazy w wydaniu", dependsOnMethods={"testLogin"})
//	@Test(description="Dodawanie zadania dla fazy w wydaniu", priority=5)
	public void testAddTask(){
		TaskPage taskPage = arena.gotoBuild().setPhase().setTask();
		Assert.assertTrue(taskPage.getContentTitle(), taskPage.getContentTitle().contains("Lista task"));
		TaskFormPage taskFormPage = taskPage.addNewTask();
		Assert.assertTrue(taskFormPage.getContentTitle().contains("rz zadania"));
		int index = (int) (Math.random()*100);
		String dane[] = {"Selenium Zadanie nr."+index, "opis "+index, "build 1.0", "faza1"};
		taskFormPage.fillForm(dane);
	}
	
	@Test(description="Wylogowanie", dependsOnMethods = {"testLogin"})
	public void testLogout(){
		arena.logoutUser();
		Assert.assertTrue(driver.findElement(By.id("login")).getAttribute("value").equals("Zaloguj"));
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown(){
		driver.quit();
	}
	
}
