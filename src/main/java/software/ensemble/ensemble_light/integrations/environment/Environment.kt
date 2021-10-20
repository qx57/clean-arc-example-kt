package software.ensemble.ensemble_light.integrations.environment

import software.ensemble.ensemble_light.core.interfaces.Env
import java.util.*
import kotlin.collections.HashMap

/**
 * Environment Configurator class
 */
class Environment : Env {

    /**
     * Env params
     */
    private var settings = HashMap<String, String>()

    /**
     * read env params from source
     */
    override fun readSettings(resource : String) {
        var inSettings = Environment::class.java.classLoader.getResourceAsStream(resource)
        var props = Properties()
        props.load(inSettings)
        props.propertyNames().toList().forEach { settings[it.toString()] = props.getProperty(it.toString()) }
    }

    /**
     * take the env params
     */
    override fun getEnvironmentSettings(): HashMap<String, String> {
        return settings
    }
}