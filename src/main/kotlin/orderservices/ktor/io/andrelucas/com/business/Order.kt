package orderservices.ktor.io.andrelucas.com.business

import java.util.UUID

data class Order(val uuid: UUID,
                 val content: String,
                 val price: Int,
                 val items: List<OrderItem>,
                 var status: OrderStatus,
                 val userId: UUID) {
    companion object {
        fun create(description: String,
                   items: List<OrderItem>,
                   userId: UUID): Order {

            val totalPrice = items
                .sumOf { item -> item.price * item.quantity!! }

            return Order(UUID.randomUUID(), description, totalPrice, items, OrderStatus.CREATED, userId)
        }
        fun restore(uuid: UUID,
                    description: String,
                    price: Int,
                    items: List<OrderItem>,
                    status: OrderStatus,
                    userId: UUID): Order {

            return Order(uuid, description, price, items, status, userId)
        }

    }

    fun changeStatusToPending() {
        this.status = OrderStatus.PENDING
    }

    fun changeStatusToConfirmed() {
        if (this.status != OrderStatus.PENDING)
            throw OrderStateException("Order must be in PENDING state to be CONFIRMED")

        this.status = OrderStatus.CONFIRMED
    }

    fun getTotalItemsQuantity() = items.sumOf { it.quantity!! }

}