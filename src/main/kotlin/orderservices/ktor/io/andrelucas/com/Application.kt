package orderservices.ktor.io.andrelucas.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.resources.*
import orderservices.ktor.io.andrelucas.com.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureResource()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    configureSerialization()
}
