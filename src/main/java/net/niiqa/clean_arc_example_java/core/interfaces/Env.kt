package net.niiqa.clean_arc_example_java.core.interfaces

/**
 * Interface for Environment Configurator
 */
interface Env {

    /**
     * Read settings from source (like files, urls etc)
     */
    fun readSettings(resource : String)

    /**
     * Get Environment params
     */
    fun getEnvironmentSettings() : Map<String, String>
}