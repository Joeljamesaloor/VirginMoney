package squad;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;
import pageobj.HomePage;
import virtusa.virginmoney.Base;

public class Case extends Base {

	@Test

	public void TestNGmethod() throws IOException {

		AndroidDriver<AndroidElement> driver = runCapabilities("GeneralStoreApp", true);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HomePage homepage = new HomePage(driver);
		homepage.nameField().sendKeys("Hello");
		driver.hideKeyboard();

		HomePage f = new HomePage(driver);
		f.getElem().click();

		driver.findElement(By.id("android:id/text1")).click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

	}

}