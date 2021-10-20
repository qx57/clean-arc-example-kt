package software.ensemble.ensemble_light_kt.integrations.web_driver

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import software.ensemble.ensemble_light_kt.core.interfaces.UiWrapper
import java.time.Duration


class UiWebDriver : UiWrapper<WebElement> {

    private var driver : ChromeDriver ?= null

    /**
     * Web driver initialization
     *
     * You can move properties into settings file
     */
    init {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe")
        driver = ChromeDriver()
        driver?.manage()?.window()?.maximize()
        driver?.manage()?.timeouts()?.implicitlyWait(Duration.ofSeconds(10))
    }

    override fun loadPage(url: String) {
        driver?.get(url)
    }

    override fun getElementByCss(css: String) : WebElement? {
        return driver?.findElement(By.cssSelector(css))
    }

    override fun getElementByXpath(xpath: String): WebElement? {
        return driver?.findElement(By.xpath(xpath))
    }

    override fun setFieldValue(field: WebElement, value: String) {
        field.sendKeys(value)
    }

    override fun getFieldValue(field: WebElement): String {
        return field.getAttribute("value").toString()
    }

    override fun clickElement(el: WebElement) {
        el.click()
    }

    override fun getInnerText(el: WebElement) : String {
        return el.text
    }

    override fun awaitVisible(duration : Long, css : String) : Boolean {
        val wait = WebDriverWait(driver!!, Duration.ofSeconds(duration))
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)))
        return driver!!.findElement(By.cssSelector(css)).isDisplayed
    }

    override fun close() {
        driver?.close()
    }
}