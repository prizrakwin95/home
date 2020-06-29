import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pojo.YandexAdrees;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SeleniumCode implements AutoCloseable {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public LocalTime getTimeToDestination(YandexAdrees yandexAdrees){
        String site = yandexAdrees.getSite();

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");

        this.driver = new ChromeDriver(options);

        driver.get(site);

//        auto-route-snippet-view
//        String text = driver.findElement(By.className("driving-route-view__route-title-primary")).getText();
        String text = driver.findElement(By.className("auto-route-snippet-view__route-title-primary")).getText();
        System.out.println(text);
        text = text.replaceAll("\\s","");
        System.out.println(text.contains("ч"));
        System.out.println(text.contains("мин"));
        boolean isHours = text.contains("ч");
        boolean isMinutes =  text.contains("мин");
        Integer hours = 0;
        if(isHours){
            hours = Integer.valueOf(text.split("ч")[0]);
        }
        System.out.println("hours - "+hours);
        Integer min = 0;
        if(isHours && isMinutes){
            min = Integer.valueOf(text.split("ч")[1].split("мин")[0]);
        }
        if(!isHours && isMinutes){
            min = Integer.valueOf(text.split("мин")[0]);
        }
        System.out.println("min - "+min);
        LocalTime localTime = LocalTime.of(hours,min);
        System.out.println("localTime - " + localTime);
//        String[] hourssplit = text.split("ч");

//        System.out.println("hourssplit = "+hourssplit.length+" - "+hourssplit[0]);
//        if(hourssplit.length>1){
//            hours = Integer.parseInt(hourssplit[0]);
//        }

//        String[] minutes = text.split("мин");
//        System.out.println("minutes = "+minutes.length+" - "+minutes[0]);
//        if(minutes.length>1){
//            min = Integer.parseInt(minutes[0]);
//        }
//        System.out.println(hours+" - "+min);


//        driver.close();
        driver.quit();
//        driver.

//        try {
//            System.exit(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return localTime;
    }


    @Override
    public void close() throws Exception {
        this.driver.quit();

//        this.driver.quit();
    }
}
