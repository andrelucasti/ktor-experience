package orderservices.ktor.io.andrelucas.com.app

import orderservices.ktor.io.andrelucas.com.repository.OrderInMemoryRepository
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderServiceIntegrationTest {
    private val orderService = OrderService(OrderInMemoryRepository())

    @Test
    fun shouldCreateNewOrder() {
        val userId = UUID.randomUUID();
        val orderRequest = OrderRequest(
            "Order 1",
            listOf(OrderItems("Item 1", 10, 2),
                OrderItems("Item 2", 10, 1)
            ),
            userId.toString()
        )

        orderService.createOrder(orderRequest)
        val orderResponse = orderService.findAllOrders().first()

        assert(orderResponse.id.isNotEmpty())
        assertEquals( userId.toString(), orderResponse.userId)
        assertEquals(30, orderResponse.totalPrice)
        assertEquals("Order 1", orderResponse.content)
        assertEquals(2, orderResponse.items.size)
        assertEquals(3, orderResponse.totalItemsQuantity)
        assertEquals("CREATED", orderResponse.status)
    }

}