package tech.zone84.examples.efficientteststartup.product

import io.micronaut.core.annotation.NonNull
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface ProductRepository {
    fun listAll(): List<Product?>?
    fun find(id: String?): Product?
    fun save(@NonNull article: @NotNull @Valid Product?): String?
}
