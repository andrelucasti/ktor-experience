package orderservices.ktor.io.andrelucas.com.app

import kotlinx.serialization.Serializable

@Serializable
data class OrderRequest(val content: String, val items: List<OrderItems>, val userId: String)

@Serializable
data class OrderItems(val content: String, val price: Int, val quantity: Int)