import java.util.stream.Collectors

private val userRepo = ArrayList<UserDTO>()

fun addAllUser(user: ArrayList<UserDTO>): Boolean {
    return userRepo.addAll(user)
}

fun getAllUsers(): List<UserDTO> {
    return userRepo
}

fun getUserById(id: String): UserDTO? {
    return userRepo.find { it.id == id }
}

fun containUser(user: UserDTO): Boolean {
    return userRepo.contains(user)
}

fun filterUsersOnPermission(permissions: Permissions): List<UserDTO> {
    return userRepo.stream().filter { it -> it.permissions == permissions }.collect(Collectors.toList())
}

fun removeUser(user: UserDTO): Boolean {
    return userRepo.remove(user)
}

fun clearAllUser() {
    userRepo.clear()
}

data class UserDTO(val id: String, val permissions: Permissions)

enum class Permissions {
    ALL, ADMIN, OWNER, READ, WRITE
}