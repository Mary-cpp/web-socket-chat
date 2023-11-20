package domain

import domain.model.Profile
import java.util.UUID

interface ProfileRepository {
    fun findUserByLogin(login: String): Profile?
    fun createUser(profile: Profile): UUID?
}