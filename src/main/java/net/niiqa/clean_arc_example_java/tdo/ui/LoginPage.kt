package net.niiqa.clean_arc_example_java.tdo.ui

import com.google.inject.Inject
import net.niiqa.clean_arc_example_java.core.interfaces.UiWrapper

/**
 * This is Test Definition Object for UI page
 *
 * It used for define all of impact points
 * of real test pages
 */
class LoginPage<WE>() {

    /**
     * Inject the various web driver wrapper
     */
    @Inject
    lateinit var webDriver : UiWrapper<WE>

    /**
     * Page fields and elements
     *
     * You can hide field's implementations through CUSTOM annotations (like @FindBy for example)
     * Use AOP for beautifulize code
     */
    private var loginField : () -> WE? = { webDriver.getElementByCss("#login_field") }
    private var passwordField : () -> WE? = { webDriver.getElementByXpath("/html/body/form/input[2]") }
    private var formButton : () -> WE? = { webDriver.getElementByCss("#submit") }

    /**
     * Get signin page
     */
    fun loadPage(url : String) {
        webDriver.loadPage(url)
    }

    /**
     * Set the login field value
     */
    fun setLogin(login : String) {
        webDriver.setFieldValue(loginField.invoke()!!, login)
    }

    /**
     * Getter for login field
     */
    fun getLoginValue() : String? {
        return webDriver.getFieldValue(loginField.invoke()!!)
    }

    /**
     * Set the password field value
     */
    fun setPassword(pwd : String) {
        webDriver.setFieldValue(passwordField.invoke()!!, pwd)
    }

    /**
     * Getter for password field
     */
    fun getPasswordValue() : String? {
        return webDriver.getFieldValue(passwordField.invoke()!!)
    }

    /**
     * send signin form
     */
    fun sendForm() {
        webDriver.clickElement(formButton.invoke()!!)
    }
}