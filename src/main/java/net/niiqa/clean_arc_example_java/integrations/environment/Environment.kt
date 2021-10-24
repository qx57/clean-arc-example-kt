package net.niiqa.clean_arc_example_java.integrations.environment

import net.niiqa.clean_arc_example_java.core.interfaces.Env
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
    override fun getEnvironmentSettings(): Map<String, String> {
        return settings
    }
}