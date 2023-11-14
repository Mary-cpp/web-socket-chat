
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import repo.DbUtils
import repo.services.ProfileService
import routes.userRoutes
import java.time.Duration


fun main() {
    DbUtils().connect("databaseConfig.json").apply { DbUtils.migration() }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureAuth()
        configureWebSockets()
        usersModule()
    }.start(wait = true)
}

fun Application.configureAuth() {
    install(Authentication) {
        basic(name = "auth-basic") {
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}

fun Application.configureWebSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
}

fun Application.configureSerialization() {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
            }
        )
    }
}

fun Application.usersModule() {
    routing {
        userRoutes(ProfileService())
    }
}