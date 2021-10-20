package software.ensemble.ensemble_light_kt.core.interfaces

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