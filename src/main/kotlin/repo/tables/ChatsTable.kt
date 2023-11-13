package repo.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object ChatsTable: UUIDTable() {
    val name = varchar("name",512)
}