package software.ensemble.ensemble_light_kt.core.interfaces

/**
 * UI Web driver wrapper interface
 *
 * There are some methods for all of cases in test or test steps
 * There is no final list of methods, in your project you can change it for your requirements
 */
interface UiWrapper<WE> {

    /**
     * load the page
     */
    fun loadPage(url : String)

    /**
     * find element in DOM by css selector
     */
    fun getElementByCss(css : String) : WE?

    /**
     * find element in DOM by xpath
     */
    fun getElementByXpath(xpath : String) : WE?

    /**
     * set various field value (for filling forms, for example)
     */
    fun setFieldValue(field : WE, value : String)

    /**
     * get field value (if you want check the field filling)
     */
    fun getFieldValue(field : WE) : String?

    /**
     * Click on various DOM element
     */
    fun clickElement(el : WE)

    /**
     * get innet text from various DOM element
     */
    fun getInnerText(el : WE) : String?

    /**
     * one of awaitings
     */
    fun awaitVisible(duration : Long, css : String) : Boolean

    /**
     * close session for web driver instance
     */
    fun close()
}