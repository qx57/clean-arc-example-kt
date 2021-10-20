package software.ensemble.ensemble_light_kt.core

import com.google.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testng.annotations.*
import software.ensemble.ensemble_light_kt.core.context.BaseContext
import software.ensemble.ensemble_light_kt.integrations.environment.Environment
import kotlin.collections.HashMap

/**
 * Custom framework class
 *
 * Used for logging, some before/after common operations etc
 */
@Guice(modules = [BaseContext::class])
open class EnsembleCore() {

    private var logger: Logger = LoggerFactory.getLogger(EnsembleCore::class.java)

    /**
     * Inject of environment module
     *
     * Available in test classes
     */
    @Inject
    private lateinit var environment: Environment

    /**
     * Property with environment settings
     */
    protected var settings : Map<String, String> = HashMap()

    /**
     * Before suite method where we receive environment settings (from file, configserver etc)
     *
     * If you want to configure test environment on-air - use environment integration module directly
     */
    @BeforeSuite
    fun getEnv() {
        environment.readSettings("test_settings.properties")
        settings = environment.getEnvironmentSettings()
    }

    @BeforeMethod
    fun before() {
        /**
         * There you can write your code if it required
         * Also you can write all Before/After methods in core too
         * And also you can change base test framework (TesnTG to JUnit for example etc)
         */
    }

    /**
     * Utility methods also can be write there
     * Like this, for example
     */
    fun step(name: String) {
        logger.info(name)
    }
}