import Permissions.READ
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserTest {

    companion object TestData {
        private val user1 = UserDTO("1109331", READ)
        private val user2 = UserDTO("1109333", Permissions.WRITE)
        private val user3 = UserDTO("1109334", Permissions.OWNER)
        private val user4 = UserDTO("1109335", Permissions.ADMIN)
        private val user5 = UserDTO("1109336", Permissions.ADMIN)
        private val user6 = UserDTO("1109337", READ)
        private val unknown = UserDTO("1109338", READ)

        private val testUsers = arrayListOf(user1, user2, user3, user4, user5, user6)
        private val testUsersWithRoleRead = arrayListOf(user1, user6)
    }

    @Before
    fun setup() {
        // add all users
        addAllUser(testUsers)
    }

    @After
    fun teardown() {
        // clear all users
        clearAllUser()
    }


    @Test
    fun `when getAllUsers then return all users`() {
        val expected = //add here the expected test data value
        val actual = //call getAllUsers()

        assertEquals("Should return $testUsers", expected, actual)
    }

    @Test
    fun `when GetUserById then return user`() {
        val expected = //add here the expected test data value
        val actual = //call getUserById()

        assertEquals("Should return $user3", expected, actual)
    }

    @Test
    fun `when GetUserById with unknown id then should return null value`() {
        val actual = //call getUserById

        assertNull("Should be null", actual)
    }

    @Test
    fun `when filter on permission is set to read then return two users`() {
        val expected = //add here the expected test data value
        val actual = //call filterUsersOnPermission()

        assertEquals("Should return $testUsersWithRoleRead", expected, actual)
    }

    @Test
    fun `when removing 'user1' then the repository should not contain that user anymore`() {
        //remove user
        //check that user is not a part anymore

// implement here your test
    }

}