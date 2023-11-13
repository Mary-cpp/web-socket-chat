package repo.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object UsersTable : UUIDTable() {
    val name = varchar("name",128)
    val surname = varchar("surname", 128)
    val login = varchar("login", 30)
    val password = varchar("password", 16)
}