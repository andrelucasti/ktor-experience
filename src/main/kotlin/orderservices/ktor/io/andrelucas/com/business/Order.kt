package orderservices.ktor.io.andrelucas.com.business

import java.util.UUID

data class Order(val uuid: UUID,
                 val description: String,
                 val price: Double,
                 val items: List<OrderItem>,
                 val status: OrderStatus,
                 val userId: UUID) {

    companion object {
        fun create(description: String,
                   items: List<OrderItem>,
                   userId: UUID): Order {

            return Order(UUID.randomUUID(), description, 0.0, items, OrderStatus.CREATED, userId)
        }
    }

    override fun toString(): String {
        return " "
    }
}