package orderservices.ktor.io.andrelucas.com.business

import java.util.UUID

data class OrderItem (val uuid: UUID, val content: String, val price: Int, val quantity: Int ? = 1)  {

    companion object {
        fun create(content: String, price: Int, quantity: Int): OrderItem {
            return OrderItem(UUID.randomUUID(), content, price, quantity)
        }
    }
}