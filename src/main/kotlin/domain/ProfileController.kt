package domain

import java.util.UUID

interface ProfileController {
    fun checkIfRegistered()
    fun registerUser(profile: Profile): UUID?
}