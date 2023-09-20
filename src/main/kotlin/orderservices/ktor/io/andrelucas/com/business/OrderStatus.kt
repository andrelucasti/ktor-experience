package orderservices.ktor.io.andrelucas.com.business

enum class OrderStatus {
    CREATED,
    ARRIVED,
    PENDING,
    ON_THE_WAY,
    DELIVERED,
    PAYMENT_PENDING,
    PAYMENT_RECEIVED,
    CANCELED
}