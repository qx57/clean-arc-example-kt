package net.niiqa.clean_arc_example_java.integrations.api_client

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification
import org.json.JSONException
import org.json.JSONObject
import net.niiqa.clean_arc_example_java.core.interfaces.BaseClient
import net.niiqa.clean_arc_example_java.integrations.api_client.helpers.HttpMethod

/**
 * Concrete realisation of
 * common API interface
 *
 * For example, here code for based on RestAssured client
 */
class BaseClientRa : BaseClient<Response> {

    /**
     * Request and response specifications for logging our requests
     */
    private var baseRequestSpec: RequestSpecification = RequestSpecBuilder().log(LogDetail.ALL).build()
    private var baseResponseSpec: ResponseSpecification = ResponseSpecBuilder().log(LogDetail.ALL).build()

    /**
     * Define URL, path and method
     */
    private var url: String = ""
    private var path: String = ""
    private var method: String = ""

    /**
     * Set default base header's values
     */
    private var accept: ContentType = ContentType.JSON
    private var contentType: ContentType = ContentType.JSON

    /**
     * Set empty other headers
     */
    private var headers: HashMap<String, String> = HashMap<String, String>()

    /**
     * Configuration for our client
     *
     * This method be used when you want to configure client for new request
     *
     * baseUrl - API url for current test
     * schema - Swagger schema in string (for set some client properties)
     */
    override fun configure(baseUrl: String, schema: String) {
        url = baseUrl
        var schemaJson = JSONObject(schema)
        path = schemaJson.getJSONObject("paths").keySet().stream().findFirst().get()
        method = schemaJson.getJSONObject("paths").getJSONObject(this.path).keySet().stream().findFirst().get().uppercase()
    }

    /**
     * Execution method for interface realisation
     *
     * There we create all of requests for our API
     * Use specifications for set your custom client as params as required your tests
     *
     * request - payload object for request body
     * type - object for body matching
     */
    override fun <R> execute(request: R?): Response {
        // Prepare request
        var response: Response
        var requestSpecification: RequestSpecification = baseRequestSpec.accept(accept)
        requestSpecification = if (request != null) requestSpecification.contentType(contentType).body(request) else requestSpecification
        // set headers and reponse specs
        val sender = RestAssured.given(requestSpecification.headers(headers), baseResponseSpec)
        // send request
        when (method) {
            HttpMethod.GET -> response = sender.get(url + path)
            HttpMethod.POST -> response = sender.post(url + path)
            HttpMethod.PUT -> response = sender.put(url + path)
            HttpMethod.PATCH -> response = sender.patch(url + path)
            HttpMethod.DELETE -> response = sender.delete(url + path)
            else -> response = sender.options(url + path)
        }
        return response
    }

    /**
     * Check the contract by Swagger scheme
     *
     * response - Rest Assured Response object
     * schema - Swagger schema in string (from resource file)
     * definitionName - name of definition object which we must check (from Swagger file)
     */
    override fun isRightSchema(response: Response, schema: String, definitionName: String): Boolean {
        var result = true

        // create schema for checking
        var defSchema = JSONObject()
        defSchema.append("$schema", "http://json-schema.org/draft-04/schema#")
        defSchema.append("type", "object")

        // set the properties and requires
        var schemaJson = JSONObject(schema)
        var properties: JSONObject = schemaJson.getJSONObject("definitions").getJSONObject(definitionName).getJSONObject("properties")
        defSchema.put("properties", properties)
        try {
            // set the required fields if it possible
            var required: JSONObject ?= schemaJson.getJSONObject("definitions").getJSONObject(definitionName)
                    .getJSONObject("required")
            defSchema.put("required", required)
        } catch (jex : JSONException) {}

        // check schema
        try {
            ((response.then() as ValidatableResponse).assertThat() as ValidatableResponse).body(JsonSchemaValidator.matchesJsonSchema(defSchema.toString()))
        } catch (var3: Exception) {
            result = false
        }
        return result
    }
}