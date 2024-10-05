import org.junit.Assert.assertEquals
import org.junit.Test

class Test {
    @Test
    fun testSolution() {

        val route = Route()
        val response = route.reply(object:Message<In>{
            override fun body(): In {
               return In("hello")
            }
        })

        val expected = Out("hello", "success")
        val actual = response.body()

        assertEquals(expected, actual)
    }
}