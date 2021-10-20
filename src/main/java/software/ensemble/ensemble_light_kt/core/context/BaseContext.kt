package software.ensemble.ensemble_light_kt.core.context

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Scopes
import software.ensemble.ensemble_light_kt.integrations.environment.Environment

/**
 * Base context with environment configurator
 */
class BaseContext : Module {

    override fun configure(binder: Binder) {
        with(binder) {
            bind(Environment::class.java).`in`(Scopes.SINGLETON)
        }
    }

}