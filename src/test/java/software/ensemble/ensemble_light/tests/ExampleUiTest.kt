package software.ensemble.ensemble_light.tests

import com.google.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebElement
import org.testng.annotations.*
import software.ensemble.ensemble_light.context.ExampleUiContext
import software.ensemble.ensemble_light.core.EnsembleCore
import software.ensemble.ensemble_light.core.interfaces.UiWrapper
import software.ensemble.ensemble_light.tdo.ui.LoginPage
import software.ensemble.ensemble_light.tdo.ui.SuccessAuthPage

/**
 * Example test class for UI tests with Selenium web driver
 *
 * For use concrete client's realisations create context
 */
@Guice(modules = [ExampleUiContext::class])
class ExampleUiTest : EnsembleCore() {

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
        settings.forEach { println("SET: $it") }
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