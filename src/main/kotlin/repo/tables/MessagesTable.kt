package repo.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MessagesTable : UUIDTable() {
    val author = reference("author", UsersTable, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val chat = reference("chat", ChatsTable, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val text = varchar("text", 2048)
    val datetime = datetime("datetime")
    val isRead = bool("isRead")
}