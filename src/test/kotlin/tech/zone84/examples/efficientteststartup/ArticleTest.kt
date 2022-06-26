package tech.zone84.examples.efficientteststartup

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.micronaut.http.HttpStatus
import jakarta.inject.Inject
import tech.zone84.examples.efficientteststartup.article.Article
import tech.zone84.examples.efficientteststartup.environment.step

class ArticleTest @Inject constructor(client: ApplicationClient): BaseIntegrationSpec({
    should("save and read articles") {
        // given
        val someArticle = Article(name = "Some name", content = "Some content")

        val id = step<String>("Save article") {
            // when
            val response = client.saveArticle(someArticle)

            // then
            response.status shouldBe HttpStatus.CREATED
            val location = response.header("location")
            location.shouldNotBeNull()
                .shouldNotBeEmpty()
            location
        }

        step("Fetch previously created article") {
            // when
            val response = client.fetchArticle(id)

            // then
            response.status shouldBe HttpStatus.OK
            val actual = response.body()
            actual.name shouldBe someArticle.name
            actual.content shouldBe someArticle.content
        }
    }
})
