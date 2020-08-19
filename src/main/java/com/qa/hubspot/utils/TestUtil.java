package com.qa.hubspot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.TestBase;

public class TestUtil extends TestBase{

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	public static String LOGIN_PAGE_TITLE = "HubSpot Login";

	public static String HOME_PAGE_TITLE = "Reports dashboard";
	public static String HOME_PAGE_HEADING = "Sales Dashboard";
	
	public static String CONTACTS_PAGE_TITLE = "Contacts";
	public static String CONTACTS_PAGE_HEADING = "Contacts";
	
	public static String TEST_DATA_SHEET_PATH = "D:\\Selenium_workspace\\PageObjectModelProject\\"
										+ "src\\main\\java\\com\\qa\\hubspot\\testdata\\HubSpotTestData.xlsx";
	
	static Workbook book;
	static Sheet sheet;

	public static WebElement waitTillElementIsVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitTillElementIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
	}
	
}
