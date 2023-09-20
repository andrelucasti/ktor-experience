package orderservices.ktor.io.andrelucas.com.repository

import orderservices.ktor.io.andrelucas.com.business.Order
import orderservices.ktor.io.andrelucas.com.business.OrderRepository
import java.util.UUID

class OrderInMemoryRepository: OrderRepository {

    private val data = mutableListOf<Order>()

    override fun save(order: Order) {
        data.add(order)
    }

    override fun findById(id: UUID): Order {
        return data.find { it.uuid == id } ?: throw Exception("Order not found")
    }

    override fun findAll(): List<Order> {
        return data
    }

    override fun update(order: Order) {
        TODO("Not yet implemented")
    }

    override fun delete(order: Order) {
        TODO("Not yet implemented")
    }
}