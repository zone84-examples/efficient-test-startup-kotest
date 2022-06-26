package tech.zone84.examples.efficientteststartup.product

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.inject.Inject
import mu.KotlinLogging
import java.net.URI

@Controller("/products")
@ExecuteOn(TaskExecutors.IO)
class ProductEndpoint @Inject constructor(private val repository: ProductRepository) {
    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun insertProduct(@Body product: Product): HttpResponse<Void> {
        log.info { "Received product: $product" }
        val id = repository.save(product)
        return HttpResponse.created(URI.create(id))
    }

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun listProducts(): List<Product?>? {
        return repository.listAll()
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun fetchProduct(@PathVariable(name = "id") id: String?): Product? {
        return repository.find(id)
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}
