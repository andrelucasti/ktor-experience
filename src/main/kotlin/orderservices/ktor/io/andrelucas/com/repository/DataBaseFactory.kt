package orderservices.ktor.io.andrelucas.com.repository

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {

    fun init() {
        val driveClassName: String = "org.postgresql.Driver"
        val databaseUrl: String = "jdbc:postgresql://localhost:5432/order"
        // For now ... But, after these tests I'm gonna move this configuration to an application.properties file
        val database: Database = Database.connect(databaseUrl, driveClassName, "order", "order")

        transaction(database) {
           SchemaUtils.create(OrderTable, OrderItemTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}