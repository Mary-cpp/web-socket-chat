package domain

import data.dto.ProfileAuthDTO
import java.util.UUID

interface ProfileController {

    val cypherService: CypherService
    fun findUserByLogin(login: String): Profile?
    suspend fun registerUser(profile: Profile): UUID?
}