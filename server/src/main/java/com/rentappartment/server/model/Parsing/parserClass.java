package com.rentappartment.server.model.Parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rentappartment.server.ServerApplication.applicationContext;

public abstract class parserClass {

    protected Document doc;
    protected static final String defaultString = "нет информации";
    protected static final Double defaultDouble = -1.0;
    protected static final Integer defaultInteger = -1;
    private static final Logger parserLogger = LoggerFactory.getLogger(EtagiParser.class);

    //Checks if the line consists only of digits
    protected boolean onlyDigits(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Gets a text prom the Element by its XPATH
    protected String getTextFromXpath(String xpath) {
        Elements elements;
        elements = doc.selectXpath(xpath);
        if (elements.size() > 0) {
            return elements.get(0).text();
        }
        return defaultString;
    }

    protected String getAttrFromXpath(String xpath, String attributeKey) {
        Elements elements;
        elements = doc.selectXpath(xpath);
        if (elements.size() > 0) {
            return elements.attr(attributeKey);
        }
        return defaultString;
    }

    //Gets a jsoup Document from URL when clicking buttons is not required
    protected Document documentFromUrl(String url) {
        try {
            return Jsoup.connect(url).maxBodySize(0).userAgent("").referrer("").get();
        }
        catch (IOException ex) {
            parserLogger.error("Unable to get document from url: " + url);
            return null;
        }
    }


    //Gets a jsoup Document from URL when clicking buttons is not required
    protected Document documentFromUrl(String url, int scrollBy) {
        applicationContext.getBean(WebDriver.class).get(url);
        try {
            JavascriptExecutor jse = (JavascriptExecutor)applicationContext.getBean(WebDriver.class);
            String script = "window.scrollBy(0," + scrollBy + ")";
            jse.executeScript("window.scrollBy(0, 5000)");
            String ps = applicationContext.getBean(WebDriver.class).getPageSource();
            return Jsoup.parse(ps);
        }
        catch (Exception ignored) {
            parserLogger.error("Next image button not found");
            return null;
        }
    }

    //Gets a jsoup Document from URL when clicking buttons is required
    protected Document documentFromUrl(String url, ArrayList<String> xpaths) {
        applicationContext.getBean(WebDriver.class).get(url);
        for (String xpath : xpaths) {
            try {
                JavascriptExecutor jse = (JavascriptExecutor)applicationContext.getBean(WebDriver.class);
                jse.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
                WebElement button =
                    (new WebDriverWait(applicationContext.getBean(WebDriver.class), Duration.ofSeconds(5)))
                            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                button.click();
            }
            catch (Exception exception) {
                parserLogger.info("Unable to click a button at xpath: " + xpath);
            }
        }
        try {
            String ps = applicationContext.getBean(WebDriver.class).getPageSource();
            return Jsoup.parse(ps);
        }
        catch (Exception exception) {
            parserLogger.error("Unable to get document from url: " + url);
            parserLogger.error(exception.getMessage());
            return null;
        }
    }

    //Get all double from string
    public static ArrayList<Double> getDoubleFromString(String line) {
        line = line.replace(",", ".");
        line = line.replace(" ", "");
        Pattern pt = Pattern.compile("\\d+.\\d+|\\d+");
        Matcher matcher = pt.matcher(line);
        ArrayList<Double> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(Double.valueOf(matcher.group()));
        }
        return result;
    }

    //Get all integers from string
    public static ArrayList<Integer> getIntegerFromString(String line) {
        Pattern pt = Pattern.compile("\\d+");
        Matcher matcher = pt.matcher(line);
        ArrayList<Integer> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(Integer.valueOf(matcher.group()));
        }
        return result;
    }

    //Get all integers from string
    public static ArrayList<Long> getLongFromString(String line) {
        Pattern pt = Pattern.compile("\\d+");
        Matcher matcher = pt.matcher(line);
        ArrayList<Long> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(Long.valueOf(matcher.group()));
        }
        return result;
    }

    public static String getStringValue(Object value) {
        if (value != null) {
            return String.valueOf(value);
        }
        else {
            return defaultString;
        }
    }

    public static Double getDoubleValue(Object value) {
        ArrayList<Double> result = getDoubleFromString((String) value);
        if (result.size() > 0) {
            return result.get(0);
        }
        else {
            return defaultDouble;
        }
    }

    public static Integer getIntegerValue(Object value) {
        ArrayList<Integer> result = getIntegerFromString((String) value);
        if (result.size() > 0) {
            return result.get(0);
        }
        else {
            return defaultInteger;
        }
    }

    public static String getTextFromElement(Elements elements) {
        if (elements.size() > 0) {
            return elements.get(0).text();
        }
        else {
            return defaultString;
        }
    }

    public static void writeToFile(String text, String[] filenames){
        if (filenames.length == 0) {
            filenames = new String[] {
                "notes.txt"
            };
        }

        for (String filename:filenames) {
            try( FileWriter writer = new FileWriter(filename, true)){
                writer.write(text + "\n");
                writer.flush();
            }
            catch(Exception exception){
                parserLogger.error("Unable to write to file: " + filename);
                System.out.println(exception.getMessage());
            }
        }
    }
}