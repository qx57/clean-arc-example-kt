package software.ensemble.ensemble_light_kt.core.interfaces

/**
 * Example of common API clients interface
 *
 * There are:
 * - configuration for client
 * - request execution
 * - contract checking (in isRightSchema method)
 */
interface BaseClient<T> {

    fun configure(baseUrl : String, schema : String)

    fun <R> execute(request: R?): T

    fun isRightSchema(response : T, schema : String, definitionName : String) : Boolean
}