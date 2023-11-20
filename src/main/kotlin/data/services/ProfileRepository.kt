package data.services

import domain.model.Profile
import domain.ProfileRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import data.tables.UsersTable
import java.util.*

class ProfileRepository : ProfileRepository {

    override fun findUserByLogin(login: String): Profile? {
        return transaction {
            UsersTable.select(where = (UsersTable.login eq login)).firstOrNull()?.let {
                Profile(name = it[UsersTable.name], surname = it[UsersTable.surname], login = it[UsersTable.login],
                    password = it[UsersTable.password])
            }
        }
    }

    override fun createUser(profile: Profile): UUID? {
        transaction {
            UsersTable.select(UsersTable.login eq profile.login).count() != 0L
        }.also { loginExists->
            return if(loginExists) null
            else transaction {
                UsersTable.insertAndGetId {
                    it[login] = profile.login
                    it[name] = profile.name
                    it[surname] = profile.surname
                    it[password] = profile.password
                }.value
            }
        }
    }
}