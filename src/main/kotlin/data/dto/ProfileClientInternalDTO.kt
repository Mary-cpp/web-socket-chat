package data.dto

/**
 * This class represents all-known representations of user in client:
 * most common fields that we need to see - name and surname
 *
 * created for convenient representation of User in chats, contacts
 *
 * @param nameAndSurname contains them not separated
 */
data class ProfileClientInternalDTO(
    val nameAndSurname: String
)
