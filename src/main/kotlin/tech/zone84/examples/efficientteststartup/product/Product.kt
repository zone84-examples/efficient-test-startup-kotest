package tech.zone84.examples.efficientteststartup.product

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import javax.validation.constraints.NotBlank

@Introspected
data class Product @BsonCreator constructor(
    @BsonId
    @JsonIgnore
    @BsonProperty("id")
    val id: ObjectId? = null,

    @NotBlank
    @JsonProperty
    @BsonProperty("name")
    val name: String,

    @NotBlank
    @JsonProperty
    @BsonProperty("description")
    val description: String
)
