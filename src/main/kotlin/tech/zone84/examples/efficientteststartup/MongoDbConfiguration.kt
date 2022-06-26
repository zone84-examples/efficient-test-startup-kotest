package tech.zone84.examples.efficientteststartup

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.core.annotation.NonNull

@ConfigurationProperties("repository")
interface MongoDbConfiguration {
    @get:NonNull
    val databaseName: String?

    @get:NonNull
    val articleCollectionName: String?

    @get:NonNull
    val productCollectionName: String?
}
