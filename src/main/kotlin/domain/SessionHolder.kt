package domain

import domain.model.Session
import org.slf4j.LoggerFactory
import java.util.*

class SessionHolder {
    private val sessions = emptySet<Session>()
    private val log by lazy { LoggerFactory.getLogger(this::class.java) }

    fun startSession(token: String){
        sessions.firstOrNull { it.token == token } ?: sessions.plus(Session(UUID.randomUUID(), token).run {
            log.info("Started new session: ${this.sessionId}")
        })
    }

    fun stopSession(token: String){
        sessions.firstOrNull { it.token == token }?.let {
            sessions.minus(it)
            log.info("Stopped session: ${it.sessionId}")
        }
    }

    fun checkSession(token: String): Boolean = sessions.firstOrNull { it.token == token }?.toString().toBoolean()
}