package software.ensemble.ensemble_light.core.context

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Scopes
import software.ensemble.ensemble_light.integrations.environment.Environment

class BaseContext : Module {
    override fun configure(binder: Binder) {
        with(binder) {
            bind(Environment::class.java).`in`(Scopes.SINGLETON)
        }
    }

}