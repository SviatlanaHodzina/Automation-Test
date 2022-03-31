package com.epam.autotesting.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class SearchResultsCloudGooglePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    public final String LINK_GOOGLE_CLOUD_PRICING_CALCULATOR_XPATH = "//a[@data-ctorig='https://cloud.google.com/products/calculator']/b";
    private final String LINK_GOOGLE_CLOUD_PRICING_CALCULATOR_HTTPS = "https://cloud.google.com/products/calculator";

    private final String DEFAULT_LOCATOR_XPATH = "//div[contains(@class, 'gsc-result gs-webResult')]/div[contains(@class,'gs-visibleUrl')";
    private final String CONTAINS_PART_LOCATOR_XPATH = " and contains (@text(), '%s']";

    @FindBy(xpath = "//h1[@class='devsite-search-title']")
    public static WebElement searchResults;

    @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']")
    WebElement pricingCalculatorLink;

    @FindBy(xpath = "//a[@href='https://cloud.google.com/products/calculator?hl=en']")
    WebElement pricingCalculatorHrefLink;

    @FindBy(xpath = "//div[@class='devsite-search-page-controls']")
    WebElement nextButton;

    @FindBy(xpath = "DEFAULT_LOCATOR_XPATH")
    private List<WebElement> generalSearchResults;

    @Override
    public SearchResultsCloudGooglePage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(searchResults));
        logger.info("You are in the 'Search results' Page");
        return this;
    }

    public SearchResultsCloudGooglePage countResultsNumberOfSearchLinks() {
        logger.info("Count of the Search results on the Page is :" + generalSearchResults.size());
        return this;
    }

    public PricingCalculatorPage linkToPricingCalculatorLink() {
            for (WebElement links : generalSearchResults) {
                if (driver.findElement(By.xpath(String.format(DEFAULT_LOCATOR_XPATH + CONTAINS_PART_LOCATOR_XPATH,
                        LINK_GOOGLE_CLOUD_PRICING_CALCULATOR_HTTPS))).isEnabled()) {
                    new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                            .until(ExpectedConditions.elementToBeClickable(pricingCalculatorLink)).click();
                } else {
                    new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                            .until(ExpectedConditions.elementToBeClickable(nextButton));
                }
            }

            logger.info("You are linked to 'Google Cloud Pricing Calculator Page'");
            return new PricingCalculatorPage();
    }
}