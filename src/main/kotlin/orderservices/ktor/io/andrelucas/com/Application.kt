package orderservices.ktor.io.andrelucas.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import orderservices.ktor.io.andrelucas.com.app.OrderService
import orderservices.ktor.io.andrelucas.com.app.configureOrderRouting
import orderservices.ktor.io.andrelucas.com.plugins.configureHTTP
import orderservices.ktor.io.andrelucas.com.plugins.configureMonitoring
import orderservices.ktor.io.andrelucas.com.plugins.configureResource
import orderservices.ktor.io.andrelucas.com.plugins.configureSerialization
import orderservices.ktor.io.andrelucas.com.repository.DataBaseFactory
import orderservices.ktor.io.andrelucas.com.repository.OrderInMemoryRepository

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DataBaseFactory.init()
    configureResource()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureOrderRouting(OrderService(OrderInMemoryRepository()))
}
