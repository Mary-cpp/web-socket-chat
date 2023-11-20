package domain

import domain.model.Profile
import java.util.*

interface ProfileController {

    val cypherService: CypherService
    fun findUserByLogin(login: String): Profile?
    suspend fun registerUser(profile: Profile): UUID?
}