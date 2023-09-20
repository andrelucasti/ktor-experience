package orderservices.ktor.io.andrelucas.com.repository

import org.jetbrains.exposed.sql.Table
import java.util.*

data class OrderItemEntity(val id: UUID,
                          val content: String,
                          val price: Int,
                          val quantity: Int,
                          val orderId: UUID)

object OrderItemTable : Table() {
    val id = uuid("id").autoGenerate()
    val content = varchar("content", 255)
    val price = integer("price")
    val quantity = integer("quantity")
    val orderId = uuid("orderId")


    override val primaryKey = PrimaryKey(id, name = "PK_OrderItem_ID")
    val order = reference("order", OrderTable.id, fkName = "FK_OrderItem_Order_ID")
}