package net.niiqa.clean_arc_example_java.tdo.api

import com.google.inject.Inject
import net.niiqa.clean_arc_example_java.core.interfaces.BaseClient


/**
 * This is Test Definition Object for API endpoint
 *
 * It used for define all of impact points
 * of real test object
 */
class Endpoint1Obj<T>() {

    /**
     * Scheme from Swagger for current endpoint
     */
    private val schema: String = "contracts/endpoint_1_swagger_schema.json"

    /**
     * Inject the client and our test settings below
     */
    @Inject
    lateinit var client: BaseClient<T>

    /**
     * other params of test obj condition
     */
    private var response: T? = null

    /**
     * send request and save response for future operations
     *
     * baseUrl - test service url for current test environment (may be different for different test environments)
     */
    fun request(baseUrl : String): T {
        client.configure(baseUrl, Endpoint1Obj::class.java.classLoader.getResource(schema).readText())
        response = client.execute(null)
        return response as T
    }

    /**
     * Check the contract (by swagger schema)
     *
     * definitionName - name of definition in Swagger schema for body checking (may be different, see JSON in file)
     */
    fun isRightDefinition(definitionName: String): Boolean {
        return client.isRightSchema(response!!, Endpoint1Obj::class.java.classLoader.getResource(schema).readText(), definitionName)
    }
}