package orderservices.ktor.io.andrelucas.com

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import orderservices.ktor.io.andrelucas.com.plugins.configureRouting
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class ApplicationTest {
    @Test
    fun shouldFindAndReturnAnOrderWhenItIsCreated() = testApplication {
        application {
            configureRouting()
        }
        client.post("/orders"){

            setBody("""
                {
                    "content": "Order 1",
                    "items": [
                        {
                            "content": "Item 1",
                            "price": 100,
                            "quantity": 1
                        },
                        {
                            "content": "Item 2",
                            "price": 200,
                            "quantity": 2
                        }
                    ],
                    "userId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
                }
            """.trimIndent())

        }.apply {
            assertEquals(HttpStatusCode.Created, status)
            assertEquals("", bodyAsText())
        }
    }
}
