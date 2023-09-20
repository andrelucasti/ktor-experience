package orderservices.ktor.io.andrelucas.com.business

import org.junit.Assert
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class OrderTest {

    @Test
    fun shouldCreateAnOrderAtTheCreatedStateWhenItIsCreated() {
        val newOrder = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 1", 1),
                OrderItem(UUID.randomUUID(), "item 2", 2)
            ),
            UUID.randomUUID()
        )

        assertNotNull(newOrder.uuid)
        assertEquals(OrderStatus.CREATED, newOrder.status)
        newOrder.items
            .forEach { item ->
                assert(item.quantity!! > 0)
            }

        newOrder.items
            .forEach { item ->
                assertNotNull(item.uuid)
            }
    }

    @Test
    fun shouldCalculateOrderPriceWhenIsCreated(){
        val newOrder = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 1", 1),
                OrderItem(UUID.randomUUID(), "item 2", 2)
            ),
            UUID.randomUUID()
        )

        assertEquals(3, newOrder.price)

        val newOrder2 = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 3", 2, 2),
                OrderItem(UUID.randomUUID(), "item 4", 3, 2)
            ),
            UUID.randomUUID()
        )

        assertEquals(10, newOrder2.price)


        val newOrder3 = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 3", 200, 2),
                OrderItem(UUID.randomUUID(), "item 4", 100, 2)
            ),
            UUID.randomUUID()
        )

        assertEquals(600, newOrder3.price)

        val newOrder4 = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 3", 200),
                OrderItem(UUID.randomUUID(), "item 4", 100, 2)
            ),
            UUID.randomUUID()
        )

        assertEquals(400, newOrder4.price)
    }

    @Test
    fun shouldChangeOrderStateToPending(){
        val newOrder = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 1", 1),
                OrderItem(UUID.randomUUID(), "item 2", 2)
            ),
            UUID.randomUUID()
        )

        newOrder.changeStatusToPending()

        assertEquals(OrderStatus.PENDING, newOrder.status)
    }

    @Test
    fun shouldChangeOrderStateToConfirmedWhenItIsPending(){
        val newOrder = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 1", 1),
                OrderItem(UUID.randomUUID(), "item 2", 2)
            ),
            UUID.randomUUID()
        )

        newOrder.changeStatusToPending()
        newOrder.changeStatusToConfirmed()

        assertEquals(OrderStatus.CONFIRMED, newOrder.status)
    }

    @Test
    fun shouldThrowExceptionAtToTryingChangeOrderStateToPendingWhenTheLastStateIsDifferentFromPending(){
        val newOrder = Order.create(
            "Order 1",
            listOf(
                OrderItem(UUID.randomUUID(), "item 1", 1),
                OrderItem(UUID.randomUUID(), "item 2", 2)
            ),
            UUID.randomUUID()
        )

        Assert.assertThrows(OrderStateException::class.java) {
            newOrder.changeStatusToConfirmed()
        }
    }
}