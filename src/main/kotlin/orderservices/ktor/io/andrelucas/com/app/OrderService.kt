package orderservices.ktor.io.andrelucas.com.app

import orderservices.ktor.io.andrelucas.com.business.Order
import orderservices.ktor.io.andrelucas.com.business.OrderItem
import orderservices.ktor.io.andrelucas.com.business.OrderRepository
import java.util.*

class OrderService (private val orderRepository: OrderRepository) {
    fun createOrder(orderRequest: OrderRequest) {
        val newOrder = Order.create(
                orderRequest.content,
                createOrderItemsBy(orderRequest.items),
                UUID.fromString(orderRequest.userId)
            )

        orderRepository.save(newOrder)
    }

    fun findAllOrders(): List<OrderResponse> {
        return orderRepository.findAll()
            .map {
                orderToOrderResponse(it)
            }
    }

    fun findOrderById(id: String): OrderResponse {
        return orderToOrderResponse(orderRepository.findById(UUID.fromString(id)))
    }

}

private fun createOrderItemsBy(orderItems: List<OrderItems>) =
    orderItems
        .map { OrderItem.create(it.content, it.price, it.quantity) }

private fun orderToOrderResponse(order: Order) = OrderResponse(
    order.uuid.toString(),
    order.description,
    order.price.toInt(),
    order.items.map { item -> OrderItemResponse(item.uuid.toString(), item.content, item.price, item.quantity!!) },
    order.status.toString(),
    order.userId.toString()
)
