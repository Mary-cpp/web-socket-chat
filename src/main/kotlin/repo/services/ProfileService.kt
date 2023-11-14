package repo.services

import domain.Profile
import domain.ProfileAuthDTO
import domain.ProfileController
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import repo.tables.UsersTable
import java.util.*

class ProfileService : ProfileController {
    override fun checkIfRegistered(profile: ProfileAuthDTO): Boolean {
        val encodedPass = Base64.getEncoder().encode(profile.password.toByteArray()).toString()
        var isUserRegistered = false
        transaction {
            isUserRegistered = UsersTable.select(UsersTable.login eq profile.login and(UsersTable.password eq encodedPass)).count() != 0L
        }
        return isUserRegistered
    }

    override suspend fun registerUser(profile: Profile): UUID? {
        var isLoginExists = false
        transaction {
            isLoginExists = UsersTable.select(UsersTable.login eq profile.login).count() != 0L
        }
        return if(isLoginExists) null
        else{
            transaction {
                UsersTable.insertAndGetId {
                    it[login] = profile.login
                    it[name] = profile.name
                    it[surname] = profile.surname
                    it[password] = Base64.getEncoder().encode(profile.password.toByteArray()).toString()
                }.value
            }
        }
    }
}