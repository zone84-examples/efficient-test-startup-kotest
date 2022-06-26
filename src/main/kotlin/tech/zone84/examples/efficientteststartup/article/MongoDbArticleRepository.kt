package tech.zone84.examples.efficientteststartup.article

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import tech.zone84.examples.efficientteststartup.MongoDbConfiguration

@Singleton
class MongoDbArticleRepository @Inject constructor(
    private val client: MongoClient,
    private val configuration: MongoDbConfiguration
) :
    ArticleRepository {
    override fun listAll(): List<Article?>? {
        return acquireCollection().find().into(ArrayList())
    }

    override fun find(id: String?): Article? {
        val query = BasicDBObject()
        query["_id"] = ObjectId(id)
        return acquireCollection().find(query).first()
    }

    override fun save(article: Article?): String? {
        return acquireCollection().insertOne(article).insertedId.asObjectId().value.toHexString()
    }

    private fun acquireCollection(): MongoCollection<Article?> {
        return client.getDatabase(configuration.databaseName)
            .getCollection(configuration.articleCollectionName, Article::class.java)
    }
}
