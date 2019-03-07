/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import com.mycompany.unitedairlinesdemo.MemberRegistration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
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
public class MemberRegistrationTest {
    MemberRegistration mr;
    private WebDriver driver;
    private String baseUrl;
    private String filePath;
    private String fileName;
    
    public MemberRegistrationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mr = new MemberRegistration();
        baseUrl = "https://www.united.com/ual/en/us/";
        filePath = "C:\\Users\\Mital\\Desktop\\";
        fileName = "UnitedTestData.xlsx";
        System.setProperty("webdriver.chrome.driver", 
                 "C:\\Users\\Sujal\\Mital\\chromedriver.exe");
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void RegistrationTest() throws InterruptedException, FileNotFoundException, IOException
    {
        String sheetName0 = "RegistrationData";
        String SheetName1 = "SecQuestionAnswers";
        String SheetName2 = "LoginData";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
       
        // Click on Expand/(+) Icon
        driver.findElement(By.xpath("//*[@id=\"loginButton\"]")).click();
        Thread.sleep(2000);
        // Click on Join Now
        driver.findElement(By.cssSelector("#loginFormModel > div.app-components-LoginForm-loginForm__mileagePlusEnroll--1lhvM > a > span")).click();
        
        //Create an object of File class to open xlsx file
        File f = new File(filePath+"\\"+fileName);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(f);
        //Create object of XSSFWorkbook class
        Workbook wb = new XSSFWorkbook(inputStream);
        //Read sheet inside the workbook by its name
        Sheet s = wb.getSheet(sheetName0);
        
        Row r0 = s.getRow(0);
        mr.setTitle(r0.getCell(0).getStringCellValue());
        mr.setFirstName(r0.getCell(1).getStringCellValue());
        mr.setLastName(r0.getCell(2).getStringCellValue());
        mr.setGender(r0.getCell(3).getStringCellValue());
        mr.setbMonth(r0.getCell(4).getStringCellValue());
        mr.setbDate(r0.getCell(5).getStringCellValue());
        mr.setbYear(r0.getCell(6).getStringCellValue());
        mr.setAddress(r0.getCell(7).getStringCellValue());
        mr.setCity(r0.getCell(8).getStringCellValue());
        mr.setState(r0.getCell(9).getStringCellValue());
        mr.setZipcode(r0.getCell(10).getStringCellValue());
        mr.setPhoneno(r0.getCell(11).getStringCellValue());
        mr.setEmail(r0.getCell(12).getStringCellValue());
        mr.setPassword(r0.getCell(13).getStringCellValue());
        
        Select drpTitle = new Select(driver.findElement(By.id("Title")));
        drpTitle.selectByVisibleText(mr.getTitle());
        
        driver.findElement(By.id("FirstName")).sendKeys(mr.getFirstName());
        driver.findElement(By.id("LastName")).sendKeys(mr.getLastName());
       
        Select drpBirthMonth = new Select(driver.findElement(By.id("DOB_BirthMonth")));
        drpBirthMonth.selectByVisibleText(mr.getbMonth().replace("\"", ""));
        
        driver.findElement(By.id("DOB_BirthDay")).sendKeys(mr.getbDate().replace("\"", ""));
        driver.findElement(By.id("DOB_BirthYear")).sendKeys(mr.getbYear().replace("\"", ""));
        
        if(mr.getGender().equals("Male") || mr.getGender().equals("male") || mr.getGender().equals("MALE")) {
            driver.findElement(By.id("Gender_M")).click();
        }
        else {
            driver.findElement(By.id("Gender_F")).click();
        }
        
        driver.findElement(By.id("AddressLine1")).sendKeys(mr.getAddress());
        driver.findElement(By.id("City")).sendKeys(mr.getCity());
        driver.findElement(By.id("StateCodeRequired")).sendKeys(mr.getState());
        driver.findElement(By.id("PostalCodeRequired")).sendKeys(mr.getZipcode().replace("\"", ""));
        driver.findElement(By.id("PhoneNumber")).sendKeys(mr.getPhoneno());
        driver.findElement(By.id("EmailAddress")).sendKeys(mr.getEmail());
        driver.findElement(By.id("EmailAddressConfirm")).sendKeys(mr.getEmail());
        driver.findElement(By.id("NewPassword")).sendKeys(mr.getPassword());
        driver.findElement(By.id("NewPasswordConfirm")).sendKeys(mr.getPassword());
       
        Thread.sleep(1000);
        
        Select drpSecQue1 = new Select(driver.findElement(By.name("Questions[0].QuestionKey")));
        drpSecQue1.selectByIndex(1); 
        mr.setSecQuestion1(drpSecQue1.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        Select drpSecAns1 = new Select(driver.findElement(By.name("Questions[0].AnswerKey")));
        drpSecAns1.selectByIndex(1);
        mr.setSecAnswer1(drpSecAns1.getFirstSelectedOption().getText());
       
        Select drpSecQue2 = new Select(driver.findElement(By.name("Questions[1].QuestionKey")));
        drpSecQue2.selectByIndex(1); 
        mr.setSecQuestion2(drpSecQue2.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        Select drpSecAns2 = new Select(driver.findElement(By.name("Questions[1].AnswerKey")));
        drpSecAns2.selectByIndex(1);
        mr.setSecAnswer2(drpSecAns2.getFirstSelectedOption().getText());
        
        Select drpSecQue3 = new Select(driver.findElement(By.name("Questions[2].QuestionKey")));
        drpSecQue3.selectByIndex(1); 
        mr.setSecQuestion3(drpSecQue3.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        Select drpSecAns3 = new Select(driver.findElement(By.name("Questions[2].AnswerKey")));
        drpSecAns3.selectByIndex(1);
        mr.setSecAnswer3(drpSecAns3.getFirstSelectedOption().getText());
        
        Select drpSecQue4 = new Select(driver.findElement(By.name("Questions[3].QuestionKey")));
        drpSecQue4.selectByIndex(1); 
        mr.setSecQuestion4(drpSecQue4.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        Select drpSecAns4 = new Select(driver.findElement(By.name("Questions[3].AnswerKey")));
        drpSecAns4.selectByIndex(1);
        mr.setSecAnswer4(drpSecAns4.getFirstSelectedOption().getText());
        
        Select drpSecQue5 = new Select(driver.findElement(By.name("Questions[4].QuestionKey")));
        drpSecQue5.selectByIndex(1); 
        mr.setSecQuestion5(drpSecQue5.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        Select drpSecAns5 = new Select(driver.findElement(By.name("Questions[4].AnswerKey")));
        drpSecAns5.selectByIndex(1);
        mr.setSecAnswer5(drpSecAns5.getFirstSelectedOption().getText());        
        System.out.println(mr.toString());
        
        Sheet s1 = wb.getSheet(SheetName1);
        Row r1 = s1.createRow(0);
        Cell CQue1 = r1.createCell(0);
        CQue1.setCellValue(mr.getSecQuestion1());
        Cell CAns1 = r1.createCell(1);
        CAns1.setCellValue(mr.getSecAnswer1());
        
        Row r2 = s1.createRow(1);
        Cell CQue2 = r2.createCell(0);
        CQue2.setCellValue(mr.getSecQuestion2());
        Cell cAns2 = r2.createCell(1);
        cAns2.setCellValue(mr.getSecAnswer2());
      
        Row r3 = s1.createRow(2);
        Cell CQue3 = r3.createCell(0);
        CQue3.setCellValue(mr.getSecQuestion3());
        Cell cAns3 = r3.createCell(1);
        cAns3.setCellValue(mr.getSecAnswer3());
        
        Row r4 = s1.createRow(3);
        Cell CQue4 = r4.createCell(0);
        CQue4.setCellValue(mr.getSecQuestion4());
        Cell cAns4 = r4.createCell(1);
        cAns4.setCellValue(mr.getSecAnswer4());
        
        Row r5 = s1.createRow(4);
        Cell CQue5 = r5.createCell(0);
        CQue5.setCellValue(mr.getSecQuestion5());
        Cell cAns5 = r5.createCell(1);
        cAns5.setCellValue(mr.getSecAnswer5());
        
        //Enroll button Click
        driver.findElement(By.id("btnEnroll")).click();
        
        Sheet s2 = wb.getSheet(SheetName2);
        Row r6 = s2.createRow(0);
        Cell mpNum = r6.createCell(0);
        mpNum.setCellValue(driver.findElement(By.className("mp-number")).getText());
        Cell password = r6.createCell(1);
        password.setCellValue(mr.getPassword());
        
        //Close input stream
        inputStream.close();
        
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(f);

        //Write data in the excel file
        wb.write(outputStream);

        //Close output stream
        outputStream.close();
           
        Thread.sleep(5000);
        
    }
}
