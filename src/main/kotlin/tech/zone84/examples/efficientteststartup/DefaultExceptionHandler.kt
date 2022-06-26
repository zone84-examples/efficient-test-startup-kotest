package tech.zone84.examples.efficientteststartup

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import mu.KotlinLogging

@Produces
@Singleton
class DefaultExceptionHandler : ExceptionHandler<Exception?, HttpResponse<Void>> {
    override fun handle(request: HttpRequest<*>?, exception: Exception?): HttpResponse<Void> {
        log.error(exception) { "An error has occurred" }
        return HttpResponse.serverError()
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}
