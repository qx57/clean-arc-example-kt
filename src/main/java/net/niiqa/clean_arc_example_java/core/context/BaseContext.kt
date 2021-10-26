package net.niiqa.clean_arc_example_java.core.context

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Scopes
import net.niiqa.clean_arc_example_java.integrations.environment.Environment

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