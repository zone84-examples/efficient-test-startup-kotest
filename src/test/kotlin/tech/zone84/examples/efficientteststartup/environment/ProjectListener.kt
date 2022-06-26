package tech.zone84.examples.efficientteststartup.environment

import io.kotest.core.listeners.BeforeProjectListener
import io.kotest.core.listeners.BeforeTestListener
import io.kotest.core.test.TestCase

object ProjectListener : BeforeProjectListener, BeforeTestListener {
    override suspend fun beforeProject() {
        Environment.startServices()
    }

    override suspend fun beforeTest(testCase: TestCase) {
        Environment.cleanUp()
    }
}
