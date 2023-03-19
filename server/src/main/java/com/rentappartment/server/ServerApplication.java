package com.rentappartment.server;

import com.rentappartment.server.model.Filter.FilterDao;
import com.rentappartment.server.model.Image.ImageDao;
import com.rentappartment.server.model.Offer.OfferDao;
import com.rentappartment.server.model.Parsing.EtagiParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ServerApplication {

	public static ApplicationContext applicationContext;

	@Bean
	public WebDriver webDriver() {
		String pathToGeckoDriver = "./geckodriver.exe";
		String pathToChromeDriver = "./chromedriver.exe";
		System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
		System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--remote-allow-origins=*", "--window-size=1920,1200","--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ServerApplication.class, args);
		applicationContext.getBean(FilterDao.class).updateFilters();
		Date date = new Date();
		new EtagiParser().parse();
		applicationContext.getBean(OfferDao.class).deleteOldOffers(date);

		//applicationContext.getBean(WebDriver.class).close();
	}
}
