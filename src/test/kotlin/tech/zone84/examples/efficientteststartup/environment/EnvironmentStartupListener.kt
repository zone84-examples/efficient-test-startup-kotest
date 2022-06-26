package tech.zone84.examples.efficientteststartup.environment

import io.micronaut.context.env.ActiveEnvironment
import io.micronaut.context.env.PropertySource
import io.micronaut.context.env.PropertySourceLoader
import io.micronaut.core.io.ResourceLoader
import java.io.IOException
import java.io.InputStream
import java.util.*

class EnvironmentStartupListener : PropertySourceLoader {
    override fun load(resourceName: String, resourceLoader: ResourceLoader?): Optional<PropertySource> {
        return if (resourceName == "application") {
            Optional.of(
                PropertySource.of(
                    mapOf(
                        "mongodb.uri" to "mongodb://localhost:" + Environment.getMongoDbPort()
                    )
                )
            )
        } else Optional.empty()
    }

    override fun loadEnv(
        resourceName: String?,
        resourceLoader: ResourceLoader?,
        activeEnvironment: ActiveEnvironment?
    ): Optional<PropertySource> {
        return Optional.empty()
    }

    @Throws(IOException::class)
    override fun read(name: String?, input: InputStream?): Map<String?, Any?>? {
        return null
    }
}