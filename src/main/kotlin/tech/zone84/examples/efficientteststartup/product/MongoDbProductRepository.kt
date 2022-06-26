package tech.zone84.examples.efficientteststartup.product

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import tech.zone84.examples.efficientteststartup.MongoDbConfiguration

@Singleton
class MongoDbProductRepository @Inject constructor(
    private val client: MongoClient,
    private val configuration: MongoDbConfiguration
) :
    ProductRepository {
    override fun listAll(): List<Product?>? {
        return acquireCollection().find().into<ArrayList<Product?>>(ArrayList())
    }

    override fun find(id: String?): Product? {
        val query = BasicDBObject()
        query["_id"] = ObjectId(id)
        return acquireCollection().find(query).first()
    }

    override fun save(product: Product?): String? {
        return acquireCollection().insertOne(product).insertedId.asObjectId().value.toHexString()
    }

    private fun acquireCollection(): MongoCollection<Product?> {
        return client.getDatabase(configuration.databaseName)
            .getCollection(
                configuration.productCollectionName,
                Product::class.java
            )
    }
}
