package domain.model

import java.util.UUID

data class Session(
    val sessionId: UUID,
    val token: String
)
