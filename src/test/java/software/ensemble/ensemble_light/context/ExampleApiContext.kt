package software.ensemble.ensemble_light.context

import com.google.inject.*
import io.restassured.response.Response
import software.ensemble.ensemble_light.core.interfaces.BaseClient
import software.ensemble.ensemble_light.integrations.api_client.BaseClientRa
import software.ensemble.ensemble_light.tdo.api.Endpoint1Obj
import software.ensemble.ensemble_light.tdo.api.Endpoint2Obj

/**
 * API test context class
 */
class ExampleApiContext : Module {

    /**
     * All of dependings needed for test run
     * can be defined there
     */
    override fun configure(binder: Binder) {
        with(binder) {
            /**
             * Like API client...
             */
            bind(object: TypeLiteral<BaseClient<Response>>() {}).toInstance(BaseClientRa())
            /**
             * Or Test Definition Objects...
             */
            bind(object: TypeLiteral<Endpoint1Obj<Response>>() {})
            bind(object: TypeLiteral<Endpoint2Obj<Response>>() {})
            /**
             * And so more
             *
             * But basically you don't need bind TDO, only 3rd-party objects like client etc
             */
        }
    }
}