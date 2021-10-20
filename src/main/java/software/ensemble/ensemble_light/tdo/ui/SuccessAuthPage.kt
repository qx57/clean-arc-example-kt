package software.ensemble.ensemble_light.tdo.ui

import com.google.inject.Inject
import software.ensemble.ensemble_light.core.interfaces.UiWrapper

/**
 * This is Test Definition Object for UI page
 *
 * It used for define all of impact points
 * of real test pages
 */
class SuccessAuthPage<WE>() {

    /**
     * Inject the various web driver wrapper
     */
    @Inject
    lateinit var webDriver : UiWrapper<WE>

    /**
     * set all needed constants (like css, xpath etc)
     */
    companion object {
        const val MESSAGE_FIELD = "div.message"
    }

    /**
     * initiate page object state after loading
     */
    fun init() : SuccessAuthPage<WE> {
        webDriver.awaitVisible(30L, MESSAGE_FIELD)
        return this
    }

    /**
     * Something do
     */
    fun getMessage() : String? {
        return webDriver.getInnerText(
                webDriver.getElementByCss(MESSAGE_FIELD)!!
        )
    }
}