package domain

import java.util.UUID

interface ProfileController {
    fun checkIfRegistered(profile: ProfileAuthDTO) : Boolean
    suspend fun registerUser(profile: Profile): UUID?
}