package orderservices.ktor.io.andrelucas.com.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import orderservices.ktor.io.andrelucas.com.app.OrderRequest
import orderservices.ktor.io.andrelucas.com.app.OrderService
import orderservices.ktor.io.andrelucas.com.repository.OrderRepository


fun Application.configureRouting() {
    val orderService = OrderService(OrderRepository())

    routing {
        get("/orders") {
            call.respond(orderService.findAllOrders())

        }

        post("/orders") {
            val receive = call.receive<OrderRequest>()

            orderService.createOrder(receive)
                .let { call.respond(HttpStatusCode.Created) }
        }

        get("/orders/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter id not found")
            orderService.findOrderById(id)
                .let { call.respond(it) }
        }
    }
}