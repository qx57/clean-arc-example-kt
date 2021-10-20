package software.ensemble.ensemble_light.tests

import com.google.inject.Inject
import io.restassured.response.Response
import org.apache.http.HttpStatus
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.testng.annotations.*
import software.ensemble.ensemble_light.context.ExampleApiContext
import software.ensemble.ensemble_light.core.EnsembleCore
import software.ensemble.ensemble_light.tdo.api.Endpoint1Obj
import software.ensemble.ensemble_light.tdo.api.Endpoint2Obj

/**
 * Example test class for API tests
 *
 * For use concrete client's realisations create context
 */
@Guice(modules = [ExampleApiContext::class])
class ExampleApiTest: EnsembleCore() {

    /**
     * Inject th API Test Definition Objects (TDO's)
     */
    @Inject
    lateinit var endpoint1: Endpoint1Obj<Response>
    @Inject
    lateinit var endpoint2: Endpoint2Obj<Response>

    /**
     * There is the example test method
     * For API Request
     */
    @Test
    fun exampleApiTest() {
        step("Send request")
        var response = endpoint1.request(settings.get("service1.baseUrl." + settings.get("env")).toString())
        step("Check response 200 Ok")
        // For example use Assertj matcher
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK)
        step("Check contract")
        assertThat(endpoint1.isRightDefinition("StartSuccessDto")).isTrue()
        step("Check field id value")
        /**
         * Use default Rest Assured matcher (but this rise code connectivity)
         * Better define getter in TDO for receive value from various field and use third-party matcher
         */
        response.then().body("processId", CoreMatchers.notNullValue())
        step("send next request and check 201 Created")
        response = endpoint2.request(
                settings.get("service1.baseUrl." + settings.get("env")).toString(),
                response.then().extract().jsonPath().getInt("processId")
        )
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED)
    }
}