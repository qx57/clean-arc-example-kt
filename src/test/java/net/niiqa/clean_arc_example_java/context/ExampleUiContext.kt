package net.niiqa.clean_arc_example_java.context

import com.google.inject.*
import org.openqa.selenium.WebElement
import net.niiqa.clean_arc_example_java.core.interfaces.UiWrapper
import net.niiqa.clean_arc_example_java.integrations.web_driver.UiWebDriver

/**
 * UI test context class
 */
class ExampleUiContext : Module {

    /**
     * All of dependings needed for test run
     * can be defined there (TDO must be binded if it not a part of test repository)
     */
    override fun configure(binder: Binder) {
        with(binder) {
            bind(object : TypeLiteral<UiWrapper<WebElement>>() {}).toInstance(UiWebDriver())
        }
    }
}