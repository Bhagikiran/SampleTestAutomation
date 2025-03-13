package automationFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationFramework.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver ;
	public LandingPage landingPage;
	public WebDriver initilizeDriver() throws Exception {

		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src//main//java//automationFramework//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox")) {	
		}
		else if(browserName.equalsIgnoreCase("edge")) {
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws Exception {
		driver=initilizeDriver();
		 landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void  tearDown() throws Exception {
		driver.close();
	}
	public List<HashMap<String, String>> getJsonData(String filePath) throws Exception {

		//read json to String
		String jsonContent=	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to Hash Map - Jackson DataBind dependency
		ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		 return data;
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
}
