package tech.zone84.examples.efficientteststartup.article

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.inject.Inject
import mu.KotlinLogging
import java.net.URI

@Controller("/articles")
@ExecuteOn(TaskExecutors.IO)
class ArticleEndpoint @Inject constructor(private val repository: ArticleRepository) {
    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun publishArticle(@Body article: Article): HttpResponse<Void> {
        log.info { "Received article: $article" }
        val id = repository.save(article)
        return HttpResponse.created(URI.create(id))
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun listArticles(): List<Article?>? {
        return repository.listAll()
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun fetchArticle(@PathVariable(name = "id") id: String?): Article? {
        return repository.find(id)
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}
