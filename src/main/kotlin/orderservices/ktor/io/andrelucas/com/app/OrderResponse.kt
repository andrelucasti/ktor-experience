package orderservices.ktor.io.andrelucas.com.app

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(val id: String, val content: String, val total: Int,val items: List<OrderItemResponse>, val status: String, val userId: String)

@Serializable
data class OrderItemResponse(val id: String, val content: String, val price: Int, val quantity: Int)