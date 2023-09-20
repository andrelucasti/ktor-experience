package orderservices.ktor.io.andrelucas.com.repository

import org.jetbrains.exposed.sql.Table
import java.util.*

data class OrderEntity(
    val id: UUID,
    val description: String,
    val price: Int,
    val status: String,
    val userId: UUID
)

object OrderTable : Table() {
    val id = uuid("id")
    val description = varchar("description", 255)
    val price = integer("price")
    val status = varchar("status", 255)
    val userId = uuid("userId")

    //TODO check how we can store jsonb in postgres with kotlin exposed or another library .... =/
    override val primaryKey = PrimaryKey(id, name = "PK_Order_ID")
}