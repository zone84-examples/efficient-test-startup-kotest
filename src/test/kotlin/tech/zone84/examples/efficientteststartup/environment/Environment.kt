package tech.zone84.examples.efficientteststartup.environment

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import mu.KotlinLogging
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

object Environment {
    const val DATABASE_NAME = "mydb"

    private val log = KotlinLogging.logger { }
    private val mongoDb = MongoDBContainer(DockerImageName.parse("mongo:5.0.5"))
    private lateinit var client: MongoClient

    fun getMongoDbPort(): Int {
        return mongoDb.firstMappedPort
    }

    fun startServices() {
        mongoDb.start()
        client = MongoClients.create(mongoDb.replicaSetUrl)
        log.info { "Started MongoDB service on mapped port ${getMongoDbPort()}, replica set: '${mongoDb.replicaSetUrl}'" }
    }

    fun cleanUp() {
        log.info { "Dropping MongoDB collections before test..." }
        val database = client.getDatabase(DATABASE_NAME)
        database.listCollectionNames()
            .forEach {
                database.getCollection(it).drop()
            }
    }
}
