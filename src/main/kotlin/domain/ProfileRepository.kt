package domain

import java.util.UUID

interface ProfileRepository {
    fun findUserByLogin(login: String): Profile?
    fun createUser(profile: Profile): UUID?
}