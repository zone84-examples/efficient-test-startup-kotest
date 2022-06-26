package tech.zone84.examples.efficientteststartup

import io.kotest.core.spec.style.ShouldSpec
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest
abstract class BaseIntegrationSpec(body: ShouldSpec.() -> Unit) : ShouldSpec(body)
