package data.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ChatAbonentsTable: LongIdTable() {
    val user = reference("user", UsersTable, onDelete = ReferenceOption.NO_ACTION, ReferenceOption.NO_ACTION)
    val chat = reference("chat", ChatsTable, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.NO_ACTION)
}