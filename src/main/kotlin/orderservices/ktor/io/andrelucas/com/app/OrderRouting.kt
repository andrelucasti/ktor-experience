package orderservices.ktor.io.andrelucas.com.app

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureOrderRouting(orderService: OrderService){
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