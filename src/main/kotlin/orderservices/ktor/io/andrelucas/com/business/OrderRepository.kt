package orderservices.ktor.io.andrelucas.com.business

import java.util.UUID

interface OrderRepository {
    fun save(order: Order)
    fun findById(id: UUID): Order
    fun findAll(): List<Order>
    fun update(order: Order)
    fun delete(order: Order)
}