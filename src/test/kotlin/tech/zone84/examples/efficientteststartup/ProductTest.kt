package tech.zone84.examples.efficientteststartup

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.micronaut.http.HttpStatus
import jakarta.inject.Inject
import tech.zone84.examples.efficientteststartup.environment.step
import tech.zone84.examples.efficientteststartup.product.Product

class ProductTest @Inject constructor(client: ApplicationClient): BaseIntegrationSpec({
    should("save and read products") {
        // given
        val someProduct = Product(name = "Some name", description = "Some description")

        val id = step<String>("Save product") {
            // when
            val response = client.saveProduct(someProduct)

            // then
            response.status shouldBe HttpStatus.CREATED
            val location = response.header("location")
            location.shouldNotBeNull()
                .shouldNotBeEmpty()
            location
        }

        step("Fetch previously created product") {
            // when
            val response = client.fetchProduct(id)

            // then
            response.status shouldBe HttpStatus.OK
            val actual = response.body()
            actual.name shouldBe someProduct.name
            actual.description shouldBe someProduct.description
        }
    }
})
