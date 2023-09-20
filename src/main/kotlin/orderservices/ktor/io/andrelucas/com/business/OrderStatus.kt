package orderservices.ktor.io.andrelucas.com.business

enum class OrderStatus {
    CREATED,
    PENDING,
    CONFIRMED,
    IN_PREPARATION,
    READY_TO_DELIVER,
    ACCEPTED,
    ON_THE_WAY,
    DELIVERED,
    CANCELED
}