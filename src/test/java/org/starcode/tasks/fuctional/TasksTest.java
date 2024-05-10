package org.starcode.tasks.fuctional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver applicationAcess() throws MalformedURLException {
        // System.setProperty("webdriver.chrome.driver", "/home/kaio.almeida/Config/Drivers/chromedriver-linux64/chromedriver");
        // WebDriver driver = new ChromeDriver();

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://172.21.0.2:4444/wd/hub"), cap);

        driver.navigate().to("http://192.168.15.8:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = applicationAcess();

        try {
            // Clicar em Add TO-DO
            driver.findElement(By.id("addTodo")).click();

            // Escrever Descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever Data
            driver.findElement(By.id("dueDate")).sendKeys("01/10/2030");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            // Fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

        WebDriver driver = applicationAcess();

        try {
            // Clicar em Add TO-DO
            driver.findElement(By.id("addTodo")).click();

            // Escrever Data
            driver.findElement(By.id("dueDate")).sendKeys("01/10/2030");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);

        } finally {
            // Fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

        WebDriver driver = applicationAcess();

        try {
            // Clicar em Add TO-DO
            driver.findElement(By.id("addTodo")).click();

            // Escrever Descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);

        } finally {
            // Fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

        WebDriver driver = applicationAcess();

        try {
            // Clicar em Add TO-DO
            driver.findElement(By.id("addTodo")).click();

            // Escrever Descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever Data
            driver.findElement(By.id("dueDate")).sendKeys("01/10/2010");

            // Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);

        } finally {
            // Fechar o browser
            driver.quit();
        }

    }

}
