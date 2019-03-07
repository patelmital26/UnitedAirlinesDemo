/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import com.mycompany.unitedairlinesdemo.MemberLogin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Mital Patel
 */
public class MemberLoginTest {
    MemberLogin mlt;
    private WebDriver driver;
    private String baseUrl;
    private String filePath;
    private String fileName;
    public MemberLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mlt = new MemberLogin();
        baseUrl = "https://www.united.com/ual/en/us/";
        filePath = "C:\\Users\\Mital\\Desktop\\";
        fileName = "UnitedTestData.xlsx";
        System.setProperty("webdriver.chrome.driver", 
                 "C:\\Users\\Mital\\Desktop\\chromedriver.exe");
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void MemberLoginTest() throws InterruptedException, FileNotFoundException, IOException {
        String sheetName0 = "LoginData";
        String sheetName1 = "SecQuestionAnswers";  // Read from the sheet 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
       
        // Click on Expand/(+) Icon
        driver.findElement(By.xpath("//*[@id=\"loginButton\"]")).click();
        Thread.sleep(2000);
        
        File f = new File(filePath+"\\"+fileName);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(f);
        //Create object of XSSFWorkbook class
        Workbook wb = new XSSFWorkbook(inputStream);
        //Read sheet inside the workbook by its name
        Sheet s = wb.getSheet(sheetName0);
        
        Row r0 = s.getRow(0);
        mlt.setMemberid(r0.getCell(0).getStringCellValue());
        mlt.setPassword(r0.getCell(1).getStringCellValue());
       
        driver.findElement(By.cssSelector("#loginFormModel\\2e login")).sendKeys(mlt.getMemberid());
        driver.findElement(By.cssSelector("#loginFormModel\\2e password")).sendKeys(mlt.getPassword());
        driver.findElement(By.cssSelector("#loginFormModel > button")).click();
        
        Thread.sleep(3000);
       
       if(driver.getTitle().equals("We don't recognize this device | United Airlines")){
           // Save First question text using xpath
           mlt.setSecQue1(driver.findElement(By.xpath("//*[@id=\"authQuestionsForm\"]/div[1]/fieldset/legend")).getText());
           // Save Second question
           mlt.setSecQue2(driver.findElement(By.xpath("//*[@id=\"authQuestionsForm\"]/div[2]/fieldset/legend")).getText());
           
           Sheet s1 = wb.getSheet(sheetName1);
           for(int i = 0; i < 4; i++){
              Row r1 = s1.getRow(i);
              if(r1.getCell(0).getStringCellValue().equals(mlt.getSecQue1()))
              {
                  mlt.setSecAns1(r1.getCell(1).getStringCellValue());
              }
              if(r1.getCell(0).getStringCellValue().equals(mlt.getSecQue2()))
              {
                   mlt.setSecAns2(r1.getCell(1).getStringCellValue());
              }
           }
           
           Select drpQueAns1 = new Select(driver.findElement(By.id("QuestionsList_0__AnswerKey")));
           drpQueAns1.selectByVisibleText(mlt.getSecAns1());
           
           Select drpQueAns2 = new Select(driver.findElement(By.id("QuestionsList_1__AnswerKey")));
           drpQueAns2.selectByVisibleText(mlt.getSecAns2());
           
           driver.findElement(By.id("IsRememberDevice_True")).click();          
           driver.findElement(By.id("btnNext")).click();
       } 
       // Login Successfully 
       //String actualResult = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/h1")).getText();      
       //Assert.assertEquals("Welcome to united.com", actualResult);
    }
}
