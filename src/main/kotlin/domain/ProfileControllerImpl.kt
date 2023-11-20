package domain

import domain.model.Profile
import java.util.*

class ProfileControllerImpl(
    private val repository: ProfileRepository,
    override val cypherService: CypherService,
): ProfileController {

    override fun findUserByLogin(login: String): Profile? {
        return repository.findUserByLogin(login)
    }

    override suspend fun registerUser(profile: Profile): UUID? {
        return repository.createUser(profile)
    }
}