package software.ensemble.ensemble_light_kt.tdo.api

import com.google.inject.Inject
import org.json.JSONObject
import software.ensemble.ensemble_light_kt.core.interfaces.BaseClient

/**
 * Test Object Definition class (like Endpoint1Obj.kt)
 *
 * There are different description of methods with Endpoint1Obj
 */
class Endpoint2Obj<T> {

    private val schema: String = "contracts/endpoint_2_swagger_schema.json"

    @Inject
    lateinit var client: BaseClient<T>

    /**
     * Request execution method with example of payload generation
     */
    fun request(baseUrl : String, processId : Int): T {
        client.configure(baseUrl, Endpoint2Obj::class.java.classLoader.getResource(schema).readText())
        val payload = JSONObject()
        payload.put("processId", processId)
        return client.execute(payload.toString())
    }
}