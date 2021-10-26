package net.niiqa.clean_arc_example_java.tests

import com.google.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebElement
import org.testng.annotations.*
import net.niiqa.clean_arc_example_java.context.ExampleUiContext
import net.niiqa.clean_arc_example_java.core.FrameworkCore
import net.niiqa.clean_arc_example_java.core.interfaces.UiWrapper
import net.niiqa.clean_arc_example_java.tdo.ui.LoginPage
import net.niiqa.clean_arc_example_java.tdo.ui.SuccessAuthPage

/**
 * Example test class for UI tests with Selenium web driver
 *
 * For use concrete client's realisations create context
 */
@Guice(modules = [ExampleUiContext::class])
class ExampleUiTest : FrameworkCore() {

    /**
     * Inject web driver (or wrapper) for session control possibility
     */
    @Inject
    lateinit var webDriver : UiWrapper<WebElement>
    /**
     * Inject the page objects from context (basically you don't need add it to context class, only 3rd-party objects)
     */
    @Inject
    lateinit var loginPage : LoginPage<WebElement>
    @Inject
    lateinit var successPage : SuccessAuthPage<WebElement>

    /**
     * Test data for example UI test
     *
     * If needed you can push it to test settings
     */
    companion object {
        const val LOGIN : String = "example_login"
        const val PASSWD : String = "very_strong_password"
    }

    /**
     * !!! THIS IS A FEATURE !!!
     *
     * Have no idea why we get empty settings when tests started by **mvn test**
     */
    @BeforeClass
    fun initEnv() {
        super.getEnv()
    }

    /**
     * There is the example test method
     * For UI tests
     */
    @Test
    fun exampleUITest() {
        step("Load main page")
        loginPage.loadPage(settings.get("login_page.url." + settings.get("env")).toString())
        step("Set login n password fields")
        loginPage.setLogin(LOGIN)
        assertThat(loginPage.getLoginValue()).isEqualTo(LOGIN)
        loginPage.setPassword(PASSWD)
        assertThat(loginPage.getPasswordValue()).isEqualTo(PASSWD)
        step("Send auth form")
        loginPage.sendForm()
        step("Check authorization")
        assertThat(successPage.init().getMessage()).isEqualTo("Access granded!")
    }

    /**
     * Close sessions manually
     */
    @AfterClass
    fun deinit() {
        webDriver.close()
    }
}