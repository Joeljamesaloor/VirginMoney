package virtusa.virginmoney;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	
	public static AndroidDriver<AndroidElement>  driver;
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\virtusa\\virginmoney\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
	File appDir = new File("src");
	File app = new File(appDir,(String) prop.get(appName));
	
	DesiredCapabilities cap= new DesiredCapabilities();
	String device =  (String) prop.get("device");
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
	cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
	AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	return driver;
	}
	
	public AndroidDriver<AndroidElement> runCapabilities(String appName, Boolean cloud) throws IOException
	{
		if(cloud)
		{
			return cloudCapabilities(appName) ;
		}
		{
			return capabilities(appName);
		}
	}
	
public static AndroidDriver<AndroidElement> cloudCapabilities(String appName) throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\virtusa\\virginmoney\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
	File appDir = new File("src");
	File app = new File(appDir,(String) prop.get(appName));
	
	DesiredCapabilities cap= new DesiredCapabilities();
	//String device =  (String) prop.get("device");
	String bsusername =  prop.getProperty("BrowserStackUsername");
	String bsaccessKey =  prop.getProperty("BrowserStackAccesskey");
	String bsDevice =  prop.getProperty("BrowserStackDevice");
	String bsOSVersion =  prop.getProperty("BrowserStackOSVersion");
	cap.setCapability("browserstack.user", bsusername);
	cap.setCapability("browserstack.key", bsaccessKey);
	cap.setCapability("project", "virginmoney-BrowserStack");
	cap.setCapability("build", "Build4");
	cap.setCapability("name", "Case");
	cap.setCapability("browserstack.debug", "true");
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
	if(appName.equalsIgnoreCase("GeneralStoreApp"))
	{
		String bsAppID =  prop.getProperty("BrowserStackAppID");
		cap.setCapability("app", bsAppID);
	}
	cap.setCapability("device", bsDevice);
	cap.setCapability("os_version", bsOSVersion);
	
	AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), cap);
	return driver;
	}


}
