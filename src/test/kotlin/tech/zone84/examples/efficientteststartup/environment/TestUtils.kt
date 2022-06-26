package tech.zone84.examples.efficientteststartup.environment

import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val log: Logger = LoggerFactory.getLogger("")

fun step(name: String, step: () -> Unit) {
    log.info("Starting test step: '$name'")
    step.invoke()
    log.info("Finished test step: '$name'")
}

fun <R> step(name: String, step: () -> R): R {
    log.info("Starting test step: '$name'")
    val result = step.invoke()
    log.info("Finished test step: '$name' with return value: $result")
    return result
}
