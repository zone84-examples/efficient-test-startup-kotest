package tech.zone84.examples.efficientteststartup.article

import io.micronaut.core.annotation.NonNull
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface ArticleRepository {
     fun listAll(): List<Article?>?
     fun find(id: String?): Article?
     fun save(@NonNull article: @NotNull @Valid Article?): String?
}
