package tech.zone84.examples.efficientteststartup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import tech.zone84.examples.efficientteststartup.article.Article
import tech.zone84.examples.efficientteststartup.product.Product

@Client("/")
interface ApplicationClient {
    @Post("/articles")
    fun saveArticle(@Body article: Article?): HttpResponse<Void>

    @Get("/articles/{id}")
    @Consumes("application/json")
    fun fetchArticle(@PathVariable id: String): HttpResponse<Article>

    @Post("/products")
    fun saveProduct(@Body product: Product): HttpResponse<Void>

    @Get("/products/{id}")
    @Consumes("application/json")
    fun fetchProduct(@PathVariable id: String): HttpResponse<Product>
}
